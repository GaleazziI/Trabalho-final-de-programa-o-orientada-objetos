package Classes;

abstract class Pessoa {
    private int telefone;
    private String nome;

    public int getTelefone(){
        return this.telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
