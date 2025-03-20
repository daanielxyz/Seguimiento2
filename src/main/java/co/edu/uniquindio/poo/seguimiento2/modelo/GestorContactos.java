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
        
    }

}
