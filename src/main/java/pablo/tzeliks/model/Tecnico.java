package pablo.tzeliks.model;

public class Tecnico {

    private long id;
    private String nome;
    private double estoque;

    public Tecnico(long id, String nome, double estoque) {
        this.id = id;
        this.nome = nome;
        this.estoque = estoque;
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

    public double getEstoque() {
        return estoque;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", estoque=" + estoque +
                '}';
    }
}