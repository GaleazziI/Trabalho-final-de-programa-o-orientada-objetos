package Classes;

import java.io.Serializable;

public class Local implements Serializable {
    private int codigo;
    private String cidade;
    private String nome;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Local(int codigo, String nome, String cidade){
        this.codigo=codigo;
        this.nome=nome;
        this.cidade=cidade;
    }

    public Local(){}
}
