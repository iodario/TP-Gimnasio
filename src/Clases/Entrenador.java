package Clases;

import Enumeradores.Especialidad;

public class Entrenador extends Empleado {
    private Especialidad especialidad;
    private String whatsapp;

    public Entrenador(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado, double sueldo, String horarioTrabajo, int horasExtras, int feriadosTrabajados, int diasVacaciones, Especialidad especialidad, String whatsapp) {
        super(id, nombre, dni, direccion, telefono, email, eliminado, sueldo, horarioTrabajo, horasExtras, feriadosTrabajados, diasVacaciones);
        this.especialidad = especialidad;
        this.whatsapp = whatsapp;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
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


