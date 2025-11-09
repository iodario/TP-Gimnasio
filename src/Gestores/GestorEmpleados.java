package Gestores;

import Clases.Empleado;
import Excepciones.DatoInvalidoException;

import java.util.List;

public class GestorEmpleados extends Gestor<Empleado> {
    //FUNCIONES

    // ==Buscar Empleado==//
    public Empleado buscarEmpleadoPorLegajo(String legajo){
        return buscarPorClave(legajo);
    }

    // ==Agregar Empleado==//
    public void altaEmpleado(Empleado e) throws DatoInvalidoException {
        super.agregar(e);
    }

    //==Eliminar Empleado==//
    public boolean bajaEmpleado(String legajo) {
        return super.eliminarPorClave(legajo);
    }
    public List<Empleado> listarEmpleados() {
        return super.listar();
    }
}



