package pablo.tzeliks.dao;

import pablo.tzeliks.dao.connection.Conexao;
import pablo.tzeliks.model.Maquina;
import pablo.tzeliks.model.OrdemManutencao;
import pablo.tzeliks.model.Tecnico;
import pablo.tzeliks.model.enums.StatusMaquina;
import pablo.tzeliks.model.enums.StatusOrdemManutencao;
import pablo.tzeliks.view.helper.MensagemHelper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdemManutencaoDAO {

    public void salvar(OrdemManutencao ordemManutencao) {

        String sqlInsereOrdemManutencao = """
                INSERT INTO OrdemManutencao(idMaquina, idTecnico, dataSolicitacao, status) VALUES (?, ?, ?, ?)
                """;

        String sqlAlteraStatusMaquina = """
                UPDATE Maquina SET status = ? WHERE id = ?;
                """;

        try (Connection conn = Conexao.getConexao()) {

            try (PreparedStatement stmt = conn.prepareStatement(sqlInsereOrdemManutencao)) {

                Timestamp dataHora = Timestamp.valueOf(ordemManutencao.getDataSolicitacao());

                stmt.setLong(1, ordemManutencao.getMaquina().getId());
                stmt.setLong(2, ordemManutencao.getTecnico().getId());
                stmt.setTimestamp(3, dataHora);
                stmt.setString(4, ordemManutencao.getStatus().name());

                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conn.prepareStatement(sqlAlteraStatusMaquina)) {

                stmt.setString(1, StatusMaquina.EM_MANUTENCAO.name());
                stmt.setLong(2, ordemManutencao.getMaquina().getId());

                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            MensagemHelper.erro("Ocorreu um erro ao salvar a Ordem de Manutenção, observe: " + e.getMessage());
        }
    }

    // Duas alternativas :
    // 1. Usa o DAO de Máquina e Técnido com o Achar por ID;
    // 2. Faz um Join para buscar informações entre tabelas ** Mais performático. <--
    public List<OrdemManutencao> listarOrdensPorStatus(StatusOrdemManutencao status) {

        List<OrdemManutencao> ordens = new ArrayList<>();

        String sql = """
                SELECT 
                    -- Coluna da Ordem
                    om.id AS om_id,
                    om.dataSolicitacao,
                    om.status,
                    
                    -- Coluna da Máquina
                    m.id AS maquina_id,
                    m.nome AS maquina_nome,
                    m.setor AS maquina_setor,
                    m.status AS maquina_status,
                    
                    -- Coluna do Técnico
                    t.id AS tecnico_id,
                    t.nome AS tecnico_nome,
                    t.especialidade AS tecnico_especialidade
                FROM 
                    OrdemManutencao om
                JOIN 
                    Maquina m ON om.idMaquina = m.id
                JOIN
                    Tecnico t ON om.idTecnico = t.id
                WHERE
                    om.status = ?;
        """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status.name());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Maquina maquina = new Maquina(rs.getLong("maquina_id"), rs.getString("maquina_nome"), rs.getString("maquina_setor"), StatusMaquina.valueOf(rs.getString("maquina_status")));
                Tecnico tecnico = new Tecnico(rs.getLong("tecnico_id"), rs.getString("tecnico_nome"), rs.getString("tecnico_especialidade"));

                OrdemManutencao ordemManutencao = new OrdemManutencao(rs.getLong(1), maquina, tecnico, rs.getTimestamp(4).toLocalDateTime(), StatusOrdemManutencao.valueOf(rs.getString(5)));

                ordens.add(ordemManutencao);
            }

        } catch (SQLException e) {

            MensagemHelper.erro("Ocorreu um erro ao listar Ordens por Status, observe: " + e.getMessage());
        }

        return ordens;
    }
}