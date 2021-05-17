public class Medio extends Jogador {
    private int recuperacao;
    public Medio(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p, int rec) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
        recuperacao = rec;
    }

    public int getRecuperacao() {
        return recuperacao;
    }

    public void setRecuperacao(int recuperacao) {
        this.recuperacao = recuperacao;
    }

    public static Medio parse(String input){
        String[] campos = input.split(",");
        int sum=0;
        if (campos.length<10){
            int skill =getSkillRandom(campos);
            return new Medio(campos[0], Integer.parseInt(campos[1]),
                    Integer.parseInt(campos[2]),
                    Integer.parseInt(campos[3]),
                    Integer.parseInt(campos[4]),
                    Integer.parseInt(campos[5]),
                    Integer.parseInt(campos[6]),
                    Integer.parseInt(campos[7]),
                    Integer.parseInt(campos[8]),
                    skill);
        }
        return new Medio(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]));
    }


    public int Rating(Medio j){
        double doub= (((j.getCabeca()*0.75)+(j.getDestreza()*1)+(j.getImpulsao()*0.5)+(j.getRemate()*0.25)
                +(j.getVelocidade()*0.50)+(j.getResistencia()*1.25)+(j.getPasse()*1.25)+(j.getRecuperacao()*1.50)/8));

        return (int) doub;
    }

    public int Rating(){
        double doub= (((getCabeca()*0.75)+(getDestreza()*1)+(getImpulsao()*0.5)+(getRemate()*0.25)
                +(getVelocidade()*0.50)+(getResistencia()*1.25)+(getPasse()*1.25)+(getRecuperacao()*1.50))/8);

        return (int) doub;
    }
}
