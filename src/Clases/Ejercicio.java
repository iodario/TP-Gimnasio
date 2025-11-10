package Clases;

import Interfaces.Claveable;

public class Ejercicio implements Claveable {
    private String nombre;
    private String descripcion;
    private int repeticiones;
    private int series;


    public Ejercicio(String descripcion, String nombre, int repeticiones, int series) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.repeticiones = repeticiones;
        this.series = series;
    }

    public Ejercicio() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return  "\n ==== EJERCICIO ==== " +
                "\n - Nombre: "+ nombre +
                "\n (Descripcion: " + descripcion + " )" +
                "\n - Cantidad de Series: " + series +
                "\n - Cantidad de Repeticiones: " + repeticiones;
    }

    @Override
    public String getClave() {
        return (nombre == null) ? null : nombre.trim().toLowerCase();
    }
}
