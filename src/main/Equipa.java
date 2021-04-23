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
    private ArrayList<Jogador> Players;


    public Equipa() {
        this.TeamID = 0;
        this.Nome = "Unidos da Moita";
        this.Players = new ArrayList<>();

    }
    public Equipa(int id, String nome, ArrayList<Jogador> jogadores) {
        this.TeamID = id;
        Nome = nome;
        this.Players = new ArrayList<Jogador>(jogadores);
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

    public void addToTeam(Jogador player,Equipa team){
        if ((this.Nome.equals(team.Nome)) && (this.TeamID == team.TeamID)){
            if (!(Players.contains(player))){
                this.Players.add(player);
            }else throw new IllegalArgumentException("Player already in team: use editPlayerTeam");
        }else throw new IllegalArgumentException("Equipa não é igual");
    }


    public void addToTeam(Jogador player){
        if (!(Players.contains(player))){
                this.Players.add(player);
            }else throw new IllegalArgumentException("Player already in team: use editPlayerTeam");
    }

    public Jogador getPlayerfromTeam(int id){
        int index = Players.stream().collect(Collectors.filtering(Jogador.)) //TODO: receber um int id e devolver o player

    }


    public void editPlayerTeam(Jogador player,Equipa team) {
        if ((this.Nome.equals(team.Nome)) && (this.TeamID == team.TeamID)) {
            if (Players.contains(player)) {
                Players.remove(player);

            }
        }
    }


}