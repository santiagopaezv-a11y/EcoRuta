package clases;

import clases.lotes.Lote;
import clases.lotes.Metal;
import clases.lotes.PapelCarton;
import clases.lotes.ResiduosElectronicos;
import enums.Metales;
import interfaces.IGenerable;
import record.Comprobante;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class Empresa {

    private String nit;
    private String nombre;
    private String direccion;
    public static List <Entrega> listEntregas ;
    public static List <Aportante> listAportantes ;
    public static List <Comprobante> listComprobantes ;

    public Empresa(String nit, String nombre, String direccion) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.listEntregas = new ArrayList<>();
        this.listAportantes = new ArrayList<>();
        this.listComprobantes = new ArrayList<>();
    }

    public String getNit() {
        return nit;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Entrega> getListEntregas() {
        return listEntregas;
    }

    public List<Aportante> getListAportantes() {
        return listAportantes;
    }

    public List<Comprobante> getListComprobantes() {
        return listComprobantes;
    }




    public void cargar() {

        String menu = "Bienvenido al menu de EcoRuta" +
                "\n ingrese la opcion (1,2,3...): " +
                "\n1. Aportante. " +
                "\n2. Entrega. " +
                "\n3. Salir. ";

        int option;
        do {
            switch (option=Integer.parseInt(JOptionPane.showInputDialog(menu))) {

                case 1:
                    moduloAportante();
                    break;

                case 2:
                    moduloEntrega();
                    break;
                case 3:
                    break;
            }
        }while (option!=3);

    }
        public void moduloAportante(){
            try {
                String menu = "Aportante:" +
                        "\n ingrese la opcion (1,2,3...): " +
                        "\n1. Registrar aportante. " +
                        "\n2. Ver aportantes. " +
                        "\n3. Actualizar aportante. " +
                        "\n4. Eliminar aportante. " +
                        "\n5 Ver valor acumulado del aportante." +
                        "\n0. Volver al menu de EcoRuta. ";

                int option;
                do {
                    switch (option=Integer.parseInt(JOptionPane.showInputDialog(menu))) {

                        case 1:
                            registrarAportante();
                            break;
                        case 2:
                            mostrarAportantes();
                            break;
                        case 3:
                            actualizarAportante();
                            break;
                        case 4:
                            eliminarAportante();
                            break;
                        case 5:
                            mostrarValorAcu();
                            break;
                    }
                }while (option!=0);
            }catch (Exception e){

            }

        }

        //CRUD APORTANTE - MODULO

    public void registrarAportante(){
        try {
            String id = JOptionPane.showInputDialog("Ingrese el id. ");
            for (Aportante aportante : getListAportantes()) {
                if (aportante.getIdentificacion().trim().equals(id.trim())){
                    JOptionPane.showMessageDialog(null, "Ya existe un aportante registrado con este id. ");
                    return;
                }
            }
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre. ");
            String correo = JOptionPane.showInputDialog("Ingrese el correo. ");

            Aportante aportante = new Aportante(id, nombre, correo);
            getListAportantes().add(aportante);
            JOptionPane.showMessageDialog(null, "Aportante registrado exitosamente.");
        }catch (Exception e){

        }
    }


    public void mostrarAportantes() {
        if (getListAportantes().isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "No hay aportantes registrados en el sistema.",
                    "Lista de Aportantes",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        StringBuilder mensaje = new StringBuilder();
        mensaje.append("===========================================\n");
        mensaje.append("           LISTA DE APORTANTES             \n");
        mensaje.append("===========================================\n\n");

        for (Aportante aportante : getListAportantes()) {
            mensaje.append("• ").append(aportante.toString()).append("\n");
            mensaje.append("--------------------------------------------------\n");
        }

        JOptionPane.showMessageDialog(
                null,
                mensaje.toString(),
                "Lista de Aportantes",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void actualizarAportante(){

        String idAportanteActualizar = JOptionPane.showInputDialog("Ingrese el id del aportante que desea actualizar. ");

        for (Aportante aportante : getListAportantes()) {
            if (aportante.getIdentificacion().trim().equals(idAportanteActualizar.trim())) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del aportante. ");
                aportante.setNombre(nombre);
                String correo = JOptionPane.showInputDialog("Ingrese el correo del aportante. ");
                aportante.setCorreo(correo);
                JOptionPane.showMessageDialog(null, "Aportante actualizado exitosamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Aportante actualizado exitosamente.");
    }

    public void eliminarAportante(){
        String idAportanteEliminar = JOptionPane.showInputDialog("Ingrese el id del aportante que desea eliminar. ");

        for (Aportante aportante : getListAportantes()) {
            if (aportante.getIdentificacion().trim().equals(idAportanteEliminar.trim())) {
                getListAportantes().remove(aportante);
                JOptionPane.showMessageDialog(null, "Aportante eliminado exitosamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro el aportante con el id " + idAportanteEliminar);
    }

    public void mostrarValorAcu(){
        String idAportanteMostrar = JOptionPane.showInputDialog("Ingrese el id del aportante. ");

        for (Aportante aportante : getListAportantes()) {
            if (aportante.getIdentificacion().trim().equals(idAportanteMostrar.trim())) {
                JOptionPane.showMessageDialog(null, "El valor acumulado el aportante es "+ consultarValorAcumulado(idAportanteMostrar));
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro el aportante con el id " + idAportanteMostrar);
    }


    public double consultarValorAcumulado(String identificacion) {

        double totalAcumulado = 0.0;

        for (Comprobante c : getListComprobantes()) {
            if (c.idAportante().equals(identificacion)) {
                totalAcumulado += c.valorTotal();
            }
        }
        return totalAcumulado;
    }

    // MODULO ENTREGA

    public void moduloEntrega(){
        try {
            String menu = "Entrega:" +
                    "\n ingrese la opcion (1,2,3...): " +
                    "\n1. Crear entrega. " +
                    "\n2. Ver entrega por id. " +
                    "\n3. Eliminar entrega. " +
                    "\n4. Ver lista de lotes. " +
                    "\n5. Aceptar entrega" +
                    "\n0. Volver al menu de EcoRuta";

            int option ;
            do {
                switch (option=Integer.parseInt(JOptionPane.showInputDialog(menu))) {

                    case 1:
                        crearEntrega();
                        break;
                    case 2:
                        verEntregaPorId();
                        break;
                    case 3:
                        eliminarEntrega();
                        break;
                    case 4:
                        moduloLote();
                        break;
                    case 5:
                        Entrega entrega= consultarEntrega();
                        entrega.aceptar();
                }
            }while (option!=0);
        }catch (Exception e){

        }
    }

    public void crearEntrega(){

        String idAportante = JOptionPane.showInputDialog("Ingrese el id del aportante que desea agregar. ");

        for (Aportante aportante : getListAportantes()) {
            if (aportante.getIdentificacion().trim().equals(idAportante)) {
                Entrega entrega = new Entrega(aportante);
                getListEntregas().add(entrega);
                JOptionPane.showMessageDialog(null, "La entrega se ha creado exitosamente:" +
                                                "\nCon el numero de id " + entrega.getId() + ".");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro el id del aportante (La entrega no se creo). ");
    }
    public void verEntregaPorId() {
        Entrega entrega = consultarEntrega();
        try {
            if (entrega == null) {
                JOptionPane.showMessageDialog(
                        null,
                        "No existe una entrega registrada con el código: " + entrega.getId(),
                        "Entrega No Encontrada",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }



        StringBuilder msj = new StringBuilder();

        // 2. Encabezado e información general de la entrega
        msj.append("===========================================\n");
        msj.append("              DETALLE DE ENTREGA           \n");
        msj.append("===========================================\n");
        msj.append("Código: ").append(entrega.getId()).append("\n");
        msj.append("Estado: ").append(entrega.getEstadoEntrega()).append("\n");
        msj.append("Aportante ID: ").append(entrega.getAportante().getIdentificacion()).append("\n");
        msj.append("-------------------------------------------\n");
        msj.append("LOTES REGISTRADOS:\n");

        if (entrega.getListaLotes().isEmpty()) {
            msj.append("  (No hay lotes en esta entrega)\n");
        } else {
            for (Lote lote : entrega.getListaLotes()) {
                msj.append(lote.toString()).append("\n");
            }
        }

        // 3. Comprobante (si fue aceptada)
        if (entrega.getComprobante() != null) {
            msj.append("\n-------------------------------------------\n");
            msj.append("COMPROBANTE DE ACEPTACIÓN:\n");
            msj.append("-------------------------------------------\n");
            msj.append("  • Peso Total: ").append(entrega.getComprobante().pesoTotal()).append(" kg\n");
            msj.append("  • Valor Total Reconocido: $").append(String.format("%.2f", entrega.getComprobante().valorTotal())).append("\n");
        }

        // 4. Etiquetas internas (solo Metales y Residuos Electrónicos)
        List<String> etiquetas = entrega.obtenerEtiquetas();
        msj.append("\n-------------------------------------------\n");
        msj.append("ETIQUETAS DE CONTROL INTERNO:\n");
        msj.append("-------------------------------------------\n");

        if (etiquetas.isEmpty()) {
            msj.append("  (No hay etiquetas generadas para esta entrega)\n");
        } else {
            for (String etiqueta : etiquetas) {
                msj.append("  ✔ ").append(etiqueta).append("\n");
            }
        }
        JOptionPane.showMessageDialog(
                null,
                msj.toString(),
                "Consulta de Entrega - " + entrega.getId(),
                JOptionPane.INFORMATION_MESSAGE
        );
        }catch (Exception e){
        }
    }
    public void eliminarEntrega(){
        String idEntregaEliminar = JOptionPane.showInputDialog("Ingrese el id de la entrega que desea eliminar. ");

        for (Entrega entrega : getListEntregas()) {
            if (entrega.getId().trim().equals(idEntregaEliminar.trim())) {
                getListAportantes().remove(entrega);
                JOptionPane.showMessageDialog(null, "Entrega eliminada exitosamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro la entrega con el id " + idEntregaEliminar);
    }
    public void moduloLote(){
        try {
            Entrega entrega = consultarEntrega();
            if (entrega == null)return;

            String menu = "Lote:" +
                    "\n ingrese la opcion (1,2,3...): " +
                    "\n1. Crear lote. " +
                    "\n2. Ver lista de lotes. " +
                    "\n3. Eliminar lote. " +
                    "\n0. Volver al de entrega";

            int option;
            do {
                switch (option=Integer.parseInt(JOptionPane.showInputDialog(menu))) {

                    case 1:
                        crearLote(entrega);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null,entrega.mostrarListaLotes());
                        break;
                    case 3:
                        removerLote(entrega);
                        break;
                }
            }while (option!=0);
        }catch (Exception e){}

    }
    private void removerLote(Entrega entrega){
        entrega.removerLote();
    }
    public void crearLote(Entrega entrega){
        try {
            Lote lote;
            String menu = "Tipo de lote:" +
                    "\n ingrese la opcion (1,2,3): " +
                    "\n1. Papel y Carton" +
                    "\n2. Metal" +
                    "\n3. Residuos electronicos" +
                    "\n0. Salir";

            int option;
            do {
                switch (option=Integer.parseInt(JOptionPane.showInputDialog(menu))) {

                    case 1:
                        lote=agregarPapelCarton();
                        entrega.agregarLote(lote);
                        break;
                    case 2:
                        lote=agregarMetal();
                        if (lote==null){
                            return;
                        }
                        entrega.agregarLote(lote);
                        break;
                    case 3:
                        lote=agregarResiduoElectronico();
                        entrega.agregarLote(lote);
                        break;
                }
            }while (option!=0);
        }catch (Exception e){}
    }
    private PapelCarton agregarPapelCarton(){
        String descripcion=JOptionPane.showInputDialog("Ingrese la descripcion del lote");
        int kilogramos=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de kilogramos del lote"));
        boolean clasificado=Boolean.parseBoolean(JOptionPane.showInputDialog("¿El lote esta clasificado?"));
        return new PapelCarton(descripcion,kilogramos,clasificado);
    }
    private Metal agregarMetal(){
        String descripcion=JOptionPane.showInputDialog("Ingrese la descripcion del lote");
        int kilogramos=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de kilogramos del lote"));
        Metales tipoMetal=Metal.escogerTipoMetal();
        if (tipoMetal==null){
            return null;
        }
        return new Metal(descripcion,kilogramos,tipoMetal);
    }
    private ResiduosElectronicos agregarResiduoElectronico(){
        String descripcion=JOptionPane.showInputDialog("Ingrese la descripcion del lote");
        int kilogramos=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de kilogramos del lote"));
        int cantidadEquipos=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de equipos electronicos completos"));
        return new ResiduosElectronicos(descripcion,kilogramos,cantidadEquipos);
    }
    public Entrega consultarEntrega(){
        String idEntrega = JOptionPane.showInputDialog("Ingrese el id de la entrega.");
        Entrega entrega1 = null;
        for (Entrega entrega : getListEntregas()) {
            if (entrega.getId().trim().equals(idEntrega.trim())) {
                entrega1 = entrega;
                return entrega1;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro la entrega con el id " + idEntrega);
    return null;
    }
}



