module com.example.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.example.proyectofinal;


    opens com.example.proyectofinal to javafx.fxml;
    exports com.example.proyectofinal;
    exports controllers;
    opens controllers to javafx.fxml;
}