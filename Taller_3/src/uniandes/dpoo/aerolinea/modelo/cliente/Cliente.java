package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
    List<Tiquete> tiquetesSinUsar;
    List<Tiquete> tiquetesUsados;

    public Cliente(String tipoCliente) {
        this.setTipoCliente(tipoCliente);
    }

    public abstract String getTipoCliente();
    public abstract String getIdentificador();

    public void agregarTiquete(Tiquete tiquete) {
        tiquetesSinUsar.add(tiquete);
    }
    public int calcularValorTotalTiquetes() {
        int total = 0;
        for (Tiquete tiquete : tiquetesSinUsar) {
            total += tiquete.getTarifa();
        }
        return total;
    }
    public void usarTiquetes(Vuelo vuelo) {
        for (Tiquete tiquete : tiquetesSinUsar) {
            if (tiquete.getVuelo().equals(vuelo)) {
                tiquete.marcarComoUsado();
                tiquetesUsados.add(tiquete);
                tiquetesSinUsar.remove(tiquete);
            }
        }
    }

	public void setTipoCliente(String tipoCliente) {
	}
	public abstract int getTarifaAlta();
	public abstract int getTarifaBaja();
}
