package Clases;

public class Empleado extends Persona {
    private double sueldo;
    private String horarioTrabajo;
    private int horasExtras;
    private int feriadosTrabajados;
    private int diasVacaciones;

    public Empleado(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado, double sueldo, String horarioTrabajo, int horasExtras, int feriadosTrabajados, int diasVacaciones) {
        super(id, nombre, dni, direccion, telefono, email, eliminado);
        this.sueldo = sueldo;
        this.horarioTrabajo = horarioTrabajo;
        this.horasExtras = horasExtras;
        this.feriadosTrabajados = feriadosTrabajados;
        this.diasVacaciones = diasVacaciones;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getHorarioTrabajo() {
        return horarioTrabajo;
    }

    public void setHorarioTrabajo(String horarioTrabajo) {
        this.horarioTrabajo = horarioTrabajo;
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    public int getFeriadosTrabajados() {
        return feriadosTrabajados;
    }

    public void setFeriadosTrabajados(int feriadosTrabajados) {
        this.feriadosTrabajados = feriadosTrabajados;
    }

    public int getDiasVacaciones() {
        return diasVacaciones;
    }

    public void setDiasVacaciones(int diasVacaciones) {
        this.diasVacaciones = diasVacaciones;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "sueldo=" + sueldo +
                ", horarioTrabajo='" + horarioTrabajo + '\'' +
                ", horasExtras=" + horasExtras +
                ", feriadosTrabajados=" + feriadosTrabajados +
                ", diasVacaciones=" + diasVacaciones +
                '}';
    }
}
