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

    public void validarContacto(Contacto contacto) throws Exception {
        if (contacto.getNombre() == null || contacto.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacío.");
        } else if (contacto.getApellido() == null || contacto.getApellido().trim().isEmpty()) {
            throw new Exception("El apellido no puede estar vacío.");
        } else if (contacto.getTelefono() == null || contacto.getTelefono().trim().isEmpty()) {
            throw new Exception("El telefono no puede estar vacío.");
        } else if (gestorContactos.getListaContactos().stream().anyMatch(c -> c.getTelefono().equals(contacto.getTelefono()))) {
            throw new Exception("Ya existe un contacto con este numero telefónico.");
        } else if (!contacto.getTelefono().matches("\\d+")) {
            throw new Exception("El telefono no puede contener caracteres.");
        } else if (contacto.getEmail() == null || contacto.getEmail().trim().isEmpty()) {
            throw new Exception("El correo no puede estar vacío.");
        } else if (!contacto.getEmail().contains("@")) {
            throw new Exception("Ingrese un correo válido.");
        } else if (contacto.getDireccion() == null || contacto.getDireccion().trim().isEmpty()) {
            throw new Exception("La direccion no puede estar vacía.");
        } else if (contacto.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new Exception("Ingrese una fecha de nacimiento válida.");
        } else if (contacto.getFechaNacimiento() == null) {
            throw new Exception("Ingrese una fecha de nacimiento válida.");
        }
    }


    public void agregarContacto(String nombre, String apellido, String telefono, String email, String direccion, LocalDate fechaNacimiento, String fotoPerfil) throws Exception {
        Contacto nuevoContacto = new Contacto(nombre, apellido, telefono, email, direccion, fechaNacimiento);
        nuevoContacto.setFotoPerfil(fotoPerfil);
        validarContacto(nuevoContacto);
        gestorContactos.agregarContacto(nuevoContacto);
    }

    public void actualizarContacto(Contacto contactoActual, Contacto contactoDatosActualizados) throws Exception {
        validarContacto(contactoDatosActualizados);
        validarContacto(contactoActual);
        contactoActual.setNombre(contactoDatosActualizados.getNombre());
        contactoActual.setApellido(contactoDatosActualizados.getApellido());
        contactoActual.setTelefono(contactoDatosActualizados.getTelefono());
        contactoActual.setDireccion(contactoDatosActualizados.getDireccion());
        contactoActual.setEmail(contactoDatosActualizados.getEmail());
        contactoActual.setFechaNacimiento(contactoDatosActualizados.getFechaNacimiento());
        contactoActual.setFotoPerfil(contactoDatosActualizados.getFotoPerfil());
    }

    public void eliminarContacto(String nombre, String telefono) throws Exception {
        Contacto contactoEliminar = gestorContactos.buscarContacto(nombre, telefono);
        if (contactoEliminar == null) {
            throw new Exception("No existe el contacto indicado");
        } else {
            gestorContactos.eliminarContacto(contactoEliminar);
        }
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
