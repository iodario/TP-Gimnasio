package Gestores;

import Clases.Empleado;
import Clases.Socio;

import java.util.ArrayList;
import java.util.List;

public class GestorEmpleados extends Gestor<Empleado> {
    //FUNCIONES

    // ==buscar==//
    public Empleado buscarEmpleadoPorDni(String dato){
        return buscarPorClave(dato);
    }

    // ==agregar==//
    public void agregarEmpleado(Empleado e) {
        super.agregar(e);
    }

    //==eliminar==//
    public void eliminarEmpleadoPorDni(String dni) {
        super.eliminarPorClave(dni);
    }
    public void listarEmpleados() { super.listar(); }


}



