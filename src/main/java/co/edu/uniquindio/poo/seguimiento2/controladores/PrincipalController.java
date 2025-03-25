package co.edu.uniquindio.poo.seguimiento2.controladores;

import co.edu.uniquindio.poo.seguimiento2.modelo.Contacto;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static co.edu.uniquindio.poo.seguimiento2.GestorContactosApp.LlenarComboBox;

public class PrincipalController implements Initializable {
    private final GestorContactosController gestorContactosController;
    private final ObservableList<Contacto> listaContactos;

    ObservableList<String> categoriasFiltro = FXCollections.observableArrayList("Nombre", "Telefono");

    @FXML private TableView<Contacto> tablaContactos;
    @FXML private TableColumn<Contacto, String> columnaNombre;
    @FXML private TableColumn<Contacto, String> columnaApellido;
    @FXML private TableColumn<Contacto, String> columnaTelefono;
    @FXML private TableColumn<Contacto, String> columnaEmail;
    @FXML private TableColumn<Contacto, String> columnaDireccion;
    @FXML private TableColumn<Contacto, String> columnaFechaNacimiento;
    @FXML private TextField campoNombre, campoApellido, campoTelefono, campoEmail, campoDireccion;
    @FXML private DatePicker campoFechaNacimiento;
    @FXML private TextField campoFiltro;
    @FXML private ComboBox<String> categoriaFiltroComboBox;

    public PrincipalController() {
        this.gestorContactosController = new GestorContactosController();
        this.listaContactos = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnaNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnaApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        columnaTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        columnaEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        columnaDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));
        columnaFechaNacimiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaNacimiento().toString()));
        tablaContactos.setItems(listaContactos);

        campoFiltro.textProperty().addListener((obs, oldValue, newValue) -> filtrarContactos());

        tablaContactos.setRowFactory(tv -> {
            TableRow<Contacto> row = new TableRow<>();
            ContextMenu contextMenu = new ContextMenu();

            MenuItem delete = new MenuItem("Eliminar");
            MenuItem edit = new MenuItem("Editar");

            delete.setOnAction(event -> {
                try {
                    eliminarContacto();
                } catch (Exception e) {
                    mostrarAlertaError("Error al eliminar", e.getMessage());
                }
            });

            edit.setOnAction(event -> {
                Contacto contactoSeleccionado = row.getItem();
                if (contactoSeleccionado != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditarContacto.fxml"));
                        Parent parent = loader.load();
                        EditarContactoController controller = loader.getController();
                        controller.inicializarDatos(contactoSeleccionado, () -> tablaContactos.refresh());

                        Stage stage = new Stage();
                        stage.setTitle("Editar Contacto");
                        stage.setScene(new Scene(parent));
                        stage.show();
                    } catch (Exception e) {
                        mostrarAlertaError("Error al eliminar", e.getMessage());
                    }
                }
            });

            contextMenu.getItems().addAll(delete, edit);

            row.contextMenuProperty().bind(
                    Bindings.when(Bindings.isNotNull(row.itemProperty()))
                            .then(contextMenu)
                            .otherwise((ContextMenu)null));

            return row;
        });
    }

    @FXML
    private void agregarContacto() throws Exception {
        try {
            Contacto contactoNuevo = new Contacto(campoNombre.getText(), campoApellido.getText(), campoTelefono.getText(), campoEmail.getText(), campoDireccion.getText(), campoFechaNacimiento.getValue());
            gestorContactosController.agregarContacto(contactoNuevo.getNombre(), contactoNuevo.getApellido(), contactoNuevo.getTelefono(), contactoNuevo.getEmail(), contactoNuevo.getDireccion(), contactoNuevo.getFechaNacimiento());
            listaContactos.add(contactoNuevo);
            limpiarCampos();
            mostrarAlertaInfo("Hecho!", "Contacto creado correctamente.");
        } catch (Exception e) {
            mostrarAlertaError("Error al crear el contacto", e.getMessage());
        }
    }

    @FXML
    private void eliminarContacto() throws Exception {
        try {
            Contacto contactoSeleccionado = tablaContactos.getSelectionModel().getSelectedItem();
            if (contactoSeleccionado != null) {
                gestorContactosController.eliminarContacto(contactoSeleccionado.getNombre(), contactoSeleccionado.getTelefono());
                listaContactos.remove(contactoSeleccionado);
                mostrarAlertaInfo("Hecho!", "Contacto eliminado correctamente.");
            }
        } catch (Exception e){
            mostrarAlertaError("Error al eliminar", e.getMessage());
        }
    }

    public void categoriaFiltrar(Event event) {
        LlenarComboBox(categoriaFiltroComboBox, categoriasFiltro);
    }

    private void filtrarContactos() {
        String filtro = campoFiltro.getText().toLowerCase();
        String criterio = categoriaFiltroComboBox.getValue();

        if (filtro.isEmpty()) {
            tablaContactos.setItems(listaContactos);
        } else {
            ObservableList<Contacto> contactosFiltrados = listaContactos.stream()
                    .filter(contacto -> {
                        if ("Nombre".equals(criterio)) {
                            return contacto.getNombre().toLowerCase().contains(filtro);
                        } else if ("Telefono".equals(criterio)) {
                            return contacto.getTelefono().toLowerCase().contains(filtro);
                        }
                        return false;
                    })
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            tablaContactos.setItems(contactosFiltrados);
        }
    }

    private void limpiarCampos() {
        campoNombre.clear();
        campoApellido.clear();
        campoTelefono.clear();
        campoEmail.clear();
        campoDireccion.clear();
        campoFechaNacimiento.setValue(null);
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
