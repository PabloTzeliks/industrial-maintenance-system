package pablo.tzeliks.dao;

import pablo.tzeliks.dao.connection.Conexao;
import pablo.tzeliks.model.Maquina;
import pablo.tzeliks.model.Tecnico;
import pablo.tzeliks.view.helper.MensagemHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void salvar(Tecnico tecnico) {

        String sql = """
                INSERT INTO Tecnico (nome, especialidade) VALUES (?, ?);
                """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tecnico.getNome());
            stmt.setString(2, tecnico.getEspecialidade());

            stmt.executeUpdate();

        } catch (SQLException e) {

            MensagemHelper.erro("Ocorreu um erro ao inserir um Técnico, observe: " + e.getMessage());
        }
    }

    public List<Tecnico> listarTecnicos() {

        List<Tecnico> tecnicos = new ArrayList<>();

        String sql = """
                SELECT id, nome, especialidade FROM Tecnico;
                """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Tecnico tecnico = new Tecnico(rs.getLong(1), rs.getString(2), rs.getString(3));

                tecnicos.add(tecnico);
            }

        } catch (SQLException e) {
            MensagemHelper.erro("Ocorreu um erro ao listar os Técnicos, observe: " + e.getMessage());
        }

        return tecnicos;
    }

    public Optional<Tecnico> acharPorId(long id) {

        String sql = """
                SELECT id, nome, especialidade FROM Tecnico WHERE id = ?;
                """;
        
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            return Optional.of(new Tecnico(rs.getLong("id"), rs.getString("nome"), rs.getString("especialidade")));
            
        } catch (SQLException e) {
            MensagemHelper.erro("Ocorreu um erro ao buscar um Técnico por seu ID, observe: " + e.getMessage());
        }

        return Optional.empty();
    }
}