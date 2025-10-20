package pablo.tzeliks.dao;

import pablo.tzeliks.dao.connection.Conexao;
import pablo.tzeliks.model.Maquina;
import pablo.tzeliks.model.OrdemManutencao;
import pablo.tzeliks.model.Peca;
import pablo.tzeliks.model.Tecnico;
import pablo.tzeliks.view.helper.MensagemHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Peca> listarPecas() {

        List<Peca> pecas = new ArrayList<>();

        String sql = """
                SELECT id, nome, estoque FROM Peca;
                """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {

                Peca novaPeca = new Peca(rs.getLong(1), rs.getString(2), rs.getDouble(3));

                pecas.add(novaPeca);
            }

        } catch(SQLException e) {

            MensagemHelper.erro("Ocorreu um erro ao listar as Peças do Sistema, observe: " + e.getMessage());
        }

        return pecas;
    }

    public Optional<Peca> buscarPecaPorId(long id) {

        String sql = """
                SELECT id, nome, estoque FROM Peca WHERE id = ?;
                """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Peca(rs.getLong("id"), rs.getString("nome"), rs.getDouble("estoque")));
            }

        } catch (SQLException e) {
            MensagemHelper.erro("Ocorreu um erro ao buscar uma Peça por seu ID, observe: " + e.getMessage());
        }

        return Optional.empty();
    }

    public void associarPeca(OrdemManutencao ordemManutencao, Peca peca, double quantidade) {

        String sql = """
                INSERT INTO OrdemPeca (idOrdem, idPeca, quantidade) VALUES (?, ?, ?);
                """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, ordemManutencao.getId());
            stmt.setLong(2, peca.getId());
            stmt.setDouble(3, quantidade);

            stmt.executeUpdate();

        } catch (SQLException e) {

            MensagemHelper.erro("Ocorreu um erro ao salvar uma associação de Peça com Ordem de Manutenção, observe:  " + e.getMessage());
        }
    }
}