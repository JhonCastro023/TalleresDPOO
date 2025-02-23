package uniandes.dpoo.aerolinea.tarifas;

import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public abstract class CalculadoraTarifas {
    public final double IMPUESTO = 0.28;
    private Cliente cliente;
    private Vuelo vuelo;
    
    public abstract int calcularTarifa (Vuelo vuelo);

    protected abstract int calcularCostoBase(Vuelo vuelo);

    protected abstract double calcularPorcentajeDescuento(Vuelo vuelo);

    protected double calcularImpuesto(Vuelo vuelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected int calcularDistanciaVuelo(Ruta ruta){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected int calcularValorImpuestos(int costoBase){
        throw new UnsupportedOperationException("Not supported yet.");
    }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
    
    
}
