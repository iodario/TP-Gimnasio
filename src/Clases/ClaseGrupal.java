package Clases;

import Enumeradores.Actividad;
import Enumeradores.TipoMembresia;
import Interfaces.Claveable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ClaseGrupal implements Claveable {
    private String id;
    private String nombre;
    private String actividad; // TODO: cambiar a Enum
    private String dia;
    private String hora;
    private int cupoMax;
    private String entrenadorLegajo;
    private List<Reserva> reservas = new ArrayList<>();

    // Constructor Vacio
    public ClaseGrupal() {

    }
    //Constructor
    public ClaseGrupal(String id, String nombre, String actividad, String dia, String hora, int cupoMax, String entrenadorLegajo) {
        this.id = id;
        this.nombre = nombre;
        this.actividad = actividad;
        this.dia = dia;
        this.hora = hora;
        this.cupoMax = cupoMax;
        this.entrenadorLegajo = entrenadorLegajo;
    }

    //Getters y Setters
    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public int getCupoMax() {
        return cupoMax;
    }

    public void setCupoMax(int cupoMax) {
        this.cupoMax = cupoMax;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getEntrenadorLegajo() {
        return entrenadorLegajo;
    }

    public void setEntrenadorLegajo(String entrenadorLegajo) {
        this.entrenadorLegajo = entrenadorLegajo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    // Funciones
    public String getClave() {
        return id;
    }

    public int cupoDisponible() {
        int activas = 0;
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).isActiva()) activas++;
        }
        return cupoMax - activas;
    }

    public List<Reserva> getReserva() {
        return reservas;
    }

    @Override
    public String toString() {
        return "\n ==== CLASE EN GRUPO ====" +
                "\n - ID: " + id +
                "\n - Nombre: " + nombre +
                "\n - Actividad: " + actividad +
                "\n - Dia y Horario: " + dia + ", " + hora +
                "\n - Cupo: " + cupoMax +
                "\n - Disponibilidad: " + cupoDisponible() + " Cupos";
    }

}
