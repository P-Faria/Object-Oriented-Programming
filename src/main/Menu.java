import java.awt.*;
import java.util.Formatter;
import java.util.Scanner;
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
        String menu =   "\n\n1 <> Carregar ficheiro predefenido(bin/logs.txt)\n" +
                        "2 <> Carregar ficheiro personalizado\n3 <> Voltar ao Menu Principal\n";
        System.out.print(menu);
    }

    public static void clean(){
        System.out.println("\n\n\n\n\n\n\n\n\n"); //TODO:solução feia, mudar se houver tempo
    }




}