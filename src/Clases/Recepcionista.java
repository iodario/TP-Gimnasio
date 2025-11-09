package Clases;

public class Recepcionista extends Empleado {

    public Recepcionista() {
    }

    public Recepcionista(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado, String legajo, double sueldo) {
        super(id, nombre, dni, direccion, telefono, email, eliminado, legajo, sueldo);
    }

    @Override
    public String toString() {
        return "Recepcionista{} " + super.toString();
    }


}
