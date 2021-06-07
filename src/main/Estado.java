import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Jogo Jogar (String ec,String ef, LocalDate d, List<Integer> jc,Map<Integer, Integer> sc, List<Integer> jf, Map<Integer, Integer> sf){
        //TODO: criar este metodo, mas que nao receba uma data.

        Equipa casa = this.equipas.get(ec);
        Equipa fora = this.equipas.get(ef);


        return Jogo(ec,ef,gc,gf,d,jc,sc,jf,sf);
    }
     */

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
