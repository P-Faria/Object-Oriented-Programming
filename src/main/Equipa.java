import java.util.ArrayList;
import java.util.List;


/**
 * Classe que gere tudo que define uma Equipa
 *
 * @author a82643 - João Pedro Goulart
 * @author a72640 - Pedro Faria
 * @author a87952 - Tiago Rodrigues
 */
public class Equipa {
    private String nome;
    private List<Jogador> jogadores;


    public Equipa(String nome) {
        this.nome = nome;
        jogadores= new ArrayList<>();


    }
      public Equipa(Equipa team){
        nome = team.nome;
        jogadores = new ArrayList<>();
        jogadores = (List<Jogador>) team.jogadores.clone(); //TODO:vai dar asneira
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void insereJogador(Jogador j){
        if (!(jogadores.contains(j))){
                this.jogadores.add(j);
                j.setHistorico(this.nome);
            }else throw new IllegalArgumentException("Player already in team");
    }

    public void removePlayerTeam(Jogador j){
        if (jogadores.contains(j)){
        jogadores.remove(j);
        }else throw new IllegalArgumentException("Player not in Team");
    }

    public String toString(){
        StringBuilder r = new StringBuilder("Equipa:" + nome + "\n");
        for (Jogador j : jogadores){
            r.append(j.toString());
        }
        return r.toString();
    }

}