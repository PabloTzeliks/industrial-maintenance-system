package pablo.tzeliks.dao;

import pablo.tzeliks.dao.connection.Conexao;
import pablo.tzeliks.model.OrdemManutencao;
import pablo.tzeliks.model.enums.StatusMaquina;
import pablo.tzeliks.view.helper.MensagemHelper;

import java.sql.*;

public class OrdemManutencaoDAO {

    public void salvar(OrdemManutencao ordemManutencao) {

        String sqlInsereOrdemManutencao = """
                INSERT INTO OrdemManutencao(idMaquina, idTecnico, dataSolicitacao, status) VALUES (?, ?, ?, ?)
                """;

        String sqlAlteraStatusMaquina = """
                UPDATE Maquina SET status = ? WHERE id = ?;
                """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sqlInsereOrdemManutencao)) {

            Timestamp dataHora = Timestamp.valueOf(ordemManutencao.getDataSolicitacao());

            stmt.setLong(1, ordemManutencao.getMaquina().getId());
            stmt.setLong(2, ordemManutencao.getTecnico().getId());
            stmt.setTimestamp(3, dataHora);
            stmt.setString(4, ordemManutencao.getStatus().name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            MensagemHelper.erro("Ocorreu um erro ao salvar a Ordem de Manutenção, observe: " + e.getMessage());
        }

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sqlAlteraStatusMaquina)) {

            stmt.setString(1, StatusMaquina.EM_MANUTENCAO.name());
            stmt.setLong(2, ordemManutencao.getMaquina().getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            MensagemHelper.erro("Ocorreu um erro ao alterar status da Máquina, observe: " + e.getMessage());
        }
    }
}