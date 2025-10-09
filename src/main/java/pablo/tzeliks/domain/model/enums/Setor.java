package pablo.tzeliks.domain.model.enums;

public enum Setor implements DisplayEnum {

    PRODUCAO("Produção"),
    ENGENHARIA("Engenharia"),
    ADMINISTRATIVO("Administrativo"),
    LOGISTICA("Logistica");

    private String name;

    Setor(String name) {
        this.name = name;
    }

    @Override
    public String getDisplayNome() {
        return name;
    }
}