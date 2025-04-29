package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

public class PedidoTest {

    @Test
    void testAgregarProducto() {
        Pedido pedido = new Pedido("Kanye", "Calle 123");
        ProductoMenu producto = new ProductoMenu("todoterreno",25000);
        pedido.agregarProducto(producto);

        assertEquals(1, pedido.getProductos().size());
    }

    @Test
    void testGenerarTextoFactura() {
        Pedido pedido = new Pedido("Cudi", "Calle 456");
        pedido.agregarProducto(new ProductoMenu("corral", 14000));

        String factura = pedido.generarTextoFactura();
        assertTrue(factura.contains("Cudi"));
        assertTrue(factura.contains("14000"));
    }

    @Test
    void testGuardarFactura() {
        Pedido pedido = new Pedido("Travis", "Calle 789");
        pedido.agregarProducto(new ProductoMenu("gaseosa", 5000));

        File archivo = new File("factura.txt");
        assertDoesNotThrow(() -> pedido.guardarFactura(archivo));
        assertTrue(archivo.exists());

        archivo.delete();
    }
}
