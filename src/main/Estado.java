import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Estado {
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
}
