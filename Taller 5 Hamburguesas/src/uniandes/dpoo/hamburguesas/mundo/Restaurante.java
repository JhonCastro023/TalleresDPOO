package uniandes.dpoo.hamburguesas.mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import uniandes.dpoo.hamburguesas.excepciones.*;

public class Restaurante {

    private static final String CARPETA_FACTURAS = "./facturas/";
    private static final String PREFIJO_FACTURAS = "factura_";

    private ArrayList<Pedido> pedidos;
    private ArrayList<Ingrediente> ingredientes;
    private ArrayList<ProductoMenu> menuBase;
    private ArrayList<Combo> menuCombos;
    private Pedido pedidoEnCurso;

    public Restaurante() {
        pedidos = new ArrayList<>();
        ingredientes = new ArrayList<>();
        menuBase = new ArrayList<>();
        menuCombos = new ArrayList<>();
    }

    public void iniciarPedido(String nombreCliente, String direccionCliente) throws YaHayUnPedidoEnCursoException {
        if (pedidoEnCurso != null) {
            throw new YaHayUnPedidoEnCursoException(pedidoEnCurso.getNombreCliente(), nombreCliente);
        }
        pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
    }

    public void cerrarYGuardarPedido() throws NoHayPedidoEnCursoException, IOException {
        if (pedidoEnCurso == null) {
            throw new NoHayPedidoEnCursoException();
        }

        File directorio = new File(CARPETA_FACTURAS);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        String nombreArchivo = PREFIJO_FACTURAS + pedidoEnCurso.getIdPedido() + ".txt";
        pedidoEnCurso.guardarFactura(new File(directorio, nombreArchivo));

        pedidos.add(pedidoEnCurso);
        pedidoEnCurso = null;
    }

    public Pedido getPedidoEnCurso() {
        return pedidoEnCurso;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public ArrayList<ProductoMenu> getMenuBase() {
        return menuBase;
    }

    public ArrayList<Combo> getMenuCombos() {
        return menuCombos;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos)
            throws HamburguesaException, IOException {
        cargarIngredientes(archivoIngredientes);
        cargarMenu(archivoMenu);
        cargarCombos(archivoCombos);
    }

    private void cargarIngredientes(File archivoIngredientes) throws IngredienteRepetidoException, IOException {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(archivoIngredientes))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] ingredientesStr = linea.split(";");
                    String nombreIngrediente = ingredientesStr[0].trim();
                    int costoIngrediente = Integer.parseInt(ingredientesStr[1].trim());

                    if (buscarIngrediente(nombreIngrediente)) {
                        throw new IngredienteRepetidoException(nombreIngrediente);
                    }
                    ingredientes.add(new Ingrediente(nombreIngrediente, costoIngrediente));
                }
            }
        }
    }

    private void cargarMenu(File archivoMenu) throws ProductoRepetidoException, IOException {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(archivoMenu))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] productoStr = linea.split(";");
                    String nombreProducto = productoStr[0].trim();
                    int costoProducto = Integer.parseInt(productoStr[1].trim());

                    if (buscarProductoMenu(nombreProducto)) {
                        throw new ProductoRepetidoException(nombreProducto);
                    }
                    menuBase.add(new ProductoMenu(nombreProducto, costoProducto));
                }
            }
        }
    }

    private void cargarCombos(File archivoCombos) throws ProductoRepetidoException, ProductoFaltanteException, IOException {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(archivoCombos))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] comboStr = linea.split(";");
                    String nombreCombo = comboStr[0].trim();
                    double descuento = Double.parseDouble(comboStr[1].replace("%", "").trim()) / 100;

                    if (buscarCombo(nombreCombo)) {
                        throw new ProductoRepetidoException(nombreCombo);
                    }

                    ArrayList<ProductoMenu> itemsCombo = new ArrayList<>();
                    for (int i = 2; i < comboStr.length; i++) {
                        String nombreProducto = comboStr[i].trim();
                        ProductoMenu productoItem = buscarProductoEnMenuBase(nombreProducto);


                        if (productoItem == null) {
                            throw new ProductoFaltanteException(nombreProducto); 
                        }
                        itemsCombo.add(productoItem);
                    }

                    menuCombos.add(new Combo(nombreCombo, descuento, itemsCombo));
                }
            }
        }
    }


    private boolean buscarIngrediente(String nombre) {
        return ingredientes.stream().anyMatch(i -> i.getNombre().equals(nombre));
    }

    private boolean buscarProductoMenu(String nombre) {
        return menuBase.stream().anyMatch(p -> p.getNombre().equals(nombre));
    }

    private boolean buscarCombo(String nombre) {
        return menuCombos.stream().anyMatch(c -> c.getNombre().equals(nombre));
    }

    private ProductoMenu buscarProductoEnMenuBase(String nombre) {
        return menuBase.stream().filter(p -> p.getNombre().equals(nombre)).findFirst().orElse(null);
    }
}
