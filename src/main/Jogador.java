import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *  Classe que define os parametros de cada jogador
 *
 * @author a82643 - João Pedro Goulart
 * @author a72640 - Pedro Faria
 * @author a87952 - Tiago Rodrigues
 */

public abstract class  Jogador implements Serializable {
    private String nomeJogador;
    private int numeroJogador;
    private int velocidade, resistencia, destreza, impulsao, cabeca, remate, passe;
    private LinkedList<String> historico;

    public Jogador(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p) {
        nomeJogador = nomeJ;
        numeroJogador = numeroJ;
        velocidade = vel;
        resistencia = res;
        destreza = des;
        impulsao = imp;
        cabeca = cab;
        remate = rem;
        passe = p;
        historico = new LinkedList<>();
    }

    public Jogador(Jogador j) {
        nomeJogador = j.getNomeJogador();
        numeroJogador = j.getNumeroJogador();
        velocidade = j.getVelocidade();
        resistencia = j.getResistencia();
        destreza = j.getDestreza();
        impulsao = j.getImpulsao();
        cabeca = j.getCabeca();
        remate = j.getRemate();
        passe = j.getPasse();
        historico = new LinkedList<>(j.historico);
    }

    public String getNomeJogador() {
        return this.nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public Integer getNumeroJogador() {
        return numeroJogador;
    }

    public void setNumeroJogador(int numeroJogador) {
        this.numeroJogador = numeroJogador;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getImpulsao() {
        return impulsao;
    }

    public void setImpulsao(int impulsao) {
        this.impulsao = impulsao;
    }

    public int getCabeca() {
        return cabeca;
    }

    public void setCabeca(int cabeca) {
        this.cabeca = cabeca;
    }

    public int getRemate() {
        return remate;
    }

    public void setRemate(int remate) {
        this.remate = remate;
    }

    public int getPasse() {
        return passe;
    }

    public void setPasse(int passe) {
        this.passe = passe;
    }

    public void setHistorico(String equipa) {
        this.historico.add(equipa);
    }

    public List<String> getHistorico() {
        return historico;
    }

    public String toString() {
        return this.nomeJogador + "\n";
    }

    /**
     * Metodo para ser usado aquando da consulta de um jogador
     * @return String dos dados do jogador
     */
    abstract public String prettyToString();

       /**
     * Metodo que é usado para criar valores random para habilidades não fornecidas no parse
     * @param campos
     * @return um valor para habilidade
     */
    static int getSkillRandom(String[] campos) {
        int sum = 0;
        for (int i = 2; i < campos.length; i++) {
            sum += Integer.parseInt(campos[i]);
        }
        int count = campos.length - 2;
        int mean = sum / count;
        int min = mean <= 10 ? 0 : mean - 10;    //Valores minimos
        int max = mean >= 90 ? 99 : mean + 10;  // valores maximos
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    /**
     * Metodo que devolve a média de todas as habilidades de um jogador
     * @return a média de todas as habiidades
     */
    abstract public int Rating();


    /**
     *  Metodo que devolve a média das habilidades base de um jogador
     * @return int média
     * @deprecated Not in use
     */
    public int RatingBase() {
        return ((cabeca + destreza + impulsao + remate
                + velocidade + resistencia + passe) / 7);


    }

    /**
     * Metodo diz se um jogador tem nome igual a outro
     * @param name nome a ser verrificado
     * @return True se é igual False se não for igual
     */
    public boolean nameEquals(String name){
        return this.nomeJogador.equals(name);
    }


    abstract public Jogador clone();

    /**
     * Metodo para receber o nome da Habilidade especifica de jogador
     * @return String com o nome da Habilidade especifica
     */
    abstract public String specialName();

    /**
     * Setter para alterar a habilidade especifica da posição
     * @param special valor a ser introduzido
     */
    abstract public void setSpecial(int special);

    /**
     * Getter da Habilidade especial
     * @return in valor da habilidade
     */
    abstract public int getSpecial();
}