package Gestores;

import Clases.Socio;
import Excepciones.UsuarioInvalidoException;


import java.util.ArrayList;
import java.util.List;

public class GestionSocios {
    private List<Socio> listaSocios = new ArrayList<>();

    // FUNCIONES

    public void agregarSocio(Socio s) throws UsuarioInvalidoException{
        if (s == null){
            throw new UsuarioInvalidoException("El dato ingresado no es valido de un Socio");
        }

        for (Socio socio : listaSocios){
            if (socio.getDni().equalsIgnoreCase(s.getDni())){
                throw new UsuarioInvalidoException("Ya existe un Socio con ese DNI");
            }
        }
        listaSocios.add(s);
        System.out.println("Socio agregado correctamente");
    }

    public void eliminarSocioPorDni(String dni) throws UsuarioInvalidoException {
        if (dni == null || dni.isBlank()){
            throw new UsuarioInvalidoException("Debe ingresar un numero de DNI valido...");
        }

        boolean eliminado = false;
        for (Socio socio : listaSocios){
            if(socio.getDni().equalsIgnoreCase(dni)){
                listaSocios.remove(socio);
                eliminado = true;
                System.out.println("Socio Eliminado");
                break;
            }
            if (!eliminado) {
                throw new UsuarioInvalidoException("No se encontro un Socio con ese DNI");
            }
        }
    }

    public void listarSocios() {
        if (listaSocios.isEmpty()) {
            System.out.println("No hay socios registrados");
        } else {
            System.out.println(" LISTA DE SOCIOS \n");
            for (Socio s : listaSocios) {
                System.out.println("\n - Nombre: " + s.getNombre() + "\n - DNI: " + s.getDni());
            }
        }
    }


}
