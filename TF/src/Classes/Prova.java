package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Prova implements Serializable {
    private int codigo;
    private Percurso percurso;
    private Local local;
    private TipoProva tipoProva;
    private Atleta atleta;
    private int tempoEstimado;
    private int bonus;
    private SituacaoProva situacaoProva;
    private int tempo;

    public void setAtleta(Atleta atleta){
        this.atleta=atleta;
    }

    public SituacaoProva getSituacaoProva(){
        return situacaoProva;
    }

    public void setSituacaoProva(SituacaoProva situacaoProva){
        this.situacaoProva=situacaoProva;
    }

    public TipoProva getTipoProva(){
        return tipoProva;
    }

    public void setTipoProva(TipoProva tipo){this.tipoProva=tipo;
    }

    public Atleta getAtleta(){
        return this.atleta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Percurso getPercurso() {
        return percurso;
    }

        public void setPercurso(Percurso percurso) {
        this.percurso = percurso;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }


    public int getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado() {
        int dist = percurso.getDistancia();
        if (dist == 5) {
            tempoEstimado = dist / (10 + bonus);
            tipoProva = TipoProva.CINCO_K;
        } else if (dist == 10) {
            tempoEstimado = dist / (9 + bonus);
            tipoProva = TipoProva.DEZ_K;
        } else if (dist == 21) {
            tempoEstimado = dist / (8 + bonus);
            tipoProva = TipoProva.VINTE_UM_K;
        } else {
            tempoEstimado = dist / (7 + bonus);
            tipoProva = TipoProva.MARATONA;
        }
    }

    public void definirBonus(){
        //Bonus foi definido como 20% da distancia
        bonus=(percurso.getDistancia()/100*20);
    }

    public Prova(){}

    public Prova(int codigo, Percurso percurso, Local local, TipoProva tipoProva, Atleta atleta){
        definirBonus();
        setTempoEstimado();
        this.codigo=codigo;
        this.percurso=percurso;
        this.local=local;
        this.tipoProva=tipoProva;
        this.atleta=atleta;
    }

    public boolean podeSerIniciada() {
        return atleta!=null && situacaoProva == SituacaoProva.INSCRITA;
    }

    public void iniciarProva() {
        if (podeSerIniciada()) {
            this.situacaoProva = SituacaoProva.EM_ANDAMENTO;
        }
    }

    public void finalizarProva(int tempoConclusao) {
        this.tempo = tempoConclusao;
        this.situacaoProva = SituacaoProva.FINALIZADA;
        //calcularPaceMedio();
    }

    public int getTempo(){
        return this.tempo;
    }

    public void setTempo(int tempo){
        this.tempo=tempo;
    }
}
