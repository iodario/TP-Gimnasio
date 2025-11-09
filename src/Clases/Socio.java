package Clases;

import Enumeradores.TipoMembresia;
import Interfaces.Claveable;


public class Socio extends Persona implements Claveable {
    private TipoMembresia membresia;
    private String objetivo;
    private boolean activo;
    private int frecuenciaSemanal;

    public Socio() {
    }

    public Socio(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado, boolean activo, int frecuenciaSemanal, TipoMembresia membresia, String objetivo) {
        super(id, nombre, dni, direccion, telefono, email, eliminado);
        this.activo = activo;
        this.frecuenciaSemanal = frecuenciaSemanal;
        this.membresia = membresia;
        this.objetivo = objetivo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getFrecuenciaSemanal() {
        return frecuenciaSemanal;
    }

    public void setFrecuenciaSemanal(int frecuenciaSemanal) {
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

    @Override
    public String getClave() {
        return getDni();
    }

    @Override
    public String toString() {
        return "\n ==== SOCIO ==== \n" +
                getNombre() +
                "\n - Dni: " + getDni() +
                "\n - Objetivo: " + objetivo +
                "\n - Tipo de Membresia: " + membresia +
                "\n - Activo: " + activo;
    }
}
