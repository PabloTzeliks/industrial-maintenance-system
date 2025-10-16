package pablo.tzeliks.dao;

import pablo.tzeliks.dao.connection.Conexao;
import pablo.tzeliks.model.Maquina;
import pablo.tzeliks.model.enums.StatusMaquina;
import pablo.tzeliks.view.helper.MensagemHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void salvar(Maquina maquina) {

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

    public List<Maquina> listarMaquinasPorStatus(StatusMaquina status) {

        List<Maquina> maquinas = new ArrayList<>();

        String sql = """
                SELECT id, nome, setor, status FROM Maquina
        """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                long id = rs.getLong(1);
                String nome = rs.getString(2);
                String setor = rs.getString(3);
                StatusMaquina statusMaquina = StatusMaquina.valueOf(rs.getString(4));

                if (statusMaquina.equals(status)) {

                    Maquina maquina = new Maquina(id, nome, setor, statusMaquina);
                    maquinas.add(maquina);
                }
            }

        } catch (SQLException e) {

            MensagemHelper.erro("Ocorreu um erro ao buscar Máquina por Status, observe: " + e.getMessage());
        }

        return maquinas;
    }

    public Optional<Maquina> buscarPorId(long id) {

        String sql = """
                SELECT id, nome, setor, status FROM Maquina WHERE id = ?;
                """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                long idMaquina = id;
                String nome = rs.getString(2);
                String setor = rs.getString(3);
                StatusMaquina statusMaquina = StatusMaquina.valueOf(rs.getString(4));

                return Optional.of(new Maquina(id, nome, setor, statusMaquina));
            }

        } catch (SQLException e) {
            MensagemHelper.erro("Ocorreu um erro ao buscar Máquina por ID, observe:" + e.getMessage());
        }

        return Optional.empty();
    }
}