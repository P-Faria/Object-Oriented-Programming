/**
 * Classe que gere todos os menus do Projeto
 */
public class Menu {

    public static void menuPrincipal(boolean loaded) {
        StringBuilder sb = new StringBuilder("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n\t\tFootball Manager\n");
                sb.append("\n  Universidade do Minho Edition\nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv\n");
                sb.append("1 <> Carregar ficheiros\n2 <> Criação de Equipas e Jogadores\n");
                if (!loaded) sb.append("3 <> Consultar Dados\n4 <> Calcular Resultados\n5 <> Editar Dados\n8 <> Gravar\n");
                sb.append("9 <> Carregar\n\n0 <> Sair.\n");
                System.out.print(sb);
    }

    public static void menuCarregamento(){
        String menu =   "\n\n1 <> Carregar ficheiro predefenido(src/bin/logs.txt)\n" +
                        "2 <> Carregar ficheiro personalizado\n3 <> Voltar ao Menu Principal\n";
        System.out.print(menu);
    }
    public static void menuCreation() {
        String menu = "\n1 <> Criar Equipa\n2 <> Criar Jogador\n3 <> Voltar ao Menu Principal\n";
        System.out.print(menu);
    }

    public static void menuCreationEquipa() {
        String menu = "\nInsira o nome da equipa:\n";
        System.out.print(menu);
    }

    public static void menuCreationJogador() {
        String menu = "\nInsira o nome do Jogador:\n";
        System.out.print(menu);
    }

    public static void clean() {

        System.out.println(System.lineSeparator().repeat(50));
    }

    public static void menuCreationJogadorPos() {
        String menu = "\nInsira a Posição do Jogador\n1 <> Guarda Redes\n2 <> Defesa\n3 <> Lateral\n4 <> Medio\n5 <> Avancado\n";
        System.out.print(menu);
    }

    public static void menuCreationJogadorNumJog() {
        String menu = "\nInsira o numero do Jogador:\n";
        System.out.print(menu);
    }

    public static void menuCreationJogadorStats() {
        String menu= "\nDeseja inserir valores de habilidade? (Valores de base serão usados)\n1 <> Sim\n2 <> Não\n";
        System.out.print(menu);
    }

    public static void menuCreationJogadorStatsCustom() {
        String menu= "\nPor Favor Insira 8 valores entre 1 e 99, 1 a 1 pressionando enter após cada valor\nvelocidade,"
        +   "resistencia, destreza, impulsao, cabeca, remate, passe, habilidade especifica da posição\n";
        System.out.print(menu);
    }

    public static void menuCreationJogadorEquipa() {
        String menu= "\nDeseja Inserir o Jogador numa equipa?\n1 <> Sim\n2 <> Não\n";
        System.out.print(menu);
    }

    public static void menuCreationJogadorEquipaSim() {
        String menu= "\nIntroduza o nome da Equipa:\n";
        System.out.print(menu);
    }

    public static void menuConsulta() {
        String menu= "\n1 <> Consultar Equipas\n2 <> Consultar Jogadores\n3 <> Consultar Jogo\n4 <> Voltar ao Menu Inicial\n";
        System.out.print(menu);

    }

    public static void menuGuardar() {
        String menu= "\nPor Favor introduza a a localização e nome.\nDeixe em branco para guardar em localização default\n";
        System.out.print(menu);
    }

    public static void menuCarregar2(){
        String menu= "\nPor Favor introduza a localização e nome ficheiro\nDeixe em branco para carregar de localização default\n";
        System.out.print(menu);
    }

    public static void menuConsultaEquipas() {
        String menu= "\nPor Favor introduza o nome da Equipa que deseja consultar, deixe em branco para ver todas\n";
        System.out.print(menu);
    }

    public static void menuConsultaJogador() {
        String menu= "\nPor Favor introduza o nome do Jogador que deseja consultar deixe em branco para ver todos\n";
        System.out.print(menu);
    }

    public static void menuJogo() {
        String menu= "\n\tMenu Jogo\n1 <> Jogar 2 equipas\n2 <> Rejogar um Jogo já existente\n3 <> Voltar ao Menu Principal\n";
        System.out.print(menu);
    }

    public static void menuJogoCriarJc() {
        String menu= "\nPor Favor introduza os numeros de jogadores da Equipa da Casa, separados por virgulas:\n";
        System.out.print(menu);
    }
    public static void menuJogoCriar() {
        String menu= "\nPor Favor introduza o nome da Equipa que joga em Casa:\n";
        System.out.print(menu);
    }

    public static void menuJogoCriarFora() {
        String menu= "\nPor Favor introduza o nome da Equipa que é Visitante:\n";
        System.out.print(menu);
    }

    public static void menuJogoCriarData() {
        String menu= "\nIntroduza uma data do formato 2021.06.21:\n";
        System.out.print(menu);
    }

    public static void menuJogoCriarJf() {
        String menu= "\nPor Favor introduza os numeros de jogadores da Equipa Visitante, separados por virgulas:\n";
        System.out.print(menu);
    }

    public static void menuJogoCriarSc(int local,int subnum,int elemnum) throws ParameterNotInScopeException {
        String subString = (subnum==1 ? "primeira" : subnum==2 ? "segunda" : subnum==3 ? "terceira" : null);
        String elem = (elemnum==1 ? "primeiro" : elemnum==2 ? "segundo" : null);
        String casa = (local==1 ? "Equipa da Casa":local==2 ?"Equipa Visitante" : null);
        if (subString==null) throw new ParameterNotInScopeException("numero de sub errado");
        if (elem==null) throw new ParameterNotInScopeException("numero de elemento");
        if (casa == null) throw new ParameterNotInScopeException("Local Errado");
        String menu= "\nPor Favor introduza o "+elem+" elemento da "+subString+" substituição da "+casa+" a fazer\nInsira 0 caso pretenda fazer menos de 3 subs\n";
        System.out.print(menu);
    }


    public static void menuJogoReplay() {
        String menu= "\nPor Favor introduza o numero do jogo que deseja refazer\n";
        System.out.print(menu);
    }

    public static void menuCarregar() {
        String menu ="\n1 <> Carregar ficheiro\n2 <> Voltar ao Menu Principal\n";
        System.out.print(menu);
    }

    public static void menuEditar() {
        String menu = "\n1 <> Transferir Jogador\n2 <> Modificar Valores Jogador\n3 <> Editar Equipa\n4 <> Voltar ao Menu Principal\n";
        System.out.print(menu);
    }

    public static void menuEditarTransfer() {
        String menu= "\nPor Favor introduza o nome do Jogador que deseja Transferir:\n";
        System.out.print(menu);
    }

    public static void menuEditarTransferTo(String currentT) {
        String menu= "\nJogador atualmente na equipa "+currentT+"\nPor Favor indique qual a equipa de Destino:\n";
        System.out.print(menu);
    }

    public static void menuEditarHabilidade() {
        String menu= "\nPor Favor introduza o nome do Jogador que deseja modificar:\n";
        System.out.print(menu);
    }

    public static void menuEditarHabilidade2(String s) {
            String menu= "\nPor Favor insira o valor que pretende modificar\n"
        +   "1 <> Velocidade\n2 <> Resistencia\n3 <> Destreza\n4 <> Impulsao\n5 <> Cabeca\n"
        +   "6 <> Remate\n7 <> Passe\n8 <> "+s+"\n";
            System.out.print(menu);
        }

    public static void menuEditarHabilidadeInput() {
        String menu= "\nPor Favor introduza um valor entre 1 e 100, ou 0 caso queira selecionar aleatoriamente\n";
        System.out.print(menu);
    }

    public static void menuEditarEquipa() {
        String menu= "\nPor Favor introduza a Equipa que pretende modificar:\n";
        System.out.print(menu);
    }

    public static void menuEditarEquipaNovo() {
        String menu= "\nPor Favor introduza o novo nome para a equipa:\n";
        System.out.print(menu);
    }

    public static void menuConsultaJogo() {
        String menu= "\nPor Favor introduza o numero do Jogo que deseja verificar\n";
        System.out.print(menu);
    }


    public static void menuCake() {
        String menu = "\n" +"\"\"\"            ,:/+/-\n" +
                "            /M/              .,-=;//;-\n" +
                "       .:/= ;MH/,    ,=/+%$XH@MM#@:\n" +
                "      -$##@+$###@H@MMM#######H:.    -/H#\n" +
                " .,H@H@ X######@ -H#####@+-     -+H###@X\n" +
                "  .,@##H;      +XM##M/,     =%@###@X;-\n" +
                "X%-  :M##########$.    .:%M###@%:\n" +
                "M##H,   +H@@@$/-.  ,;$M###@%,          -\n" +
                "M####M=,,---,.-%%H####M$:          ,+@##\n" +
                "@##################@/.         :%H##@$-\n" +
                "M###############H,         ;HM##M$=\n" +
                "#################.    .=$M##M$=\n" +
                "################H..;XM##M$=          .:+\n" +
                "M###################@%=           =+@MH%\n" +
                "@################M/.          =+H#X%=\n" +
                "=+M##############M,       -/X#X+;.\n" +
                "  .;XM##########H=    ,/X#H+:,\n" +
                "     .=+HM######M+/+HM@+=.\n" +
                "         ,:/%XM####H/.\n" +
                "              ,.:=-.                    \"\"\""
                +"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n Feito por:\n\n" +
                "\n" +
                "    João Goulart - a82643\n" +
                "\n" +
                "    Pedro Faria - a72640\n" +
                "\n" +
                "    Tiago Rodrigues - a87952\n";
        System.out.print(menu);
    }
}