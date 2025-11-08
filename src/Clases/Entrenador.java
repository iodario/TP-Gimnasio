package Clases;

import Enumeradores.Actividad;

public class Entrenador extends Empleado {
    private Actividad especialidad;
    private String whatsapp;

    public Entrenador(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado, String legajo, double sueldo, String horarioTrabajo, int horasExtras, int feriadosTrabajados, Actividad especialidad, String whatsapp) {
        super(id, nombre, dni, direccion, telefono, email, eliminado, legajo, sueldo, horarioTrabajo, horasExtras, feriadosTrabajados, getDiasVacaciones());
        this.especialidad = especialidad;
        this.whatsapp = whatsapp;
    }

    public Actividad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Actividad especialidad) {
        this.especialidad = especialidad;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    // FUNCIONES

    public void asignarSocio(Socio socio){

    }

}


