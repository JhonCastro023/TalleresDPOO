package uniandes.dpoo.aerolinea.modelo.cliente;

import org.json.JSONObject;

public class ClienteCorporativo extends Cliente {
    public static final String CORPORATIVO = "Corporativo";
    public static final int GRANDE = 1;
    public static final int MEDIANA = 2;
    public static final int PEQUENA = 3;

    private String nombreEmpresa;
    private int tamanoEmpresa;

    public ClienteCorporativo(String nombreEmpresa, int tamanoEmpresa) {
        super(CORPORATIVO); 
        this.nombreEmpresa = nombreEmpresa;
        this.tamanoEmpresa = tamanoEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public int getTamanoEmpresa() {
        return tamanoEmpresa;
    }

    public static ClienteCorporativo cargarDesdeJSON(JSONObject cliente) {
        String nombreEmpresa = cliente.getString("nombreEmpresa");
        int tam = cliente.getInt("tamanoEmpresa");
        return new ClienteCorporativo(nombreEmpresa, tam);
    }

    public JSONObject salvarEnJSON() {
        JSONObject jobject = new JSONObject();
        jobject.put("nombreEmpresa", this.nombreEmpresa);
        jobject.put("tamanoEmpresa", this.tamanoEmpresa);
        jobject.put("tipo", CORPORATIVO);
        return jobject;
    }

    @Override
    public String getTipoCliente() {
        return CORPORATIVO;
    }

    @Override
    public String getIdentificador() {
        return nombreEmpresa;
    }

    @Override
    public int getTarifaAlta() {
        switch (tamanoEmpresa) {
            case GRANDE:
                return 150000;
            case MEDIANA:
                return 200000;
            case PEQUENA:
                return 250000;
            default:
                return 0;
        }
    }

    @Override
    public int getTarifaBaja() {
        switch (tamanoEmpresa) {
            case GRANDE:
                return 100000;
            case MEDIANA:
                return 150000;
            case PEQUENA:
                return 200000;
            default:
                return 0;
        }
    }


}

