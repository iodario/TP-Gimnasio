package Clases;

import Enumeradores.Actividad;

public class Entrenador extends Empleado {
    private String especialidad;

    public Entrenador() {

    }

    public Entrenador(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado, String legajo, double sueldo, String especialidad) {
        super(id, nombre, dni, direccion, telefono, email, eliminado, legajo, sueldo);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }


    // FUNCIONES

    public void asignarSocio(Socio socio){

    }

}


