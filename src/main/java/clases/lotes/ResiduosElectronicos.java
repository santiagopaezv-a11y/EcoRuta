package clases.lotes;

import interfaces.IGenerable;

import static enums.Metales.ALUMINIO;

public class ResiduosElectronicos extends Lote implements IGenerable {
    private int cantidadEquipos;

    public ResiduosElectronicos(String descripcion, int kilogramos, int cantidadEquipos) {
        super(descripcion, kilogramos);
        this.cantidadEquipos = cantidadEquipos;
        setValorKilogramo(0);
    }

    public int getCantidadEquipos() {
        return cantidadEquipos;
    }

    public void setCantidadEquipos(int cantidadEquipos) {
        this.cantidadEquipos = cantidadEquipos;
    }

    @Override
    public void setValorKilogramo(double valorKilogramo) {
        valorKilogramo = 1400;
        super.setValorKilogramo(valorKilogramo);
    }
    @Override
    public String generarEtiqueta() {
        String mensaje= getId()+" "+getCantidadEquipos();
        return mensaje;
    }
}
