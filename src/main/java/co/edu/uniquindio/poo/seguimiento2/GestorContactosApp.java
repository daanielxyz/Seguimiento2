package co.edu.uniquindio.poo.seguimiento2;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;


/*DUDAS A RESOLVER:
    -error en la validacion del telefono repetido al editar un contacto
    -En la funcionalidad de buscar contacto por nombre, es una alerta o un stage? si es alerta no se puede mostrar la foto
 */

public class GestorContactosApp extends Application {
        @Override
        public void start(Stage stage) throws Exception {


            FXMLLoader loader = new FXMLLoader(GestorContactosApp.class.getResource("/Principal.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            String css = this.getClass().getResource("/style.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.setTitle("Gestor de Contactos");
            stage.show();
        }
        public static void main(String[] args) {
            launch(GestorContactosApp.class, args);
        }
        public static void LlenarComboBox(ComboBox<String> llenarCombo, ObservableList<String> infoCombo){
            llenarCombo.setItems(infoCombo);
        }
}
