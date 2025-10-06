package pablo.tzeliks.domain.model.entities;

import pablo.tzeliks.domain.model.enums.StatusOrdemManutencao;

import java.time.LocalDateTime;

public class OrdemManutencao {

    private long id;
    LocalDateTime dataSolicitacao;
    StatusOrdemManutencao status;

    public OrdemManutencao(long id, LocalDateTime dataSolicitacao, StatusOrdemManutencao status) {
        this.id = id;
        this.dataSolicitacao = LocalDateTime.now();
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public StatusOrdemManutencao getStatus() {
        return status;
    }

    public void setStatus(StatusOrdemManutencao status) {
        this.status = status;
    }
}