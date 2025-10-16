package pablo.tzeliks.model;

import pablo.tzeliks.model.enums.StatusOrdemManutencao;

import java.time.LocalDateTime;

public class OrdemManutencao {

    private long id;
    private Maquina maquina;
    private Tecnico tecnico;
    private LocalDateTime dataSolicitacao;
    private StatusOrdemManutencao status;

    public OrdemManutencao(long id, Maquina maquina, Tecnico tecnico, LocalDateTime dataSolicitacao, StatusOrdemManutencao status) {
        this.id = id;
        this.maquina = maquina;
        this.tecnico = tecnico;
        this.dataSolicitacao = dataSolicitacao;
        this.status = status;
    }

    public OrdemManutencao(long id, Maquina maquina, Tecnico tecnico, StatusOrdemManutencao status) {
        this.id = id;
        this.maquina = maquina;
        this.tecnico = tecnico;
        this.dataSolicitacao = LocalDateTime.now();
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
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

    @Override
    public String toString() {
        return "OrdemManutencao{" +
                "id=" + id +
                ", maquina=" + maquina +
                ", tecnico=" + tecnico +
                ", dataSolicitacao=" + dataSolicitacao +
                ", status=" + status +
                '}';
    }
}