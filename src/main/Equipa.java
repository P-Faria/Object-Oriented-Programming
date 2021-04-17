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


public class Equipa{
    private int TeamID;
    private String Nome;
    private ArrayList<Jogador> Players;


    public Equipa() {
        this.TeamID = ID.getNewID(); // TODO: Utilizar o tree set ou o hash set para criar 1 array que servirá como BD dos ID disponiveis e usados
        this.Nome = "Unidos da Moita";
        this.Jogadores = new ArrayList();

    }
    public Equipa(ID teamID, String nome, ArrayList jogadores) {
        this.TeamID = ID.setID(teamID);
        Nome = nome;
        this.Players = (ArrayList) jogadores.clone();
    }

    public Equipa(Equipa team){
        this.TeamID = team.TeamID;
        this.Nome = team.Nome;
        this.PLayers = team.Players;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }


}