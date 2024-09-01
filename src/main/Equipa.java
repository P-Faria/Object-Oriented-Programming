import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
        this.jogadores= new ArrayList<>();


    }
      public Equipa(Equipa team){
        nome = team.nome;
        jogadores = new ArrayList<>(team.jogadores);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo que Cria ua Equipa dependendo do input por ficheiro de texto
     * do Jogador
     * @param s String com formatação CSV
     * @return Equipa
     */
    public static Equipa parse(String s) {
        return new Equipa(s);
    }



    /**
     * Metodo que insere um jogador numa equipa e atualiza o seu historico
     * @param j jogador a ser inserido
     */
    public void insereJogador(Jogador j){
        if (!this.isPresent(j)){
            j.setHistorico(this.getNome());
           this.jogadores.add(j.clone());


        }else throw new IllegalArgumentException("Player already in team");
    }

    /**
     * Metodo que Remove 1 jogador d da lista de jogadores de um Estado
     * @param j Jogador a ser removido
     * @throws  NotFoundException caso nao encontre o jogador
     */
    public void removePlayerTeam(Jogador j) throws NotFoundException {
        if (this.isPresent(j)) {
            this.jogadores.removeIf(r -> r.nameEquals(j.getNomeJogador()));
        } else throw new NotFoundException("Player not in Team");
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    /**
     * Métodoq que verifica se um jogador esta presente numa lista de jogadores
     * @param j Jogador a ser procurado
     * @return True se encontrar, False caso contrario
     */
    public boolean isPresent(Jogador j) {
        return this.jogadores.stream().anyMatch(f->f.nameEquals(j.getNomeJogador()));
    }

    public Jogador getJogadorByName(String name){
        return this.jogadores.stream().filter(jogador -> name.equals(jogador.getNomeJogador())).findAny().orElse(null);
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    /**
     *Metodo Equipa.toString
     * @return O nome da equipa e caso existam, cada jogador, posição e o seu rating
     *
     */
    public String toString(){
        StringBuilder r = new StringBuilder("\nEquipa:" + nome + "\n");
        if (jogadores.size()>0) {
            for (Jogador j : jogadores) {
                r.append(j.getNomeJogador()).append(": ").append(j.getClass().getSimpleName()).append("\n");
            }
            r.append("Rating de Equipa: ").append(this.RatingEquipa()).append("\n");
        }

        return r.toString();
    }

    /**
     * Devolve a Média de valores de todos os jogadores da equipa
     * @return int média da equipa
     */
    public  int RatingEquipa(){
        int sum = this.jogadores.stream().mapToInt(Jogador::Rating).sum();
        return (int) (sum / (long) this.jogadores.size());
    }

    /**
     * Metodo que dada uma Lista de Jogadores devolve a média do rating desses 11 jogadores
     * @param players Lista de Jogadores
     * @return A média dos seus ratings
     */
    public int ratingJogadores(List<Integer> players){

            List<Jogador> res = jogadores.stream(). //vai a lista de jogadores da equipa
                    filter(pl -> players.stream().  //filtra todos os resultados da lista dada
                    anyMatch(it->it.equals(pl.getNumeroJogador()))) // em que os valores numeroJogador e o valor dado seja igual
                    .collect(Collectors.toList());      //devolve a lista de todos os jogadores que obedecem á condição

            return (res.stream().mapToInt(Jogador::Rating).sum() / 11);
    }

    /**
     * Metodo que devolve o indice de um Jogador na Lista Estado.joadores
     * @param nomeJogador   Nome do Jogador a procurar
     * @return o seu indice
     * @deprecated  NOT IN USE
     */
    public int getIndexJogador(String nomeJogador) {
        Jogador j = jogadores.stream().filter(f->f.nameEquals(nomeJogador)).findAny().get();
        return this.jogadores.indexOf(j);
    }


}