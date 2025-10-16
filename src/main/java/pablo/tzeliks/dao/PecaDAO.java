package pablo.tzeliks.dao;

import pablo.tzeliks.dao.connection.Conexao;
import pablo.tzeliks.model.Maquina;
import pablo.tzeliks.model.Peca;
import pablo.tzeliks.view.helper.MensagemHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PecaDAO {

    public boolean existePeca(String nome) {

        String sql = """
                SELECT COUNT(*) FROM Peca WHERE nome = ?;
                """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                int count = rs.getInt(1);

                return count > 0;
            }

        } catch (SQLException e) {

            MensagemHelper.erro("Ocorreu um erro ao verificar se a Peça é elegível ao cadastrar, observe: " + e.getMessage());
            return true;
        }

        return false;
    }

    public void salvar(Peca peca) {

        String sql = """
                INSERT INTO Peca (nome, estoque) VALUES (?, ?);
        """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, peca.getNome());
            stmt.setDouble(2, peca.getEstoque());

            stmt.executeUpdate();

        } catch (SQLException e) {

            MensagemHelper.erro("Ocorreu um erro ao inserir uma peça, observe: " + e.getMessage());
        }
    }
}