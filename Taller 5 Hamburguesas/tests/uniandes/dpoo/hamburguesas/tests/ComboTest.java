package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class ComboTest {

    @Test
    void testGetPrecioCombo() {
        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add(new ProductoMenu("corral", 14000));
        items.add(new ProductoMenu("criolla", 22000));

        Combo combo = new Combo("combo especial", 0.9, items);
        assertEquals(32400, combo.getPrecio());
    }

    @Test
    void testGenerarTextoFactura() {
        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add(new ProductoMenu("gaseosa", 5000));

        Combo combo = new Combo("combo corral", 0.8, items);
        String factura = combo.generarTextoFactura();

        assertTrue(factura.contains("combo corral"));
        assertTrue(factura.contains("Descuento: 0.8"));
    }
}

