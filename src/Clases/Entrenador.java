package Clases;

import Enumeradores.Actividad;

public class Entrenador extends Empleado {
    private String especialidad;

    public Entrenador() {

    }

    public Entrenador(String nombre, String dni, int telefono, boolean eliminado, String legajo, double sueldo, String especialidad) {
        super(nombre, dni, telefono, eliminado, legajo, sueldo);
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


