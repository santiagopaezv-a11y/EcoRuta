package record;

public record Comprobante(String idEntrega, String idAportante, int pesoTotal, double valorTotal) {

    public String idEntrega() {
        return idEntrega;
    }

    public String idAportante() {
        return idAportante;
    }

    public int pesoTotal() {
        return pesoTotal;
    }

    public double valorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        return "\nComprobante{" +
                "idEntrega='" + idEntrega + '\'' +
                ", idAportante='" + idAportante + '\'' +
                ", pesoTotal=" + pesoTotal +
                ", valorTotal=" + valorTotal +
                '}'+"\n";
    }
}
