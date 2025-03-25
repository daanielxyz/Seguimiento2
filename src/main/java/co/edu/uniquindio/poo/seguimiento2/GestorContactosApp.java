package co.edu.uniquindio.poo.seguimiento2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*DUDAS A RESOLVER:
    -la validacion de contacto existente tiene que ser solo por telefono o telefono y nombre?
    -error en la validacion del telefono repetido al editar un contacto
 */

public class GestorContactosApp extends Application {
        @Override
        public void start(Stage stage) throws Exception {


            FXMLLoader loader = new FXMLLoader(GestorContactosApp.class.getResource("/Principal.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Gestor de Contactos");
            stage.show();
        }
        public static void main(String[] args) {
            launch(GestorContactosApp.class, args);
        }
}
