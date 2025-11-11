package Clases;

public abstract class Persona {
    private String nombre;
    private String dni;
    private int telefono;
    private boolean eliminado;

    // Constructor vacío necesario para permitir crear objetos sin parámetros,
    // facilitar la herencia (llamadas a super()) y el uso de librerías o gestores
    // que instancian las clases de forma automática.

    public Persona() {
    }

    public Persona(String nombre, String dni, int telefono, boolean eliminado) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.eliminado = eliminado;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }


    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String descripcionCorta() {
        return nombre + " (DNI: " + dni + ")";
    }


    @Override
    public String toString() {
        return "\n ==== PERSONA ==== \n" +
                "\n - Nombre: " + nombre +
                "\n - Dni: " + dni +
                "\n - Telefono " + telefono ;
    }




}
