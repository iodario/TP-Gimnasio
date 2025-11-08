package Gestores;

import Clases.Socio;
import Excepciones.UsuarioInvalidoException;


public class GestionSocios extends Gestor<Socio> {


    //FUNCIONES

    // ==buscar==//
    public Socio buscarSocioPorDni(String dato){
        return buscarPorClave(dato);
    }

    // ==agregar==//
    public void agregarSocio(Socio s) {

        super.agregar(s); // usa la lógica genérica (null, clave, duplicado)
    }

    //==eliminar==//
    public void eliminarSocioPorDni(String dni) {
        super.eliminarPorClave(dni);
    }
    public void listarSocios() { super.listar(); }
}





