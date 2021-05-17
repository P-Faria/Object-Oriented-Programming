public class Avancado extends Jogador {
    private int finalizacao;
    public Avancado(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p,int fin) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
        finalizacao= fin;
    }

    public static Avancado parse(String input){
        String[] campos = input.split(",");
        int sum=0;
        if (campos.length<10){
            int skill =getSkillRandom(campos);
            return new Avancado(campos[0], Integer.parseInt(campos[1]),
                    Integer.parseInt(campos[2]),
                    Integer.parseInt(campos[3]),
                    Integer.parseInt(campos[4]),
                    Integer.parseInt(campos[5]),
                    Integer.parseInt(campos[6]),
                    Integer.parseInt(campos[7]),
                    Integer.parseInt(campos[8]),
                    skill);
        }
        return new Avancado(campos[0], Integer.parseInt(campos[1]),
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
