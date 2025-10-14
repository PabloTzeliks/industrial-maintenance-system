package pablo.tzeliks.model;

import pablo.tzeliks.model.enums.StatusMaquina;

public class Maquina {

    private long id;
    private String nome;
    private String setor;
    private StatusMaquina status;

    public Maquina(long id, String nome, String setor, StatusMaquina status) {
        this.id = id;
        this.nome = nome;
        this.setor = setor;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public StatusMaquina getStatus() {
        return status;
    }

    public void setStatus(StatusMaquina status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "\nNome: " + nome +
                "\nSetor: " + setor +
                "\nStatus: " + status;
    }
}