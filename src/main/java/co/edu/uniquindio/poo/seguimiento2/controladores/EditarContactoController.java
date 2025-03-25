package co.edu.uniquindio.poo.seguimiento2.controladores;

import co.edu.uniquindio.poo.seguimiento2.modelo.Contacto;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;

public class EditarContactoController {

    @FXML private TextField campoNombre;
    @FXML private TextField campoApellido;
    @FXML private TextField campoTelefono;
    @FXML private TextField campoEmail;
    @FXML private TextField campoDireccion;
    @FXML private DatePicker campoFechaNacimiento;
    @FXML private Button botonSeleccionarFoto;
    @FXML private ImageView vistaPreviaFoto;

    private Contacto contactoActual;
    private Runnable callbackActualizarTabla;
    private String rutaFotoSeleccionada;

    public void inicializarDatos(Contacto contacto, Runnable callback) {
        this.contactoActual = contacto;
        this.callbackActualizarTabla = callback;
        this.rutaFotoSeleccionada = contacto.getFotoPerfil();

        campoNombre.setText(contacto.getNombre());
        campoApellido.setText(contacto.getApellido());
        campoTelefono.setText(contacto.getTelefono());
        campoEmail.setText(contacto.getEmail());
        campoDireccion.setText(contacto.getDireccion());
        campoFechaNacimiento.setValue(contacto.getFechaNacimiento());
        if (contacto.getFotoPerfil() != null && !contacto.getFotoPerfil().isEmpty()) {
            vistaPreviaFoto.setImage(new javafx.scene.image.Image("file:" + contacto.getFotoPerfil()));
        }
    }

    @FXML
    private void seleccionarFoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Foto de Perfil");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        File archivoSeleccionado = fileChooser.showOpenDialog(botonSeleccionarFoto.getScene().getWindow());
        if (archivoSeleccionado != null) {
            rutaFotoSeleccionada = archivoSeleccionado.getAbsolutePath();
            vistaPreviaFoto.setImage(new javafx.scene.image.Image(archivoSeleccionado.toURI().toString()));
        }
    }

    @FXML
    private void actualizarDatosUsuario() throws Exception {
        try {
            Contacto datosNuevos = new Contacto(campoNombre.getText(), campoApellido.getText(), campoTelefono.getText(), campoEmail.getText(), campoDireccion.getText(), campoFechaNacimiento.getValue());
            datosNuevos.setFotoPerfil(rutaFotoSeleccionada);
            GestorContactosController gestor = new GestorContactosController();
            gestor.actualizarContacto(contactoActual, datosNuevos);
            callbackActualizarTabla.run();
            cerrarVentana();
            mostrarAlertaInfo("Éxito", "Contacto actualizado correctamente.");
        } catch (Exception e) {
            mostrarAlertaError("Error al Actualizar", e.getMessage());
        }
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) campoNombre.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void mostrarAlertaInfo(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}