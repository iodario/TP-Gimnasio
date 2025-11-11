package Gestores;

import Clases.Empleado;
import Clases.Entrenador;
import Clases.Recepcionista;
import Excepciones.DatoInvalidoException;
import ManejoJSON.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class GestorEmpleados extends Gestor<Empleado> {

    private static final String ARCHIVO_EMPLEADOS = "empleados.json";

    public GestorEmpleados() {
        super();
        cargarEmpleadosDesdeJSON();
    }

    private void cargarEmpleadosDesdeJSON() {
        List<Empleado> empleadosDesdeJSON = mapeoEmpleadosDesdeJSON();
        for (Empleado empleado : empleadosDesdeJSON) {
            try {
                super.agregar(empleado);
            } catch (DatoInvalidoException e) {
                System.out.println("Error al cargar empleado desde JSON: " + e.getMessage());
            }
        }
    }

    private List<Empleado> mapeoEmpleadosDesdeJSON() {
        List<Empleado> lista = new ArrayList<>();

        try {
            JSONTokener tokener = JSONUtiles.leer(ARCHIVO_EMPLEADOS);
            if (tokener != null) {
                JSONArray array = new JSONArray(tokener);

                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonEmpleado = array.getJSONObject(i);
                    Empleado empleado = mapearJSONaEmpleado(jsonEmpleado);
                    if (empleado != null) {
                        lista.add(empleado);
                    }
                }
                System.out.println("✓ Se cargaron " + lista.size() + " empleados desde " + ARCHIVO_EMPLEADOS);
            } else {
                System.out.println("ℹ No se encontró " + ARCHIVO_EMPLEADOS + ", se creará uno nuevo al guardar");
            }

        } catch (Exception e) {
            System.out.println("✗ Error al leer " + ARCHIVO_EMPLEADOS + ": " + e.getMessage());
        }

        return lista;
    }

    private Empleado mapearJSONaEmpleado(JSONObject jsonEmpleado) {
        String tipo = jsonEmpleado.optString("tipo", "").toLowerCase();

        // Campos comunes de Persona y Empleado
        String nombre = jsonEmpleado.optString("nombre", "");
        String dni = jsonEmpleado.optString("dni", "");
        int telefono = jsonEmpleado.optInt("telefono", 0);
        boolean eliminado = jsonEmpleado.optBoolean("eliminado", false);
        String legajo = jsonEmpleado.optString("legajo", "");
        double sueldo = jsonEmpleado.optDouble("sueldo", 0.0);

        // Crear el tipo específico de empleado
        switch (tipo) {
            case "entrenador":
                String especialidad = jsonEmpleado.optString("especialidad", "");
                return new Entrenador(nombre, dni, telefono, eliminado, legajo, sueldo, especialidad);

            case "recepcionista":
                return new Recepcionista(nombre, dni, telefono, eliminado, legajo, sueldo);

            default:
                System.out.println("Tipo de empleado desconocido: " + tipo);
                return null;
        }
    }

    // == Métodos de negocio == //
    public Empleado buscarEmpleadoPorLegajo(String legajo) {
        return buscarPorClave(legajo);
    }

    public void altaEmpleado(Empleado e) throws DatoInvalidoException {
        super.agregar(e);
    }

    public boolean bajaEmpleado(String legajo) {
        return super.eliminarPorClave(legajo);
    }

    public List<Empleado> listarEmpleados() {
        return super.listar();
    }

    // == Persistencia JSON == //
    public JSONArray convertirEmpleadosAJSON() {
        JSONArray array = new JSONArray();
        List<Empleado> listaEmpleados = listarEmpleados();

        for (Empleado empleado : listaEmpleados) {
            JSONObject jsonEmpleado = mapearEmpleadoAJSON(empleado);
            if (jsonEmpleado != null) {
                array.put(jsonEmpleado);
            }
        }
        return array;
    }

    private JSONObject mapearEmpleadoAJSON(Empleado empleado) {
        JSONObject jsonEmpleado = new JSONObject();
        try {
            // Campos comunes de Persona
            jsonEmpleado.put("nombre", empleado.getNombre());
            jsonEmpleado.put("dni", empleado.getDni());
            jsonEmpleado.put("telefono", empleado.getTelefono());
            jsonEmpleado.put("eliminado", empleado.isEliminado());

            // Campos comunes de Empleado
            jsonEmpleado.put("legajo", empleado.getLegajo());
            jsonEmpleado.put("sueldo", empleado.getSueldo());

            // Campos específicos según el tipo
            if (empleado instanceof Entrenador) {
                Entrenador entrenador = (Entrenador) empleado;
                jsonEmpleado.put("tipo", "entrenador");
                jsonEmpleado.put("especialidad", entrenador.getEspecialidad());
            } else if (empleado instanceof Recepcionista) {
                jsonEmpleado.put("tipo", "recepcionista");
            }

        } catch (JSONException e) {
            System.out.println("Error al mapear empleado a JSON: " + e.getMessage());
            return null;
        }
        return jsonEmpleado;
    }

    public void guardarEmpleadosEnJSON() {
        try {
            JSONArray array = convertirEmpleadosAJSON();
            JSONUtiles.grabar(array, ARCHIVO_EMPLEADOS);
        } catch (Exception e) {
            System.out.println("✗ Error al guardar empleados en JSON: " + e.getMessage());
        }
    }
}