
public class Defesa extends Jogador {
    private int carrinhos;


    public Defesa(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p,int carr) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
        carrinhos=carr;
    }

    public Defesa(Defesa defesa) {
        super(defesa);
        carrinhos=defesa.getCarrinhos();
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

    public int Rating(){
        double doub= (((getCabeca()*1)+(getDestreza()*1)+(getImpulsao()*0.75)+(getRemate()*0.25)
                +(getVelocidade()*0.25)+(getResistencia()*1.25)+(getPasse()*1.75)+(getCarrinhos()*1.75))/8);

        return (int) doub;
    }

    @Override
    public Defesa clone() {
        return new Defesa(this);
    }

    public String prettyToString(){

        String sb = "--Perfil do Jogador-- " + "\nNome: " + this.getNomeJogador() + "\n" +
                "Numero: " + this.getNumeroJogador() + "\n" + "-- Valores de Habilidade--\n" +
                "Velocidade: " + this.getVelocidade() + "\tResistencia: " + this.getResistencia() +
                "\nDestreza: " + this.getDestreza() + ("\tImpulsão: ") + this.getImpulsao() +
                "\nCabeceamento: " + this.getCabeca() + "\tRemate: " + this.getRemate() +
                "\nPasse: " + this.getPasse() + "\tCarrinhos: " + this.getCarrinhos() +
                "\n\nRating: " + this.Rating();
        return sb;
    }

    @Override
    public String specialName() {
        return "Carrinhos";
    }
    @Override
    public void setSpecial(int special) {
        this.setCarrinhos(special);
    }

    @Override
    public int getSpecial() {
        return this.getCarrinhos();
    }
}
