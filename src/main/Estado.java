import java.io.*;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Estado implements Serializable {
    Map<String, Equipa> equipas = new HashMap<>(); //nome, equipa
    Map<Integer, Jogador> jogadores = new HashMap<>(); //numero, jogador
    List<Jogo> jogos;

    public Estado(Map<String, Equipa> e,Map<Integer, Jogador> j,List<Jogo> g){
        equipas.putAll(e);
        jogadores.putAll(j);
        jogos = new ArrayList<>(g);
    }

    public Estado() {
        equipas = null;
        jogadores = null;
        jogos = null;
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

    public void debug(){
        for (Equipa Ee: this.equipas.values()){
            System.out.println(Ee.toString());
        }
        for (Jogo jog: this.jogos){
            System.out.println(jog.toString());
        }
    }

    /**
     * Metodo que dado um jogo decide um vencedor dependendo dos ratings das equipas levadas para o jogo
     * @param ec    String equipa Casa
     * @param ef    String equipa Visitante
     * @param d     Data do jogo
     * @param jc    lista de jogadores da equipa da Casa titulares
     * @param sc    Map das substituições a fazer da equipa da Casa
     * @param jf    Map das substituições a fazer da equipa Visitante
     * @param sf    lista de jogadores da equipa Visitante titulares
     * @return  O Jogo após ter sido jogado
     */
    public Jogo Jogar (String ec,String ef, LocalDate d, List<Integer> jc,Map<Integer, Integer> sc, List<Integer> jf, Map<Integer, Integer> sf){
        //TODO: caso se faça 1 controller esta função pode passar para Jogo.java.
        int rCasa,rFora,rCasaSub,rForaSub,vantagemCasa,vantagemFora,igualdade;
        int gc=0,gf=0;


        Equipa casa = this.equipas.get(ec);
        Equipa fora = this.equipas.get(ef);

        rCasa = casa.ratingJogadores(jc);
        rFora = fora.ratingJogadores(jf);

        List<Integer> casaSubed = substitui(jc,sc);
        List<Integer> foraSubed = substitui(jf,sf);

        rCasaSub = casa.ratingJogadores(casaSubed);
        rForaSub = fora.ratingJogadores(foraSubed);

        int rCasaTotal = rCasaSub+rCasa/2;
        int rForaTotal = rForaSub+rFora/2;

        int dif = rCasaTotal - rForaTotal;
        int numGolos = (int)(Math.random()*6+1); //1d6

        while(numGolos>0 && (dif>0)){
            if (roll(dif)) gc++;
            else gf++;
            numGolos--;
        }

        while(numGolos>0 && (dif==0)){
            igualdade = (int)(Math.random()*2+1);
            if (igualdade>1) gc++;
            else gf++;
            numGolos--;
        }

        while(numGolos>0 && (dif<0)){
            if (roll(Math.abs(dif))) gf++;
            else gc++;
            numGolos--;
        }

        return new Jogo(ec,ef,0,0,d,jc,sc,jf,sf);
    }

    /**
     * Metodo que dado um jogo decide um vencedor dependendo dos ratings das equipas levadas para o jogo
     * @param game o Jogo a ser jogado
     * @return o Jogo após ter sido jogado
     */
    public Jogo Jogar (Jogo game){

        int rCasa,rFora,rCasaSub,rForaSub,vantagemCasa,vantagemFora,igualdade;


        Equipa casa = this.equipas.get(game.getEquipaCasa());
        Equipa fora = this.equipas.get(game.getEquipaFora());

        rCasa = casa.ratingJogadores(game.getJogadoresCasa());
        rFora = fora.ratingJogadores(game.getJogadoresFora());

        List<Integer> casaSubed = substitui(game.getJogadoresCasa(),game.getSubstituicoesCasa());
        List<Integer> foraSubed = substitui(game.getJogadoresFora(),game.getSubstitucoesFora());

        rCasaSub = casa.ratingJogadores(casaSubed);
        rForaSub = fora.ratingJogadores(foraSubed);

        int rCasaTotal = rCasaSub+rCasa/2;
        int rForaTotal = rForaSub+rFora/2;

        int dif = rCasaTotal - rForaTotal;
        int numGolos = (int)(Math.random()*6+1); //1d6

        while(numGolos>0 && (dif>0)){
            if (roll(dif)) game.setGolosCasa(game.getGolosCasa()+1);
            else game.setGolosFora(game.getGolosFora()+1);
            numGolos--;
        }

        while(numGolos>0 && (dif==0)){
            igualdade = (int)(Math.random()*2+1); //flip a coin
            if (igualdade>1) game.setGolosCasa(game.getGolosCasa()+1);
            else game.setGolosFora(game.getGolosFora()+1);
            numGolos--;
        }

        while(numGolos>0 && (0>dif)){
                if (roll(Math.abs(dif))) game.setGolosFora(game.getGolosFora()+1);
                else game.setGolosCasa(game.getGolosCasa()+1);
                numGolos--;
        }
        return game;
    }
    private boolean isGolo(int roll){
        return roll > 6;
    }

    /**
     * Metodo que faz o lançamento de dados usando um fator de vantagem e devolve se foi golo ou não
     * @param num um valor que é usado para dar vantagem a equipas fortes
     * @return se é golo
     */
    private boolean roll(int num){
        int res;
        if (num>=10) res =(int)(Math.random()*6+1); //1d6
        else if(num>=5) res = (int)(Math.random()*4+1);// 1d4
        else res = (int)(Math.random()*num+1); // 1d(1...4)
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
     * @param e1 Equipa Source
     * @param e2 Equipa Destino
     */
    public void transferencia(Jogador j,Equipa e1,Equipa e2){
        e1.removePlayerTeam(j);
        e2.insereJogador(j);

    }

    // Save in object file
    public static void save(Estado state, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(state);
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
