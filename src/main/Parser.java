import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    public static void parse() throws LinhaIncorretaException {
        List<String> linhas = lerFicheiro("D:\\Cloud\\OneUminho\\OneDrive - Universidade do Minho\\20-21\\2\\POO\\Projeto\\src\\bin\\logs.txt");
        Map<String, Equipa> equipas = new HashMap<>(); //nome, equipa
        Map<Integer, Jogador> jogadores = new HashMap<>(); //numero, jogador
        List<Jogo> jogos = new ArrayList<>();
        Equipa ultima = null; Jogador j = null;
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]){
                case "Equipa":
                    Equipa e = Equipa.parse(linhaPartida[1]);
                    equipas.put(e.getNome(), e);
                    ultima = e;
                    break;
                case "Guarda-Redes":
                    j = GuardaRedes.parse(linhaPartida[1]);
                    jogadores.put(j.getNumeroJogador(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Defesa":
                    j = Defesa.parse(linhaPartida[1]);
                    jogadores.put(j.getNumeroJogador(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Medio":
                    j = Medio.parse(linhaPartida[1]);
                    jogadores.put(j.getNumeroJogador(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Lateral":
                    j = Lateral.parse(linhaPartida[1]);
                    jogadores.put(j.getNumeroJogador(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Avancado":
                    j = Avancado.parse(linhaPartida[1]);
                    jogadores.put(j.getNumeroJogador(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Jogo":
                    Jogo jo = Jogo.parse(linhaPartida[1]);
                    jogos.add(jo);
                    break;
                default:
                    throw new LinhaIncorretaException();

            }
        }

        //debug
        for (Equipa e: equipas.values()){
            System.out.println(e.toString());
        }
        for (Jogo jog: jogos){
            System.out.println(jog.toString());
        }


    }

    public static void parse(Estado e) throws LinhaIncorretaException {
        List<String> linhas = lerFicheiro("D:\\Cloud\\OneUminho\\OneDrive - Universidade do Minho\\20-21\\2\\POO\\Projeto\\src\\bin\\logs.txt");
        Map<String, Equipa> equipas = new HashMap<>(); //nome, equipa
        Map<Integer, Jogador> jogadores = new HashMap<>(); //numero, jogador
        List<Jogo> jogos = new ArrayList<>();
        Equipa ultima = null; Jogador j = null;
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]){
                case "Equipa":
                    debugRatingEquipa(ultima);
                    Equipa eq = Equipa.parse(linhaPartida[1]);
                    equipas.put(eq.getNome(), eq);
                    ultima = eq;
                    break;
                case "Guarda-Redes":
                    j = GuardaRedes.parse(linhaPartida[1]);
                    jogadores.put(j.getNumeroJogador(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed

                    //debug
                    debugRating(j,ultima);
                    break;
                case "Defesa":
                    j = Defesa.parse(linhaPartida[1]);
                    jogadores.put(j.getNumeroJogador(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    //debug
                    debugRating(j,ultima);
                    break;
                case "Medio":
                    j = Medio.parse(linhaPartida[1]);
                    jogadores.put(j.getNumeroJogador(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    //debug
                    debugRating(j,ultima);
                    break;
                case "Lateral":
                    j = Lateral.parse(linhaPartida[1]);
                    jogadores.put(j.getNumeroJogador(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    //debug
                    debugRating(j,ultima);
                    break;
                case "Avancado":
                    j = Avancado.parse(linhaPartida[1]);
                    jogadores.put(j.getNumeroJogador(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    //debug
                    debugRating(j,ultima);
                    break;
                case "Jogo":
                    Jogo jo = Jogo.parse(linhaPartida[1]);
                    jogos.add(jo);
                    break;
                default:
                    throw new LinhaIncorretaException();

            }
        }
        e.setEquipas(equipas);
        e.setJogadores(jogadores);
        e.setJogos(jogos);

        //debug
        for (Equipa Ee: e.equipas.values()){
            System.out.println(Ee.toString());
        }
        for (Jogo jog: e.jogos){
            System.out.println(jog.toString());
        }

    }
    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }

    private static void debugRating(Jogador j, Equipa e){
        System.out.println(j.getNomeJogador() + "\t\t\tTeam: "+e.getNome() + "\tPosição:  " +j.getClass()  + "\tRating >>> " +j.Rating() +"vs "+j.RatingBase());

    }

    private static void debugRatingEquipa(Equipa e){
        if  (e != null) System.out.println("Rating eq:\t"+e.RatingEquipa());
    }
}
