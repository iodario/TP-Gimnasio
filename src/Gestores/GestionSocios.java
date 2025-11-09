package Gestores;

import Clases.Socio;
import Excepciones.DatoInvalidoException;

import java.util.List;


public class GestionSocios extends Gestor<Socio> {


    //FUNCIONES

    // ==Buscar Socios==//
    public Socio buscarSocioPorDni(String dni){
        return buscarPorClave(dni);
    }

    // ==Agregar Socios==//
    public void altaSocio(Socio s) throws DatoInvalidoException {

        super.agregar(s); // usa la lógica genérica (null, clave, duplicado)
    }

    //==Eliminar Socios==//
    public boolean bajaSocio(String dni) {
        return super.eliminarPorClave(dni);
    }

    //==Listar Socios==//
    public List<Socio> listarSocios() {
        return super.listar();
    }
}





