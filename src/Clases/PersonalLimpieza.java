package Clases;

public class PersonalLimpieza extends Empleado {

    public PersonalLimpieza(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado, double sueldo, String horarioTrabajo, int horasExtras, int feriadosTrabajados, int diasVacaciones) {
        super(id, nombre, dni, direccion, telefono, email, eliminado, sueldo, horarioTrabajo, horasExtras, feriadosTrabajados, diasVacaciones);
    }

    @Override
    public String toString() {
        return "PersonalLimpieza{} " + super.toString();
    }
}
