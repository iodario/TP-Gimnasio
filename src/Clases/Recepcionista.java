package Clases;

public class Recepcionista extends Empleado {

    public Recepcionista() {
    }

    public Recepcionista(String nombre, String dni, int telefono, boolean eliminado, String legajo, double sueldo) {
        super(nombre, dni, telefono, eliminado, legajo, sueldo);
    }

    @Override
    public String toString() {
        return "Recepcionista{} " + super.toString();
    }


}
