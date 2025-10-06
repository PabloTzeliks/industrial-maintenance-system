package pablo.tzeliks.domain.model.entities;

import pablo.tzeliks.domain.model.enums.StatusOperacional;

public class Maquina {

    long id;
    String name;
    String setor;
    StatusOperacional status;

    public Maquina(long id, String name, String setor, StatusOperacional status) {
        this.id = id;
        this.name = name;
        this.setor = setor;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public StatusOperacional getStatus() {
        return status;
    }

    public void setStatus(StatusOperacional status) {
        this.status = status;
    }
}