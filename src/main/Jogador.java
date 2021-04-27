import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *  Classe Jogador
 *  Classe que define os parametros de cada jogador
 *
 * @author Pedro Faria
 * @since 0.1
 */

public class Jogador {

    private int uniqueID; //ID UNICO
    private String NOME; // Nome
    private Map<String,Integer> Skill;


    private int POS; // Posição
    public static final int undefined = 0;  // * 0. Não defenida
    public static final int GR = 1;         // * 1. Guarda-Redes
    public static final int Def = 2;        // * 2. Defesas
    public static final int Med = 3;        // * 3. Médios
    public static final int Ava = 4;        // * 4. Avançados
    public static final int Lat = 5;        // * 5. Laterais



    /**  Metodo Jogador
     * Cria 1 jogador novo
     *
     *
     * */
    public Jogador(){
        this.NOME ="John Cena" ; // nome Standard
        this.POS = 0; // Posição Standard
        this.uniqueID = 0;
        this.setSkill();
    }

    public Jogador(int id, String nome, int pos,int skill){
        this.uniqueID = id;
        this.NOME = nome;
        this.POS = pos;
        this.setSkill(skill);
    }

    public Jogador(Jogador umJogador){
        this.uniqueID = umJogador.uniqueID;
        this.NOME = umJogador.NOME;
        this.POS = umJogador.POS;
        this.Skill = umJogador.Skill;
    }

    /**
     * Método que devolve numero do ID do Jogador
     *
     * @return int com o numero de identificação
     */
    public int getID(){
        return this.uniqueID;
    }

    public void setID(int ID) {
        this.uniqueID = ID; // TODO: funçao que verifique se ID está livre
    }

    /**
     * Método que devolve o nome do Jogador
     *
     * @return String com o nome do Jogador
     */
    public String getNOME() {
        return this.NOME;
    }


    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    /**
     * Método que devolve numero da posição do Jogador
     *
     * @return int com o numero da posição
     */
    public int getPOS() {
        return this.POS;
    }

    public void setPOS(int POS) {
        this.POS = POS;
    }

    public void setSkill(){
        this.Skill.put("Velocidade",10);
        this.Skill.put("Resistência",10);
        this.Skill.put("Destreza",10);
        this.Skill.put("Impulsão",10);
        this.Skill.put("Jogo de Cabeça",10);
        this.Skill.put("Remate",10);
        this.Skill.put("Capacidade de Passe",10);

    }

    public void setSkill(String name,int skill){
        skill = skill >= 20 ? 20 : Math.max(skill, 0); //assegura que esta dentro dos valores aceites
        this.Skill.put(name,skill);

    }

    public void setSkill(int skill) {
        skill = skill >= 20 ? 20 : Math.max(skill, 0); //assegura que esta dentro dos valores aceites
        this.Skill.put("Velocidade", skill);
        this.Skill.put("Resistência", skill);
        this.Skill.put("Destreza", skill);
        this.Skill.put("Impulsão", skill);
        this.Skill.put("Jogo de Cabeça", skill);
        this.Skill.put("Remate", skill);
        this.Skill.put("Capacidade de Passe", skill);
    }

    private String skillPos(Jogador player){
        if (player.POS == undefined) throw new IllegalArgumentException("Erro: Jogador.skillPos - POS errada");
        if ( player.POS == GR) return
        public static final int Def = 2;        // * 2. Defesas TODO:"Acabar posiçoes especiais
        public static final int Med = 3;        // * 3. Médios
        public static final int Ava = 4;        // * 4. Avançados
        public static final int Lat = 5;        // * 5. Laterais

    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return uniqueID == jogador.getID() && getPOS() == jogador.getPOS() && getNOME().equals(jogador.getNOME());
    }

}
