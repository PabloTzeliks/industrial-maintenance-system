package pablo.tzeliks.dao;

import pablo.tzeliks.dao.connection.Conexao;
import pablo.tzeliks.model.Maquina;
import pablo.tzeliks.model.Tecnico;
import pablo.tzeliks.view.helper.MensagemHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TecnicoDAO {

    public boolean existeTecnico(String nome, String especialidade) {

        String sql = """
                SELECT COUNT(*) FROM Tecnico WHERE nome = ? AND especialidade = ?;
                """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, especialidade);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                int count = rs.getInt(1);

                return count > 0;
            }

        } catch (SQLException e) {

            MensagemHelper.erro("Ocorreu um erro ao verificar se o Técnico é elegível ao cadastrar, observe: " + e.getMessage());
            return true;
        }

        return false;
    }

    public void save(Tecnico tecnico) {

        String sql = """
                INSERT INTO Tecnico (nome, especialidade) VALUES (?, ?);
        """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tecnico.getNome());
            stmt.setString(2, tecnico.getEspecialidade());

            stmt.executeUpdate();

        } catch (SQLException e) {

            MensagemHelper.erro("Ocorreu um erro ao inserir um técnico, observe: " + e.getMessage());
        }
    }
}