import java.sql.SQLException;
import java.util.Scanner;

public class menu {
    public static void abrir(String usuarioLogado) throws SQLException {
        Scanner sc = new Scanner(System.in);

        int opcao = 0;

        do {
            System.out.printf("""
                        ****************************
                           Olá %s seja bem vindo!
                        ****************************
                        ======== MENU ========
                        1 - Usuario
                        2 - Visitante
                        3 - Departamento
                        4 - Visitas
                        5 - Sair
                        """,usuarioLogado);
            opcao = sc.nextInt();

            switch (opcao){
                case 1:
                    usuario.UserM(usuarioLogado);
                    break;
                case 2:
                    break;
                case 3:
                    departamentos.DepartM(usuarioLogado);
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
        } while (opcao !=5);
    }
}
