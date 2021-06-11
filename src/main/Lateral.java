import java.util.concurrent.ThreadLocalRandom;

public class Lateral extends Jogador{
    private int cruzamento;
    public Lateral(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p, int cruz) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
        cruzamento = cruz;
    }

    public Lateral(Lateral lateral) {
        super(lateral);
        cruzamento=lateral.cruzamento;
    }

    public int getCruzamento() {
        return cruzamento;
    }

    public void setCruzamento(int cruzamento) {
        this.cruzamento = cruzamento;
    }

    public static Lateral parse(String input){
        String[] campos = input.split(",");
        if (campos.length<10){
            int skill =getSkillRandom(campos);
            return new Lateral(campos[0], Integer.parseInt(campos[1]),
                    Integer.parseInt(campos[2]),
                    Integer.parseInt(campos[3]),
                    Integer.parseInt(campos[4]),
                    Integer.parseInt(campos[5]),
                    Integer.parseInt(campos[6]),
                    Integer.parseInt(campos[7]),
                    Integer.parseInt(campos[8]),
                    skill);
        }
        return new Lateral(campos[0], Integer.parseInt(campos[1]),
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
        double doub= (((getCabeca()*0.25)+(getDestreza()*1)+(getImpulsao()*0.5)+(getRemate()*0.25)
                +(getVelocidade()*1)+(getResistencia()*1.25)+(getPasse()*1.25)+(getCruzamento()*1.50))/8);

        return (int) doub;
    }

    public Lateral clone() {
        return new Lateral(this);
    }

    public String prettyToString(){

        return "--Perfil do Jogador-- " + "\nNome: " + this.getNomeJogador() + "\n" +
                "Numero: " + this.getNumeroJogador() + "\n" + "-- Valores de Habilidade--\n" +
                "Velocidade: " + this.getVelocidade() + "\tResistencia: " + this.getResistencia() +
                "\nDestreza: " + this.getDestreza() + ("\tImpulsão: ") + this.getImpulsao() +
                "\nCabeceamento: " + this.getCabeca() + "\tRemate: " + this.getRemate() +
                "\nPasse: " + this.getPasse() + "\tCruzamento: " + this.getCruzamento() +
                "\n\nRating: " + this.Rating();
    }

    @Override
    public String specialName() {
        return "Cruzamento";
    }

    @Override
    public void setSpecial(int special) {
        this.setCruzamento(special);
    }

    @Override
    public int getSpecial() {
        return this.getCruzamento();
    }
}
