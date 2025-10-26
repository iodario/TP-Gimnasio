import Enumeradores.TipoMembresia;
import Excepciones.UsuarioInvalidoException;
import Gestores.GestionSocios;
import Clases.*;


public class Main {
    public static void main(String[] args) {
        System.out.println("Proyecto final - Gimnasio");

        try {
            GestionSocios gestion = new GestionSocios();

            Socio s1 = new Socio(1, "Pedro", "45654987", "Av. Libertad 123",
                    4811589, "pedro@gmail.com", false,
                    TipoMembresia.FULL, "adelgazar", true, "Lunes", 1);

            gestion.agregarSocio(s1);
            gestion.listarSocios();

            gestion.eliminarSocioPorDni("4565 4987");
            gestion.listarSocios();

        } catch (UsuarioInvalidoException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}