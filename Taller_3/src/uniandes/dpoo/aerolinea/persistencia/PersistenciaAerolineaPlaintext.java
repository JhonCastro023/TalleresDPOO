package uniandes.dpoo.aerolinea.persistencia;

import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

import java.io.*;
import java.util.List;

public class PersistenciaAerolineaPlaintext implements IPersistenciaAerolinea {

    /**
     * Carga la información de una aerolínea desde un archivo de texto plano.
     * El archivo debe seguir el formato:
     * RUTA,origen,destino
     * VUELO,origen,destino,fecha
     *
     * @param archivo   La ruta del archivo a cargar.
     * @param aerolinea La instancia de Aerolinea a la que se cargarán los datos.
     */
	    @Override
	    public void cargarAerolinea(String archivo, Aerolinea aerolinea) {
	        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                String[] partes = linea.split(",");
	                switch (partes[0]) {
	                    case "RUTA":
	                        if (partes.length == 3) {
	                            String origen = partes[1];
	                            String destino = partes[2];
	                            aerolinea.agregarRuta(new Ruta(origen, null, null, destino, destino, 0));
	                        }
	                        break;
	
	                    case "VUELO":
	                        if (partes.length == 4) {
	                            String origen = partes[1];
	                            String destino = partes[2];
	                            String fecha = partes[3];
	                            aerolinea.agregarVuelo(new Vuelo(new Ruta(origen, null, null, destino, fecha, 0), fecha, null));
	                        }
	                        break;
	
	                    default:
	                        System.err.println("Línea no reconocida: " + linea);
	                }
	            }
	        } catch (IOException e) {
	            System.err.println("Error al cargar el archivo: " + e.getMessage());
	        }
	    }

    /**
     * Guarda la información de una aerolínea en un archivo de texto plano.
     * El archivo seguirá el formato:
     * RUTA,origen,destino
     * VUELO,origen,destino,fecha
     *
     * @param archivo   La ruta del archivo donde se guardarán los datos.
     * @param aerolinea La instancia de Aerolinea cuyas rutas y vuelos se guardarán.
     */
    @Override
    public void salvarAerolinea(String archivo, Aerolinea aerolinea) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            List<Ruta> rutas = (List<Ruta>) aerolinea.getRutas();
            for (Ruta ruta : rutas) {
                bw.write("RUTA," + ruta.getOrigen() + "," + ruta.getDestino());
                bw.newLine();
            }

            List<Vuelo> vuelos = (List<Vuelo>) aerolinea.getVuelos();
            for (Vuelo vuelo : vuelos) {
                Ruta ruta = vuelo.getRuta();
                bw.write("VUELO," + ruta.getOrigen() + "," + ruta.getDestino() + "," + vuelo.getFecha());
                bw.newLine();
            }

            System.out.println("Aerolínea guardada correctamente en: " + archivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}

