public class Avancado extends Jogador {
    private int finalizacao;
    public Avancado(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p,int fin) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
        finalizacao= fin;
    }

    public Avancado(Avancado avancado) {
        super(avancado);
        finalizacao=avancado.getFinalizacao();
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

    public int getFinalizacao() {
        return finalizacao;
    }

    public void setFinalizacao(int finalizacao) {
        this.finalizacao = finalizacao;
    }

    public int Rating(Avancado j){
        double doub= (((j.getCabeca()*0.75)+(j.getDestreza()*1)+(j.getImpulsao()*0.5)+(j.getRemate()*0.25)
                +(j.getVelocidade()*0.50)+(j.getResistencia()*1.25)+(j.getPasse()*1.25)+(j.getFinalizacao()*1.50)/8));

        return (int) doub;
    }

    public int Rating(){
        double doub= (((getCabeca()*1)+(getDestreza()*0.75)+(getImpulsao()*0.5)+(getRemate()*1.75)
                +(getVelocidade()*0.50)+(getResistencia()*0.75)+(getPasse()*0.25)+(getFinalizacao()*1.50))/8);

        return (int) doub;
    }

    @Override
    public Avancado clone() {
        return new Avancado(this);
    }

    public String prettyToString(){

        String sb = "--Perfil do Jogador-- " + "\nNome: " + this.getNomeJogador() + "\n" +
                "Numero: " + this.getNumeroJogador() + "\n" + "-- Valores de Habilidade--\n" +
                "Velocidade: " + this.getVelocidade() + "\tResistencia: " + this.getResistencia() +
                "\nDestreza: " + this.getDestreza() + ("\tImpulsão: ") + this.getImpulsao() +
                "\nCabeceamento: " + this.getCabeca() + "\tRemate: " + this.getRemate() +
                "\nPasse: " + this.getPasse() + "\tFinalização: " + this.getFinalizacao() +
                "\n\nRating: " + this.Rating();
        return sb;
    }
}
