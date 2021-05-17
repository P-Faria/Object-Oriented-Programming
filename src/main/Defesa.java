import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Defesa extends Jogador {
    private int carrinhos;
    public Defesa(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p,int carr) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
        carrinhos=carr;
    }

    public static Defesa parse(String input){
        String[] campos = input.split(",");
        if (campos.length<10){
            int skill=getSkillRandom(campos);
            return new Defesa(campos[0], Integer.parseInt(campos[1]),
                    Integer.parseInt(campos[2]),
                    Integer.parseInt(campos[3]),
                    Integer.parseInt(campos[4]),
                    Integer.parseInt(campos[5]),
                    Integer.parseInt(campos[6]),
                    Integer.parseInt(campos[7]),
                    Integer.parseInt(campos[8]),
                    skill);
        }

        return new Defesa(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]));
    }

    public int getCarrinhos() {
        return carrinhos;
    }

    public void setCarrinhos(int carrinhos) {
        this.carrinhos = carrinhos;
    }

    public int Rating(Defesa j){
        double doub= (((j.getCabeca()*0.75)+(j.getDestreza()*1)+(j.getImpulsao()*0.5)+(j.getRemate()*0.25)
                +(j.getVelocidade()*0.25)+(j.getResistencia()*1.25)+(j.getPasse()*1.50)+(j.getCarrinhos()*1.50)/8));

        return (int) doub;
    }

    public int Rating(){
        double doub= (((getCabeca()*0.75)+(getDestreza()*1)+(getImpulsao()*0.5)+(getRemate()*0.25)
                +(getVelocidade()*0.25)+(getResistencia()*1.25)+(getPasse()*1.50)+(getCarrinhos()*1.50))/8);

        return (int) doub;
    }

}
