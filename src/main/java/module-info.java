module com.example.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;



    opens com.example.proyectofinal to javafx.fxml;
    exports com.example.proyectofinal;
    exports controllers;
    opens controllers to javafx.fxml;
    opens controllers.Administradores to javafx.fxml;
    opens controllers.Cajeros to javafx.fxml;
    opens controllers.Clientes to javafx.fxml;

}