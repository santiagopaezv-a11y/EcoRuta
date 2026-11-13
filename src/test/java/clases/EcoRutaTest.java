package clases;

import clases.lotes.Lote;
import clases.lotes.Metal;
import clases.lotes.PapelCarton;
import clases.lotes.ResiduosElectronicos;
import enums.Estados;
import enums.Metales;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase para probar las funcionalidades de negocio del sistema EcoRuta
 * @author Erick Casallas y Santiago Paez
 * @since 2026-11
 * Licencia GNU/GPL V3.0
 * (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */
class EcoRutaTest {

    /**
     * Instancia para el manejo de logs
     */

    private static final Logger LOG = Logger.getLogger(EcoRutaTest.class.getName());

    private Empresa empresa;
    private Aportante aportanteRegistrado;

    @BeforeEach
    public void setUp() {
        empresa = new Empresa("900123456", "EcoRuta", "Armenia");
        aportanteRegistrado = new Aportante("1094001", "Laura Gomez", "laura@mail.com");
        empresa.getListAportantes().add(aportanteRegistrado);
    }

    //Prueba 1: Registro de una entrega, validación de estado inicial, duplicados y aportante desconocido

    @Test
    @DisplayName("Registro de una entrega")

    public void testRegistroEntrega() {
        LOG.info("Inicio de prueba testRegistroEntrega.");

        Entrega entrega1 = new Entrega(aportanteRegistrado);
        empresa.getListEntregas().add(entrega1);

        // Verificacion de estado inicial
        assertEquals(Estados.ABIERTA, entrega1.getEstadoEntrega());
        assertTrue(entrega1.getListaLotes().isEmpty());
        assertNull(entrega1.getComprobante());
        assertEquals(1, empresa.getListEntregas().size());

        // id automatico
        assertNotEquals("E01", entrega1.getId(), "El sistema autogenera IDs secuenciales en lugar de códigos manuales");

        LOG.info("Fin de prueba testRegistroEntrega.");
    }


     // Prueba 2: Validación de lotes (código duplicado y peso igual a 0 kg)

    @Test
    @DisplayName("Validación de lotes")
    public void testValidacionLotes() {
        LOG.info("Inicio de prueba testValidacionLotes.");

        Entrega entrega = new Entrega(aportanteRegistrado);

        Lote lote1 = new PapelCarton("Carton corrugado", 5, true);
        entrega.agregarLote(lote1);
        assertEquals(1, entrega.getListaLotes().size());

        // Intento de agregar lote con 0 kg
        Lote loteCeroKg = new PapelCarton("Papel archivo", 0, false);
        entrega.agregarLote(loteCeroKg);

        // Debe conservar únicamente el primer lote.
        assertEquals(1, entrega.getListaLotes().size(),
                "Error: El sistema permitió agregar un lote con 0 kg");

        LOG.info("Fin de prueba testValidacionLotes.");
    }


     // Prueba 3: Rechazo de aceptación de una entrega vacía

    @Test
    @DisplayName("Rechazo de una entrega vacía")
    public void testRechazoEntregaVacia() {
        LOG.info("Inicio de prueba testRechazoEntregaVacia.");

        Entrega entregaVacia = new Entrega(aportanteRegistrado);
        entregaVacia.aceptar();

        assertEquals(Estados.ABIERTA, entregaVacia.getEstadoEntrega());
        assertNull(entregaVacia.getComprobante());

        LOG.info("Fin de prueba testRechazoEntregaVacia.");
    }


     // Prueba 4: Aceptación y cálculo con los tres tipos de lote

    @Test
    @DisplayName("Aceptación y cálculo con los tres tipos de lote")
    public void testAceptacionYCalculoTresLotes() {
        LOG.info("Inicio de prueba testAceptacionYCalculoTresLotes.");

        Entrega entrega = new Entrega(aportanteRegistrado);

        Lote lotePapel = new PapelCarton("Papel y carton", 10, true);
        Lote loteMetal = new Metal("Perfiles aluminio", 5, Metales.ALUMINIO);
        Lote loteElectronico = new ResiduosElectronicos("Computadores", 4, 2);

        entrega.agregarLote(lotePapel);
        entrega.agregarLote(loteMetal);
        entrega.agregarLote(loteElectronico);

        // Valores esperados por cada lote
        assertEquals(6000.0, lotePapel.valorTotalKilogramo(), 0.01);
        assertEquals(11000.0, loteMetal.valorTotalKilogramo(), 0.01);

        assertEquals(7200.0, loteElectronico.valorTotalKilogramo(), 0.01,
                "Error en cálculo de ResiduosElectronicos: obtuvo $5.600 en lugar de $7.200");

        entrega.aceptar();

        assertEquals(Estados.ACEPTADA, entrega.getEstadoEntrega());
        assertNotNull(entrega.getComprobante());
        assertEquals(19, entrega.getComprobante().pesoTotal());
        assertEquals(24200.0, entrega.getComprobante().valorTotal(), 0.01);

        LOG.info("Fin de prueba testAceptacionYCalculoTresLotes.");
    }

    // Prueba 5: Consulta de etiquetas de la entrega aceptada

    @Test
    @DisplayName("Consulta de etiquetas")
    public void testConsultaEtiquetas() {
        LOG.info("Inicio de prueba testConsultaEtiquetas.");

        Entrega entrega = new Entrega(aportanteRegistrado);

        Lote lotePapel = new PapelCarton("Papel y carton", 10, true);
        Lote loteMetal = new Metal("Perfiles aluminio", 5, Metales.ALUMINIO);
        Lote loteElectronico = new ResiduosElectronicos("Computadores", 4, 2);

        entrega.agregarLote(lotePapel);
        entrega.agregarLote(loteMetal);
        entrega.agregarLote(loteElectronico);
        entrega.aceptar();

        List<String> etiquetas = entrega.obtenerEtiquetas();

        // 2 etiquetas Metal y ResiduosElectronicos
        assertEquals(2, etiquetas.size());

        // Etiqueta Metal
        assertTrue(etiquetas.get(0).contains(loteMetal.getId()));
        assertTrue(etiquetas.get(0).contains("ALUMINIO"));

        // Etiqueta Residuo Electrónico
        assertTrue(etiquetas.get(1).contains(loteElectronico.getId()));
        assertTrue(etiquetas.get(1).contains("2"));

        LOG.info("Fin de prueba testConsultaEtiquetas.");
    }
}