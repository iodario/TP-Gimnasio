package Gestores;

import Clases.Ejercicio;
import Clases.Rutina;
import Clases.Socio;
import Excepciones.DatoInvalidoException;
import ManejoJSON.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class GestorRutinas extends Gestor<Rutina> {

    private GestorSocios gestorSocios; // Referencia al gestor de socios

    public GestorRutinas(GestorSocios gestorSocios) {
        super();
        this.gestorSocios = gestorSocios;
        // Cargar rutinas desde JSON al crear el gestor
        cargarRutinasDesdeJSON();
    }

    private void cargarRutinasDesdeJSON() {
        List<Rutina> rutinasDesdeJSON = mapeoRutinas();
        for (Rutina rutina : rutinasDesdeJSON) {
            try {
                super.agregar(rutina); // Agregar cada rutina a la lista del gestor
            } catch (DatoInvalidoException e) {
                System.out.println("Error al cargar rutina desde JSON: " + e.getMessage());
            }
        }
    }

    public List<Rutina> mapeoRutinas() {
        List<Rutina> lista = new ArrayList<>();

        try {
            // Leer el archivo JSON usando JSONUtiles
            JSONTokener tokener = JSONUtiles.leer("rutinas.json");

            if (tokener != null) {
                JSONArray array = new JSONArray(tokener);

                // Recorrer el array y mapear cada objeto
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonRutina = array.getJSONObject(i);

                    Rutina rutina = new Rutina();

                    // Mapeo de campos básicos de Rutina
                    rutina.setNombre(jsonRutina.optString("nombre", ""));
                    rutina.setDescripcion(jsonRutina.optString("descripcion", ""));
                    rutina.setClaveSocio(jsonRutina.optString("claveSocio", ""));
                    rutina.setEntrenador(jsonRutina.optString("entrenador", ""));

                    // Mapeo de la lista de ejercicios
                    if (jsonRutina.has("ejercicios")) {
                        JSONArray ejerciciosArray = jsonRutina.getJSONArray("ejercicios");
                        List<Ejercicio> ejercicios = new ArrayList<>();

                        for (int j = 0; j < ejerciciosArray.length(); j++) {
                            JSONObject jsonEjercicio = ejerciciosArray.getJSONObject(j);

                            Ejercicio ejercicio = new Ejercicio();
                            ejercicio.setNombre(jsonEjercicio.optString("nombre", ""));
                            ejercicio.setDescripcion(jsonEjercicio.optString("descripcion", ""));
                            ejercicio.setRepeticiones(jsonEjercicio.optInt("repeticiones", 0));
                            ejercicio.setSeries(jsonEjercicio.optInt("series", 0));

                            ejercicios.add(ejercicio);
                        }
                        rutina.setEjercicios(ejercicios);
                    }

                    lista.add(rutina);
                }
                System.out.println("Se cargaron " + lista.size() + " rutinas desde el archivo JSON.");
            } else {
                System.out.println("No se pudo leer el archivo rutinas.json");
            }

        } catch (Exception e) {
            System.out.println("Error al leer o mapear rutinas.json: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void agregar(Rutina rutina) throws DatoInvalidoException {
        if (rutina == null) {
            throw new DatoInvalidoException("La Rutina no existe, NO fue agregada");
        }

        String claveSocio = rutina.getClaveSocio();
        Socio socio = gestorSocios.buscarSocioPorDni(claveSocio);
        if (socio == null) {
            throw new DatoInvalidoException("No existe un socio con la clave: " + claveSocio);
        }

        super.agregar(rutina);
        System.out.println("\n La Rutina fue agregada correctamente para el socio: " + socio.getNombre());
    }

    public List<Rutina> buscarRutinasPorSocio(String claveSocio) {
        List<Rutina> resultado = new ArrayList<>();
        if (claveSocio == null || claveSocio.trim().isEmpty()) return resultado;

        for (Rutina r : listar()) {
            if (r.getClaveSocio().equalsIgnoreCase(claveSocio)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public void mostrarRutinasPorSocio(String claveSocio) {
        List<Rutina> lista = buscarRutinasPorSocio(claveSocio);
        if (lista.isEmpty()) {
            System.out.println("El socio con la clave " + claveSocio + " no tiene rutinas asignadas.");
        } else {
            System.out.println("\n ~ Rutinas del socio " + claveSocio + ": \n");
            for (Rutina r : lista) {
                System.out.println("- " + r.toString());
            }
        }
    }

    // Metodo para obtener rutinas por entrenador
    public List<Rutina> buscarRutinasPorEntrenador(String entrenador) {
        List<Rutina> resultado = new ArrayList<>();
        if (entrenador == null || entrenador.trim().isEmpty()) return resultado;

        for (Rutina r : listar()) {
            if (r.getEntrenador().equalsIgnoreCase(entrenador)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    // FUNCIONES

    // ==Buscar Rutina==//
    public Rutina buscarRutinaPorClave(String clave) {
        return buscarPorClave(clave);
    }

    // ==Agregar Rutina==//
    public void altaRutina(Rutina r) throws DatoInvalidoException {
        super.agregar(r);
    }

    //==Eliminar Rutina==//
    public boolean bajaRutina(String clave) {
        return super.eliminarPorClave(clave);
    }


    // ==Metodo para mostrar todas las rutinas con descripción corta==
    public void listarRutinas() {
        List<Rutina> rutinas = super.listar();

        if (rutinas.isEmpty()) {
            System.out.println("\n--- No hay rutinas registradas en el sistema ---");
            return;
        }

        System.out.println("\n=== LISTADO COMPLETO DE RUTINAS ===");
        System.out.println("Total de rutinas: " + rutinas.size());
        System.out.println("===================================");

        for (int i = 0; i < rutinas.size(); i++) {
            Rutina rutina = rutinas.get(i);
            System.out.println("[" + (i + 1) + "] " + rutina.descripcionCorta());
        }
    }

}