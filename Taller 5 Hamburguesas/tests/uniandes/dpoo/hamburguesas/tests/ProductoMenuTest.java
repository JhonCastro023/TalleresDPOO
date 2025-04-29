package uniandes.dpoo.hamburguesas.tests;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProductoMenuTest {
    @Test
	void testGetNombre() {
        ProductoMenu producto = new ProductoMenu("ensalada mexicana",20900);
        assertEquals("ensalada mexicana", producto.getNombre());
    }
    @Test
	void testGetPrecio() {
        ProductoMenu producto = new ProductoMenu("papa medianas",5500);
        assertEquals(5500, producto.getPrecio());
    }
    @Test
	 void testGenerarTextoFactura() {
	        ProductoMenu producto = new ProductoMenu("gaseosa", 5000);
	        String factura = producto.generarTextoFactura();
	        assertTrue(factura.contains("gaseosa"));
	        assertTrue(factura.contains("5000"));
	    }
}
