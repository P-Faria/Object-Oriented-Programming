import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jogo implements Serializable {
    private String equipaCasa;
    private String equipaFora;
    private int golosCasa;
    private int golosFora;
    private LocalDate date;
    private List<Integer> jogadoresCasa;
    private List<Integer> jogadoresFora;
    Map<Integer, Integer> substituicoesCasa;
    Map<Integer, Integer> substitucoesFora;

    public Jogo (String ec, String ef, int gc, int gf, LocalDate d,  List<Integer> jc, Map<Integer, Integer> sc,  List<Integer> jf, Map<Integer, Integer> sf){
        equipaCasa = ec;
        equipaFora = ef;
        golosCasa = gc;
        golosFora = gf;
        date = d;
        jogadoresCasa = new ArrayList<>(jc);
        jogadoresFora = new ArrayList<>(jf);
        substituicoesCasa = new HashMap<>(sc);
        substitucoesFora = new HashMap<>(sf);
    }

    public static Jogo parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        List<Integer> jc = new ArrayList<>();
        List<Integer> jf = new ArrayList<>();
        Map<Integer, Integer> subsC = new HashMap<>();
        Map<Integer, Integer> subsF = new HashMap<>();
        for (int i = 5; i < 16; i++){
            jc.add(Integer.parseInt(campos[i]));
        }
        for (int i = 16; i < 19; i++){
            String[] sub = campos[i].split("->");
            subsC.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        for (int i = 19; i < 30; i++){
            jf.add(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            subsF.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        return new Jogo(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                jc, subsC, jf, subsF);
    }

    public String getEquipaCasa() {
        return equipaCasa;
    }

    public void setEquipaCasa(String equipaCasa) {
        this.equipaCasa = equipaCasa;
    }

    public String getEquipaFora() {
        return equipaFora;
    }

    public void setEquipaFora(String equipaFora) {
        this.equipaFora = equipaFora;
    }

    public int getGolosCasa() {
        return golosCasa;
    }

    public void setGolosCasa(int golosCasa) {
        this.golosCasa = golosCasa;
    }

    public int getGolosFora() {
        return golosFora;
    }

    public void setGolosFora(int golosFora) {
        this.golosFora = golosFora;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Integer> getJogadoresCasa() {
        return jogadoresCasa;
    }

    public void setJogadoresCasa(List<Integer> jogadoresCasa) {
        this.jogadoresCasa = jogadoresCasa;
    }

    public List<Integer> getJogadoresFora() {
        return jogadoresFora;
    }

    public void setJogadoresFora(List<Integer> jogadoresFora) {
        this.jogadoresFora = jogadoresFora;
    }

    public Map<Integer, Integer> getSubstituicoesCasa() {
        return substituicoesCasa;
    }

    public void setSubstituicoesCasa(Map<Integer, Integer> substituicoesCasa) {
        this.substituicoesCasa = substituicoesCasa;
    }

    public Map<Integer, Integer> getSubstitucoesFora() {
        return substitucoesFora;
    }

    public void setSubstitucoesFora(Map<Integer, Integer> substitucoesFora) {
        this.substitucoesFora = substitucoesFora;
    }

    public String Vencedor(){
        if (this.golosCasa > this.golosFora) return this.equipaCasa;
        else if(this.golosFora > this.golosCasa) return this.equipaFora;
        else return "Empate";
    }

    public String toString() {
        return  "Jogo:" + equipaCasa + " vs. " + equipaFora
        + "\nData de Jogo:" + date.toString()
        + "\nResultado: " + golosCasa + ":" + golosFora
        + "\nVencedor:"   + this.Vencedor()
        + "\nSubs Casa -> " + substituicoesCasa.toString()
        + "\nSubs Fora -> " + substitucoesFora.toString()
        + "\n\n";
    }

    public String simpleToString(){
        return  "Jogo:" + equipaCasa + " vs. " + equipaFora;
    }

}
