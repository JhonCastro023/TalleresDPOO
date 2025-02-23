package uniandes.dpoo.aerolinea.modelo.cliente;

public class ClienteNatural extends Cliente {
    public static final String NATURAL = "Natural";
    private String nombre;

    public ClienteNatural(String nombre) {
        super(NATURAL);
        this.nombre = nombre;
    }

    @Override
    public String getTipoCliente() {
        return NATURAL;
    }

    @Override
    public String getIdentificador() {
        return nombre;
    }

    @Override
    public int getTarifaAlta() {
        return 200000; 
    }

    @Override
    public int getTarifaBaja() {
        return 150000; 
    }
}

