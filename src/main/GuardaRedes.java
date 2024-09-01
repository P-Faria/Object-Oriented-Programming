public class GuardaRedes extends Jogador{
    private int elasticidade;



    public GuardaRedes (String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int pas, int elast) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, pas);
        elasticidade = elast;
    }

    public GuardaRedes(GuardaRedes redes) {
        super(redes);
        elasticidade= redes.getElasticidade();
    }

    public int getElasticidade() {
        return elasticidade;
    }

    public void setElasticidade(int elasticidade) {
        this.elasticidade = elasticidade;
    }

    /**
     * Metodo que Cria um Jogador dependendo do input por ficheiro de texto
     * Caso não haja valor para habiidade especial ele gera um numero baseado nas habilidades
     * do Jogador
     * @param input String com formatação CSV
     * @return Jogador
     */
    public static GuardaRedes parse(String input){
        String[] campos = input.split(",");
        return new GuardaRedes(campos[0], Integer.parseInt(campos[1]),
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
        double doub= (((getCabeca()*0.5)+(getDestreza()*0.75)+(getImpulsao()*1.50)+(getRemate()*1)
                +(getVelocidade()*0.75)+(getResistencia()*0.75)+(getPasse()*1)+(getElasticidade()*1.75))/8);

        return (int) doub;    }

    public String prettyToString(){

        String sb = "--Perfil do Jogador-- " + "\nNome: " + this.getNomeJogador() + "\n" +
                "Numero: " + this.getNumeroJogador() + "\n" + "-- Valores de Habilidade--\n" +
                "Velocidade: " + this.getVelocidade() + "\tResistencia: " + this.getResistencia() +
                "\nDestreza: " + this.getDestreza() + ("\tImpulsão: ") + this.getImpulsao() +
                "\nCabeceamento: " + this.getCabeca() + "\tRemate: " + this.getRemate() +
                "\nPasse: " + this.getPasse() + "\tElasticidade: " + this.getElasticidade() +
                "\n\nRating: " + this.Rating();
        return sb;
    }

    public GuardaRedes clone(){
        return new GuardaRedes(this);
    }

    @Override
    public String specialName() {
        return "Elasticidade";
    }
    @Override
    public void setSpecial(int special) {
        this.setElasticidade(special);
    }

    @Override
    public int getSpecial() {
        return this.getElasticidade();
    }
}
