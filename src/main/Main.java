import java.io.*;
import java.util.List;

public class Main
{

    public static void main(String[] args) throws LinhaIncorretaException, IOException, ClassNotFoundException, CloneNotSupportedException {
        Estado status = new Estado();
        Parser.parse(status);
        save(status,"D:\\Cloud\\OneUminho\\OneDrive - Universidade do Minho\\20-21\\2\\POO\\Projeto\\src\\bin\\save.OBJ");
        Jogador j = status.equipas.get("Mahler Athletic").getJogadorByName("Pedro Miguel Sampaio Faria");
        status.equipas.get("Mahler Athletic").removePlayerTeam(j);
        status.equipas.get("Sporting Club Chopin").insereJogador(j);
        System.out.println(j.getHistorico().toString());
        System.out.println(status.equipas.get("Mahler Athletic").RatingEquipa());
        status = load("D:\\Cloud\\OneUminho\\OneDrive - Universidade do Minho\\20-21\\2\\POO\\Projeto\\src\\bin\\save.OBJ");


        if (status.equipas.get("Mahler Athletic").getJogadorByName("Pedro Miguel Sampaio Faria") != null){
            System.out.println("Pedro in Mahler");
        }


        List<Integer> gamers = status.jogos.get(2).getJogadoresCasa();
        String nome = status.jogos.get(2).getEquipaCasa();
        int rat = status.equipas.get(nome).ratingJogadores(gamers);



    }

    // Save in object file
    public static void save(Estado state, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(state);
        System.out.println("Estado salvo em: "+fileName);
        oos.close();
        fos.close();
    }

    // Load from object file
    public static Estado load(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Estado loaded = (Estado) ois.readObject();
        System.out.println("Estado lido de: "+fileName);
        ois.close();
        fis.close();
        return loaded;
    }

}
