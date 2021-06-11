public class Medio extends Jogador {
    private int recuperacao;
    public Medio(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p, int rec) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
        recuperacao = rec;
    }

    public Medio(Medio medio) {
        super(medio);
        recuperacao=medio.getRecuperacao();
    }

    public int getRecuperacao() {
        return recuperacao;
    }

    public void setRecuperacao(int recuperacao) {
        this.recuperacao = recuperacao;
    }

    public static Medio parse(String input){
        String[] campos = input.split(",");
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



    public int Rating(){
        double doub= (((getCabeca()*0.75)+(getDestreza()*1)+(getImpulsao()*0.5)+(getRemate()*0.25)
                +(getVelocidade()*0.50)+(getResistencia()*1.25)+(getPasse()*1.25)+(getRecuperacao()*1.50))/8);

        return (int) doub;
    }


    public Medio clone() {
        return new Medio(this);
    }

    public String prettyToString(){

        return "--Perfil do Jogador-- " + "\nNome: " + this.getNomeJogador() + "\n" +
                "Numero: " + this.getNumeroJogador() + "\n" + "-- Valores de Habilidade--\n" +
                "Velocidade: " + this.getVelocidade() + "\tResistencia: " + this.getResistencia() +
                "\nDestreza: " + this.getDestreza() + ("\tImpulsão: ") + this.getImpulsao() +
                "\nCabeceamento: " + this.getCabeca() + "\tRemate: " + this.getRemate() +
                "\nPasse: " + this.getPasse() + "\tRecuperação: " + this.getRecuperacao() +
                "\n\nRating: " + this.Rating();
    }

    @Override
    public String specialName() {
        return "Recuperação";
    }
    @Override
    public void setSpecial(int special) {
        this.setRecuperacao(special);
    }

    @Override
    public int getSpecial() {
        return this.getRecuperacao();
    }
}
