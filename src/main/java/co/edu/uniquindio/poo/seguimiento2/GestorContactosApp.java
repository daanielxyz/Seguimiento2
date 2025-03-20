package co.edu.uniquindio.poo.seguimiento2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GestorContactosApp extends Application {
        @Override
        public void start(Stage stage) throws Exception {


            FXMLLoader loader = new FXMLLoader(GestorContactosApp.class.getResource("/principal.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Test");
            stage.show();
        }
        public static void main(String[] args) {
            launch(GestorContactosApp.class, args);
        }
}
