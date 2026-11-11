package clases;

import clases.lotes.Lote;
import enums.Metales;
import clases.lotes.Metal;
public class DatosPrueba {



    public static void cargar() {

        // APORTANTES

        Aportante aportante1 =
                new Aportante("1095552185", "Paez", "santiago.paezv@uqvitrual.edu.co");
        Aportante aportante2 =
                new Aportante("1188963354", "Erick", "ericks.casallasp@uqvitrual.edu.co");
        Aportante aportante3 =
                new Aportante("123456789", "Jhan Carlos", "jhancarlos@uqvirtual.edu.co");
        Aportante aportante4 =
                new Aportante("246810", "Patrick Jane", "patrickjuqvitrual.edu.co");

        // LOTES

        var lote1 = new Metal("DESCRIPCION", 5, Metales.ACERO);
        var lote2 = new Metal("DESCRIPCION", 5, Metales.ACERO);
        var lote3 = new Metal("DESCRIPCION", 5, Metales.ACERO);
        var lote4 = new Metal("DESCRIPCION", 5, Metales.ACERO);

        // ENTREGAS
        Entrega entrega1 =
                new Entrega(aportante1);
        entrega1.agregarLote(lote1);
        Entrega entrega2 =
                new Entrega(aportante2);
        entrega2.agregarLote(lote2);
        Entrega entrega3 =
                new Entrega(aportante3);
        entrega3.agregarLote(lote3);
        Entrega entrega4 =
                new Entrega(aportante4);
        entrega4.agregarLote(lote4);

    }
}
