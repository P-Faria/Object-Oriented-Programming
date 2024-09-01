import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe que controla  o funcionamento do projeto.
 */
public class Controller {

    /**
     * Metodo que inicia o Projeto
     * @throws Exception varias
     */
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
                        System.out.println("Erro durante o Carregamento do ficheiro\n");
                        pressEnterKeyToContinue();
                        break;
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
                                try {
                                    if ((vel > 0 && vel < 100) && (res > 0 && res < 100) && (des > 0 && des < 100) &&
                                            (imp > 0 && imp < 100) && (cab > 0 && cab < 100) && (rem > 0 && rem < 100) &&
                                            (pas > 0 && pas < 100) && (spe > 0 && spe < 100)) check = 1;
                                    else {
                                        throw new ParameterNotInScopeException("Valor não está entre 1 e 99\n");

                                    }
                                }catch(ParameterNotInScopeException pns){
                                    System.out.print(pns);
                                    check =0;
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
                        String nomeEquipa="";
                        if (!(status.equipas.isEmpty())) {
                            Menu.menuCreationJogadorEquipa();
                            option = inputScanner.nextInt();
                            if (option == 1) {
                                Menu.menuCreationJogadorEquipaSim();
                                nomeEquipa = inputScanner.nextLine() + inputScanner.nextLine();
                            } else nomeEquipa = "Se te quiseres preocupar, preocupa-te";
                        }
                        Jogador jog;
                        switch (pos) {
                            case 1:
                                jog = new GuardaRedes(jNome, jNum, vel, res, des, imp, cab, rem, pas, spe);
                                break;
                            case 2:
                                jog = new Defesa(jNome, jNum, vel, res, des, imp, cab, rem, pas, spe);
                                break;
                            case 3:
                                jog = new Lateral(jNome, jNum, vel, res, des, imp, cab, rem, pas, spe);
                                break;
                            case 4:
                                jog = new Medio(jNome, jNum, vel, res, des, imp, cab, rem, pas, spe);
                                break;
                            case 5:
                                jog = new Avancado(jNome, jNum, vel, res, des, imp, cab, rem, pas, spe);
                                break;
                            default:
                                throw new ParameterNotInScopeException("Unexpected value for POS: " + pos);
                        }
                        /*
                        Tambem funcionaria um sstatus.jogadores.values().size(), mas esta solução permite-nos usar
                        o orElse, para inicializar idMax caso nao exista nenhuma entrada o que torna mais consistente
                        o uso do idMax (se for iniciado a 0 teria de se usar um if para garantir que num map vazio
                        0 seria sempre a 1a entrada)
                         */
                        int idMax = status.jogadores.keySet().stream().max(Integer::compareTo).orElse(-1);


                        status.jogadores.put(idMax + 1, jog);
                        if (nomeEquipa.equals("Se te quiseres preocupar, preocupa-te") || nomeEquipa.equals("")) {
                            System.out.println("Jogador Criado");
                            break;
                        }
                        else{
                            try {
                            if (status.equipas.containsKey(nomeEquipa))
                                status.equipas.get(nomeEquipa).insereJogador(jog);
                            else {
                                throw new NotFoundException("Equipa não encontrada");
                            }
                            }catch (NotFoundException nfE){
                                System.out.print(nfE);
                                System.out.print("\nJogador Criado <> mas não foi inserido em Equipa\n");
                                pressEnterKeyToContinue();
                                break;
                            }
                        }
                    }else break;
                case 3:// Consultar dados
                    try{ if (status== null)throw new NotFoundException("Se estas a ler isto é porque nao inicializaste a base de dados!\n");
                    }catch (NotFoundException nfE){
                        System.out.print(nfE);
                        pressEnterKeyToContinue();
                        break;
                    }
                    Menu.clean();
                    option = 0;
                    while (1 > option || option > 4) {
                        Menu.menuConsulta();
                        option = inputScanner.nextInt();
                        Menu.clean();
                    }
                    if (option == 1) {// consultar equipas
                        try{
                            if (status.equipas.isEmpty()) throw new EmptyParameterException("Crie equipas primeiro");
                        }catch (EmptyParameterException emE){
                            System.out.print(emE);
                            pressEnterKeyToContinue();
                            break;
                        }
                        Menu.menuConsultaEquipas();
                        String consultEq = inputScanner.nextLine() + inputScanner.nextLine();
                        if (consultEq.equals("")) {
                            System.out.print(status.equipas.toString());
                            pressEnterKeyToContinue();
                            break;

                        }else if ((!status.equipas.containsKey(consultEq))){
                            throw new NotFoundException("Equipa inexistente");
                        }else{
                            Equipa team = status.equipas.get(consultEq);
                            System.out.print("\n" + team.toString());
                            pressEnterKeyToContinue();
                            break;
                        }


                    } else if (option == 2) { //consultar jogadores
                        try{
                            if (status.jogadores.isEmpty()) throw new EmptyParameterException("Crie jogadores primeiro");
                        }catch (EmptyParameterException emE){
                            System.out.print(emE);
                            pressEnterKeyToContinue();
                            break;
                        }
                        Menu.menuConsultaJogador();
                        String consultJog = inputScanner.nextLine() + inputScanner.nextLine();
                        if (consultJog.equals("")) {
                            System.out.print(status.jogadores.toString());
                            pressEnterKeyToContinue();
                            break;
                        } else if (!(status.isPlayer(consultJog))) {
                            System.out.println("Nome Incorreto");
                            pressEnterKeyToContinue();
                            break;
                        } else {
                            Jogador jog;
                            try {
                                jog = status.getJogadorByName(consultJog);
                            }catch (NotFoundException nfe){
                                System.out.println(nfe);
                                pressEnterKeyToContinue();
                                break;
                            }
                            if (status.isPlayerinAnyTeam(jog)) {
                                System.out.print(jog.prettyToString() + "\n\nJogador da Equipa: "
                                        + status.getTeamNameifPlayerPresent(jog) + "\nEquipas onde Jogou:\n"
                                        + jog.getHistorico());
                            } else
                                System.out.print(jog.prettyToString());

                            pressEnterKeyToContinue();
                            break;
                        }

                    }else if (option == 3){// Consultar Jogo
                        try{
                            if (status.jogos.isEmpty()) throw new EmptyParameterException("Crie jogos primeiro");
                        }catch (EmptyParameterException emE){
                            System.out.print(emE);
                            pressEnterKeyToContinue();
                            break;
                        }
                        System.out.print(status.jogoToStringList());
                        Menu.menuConsultaJogo();
                        int jogo = inputScanner.nextInt();
                        while(jogo>status.jogos.size() || 0>jogo){
                            System.out.print("Jogo invalido!\n");
                            Menu.menuConsultaJogo();
                            jogo = inputScanner.nextInt();
                        }
                        System.out.print(status.jogos.get(jogo-1).toString());
                        pressEnterKeyToContinue();
                        break;
                    }else {
                        System.out.println("Voltando ao Menu Inical\n");
                        break;
                    }


                case 4: //Menu Calcular Resultados
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
                    if (option == 1) { // Criar Jogo via input
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
                    else if(option==2){ // calcular resultado de jogos em Base de dados
                        Menu.menuJogoReplay();
                        int nJogo = inputScanner.nextInt();
                        Jogo rePlay = status.jogos.get(nJogo);
                        rePlay =status.Jogar(rePlay);
                        status.jogos.add(nJogo,rePlay);
                        System.out.print(rePlay);
                        pressEnterKeyToContinue();
                        break;
                    }else break;

                case 5: // Editar dados
                    Menu.clean();
                    option=0;
                    while (1 > option || option > 6) {
                        Menu.menuEditar();
                        option = inputScanner.nextInt();
                        Menu.clean();
                    }
                    if (option==1){ // Transferir Jogador
                        Menu.menuEditarTransfer();
                        String nomeJog = inputScanner.nextLine() +inputScanner.nextLine();
                        Jogador player;

                        try {
                            player = status.getJogadorByName(nomeJog);
                        }catch (NotFoundException nfe){
                            System.out.println(nfe);
                            pressEnterKeyToContinue();
                            break;
                        }
                        String currentT  = status.getTeamNameifPlayerPresent(player);
                        Menu.menuEditarTransferTo(currentT);
                        String teamTo = inputScanner.nextLine() + inputScanner.nextLine();

                        try {
                            status.transferencia(player,status.equipas.get(currentT),status.equipas.get(teamTo));
                        }catch (NotFoundException nfE){
                            System.out.println(nfE);
                            pressEnterKeyToContinue();
                            break;
                        }
                        pressEnterKeyToContinue();
                        break;
                    }else if (option==2){// Modificar valores jogador
                        Menu.menuEditarHabilidade();
                        String nomeJog = inputScanner.nextLine() + inputScanner.nextLine();
                        Jogador player;
                        try { // caso não esteja numa equipa
                            player = status.getJogadorByName(nomeJog);
                            if(!status.isPlayerinAnyTeam(player))
                                throw new NotFoundException("Jogador nã esta em nenhuma equipa");
                        }catch (NotFoundException nfe){
                            System.out.println(nfe);
                            pressEnterKeyToContinue();
                            break;
                        }
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

                    }else if (option==3) {// Editar Equipa
                        Menu.menuEditarEquipa();
                        String nomeEquipa = inputScanner.nextLine() + inputScanner.nextLine();
                        Equipa e = status.equipas.get(nomeEquipa);
                        Menu.menuEditarEquipaNovo();
                        String novoNomeEquipa = inputScanner.nextLine() + inputScanner.nextLine();
                        e.setNome(novoNomeEquipa);
                        status.equipas.remove(nomeEquipa);
                        status.equipas.put(e.getNome(), e);
                        System.out.print(e);
                        pressEnterKeyToContinue();
                        break;

                    }else if (option==4){
                        Menu.menuEditarRemoverJogador();
                        String nomeJogador= inputScanner.nextLine() + inputScanner.nextLine();



                    }else if (option==5) break;

                case 7:
                    Menu.menuCake();
                    pressEnterKeyToContinue();
                    Menu.clean();
                    System.out.print("Cake is a Lie\n");
                    break;

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
                            pressEnterKeyToContinue();
                            break;
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
                        try {
                            status = Estado.load(loadLoc);
                        }catch (Exception e){
                            System.out.println("Erro a Carregar");
                        }
                        System.out.println("\nEstado Carregado!\nA voltar ao menu principal\n");
                        break;
                    }
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * Metodo que cria um Map das substituições a serem realizadas
     * @param local 1 se For equipa da casa 2 se for equipa visitante (usado apenas meramente para o display do menu)
     * @return um Map com as substituições do formato sai -> entra
     * @throws ParameterNotInScopeException caso um dos parametros não seja um valor válido
     */
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

    /**
     * Metodo para esperar pelo input antes de prosseguir o display de menus
     */
    static private void pressEnterKeyToContinue () {
            System.out.println("\nPressione enter para continuar\n");
            try {
                System.in.read();
            } catch (Exception ignored) {
            }
        }

    /**
     * Metodo que devolve uma Lista de jogadores dado uma linha com valores separados por virgulas (CSV)
     * com os numeros de Jogador
     * @param linha uma String CSV com os numeros do jogadores
     * @return uma Lista de Inteiros com os nmeros dos jogadores a entrar em campo
     */
    static private List<Integer> criaLista (String linha){
            String[] valores = linha.split(",");
            return Arrays.stream(valores).map(Integer::parseInt).collect(Collectors.toList());
        }
}
