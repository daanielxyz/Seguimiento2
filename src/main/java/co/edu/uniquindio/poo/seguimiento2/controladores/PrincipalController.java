package co.edu.uniquindio.poo.seguimiento2.controladores;

import co.edu.uniquindio.poo.seguimiento2.modelo.Contacto;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {
    private final GestorContactosController gestorContactosController;
    private final ObservableList<Contacto> listaContactos;

    @FXML private TableView<Contacto> tablaContactos;
    @FXML private TableColumn<Contacto, String> columnaNombre;
    @FXML private TableColumn<Contacto, String> columnaApellido;
    @FXML private TableColumn<Contacto, String> columnaTelefono;
    @FXML private TableColumn<Contacto, String> columnaEmail;
    @FXML private TableColumn<Contacto, String> columnaDireccion;
    @FXML private TableColumn<Contacto, String> columnaFechaNacimiento;
    @FXML private TextField campoNombre, campoApellido, campoTelefono, campoEmail, campoDireccion;
    @FXML private DatePicker campoFechaNacimiento;

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

        tablaContactos.setRowFactory(tv -> {
            TableRow<Contacto> row = new TableRow<>();
            ContextMenu contextMenu = new ContextMenu();

            MenuItem delete = new MenuItem("Eliminar");
            MenuItem edit = new MenuItem("Editar");

            delete.setOnAction(event -> {
                System.out.println(row.getItem().email);
            });

            edit.setOnAction(event -> {
                System.out.println(row.getItem().email);
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
        Contacto contactoNuevo = new Contacto(campoNombre.getText(), campoApellido.getText(), campoTelefono.getText(), campoEmail.getText(), campoDireccion.getText(), campoFechaNacimiento.getValue());
        gestorContactosController.agregarContacto(contactoNuevo.getNombre(), contactoNuevo.getApellido(), contactoNuevo.getTelefono(), contactoNuevo.getEmail(), contactoNuevo.getDireccion(), contactoNuevo.getFechaNacimiento());
        listaContactos.add(contactoNuevo);
    }


}
