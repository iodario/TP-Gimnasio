package Clases;

import Enumeradores.TipoMembresia;

import java.time.LocalDateTime;

public class Asistencia extends Socio{
    private int id;
    private int socioId;
    private LocalDateTime fechaHora;

    public Asistencia(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado, TipoMembresia membresia, String objetivo, boolean activo, String agendaSemanal, int frecuenciaSemanal, int id1, int socioId, LocalDateTime fechaHora) {
        super(id, nombre, dni, direccion, telefono, email, eliminado, membresia, objetivo, activo, agendaSemanal, frecuenciaSemanal);
        this.id = id1;
        this.socioId = socioId;
        this.fechaHora = fechaHora;
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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
