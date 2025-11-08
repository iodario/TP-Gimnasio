package Clases;

import Enumeradores.Actividad;
import Enumeradores.TipoMembresia;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class Clase_Grupal extends Socio{
    private int id;
    private String nombre;
    private Actividad actividad;
    private DayOfWeek dia;
    private LocalTime hora;
    private int cupoMax;
    private Entrenador entrenadorId;
    private boolean eliminado;
    private List<Reserva> reservas;

    public Clase_Grupal(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado, TipoMembresia membresia, String objetivo, boolean activo, String agendaSemanal, int frecuenciaSemanal, int id1, String nombre1, Actividad actividad, DayOfWeek dia, LocalTime hora, int cupoMax, Entrenador entrenadorId, boolean eliminado1, List<Reserva> reservas) {
        super(id, nombre, dni, direccion, telefono, email, eliminado, membresia, objetivo, activo, agendaSemanal, frecuenciaSemanal);
        this.id = id1;
        this.nombre = nombre1;
        this.actividad = actividad;
        this.dia = dia;
        this.hora = hora;
        this.cupoMax = cupoMax;
        this.entrenadorId = entrenadorId;
        this.eliminado = eliminado1;
        this.reservas = reservas;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public DayOfWeek getDia() {
        return dia;
    }

    public void setDia(DayOfWeek dia) {
        this.dia = dia;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getCupoMax() {
        return cupoMax;
    }

    public void setCupoMax(int cupoMax) {
        this.cupoMax = cupoMax;
    }

    public Entrenador getEntrenadorId() {
        return entrenadorId;
    }

    public void setEntrenadorId(Entrenador entrenadorId) {
        this.entrenadorId = entrenadorId;
    }

    @Override
    public boolean isEliminado() {
        return eliminado;
    }

    @Override
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public int cupoDisponible() {
        return 0;
    }

}
