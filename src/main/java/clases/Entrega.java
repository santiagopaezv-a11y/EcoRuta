package clases;

import clases.lotes.Lote;
import enums.Estados;
import interfaces.IGenerable;
import record.Comprobante;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static enums.Estados.*;

public class Entrega {
    private String id;
    private Aportante aportante;
    private List<Lote> listaLotes;
    private Estados estadoEntrega;
    private int pesoTotal;
    private Comprobante comprobante;
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

    public int getPesoTotal() {
        return pesoTotal;
    }

    public void agregarPesoTotal(int peso) {
        this.pesoTotal+=peso;
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

    public Comprobante getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    public void setListaLotes(List<Lote> listaLotes) {
        this.listaLotes = listaLotes;
    }
    public void aceptar(){
        if (!validarEstados()){
            JOptionPane.showMessageDialog(null, "No se puede aceptar una entrega que ya fue aceptada");
            return;
        }
        if (getListaLotes().isEmpty()){
            JOptionPane.showMessageDialog(null, "No se puede aceptar la entrega porque esta vacía");
        }else if (!getListaLotes().isEmpty()){
            setEstadoEntrega(ACEPTADA);
            setComprobante(calcularTotal());
            double valorAcumulado=getComprobante().valorTotal();
            getAportante().setValorAcumulado(getAportante().getValorAcumulado()+valorAcumulado);
            JOptionPane.showMessageDialog(null, "Se acepto la entrega con exito"+"\n Cantidad de lotes: "+getListaLotes().size());
        }
    }
    private boolean validarEstados(){
        if (getEstadoEntrega()==ABIERTA){
            return true;
        }else {
            return false;
        }
    }
    public void agregarLote(Lote lote){
        if (validarEstados()){
            getListaLotes().add(lote);
            agregarPesoTotal(lote.getKilogramos());
        }else {
            JOptionPane.showMessageDialog(null, "No se puede aceptar un nuevo lote mientras la entrega este "+ getEstadoEntrega());
        }
    }
    public String mostrarListaLotes() {
        if (getListaLotes().isEmpty()) {
            return "No hay lotes registrados en esta entrega.";
        }
        StringBuilder msj = new StringBuilder();
        for (Lote lote : getListaLotes()) {
            msj.append("\n• ").append(lote.toString())
                    .append("\n--------------------------------------------------\n");
        }
        return msj.toString();
    }
    public void removerLote(){
        if (!validarEstados()){
            JOptionPane.showMessageDialog(null, "No se puede remover un lote que su entrega ya fue aceptada");
            return;
        }
        String id=JOptionPane.showInputDialog("Indique el id del lote a remover");
        for (Lote lote: getListaLotes()){
            if (lote.getId().equals(id)){
                getListaLotes().remove(lote);
            }
        }
    }
    private Comprobante calcularTotal(){
        double total=0;
        for (Lote lote: getListaLotes()){
            total+=lote.valorTotalKilogramo();
        }
        Comprobante comprobante=new Comprobante(getId(),getAportante().getIdentificacion(), getPesoTotal(), total);
        Empresa.listComprobantes.add(comprobante);
        return comprobante;
    }
    public List<String> obtenerEtiquetas() {
        List<String> etiquetas = new ArrayList<>();
        for (Lote lote : getListaLotes()) {
            if (lote instanceof IGenerable generable) {
                etiquetas.add(generable.generarEtiqueta());
            }
        }
        return etiquetas;
    }
    @Override
    public String toString() {
        return String.format("Entrega [%s] | Estado: %s | Aportante ID: %s | Peso Total: %d kg",
                id,
                estadoEntrega,
                (aportante != null ? aportante.getIdentificacion() : "Sin asignar"),
                pesoTotal);
    }
}
