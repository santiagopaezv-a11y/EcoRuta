package clases.lotes;

public abstract class Lote {
    private String id;
    private String descripcion;
    private int kilogramos;
    private double valorKilogramo;
    private static int cantidadLote=0;

    public Lote(String descripcion, int kilogramos) {
        this.id=""+(++cantidadLote);
        this.descripcion = descripcion;
        this.kilogramos = kilogramos;
    }

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getKilogramos() {
        return kilogramos;
    }

    public void setKilogramos(int kilogramacios) {
        this.kilogramos = kilogramacios;
    }

    public static int getCantidadLote() {
        return cantidadLote;
    }

    public double getValorKilogramo() {
        return valorKilogramo;
    }

    protected void setValorKilogramo(double valorKilogramo) {
        this.valorKilogramo = valorKilogramo;
    }
    public double valorTotalKilogramo(){
        double total= getKilogramos()*getValorKilogramo();
        return total;
    }
    @Override
    public String toString() {
        return String.format("  • [%s] %s | %d kg | Valor: $%.2f",
                id, descripcion, kilogramos, valorTotalKilogramo());
    }
}
