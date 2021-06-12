import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    /**
     * Metodo que popula um ESTADO apartir de um ficheiro formatado em CSV
     * @param e Estado onde serão inseridos os dados
     * @param nomeFich localização e nome do ficheiro
     * @throws LinhaIncorretaException Caso o ficheiro não esteja bem formatado
     */
    public static void parse(Estado e,String nomeFich) throws LinhaIncorretaException {
        List<String> linhas = new ArrayList<>();
        try {
            linhas = lerFicheiro(nomeFich);
        }catch (Exception excep){
            System.out.println("Nome Ficheiro errado!");
            System.exit(1);
        }
        Map<String, Equipa> equipas = new HashMap<>(); //nome, equipa
        Map<Integer, Jogador> jogadores = new HashMap<>(); //numero, jogador
        List<Jogo> jogos = new ArrayList<>();
        int id =0;
        Equipa ultima = null; Jogador j;
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]){
                case "Equipa":

                    Equipa eq = Equipa.parse(linhaPartida[1]);
                    equipas.put(eq.getNome(), eq);
                    ultima = eq;
                    break;
                case "Guarda-Redes":
                    j = GuardaRedes.parse(linhaPartida[1]);
                    jogadores.put(id++, j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    j.setHistorico(ultima.getNome());
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed

                    break;
                case "Defesa":
                    j = Defesa.parse(linhaPartida[1]);
                    jogadores.put(id++, j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    j.setHistorico(ultima.getNome());
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed

                    break;
                case "Medio":
                    j = Medio.parse(linhaPartida[1]);
                    jogadores.put(id++, j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    j.setHistorico(ultima.getNome());
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed

                    break;
                case "Lateral":
                    j = Lateral.parse(linhaPartida[1]);
                    jogadores.put(id++, j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    j.setHistorico(ultima.getNome());
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed

                    break;
                case "Avancado":
                    j = Avancado.parse(linhaPartida[1]);
                    jogadores.put(id++, j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    j.setHistorico(ultima.getNome());
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    ;
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

    }

    /**
     * Metodo que le 1 ficheiro e o transforma numa Lista
     * @param nomeFich localização e nome do ficheiro
     * @return uma Lista com todas as Strings idas
     * @throws IOException Caso o ficheiro não exista
     */
    public static List<String> lerFicheiro(String nomeFich) throws IOException {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8);
        } catch(IOException exc) {
            throw new IOException("Invalid FileName");
        }
        return lines;
    }
}
