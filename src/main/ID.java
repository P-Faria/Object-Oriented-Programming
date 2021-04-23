import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
public Integer newPlayerID(String playername){

    return getID(playername, player);
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
        System.out.println("getPlayerID: ID not found!");
        return null;
    }
    return null;

}


public String getIDplayerName(Integer id){
    return player.get(id);
}

    public Integer newTeamID(String teamname){

        return getID(teamname, team);
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
            System.out.println("getTeamID: ID not found!");
            return null;
        }
        return null;

    }


    public String getIDteamName(Integer id) {
        return team.get(id);
    }




    private Integer getID(String name, Map<Integer, String> map) {
        int maxID = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
        int newID;

        if (maxID == 0) newID = 1;
        else newID = maxID +1;

        if (map.putIfAbsent(newID,name) == null) return newID;
        else {
            System.out.println("newPlayerID : ID já existe");
            return null;
        }
    }
}