package co.edu.uniquindio.poo.seguimiento2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestApp extends Application {
        @Override
        public void start(Stage stage) throws Exception {


            FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("inicio.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Test");
            stage.show();
        }
        public static void main(String[] args) {
            launch(TestApp.class, args);
        }
}
