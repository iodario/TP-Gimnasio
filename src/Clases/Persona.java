package Clases;

public abstract class Persona {
    private int id;
    private String nombre;
    private String dni;
    private String direccion;
    private int telefono;
    private String email;
    private boolean eliminado;

    // Constructor vacío necesario para permitir crear objetos sin parámetros,
    // facilitar la herencia (llamadas a super()) y el uso de librerías o gestores
    // que instancian las clases de forma automática.

    public Persona() {
    }

    public Persona(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.eliminado = eliminado;
    }

    public int getId() {
        return id;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "Clases.Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                ", eliminado=" + eliminado +
                '}';
    }
}
