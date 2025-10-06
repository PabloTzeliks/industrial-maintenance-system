package pablo.tzeliks.domain.model.enums;

public enum StatusOrdemManutencao {

    PENDENTE("Pendente"),
    EXECUTADA("Executada"),
    CANCELADA("Cancelada");

    private final String nome;

    private StatusOrdemManutencao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}