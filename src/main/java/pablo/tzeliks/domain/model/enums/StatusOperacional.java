package pablo.tzeliks.domain.model.enums;

public enum StatusOperacional implements DisplayEnum {

    OPERACIONAL("Operacional"),
    EM_ANDAMENTO("Em Andamento");

    private final String nome;

    StatusOperacional(String nome) {
        this.nome = nome;
    }

    @Override
    public String getDisplayNome() {
        return nome;
    }
}