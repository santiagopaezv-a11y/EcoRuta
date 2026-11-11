package clases;

import clases.lotes.Lote;
import enums.Estados;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static enums.Estados.*;

public class Entrega {
    private String id;
    private Aportante aportante;
    private List<Lote> listaLotes;
    private Estados estadoEntrega;
    private static int cantidadEntregas=0;

    public Entrega(Aportante aportante) {
        this.id = ""+(++cantidadEntregas);
        this.aportante = aportante;
        this.listaLotes=new ArrayList<>();
        this.estadoEntrega=ABIERTA;
        JOptionPane.showMessageDialog(null, "Se creo exictosamente la entrega"+"\n Estado de entrega: "+getEstadoEntrega());
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

    public Estados getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(Estados estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }

    public void setListaLotes(List<Lote> listaLotes) {
        this.listaLotes = listaLotes;
    }
    private void aceptar(){
        if (getListaLotes().isEmpty()){
            JOptionPane.showMessageDialog(null, "No se puede aceptar la entrega porque esta vacía");
        }else if (!getListaLotes().isEmpty()){
            setEstadoEntrega(ACEPTADA);
            JOptionPane.showMessageDialog(null, "Se acepto la entrega con exito"+"\n Cantidad de lotes: "+getListaLotes().size());
        }
    }
    public void agregarLote(Lote lote){
        getListaLotes().add(lote);
    }
    public String mostrarListaLotes(){
        String mensaje="";
        for (Lote lote:getListaLotes()){
            mensaje+=lote.toString();
            mensaje+="\n-------------------------";
        }
        return mensaje;
    }
    public double calcularTotal(){
        double total=0;
        for (Lote lote: getListaLotes()){
            total+=lote.valorTotalKilogramo();
        }
        return total;
    }
}
