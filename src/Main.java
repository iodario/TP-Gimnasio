import Clases.*;
import Enumeradores.TipoMembresia;
import Enumeradores.Actividad;
import Excepciones.UsuarioInvalidoException;
import Excepciones.DatoInvalidoException;
import Gestores.GestionSocios;
import Gestores.GestorEmpleados;

public class Main {
    public static void main(String[] args) {
        System.out.println("== Proyecto - Gimnasio (pruebas básicas) ==");

        try {
            // gestores
            GestionSocios gestionSocios = new GestionSocios();
            GestorEmpleados gestorEmpleados = new GestorEmpleados();

            // --- TEST SOCIOS ---
            // Crear un socio (usar formato de DNI SIN espacios para la clave)
            Socio s1 = new Socio(
                    1,                      // id
                    "Pedro",                // nombre
                    "45654987",             // dni (clave)
                    "Av. Libertad 123",     // direccion
                    4811589,                // telefono
                    "pedro@gmail.com",      // email
                    false,                  // eliminado
                    TipoMembresia.FULL,     // membresia
                    "adelgazar",            // objetivo
                    true,                   // activo
                    "Lunes",                // agendaSemanal (ejemplo)
                    1                       // frecuenciaSemanal
            );

            System.out.println("\n-- Agrego socio y listo --");
            gestionSocios.agregar(s1);
            gestionSocios.listarSocios();

            System.out.println("\n-- Busco socio por DNI (45654987) --");
            Socio encontrado = gestionSocios.buscarSocioPorDni("45654987");
            System.out.println("Encontrado: " + (encontrado != null ? encontrado : "NO"));

            System.out.println("\n-- Elimino socio por DNI y vuelvo a listar --");
            gestionSocios.eliminarSocioPorDni("45654987");
            gestionSocios.listarSocios();

            // --- TEST EMPLEADOS ---
            System.out.println("\n-- Agrego empleados y listo --");
            Entrenador ent1 = new Entrenador(
                    10, "Lucia", "30111222",
                    "Calle Falsa 1", 115555, "lucia@gym.com",
                    false, "Jefe de sala", 1000, "hora de entrada", 0, 14,
                    Actividad.FUNCIONAL, "1122334455"
            );
            Recepcionista r1 = new Recepcionista(
                    11, "Ana", "30111333",
                    "Calle Real 2", 116666, "ana@gym.com",
                    false, "Recepcion", 10005, "horario", 0, 14,
                    25);

            gestorEmpleados.agregar(ent1);
            gestorEmpleados.agregar(r1);
            // listar() viene del gestor generico
            gestorEmpleados.listar();

            System.out.println("\n-- Buscar empleado por clave (dni) --");
            // Si el Gestor implementa buscarPorClave puedes hacer:
            // System.out.println("Empleado: " + gestorEmpleados.buscarPorClave(\"30111222\"));
            // Sino, implementar en GestorEmpleados delegando a Gestor.buscarPorClave

            // Ejemplo de eliminar (si Gestor tiene eliminarPorClave):
            gestorEmpleados.eliminarPorClave("30111333");
            System.out.println("\n-- Lista empleados tras eliminar --");
            gestorEmpleados.listar();

        } catch (UsuarioInvalidoException | DatoInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            // captura general para que Main no muera sin info
            System.out.println("Error inesperado: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
        }
    }
}