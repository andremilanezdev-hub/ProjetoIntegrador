import java.sql.*;
import java.util.Scanner;

public class login {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        String usuario, senha;
        int opcao = 0, tentativa=0;
           do {
               System.out.print("Usuário: ");
               usuario = sc.nextLine();
               System.out.print("Senha: ");
               senha = sc.nextLine();
               if (autenticar(usuario, senha)) {
                   System.out.println("Login efetuado com sucesso! Bem-vindo, " + usuario + ".");
                   menu.abrir(usuario);
                   sc.close();
               } else {
                   System.out.println("Usuário ou senha inválidos.");
                   tentativa++;

               }
           }while (tentativa <=3);
    }

    private static boolean autenticar(String login, String senha) {
        String sql = "SELECT id FROM usuarios WHERE login = ? AND senha = ?";

        try (Connection conexao = conexao_banco.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            try (ResultSet resposta = stmt.executeQuery()) {
                return resposta.next();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar o banco: " + e.getMessage());
            return false;
        }
    }
}
