 package Clases;

import Interfaces.Claveable;

import java.util.ArrayList;
import java.util.List;

public class Rutina implements Claveable {

    private String nombre;
    private String descripcion;
    private String claveSocio;   // socio dueño de la rutina
    private String entrenador;   // nombre o legajo del entrenador
    private List<Ejercicio> ejercicios; // lista interna de ejercicios (sin gestión directa)


    public Rutina(String nombre, String descripcion, String claveSocio, String entrenador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.claveSocio = claveSocio;
        this.entrenador = entrenador;
        this.ejercicios = new ArrayList<>();
    }

    // Se iniciliza tambien de forma independiente para poder utilizarlo en el JSON
    public Rutina() {
        this.ejercicios = new ArrayList<>();
    }

    // Implementacion de Claveable
    @Override
    public String getClave() {
        // combina claveSocio + nombre de rutina, para que sea única
        return (claveSocio + "-" + nombre).toLowerCase();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getClaveSocio() { return claveSocio; }
    public void setClaveSocio(String claveSocio) { this.claveSocio = claveSocio; }

    public String getEntrenador() { return entrenador; }
    public void setEntrenador(String entrenador) { this.entrenador = entrenador; }

    public List<Ejercicio> getEjercicios() { return ejercicios; }
    public void setEjercicios(List<Ejercicio> ejercicios) { this.ejercicios = ejercicios; }



    @Override
    public String toString() {
        return  "\n ==== RUTINA ====" +
                "\n - Clave del Socio: " + claveSocio +
                "\n - Nombre: " + nombre +
                "\n - Descripcion: " + descripcion +
                "\n - Entrenador: " + entrenador +
                "\n - Ejercicios: " + ejercicios;
    }

   /*public String descripcionCorta() {
        return System.out.println( "\n | Nombre: " + nombre +
                                   "\n | Socio: " + claveSocio +
                                   "\n | Ejercicios:  " + ejercicios +
                                   "\n | Entrenador: " + entrenador);
    }*/
}