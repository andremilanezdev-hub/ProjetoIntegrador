import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class visitantes {
    public static void DepartM(String usu) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String usuario="", nome="", cpf="", telefone="", empresa="";
        int opcao = 0, idVis=0;
           do {
               System.out.printf("""
                       ====== Visitantes ====
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
                       System.out.print("Informe o CPF: ");
                       cpf = sc.nextLine();
                       System.out.print("Informe o Telefone: ");
                       telefone = sc.nextLine();
                       System.out.print("Informe o Empresa: ");
                       empresa = sc.nextLine();
                   if (nome.equals(" ") || telefone.equals(" ") || cpf.equals(" ") || empresa.equals(" ")){
                       System.out.println("Nenhuma informação deve estar vaiza");
                   }else if (verifica(cpf)) {
                       System.out.print("Visitante já cadastrado");
                   }else{
                       boolean ok = conexao_banco.inserir("INSERT INTO visitantes (nome, cpf, telefone, empresa) VALUES (? ? ? ?)", nome, cpf, telefone, empresa);
                       if (ok) {
                           System.out.println("Visitante cadastrado com sucesso!");
                       } else {
                           System.out.println("Erro ao cadastrar.");
                       }
                   }
                       break;
                   case 3:
                       listar();
                       System.out.print("Informe o ID do Visitante que deseja excluir: ");
                       idVis = sc.nextInt();

                       boolean del = conexao_banco.deletar("DELETE FROM visitantes WHERE id = ?",idVis);
                       if (del) {
                           System.out.println("Visitante deletado com sucesso!");
                       } else {
                           System.out.println(del);
                       }
                       break;
                   case 4:
                       idVis = 0;
                       sc.nextLine();
                       listar();

                       System.out.print("Informe o ID do Visitante que irá alterar: ");
                       idVis = sc.nextInt();
                       sc.nextLine();
                       System.out.print("Informe o novo Nome: ");
                       nome = sc.nextLine();
                       System.out.print("Informe o novo CPF: ");
                       cpf = sc.nextLine();
                       System.out.print("Informe o novo Telefone: ");
                       telefone = sc.nextLine();
                       System.out.print("Informe o novo Empresa: ");
                       empresa = sc.nextLine();

                       boolean alt = conexao_banco.deletar("UPDATE visitantes SET nome = ? , cpf = ?, telefone = ?, empresa = ?  WHERE id = ?;",nome, cpf, telefone, empresa, idVis);
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
        ResultSet rs = conexao_banco.getSelect("SELECT * FROM visitantes");
        System.out.println("Id   -  Departamento ");
        while (rs.next()) {
            System.out.printf(" %d - %s \n", rs.getInt("id"), rs.getString("nome"));
        }
    }
    private static boolean verifica(String cpf) {
        String sql = "SELECT id FROM visitantes WHERE nome = ?";
        return conexao_banco.existe(sql, cpf);
    }
}
