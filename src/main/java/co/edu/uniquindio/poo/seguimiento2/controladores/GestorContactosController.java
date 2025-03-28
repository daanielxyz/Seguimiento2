package co.edu.uniquindio.poo.seguimiento2.controladores;
import co.edu.uniquindio.poo.seguimiento2.modelo.Contacto;
import co.edu.uniquindio.poo.seguimiento2.modelo.GestorContactos;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GestorContactosController {

    private final GestorContactos gestorContactos;

    public GestorContactosController() {
        this.gestorContactos = new GestorContactos();
    }

    public void agregarContacto(String nombre, String apellido, String telefono, String email, String direccion, LocalDate fechaNacimiento, String fotoPerfil) throws Exception {
        Contacto nuevoContacto = new Contacto(nombre, apellido, telefono, email, direccion, fechaNacimiento);
        nuevoContacto.setFotoPerfil(fotoPerfil);
        gestorContactos.agregarContacto(nuevoContacto);
    }

    public void actualizarContacto(Contacto contactoActual, Contacto contactoDatosActualizados) throws Exception {
        gestorContactos.actualizarContacto(contactoActual, contactoDatosActualizados);
    }

    public void eliminarContacto(String nombre, String telefono) throws Exception {
        gestorContactos.eliminarContacto(nombre, telefono);
    }

    public Contacto buscarContactoPorNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre de búsqueda no puede estar vacío.");
        }
        Contacto contacto = gestorContactos.buscarContactoPorNombre(nombre);
        if (contacto == null) {
            throw new Exception("No se encontró un contacto con el nombre '" + nombre + "'.");
        }
        return contacto;
    }

}
