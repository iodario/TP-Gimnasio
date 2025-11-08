package Clases;

import Enumeradores.Actividad;

public class Entrenador extends Empleado {
    private Actividad especialidad;
    private String whatsapp;

    public Entrenador(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado, double sueldo, String horarioTrabajo, int horasExtras, int feriadosTrabajados, int diasVacaciones, Actividad especialidad, String whatsapp) {
        super(id, nombre, dni, direccion, telefono, email, eliminado, sueldo, horarioTrabajo, horasExtras, feriadosTrabajados, diasVacaciones);
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


