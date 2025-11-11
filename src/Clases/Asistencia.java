package Clases;

import Enumeradores.TipoMembresia;

import java.time.LocalDateTime;

public class Asistencia extends Socio {
    private int id;
    private int socioId;
    private LocalDateTime fechaHora;


    public Asistencia(String nombre,
                      String dni,
                      int telefono,
                      boolean eliminado,
                      boolean activo,
                      int frecuenciaSemanal,
                      TipoMembresia membresia,
                      String objetivo,
                      int id,
                      int socioId,
                      LocalDateTime fechaHora) {
        super(nombre, dni, telefono, eliminado, activo, frecuenciaSemanal, membresia, objetivo);
        this.id = id;
        this.socioId = socioId;
        this.fechaHora = fechaHora;
    }

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

