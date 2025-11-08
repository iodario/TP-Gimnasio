package Gestores;

import Interfaces.Claveable;
import Excepciones.DatoInvalidoException;
import Excepciones.UsuarioInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Gestor<T extends Claveable> {

    private List<T> lista = new ArrayList<>();

    //buscar por clave
    //busca una clave pasada por parametro si se encuentra en la lista de elementos
    //devuelve null si: es nula, si esta en blanco o si no la encuentra
    //si la encuentra devuelve el elemento que contiene esa clave, usando getClave( )


    public T buscarPorClave(String clave) {
        if (clave == null || clave.isBlank()) return null;

        String k = clave.trim();
        for (T e : lista) {
            if (e.getClave() != null && e.getClave().equals(k)) {
                return e;    // actua como un true SI LO ENCONTRO
            }
        }
        return null;   // actua como un false, que indica que no lo encontro dentro la lista
    }

    //agregar entidad
    public void agregar (T entidad) throws DatoInvalidoException {
        if (entidad == null) {
            throw new DatoInvalidoException("El dato ingresado es invalido");
        }
        String claveEntidad = entidad.getClave();
        if (claveEntidad == null || claveEntidad.isBlank()) {
            throw new DatoInvalidoException("El dato ingresado es invalido");
        }
        if (buscarPorClave(claveEntidad) != null) {
            throw new DatoInvalidoException("Ya existe ese dato ");
        }
        lista.add(entidad);

    }



    // Eliminar por clave (equivale a tu eliminarSocioPorDni)
    public void eliminarPorClave(String clave) throws UsuarioInvalidoException {
        if (clave == null || clave.isBlank()) {
            throw new UsuarioInvalidoException("Debe ingresar una clave válida...");
        }
        T encontrada = buscarPorClave(clave);
        if (encontrada == null) {
            throw new UsuarioInvalidoException("No se encontró entidad con esa clave.");
        }
        lista.remove(encontrada);
        System.out.println("Entidad eliminada correctamente");
    }


    //listar entidades
        // Listado genérico (similar a tu listarSocios)
        public void listar() {
            if (lista.isEmpty()) {
                System.out.println("La lista esta vacia");
            } else {
                System.out.println("LISTA:");
                for (T e : lista) System.out.println("- " + e);
            }
        }


    }








