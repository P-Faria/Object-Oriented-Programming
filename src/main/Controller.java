import java.io.IOException;
import java.util.Scanner;

public class Controller {

    public static void start() throws Exception {
        Estado status = null;
        String path;
        Scanner inputScanner = new Scanner(System.in);
        //inputScanner.useDelimiter("/n");
        while (true){
            Menu.menuPrincipal(status==null);
            int option = inputScanner.nextInt();
            switch (option){
                case 1:
                    status = new Estado();
                    String nomeFich = null;
                    option=0;
                    while (1>option || option >3){
                        Menu.menuCarregamento();
                        option = inputScanner.nextInt();
                        Menu.clean();
                    }
                    if(option == 1) nomeFich ="src/bin/logs.txt";
                    else if(option == 3) {
                    System.out.println("\nRegressando ao menu Principal\n");
                    Menu.clean();
                    break;
                    } else {
                        System.out.println("\nPor Favor introduza o caminho do ficheiro\n");
                        nomeFich = inputScanner.next();
                        Menu.clean();
                    }

                    //proceder ao carregamento
                    try{
                        Parser.parse(status,nomeFich);
                    }
                    catch (Exception e){
                        System.out.println("Erro durante o Carregamento\n");
                    }

                    System.out.println("\nCarregamento efetuado\n");
                    Menu.clean();
                    break;

            }
        }
    }
}
