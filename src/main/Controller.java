import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {

    public static void start() throws Exception {
        Estado status = null;
        Scanner inputScanner = new Scanner(System.in);
        //inputScanner.useDelimiter("/n");
        while (true) {
            Menu.clean();
            Menu.menuPrincipal(status == null);
            int option = inputScanner.nextInt();
            switch (option) {
                case 1: // Carregar Estado
                    Menu.clean();
                    String nomeFich;
                    option = 0;
                    while (1 > option || option > 3) {
                        Menu.menuCarregamento();
                        option = inputScanner.nextInt();
                        Menu.clean();
                    }
                    if (option == 1) nomeFich = "src/bin/logs.txt"; //Default
                    else if (option == 3) {  //Sair
                        System.out.println("\nRegressando ao menu Principal\n");
                        Menu.clean();
                        break;
                    } else { //2 <> Carregar de custom path
                        System.out.println("\nPor Favor introduza o caminho do ficheiro\n");
                        nomeFich = inputScanner.nextLine() + inputScanner.nextLine();
                        Menu.clean();
                    }

                    //proceder ao carregamento
                    if (status == null) status = new Estado();
                    try {
                        Parser.parse(status, nomeFich);
                    } catch (Exception e) {
                        System.out.println("Erro durante o Carregamento\n");
                    }

                    System.out.println("\nCarregamento efetuado\n");
                    Menu.clean();
                    break;
                case 2:
                    Menu.clean();
                    option = 0;
                    while (1 > option || option > 4) {
                        Menu.menuCreation();
                        option = inputScanner.nextInt();
                        Menu.clean();
                    }
                    if (option == 1) { // Criar equipa
                        Menu.menuCreationEquipa();
                        if (status == null) status = new Estado();
                        String team = inputScanner.nextLine() + inputScanner.nextLine();
                        Equipa nova = new Equipa(team);
                        status.equipas.put(team, nova);
                        break;
                    } else if (option == 2) { // Criar Jogador
                        Menu.menuCreationJogador();
                        if (status == null) status = new Estado();
                        String jNome = inputScanner.nextLine() + inputScanner.nextLine();
                        int pos =0;
                        while (pos <1 || pos >5) {
                            Menu.menuCreationJogadorPos(); // selecionar posição
                            pos = inputScanner.nextInt();
                        }
                        Menu.menuCreationJogadorNumJog(); // numero de Jogador
                        int jNum = inputScanner.nextInt();
                        Menu.menuCreationJogadorStats(); // stats
                        int vel = 0, res = 0, des = 0, imp = 0, cab = 0, rem = 0, pas = 0, spe = 0;
                        option = inputScanner.nextInt();
                        if (option == 1) {
                            int check = 0;

                            while (check == 0) {
                                Menu.menuCreationJogadorStatsCustom();
                                vel = inputScanner.nextInt();
                                res = inputScanner.nextInt();
                                des = inputScanner.nextInt();
                                imp = inputScanner.nextInt();
                                cab = inputScanner.nextInt();
                                rem = inputScanner.nextInt();
                                pas = inputScanner.nextInt();
                                spe = inputScanner.nextInt();
                                if ((vel > 0 && vel < 100) && (res > 0 && res < 100) && (des > 0 && des < 100) &&
                                        (imp > 0 && imp < 100) && (cab > 0 && cab < 100) && (rem > 0 && rem < 100) &&
                                        (pas > 0 && pas < 100) && (spe > 0 && spe < 100)) check = 1;
                                else {
                                    System.out.println("Valor incorreto\n");
                                    check = 0;
                                }
                            }

                        } else if (option == 2) {
                            vel = (int) ((Math.random() * (100 - 1)) + 1);
                            res = (int) ((Math.random() * (100 - 1)) + 1);
                            des = (int) ((Math.random() * (100 - 1)) + 1);
                            imp = (int) ((Math.random() * (100 - 1)) + 1);
                            cab = (int) ((Math.random() * (100 - 1)) + 1);
                            rem = (int) ((Math.random() * (100 - 1)) + 1);
                            pas = (int) ((Math.random() * (100 - 1)) + 1);
                            spe = (int) ((Math.random() * (100 - 1)) + 1);

                        } else {
                            System.out.println("opção invalida, regressando a menu principal");
                            break;
                        }
                        String nomeEquipa ;
                        Menu.menuCreationJogadorEquipa();
                        option = inputScanner.nextInt();
                        if (option == 1) {
                            Menu.menuCreationJogadorEquipaSim();
                            nomeEquipa = inputScanner.nextLine() + inputScanner.nextLine();
                        } else nomeEquipa = null;
                        Jogador jog;
                        switch (pos) {
                            case 1:
                                jog = new GuardaRedes(jNome, jNum, vel, res, des, imp, cab, rem, pas, spe);
                            case 2:
                                jog = new Defesa(jNome, jNum, vel, res, des, imp, cab, rem, pas, spe);
                            case 3:
                                jog = new Lateral(jNome, jNum, vel, res, des, imp, cab, rem, pas, spe);
                            case 4:
                                jog = new Medio(jNome, jNum, vel, res, des, imp, cab, rem, pas, spe);
                            case 5:
                                jog = new Avancado(jNome, jNum, vel, res, des, imp, cab, rem, pas, spe);
                                break;
                            default:
                                throw new ParameterNotInScopeException("Unexpected value: " + pos);
                        }
                        int idMax = status.jogadores.keySet().stream().max(Integer::compareTo).orElse(0);
                        status.jogadores.put(idMax + 1, jog);
                        if (nomeEquipa != null && status.equipas.containsKey(nomeEquipa))
                            status.equipas.get(nomeEquipa).insereJogador(jog);
                        else {
                            System.out.println("Nome de Equipa Inexistente");
                            break;

                        }
                    }else break;
                case 3:
                    if (status == null) {
                        System.out.println("Não se pode consultar algo que não existe!\n");
                        break;
                    }
                    Menu.clean();
                    option = 0;
                    while (1 > option || option > 3) {
                        Menu.menuConsulta();
                        option = inputScanner.nextInt();
                        Menu.clean();
                    }
                    if (option == 1) {// consultar equipas
                        Menu.menuConsultaEquipas();
                        String consultEq = inputScanner.nextLine() + inputScanner.nextLine();
                        if (consultEq.equals("")) {
                            System.out.print(status.equipas.toString());
                            pressEnterKeyToContinue();
                            break;

                        }else if ((!Objects.requireNonNull(status).equipas.containsKey(consultEq))){
                            throw new NotFoundException("Equipa inexistente");
                        }else{
                            Equipa team = status.equipas.get(consultEq);
                            System.out.print("\n" + team.toString());
                            Controller.pressEnterKeyToContinue();
                            break;
                        }


                    } else if (option == 2) { //consultar jogadores
                        Menu.menuConsultaJogador();
                        String consultJog = inputScanner.nextLine() + inputScanner.nextLine();
                        if(consultJog.equals("")) {
                            System.out.print(status.jogadores.toString());
                            pressEnterKeyToContinue();
                            break;
                        }else if (!(status.isPlayer(consultJog))) {
                            System.out.println("Nome Incorreto");
                            pressEnterKeyToContinue();
                            break;
                        } else{
                            Jogador jog = status.getJogadorByName(consultJog);
                            if (status.isPlayerinAnyTeam(jog)) {
                                System.out.print(jog.prettyToString());
                            } else
                                System.out.print(jog.prettyToString() + "\n\nJogador da Equipa: " + status.getTeamNameifPlayerPresent(jog) + "\nEquipas onde Jogou:\n" + jog.getHistorico());
                            Controller.pressEnterKeyToContinue();
                            break;
                        }

                    }else {
                        System.out.println("Voltando ao Menu Inical\n");
                        break;
                    }


                case 4:
                    Menu.clean();
                    option = 0;
                    if(status == null){
                        System.out.println("Por favor crie ou carregue dados primeiro\n");
                        break;
                    }
                    while (1 > option || option > 4) {
                        Menu.menuJogo();
                        option = inputScanner.nextInt();
                        Menu.clean();
                    }
                    if (option == 1) {
                        Menu.menuJogoCriar();
                        String ec = inputScanner.nextLine() + inputScanner.nextLine();
                        if (!status.equipas.containsKey(ec)){
                            System.out.println("Erro: Equipa Inexistente\n");
                            pressEnterKeyToContinue();
                            break;
                        }
                        Menu.menuJogoCriarFora();
                        String ef = inputScanner.nextLine() + inputScanner.nextLine();
                        if (!status.equipas.containsKey(ec)){
                            System.out.println("Erro: Equipa Inexistente\n");
                            pressEnterKeyToContinue();
                            break;
                        }
                        Menu.menuJogoCriarData();
                        LocalDate d;
                        String str = inputScanner.nextLine()+inputScanner.nextLine();
                        if (str.equals("")) d = LocalDate.now();
                        else {
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                            d = LocalDate.parse(str, dtf);
                        }

                        Menu.menuJogoCriarJc();
                        String linha = inputScanner.nextLine() + inputScanner.nextLine();
                        List<Integer> jc = criaLista(linha);
                        if (jc.size()!=11) throw new ParameterNotInScopeException();
                        Menu.menuJogoCriarJf();
                        linha = inputScanner.nextLine()+inputScanner.nextLine();
                        List<Integer> jf = criaLista(linha);
                        if (jf.size()!=11) throw new ParameterNotInScopeException();
                        Map<Integer,Integer> sc = criarSubs(1);
                        //36,29,20,48,8,17,12,11,39,2,22
                        Map<Integer,Integer> sf = criarSubs(2);
                        //20,42,9,44,16,25,4,26,50,35,37
                        Jogo criado = status.Jogar(ec, ef, d, jc, sc, jf, sf);
                        System.out.print(criado);
                        pressEnterKeyToContinue();
                        break;
                    }
                    else if(option==2){
                        Menu.menuJogoReplay();
                        int nJogo = inputScanner.nextInt();
                        Jogo rePlay = status.jogos.get(nJogo);
                        rePlay =status.Jogar(rePlay);
                        status.jogos.add(nJogo,rePlay);
                        System.out.print(rePlay);
                        pressEnterKeyToContinue();
                        break;
                    }else break;

                case 5:
                    Menu.clean();
                    while (1 > option || option > 4) {
                        Menu.menuEditar();
                        option = inputScanner.nextInt();
                        Menu.clean();
                    }
                    if (option==1){ // Transferir Jogador
                        Menu.menuEditarTransfer();
                        String nomeJog = inputScanner.nextLine() +inputScanner.nextLine();
                        Jogador player = status.getJogadorByName(nomeJog);
                        String currentT  = status.getTeamNameifPlayerPresent(player);
                        Menu.menuEditarTransferTo(currentT);
                        String teamTo = inputScanner.nextLine() + inputScanner.nextLine();
                        status.transferencia(player,status.equipas.get(currentT),status.equipas.get(teamTo));

                    }else if (option==2){// Modificar valores jogador
                        Menu.menuEditarHabilidade();
                        String nomeJog = inputScanner.nextLine() + inputScanner.nextLine();
                        Jogador player = status.getJogadorByName(nomeJog);
                        Menu.menuEditarHabilidade2(player.specialName());
                        option = inputScanner.nextInt();
                        int input;
                        switch (option){
                            case 1:
                                Menu.menuEditarHabilidadeInput();
                                input = inputScanner.nextInt();
                                if (input==0) input= (int) (Math.random() * 100) +1;
                                player.setVelocidade(input);
                                status.updatePlayer(player);
                            case 2:
                                Menu.menuEditarHabilidadeInput();
                                input = inputScanner.nextInt();
                                if (input==0) input= (int) (Math.random() * 100) +1;
                                player.setResistencia(input);
                                status.updatePlayer(player);
                            case 3:
                                Menu.menuEditarHabilidadeInput();
                                input = inputScanner.nextInt();
                                if (input==0) input= (int) (Math.random() * 100) +1;
                                player.setDestreza(input);
                                status.updatePlayer(player);
                            case 4:
                                Menu.menuEditarHabilidadeInput();
                                input = inputScanner.nextInt();
                                if (input==0) input= (int) (Math.random() * 100) +1;
                                player.setImpulsao(input);
                                status.updatePlayer(player);
                            case 5:
                                Menu.menuEditarHabilidadeInput();
                                input = inputScanner.nextInt();
                                if (input==0) input= (int) (Math.random() * 100) +1;
                                player.setCabeca(input);
                                status.updatePlayer(player);
                            case 6:
                                Menu.menuEditarHabilidadeInput();
                                input = inputScanner.nextInt();
                                if (input==0) input= (int) (Math.random() * 100) +1;
                                player.setRemate(input);
                                status.updatePlayer(player);
                            case 8:
                                Menu.menuEditarHabilidadeInput();
                                input = inputScanner.nextInt();
                                if (input==0) input= (int) (Math.random() * 100) +1;
                                player.setSpecial(input);
                                status.updatePlayer(player);
                            case 0:
                                break;
                        }

                    }else if (option==3){// Editar Equipa
                        Menu.menuEditarEquipa();
                        String nomeEquipa = inputScanner.nextLine() + inputScanner.nextLine();
                        Equipa e = status.equipas.get(nomeEquipa);
                        Menu.menuEditarEquipaNovo();
                        String novoNomeEquipa = inputScanner.nextLine() + inputScanner.nextLine();
                        e.setNome(novoNomeEquipa);
                        status.equipas.remove(nomeEquipa);
                        status.equipas.put(e.getNome(),e);
                        System.out.print(e);
                        pressEnterKeyToContinue();
                        break;

                    }else if (option==4) break;



                case 8:
                    Menu.clean();
                    String saveLoc;
                    if (status != null) {
                        try {
                            Menu.menuGuardar();
                            saveLoc = inputScanner.nextLine() + inputScanner.nextLine();
                            if (saveLoc.equals("")) saveLoc = "src/bin/save.obj";
                            status.save(saveLoc);
                            System.out.println("Estado Guardado em: "+saveLoc);
                            pressEnterKeyToContinue();
                            break;
                        } catch (Exception guard) {
                            System.out.println("Erro em: Guardar\n");
                            System.exit(8);
                        }
                    } else throw new NullPointerException("Não é possivel guardar objectos inexistentes");
                case 9:
                    Menu.clean();
                    String loadLoc = "";
                    while (1 > option || option > 2) {
                        Menu.menuCarregar();
                        option = inputScanner.nextInt();
                        Menu.clean();
                    }
                    if (option==2) break;
                    else {
                        Menu.menuCarregar2();
                        loadLoc = inputScanner.nextLine() + inputScanner.nextLine();
                        if (loadLoc.equals("")) loadLoc = "src/bin/save.obj";
                        status = new Estado();
                        System.out.println("\nA carregar de:" + loadLoc);
                        status = Estado.load(loadLoc);
                        System.out.println("\nEstado Carregado!\nA voltar ao menu principal\n");
                        break;
                    }
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }

    private static Map<Integer, Integer> criarSubs(int local) throws ParameterNotInScopeException {
        Map<Integer,Integer> subs = new HashMap<>();
        Scanner inputScanner = new Scanner(System.in);
        int numsubs = 1;
        int elem =1;
        int sub1, sub2;
        while (numsubs < 4) {
            Menu.menuJogoCriarSc(local,numsubs,1);
            sub1 = inputScanner.nextInt();
            if(sub1==0) return subs;
            Menu.menuJogoCriarSc(local,numsubs,2);
            sub2 = inputScanner.nextInt();
            subs.put(sub1,sub2);
            numsubs++;
        }
        return subs;
    }


        static private void pressEnterKeyToContinue () {
            System.out.println("\nPressione enter para continuar\n");
            try {
                System.in.read();
            } catch (Exception ignored) {
            }
        }

        static private List<Integer> criaLista (String linha){
            String[] valores = linha.split(",");
            return Arrays.stream(valores).map(Integer::parseInt).collect(Collectors.toList());
        }
}
