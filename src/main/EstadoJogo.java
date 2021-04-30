import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que gere tudo que é necessario para criar 1 jogo e o manter
 *
 * @author a82643 - João Pedro Goulart
 * @author a72640 - Pedro Faria
 * @author a87952 - Tiago Rodrigues
 */


public class EstadoJogo {


    private String Nome;
    private Map<Integer,Jogador> PlayerDB;
    private Map<Integer,Equipa> TeamDB;

    public EstadoJogo(){
        Nome = "Default";
        PlayerDB = new HashMap<>();
        TeamDB = new HashMap<>();

    }

    public EstadoJogo(String nome){
        Nome = nome;
    }



    public String getNome() {
        return this.Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public Map<Integer, Jogador> getPlayerDB() {
        return PlayerDB;
    }

    public void setPlayerDB(Map<Integer, Jogador> playerDB) {
        PlayerDB = playerDB;
    }

    public Map<Integer, Equipa> getTeamDB() {
        return TeamDB;
    }

    public void setTeamDB(Map<Integer, Equipa> teamDB) {
        TeamDB = teamDB;
    }

    /**
     * Cria um novo Jogador adiciona á Base de dados do Estado de Jogo
     * @param name Nome do Jogador
     * @param pos  Posição (1-5)
     * @param skill Valor Base do skill (0-20)
     * @param teamName Equipa a que pertence
     * @return  Id do Jogador criado
     */
    public int createPlayer(String name, int pos,int skill,Equipa teamName) {
        int maxID = Collections.max(PlayerDB.keySet());
        int pID = maxID +1;
        if (pID != 0) {
            teamName.addToTeam(pID);
            Jogador player = new Jogador(pID,name,pos,skill);
            PlayerDB.put(pID,player);
            return pID;
        } else throw new IllegalArgumentException("Erro: EstadoJogo.createPlayer");
    }

    /**
     * Cria uma equipa e adicona-a á Base de dados do Estado de Jogo
     * @param name O nome da equipa a adicionar
     * @return O Id da equipa criada
     */
    public int createTeam(String name){
        int maxId = Collections.max(TeamDB.keySet());
        int tID = maxId +1;
        if (tID != 0){
            Equipa team = new Equipa(tID,name);
            TeamDB.put(tID,team);
            return  tID;
        }else throw new IllegalArgumentException("Erro: EstadoJogo.createTeam");

    }


}


