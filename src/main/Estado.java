import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Jogo Jogar (String ec,String ef, LocalDate d, List<Integer> jc,Map<Integer, Integer> sc, List<Integer> jf, Map<Integer, Integer> sf){
        //TODO: criar este metodo, mas que nao receba uma data.
        int rCasa,rFora,rCasaSub,rForaSub;
        Equipa casa = this.equipas.get(ec);
        Equipa fora = this.equipas.get(ef);
        rCasa = casa.ratingJogadores(jc);
        rFora = fora.ratingJogadores(jf);
        substitui(jc,sc);
        substitui(jf,sf);
        rCasaSub = casa.ratingJogadores(jc);
        rForaSub = fora.ratingJogadores(jf);


        return new Jogo(ec,ef,0,0,d,jc,sc,jf,sf);
    }

    public Jogo Jogar (Jogo game){
        //TODO: criar este metodo, mas que nao receba uma data. tornar esta funcao num void
        int rCasa,rFora,rCasaSub,rForaSub;

        Equipa casa = this.equipas.get(game.getEquipaCasa());
        Equipa fora = this.equipas.get(game.getEquipaFora());

        rCasa = casa.ratingJogadores(game.getJogadoresCasa());
        rFora = fora.ratingJogadores(game.getJogadoresFora());

        List<Integer> casaSubed = substitui(game.getJogadoresCasa(),game.getSubstituicoesCasa());
        List<Integer> foraSubed = substitui(game.getJogadoresFora(),game.getSubstitucoesFora());

        rCasaSub = casa.ratingJogadores(casaSubed);
        rForaSub = fora.ratingJogadores(foraSubed);


        return game;
    }





    public List<Integer> substitui(List<Integer> jc,Map<Integer, Integer> sc) {
        List<Integer> res = new ArrayList<>();
        for (Integer key : sc.keySet()) {
            jc.set(jc.indexOf(key),sc.get(key));
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
