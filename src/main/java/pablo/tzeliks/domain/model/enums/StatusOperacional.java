package pablo.tzeliks.domain.model.enums;

public enum StatusOperacional {

    OPERACIONAL("Operacional"),
    EM_ANDAMENTO("Em Andamento");

    private final String nome;

    StatusOperacional(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}