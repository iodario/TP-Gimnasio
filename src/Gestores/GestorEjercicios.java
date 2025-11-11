package Gestores;

import Clases.Ejercicio;
import Clases.Rutina;
import Excepciones.DatoInvalidoException;

import java.util.List;

/**
 * Administra los ejercicios de UNA rutina.
 * Extiende Gestor<Ejercicio> para reutilizar agregar/buscar/eliminar/listar.
 * Mantiene sincronizada la lista interna de la Rutina.
 */
public class GestorEjercicios extends Gestor<Ejercicio> {

    private Rutina rutina;

    public GestorEjercicios(Rutina rutina) {
        super();
        this.rutina = rutina;

        // Sincroniza el estado inicial: carga los ejercicios existentes de la rutina
        List<Ejercicio> existentes = rutina.getEjercicios();
        if (existentes != null) {
            for (Ejercicio e : existentes) {
                try { super.agregar(e); } catch (DatoInvalidoException ignore) {}
            }
        }
    }




    public Rutina getRutina() {
        return rutina;
    }

    // Funciones
    @Override
    public void agregar(Ejercicio ejercicio) throws DatoInvalidoException {
        super.agregar(ejercicio);               // usa validaciones del Gestor base (clave, duplicados)
        rutina.getEjercicios().add(ejercicio);  // sincroniza en la Rutina
    }

    @Override
    public boolean eliminarPorClave(String clave) {
        boolean ok = super.eliminarPorClave(clave); // elimina del gestor base
        if (ok) {
            // elimina también de la lista de la rutina
            List<Ejercicio> lista = rutina.getEjercicios();
            for (int i = 0; i < lista.size(); i++) {
                Ejercicio e = lista.get(i);
                String k = (e.getClave() == null) ? null : e.getClave();
                if (k != null && k.equalsIgnoreCase(clave)) {
                    lista.remove(i);
                    break;
                }
            }
        }
        return ok;
    }


}
