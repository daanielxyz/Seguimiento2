module co.edu.uniquindio.poo.seguimiento2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.seguimiento2 to javafx.fxml;
    exports co.edu.uniquindio.poo.seguimiento2;
    requires static lombok;
}