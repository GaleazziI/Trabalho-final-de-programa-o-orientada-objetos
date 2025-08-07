package Classes;

public enum SituacaoProva {
    INSCRITA("Inscrito para a prova"),
    EM_ANDAMENTO("Classes.Prova em andamento"),
    FINALIZADA("Classes.Prova finalizada"),
    CANCELADA("Classes.Prova cancelada");

    private final String descricao;

    SituacaoProva(String descricao){
        this.descricao=descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
