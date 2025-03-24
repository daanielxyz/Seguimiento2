package co.edu.uniquindio.poo.seguimiento2.controladores;
import co.edu.uniquindio.poo.seguimiento2.modelo.Contacto;
import co.edu.uniquindio.poo.seguimiento2.modelo.GestorContactos;
import java.time.LocalDate;
import java.util.List;

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
        } else if (gestorContactos.getListaContactos().stream().anyMatch(c -> c.getTelefono().equals(contacto.getTelefono()))){
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

    public void agregarContacto(String nombre, String apellido, String telefono, String email, String direccion, LocalDate fechaNacimiento) throws Exception {
        Contacto nuevoContacto = new Contacto(nombre, apellido, telefono, email, direccion, fechaNacimiento);
        validarContacto(nuevoContacto);
        gestorContactos.agregarContacto(nuevoContacto);
    }

    public void actualizarContacto(Contacto contacto, String nombreNuevo, String apellidoNuevo, String telefonoNuevo, String correoNuevo, String direccionNueva, LocalDate fechaNueva) throws Exception {
        validarContacto(contacto);
        Contacto nuevoContacto = new Contacto(nombreNuevo, apellidoNuevo, telefonoNuevo, correoNuevo, direccionNueva, fechaNueva);
        validarContacto(nuevoContacto);
        contacto.setNombre(nuevoContacto.getNombre());
        contacto.setApellido(nuevoContacto.getApellido());
        contacto.setTelefono(nuevoContacto.getTelefono());
        contacto.setEmail(nuevoContacto.getEmail());
        contacto.setDireccion(nuevoContacto.getDireccion());
        contacto.setFechaNacimiento(fechaNueva);
    }

    public void eliminarContacto(String nombre, String telefono) throws Exception {
        Contacto contactoEliminar = gestorContactos.buscarContacto(nombre, telefono);
        if (contactoEliminar == null) {
            throw new Exception("No existe el contacto indicado");
        } else {
            gestorContactos.eliminarContacto(contactoEliminar);
        }
    }

    public Contacto buscarContacto(String nombre, String telefono) throws Exception {
        return gestorContactos.buscarContacto(nombre, telefono);
    }

    public List<Contacto> obtenerContactos(){
        return gestorContactos.getListaContactos();
    }
}
