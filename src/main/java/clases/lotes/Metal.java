package clases.lotes;

import enums.Metales;
import interfaces.IGenerable;

import javax.swing.*;

import static enums.Metales.*;

public class Metal extends Lote implements IGenerable {
    private Metales tipoMetal;

    public Metal(String descripcion, int kilogramos, Metales tipoMetal) {
        super(descripcion, kilogramos);
        this.tipoMetal = tipoMetal;
        setValorKilogramo(0);
    }

    public Metales getTipoMetal() {
        return tipoMetal;
    }

    public void setTipoMetal(Metales tipoMetal) {
        this.tipoMetal = tipoMetal;
    }

    @Override
    public void setValorKilogramo(double valorKilogramo) {
        if (tipoMetal==ALUMINIO){
            valorKilogramo=2200;
        }else {
            valorKilogramo=900;
        }
        super.setValorKilogramo(valorKilogramo);
    }
    @Override
    public String generarEtiqueta() {
        return String.format("[ETIQUETA METAL] Código: %s | Metal: %s", getId(), getTipoMetal());
    }
    public static Metales escogerTipoMetal(){
        String tipo= JOptionPane.showInputDialog("Ingrese el tipo de metal (Aluminio/Acero)");
        Metales tipoMetal=null;
        if (tipo.toLowerCase().equals("aluminio")){
            tipoMetal= ALUMINIO;
        }else if (tipo.toLowerCase().equals("acero")){
            tipoMetal=ACERO;
        }
        return tipoMetal;
    }
    @Override
    public String toString() {
        return super.toString() + String.format(" (Metal: %s)", tipoMetal);
    }
}
