package Clases;

import Enumeradores.TipoMembresia;
import Interfaces.Claveable;


public class Socio extends Persona implements Claveable {
    private TipoMembresia membresia;
    private String objetivo;
    private boolean activo;
    private String agendaSemanal;
    private int frecuenciaSemanal;

    public Socio(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado, TipoMembresia membresia, String objetivo, boolean activo, String agendaSemanal, int frecuenciaSemanal) {
        super(id, nombre, dni, direccion, telefono, email, eliminado);
        this.membresia = membresia;
        this.objetivo = objetivo;
        this.activo = activo;
        this.agendaSemanal = agendaSemanal;
        this.frecuenciaSemanal = frecuenciaSemanal;
    }

    public TipoMembresia getMembresia() {
        return membresia;
    }

    public void setMembresia(TipoMembresia membresia) {
        this.membresia = membresia;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getAgendaSemanal() {
        return agendaSemanal;
    }

    public void setAgendaSemanal(String agendaSemanal) {
        this.agendaSemanal = agendaSemanal;
    }

    public int getFrecuenciaSemanal() {
        return frecuenciaSemanal;
    }

    public void setFrecuenciaSemanal(int frecuenciaSemanal) {
        this.frecuenciaSemanal = frecuenciaSemanal;
    }


    @Override
    public String getClave() {
        return getDni();
    }

    @Override
    public String toString() {
        return "Socio{" +
                "membresia=" + membresia +
                ", objetivo='" + objetivo + '\'' +
                ", activo=" + activo +
                ", agendaSemanal='" + agendaSemanal + '\'' +
                ", frecuenciaSemanal=" + frecuenciaSemanal +
                '}';
    }
}
