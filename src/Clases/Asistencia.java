package Clases;

import Enumeradores.TipoMembresia;

import java.time.LocalDateTime;

public class Asistencia extends Socio{
    private int id;
    private int socioId;
    private LocalDateTime fechaHora;

    public Asistencia(LocalDateTime fechaHora, int id, int socioId) {
        this.fechaHora = fechaHora;
        this.id = id;
        this.socioId = socioId;
    }

    public Asistencia(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado, boolean activo, int frecuenciaSemanal, TipoMembresia membresia, String objetivo, LocalDateTime fechaHora, int id1, int socioId) {
        super(id, nombre, dni, direccion, telefono, email, eliminado, activo, frecuenciaSemanal, membresia, objetivo);
        this.fechaHora = fechaHora;
        this.id = id1;
        this.socioId = socioId;
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
