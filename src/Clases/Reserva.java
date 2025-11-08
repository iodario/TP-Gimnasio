package Clases;

import Enumeradores.EstadoReserva;
import Enumeradores.TipoMembresia;

import java.time.LocalDateTime;

public class Reserva extends Socio{
    private int id;
    private int socioId;
    private int claseId;
    private LocalDateTime fechaReserva;
    private EstadoReserva estado;

    public Reserva(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado, TipoMembresia membresia, String objetivo, boolean activo, String agendaSemanal, int frecuenciaSemanal, int id1, int socioId, int claseId, LocalDateTime fechaReserva, EstadoReserva estado) {
        super(id, nombre, dni, direccion, telefono, email, eliminado, membresia, objetivo, activo, agendaSemanal, frecuenciaSemanal);
        this.id = id1;
        this.socioId = socioId;
        this.claseId = claseId;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSocioId() {
        return socioId;
    }

    public void setSocioId(int socioId) {
        this.socioId = socioId;
    }

    public int getClaseId() {
        return claseId;
    }

    public void setClaseId(int claseId) {
        this.claseId = claseId;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public void cancelarReserva() {

    }
}
