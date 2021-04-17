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

public Integer newPlayerID(String playername) throws Exception {

    int maxID = Collections.max(player.entrySet(), Map.Entry.comparingByValue()).getKey();
    int newID = maxID +1;
     if (player.putIfAbsent((newID),playername) == null) return newID;
     else throw new Exception("newPlayerID : ID já existe");
}
public Integer getPlayerID(String playername){

        if (player.containsValue(playername)) {
            return player.get
        }
        return pID;
}




}
