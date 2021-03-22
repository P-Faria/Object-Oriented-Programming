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
    private int ID;
    private String Nome;
    private ArrayList Jogadores;


    public Equipa() {
        this.ID = ID.newTeamID(); // TODO: Utilizar o tree set ou o hash set para criar 1 array que servirá como BD dos ID disponiveis e usados
        this.Nome = "Unidos da Moita";
        this.Jogadores = new ArrayList();

    }
    public Equipa(ID teamID, String nome, ArrayList jogadores) {
        this.ID = ID;
        Nome = nome;
        this.Jogadores = (ArrayList) jogadores.clone();
    }

    public Equipa(Equipa team){
        this.ID = team.ID;
        this.Nome = team.Nome;
        this.Jogadores = team.Jogadores;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }


}