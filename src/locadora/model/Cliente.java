package locadora.model;

import java.time.LocalDate;

public class Cliente extends ClasseId {

    protected String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPessoaJuridica() {
        return false;
    }
}
