package Classes;

import java.io.Serializable;

public enum TipoProva implements Serializable {
    CINCO_K(5),
    DEZ_K(10),
    VINTE_UM_K(21),
    MARATONA(42);

    private final int distancia;

    TipoProva(int distancia) {
        this.distancia = distancia;
    }

    public int getDistancia() {
        return distancia;
    }
}
