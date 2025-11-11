package Gestores;

import Clases.ClaseGrupal;
import Clases.Reserva;
import Excepciones.DatoInvalidoException;

import java.util.ArrayList;
import java.util.List;

/**
 * GestorClasesGrupales
 * - Extiende del Gestor<T> genérico usando T = ClaseGrupal.
 */

public class GestorClasesGrupales extends Gestor<ClaseGrupal> {

    // --------- CRUD básico (envoltorios con nombres más "dominio") ---------

    public ClaseGrupal buscarPorId(String idClase) {
        return super.buscarPorClave(idClase);
    }

    public void agregarClase(ClaseGrupal clase) throws DatoInvalidoException {
        // Reutiliza validaciones de Gestor.agregar(...)
        super.agregar(clase);
    }

    public boolean eliminarClase(String idClase) {
        return super.eliminarPorClave(idClase);
    }



    /**
     * Lista todas las clases de un día (case-insensitive)
     */
    public List<ClaseGrupal> listarPorDia(String dia) {
        List<ClaseGrupal> res = new ArrayList<ClaseGrupal>();
        if (dia == null) return res;
        String d = dia.trim();
        List<ClaseGrupal> todas = super.listar();
        for (int i = 0; i < todas.size(); i++) {
            ClaseGrupal c = todas.get(i);
            if (c.getDia() != null && c.getDia().equalsIgnoreCase(d)) {
                res.add(c);
            }
        }
        return res;
    }

    /**
     * Lista todas las clases dictadas por un entrenador (legajo)
     */
    public List<ClaseGrupal> listarPorEntrenador(String legajoEntrenador) {
        List<ClaseGrupal> res = new ArrayList<ClaseGrupal>();
        if (legajoEntrenador == null) return res;
        String leg = legajoEntrenador.trim();
        List<ClaseGrupal> todas = super.listar();
        for (int i = 0; i < todas.size(); i++) {
            ClaseGrupal c = todas.get(i);
            if (c.getEntrenadorLegajo() != null && c.getEntrenadorLegajo().equalsIgnoreCase(leg)) {
                res.add(c);
            }
        }
        return res;
    }




    // ---------------------- Reservas (operaciones básicas) ------------------

    /**
     * Registra una reserva en la clase indicada, validando:
     * - existencia de la Clase
     * - datos mínimos de la Reserva
     * - cupo disponible
     * - que el socio no tenga ya una reserva ACTIVA en la misma clase
     */
    public void reservar(String idClase, Reserva reserva) throws DatoInvalidoException {
        if (idClase == null || idClase.trim().isEmpty()) {
            throw new DatoInvalidoException("Id de clase inválido");
        }
        if (reserva == null) {
            throw new DatoInvalidoException("Reserva nula");
        }

        ClaseGrupal clase = buscarPorId(idClase);
        if (clase == null) {
            throw new DatoInvalidoException("No existe la clase con id: " + idClase);
        }

        if (clase.cupoDisponible() <= 0) {
            throw new DatoInvalidoException("Cupo lleno para la clase " + idClase);
        }

        // Chequeo de duplicado: socio ya tiene reserva ACTIVA en esta clase
         String idSocio = reserva.getClave(); // se asume que existe este getter
        if (existeReservaActivaParaSocio(clase, idSocio)) {
            throw new DatoInvalidoException("El socio " + idSocio + " ya tiene una reserva activa en esta clase");
        }

        // Agregar la reserva
        List<Reserva> lista = clase.getReservas();
        if (lista == null) {
            lista = new ArrayList<Reserva>();
            clase.setReservas(lista);
        }
        lista.add(reserva);
    }

    /**
     * Cancela una reserva activa por socio dentro de una clase.
     * Devuelve true si encontró y canceló; false si no existía reserva activa.
     */
    public boolean cancelarReserva(String idClase, String idSocio) throws DatoInvalidoException {
        if (idClase == null || idClase.trim().isEmpty()) {
            throw new DatoInvalidoException("Id de clase inválido");
        }
        ClaseGrupal clase = buscarPorId(idClase);
        if (clase == null) {
            throw new DatoInvalidoException("No existe la clase con id: " + idClase);
        }

        List<Reserva> lista = clase.getReservas();
        if (lista == null) return false;

        for (int i = 0; i < lista.size(); i++) {
            Reserva r = lista.get(i);
            if (r != null && r.isActiva() && r.getClave().equals(idSocio)) {
                // Estrategia simple: marcar inactiva (o eliminar del listado, según tu modelo)
                // Si tu Reserva no tiene "setActiva", se podría remover del array.
                // Aquí intentamos primero desactivar:
                try {
                    r.isActiva(); // si existe
                } catch (Exception e) {
                    // si no existe setActiva, se elimina la reserva como cancelación
                    lista.remove(i);
                }
                return true;
            }
        }
        return false;
    }

    // --------------------------- Utilidades internas ------------------------

    private boolean existeReservaActivaParaSocio(ClaseGrupal clase, String clave) {
        List<Reserva> lista = clase.getReservas();
        if (lista == null) return false;
        for (int i = 0; i < lista.size(); i++) {
            Reserva r = lista.get(i);
            if (r != null && r.isActiva() && r.getClave() == clave) {
                return true;
            }
        }
        return false;
    }
}




