package uniandes.dpoo.hamburguesas.mundo;

import java.util.ArrayList;

/**
 * Un producto ajustado es un producto para el cual el cliente solicitó alguna modificación.
 */
public class ProductoAjustado implements Producto {
    /**
     * El producto base que el cliente sobre el cual el cliente quiere hacer ajustes
     */
    private ProductoMenu productoBase;

    /**
     * La lista de ingredientes que el usuario quiere agregar. El mismo ingrediente puede aparecer varias veces.
     */
    private ArrayList<Ingrediente> agregados;

    /**
     * La lista de ingredientes que el usuario quiere eliminar.
     */
    private ArrayList<Ingrediente> eliminados;

    /**
     * Construye un nuevo producto ajustado a partir del producto base y sin modificaciones
     * @param productoBase El producto base que se va a ajustar
     */
    public ProductoAjustado(ProductoMenu productoBase) {
        this.productoBase = productoBase;
        this.agregados = new ArrayList<>();
        this.eliminados = new ArrayList<>();
    }

    @Override
    public String getNombre() {
        return productoBase.getNombre();
    }

    /**
     * Retorna el precio del producto ajustado, que debe ser igual al del producto base,
     * sumándole el precio de los ingredientes adicionales.
     */
    @Override
    public int getPrecio() {
        int precioTotal = productoBase.getPrecio();
        for (Ingrediente ing : agregados) {
            precioTotal += ing.getCostoAdicional();
        }
        return precioTotal;
    }

    /**
     * Genera el texto que debe aparecer en la factura.
     * 
     * El texto incluye el producto base, los ingredientes adicionales con su costo, 
     * los ingredientes eliminados, y el precio total.
     */
    @Override
    public String generarTextoFactura() {
        StringBuilder sb = new StringBuilder();
        sb.append(productoBase.getNombre()).append("\n"); // Añadido para mostrar el nombre del producto base
        for (Ingrediente ing : agregados) {
            sb.append("    +").append(ing.getNombre()).append("\n");
            sb.append("                ").append(ing.getCostoAdicional()).append("\n");
        }
        for (Ingrediente ing : eliminados) {
            sb.append("    -").append(ing.getNombre()).append("\n");
        }
        sb.append("            Total: ").append(getPrecio()).append("\n");

        return sb.toString();
    }

    public ArrayList<Ingrediente> getAgregados() {
        return agregados;
    }

    public void setAgregados(ArrayList<Ingrediente> agregados) {
        this.agregados = agregados;
    }

    public ArrayList<Ingrediente> getEliminados() {
        return eliminados;
    }

    public void setEliminados(ArrayList<Ingrediente> eliminados) {
        this.eliminados = eliminados;
    }
}
