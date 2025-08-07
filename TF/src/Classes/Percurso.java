package Classes;

import java.io.Serializable;

public class Percurso implements Serializable {
    private int codigo;
    private String nome;
    private int distancia;
    private String cidade;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Percurso(int cod,String nome, int dist, String cidade){
        this.cidade=cidade;
        this.codigo=cod;
        this.nome=nome;
        this.distancia=dist;
    }

    public Percurso(){}
}
