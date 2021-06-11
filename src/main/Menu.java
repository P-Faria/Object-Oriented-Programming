import java.io.IOException;

public class Menu {
    //POSSO TER VARIAS VIEWS POR EXEMPLO UMA VIEW POR CADA MENU

    public static void menuPrincipal(boolean loaded) {
        StringBuilder sb = new StringBuilder("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n\t\tFootball Manager\n");
                sb.append("\n  Universidade do Minho Edition\nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv\n");
                sb.append("1 <> Carregar ficheiros\n2 <> Criação Equipas e Jogadores\n");
                if (!loaded) sb.append("3 <> Consultar Dados\n4 <> Calcular Resultados\n8 <> Gravar objeto ( ObjectStream)\n");
                sb.append("9 <> Carregar (de ObjectStream)\n\n0 <> Sair.\n");
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
        String menu= "\nPor Favor Insira 8 valores entre 1 e 100, 1 a 1 pressionando enter após cada valor\nvelocidade, resistencia, destreza, impulsao, cabeca, remate, passe, habilidade especifica da posição\n";
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
        String menu= "\n1 <> Consultar Equipas\n2 <> Consultar Jogadores\n";
        System.out.print(menu);

    }

    public static void menuGuardar() {
        String menu= "\nPor Favor introduza a a localização e nome.\nDeixe em branco para guardar em localização default\n";
        System.out.print(menu);
    }

    public static void menuCarregar(){
        String menu= "\nPor Favor introduza a localização e nome ficheiro\nDeixe em branco para carregar de localização default\n";
        System.out.print(menu);
    }

    public static void menuConsultaEquipas() {
        String menu= "\nPor Favor introduza o nome da Equipa que deseja consultar:\n";
        System.out.print(menu);
    }

    public static void menuConsultaJogador() {
        String menu= "\nPor Favor introduza o nome do Jogador que deseja consultar:\n";
        System.out.print(menu);
    }
}