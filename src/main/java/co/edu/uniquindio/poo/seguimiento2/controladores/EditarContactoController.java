package co.edu.uniquindio.poo.seguimiento2.controladores;

import co.edu.uniquindio.poo.seguimiento2.modelo.Contacto;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

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
    private void actualizarDatosUsuario() throws Exception {
        String nuevoNombre = campoNombre.getText();
        String nuevoApellido = campoApellido.getText();
        String nuevoTelefono = campoTelefono.getText();
        String nuevoEmail = campoEmail.getText();
        String nuevaDireccion = campoDireccion.getText();
        LocalDate nuevaFechaNacimiento = campoFechaNacimiento.getValue();

        GestorContactosController gestor = new GestorContactosController();
        gestor.actualizarContacto(contactoActual, nuevoNombre, nuevoApellido, nuevoTelefono, nuevoEmail, nuevaDireccion, nuevaFechaNacimiento);
        callbackActualizarTabla.run();
        cerrarVentana();
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) campoNombre.getScene().getWindow();
        stage.close();
    }
}