package uniandes.dpoo.estructuras.logica;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.Arrays;

public class SandboxConjuntos {

    private NavigableSet<String> arbolCadenas;

    public SandboxConjuntos() {
        arbolCadenas = new TreeSet<>();
    }

    public List<String> getCadenasComoLista() {
        return new ArrayList<>(arbolCadenas);
    }

    public List<String> getCadenasComoListaInvertida() {
        List<String> listaInvertida = new ArrayList<>(arbolCadenas);
        Collections.reverse(listaInvertida);
        return listaInvertida;
    }

    public String getPrimera() {
        return arbolCadenas.isEmpty() ? null : arbolCadenas.first();
    }

    public String getUltima() {
        return arbolCadenas.isEmpty() ? null : arbolCadenas.last();
    }

    public Collection<String> getSiguientes(String cadena) {
        return arbolCadenas.tailSet(cadena, true);
    }

    public int getCantidadCadenas() {
        return arbolCadenas.size();
    }

    public void agregarCadena(String cadena) {
        arbolCadenas.add(cadena);
    }

    public void eliminarCadena(String cadena) {
        arbolCadenas.remove(cadena);
    }

    public void eliminarCadenaSinMayusculasOMinusculas(String cadena) {
        String cadenaMinuscula = cadena.toLowerCase();
        for (String s : arbolCadenas) {
            if (s.equalsIgnoreCase(cadena)) {
                arbolCadenas.remove(s);
                break;
            }
        }
    }

    public void eliminarPrimera() {
        if (!arbolCadenas.isEmpty()) {
            arbolCadenas.pollFirst();
        }
    }

    public void reiniciarConjuntoCadenas(List<Object> objetos) {
        arbolCadenas.clear();
        for (Object obj : objetos) {
            arbolCadenas.add(obj.toString());
        }
    }

    public void volverMayusculas() {
        NavigableSet<String> nuevoArbol = new TreeSet<>();
        for (String s : arbolCadenas) {
            nuevoArbol.add(s.toUpperCase());
        }
        arbolCadenas = nuevoArbol;
    }

    public TreeSet<String> invertirCadenas() {
        return new TreeSet<>(Collections.reverseOrder(arbolCadenas.comparator()));
    }

    public boolean compararElementos(String[] otroArreglo) {
        return arbolCadenas.containsAll(Arrays.asList(otroArreglo));
    }
}
