public class Main
{

    public static void main(String[] args) throws LinhaIncorretaException {
        Estado status = new Estado();
        Parser.parse(status);
        Jogador j = status.equipas.get("Mahler Athletic").getJogadorByName("Pedro Miguel Sampaio Faria");
        status.equipas.get("Mahler Athletic").removePlayerTeam(j);
        status.equipas.get("Sporting Club Chopin").insereJogador(j);
        System.out.println(j.getHistorico().toString());


    }


}
