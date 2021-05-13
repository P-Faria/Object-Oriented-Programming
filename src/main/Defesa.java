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
            int sum = IntStream.range(1, campos.length); //TODO: nao esta a funcionar
            int count= (int) IntStream.range(1, campos.length).count();
            int mean = sum/count;
            int skill= ThreadLocalRandom.current().nextInt(mean-10,mean+10);
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

}
