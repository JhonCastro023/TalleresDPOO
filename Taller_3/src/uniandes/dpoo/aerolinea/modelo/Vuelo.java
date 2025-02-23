package uniandes.dpoo.aerolinea.modelo;

import java.util.Map;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
    private String fecha;
    private Avion avion;
    private Map<String, Tiquete> tiquetes;
    private Ruta ruta;
    private Cliente cliente;
    private int numeroTiquetes;
    
    public Vuelo(Ruta ruta, String fecha, Avion avion) {
        this.fecha = fecha;
        this.avion = avion;
        this.ruta = ruta;
    }


	public String getFecha() {
        return fecha;
    }

    public Avion getAvion() {
        return avion;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public Map<String, Tiquete> getTiquetes() {
        return tiquetes;
    }
    public int getNumeroTiquetes() {
        return numeroTiquetes;
    }

    public void agregarTiquete(Tiquete tiquete) {
        tiquetes.put(tiquete.getCodigo(), tiquete);
    }

    public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad)
    {
        int tarifaTotal = 0;
        for (int i = 0; i < cantidad; i++) {
            Tiquete tiquete = new Tiquete("T" + i, this, cliente, calculadora.calcularTarifa(this));
            tarifaTotal += tiquete.getTarifa();
            agregarTiquete(tiquete);
            cliente.agregarTiquete(tiquete);
        }
        return tarifaTotal;
    }

    @Override
    public boolean equals(Object vuelo) {
        return fecha.equals(((Vuelo) vuelo).getFecha()) && avion.equals(((Vuelo) vuelo).getAvion()) && ruta.equals(((Vuelo) vuelo).getRuta());
    }

}
