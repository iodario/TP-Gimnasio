package Clases;

import Interfaces.Claveable;

public abstract class Empleado extends Persona implements Claveable {

    private String legajo; // Clave del Empleado
    private double sueldo;

    public Empleado() {

    }

    public Empleado(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado, String legajo, double sueldo) {
        super(id, nombre, dni, direccion, telefono, email, eliminado);
        this.legajo = legajo;
        this.sueldo = sueldo;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String getClave() {
        return legajo;
    }

    @Override
    public String toString() {
        return "==== EMPLEADO ==== \n" +
                getNombre() +
                "\n Legajo='" + legajo + '\'' +
                "\n Sueldo=" + sueldo +
                "} ";
    }
}
