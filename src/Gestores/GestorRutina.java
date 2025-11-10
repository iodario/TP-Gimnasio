package Gestores;

import Clases.Rutina;
import Clases.Socio;
import Excepciones.DatoInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class GestorRutina extends Gestor<Rutina> {

    private List<Socio> socios; // referencia a los socios para validar

    public GestorRutina(List<Socio> socios) {
        super();
        this.socios = socios;
    }

    @Override
    public void agregar(Rutina rutina) throws DatoInvalidoException {
        if (rutina == null) {
            throw new DatoInvalidoException("La Rutina no existe, NO fue agregada");
        }

        String claveSocio = rutina.getClaveSocio();
        Socio socio = buscarSocioPorClave(claveSocio);
        if (socio == null) {
            throw new DatoInvalidoException("No existe un socio con la clave: " + claveSocio);
        }

        super.agregar(rutina);
        System.out.println("\n La Rutina fue agregada correctamente para el socio: " + socio.getNombre());
    }

    public List<Rutina> buscarRutinasPorSocio(String claveSocio) {
        List<Rutina> resultado = new ArrayList<>();
        if (claveSocio == null || claveSocio.trim().isEmpty()) return resultado;

        for (Rutina r : listar()) {
            if (r.getClaveSocio().equalsIgnoreCase(claveSocio)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public void mostrarRutinasPorSocio(String claveSocio) {
        List<Rutina> lista = buscarRutinasPorSocio(claveSocio);
        if (lista.isEmpty()) {
            System.out.println("El socio con la clave " + claveSocio + " no tiene rutinas asignadas.");
        } else {
            System.out.println("\n ~ Rutinas del socio " + claveSocio + ": \n");
            for (Rutina r : lista) {
                System.out.println("- " + r.toString());
            }
        }
    }

    private Socio buscarSocioPorClave(String clave) {
        if (clave == null || clave.trim().isEmpty()) return null;
        for (Socio s : socios) {
            if (s.getClave().equalsIgnoreCase(clave)) {
                return s;
            }
        }
        return null;
    }
}
