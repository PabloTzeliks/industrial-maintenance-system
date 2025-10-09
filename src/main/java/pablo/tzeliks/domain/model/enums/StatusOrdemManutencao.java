package pablo.tzeliks.domain.model.enums;

public enum StatusOrdemManutencao implements DisplayEnum {

    PENDENTE("Pendente"),
    EXECUTADA("Executada"),
    CANCELADA("Cancelada");

    private final String nome;

    private StatusOrdemManutencao(String nome) {
        this.nome = nome;
    }

    @Override
    public String getDisplayNome() {
        return nome;
    }
}