package co.edu.uniquindio.poo.seguimiento2.controladores;

import co.edu.uniquindio.poo.seguimiento2.modelo.Contacto;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditarContactoController {

    @FXML private TextField campoNombre;
    @FXML private TextField campoApellido;
    @FXML private TextField campoTelefono;
    @FXML private TextField campoEmail;
    @FXML private TextField campoDireccion;
    @FXML private DatePicker campoFechaNacimiento;

    private Contacto contactoActual;
    private Runnable callbackActualizarTabla;

    public void inicializarDatos(Contacto contacto, Runnable callback) {
        this.contactoActual = contacto;
        this.callbackActualizarTabla = callback;

        campoNombre.setText(contacto.getNombre());
        campoApellido.setText(contacto.getApellido());
        campoTelefono.setText(contacto.getTelefono());
        campoEmail.setText(contacto.getEmail());
        campoDireccion.setText(contacto.getDireccion());
        campoFechaNacimiento.setValue(contacto.getFechaNacimiento());
    }

    @FXML
    private void guardarCambios() {
        contactoActual.setNombre(campoNombre.getText());
        contactoActual.setApellido(campoApellido.getText());
        contactoActual.setTelefono(campoTelefono.getText());
        contactoActual.setEmail(campoEmail.getText());
        contactoActual.setDireccion(campoDireccion.getText());
        contactoActual.setFechaNacimiento(campoFechaNacimiento.getValue());

        callbackActualizarTabla.run();

        cerrarVentana();
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) campoNombre.getScene().getWindow();
        stage.close();
    }
}
