import java.io.*;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estado implements Serializable {
    Map<String, Equipa> equipas;//nome, equipa
    Map<Integer, Jogador> jogadores; //numero, jogador
    List<Jogo> jogos;

    public Estado() {
    equipas = new HashMap<>();
    jogadores = new HashMap<>();
    jogos = new ArrayList<>();
    }

    public Estado(Map<String,Equipa> equipas, Map<Integer,Jogador> jogadores, List<Jogo> jogos) {
        equipas = new HashMap<>(equipas);
        jogadores = new HashMap<>(jogadores);
        jogos = new ArrayList<>(jogos);
    }

    public boolean isPlayer(String nome) {
        return jogadores.values().stream().anyMatch(f-> f.nameEquals(nome));
    }
    public Jogador getJogadorByName(String nome){
        return jogadores.values().stream().filter(f->f.nameEquals(nome)).findAny().get();
    }

    public String getTeamNameifPlayerPresent(Jogador jog) {
        for (Equipa t : equipas.values()) {
            if (t.getJogadores().stream().anyMatch(f->f.nameEquals(jog.getNomeJogador()))) return t.getNome();
        }
        throw new InvalidParameterException("getTeamNameifPlayerPresent == false");
    }

    public boolean isPlayerinAnyTeam(Jogador jog){
        return equipas.values().stream().anyMatch(f->f.getJogadores().contains(jog));
    }

    public Map<String, Equipa> getEquipas() {
        return equipas;
    }

    public void setEquipas(Map<String, Equipa> equipas) {
        this.equipas = equipas;
    }

    public Map<Integer, Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(Map<Integer, Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }
    /*
    public void debug(){
        for (Equipa Ee: this.equipas.values()){
            System.out.println(Ee.toString());
        }
        for (Jogo jog: this.jogos){
            System.out.println(jog.toString());
        }
    }*/

    /**
     * Metodo que dado um jogo decide um vencedor dependendo dos ratings das equipas levadas para o jogo, este metodo
     * adiciona o jogo criado ao estado
     * @param ec    String equipa Casa
     * @param ef    String equipa Visitante
     * @param d     Data do jogo (caso seja null sera introduzida a data atual)
     * @param jc    lista de jogadores da equipa da Casa titulares
     * @param sc    Map das substituições a fazer da equipa da Casa
     * @param jf    Map das substituições a fazer da equipa Visitante
     * @param sf    lista de jogadores da equipa Visitante titulares
     * @return  O Jogo após ter sido jogado
     */
    public Jogo Jogar (String ec,String ef, LocalDate d, List<Integer> jc,Map<Integer, Integer> sc, List<Integer> jf, Map<Integer, Integer> sf) throws EmptyParameterException {
        int rCasa,rFora,rCasaSub,rForaSub,vantagemCasa,vantagemFora;double igualdade;
        int gc=0,gf=0;
        if (d==null) d = LocalDate.now();
        if(ec.equals("")||ef.equals("")||jc.isEmpty()||jf.isEmpty())throw new EmptyParameterException("Parametro em falta em Estado.Jogar");


        Equipa casa = this.equipas.get(ec);
        Equipa fora = this.equipas.get(ef);

        rCasa = casa.ratingJogadores(jc);
        rFora = fora.ratingJogadores(jf);
        List<Integer> casaSubed;
        List<Integer> foraSubed;
        if(!sc.isEmpty()) {
            casaSubed= substitui(jc,sc);
            rCasaSub = casa.ratingJogadores(casaSubed);
        }else rCasaSub=rCasa;
        if(!sf.isEmpty()){
            foraSubed= substitui(jf,sf);
            rForaSub = fora.ratingJogadores(foraSubed);
        }else rForaSub =rFora;

        int rCasaTotal = (rCasaSub+rCasa)/2;
        int rForaTotal = (rForaSub+rFora)/2;

        int dif = rCasaTotal - rForaTotal;
        int numGolos = (int)(Math.random()*6+1); //1d6

        while(numGolos>0 && (dif>0)){ //vantagem casa
            if (roll(dif)) gc++;
            else gf++;
            numGolos--;
        }

        while(numGolos>0 && (dif==0)){
            igualdade =Math.random();
            if (igualdade>0.5) gc++;
            else gf++;
            numGolos--;
        }

        while(numGolos>0 && (dif<0)){ // vantagem visitante
            if (roll(Math.abs(dif))) gf++;
            else gc++;
            numGolos--;
        }
        Jogo jogado = new Jogo(ec,ef,gc,gf,d,jc,sc,jf,sf);
        this.jogos.add(jogado);
        return jogado;
    }

    /**
     * Metodo que dado um jogo decide um vencedor dependendo dos ratings das equipas levadas para o jogo
     * ATENÇÃO este metodo não insere o jogo no Estado!
     * @param game o Jogo a ser jogado
     * @return o Jogo após ter sido jogado
     */
    public Jogo Jogar (Jogo game){

        int rCasa,rFora,rCasaSub,rForaSub,vantagemCasa,vantagemFora;double igualdade;
        List<Integer> jc,jf; Map<Integer,Integer> sc,sf;
        jc = game.getJogadoresCasa();
        sc = game.getSubstituicoesCasa();
        jf = game.getJogadoresFora();
        sf = game.getSubstitucoesFora();
        game.setGolosCasa(0);
        game.setGolosFora(0);

        Equipa casa = this.equipas.get(game.getEquipaCasa());
        Equipa fora = this.equipas.get(game.getEquipaFora());

        rCasa = casa.ratingJogadores(jc);
        rFora = fora.ratingJogadores(jf);

        List<Integer> casaSubed;
        List<Integer> foraSubed;

        if(!sc.isEmpty()) {
            casaSubed= substitui(jc,sc);
            rCasaSub = casa.ratingJogadores(casaSubed);
        }else rCasaSub=rCasa;
        if(!sf.isEmpty()){
            foraSubed= substitui(jf,sf);
            rForaSub = fora.ratingJogadores(foraSubed);
        }else rForaSub =rFora;

        int rCasaTotal = (rCasaSub+rCasa)/2;
        int rForaTotal = (rForaSub+rFora)/2;

        int dif = rCasaTotal - rForaTotal;
        int numGolos = (int)(Math.random()*6+1); //1d6

        while(numGolos>0 && (dif>0)){
            if (roll(dif)) game.setGolosCasa(game.getGolosCasa()+1);
            else game.setGolosFora(game.getGolosFora()+1);
            numGolos--;
        }

        while(numGolos>0 && (dif==0)){
            igualdade = Math.random(); //flip a coin
            if (igualdade>0.5) game.setGolosCasa(game.getGolosCasa()+1);
            else game.setGolosFora(game.getGolosFora()+1);
            numGolos--;
        }

        while(numGolos>0){
                if (roll(Math.abs(dif))) game.setGolosFora(game.getGolosFora()+1);
                else game.setGolosCasa(game.getGolosCasa()+1);
                numGolos--;
        }
        return game;
    }
    /**
     * Metodo que faz o lançamento de dados usando um fator de vantagem e devolve se foi golo ou não
     * @param num um valor que é usado para dar vantagem a equipas fortes
     * @return se é golo
     */
    private boolean roll(int num){
        int res;
        if (num>10) res=(((num-10)/2)+5); //d&d5th edition +5 pelos negativos
        else res = num/2;// 1 a 5
        int dice1=(int)(Math.random()*6+1);

        return dice1 + (res) > 6;
    }


    /**
     * Metodo que substitui jogadores num jogo
     * @param jc Lista que contem os jogadores atuais
     * @param sc Map que contem as substituições
     * @return jc com substituições efetuadas
     * @throws InvalidParameterException caso tente substituir um jogador que nao esta em campo
     */
    private List<Integer> substitui(List<Integer> jc,Map<Integer, Integer> sc) throws InvalidParameterException {

        for (Integer key : sc.keySet()) {

            if (jc.contains(key)) {
                jc.set(jc.indexOf(key), sc.get(key));
            } else throw new InvalidParameterException("Estado.substitui invalid key");

        }
        return jc;
    }


    /**
     * Metodo que transfere 1 jogador de uma equipa para outra
     *
     * @param j  Jogador a ser transferido
     * @param e1 Equipa Origem
     * @param e2 Equipa Destino
     */
    public void transferencia(Jogador j,Equipa e1,Equipa e2){
        e1.removePlayerTeam(j);
        e2.insereJogador(j);
        int key = -1;
        try{
            key = this.getPlayerIndex(j);
        }catch (ParameterNotInScopeException pmn){
            System.exit(6);
        }
        this.jogadores.put(key,j.clone());

    }


    public void updatePlayer(Jogador j) throws NotFoundException {
        Equipa e =this.equipas.values().stream().filter(f->f.isPresent(j)).findAny().orElseThrow(NotFoundException::new);
        e.removePlayerTeam(j);
        e.insereJogador(j);
    }
    public int getPlayerIndex(Jogador j) throws ParameterNotInScopeException {
        for (Map.Entry<Integer, Jogador> entry : jogadores.entrySet()) {
            if (entry.getValue().equals(j)) {
                return (entry.getKey());
            }
        }
        throw new ParameterNotInScopeException("Jogador not found");
    }


    // Save in object file
    public void save(String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
        fos.close();
    }

    // Load from object file
    public static Estado load(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Estado loaded = (Estado) ois.readObject();
        ois.close();
        fis.close();
        return loaded;
    }
}
