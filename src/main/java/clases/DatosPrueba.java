package clases;

public class DatosPrueba {

    public static void cargar() {
        Aportante aportante1 =
                new Aportante("1095552185", "Paez", "santiago.paezv@uqvitrual.edu.co");
        Aportante aportante2 =
                new Aportante("1188963354", "Erick", "ericks.casallasp@uqvitrual.edu.co");
        Aportante aportante3 =
                new Aportante("123456789", "Jhan Carlos", "jhancarlos@uqvirtual.edu.co");
        Aportante aportante4 =
                new Aportante("246810", "Patrick Jane", "patrickjuqvitrual.edu.co");



        Entrega entrega1 =
                new Entrega(aportante1);
        Entrega entrega2 =
                new Entrega(aportante2);
        Entrega entrega3 =
                new Entrega(aportante3);
        Entrega entrega4 =
                new Entrega(aportante4);


    }
}
