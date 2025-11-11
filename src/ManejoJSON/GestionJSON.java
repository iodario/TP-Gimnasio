package ManejoJSON;

import Clases.*;
import Excepciones.DatoInvalidoException;
import Gestores.Gestor;
import Gestores.GestorEmpleados;
import Gestores.GestorSocios;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileWriter;
import java.io.IOException;


public class GestionJSON {


    // LECTURA

    public static void mapeoGimnasio(
            String archivo,
            GestorSocios socios,
            GestorEmpleados empleados,
            Gestor<ClaseGrupal> clases,
            Gestor<Reserva> reservas,
            Gestor<Pago> pagos,
            Gestor<Rutina> rutinas) {

        try {
            JSONTokener tokener = JSONUtiles.leer("gimnasio.json");
            if (tokener == null) return;

            JSONObject root = new JSONObject(tokener);
            JSONObject g = root.getJSONObject("gimnasio");

            // SOCIOS
            if (g.has("socios")) {
                JSONArray jSocios = g.getJSONArray("socios");
                for (int i = 0; i < jSocios.length(); i++) {
                    Socio s = mapeoSocio(jSocios.getJSONObject(i));
                    socios.agregar(s);
                }
            }

            // EMPLEADOS
            if (g.has("empleados")) {
                JSONArray jEmps = g.getJSONArray("empleados");
                for (int i = 0; i < jEmps.length(); i++) {
                    Empleado e = mapeoEmpleado(jEmps.getJSONObject(i));
                    empleados.agregar(e);
                }
            }

            // CLASES
            if (g.has("clasesGrupales")) {
                JSONArray jClases = g.getJSONArray("clasesGrupales");
                for (int i = 0; i < jClases.length(); i++) {
                    ClaseGrupal c = mapeoClaseGrupal(jClases.getJSONObject(i));
                    clases.agregar(c);
                }
            }

            // RESERVAS
            if (g.has("reservas")) {
                JSONArray jReservas = g.getJSONArray("reservas");
                for (int i = 0; i < jReservas.length(); i++) {
                    Reserva r = mapeoReserva(jReservas.getJSONObject(i));
                    reservas.agregar(r);
                }
            }

            // PAGOS
            if (g.has("pagos")) {
                JSONArray jPagos = g.getJSONArray("pagos");
                for (int i = 0; i < jPagos.length(); i++) {
                    Pago p = mapeoPago(jPagos.getJSONObject(i));
                    pagos.agregar(p);
                }
            }

            // RUTINAS (con ejercicios adentro)
            if (g.has("rutinas")) {
                JSONArray jRutinas = g.getJSONArray("rutinas");
                for (int i = 0; i < jRutinas.length(); i++) {
                    Rutina r = mapeoRutina(jRutinas.getJSONObject(i));
                    rutinas.agregar(r);
                }
            }

        } catch (JSONException ex) {
            throw new RuntimeException("Error parseando JSON: " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Error cargando datos: " + ex.getMessage());
        }
    }


    //  Socio -----------------------------------------------------------


    //MapeoJSON
    public static Socio mapeoSocio(JSONObject jSocio) {
        Socio s = new Socio();
        try {
            // OBLIGATORIOS con getXxx()  pueden lanzar JSONException
            s.setNombre(jSocio.getString("nombre"));
            s.setDni(jSocio.getString("dni"));

            // OPCIONALES con has()

            s.setTelefono(jSocio.has("telefono") ? jSocio.getInt("telefono") : 0);
            s.setEliminado(jSocio.optBoolean("eliminado", false));
            s.setObjetivo(jSocio.optString("objetivo", ""));
            s.setActivo(jSocio.optBoolean("activo", true));
            s.setFrecuenciaSemanal(jSocio.optInt("frecuenciaSemanal", 0));

            if (jSocio.has("membresia")) {
                String tipo = jSocio.getString("membresia"); // obligatorio si existe la clave
                s.setMembresia(Enumeradores.TipoMembresia.valueOf(tipo));
            }

        } catch (org.json.JSONException e) {
            throw new RuntimeException("Error mapeando Socio: " + e.getMessage(), e);
        }
        return s;
    }


    //  Empleado (Entrenador/Recepcionista) -----------------------------


    public static Empleado mapeoEmpleado(JSONObject j) {
        Empleado e;

        String tipo = j.optString("tipo", "RECEPCIONISTA").trim().toUpperCase();
        if ("ENTRENADOR".equals(tipo)) {
            Entrenador ent = new Entrenador();
            ent.setEspecialidad(j.optString("especialidad", "").trim());
            e = ent;
        } else {
            e = new Recepcionista();
        }

        try {
            e.setNombre(j.optString("nombre", "").trim());
            e.setDni(j.optString("dni", "").trim());
            e.setTelefono(j.optInt("telefono", 0));
            e.setEliminado(j.optBoolean("eliminado", false));

            e.setLegajo(j.optString("legajo", "").trim());
            e.setSueldo(j.optDouble("sueldo", 0.0));
            return e;

        } catch (DatoInvalidoException ex) {
            // mensaje más claro para debug
            throw new RuntimeException(
                    "Datos inválidos al mapear Empleado (legajo=" + j.optString("legajo", "?") + "): " + ex.getMessage(), ex
            );
        }
    }


    //  ClaseGrupal -----------------------------------------------------
    public static ClaseGrupal mapeoClaseGrupal(JSONObject j) {
        ClaseGrupal c = new ClaseGrupal();
        try {
            c.setId(j.optString("id", ""));               // String (tiene setId)
            c.setNombre(j.optString("nombre", ""));
            c.setActividad(j.optString("actividad", "")); // en tu clase es String
            c.setDia(j.optString("dia", ""));
            c.setHora(j.optString("hora", ""));
            c.setCupoMax(j.optInt("cupoMax", 0));
            c.setEntrenadorLegajo(j.optString("entrenadorLegajo", ""));

            // Reservas anidadas dentro de la clase (opcional)
            if (j.has("reservas")) {
                JSONArray jr = j.getJSONArray("reservas");
                for (int i = 0; i < jr.length(); i++) {
                    Reserva r = mapeoReserva(jr.getJSONObject(i));
                    c.getReserva().add(r); // tu getter es getReserva()
                }
            }

        } catch (JSONException ex) {
            throw new RuntimeException("Error mapeando ClaseGrupal: " + ex.getMessage());
        }
        return c;
    }

    //  Reserva ---------------------------------------------------------
    public static Reserva mapeoReserva(JSONObject j) {
        // Reserva(String id, String socioDni, String claseId)
        String id = j.optString("id", "");
        String socioDni = j.optString("socioDni", "");
        String claseId = j.optString("claseId", "");
        Reserva r = new Reserva(id, socioDni, claseId);
        // Activa (si existe)
        if (j.has("activa")) {

        }
        return r;
    }

    // ---- Pago ------------------------------------------------------------
    public static Pago mapeoPago(JSONObject j) {
        // Pago(String id, String medioDePago, double monto, String periodo, String socioDni)
        String id = j.optString("id", "");
        String medio = j.optString("medioDePago", "EFECTIVO");
        double monto = j.optDouble("monto", 0.0);
        String periodo = j.optString("periodo", "");
        String socioDni = j.optString("socioDni", "");
        return new Pago(id, medio, monto, periodo, socioDni);
    }

    // ---- Rutina + Ejercicios --------------------------------------------
    public static Rutina mapeoRutina(JSONObject j) {
        Rutina r = new Rutina();
        r.setNombre(j.optString("nombre", ""));
        r.setDescripcion(j.optString("descripcion", ""));
        r.setClaveSocio(j.optString("claveSocio", "")); // dni del socio dueño
        r.setEntrenador(j.optString("entrenador", "")); // nombre o legajo del entrenador

        if (j.has("ejercicios")) {
            JSONArray je = null;
            try {
                je = j.getJSONArray("ejercicios");
                for (int i = 0; i < je.length(); i++) {
                    r.getEjercicios().add(mapeoEjercicio(je.getJSONObject(i)));
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }
        return r;
    }

    public static Ejercicio mapeoEjercicio(JSONObject j) {
        Ejercicio e = new Ejercicio();
        e.setNombre(j.optString("nombre", ""));
        e.setDescripcion(j.optString("descripcion", ""));
        e.setSeries(j.optInt("series", 0));
        e.setRepeticiones(j.optInt("repeticiones", 0));
        return e;
    }


    // ESCRITURA
    // ==========================================================
    // Construye el JSONObject raíz

    public static JSONObject aJSON(
            String nombreGimnasio,
            String direccion,
            String telefono,
            GestorSocios socios,
            GestorEmpleados empleados,
            Gestor<ClaseGrupal> clases,
            Gestor<Reserva> reservas,
            Gestor<Pago> pagos,
            Gestor<Rutina> rutinas) {

        try {
            JSONObject root = new JSONObject();
            JSONObject g = new JSONObject();

            g.put("nombre", nombreGimnasio);
            g.put("direccion", direccion);
            g.put("telefono", telefono);

            // SOCIOS
            JSONArray jSocios = new JSONArray();
            for (Socio s : socios.listarSocios()) {
                JSONObject js = new JSONObject();
                js.put("nombre", s.getNombre());
                js.put("dni", s.getDni());
                js.put("telefono", s.getTelefono());
                js.put("eliminado", s.isEliminado());

                js.put("objetivo", s.getObjetivo());
                js.put("activo", s.isActivo());
                js.put("frecuenciaSemanal", s.getFrecuenciaSemanal());
                js.put("membresia", (s.getMembresia() == null) ? "BASICA" : s.getMembresia().name());

                jSocios.put(js);
            }
            g.put("socios", jSocios);

            // EMPLEADOS
            JSONArray jEmps = new JSONArray();
            for (Empleado e : empleados.listarEmpleados()) {
                JSONObject je = new JSONObject();

                String tipo = (e instanceof Entrenador) ? "ENTRENADOR" : "RECEPCIONISTA";
                je.put("tipo", tipo);

                je.put("nombre", e.getNombre());
                je.put("dni", e.getDni());
                je.put("telefono", e.getTelefono());
                je.put("eliminado", e.isEliminado());
                je.put("legajo", e.getLegajo());
                je.put("sueldo", e.getSueldo());

                if (e instanceof Entrenador) {
                    je.put("especialidad", ((Entrenador) e).getEspecialidad());
                }
                jEmps.put(je);
            }
            g.put("empleados", jEmps);

            // CLASES
            JSONArray jClases = new JSONArray();
            for (ClaseGrupal c : clases.listar()) {
                JSONObject jc = new JSONObject();
                jc.put("id", c.getId());
                jc.put("nombre", c.getNombre());
                jc.put("actividad", c.getActividad());
                jc.put("dia", c.getDia());
                jc.put("hora", c.getHora());
                jc.put("cupoMax", c.getCupoMax());
                jc.put("entrenadorLegajo", c.getEntrenadorLegajo());

                // Reservas anidadas (si quisieras emitirlas dentro de la clase)
                JSONArray jr = new JSONArray();
                for (Reserva r : c.getReserva()) {
                    JSONObject jres = new JSONObject();
                    jres.put("id", r.getClave()); // id de Reserva = getClave()
                    jres.put("socioDni", r.getSocioDni());
                    jres.put("claseId", r.getClaseId());
                    jres.put("activa", r.isActiva());
                    jr.put(jres);
                }
                jc.put("reservas", jr);

                jClases.put(jc);
            }
            g.put("clasesGrupales", jClases);

            // RESERVAS (nivel raíz, opcional si no las anidás arriba)
            JSONArray jReservas = new JSONArray();
            for (Reserva r : reservas.listar()) {
                JSONObject jr = new JSONObject();
                jr.put("id", r.getClave());
                jr.put("socioDni", r.getSocioDni());
                jr.put("claseId", r.getClaseId());
                jr.put("activa", r.isActiva());
                jReservas.put(jr);
            }
            g.put("reservas", jReservas);

            // PAGOS
            JSONArray jPagos = new JSONArray();
            for (Pago p : pagos.listar()) {
                JSONObject jp = new JSONObject();
                jp.put("id", p.getClave()); // id
                jp.put("socioDni", p.getSocioDni());
                jp.put("periodo", p.getPeriodo());
                jp.put("monto", p.getMonto());
                jp.put("medioDePago", p.getMedioDePago());
                jPagos.put(jp);
            }
            g.put("pagos", jPagos);

            // RUTINAS (con ejercicios)
            JSONArray jRutinas = new JSONArray();
            for (Rutina r : rutinas.listar()) {
                JSONObject jr = new JSONObject();
                jr.put("nombre", r.getNombre());
                jr.put("descripcion", r.getDescripcion());
                jr.put("claveSocio", r.getClaveSocio());
                jr.put("entrenador", r.getEntrenador());

                JSONArray je = new JSONArray();
                for (Ejercicio e : r.getEjercicios()) {
                    JSONObject jex = new JSONObject();
                    jex.put("nombre", e.getNombre());
                    jex.put("descripcion", e.getDescripcion());
                    jex.put("series", e.getSeries());
                    jex.put("repeticiones", e.getRepeticiones());
                    je.put(jex);
                }
                jr.put("ejercicios", je);

                jRutinas.put(jr);
            }
            g.put("rutinas", jRutinas);

            root.put("gimnasio", g);
            return root;

        } catch (JSONException ex) {
            throw new RuntimeException("Error generando JSON: " + ex.getMessage());
        }
    }

    /** Persiste el objeto JSON a disco (sin depender de JSONUtiles.grabar(JSONArray)). */
    public static void guardarGimnasio(
            String archivo,
            String nombreGimnasio,
            String direccion,
            String telefono,
            GestorSocios socios,
            GestorEmpleados empleados,
            Gestor<ClaseGrupal> clases,
            Gestor<Reserva> reservas,
            Gestor<Pago> pagos,
            Gestor<Rutina> rutinas) {

        JSONObject root = aJSON(nombreGimnasio, direccion, telefono, socios, empleados, clases, reservas, pagos, rutinas);

        try (FileWriter fw = new FileWriter(archivo)) {
            fw.write(root.toString(2));
        } catch (IOException | JSONException e) {
            throw new RuntimeException("Error guardando JSON: " + e.getMessage());
        }
    }
}