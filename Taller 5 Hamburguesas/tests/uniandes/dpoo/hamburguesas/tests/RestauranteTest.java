package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.excepciones.*;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    public void setup() {
        restaurante = new Restaurante();
    }

    @Test
    public void testIniciarPedidoConPedidoEnCurso() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Kanye", "Calle 123");

        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
            restaurante.iniciarPedido("Cudi", "Calle 456");
        }, "Se esperaba YaHayUnPedidoEnCursoException.");
    }

    @Test
    public void testCerrarPedidoSinPedidoEnCurso() {
        assertThrows(NoHayPedidoEnCursoException.class, () -> {
            restaurante.cerrarYGuardarPedido();
        }, "Se esperaba NoHayPedidoEnCursoException.");
    }

    @Test
    public void testIngredienteRepetidoException() throws IOException {
        File archivo = crearArchivoIngredientesDuplicados();

        assertThrows(IngredienteRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(archivo, new File("menu.txt"), new File("combos.txt"));
        }, "Se esperaba IngredienteRepetidoException.");
    }

    @Test
    public void testProductoRepetidoException() throws IOException {
        crearArchivoIngredientesValidos();

        File archivo = crearArchivoMenuDuplicado();

        assertThrows(ProductoRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(new File("ingredientes.txt"), archivo, new File("combos.txt"));
        }, "Se esperaba ProductoRepetidoException.");
    }

    private void crearArchivoIngredientesValidos() throws IOException {
        File archivo = new File("ingredientes.txt");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write("queso mozzarella;500\n");
            writer.write("lechuga;200\n");
            writer.write("tomate;300\n");
        }
    }


    @Test
    private File crearArchivoMenuSinDuplicados() throws IOException {
        File archivo = new File("menu.txt");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write("corral;14000\n");
        }
        return archivo;
    }
    public void testProductoFaltanteException() throws IOException {
        crearArchivoIngredientesValidos();
        File archivo = crearArchivoComboConProductoInexistente();
        File menuArchivo = crearArchivoMenuSinDuplicados();

        assertThrows(ProductoFaltanteException.class, () -> {
            restaurante.cargarInformacionRestaurante(new File("ingredientes.txt"), menuArchivo, archivo);
        }, "Se esperaba ProductoFaltanteException.");
    }

    private File crearArchivoIngredientesDuplicados() throws IOException {
        File archivo = new File("ingredientes.txt");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write("queso mozzarella;2500\n");
            writer.write("queso mozzarella;2500\n"); 
        }
        return archivo;
    }

    private File crearArchivoMenuDuplicado() throws IOException {
        File archivo = new File("menu.txt");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write("agua cristal con gas;5000\n");
            writer.write("agua cristal con gas;5000\n"); 
        }
        return archivo;
    }

    private File crearArchivoComboConProductoInexistente() throws IOException {
        File archivo = new File("combos.txt");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write("Combo Especial;10%;ProductoInexistente\n");
        }
        return archivo;
    }
}
