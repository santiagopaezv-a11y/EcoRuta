package clases;

import clases.lotes.Lote;

import java.util.ArrayList;
import java.util.List;

public class Entrega {
    private String id;
    private Aportante aportante;
    private List<Lote> listaLotes;
    private static int cantidadEntregas=0;

    public Entrega(String id, Aportante aportante) {
        this.id = ""+(++cantidadEntregas);
        this.aportante = aportante;
        this.listaLotes = new ArrayList<>();
    }

    public static int getCantidadEntregas() {
        return cantidadEntregas;
    }

    public String getId() {
        return id;
    }

    public Aportante getAportante() {
        return aportante;
    }

    public void setAportante(Aportante aportante) {
        this.aportante = aportante;
    }

    public List<Lote> getListaLotes() {
        return listaLotes;
    }

    public void setListaLotes(List<Lote> listaLotes) {
        this.listaLotes = listaLotes;
    }
}
