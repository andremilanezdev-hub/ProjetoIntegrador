import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class departamentos {
    public static void DepartM(String usu) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String usuario="", nome="", senha="", login="";
        int opcao = 0, idDep=0;
           do {
               System.out.printf("""
                       ====== Departamentos ====
                       1 - Listar
                       2 - Cadastrar
                       3 - Deletar
                       4 - Alterar
                       5 - Voltar Menu Anterior
                       """);
               opcao = sc.nextInt();

               switch (opcao){
                   case 1:
                       listar();
                       break;
                   case 2:
                       sc.nextLine();
                       System.out.print("Informe o Nome: ");
                       nome = sc.nextLine();
                   if (nome.equals(" ")){
                       System.out.println("Nenhuma informação deve estar vaiza");
                   }else if (verifica(nome)) {
                       System.out.print("Departamento já cadastrado");
                   }else{
                       boolean ok = conexao_banco.inserir("INSERT INTO departamentos (nome) VALUES (?)", nome);
                       if (ok) {
                           System.out.println("Departamento cadastrado com sucesso!");
                       } else {
                           System.out.println("Erro ao cadastrar.");
                       }
                   }
                       break;
                   case 3:
                       listar();
                       System.out.print("Informe o ID do Departamento que deseja excluir: ");
                       idDep = sc.nextInt();

                       boolean del = conexao_banco.deletar("DELETE FROM departamentos WHERE id = ?",idDep);
                       if (del) {
                           System.out.println("Usuário deletado com sucesso!");
                       } else {
                           System.out.println(del);
                       }
                       break;
                   case 4:
                       idDep = 0;
                       sc.nextLine();
                       listar();

                       System.out.print("Informe o ID do Departamento que irá alterar: ");
                       idDep = sc.nextInt();
                       sc.nextLine();
                       System.out.print("Informe o novo NOME: ");
                       nome = sc.nextLine();

                       boolean alt = conexao_banco.deletar("UPDATE departamentos SET nome = ? WHERE id = ?;",nome, idDep);
                       if (alt) {
                           System.out.println("Nome do Departamento alterado com sucesso!");
                       } else {
                           System.out.println(alt);
                       }
                       break;
                   case 5:
                       menu.abrir(usuario);
                       break;
                   default:
                       System.out.println("Opção Inválida");
                       break;
               }
           }while (opcao !=5);
    }
    private static void listar() throws SQLException {
        ResultSet rs = conexao_banco.getSelect("SELECT * FROM departamentos");
        System.out.println("Id   -  Departamento ");
        while (rs.next()) {
            System.out.printf(" %d - %s \n", rs.getInt("id"), rs.getString("nome"));
        }
    }
    private static boolean verifica(String nome) {
        String sql = "SELECT id FROM departamentos WHERE nome like ?";
        return conexao_banco.existe(sql, nome);
    }
}
