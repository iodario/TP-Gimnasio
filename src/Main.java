import Clases.*;
import Enumeradores.TipoMembresia;
import Excepciones.DatoInvalidoException;
import Gestores.*;

import java.util.List;
import java.util.Scanner;

public class Main {



    private static final String archivo = "gimnasio.json";

    private static final Scanner sc = new Scanner(System.in);

    // Repos/gestores en memoria
    private static final GestorSocios socios = new GestorSocios();
    private static final GestorEmpleados empleados = new GestorEmpleados();
    private static final Gestor<ClaseGrupal> clases = new Gestor<>();
    private static final Gestor<Reserva> reservas = new Gestor<>();
    private static final Gestor<Pago> pagos = new Gestor<>();
    private static GestorRutinas gestorRutinas = new GestorRutinas(socios);


    public static void main(String[] args) {



        int opcion;
        do {
            System.out.println("\n ===== GIMNASIO FITLIFE =====");
            System.out.println("1) Socios");
            System.out.println("2) Entrenadores");
            System.out.println("3) Recepcionista");
            System.out.println("4) Rutinas");
            System.out.println("0) Salir");
            opcion = leerEntero("Opción: ");

            try {
                switch (opcion) {
                    case 1 -> menuSocios();
                    case 2 -> menuEmpleados();
                    case 3 -> menuRecepcionista();
                    case 4 -> menuRutinas();
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida");
                }
            } catch (DatoInvalidoException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }

            guardarTodo();

        } while (opcion != 0);
    }


    // ======== GUARDAR EN JSON ========= //
    private static void guardarTodo() {
        System.out.println("\n--- Guardando datos en archivos JSON... ---");
        socios.guardarSociosEnJSON();
        empleados.guardarEmpleadosEnJSON();
        System.out.println("--- Guardado completado ---");
    }



    // ======== SUBMENÚS ========

    private static void menuSocios() throws DatoInvalidoException {
        int op;
        do {
            System.out.println("\n--- SOCIOS ---");
            System.out.println("1) Alta socio");
            System.out.println("2) Listar socios");
            System.out.println("3) Buscar socio");
            System.out.println("4) Eliminar socio");
            System.out.println("0) Volver");
            op = leerEntero("Opción: ");

            switch (op) {
                case 1 -> altaSocio();
                case 2 -> listarSocios();
                case 3 -> buscarSocio();
                case 4 -> eliminarSocio();
                case 0 -> {}
                default -> System.out.println("Opción inválida");
            }
        } while (op != 0);
    }

    private static void menuEmpleados() throws DatoInvalidoException {
        int op;
        do {
            System.out.println("\n--- ENTRENADORES / EMPLEADOS ---");
            System.out.println("1) Alta empleado");
            System.out.println("2) Listar empleados");
            System.out.println("3) Buscar empleado");
            System.out.println("4) Eliminar empleado");
            System.out.println("0) Volver");
            op = leerEntero("Opción: ");

            switch (op) {
                case 1 -> altaEmpleado();
                case 2 -> listarEmpleados();
                case 3 -> buscarEmpleado();
                case 4 -> eliminarEmpleado();
                case 0 -> {}
                default -> System.out.println("Opción inválida");
            }
        } while (op != 0);
    }

    private static void menuRecepcionista() throws DatoInvalidoException {
        int op;
        do {
            System.out.println("\n--- RECEPCIONISTA ---");
            System.out.println("1) Alta clase grupal");
            System.out.println("2) Listar clases");
            System.out.println("3) Reservar clase");
            System.out.println("4) Listar reservas");
            System.out.println("5) Cancelar reserva");
            System.out.println("6) Registrar pago");
            System.out.println("7) Listar pagos");
            System.out.println("0) Volver");
            op = leerEntero("Opción: ");

            switch (op) {
                case 1 -> altaClase();
                case 2 -> listarClases();
                case 3 -> reservarClase();
                case 4 -> listarReservas();
                case 5 -> cancelarReserva();
                case 6 -> registrarPago();
                case 7 -> listarPagos();
                case 0 -> {}
                default -> System.out.println("Opción inválida");
            }
        } while (op != 0);
    }

    private static void menuRutinas() throws DatoInvalidoException {
        int op;
        do {
            System.out.println("\n--- RUTINAS ---");
            System.out.println("1) Crear rutina para socio");
            System.out.println("2) Listar rutinas de un socio");
            System.out.println("3) Agregar ejercicio a una rutina");
            System.out.println("4) Listar ejercicios de una rutina");
            System.out.println("5) Eliminar ejercicio de una rutina");
            System.out.println("6) Listar todas las rutinas");
            System.out.println("0) Volver");
            op = leerEntero("Opción: ");

            switch (op) {
                case 1 -> crearRutina();
                case 2 -> listarRutinasPorSocio();
                case 3 -> agregarEjercicioARutina();
                case 4 -> listarEjerciciosDeRutina();
                case 5 -> eliminarEjercicioDeRutina();
                case 6 -> gestorRutinas.listarRutinas();
                case 0 -> {}
                default -> System.out.println("Opción inválida");
            }
        } while (op != 0);
    }

    // ======== SOCIOS ========
    private static void altaSocio() throws DatoInvalidoException {
        System.out.println("\n-- Alta socio --");
        String nombre = leerLinea("Nombre: ");
        String dni = leerLinea("DNI: ");
        int telefono = leerEntero("Teléfono: "); //
        String objetivo = leerLinea("Objetivo: "); //
        String mem = leerLinea("Membresía (BASICA/INTERMEDIA/PREMIUM): ").toUpperCase();
        int freq = leerEntero("Frecuencia semanal: ");

        TipoMembresia tipo;
        try {
            tipo = TipoMembresia.valueOf(mem);
        } catch(Exception e) {
            tipo = TipoMembresia.BASICA;
            System.out.println("Membresía no válida, se asignó BASICA por defecto");
        }



        Socio s = new Socio(nombre, dni, telefono, false, true, freq, tipo, objetivo);
        socios.altaSocio(s);
        System.out.println("Socio cargado correctamente.");
    }
    private static void listarSocios() {
        System.out.println("\n-- Socios --");
        List<Socio> lista = socios.listarSocios();
        if (lista.isEmpty()) System.out.println("(sin datos)");
        else lista.forEach(System.out::println);
    }

    private static void buscarSocio() {
        String dni = leerLinea("DNI a buscar: ");
        Socio s = socios.buscarSocioPorDni(dni);
        System.out.println(s != null ? s : "No encontrado");
    }

    private static void eliminarSocio() {
        String dni = leerLinea("DNI a eliminar: ");
        boolean ok = socios.bajaSocio(dni);
        System.out.println(ok ? "Eliminado" : "No se encontró");
    }

    // ======== EMPLEADOS ========
    private static void altaEmpleado() throws DatoInvalidoException {
        System.out.println("\n-- Alta empleado --");
        String nombre = leerLinea("Nombre: ");
        String dni = leerLinea("DNI: ");
        int telefono = leerEntero("Teléfono: ");
        String legajo = leerLinea("Legajo: ");
        double sueldo = leerDouble("Sueldo: ");

        System.out.println("Tipo de empleado:");
        System.out.println("1) Entrenador");
        System.out.println("2) Recepcionista");
        int tipo = leerEntero("Opción: ");

        Empleado empleado;
        if (tipo == 1) {
            String especialidad = leerLinea("Especialidad: ");
            empleado = new Entrenador(nombre, dni, telefono, false, legajo, sueldo, especialidad);
        } else {
            empleado = new Recepcionista(nombre, dni, telefono, false, legajo, sueldo);
        }

        empleados.altaEmpleado(empleado);
        System.out.println("✓ Empleado cargado correctamente.");
    }

    private static void listarEmpleados() {
        System.out.println("\n-- Empleados --");
        List<Empleado> lista = empleados.listarEmpleados();
        if (lista.isEmpty()) System.out.println("(sin datos)");
        else lista.forEach(System.out::println);
    }

    private static void buscarEmpleado() {
        String legajo = leerLinea("Legajo a buscar: ");
        Empleado e = empleados.buscarEmpleadoPorLegajo(legajo);
        System.out.println(e != null ? e : "No encontrado");
    }

    private static void eliminarEmpleado() {
        String legajo = leerLinea("Legajo a eliminar: ");
        boolean ok = empleados.bajaEmpleado(legajo);
        System.out.println(ok ? "Eliminado" : "No se encontró");
    }

    // ======== RUTINAS / EJERCICIOS ========

    private static void crearRutina() throws DatoInvalidoException {
        System.out.println("\n-- Crear rutina --");
        String claveSocio = leerLinea("DNI/clave socio: ");
        String nombre = leerLinea("Nombre de rutina: ");
        String desc = leerLinea("Descripción: ");
        String entrenador = leerLinea("Entrenador: ");

        Rutina r = new Rutina();
        gestorRutinas.agregar(r);
        System.out.println("Rutina creada: " + r.toString());
    }

    private static void listarRutinasPorSocio() {
        String claveSocio = leerLinea("DNI/clave socio: ");
        List<Rutina> lista = gestorRutinas.buscarRutinasPorSocio(claveSocio);
        if (lista.isEmpty()) System.out.println("(sin rutinas)");
        else lista.forEach(r -> System.out.println("• " + r.toString()));
    }

    private static Rutina obtenerRutinaPorNombre(String claveSocio, String nombreRutina) {
        String claveRutina = (claveSocio + "-" + nombreRutina).toLowerCase();
        return gestorRutinas.buscarPorClave(claveRutina);
    }

    private static void agregarEjercicioARutina() throws DatoInvalidoException {
        System.out.println("\n-- Agregar ejercicio a rutina --");
        String claveSocio = leerLinea("DNI/clave socio: ");
        String nombreRutina = leerLinea("Nombre de rutina: ");
        Rutina r = obtenerRutinaPorNombre(claveSocio, nombreRutina);
        if (r == null) throw new DatoInvalidoException("Rutina no encontrada");

        GestorEjercicios ge = new GestorEjercicios(r);

        String nom = leerLinea("Ejercicio: ");
        String des = leerLinea("Descripción: ");
        int series = leerEntero("Series: ");
        int reps = leerEntero("Repeticiones: ");

        ge.agregar(new Ejercicio(nom, des, series, reps));
        System.out.println("Ejercicio agregado correctamente.");
    }

    private static void listarEjerciciosDeRutina() {
        System.out.println("\n-- Ejercicios de rutina --");
        String claveSocio = leerLinea("DNI/clave socio: ");
        String nombreRutina = leerLinea("Nombre de rutina: ");
        Rutina r = obtenerRutinaPorNombre(claveSocio, nombreRutina);
        if (r == null) {
            System.out.println("Rutina no encontrada");
            return;
        }
        GestorEjercicios ge = new GestorEjercicios(r);
        List<Ejercicio> lista = ge.listar();
        if (lista.isEmpty()) System.out.println("(sin ejercicios)");
        else lista.forEach(e -> System.out.println("• " + e));
    }

    private static void eliminarEjercicioDeRutina() {
        System.out.println("\n-- Eliminar ejercicio --");
        String claveSocio = leerLinea("DNI/clave socio: ");
        String nombreRutina = leerLinea("Nombre de rutina: ");
        String nombreEj = leerLinea("Nombre de ejercicio: ");
        Rutina r = obtenerRutinaPorNombre(claveSocio, nombreRutina);
        if (r == null) {
            System.out.println("Rutina no encontrada");
            return;
        }
        GestorEjercicios ge = new GestorEjercicios(r);
        boolean ok = ge.eliminarPorClave(nombreEj.toLowerCase());
        System.out.println(ok ? "Ejercicio eliminado." : "No se encontró ese ejercicio.");
    }

    // ======== CLASES / RESERVAS / PAGOS ========

    private static void altaClase() throws DatoInvalidoException {
        System.out.println("\n-- Alta clase grupal --");
        String id = leerLinea("ID clase: ");
        String nombre = leerLinea("Nombre: ");
        String actividad = leerLinea("Actividad: ");
        String dia = leerLinea("Día: ");
        String hora = leerLinea("Hora (HH:mm): ");
        int cupo = leerEntero("Cupo máximo: ");
        String legajo = leerLinea("Legajo entrenador: ");

        ClaseGrupal c = new ClaseGrupal(id, nombre, actividad, dia, hora, cupo, legajo);
        clases.agregar(c);
        System.out.println("Clase creada.");
    }

    private static void listarClases() {
        System.out.println("\n-- Clases --");
        List<ClaseGrupal> lista = clases.listar();
        if (lista.isEmpty()) System.out.println("(sin datos)");
        else lista.forEach(System.out::println);
    }

    private static void reservarClase() throws DatoInvalidoException {
        System.out.println("\n-- Reservar clase --");
        String claseId = leerLinea("ID clase: ");
        String dni = leerLinea("DNI socio: ");

        ClaseGrupal c = clases.buscarPorClave(claseId);
        if (c == null) throw new DatoInvalidoException("Clase inexistente");
        Socio s = socios.buscarSocioPorDni(dni);
        if (s == null || !s.isActivo()) throw new DatoInvalidoException("Socio inexistente o inactivo");
        if (c.cupoDisponible() <= 0) throw new DatoInvalidoException("Cupo completo");

        List<Reserva> rs = c.getReservas();
        for (Reserva re : rs) {
            if (dni.equalsIgnoreCase(re.getSocioDni()) && re.isActiva())
                throw new DatoInvalidoException("El socio ya tiene reserva activa");
        }

        String rid = claseId + "-" + (rs.size() + 1);
        Reserva r = new Reserva(rid, dni, claseId);
        rs.add(r);
        reservas.agregar(r);
        System.out.println("Reserva creada: " + r);
    }

    private static void listarReservas() {
        System.out.println("\n-- Reservas --");
        List<Reserva> lista = reservas.listar();
        if (lista.isEmpty()) System.out.println("(sin datos)");
        else lista.forEach(System.out::println);
    }

    private static void cancelarReserva() {
        String rid = leerLinea("ID de reserva a cancelar: ");
        Reserva r = reservas.buscarPorClave(rid);
        if (r != null) {
            r.cancelarReserva();
            System.out.println("Cancelada: " + r);
        } else System.out.println("No encontrada");
    }

    private static void registrarPago() throws DatoInvalidoException {
        System.out.println("\n-- Registrar pago --");
        String id = leerLinea("ID pago: ");
        String dni = leerLinea("DNI socio: ");
        String periodo = leerLinea("Periodo (YYYY-MM): ");
        double monto = leerDouble("Monto: ");
        String medio = leerLinea("Medio (EFECTIVO/TARJETA/TRANSFERENCIA): ");

        if (socios.buscarSocioPorDni(dni) == null) throw new DatoInvalidoException("Socio inexistente");

        Pago p = new Pago(id, dni, monto, periodo, medio.toUpperCase());
        pagos.agregar(p);
        System.out.println("Pago registrado.");
    }

    private static void listarPagos() {
        System.out.println("\n-- Pagos --");
        List<Pago> lista = pagos.listar();
        if (lista.isEmpty()) System.out.println("(sin datos)");
        else lista.forEach(System.out::println);
    }

    // ======== Utiles de entrada ========
    private static String leerLinea(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private static int leerEntero(String msg) {
        try {
            System.out.print(msg);
            return Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) { return 0; }
    }

    private static double leerDouble(String msg) {
        try {
            System.out.print(msg);
            return Double.parseDouble(sc.nextLine().trim());
        } catch (Exception e) { return 0.0; }
    }
}
