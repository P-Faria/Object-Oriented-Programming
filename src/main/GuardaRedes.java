public class GuardaRedes extends Jogador{
    private int elasticidade;



    public GuardaRedes (String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p, int elast) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
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

    public int Rating(GuardaRedes j){
        double doub= (((j.getCabeca()*0.5)+(j.getDestreza()*0.75)+(j.getImpulsao()*1.50)+(j.getRemate()*1)
                +(j.getVelocidade()*0.75)+(j.getResistencia()*0.75)+(j.getPasse()*1)+(j.getElasticidade()*1.75)/8));

        return (int) doub;
    }

    public int Rating(){
        double doub= (((getCabeca()*0.5)+(getDestreza()*0.75)+(getImpulsao()*1.50)+(getRemate()*1)
                +(getVelocidade()*0.75)+(getResistencia()*0.75)+(getPasse()*1)+(getElasticidade()*1.75))/8);

        return (int) doub;
    }

    public GuardaRedes clone(){
        return new GuardaRedes(this);
    }
}
