import Clases.*;
import Enumeradores.TipoMembresia;
import Excepciones.DatoInvalidoException;
import Gestores.GestionSocios;
import Gestores.GestorEmpleados;
import Gestores.Gestor;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    // Repos/gestores en memoria
    private static final GestionSocios socios = new GestionSocios();
    private static final GestorEmpleados empleados = new GestorEmpleados();
    private static final Gestor<ClaseGrupal> clases = new Gestor<ClaseGrupal>();
    private static final Gestor<Reserva> reservas = new Gestor<Reserva>();
    private static final Gestor<Pago> pagos = new Gestor<Pago>();

    public static void main(String[] args) {
        int op;
        do {
            mostrarMenu();
            op = leerEntero("Opción: ");
            try {
                switch (op) {
                    case 1: altaSocio(); break;
                    case 2: listarSocios(); break;
                    case 3: buscarSocio(); break;
                    case 4: eliminarSocio(); break;

                    case 5: altaEmpleado(); break;
                    case 6: listarEmpleados(); break;
                    case 7: buscarEmpleado(); break;
                    case 8: eliminarEmpleado(); break;

                    case 9: altaClase(); break;
                    case 10: listarClases(); break;

                    case 11: reservarClase(); break;
                    case 12: listarReservas(); break;
                    case 13: cancelarReserva(); break;

                    case 14: registrarPago(); break;
                    case 15: listarPagos(); break;

                    case 0: System.out.println("Saliendo..."); break;
                    default: System.out.println("Opción inválida");
                }
            } catch (DatoInvalidoException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        } while (op != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n===== Gimnasio – Menú =====");
        System.out.println("1) Alta socio");
        System.out.println("2) Listar socios");
        System.out.println("3) Buscar socio");
        System.out.println("4) Eliminar socio");
        System.out.println("5) Alta empleado/entrenador");
        System.out.println("6) Listar empleados");
        System.out.println("7) Buscar empleado");
        System.out.println("8) Eliminar empleado");
        System.out.println("9) Alta clase grupal");
        System.out.println("10) Listar clases");
        System.out.println("11) Reservar clase para socio");
        System.out.println("12) Listar reservas");
        System.out.println("13) Cancelar reserva");
        System.out.println("14) Registrar pago");
        System.out.println("15) Listar pagos");
        System.out.println("0) Salir");
    }

    // ======== Socios ========
    private static void altaSocio() throws DatoInvalidoException {
        System.out.println("\n-- Alta socio --");
        int id = leerEntero("ID interno: ");
        String nombre = leerLinea("Nombre: ");
        String dni = leerLinea("DNI: ");
        String dir = leerLinea("Dirección: ");
        String tel = leerLinea("Teléfono: ");
        String mail = leerLinea("Email: ");
        String mem = leerLinea("Membresía (BASICA/MEDIA/FULL): ").toUpperCase();
        boolean activo = true;
        int freq = leerEntero("Frecuencia semanal: ");

        TipoMembresia tipo;
        try { tipo = TipoMembresia.valueOf(mem); } catch(Exception e) { tipo = TipoMembresia.BASICA; }

        Socio s = new Socio(id, nombre, dni, dir, 223462462, mail, false, activo, freq, tipo, "Tren Superior");
        socios.altaSocio(s);
        System.out.println("Socio cargado.");
    }

    private static void listarSocios() {
        System.out.println("\n-- Socios --");
        List<Socio> lista = socios.listarSocios();
        if (lista.isEmpty()) System.out.println("(sin datos)");
        for (int i = 0; i < lista.size(); i++) System.out.println("- " + lista.get(i));
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

    // ======== Empleados / Entrenadores ========
    private static void altaEmpleado() throws DatoInvalidoException {
        System.out.println("\n-- Alta empleado/entrenador --");
        int id = leerEntero("ID interno: ");
        String nombre = leerLinea("Nombre: ");
        String dni = leerLinea("DNI: ");
        String dir = leerLinea("Dirección: ");
        String tel = leerLinea("Teléfono: ");
        String mail = leerLinea("Email: ");
        String legajo = leerLinea("Legajo (clave): ");
        double sueldo = leerDouble("Sueldo: ");
        String especialidad = leerLinea("Especialidad (texto): ");

        Entrenador e = new Entrenador(id, nombre, dni, dir, 22364642, mail, false, legajo, sueldo, especialidad);
        empleados.altaEmpleado(e);
        System.out.println("Empleado cargado.");
    }

    private static void listarEmpleados() {
        System.out.println("\n-- Empleados --");
        List<Empleado> lista = empleados.listarEmpleados();
        if (lista.isEmpty()) System.out.println("(sin datos)");
        for (int i = 0; i < lista.size(); i++) System.out.println("- " + lista.get(i));
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

    // ======== Clases / Reservas ========
    private static void altaClase() throws DatoInvalidoException {
        System.out.println("\n-- Alta clase grupal --");
        String id = leerLinea("ID clase (texto): ");
        String nombre = leerLinea("Nombre: ");
        String actividad = leerLinea("Actividad: ");
        String dia = leerLinea("Día (texto): ");
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
        for (int i = 0; i < lista.size(); i++) System.out.println("- " + lista.get(i));
    }

    private static void reservarClase() throws DatoInvalidoException {
        System.out.println("\n-- Reservar clase --");
        String claseId = leerLinea("ID clase: ");
        String dni = leerLinea("DNI socio: ");

        // validaciones simples
        ClaseGrupal c = clases.buscarPorClave(claseId);
        if (c == null) throw new DatoInvalidoException("Clase inexistente");
        Socio s = socios.buscarSocioPorDni(dni);
        if (s == null || !s.isActivo()) throw new DatoInvalidoException("Socio inexistente o inactivo");
        if (c.cupoDisponible() <= 0) throw new DatoInvalidoException("Cupo completo");

        // evitar duplicado
        List<Reserva> rs = c.getReservas();
        for (int i = 0; i < rs.size(); i++) {
            if (dni.equalsIgnoreCase(rs.get(i).getSocioDni()) && rs.get(i).isActiva()) {
                throw new DatoInvalidoException("El socio ya tiene reserva activa");
            }
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
        for (int i = 0; i < lista.size(); i++) System.out.println("- " + lista.get(i));
    }

    private static void cancelarReserva() {
        String rid = leerLinea("ID de reserva a cancelar: ");
        Reserva r = reservas.buscarPorClave(rid);
        if (r != null) {
            r.cancelarReserva();
            System.out.println("Cancelada: " + r);
        } else {
            System.out.println("No encontrada");
        }
    }

    // ======== Pagos ========
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
        for (int i = 0; i < lista.size(); i++) System.out.println("- " + lista.get(i));
    }

    // ======== Utiles entrada ========
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
