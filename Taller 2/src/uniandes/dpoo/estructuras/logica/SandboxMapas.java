package uniandes.dpoo.estructuras.logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SandboxMapas {

    private Map<String, String> mapaCadenas;

    public SandboxMapas() {
        mapaCadenas = new HashMap<>();
    }

    public List<String> getValoresComoLista() {
        return mapaCadenas.values().stream()
            .sorted()
            .collect(Collectors.toList());
    }

    public List<String> getLlavesComoListaInvertida() {
        return mapaCadenas.keySet().stream()
            .sorted((a, b) -> b.compareTo(a))
            .collect(Collectors.toList());
    }

    public String getPrimera() {
        return mapaCadenas.keySet().stream()
            .min(String::compareTo)
            .orElse(null);
    }

    public String getUltima() {
        return mapaCadenas.values().stream()
            .max(String::compareTo)
            .orElse(null);
    }

    public Collection<String> getLlaves() {
        return mapaCadenas.keySet().stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
    }

    public int getCantidadCadenasDiferentes() {
        return (int) mapaCadenas.values().stream()
            .distinct()
            .count();
    }

    public void agregarCadena(String cadena) {
        String llave = new StringBuilder(cadena).reverse().toString();
        mapaCadenas.putIfAbsent(llave, cadena);
    }

    public void eliminarCadenaConLLave(String llave) {
        mapaCadenas.remove(llave);
    }

    public void eliminarCadenaConValor(String valor) {
        mapaCadenas.entrySet().removeIf(entry -> entry.getValue().equals(valor));
    }

    public void reiniciarMapaCadenas(List<Object> objetos) {
        mapaCadenas.clear();
        for (Object obj : objetos) {
            String cadena = obj.toString();
            String llave = new StringBuilder(cadena).reverse().toString();
            mapaCadenas.put(llave, cadena);
        }
    }

    public void volverMayusculas() {
        Map<String, String> nuevoMapa = new HashMap<>();
        for (Map.Entry<String, String> entry : mapaCadenas.entrySet()) {
            nuevoMapa.put(entry.getKey().toUpperCase(), entry.getValue());
        }
        mapaCadenas = nuevoMapa;
    }

    public boolean compararValores(String[] otroArreglo) {
        List<String> valores = new ArrayList<>(mapaCadenas.values());
        return valores.containsAll(List.of(otroArreglo));
    }
}
