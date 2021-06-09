import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Classe que gere tudo que define uma Equipa
 *
 * @author a82643 - João Pedro Goulart
 * @author a72640 - Pedro Faria
 * @author a87952 - Tiago Rodrigues
 */
public class Equipa implements Serializable {
    private String nome;
    private List<Jogador> jogadores;


    public Equipa(String nome) {
        this.nome = nome;
        jogadores= new ArrayList<>();


    }
      public Equipa(Equipa team){
        nome = team.nome;
        jogadores = new ArrayList<>(team.jogadores);
    }

    public static Equipa parse(String s) {
        return new Equipa(s);
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

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public Jogador getJogadorByName(String name){
        return this.jogadores.stream().filter(jogador -> name.equals(jogador.getNomeJogador())).findAny().orElse(null);
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public String toString(){
        StringBuilder r = new StringBuilder("Equipa:" + nome + "\n");
        for (Jogador j : jogadores){
            r.append(j.toString());
        }
        r.append("Rating de Equipa: ").append(this.RatingEquipa()).append("\n");

        return r.toString();
    }

    public  int RatingEquipa(){
        int sum = this.jogadores.stream().mapToInt(Jogador::Rating).sum();
        return (int) (sum / (long) this.jogadores.size());
    }

    public int ratingJogadores(List<Integer> players){


            List<Jogador> res = jogadores.stream(). //vai a lista de jogadores da equipa
                    filter(pl -> players.stream().  //filtra todos os resultados da lista dada
                    anyMatch(it->it.equals(pl.getNumeroJogador()))) // em que os valores numeroJogador e o valor dado seja igual
                    .collect(Collectors.toList());      //devolve a lista de todos os jogadores que obedecem á condição
        //debug
        for (Jogador j: res){
            System.out.println(j.getNomeJogador() + j.Rating());
        }
            return (int) (res.stream().mapToInt(Jogador::Rating).sum() / 11);

    }

}