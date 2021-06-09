import java.io.*;
import java.util.List;

public class Main
{
    /*
    public static void main(String[] args) throws Exception {
        Estado status = new Estado();
        Parser.parse(status);

        Jogador j = status.equipas.get("Mahler Athletic").getJogadorByName("Pedro Miguel Sampaio Faria");
        j.setRemate(99);
        status.equipas.get("Mahler Athletic").removePlayerTeam(j);
        status.equipas.get("Sporting Club Chopin").insereJogador(j);
        System.out.println(j.getHistorico().toString());
        System.out.println(status.equipas.get("Mahler Athletic").RatingEquipa());



        if (status.equipas.get("Mahler Athletic").getJogadorByName("Pedro Miguel Sampaio Faria") != null){
            System.out.println("Pedro in Mahler");
        }


        List<Integer> gamers = status.jogos.get(2).getJogadoresCasa();
        String nome = status.jogos.get(2).getEquipaCasa();
        int rat = status.equipas.get(nome).ratingJogadores(gamers);

        Jogo teste = status.Jogar(status.jogos.get(4));



    }
    */
    public static void main(String[] args) throws Exception{
        Controller.start();
    }

}
