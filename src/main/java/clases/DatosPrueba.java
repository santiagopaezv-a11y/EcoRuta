package clases;

import clases.lotes.PapelCarton;
import clases.lotes.ResiduosElectronicos;
import enums.Metales;
import clases.lotes.Metal;

import java.awt.Window;
import javax.swing.Timer;
import javax.swing.JDialog;

public class DatosPrueba {



    public static void cargarDatosPrueba(Empresa empresa) {

        Timer cierraVentanas = new Timer(1, e -> {
            for (Window window : Window.getWindows()) {
                if (window instanceof JDialog dialog && dialog.isVisible()) {
                    dialog.dispose();
                }
            }
        });
        cierraVentanas.start();



        // APORTANTES

        Aportante aportante1 =
                new Aportante("1095552185", "Paez", "santiago.paezv@uqvitrual.edu.co");
        Aportante aportante2 =
                new Aportante("1188963354", "Erick", "ericks.casallasp@uqvitrual.edu.co");
        Aportante aportante3 =
                new Aportante("123456789", "Jhan Carlos", "jhancarlos@uqvirtual.edu.co");
        Aportante aportante4 =
                new Aportante("246810", "Patrick Jane", "patrickj@uqvitrual.edu.co");

        empresa.getListAportantes().add(aportante1);
        empresa.getListAportantes().add(aportante2);
        empresa.getListAportantes().add(aportante3);
        empresa.getListAportantes().add(aportante4);

        // LOTES

        var loteMetal1 = new Metal("Varillas de acero", 10, Metales.ACERO);
        var loteMetal2 = new Metal("Latas de aluminio", 7, Metales.ALUMINIO);
        var loteMetal3 = new Metal("Vigas de acero", 40, Metales.ACERO);
        var lotePapel1 = new PapelCarton("Cajas de carton limpias", 5, true);
        var lotePapel2 = new PapelCarton("Papel periodico", 14, false);
        var loteElec1 = new ResiduosElectronicos("Portatiles dañados", 21,7);

        // ENTREGAS
        Entrega entrega1 =
                new Entrega(aportante1);
        entrega1.agregarLote(loteMetal1);
        entrega1.agregarLote(lotePapel1);

        Entrega entrega2 =
                new Entrega(aportante2);
        entrega2.agregarLote(loteMetal2);
        entrega2.agregarLote(loteElec1);

        Entrega entrega3 =
                new Entrega(aportante3);
        entrega3.agregarLote(lotePapel2);

        Entrega entrega4 =
                new Entrega(aportante4);
        entrega4.agregarLote(loteMetal3);
        entrega4.aceptar();

        empresa.getListEntregas().add(entrega1);
        empresa.getListEntregas().add(entrega2);
        empresa.getListEntregas().add(entrega3);
        empresa.getListEntregas().add(entrega4);

        cierraVentanas.stop();
    }
}
