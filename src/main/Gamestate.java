/**
 * Classe que gere tudo que é necessario para criar 1 jogo e o manter
 *
 * @author Pedro Faria - a72640
 */


public class Gamestate {

    private ID id = new ID();


    private Jogador createPlayer(ID idSet,String name, int pos,int skill) {
        Integer id = idSet.newPlayerID(name);
        if (id != 0) {
            return new Jogador(id,name,pos,skill);
        } else throw new IllegalArgumentException("Erro ID");
    }


}


