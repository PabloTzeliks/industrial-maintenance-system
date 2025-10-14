package pablo.tzeliks.dao;

import pablo.tzeliks.dao.connection.Conexao;
import pablo.tzeliks.model.Maquina;
import pablo.tzeliks.view.helper.MensagemHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaquinaDAO {

    public boolean existeMaquina(String nome, String setor) {

        String sql = """
                SELECT COUNT(*) FROM Maquina WHERE nome = ? AND setor = ?;
                """;

        try (Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, setor);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                int count = rs.getInt(1);

                return count > 0;
            }

        } catch (SQLException e) {

            MensagemHelper.erro("Ocorreu um erro ao verificar se a Máquina é elegível ao cadastrar, observe: " + e.getMessage());
            return true;
        }

        return false;
    }

    public void save(Maquina maquina) {

        String sql = """
                INSERT INTO Maquina (nome, setor, status) VALUES (?, ?, ?);
        """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maquina.getNome());
            stmt.setString(2, maquina.getSetor());
            stmt.setString(3, maquina.getStatus().name());

            stmt.executeUpdate();

        } catch (SQLException e) {

            MensagemHelper.erro("Ocorreu um erro ao inserir uma máquina, observe: " + e.getMessage());
        }
    }
}