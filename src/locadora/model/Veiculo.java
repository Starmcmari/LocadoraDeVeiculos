package locadora.model;

public class Veiculo {

    private String placa;
    private String nome;
    private TipoVeiculo tipo;
    private boolean disponivel;

    public Veiculo(String placa, String nome, TipoVeiculo tipo) {
        this.placa = placa;
        this.nome = nome;
        this.tipo = tipo;
        this.disponivel = true;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", nome='" + nome + '\'' +
                ", tipo=" + tipo +
                ", disponivel=" + disponivel +
                '}';
    }
}