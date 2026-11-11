package clases.lotes;

import enums.Metales;
import interfaces.IGenerable;

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
        String mensaje= getId()+" "+getTipoMetal();
        return mensaje;
    }

    @Override
    public String toString() {
        String mensaje=super.toString();
        mensaje+="\nMetal{" +
                "tipoMetal=" + tipoMetal +
                '}';
        return mensaje;
    }
}
