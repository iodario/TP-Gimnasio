package Clases;

import Enumeradores.MedioDePago;
import Enumeradores.TipoMembresia;
import Interfaces.Claveable;

import java.time.LocalDate;
import java.time.YearMonth;

public class Pago implements Claveable {
    private String id;
    private String socioDni;
    private String periodo;
    private double monto;
    private String medioDePago;  // "EFECTIVO // TARJETA // TRANSFERENCIA
    // TODO: Ver si agregamos: "fechaPago"


    public Pago() {
    }

    public Pago(String id, String medioDePago, double monto, String periodo, String socioDni) {
        this.id = id;
        this.medioDePago = medioDePago;
        this.monto = monto;
        this.periodo = periodo;
        this.socioDni = socioDni;
    }

    @Override
    public String getClave() { return id; }

    @Override
    public String toString() {
        return "Pago{" + id +
                " socio=" + socioDni +
                " periodo=" + periodo +
                " monto=" + monto +
                " medio=" + medioDePago +
                "}";
    }

}
