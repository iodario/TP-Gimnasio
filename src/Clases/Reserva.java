package Clases;

import Enumeradores.EstadoReserva;
import Enumeradores.TipoMembresia;
import Interfaces.Claveable;

import java.time.LocalDateTime;

public class Reserva implements Claveable {
    private String id;
    private String socioDni;
    private String claseId;
    private boolean activa = true;
    // TODO: Ver de agregar "fechaDeReserva"


    public Reserva() {

    }

    public Reserva(String id, String socioDni, String claseId) {
        this.id = id;
        this.socioDni = socioDni;
        this.claseId = claseId;
    }


    public boolean isActiva() {
        return activa;
    }

    public void cancelarReserva() {
        this.activa = false;
    }

    public String getSocioDni() {
        return socioDni;
    }

    public String getClaseId() {
        return claseId;
    }

    @Override
    public String getClave() {
        return id;
    }

    @Override
    public String toString() {
        return "Reserva{" + id + " socio=" + socioDni + " clase=" + claseId + " activa=" + activa + "}";
    }
}
