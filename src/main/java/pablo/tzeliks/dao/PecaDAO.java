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
import java.util.*;

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

    public void execucaoManutencao(OrdemManutencao ordemManutencao) {

        Map<Long, Double> estoque = new HashMap<>();

        String sqlSelectOrdemPeca = """
                SELECT idOrdem, idPeca, quantidade FROM OrdemPeca WHERE idOrdem = ?;
                """;

        String sqlUpdatePeca = """
                UPDATE Peca SET estoque = estoque - ? WHERE id = ? AND estoque >= ?;
                """;

        String sqlUpdateOrdem = """
                UPDATE OrdemManutencao SET status = 'EXECUTADA' WHERE id = ?;
                """;

        String sqlUpdateMaquina = """
                UPDATE Maquina SET status = 'OPERACIONAL' WHERE id = ?;
                """;

        Connection conn = null;

        try {
            conn = Conexao.getConexao();

            conn.setAutoCommit(false);

            // Criação da Lista de Peças usadas na Ordem de Manutenção
            try (PreparedStatement listagemStmt = conn.prepareStatement(sqlSelectOrdemPeca)) {

                listagemStmt.setLong(1, ordemManutencao.getId());
                ResultSet rs = listagemStmt.executeQuery();

                while (rs.next()) {

                    estoque.put(rs.getLong("idPeca"), rs.getDouble("quantidade"));
                }
            }

            try (PreparedStatement updatePecaStmt = conn.prepareStatement(sqlUpdatePeca)) {

                for (Map.Entry<Long, Double> item : estoque.entrySet()) {

                    long idPeca = item.getKey();
                    double quantidade = item.getValue();

                    updatePecaStmt.setDouble(1, quantidade);
                    updatePecaStmt.setLong(2, idPeca);
                    updatePecaStmt.setDouble(3, quantidade);

                    int linhasAfetadas = updatePecaStmt.executeUpdate();

                    if (linhasAfetadas == 0) {
                        throw new SQLException("Estoque insuficiente para a Peça " + idPeca + " de quantidade " + quantidade);
                    }
                }
            }

            try (PreparedStatement updateOrdemStmt = conn.prepareStatement(sqlUpdateOrdem)) {

                updateOrdemStmt.setLong(1, ordemManutencao.getId());

                updateOrdemStmt.executeUpdate();
            }

            try (PreparedStatement updateMaquinaStmt = conn.prepareStatement(sqlUpdateMaquina)) {

                updateMaquinaStmt.setLong(1, ordemManutencao.getMaquina().getId());

                updateMaquinaStmt.executeUpdate();
            }

            // Executa todas as alterações
            conn.commit();

        } catch(SQLException e) {

            MensagemHelper.erro("Ocorreu um erro durante a Verificação de Estoque das Peças com a Ordem de Manutenção, observe: " + e.getMessage());

            MensagemHelper.info("Desfazendo alterações.");

            if (conn != null) {

                try {
                    conn.rollback();

                    MensagemHelper.info("Alterações desfeitas.");
                } catch (SQLException ex) {

                    MensagemHelper.erro("Ocorreu um erro crítico ao tentar executar o Rollback." + ex.getMessage());
                }
            }

        } finally {

            if (conn != null) {

                try {
                    conn.setAutoCommit(true);
                } catch (SQLException ex) {
                    MensagemHelper.erro("Erro ao resetar autoCommit: " + ex.getMessage());
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    MensagemHelper.erro("Erro ao fechar a conexão: " + ex.getMessage());
                }
            }
        }
    }
}