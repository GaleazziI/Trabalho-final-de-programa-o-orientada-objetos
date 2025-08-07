package Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Data implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Queue<Prova> provas;
    private final ArrayList<Percurso> percursos;
    private final ArrayList<Atleta> atletas;
    private final ArrayList<Local> locais;
    private final ArrayList<TipoProva> tiposProva;

    public Data(Queue<Prova> provas, ArrayList<Percurso> percursos,
                ArrayList<Atleta> atletas, ArrayList<Local> locais,
                ArrayList<TipoProva> tiposProva) {

        this.provas = new LinkedList<>(provas);
        this.percursos = new ArrayList<>(percursos);
        this.atletas = new ArrayList<>(atletas);
        this.locais = new ArrayList<>(locais);
        this.tiposProva = new ArrayList<>(tiposProva);
    }

    // Getters
    public Queue<Prova> getProvas() { return new LinkedList<>(provas); }
    public ArrayList<Percurso> getPercursos() { return new ArrayList<>(percursos); }
    public ArrayList<Atleta> getAtletas() { return new ArrayList<>(atletas); }
    public ArrayList<Local> getLocais() { return new ArrayList<>(locais); }
    public ArrayList<TipoProva> getTiposProva() { return new ArrayList<>(tiposProva); }
}
