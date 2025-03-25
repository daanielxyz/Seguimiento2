package co.edu.uniquindio.poo.seguimiento2.modelo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
        if(!listaContactos.contains(contacto)) {
            listaContactos.add(contacto);
        } else{
            throw new Exception("Ya existe un contacto con ese nombre");
        }
    }

    public void eliminarContacto(Contacto contacto) throws Exception {
        if(!listaContactos.contains(contacto)) {
            throw new Exception("No existe un contacto con ese nombre");
        } else{
            listaContactos.remove(contacto);
        }
    }

    public Contacto buscarContacto(String nombre, String telefono) throws Exception {
        return listaContactos.stream().filter(contacto -> contacto.getNombre().equalsIgnoreCase(nombre)&&contacto.getTelefono().equals(telefono))
                .findFirst().orElseThrow(() -> new Exception("No existe el contacto indicado"));
    }

    public Contacto buscarContactoPorNombre(String nombre) {
        return listaContactos.stream().filter(c -> c.getNombre().equalsIgnoreCase(nombre)).findFirst().orElse(null);
    }
}
