package Clases;

import Enumeradores.MedioDePago;
import Enumeradores.TipoMembresia;

import java.time.LocalDate;
import java.time.YearMonth;

public class Pago extends Socio{
    private int id;
    private int socioId;
    private YearMonth periodo;
    private double monto;
    private MedioDePago medio;
    private LocalDate fechaPago;

    public Pago(int id, String nombre, String dni, String direccion, int telefono, String email, boolean eliminado, TipoMembresia membresia, String objetivo, boolean activo, String agendaSemanal, int frecuenciaSemanal, int id1, int socioId, YearMonth periodo, double monto, MedioDePago medio, LocalDate fechaPago) {
        super(id, nombre, dni, direccion, telefono, email, eliminado, membresia, objetivo, activo, agendaSemanal, frecuenciaSemanal);
        this.id = id1;
        this.socioId = socioId;
        this.periodo = periodo;
        this.monto = monto;
        this.medio = medio;
        this.fechaPago = fechaPago;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSocioId() {
        return socioId;
    }

    public void setSocioId(int socioId) {
        this.socioId = socioId;
    }

    public YearMonth getPeriodo() {
        return periodo;
    }

    public void setPeriodo(YearMonth periodo) {
        this.periodo = periodo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public MedioDePago getMedio() {
        return medio;
    }

    public void setMedio(MedioDePago medio) {
        this.medio = medio;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

}
