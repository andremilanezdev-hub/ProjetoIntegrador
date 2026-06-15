import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class visitas {

    public static void VisitasM() throws SQLException {
    Scanner sc = new Scanner(System.in);
        int opcao = 0,idVisita, departamentoId, usuarioId;
        String dataEntrada, dataSaida, motivo,usuario="";
        do {
            System.out.printf("""
                    ====== VISITAS ======
                    1 - Listar
                    2 - Cadastrar
                    3 - Excluir
                    4 - Alterar
                    5 - Vinculo Visitantes
                    6 - Voltar
                    """);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    listar();
                    break;
                case 2:
                    System.out.print("Digite o Departamento: ");
                    departamentoId = sc.nextInt();
                    System.out.print("Digite o Usuário: ");
                    usuarioId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Data Entrada (AAAA-MM-DD HH:MM:SS): ");
                    dataEntrada = sc.nextLine();
                    System.out.print("Data Saída (AAAA-MM-DD HH:MM:SS): ");
                    dataSaida = sc.nextLine();
                    System.out.print("Motivo: ");
                    motivo = sc.nextLine();
                boolean cad = conexao_banco.inserir(
                    """
                    INSERT INTO visitas
                    (departamento_id, usuario_id, data_entrada, data_saida, motivo)
                    VALUES (?, ?, ?, ?, ?)
                    """,
                    departamentoId,
                    usuarioId,
                    dataEntrada,
                    dataSaida,
                    motivo
                    );
                if (cad) {
                    System.out.println("Visita cadastrada com sucesso!");
                } else {
                    System.out.println("Erro ao cadastrar visita.");
                    }
                    break;

                case 3:
                    listar();
                    System.out.print("Informe o ID da visita que deseja excluir: ");
                    idVisita = sc.nextInt();

                boolean del = conexao_banco.deletar(
                    "DELETE FROM visitas WHERE id = ?",
                    idVisita
                    );

                if (del) {
                    System.out.println("Visita excluída com sucesso!");
                } else {
                    System.out.println("Erro ao excluir visita.");
                    }

                    break;

                case 4:
                    listar();
                    System.out.print("ID da Visita: ");
                    idVisita = sc.nextInt();
                    System.out.print("Novo ID Departamento: ");
                    departamentoId = sc.nextInt();
                    System.out.print("Novo ID Usuário: ");
                    usuarioId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nova Data Entrada: ");
                    dataEntrada = sc.nextLine();
                    System.out.print("Nova Data Saída: ");
                    dataSaida = sc.nextLine();
                    System.out.print("Novo Motivo: ");
                    motivo = sc.nextLine();

                boolean alt = conexao_banco.deletar(
                    """
                    UPDATE visitas
                    SET departamento_id = ?,
                        usuario_id = ?,
                        data_entrada = ?,
                        data_saida = ?,
                        motivo = ?
                        WHERE id = ?
                    """,
                    departamentoId,
                    usuarioId,
                    dataEntrada,
                    dataSaida,
                    motivo,
                    idVisita
                    );

                if (alt) {
                    System.out.println("Visita alterada com sucesso!");
                 } else {
                    System.out.println("Erro ao alterar visita.");
                    }

                    break;

                case 5:
                    visitas_visitantes.Visitas_visitantesM(usuario);
                case 6:
                    menu.abrir(usuario);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 5);
    }

    private static void listar() throws SQLException {

        ResultSet rs = conexao_banco.getSelect("""
                SELECT
                    v.id,
                    d.nome AS departamento,
                    u.nome AS usuario,
                    v.data_entrada,
                    v.data_saida,
                    v.motivo
                FROM visitas v
                INNER JOIN departamentos d
                    ON d.id = v.departamento_id
                INNER JOIN usuarios u
                    ON u.id = v.usuario_id
                ORDER BY v.id
                """);

        System.out.println("\n===== LISTA DE VISITAS =====");

        while (rs.next()) {

            System.out.printf("""
                    ID: %d
                    Departamento: %s
                    Usuário: %s
                    Entrada: %s
                    Saída: %s
                    Motivo: %s
                    --------------------------------
                    """,
                    rs.getInt("id"),
                    rs.getString("departamento"),
                    rs.getString("usuario"),
                    rs.getString("data_entrada"),
                    rs.getString("data_saida"),
                    rs.getString("motivo")
            );
        }
    }
}