package clases;

import clases.lotes.Lote;
import clases.lotes.Metal;
import clases.lotes.PapelCarton;
import enums.Metales;

public class LaunchApplication {
    static void main() {
        var lote=new Metal("Hola",2, Metales.ALUMINIO);
        var lote2=new Metal("Hola2",3, Metales.ACERO);
        var aportante= new Aportante("1313132","Erick","asdasda@adas");
        var entrega=new Entrega(aportante);
        entrega.agregarLote(lote);
        entrega.agregarLote(lote2);
    }
}
