import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class visitas_visitantes {
    public static void Visitas_visitantesM(String usu) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String usuario="";
        int opcao = 0, visi_id=0, visit_id;
           do {
               System.out.printf("""
                       ====== Visitantes Visitas ====
                       1 - Listar
                       2 - Cadastrar
                       3 - Deletar
                       4 - Voltar Menu Anterior
                       """);
               opcao = sc.nextInt();

               switch (opcao){
                   case 1:
                       listar();
                       break;
                   case 2:
                       sc.nextLine();
                       System.out.print("ID Visitante: ");
                       visi_id = sc.nextInt();
                       System.out.print("ID Visita: ");
                       visit_id = sc.nextInt();
                       boolean ok = conexao_banco.inserir("INSERT INTO visitantes_visitas (visitantes_id, visitas_id) VALUES (?)", visi_id,visit_id);
                       if (ok) {
                           System.out.println("Visitantes cadastrado com sucesso!");
                       } else {
                           System.out.println("Erro ao cadastrar.");
                       }
                       break;
                   case 3:
                       listar();
                       System.out.print("Informe o ID do Visita que deseja excluir: ");
                       visit_id = sc.nextInt();
                       System.out.print("Informe o ID do Visitante que deseja excluir: ");
                       visi_id = sc.nextInt();

                       boolean del = conexao_banco.deletar("DELETE FROM visitantes_visitas WHERE visitantes_id = ? and visitas_id = ?",visit_id,visi_id);
                       if (del) {
                           System.out.println("Visitante deletado com sucesso!");
                       } else {
                           System.out.println(del);
                       }
                       break;
                   case 4:
                       menu.abrir(usuario);
                       break;
                   default:
                       System.out.println("Opção Inválida");
                       break;
               }
           }while (opcao !=4);
    }
    private static void listar() throws SQLException {
        ResultSet rs = conexao_banco.getSelect("SELECT * FROM visitantes_visitas");
        System.out.println("Id   -  visitantes_visitas ");
        while (rs.next()) {
            System.out.printf(" %d - %s \n", rs.getInt("id"), rs.getString("nome"));
        }
    }
    private static boolean verifica(String nome) {
        String sql = "SELECT id FROM visitantes_visitas WHERE nome like ?";
        return conexao_banco.existe(sql, nome);
    }
}
