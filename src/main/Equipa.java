import java.util.HashMap;
import java.util.Map;

/**
 * Classe que gere tudo que define uma Equipa
 *
 * @author a82643 - João Pedro Goulart
 * @author a72640 - Pedro Faria
 * @author a87952 - Tiago Rodrigues
 */
public class Equipa {
    private String nome;
    private Map<Integer, Jogador> players;




    public Equipa() {
        this.nome = "Unidos da Moita";

    }

    public Equipa(String nome) {
        this.nome = nome;


    }
      public Equipa(Equipa team){
        nome = team.nome;
        players= new HashMap<>();
        players = (Map<Integer, Jogador>) team.players.clone(); //TODO:vai dar asneira
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void insereJogador(Jogador j){
        if (!(players.containsValue(j))){
                this.players.put(j.getNumeroJogador(),j);
                j.setHistorico(this.nome);
            }else throw new IllegalArgumentException("Player already in team");
    }

    public void removePlayerTeam(Jogador j){
        if (players.containsValue(j)){
        players.remove(j);
        }else throw new IllegalArgumentException("Player not in Team");
    }


}