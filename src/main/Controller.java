import java.io.IOException;
import java.security.InvalidKeyException;
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
                    Menu.clean();
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
                        nomeFich = inputScanner.nextLine()+inputScanner.nextLine();
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
                case 2:
                    Menu.clean();
                    option=0;
                    while (1>option || option >3){
                        Menu.menuCreation();
                        option = inputScanner.nextInt();
                        Menu.clean();
                    }
                    if (option==1) {
                        Menu.menuCreationEquipa();
                        //inputScanner.nextLine();
                        String team = inputScanner.nextLine()+inputScanner.nextLine();
                        Equipa nova = new Equipa(team);
                        if (status == null) {
                            System.out.println("Estado por inicializar\n");
                            break;
                        }
                        else status.equipas.put(team, nova);
                        break;
                    }else if (option==2) {
                        Menu.menuCreationJogador();
                        String jNome = inputScanner.nextLine() + inputScanner.nextLine();
                        Menu.menuCreationJogadorPos();
                        int pos = inputScanner.nextInt();
                        Menu.menuCreationJogadorNumJog();
                        int jNum = inputScanner.nextInt();
                        option = 0;
                        Menu.menuCreationJogadorStats();
                        int vel=0,res=0, des=0, imp=0, cab=0, rem=0, pas=0, spe=0;
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
                        String nomeEquipa;
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
                                throw new IllegalStateException("Unexpected value: " + pos);
                        }
                        int idMax = status.jogadores.keySet().stream().max(Integer::compareTo).orElseThrow(InvalidKeyException::new);
                        status.jogadores.put(idMax + 1, jog);
                        if (nomeEquipa != null && status.equipas.containsKey(nomeEquipa)) status.equipas.get(nomeEquipa).insereJogador(jog);
                        else {
                            System.out.println("Nome de Equipa Inexistente");
                            break;

                        }
                    }
                case 3:
                    Menu.clean();
                    option=0;
                    while (1>option || option >2){
                        Menu.menuConsulta();
                        option = inputScanner.nextInt();
                        Menu.clean();
                    }
                        //TODO: ACABAR OS MENUS

                case 4:
                case 8:
                    if (status!=null){
                        try{
                            Menu.menuGuardar();
                            String saveLoc = inputScanner.nextLine()+inputScanner.nextLine();
                            status.save(saveLoc);
                            break;
                        } catch (Exception guard){
                            System.out.println("Erro em: Guardar\n");
                            System.exit(8);
                        }
                    }else throw new NullPointerException("Não é possivel guardar objectos inexistentes");
                case 9:
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }
}
