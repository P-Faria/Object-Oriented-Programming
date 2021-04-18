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
 * @since 0.2
 */
public class ID {
Map<Integer,String> player = new HashMap<>();
Map<Integer,String> team = new HashMap<>();

public Integer newPlayerID(String playername){

    int maxID = Collections.max(player.entrySet(), Map.Entry.comparingByValue()).getKey();
    int newID;

    if (maxID == 0) newID = 1;
        else newID = maxID +1;

     if (player.putIfAbsent((newID),playername) == null) return newID;
        else {
         System.out.println("newPlayerID : ID já existe");
         return null;
        }
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




}
