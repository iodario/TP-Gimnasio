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

    public GestorSocios() {
        super();
        // Cargar socios desde JSON al crear el gestor
        cargarSociosDesdeJSON();
    }


    private void cargarSociosDesdeJSON() {
        List<Socio> sociosDesdeJSON = mapeoSocios();
        for (Socio socio : sociosDesdeJSON) {
            try {
                super.agregar(socio); // Agregar cada socio a la lista del gestor
            } catch (DatoInvalidoException e) {
                System.out.println("Error al cargar socio desde JSON: " + e.getMessage());
            }
        }
    }

    public static List<Socio> mapeoSocios() {
        List<Socio> lista = new ArrayList<>();

        try {
            // Leer el archivo JSON usando JSONUtiles
            JSONTokener tokener = JSONUtiles.leer("socios.json");

            if (tokener != null) {
                JSONArray array = new JSONArray(tokener);

                // Recorrer el array y mapear cada objeto
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonSocio = array.getJSONObject(i);

                    Socio socio = new Socio();

                    // Mapeo de campos básicos de Persona
                    socio.setNombre(jsonSocio.optString("nombre", ""));
                    socio.setDni(jsonSocio.optString("dni", ""));
                    socio.setTelefono(jsonSocio.optInt("telefono", 0));
                    socio.setEliminado(jsonSocio.optBoolean("eliminado", false));

                    // Mapeo de campos específicos de Socio
                    socio.setActivo(jsonSocio.optBoolean("activo", true));
                    socio.setFrecuenciaSemanal(jsonSocio.optInt("frecuenciaSemanal", 0));
                    socio.setObjetivo(jsonSocio.optString("objetivo", ""));

                    // Mapeo del Enum TipoMembresia
                    String membresiaStr = jsonSocio.optString("membresia", "BASICA");
                    try {
                        TipoMembresia membresia = TipoMembresia.valueOf(membresiaStr.toUpperCase());
                        socio.setMembresia(membresia);
                    } catch (IllegalArgumentException e) {
                        // Si el valor no coincide con el enum, usar valor por defecto
                        socio.setMembresia(TipoMembresia.BASICA);
                    }

                    lista.add(socio);
                }
                System.out.println("Se cargaron " + lista.size() + " socios desde el archivo JSON.");
            } else {
                System.out.println("No se pudo leer el archivo socios.json");
            }

        } catch (Exception e) {
            System.out.println("Error al leer o mapear socios.json: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    // METODOS

    // ==Buscar Socios==//
    public Socio buscarSocioPorDni(String dni) {
        return buscarPorClave(dni);
    }

    // ==Agregar Socios==//
    public void altaSocio(Socio s) throws DatoInvalidoException {
        super.agregar(s); // usa la lógica genérica (null, clave, duplicado)
    }

    //==Eliminar Socios==//
    public boolean bajaSocio(String dni) {
        return super.eliminarPorClave(dni);
    }

    //==Listar Socios==//
    public List<Socio> listarSocios() {
        return super.listar();
    }


    public JSONArray convertirSociosAJSON() {
        JSONArray array = new JSONArray();
        List<Socio> listaSocios = listarSocios();

        for (Socio socio : listaSocios) {
            JSONObject jsonSocio = new JSONObject();
            try {
                jsonSocio.put("nombre", socio.getNombre());
                jsonSocio.put("dni", socio.getDni());
                jsonSocio.put("telefono", socio.getTelefono());
                jsonSocio.put("eliminado", socio.isEliminado());
                jsonSocio.put("activo", socio.isActivo());
                jsonSocio.put("frecuenciaSemanal", socio.getFrecuenciaSemanal());
                jsonSocio.put("membresia", socio.getMembresia().name());
                jsonSocio.put("objetivo", socio.getObjetivo());

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            array.put(jsonSocio);
        }
        return array;
    }

    // ==Guardar Socios en JSON==//
    public void guardarSociosEnJSON() {
        try {
            JSONArray array = convertirSociosAJSON();
            JSONUtiles.grabar(array, "socios.json");
        } catch (Exception e) {
            System.out.println("Error al guardar socios en JSON: " + e.getMessage());
        }
    }
}
