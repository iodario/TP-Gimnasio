package Gestores;

import Clases.Claveable;
import Clases.Socio;
import Excepciones.UsuarioInvalidoException;

import java.util.ArrayList;
import java.util.List;


public class GestionSocios extends GestionGimnasio<Socio> {
    private List<Socio> listaSocios = new ArrayList<>();
    //FUNCIONES

    public Socio buscarSocioPorDni(String dato){

        return buscarPorClave(dato);
    }

    public void agregarSocio(Socio s) throws UsuarioInvalidoException {
        // Validaciones extra específicas de Socio (si querés):
        if (s != null && (s.getNombre() == null || s.getNombre().isBlank())) {
            throw new UsuarioInvalidoException("El nombre del socio es obligatorio.");
        }
        super.agregar(s); // usa la lógica genérica (null, clave, duplicado)
    }
    public void eliminarSocioPorDni(String dni) throws UsuarioInvalidoException {
        super.eliminarPorClave(dni);
    }
    public void listarSocios() { super.listar(); }
}




