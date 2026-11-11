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
        return String.format(
                "===========================================\n" +
                        "            COMPROBANTE DE ENTREGA         \n" +
                        "===========================================\n" +
                        "  Código Entrega : %s\n" +
                        "  ID Aportante   : %s\n" +
                        "  Peso Total     : %d kg\n" +
                        "  Valor Total    : $%.2f\n" +
                        "===========================================",
                idEntrega, idAportante, pesoTotal, valorTotal
        );
    }
}
