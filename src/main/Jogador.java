import java.util.Objects;

/**
 *  Classe Jogador
 *  Classe que define os parametros de cada jogador
 *
 *
 *
 * @author Pedro Faria
 * @since 0.1
 */

public class Jogador {
    private static int ID; //ID UNICO
    private String NOME; // Nome


    // * 0. Não defenida
    // * 1. Guarda-Redes
    // * 2. Defesas
    // * 3. Médios
    // * 4. Avançados
    // * 5. Laterais

    private int POS; // Posição
    private static int ID_Skill; //qual o id da sua classe de skills


    /**  Metodo Jogador
     * Cria 1 jogador novo
     *
     *
     * */
    public Jogador(){
        this.ID = ID.NewPlayID(); // TODO: Utilizar o tree set ou o hash set para criar 1 array que servirá como BD dos ID disponiveis e usados
        this.NOME ="John Cena" ; // nome Standard
        this.POS = 0; // Posição Standard
        this.ID_Skill = Skill.getNew_ID(); // TODO: Criar a classe skill
    }

    public Jogador(int id, String nome, int pos, int id_skill){
        this.ID = id;
        this.NOME = nome;
        this.POS = pos;
        this.ID_Skill = id_skill;
    }

    public Jogador(Jogador umJogador){
        this.ID = umJogador.ID;
        this.NOME = umJogador.NOME;
        this.POS = umJogador.POS;
        this.ID_Skill = umJogador.ID_SKILL;
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
     * Método que devolve numero do ID do Jogador
     *
     * @return int com o numero da posição
     */
    public int getPOS() {
        return this.POS;
    }

    public void setPOS(int POS) {
        this.POS = POS;
    }

    /**
     * Método que devolve numero do ID das Habilidades do Jogador
     *
     * @return int com o numero de identificação das Habilidades
     */
    public static int getID_Skill() {
        return ID_Skill;
    }

    public static void setID_Skill(int ID_Skill) {
        Jogador.ID_Skill = ID_Skill; // TODO: funçao que verifique se ID da Skill está livre
    }

  }
