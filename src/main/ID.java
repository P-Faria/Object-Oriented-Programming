import java.util.*;

/**
 *  Classe ID
 *  Classe que gere os identificadores unicos usados por outras classes
 *
 *
 *
 * @author Pedro Faria
 */


public class ID {
Map<Integer,String> player;
Map<Integer,String> team;


public ID(){
    this.player = new HashMap();
    this.team = new HashMap();
}

public ID(Map<Integer, String> player, Map<Integer, String> team) {
    this.player = player;
    this.team = team;
}

    /**
     * Cria um Jogador e atribu-lhe um ID
     * @param playername Nome do Jogador
     * @return Integer - Numero de ID do Jogador criado
     */
    public Integer newPlayerID(String playername){
    return createID(playername, player);
}

    public Integer getPlayerID(String playername) {

    if (player.containsValue(playername)) {
        for(Map.Entry<Integer,String> entry: player.entrySet()){
            if(entry.getValue().equals(playername)){
                return entry.getKey();
            }
        }
    }
    else {
        throw new IllegalArgumentException("getPlayerID: ID not found!");
    }
    return null;

}


public String getIDplayerName(Integer id){
    return player.get(id);
}

    public Integer newTeamID(String teamname){

        return createID(teamname, team);
    }




    public Integer getTeamId(String teamName) {

        if (player.containsValue(teamName)) {
            for(Map.Entry<Integer,String> entry: team.entrySet()){
                if(entry.getValue().equals(teamName)){
                    return entry.getKey();
                }
            }
        }
        else {
            throw new IllegalArgumentException("getTeamID: ID not found!");
        }
        return null;

    }


    public String getIDteamName(Integer id) {
        return team.get(id);
    }


    /**
     * Cria uma nova entidade na base de dados enviada
     *
     * @param name Nome da Equipa/Player
     * @param map  Em que Map ficará guardado
     * @return  Numero de ID da entidade criada
     */
    private Integer createID(String name, Map<Integer, String> map) {
        int maxID = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
        int newID;

        if (maxID == 0) newID = 1;
        else newID = maxID +1;

        if (map.putIfAbsent(newID,name) == null) return newID;
        else {
            throw new IllegalArgumentException("newPlayerID : ID já existe");
        }
    }


    public Map<Integer, String> getPlayer() {
        return player;
    }

    public Map<Integer, String> getTeam() {
        return team;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ID id = (ID) o;
        return Objects.equals(this.getPlayer(), id.getPlayer()) && Objects.equals(this.getTeam(), id.getTeam());
    }

}