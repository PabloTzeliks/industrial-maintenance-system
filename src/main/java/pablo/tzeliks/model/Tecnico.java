package pablo.tzeliks.model;

public class Tecnico {

    private long id;
    private String nome;
    private String especialidade;

    public Tecnico(long id, String nome, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "Nome: " + nome +
                "Especialidade=" + especialidade;
    }
}