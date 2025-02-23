package uniandes.dpoo.aerolinea.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {

    private static final int COSTO_POR_KM_NATURAL = 600;
    private static final int COSTO_POR_KM_CORPORATIVO = 800;
    private static final double DESCUENTO_PEQ = 0.02;      
    private static final double DESCUENTO_MEDIANAS = 0.1;  
    private static final double DESCUENTO_GRANDES = 0.2;   

    /**
     * Calcula el costo base de un vuelo dependiendo del tipo de cliente y la distancia.
     */
    @Override
    protected int calcularCostoBase(Vuelo vuelo) {
        Cliente cliente = vuelo.getCliente();
        int distancia = vuelo.getRuta().getDistancia();
        boolean esCorporativo = cliente instanceof ClienteCorporativo;

        return esCorporativo ? distancia * COSTO_POR_KM_CORPORATIVO : distancia * COSTO_POR_KM_NATURAL;
    }

    /**
     * Calcula el porcentaje de descuento dependiendo de la cantidad de tiquetes comprados.
     */
    @Override
    protected double calcularPorcentajeDescuento(Vuelo vuelo) {
        int numTiquetes = vuelo.getNumeroTiquetes();
        if (numTiquetes >= 30) {
            return DESCUENTO_GRANDES;
        } else if (numTiquetes >= 10) {
            return DESCUENTO_MEDIANAS;
        } else {
            return DESCUENTO_PEQ;
        }
    }

    /**
     * Calcula la tarifa final aplicando el costo base y el descuento correspondiente.
     */
    @Override
    public int calcularTarifa(Vuelo vuelo) {
        int costoBase = calcularCostoBase(vuelo);
        double descuento = calcularPorcentajeDescuento(vuelo);
        return (int) (costoBase * (1 - descuento));
    }
}


