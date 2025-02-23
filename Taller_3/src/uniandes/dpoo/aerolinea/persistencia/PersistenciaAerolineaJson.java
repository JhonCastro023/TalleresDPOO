package uniandes.dpoo.aerolinea.persistencia;

import uniandes.dpoo.aerolinea.modelo.Aerolinea;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {

	@Override
	public void cargarAerolinea(String archivo, Aerolinea aerolinea) {
		System.out.println("Cargando aerolínea desde archivo JSON: " + archivo);

	}

	@Override
	public void salvarAerolinea(String archivo, Aerolinea aerolinea) {
		System.out.println("Guardando aerolínea en archivo JSON: " + archivo);

	}

}
