package clases;

import clases.lotes.Lote;
import clases.lotes.Metal;
import clases.lotes.ResiduosElectronicos;
import record.Comprobante;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Empresa {

    private String nit;
    private String nombre;
    private String direccion;
    List <Entrega> listEntregas ;
    List <Aportante> listAportantes ;
    List <Comprobante> listComprobantes ;

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

        menu = JOptionPane.showInputDialog(menu);

        switch (menu) {

            case "1":
                moduloAportante();
                break;

            case "2":
                moduloEntrega();
                break;

            case "3":
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opcion no valida");
                break;
        }
    }
        public void moduloAportante(){

            String menu = "Aportante:" +
                    "\n ingrese la opcion (1,2,3...): " +
                    "\n1. Registrar aportante. " +
                    "\n2. Ver aportantes. " +
                    "\n3. Actualizar aportante. " +
                    "\n4. Eliminar aportante. " +
                    "\n5 Ver valor acumulado del aportante." +
                    "\n6. Volver al menu de EcoRuta. ";

            menu = JOptionPane.showInputDialog(menu);

            switch (menu) {

                case "1":
                    registrarAportante();
                    break;
                case "2":
                    mostrarAportantes();
                    break;
                case "3":
                    actualizarAportante();
                    break;
                case "4":
                    eliminarAportante();
                    break;
                case "5":
                    mostrarValorAcu();
                    break;
                case "6":
                    cargar();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida");
                    break;
            }
        }

        //CRUD APORTANTE - MODULO

    public void registrarAportante(){

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
    }


    public void mostrarAportantes(){
        String mensaje = "";
        for (Aportante aportante : getListAportantes()) {
            mensaje += getListAportantes().toString() + "-------------------\n";
        }
        JOptionPane.showMessageDialog(null, mensaje);
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

        String menu = "Entrega:" +
                "\n ingrese la opcion (1,2,3...): " +
                "\n1. Crear entrega. " +
                "\n2. Ver entrega por id. " +
                "\n3. Eliminar entrega. " +
                "\n4. Ver lista de lotes. " +
                "\n5. Volver al menu de EcoRuta";

        menu = JOptionPane.showInputDialog(menu);

        switch (menu) {

            case "1":
                crearEntrega();
                break;
            case "2":
                verEntregaPorId();
                break;
            case "3":
                eliminarEntrega();
                break;
            case "4":
                moduloLote();
                break;
            case "5":
                cargar();
            default:
                JOptionPane.showMessageDialog(null, "Opcion no valida");
                break;
        }
    }

    public void crearEntrega(){

        String idAportante = JOptionPane.showInputDialog("Ingrese el id del aportante que desea agregar. ");

        for (Aportante aportante : getListAportantes()) {
            if (aportante.getIdentificacion().trim().equals(idAportante)) {
                Entrega entrega = new Entrega(aportante);
                getListEntregas().add(entrega);
                JOptionPane.showMessageDialog(null, "La entrega se ha creado exitosamente:" +
                                                "\nCon el numero de id " + entrega.getId() + "."); return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro el id del aportante (La entrega no se creo). ");
    }

    public void verEntregaPorId(){

        String idEntregaVer = JOptionPane.showInputDialog("Ingrese el id de la entrega que desea ver: ");

        for (Entrega entrega : getListEntregas()) {
            if(entrega.getId().trim().equals(idEntregaVer.trim())) {
                String mensaje = "";
                for (Lote lote : entrega.getListaLotes()) {
                    mensaje += etiquetas(lote) + "\n--------------\n";
            }
                mensaje +=  entrega.getComprobante();
                JOptionPane.showMessageDialog(null, mensaje);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro la entrega con el id " + idEntregaVer);
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

        Entrega entrega = consultarEntrega();
        if (entrega == null)return;

        String menu = "Lote:" +
                "\n ingrese la opcion (1,2,3...): " +
                "\n1. Crear lote. " +
                "\n2. Ver lista de lotes. " +
                "\n3. Eliminar lote. " +
                "\n4. Volver al menu de EcoRuta";

        menu = JOptionPane.showInputDialog(menu);

        switch (menu) {

            case "1":
                crearLote();
                break;
            case "2":
                verListaLotes();
                break;
            case "3":
                eliminarLote();
                break;
            case "4":
                cargar();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opcion no valida");
                break;
        }
    }

    public void crearLote(){
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion del lote. ");
    }







    public Entrega consultarEntrega(){
        String idEntrega = JOptionPane.showInputDialog("Ingrese el id de la entrega.");
        Entrega entrega1 = null;
        for (Entrega entrega : getListEntregas()) {
            if (entrega.getId().trim().equals(idEntrega.trim())) {
                entrega1 = entrega;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro la entrega con el id " + idEntrega);
    return entrega1;
    }

    public String etiquetas(Lote lote){

        String mensaje = "";
            if (lote instanceof Metal){
                mensaje = ((Metal) lote).generarEtiqueta();
        }
            else if (lote instanceof ResiduosElectronicos){
                mensaje = ((ResiduosElectronicos) lote).generarEtiqueta();
            }
            return mensaje;
    }
}



