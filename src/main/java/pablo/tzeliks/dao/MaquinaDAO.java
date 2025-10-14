package pablo.tzeliks.dao;

import pablo.tzeliks.dao.connection.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaquinaDAO {

    public boolean existeMaquina(String setor, String nome) {

        String sql = """
                SELECT COUNT(*) Maquina WHERE setor = ? AND nome = ?;
                """;

        try (Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, setor);
            stmt.setString(2, nome);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                int count = rs.getInt(1);

                return count > 0;
            }

        } catch (SQLException e) {

            System.err.println("Ocorreu um erro ao verificar se a Máquina é elegível ao cadastrar, observe: " + e.getMessage());
            return false;
        }

        return false;
    }
}