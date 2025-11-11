package Gestores;

import Clases.Socio;
import Enumeradores.TipoMembresia;
import Excepciones.DatoInvalidoException;
import ManejoJSON.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class GestorSocios extends Gestor<Socio> {

    private static final String ARCHIVO_SOCIOS = "socios.json";

    public GestorSocios() {
        super();
        cargarSociosDesdeJSON();
    }

    private void cargarSociosDesdeJSON() {
        List<Socio> sociosDesdeJSON = mapeoSociosDesdeJSON();
        for (Socio socio : sociosDesdeJSON) {
            try {
                super.agregar(socio);
            } catch (DatoInvalidoException e) {
                System.out.println("Error al cargar socio desde JSON: " + e.getMessage());
            }
        }
    }

    private List<Socio> mapeoSociosDesdeJSON() {
        List<Socio> lista = new ArrayList<>();

        try {
            JSONTokener tokener = JSONUtiles.leer(ARCHIVO_SOCIOS);
            if (tokener != null) {
                JSONArray array = new JSONArray(tokener);

                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonSocio = array.getJSONObject(i);
                    Socio socio = mapearJSONaSocio(jsonSocio);
                    lista.add(socio);
                }
                System.out.println("✓ Se cargaron " + lista.size() + " socios desde " + ARCHIVO_SOCIOS);
            } else {
                System.out.println("ℹ No se encontró " + ARCHIVO_SOCIOS + ", se creará uno nuevo al guardar");
            }

        } catch (Exception e) {
            System.out.println("✗ Error al leer " + ARCHIVO_SOCIOS + ": " + e.getMessage());
        }

        return lista;
    }

    private Socio mapearJSONaSocio(JSONObject jsonSocio) {
        Socio socio = new Socio();

        // Mapeo de campos de Persona
        socio.setNombre(jsonSocio.optString("nombre", ""));
        socio.setDni(jsonSocio.optString("dni", ""));
        socio.setTelefono(jsonSocio.optInt("telefono", 0));
        socio.setEliminado(jsonSocio.optBoolean("eliminado", false));

        // Mapeo de campos específicos de Socio
        socio.setActivo(jsonSocio.optBoolean("activo", true));
        socio.setFrecuenciaSemanal(jsonSocio.optInt("frecuenciaSemanal", 0));
        socio.setObjetivo(jsonSocio.optString("objetivo", ""));

        // Mapeo del Enum TipoMembresia TODO: arreglar error
        /*
        String membresiaStr = jsonSocio.optString("membresia", "BASICA");
        try {
            TipoMembresia membresia = TipoMembresia.valueOf(membersiaStr.toUpperCase());
            socio.setMembresia(membresia);
        } catch (IllegalArgumentException e) {
            socio.setMembresia(TipoMembresia.BASICA);
        }
        */
        return socio;
    }

    // == Métodos de negocio == //
    public Socio buscarSocioPorDni(String dni) {
        return buscarPorClave(dni);
    }

    public void altaSocio(Socio s) throws DatoInvalidoException {
        super.agregar(s);
    }

    public boolean bajaSocio(String dni) {
        return super.eliminarPorClave(dni);
    }

    public List<Socio> listarSocios() {
        return super.listar();
    }

    // == Persistencia JSON == //
    public JSONArray convertirSociosAJSON() {
        JSONArray array = new JSONArray();
        List<Socio> listaSocios = listarSocios();

        for (Socio socio : listaSocios) {
            JSONObject jsonSocio = mapearSocioAJSON(socio);
            array.put(jsonSocio);
        }
        return array;
    }

    private JSONObject mapearSocioAJSON(Socio socio) {
        JSONObject jsonSocio = new JSONObject();
        try {
            // Campos de Persona
            jsonSocio.put("nombre", socio.getNombre());
            jsonSocio.put("dni", socio.getDni());
            jsonSocio.put("telefono", socio.getTelefono());
            jsonSocio.put("eliminado", socio.isEliminado());

            // Campos específicos de Socio
            jsonSocio.put("activo", socio.isActivo());
            jsonSocio.put("frecuenciaSemanal", socio.getFrecuenciaSemanal());
            jsonSocio.put("membresia", socio.getMembresia().name());
            jsonSocio.put("objetivo", socio.getObjetivo());

        } catch (JSONException e) {
            System.out.println("Error al mapear socio a JSON: " + e.getMessage());
        }
        return jsonSocio;
    }

    public void guardarSociosEnJSON() {
        try {
            JSONArray array = convertirSociosAJSON();
            JSONUtiles.grabar(array, ARCHIVO_SOCIOS);
        } catch (Exception e) {
            System.out.println("✗ Error al guardar socios en JSON: " + e.getMessage());
        }
    }
}