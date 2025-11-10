package Gestores;

import Interfaces.Claveable;
import Excepciones.DatoInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Gestor<T extends Claveable> {

    private List<T> lista = new ArrayList<>();

    /*buscar por clave
      busca una clave pasada por parametro si se encuentra en la lista de elementos
      devuelve null si: es nula, si esta en blanco o si no la encuentra
      si la encuentra devuelve el elemento que contiene esa clave, usando getClave( )*/


    public T buscarPorClave(String clave) {
        if (clave == null || clave.trim().isEmpty()) return null;
        String k = clave.trim();
        for (int i = 0; i < lista.size(); i++) {
            T e = lista.get(i);
            if (e.getClave() != null && e.getClave().equalsIgnoreCase(k)) {
                return e;
            }
        }
        return null;
    }

    //agregar entidad
    public void agregar(T entidad) throws DatoInvalidoException {
        if (entidad == null) throw new DatoInvalidoException("Entidad nula");
        String clave = entidad.getClave();
        if (clave == null || clave.trim().isEmpty())
            throw new DatoInvalidoException("Clave inválida");
        if (buscarPorClave(clave) != null)
            throw new DatoInvalidoException("Duplicado: ya existe clave " + clave);
        lista.add(entidad);
    }


    // Eliminar por clave (equivale a tu eliminarSocioPorDni)
    public boolean eliminarPorClave(String clave) {
        if (clave == null) return false;
        for (int i = 0; i < lista.size(); i++) {
            if (clave.equalsIgnoreCase(lista.get(i).getClave())) {
                lista.remove(i);
                return true;
            }
        }
        return false;
    }

    //listar entidades
    public List<T> listar() {
        return new ArrayList<T>(lista); // copia defensiva
    }

    public int cantidad() {
        return lista.size();
    }

}









