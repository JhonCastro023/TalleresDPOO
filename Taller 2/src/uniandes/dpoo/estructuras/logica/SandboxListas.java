package uniandes.dpoo.estructuras.logica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class SandboxListas {

    private List<Integer> listaEnteros;
    private List<String> listaCadenas;

    public SandboxListas() {
        listaEnteros = new ArrayList<>();
        listaCadenas = new LinkedList<>();
    }

    public List<Integer> getCopiaEnteros() {
        return new ArrayList<>(listaEnteros);
    }

    public List<String> getCopiaCadenas() {
        return new ArrayList<>(listaCadenas);
    }

    public int[] getEnterosComoArreglo() {
        int[] arreglo = new int[listaEnteros.size()];
        for (int i = 0; i < listaEnteros.size(); i++) {
            arreglo[i] = listaEnteros.get(i);
        }
        return arreglo;
    }

    public int getCantidadEnteros() {
        return listaEnteros.size();
    }

    public int getCantidadCadenas() {
        return listaCadenas.size();
    }

    public void agregarEntero(int entero) {
        listaEnteros.add(entero);
    }

    public void agregarCadena(String cadena) {
        listaCadenas.add(cadena);
    }

    public void eliminarEntero(int valor) {
        listaEnteros.removeIf(x -> x == valor);
    }

    public void eliminarCadena(String cadena) {
        listaCadenas.removeIf(s -> s.equalsIgnoreCase(cadena));
    }

    public void insertarEntero(int entero, int posicion) {
        if (posicion < 0) {
            listaEnteros.add(0, entero);
        } else if (posicion >= listaEnteros.size()) {
            listaEnteros.add(entero);
        } else {
            listaEnteros.add(posicion, entero);
        }
    }

    public void eliminarEnteroPorPosicion(int posicion) {
        if (posicion >= 0 && posicion < listaEnteros.size()) {
            listaEnteros.remove(posicion);
        }
    }

    public void reiniciarArregloEnteros(double[] valores) {
        listaEnteros.clear();
        for (double valor : valores) {
            listaEnteros.add((int) valor);
        }
    }

    public void reiniciarArregloCadenas(List<Object> objetos) {
        listaCadenas.clear();
        for (Object obj : objetos) {
            listaCadenas.add(obj.toString());
        }
    }

    public void volverPositivos() {
        for (int i = 0; i < listaEnteros.size(); i++) {
            listaEnteros.set(i, Math.abs(listaEnteros.get(i)));
        }
    }

    public void organizarEnteros() {
        listaEnteros.sort((a, b) -> b - a);
    }

    public void organizarCadenas() {
        listaCadenas.sort(String::compareTo);
    }

    public int contarApariciones(int valor) {
        int count = 0;
        for (int entero : listaEnteros) {
            if (entero == valor) {
                count++;
            }
        }
        return count;
    }

    public int contarApariciones(String cadena) {
        int count = 0;
        for (String s : listaCadenas) {
            if (s.equalsIgnoreCase(cadena)) {
                count++;
            }
        }
        return count;
    }

    public int contarEnterosRepetidos() {
        List<Integer> visitados = new ArrayList<>();
        List<Integer> repetidos = new ArrayList<>();
        for (int entero : listaEnteros) {
            if (visitados.contains(entero) && !repetidos.contains(entero)) {
                repetidos.add(entero);
            } else {
                visitados.add(entero);
            }
        }
        return repetidos.size();
    }

    public boolean compararArregloEnteros(int[] otroArreglo) {
        if (otroArreglo.length != listaEnteros.size()) {
            return false;
        }
        for (int i = 0; i < otroArreglo.length; i++) {
            if (listaEnteros.get(i) != otroArreglo[i]) {
                return false;
            }
        }
        return true;
    }

    public void generarEnteros(int cantidad, int minimo, int maximo) {
        listaEnteros.clear();
        for (int i = 0; i < cantidad; i++) {
            int valor = minimo + (int) (Math.random() * (maximo - minimo + 1));
            listaEnteros.add(valor);
        }
    }
}
