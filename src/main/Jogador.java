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
    private Map<String,Integer> Skill = new HashMap<>();


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
        this.uniqueID = ID.newPlayerID(NOME);
        this.setSkill();
    }

    public Jogador(int id, String nome, int pos,int skill){
        this.uniqueID = ID.getIDplayerName(id);
        this.NOME = nome;
        this.POS = pos;
        this.setSkill(skill);
            }

    public Jogador(Jogador umJogador){
        this.ID = umJogador.ID;
        this.NOME = umJogador.NOME;
        this.POS = umJogador.POS;
    }

    /**
     * Método que devolve numero do ID do Jogador
     *
     * @return int com o numero de identificação
     */
    public int getID(){
        return this.ID;
    }

    public static void setID(int ID) {
        Jogador.ID = ID; // TODO: funçao que verifique se ID está livre
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

    public void setSkill(int k) {
        k = k >= 20 ? 20 : Math.max(k, 0); //assegura que esta dentro dos valores aceites
        this.Skill.put("Velocidade", k);
        this.Skill.put("Resistência", k);
        this.Skill.put("Destreza", k);
        this.Skill.put("Impulsão", k);
        this.Skill.put("Jogo de Cabeça", k);
        this.Skill.put("Remate", k);
        this.Skill.put("Capacidade de Passe", k);
    }







}
