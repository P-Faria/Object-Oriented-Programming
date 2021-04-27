/**
 *  Classe Equipa
 *  Classe que define os parametros de cada Equipa
 *
 *
 *
 * @author Pedro Faria
 * @since 0.2
 */

import java.util.ArrayList;
import java.util.stream.Collectors;


public class Equipa{
    private int TeamID;
    private String Nome;
    private ArrayList<Integer> Players;


    public Equipa() {
        this.TeamID = 0;
        this.Nome = "Unidos da Moita";
        this.Players = new ArrayList<>();

    }

    public Equipa(String nome,int id) {
        this.TeamID = id;
        this.Nome = nome;
        this.Players = new ArrayList<>();

    }
    public Equipa(int id, String nome, ArrayList<Integer> jogadores) {
        this.TeamID = id;
        Nome = nome;
        this.Players = new ArrayList<>(jogadores);
    }

    public Equipa(Equipa team){
        this.TeamID = team.TeamID;
        this.Nome = team.Nome;
        this.Players = team.Players;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }


    public void addToTeam(int player){
        if (!(Players.contains(player))){
                this.Players.add(player);
            }else throw new IllegalArgumentException("Player already in team");
    }

    public void removePlayerTeam(int id){
        if (Players.contains(id)){
        Players.remove(Players.indexOf(id));
        }else throw new IllegalArgumentException("Player not in Team");
    }

}