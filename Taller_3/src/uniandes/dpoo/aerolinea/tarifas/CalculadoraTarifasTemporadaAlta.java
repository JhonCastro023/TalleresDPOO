package uniandes.dpoo.aerolinea.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {
    protected final int COSTO_POR_KM = 1000;

    public int calcularCostoBase(Vuelo vuelo, Cliente cliente ){
        int distancia = calcularDistanciaVuelo(vuelo.getRuta());
        int costoBase = distancia * COSTO_POR_KM;
        return costoBase;
    }

    public double calcularPorcentajeDescuento(Vuelo vuelo, Cliente cliente){
        return 0;
    }

    @Override
    public int calcularTarifa(Vuelo vuelo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularTarifa'");
    }

    @Override
    protected int calcularCostoBase(Vuelo vuelo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularCostoBase'");
    }

    @Override
    protected double calcularPorcentajeDescuento(Vuelo vuelo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularPorcentajeDescuento'");
    }
}
