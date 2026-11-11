package clases.lotes;

public class PapelCarton extends Lote {
    private boolean clasificado;

    public PapelCarton(String descripcion, int kilogramos, boolean clasificado) {
        super(descripcion, kilogramos);
        this.clasificado = clasificado;
        setValorKilogramo(0);
    }

    public boolean isClasificado() {
        return clasificado;
    }

    public void setClasificado(boolean clasificado) {
        this.clasificado = clasificado;
    }

    @Override
    public void setValorKilogramo(double valorKilogramo) {
        if (isClasificado()){
            valorKilogramo=600;
        }else {
            valorKilogramo=500;
        }
        super.setValorKilogramo(valorKilogramo);
    }

    @Override
    public String toString() {
        String mensaje=super.toString();
        mensaje+="\n PapelCarton{" +
                "clasificado=" + clasificado +
                '}';
        return mensaje;
    }
}
