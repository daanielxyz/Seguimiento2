package co.edu.uniquindio.poo.seguimiento2.modelo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class GestorContactos {

    private List<Contacto> listaContactos;

    public GestorContactos() {
        this.listaContactos = new ArrayList<>();
    }

    public void agregarContacto(Contacto contacto) throws Exception {

        if(buscarPorTelefono(contacto.getTelefono())){
            throw new Exception("Ya existe un contacto con el telefono");
        }

        validarContacto(contacto);
        listaContactos.add(contacto);
    }

    public void actualizarContacto(Contacto contactoActual, Contacto contactoDatosActualizados) throws Exception {
        validarContacto(contactoDatosActualizados);
        validarTelefonoEditar(contactoDatosActualizados);

        for(int i = 0 ; i < listaContactos.size() ; i++){
            if(contactoActual.getTelefono().equals(listaContactos.get(i).getTelefono())){
                //validarContacto(contactoActual);
                contactoActual.setNombre(contactoDatosActualizados.getNombre());
                contactoActual.setApellido(contactoDatosActualizados.getApellido());
                contactoActual.setTelefono(contactoDatosActualizados.getTelefono());
                contactoActual.setDireccion(contactoDatosActualizados.getDireccion());
                contactoActual.setEmail(contactoDatosActualizados.getEmail());
                contactoActual.setFechaNacimiento(contactoDatosActualizados.getFechaNacimiento());
                contactoActual.setFotoPerfil(contactoDatosActualizados.getFotoPerfil());
                listaContactos.set(i, contactoActual);
                break;
            }
        }

    }

    public void eliminarContacto(String nombre, String telefono) throws Exception {
        Contacto contactoEliminar = buscarContacto(nombre, telefono);
        if (contactoEliminar == null) {
            throw new Exception("No existe el contacto indicado");
        } else {
            listaContactos.remove(contactoEliminar);
        }
    }

    public Contacto buscarContacto(String nombre, String telefono) throws Exception {
        return listaContactos.stream().filter(contacto -> contacto.getNombre().equalsIgnoreCase(nombre)&&contacto.getTelefono().equals(telefono))
                .findFirst().orElseThrow(() -> new Exception("No existe el contacto indicado"));
    }

    public Contacto buscarContactoPorNombre(String nombre) {
        return listaContactos.stream().filter(c -> c.getNombre().equalsIgnoreCase(nombre)).findFirst().orElse(null);
    }

    public boolean buscarPorTelefono(String telefono){
        return listaContactos.stream().anyMatch(c -> c.getTelefono().equals(telefono));
    }

    public void validarContacto(Contacto contacto) throws Exception {
        if (contacto.getNombre() == null || contacto.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacío.");
        } else if (contacto.getApellido() == null || contacto.getApellido().trim().isEmpty()) {
            throw new Exception("El apellido no puede estar vacío.");
        } else if (contacto.getTelefono() == null || contacto.getTelefono().trim().isEmpty()) {
            throw new Exception("El telefono no puede estar vacío.");
        }  else if (!contacto.getTelefono().matches("\\d+")) {
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

    public void validarTelefonoEditar(Contacto contacto) throws Exception {
        int indiceContacto = listaContactos.indexOf(contacto);
        for (int i = 0; i < listaContactos.size(); i++) {
            if(contacto.getTelefono().equals(listaContactos.get(i).getTelefono()) && i != indiceContacto){
                throw new Exception("El telefono a editar ya está el la lista");
            }
        }
    }
}
