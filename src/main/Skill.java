import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Skill{

    public Map<String,Integer> Skill = new HashMap<>();
    public int ID;

    public Skill(){
        this.ID = 0;
        this.Skill.put("Velocidade",10);
        this.Skill.put("Resistência",10);
        this.Skill.put("Destreza",10);
        this.Skill.put("Impulsão",10);
        this.Skill.put("Jogo de Cabeça",10);
        this.Skill.put("Remate",10);
        this.Skill.put("Capacidade de Passe",10);
    }

    public Skill(Jogador player,int k){
        this.ID = player.getID();
        k = k>=20?20 : Math.max(k, 0); //assegura que esta dentro dos valores aceites
        this.Skill.put("Velocidade",k);
        this.Skill.put("Resistência",k);
        this.Skill.put("Destreza",k);
        this.Skill.put("Impulsão",k);
        this.Skill.put("Jogo de Cabeça",k);
        this.Skill.put("Remate",k);
        this.Skill.put("Capacidade de Passe",k);
        //TODO: Adicionar especificidade de posições
    }

}
