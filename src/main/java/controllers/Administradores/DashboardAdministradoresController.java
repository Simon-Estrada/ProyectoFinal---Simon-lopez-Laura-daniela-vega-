package controllers.Administradores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.Usuarios.Admin;
import models.Usuarios.Cashier;
import models.Usuarios.GestionUsuarios;

public class DashboardAdministradoresController {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnEmpleados;

    @FXML
    private Button btnRegistroEmpleados;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private BorderPane mainBorderPane;
    private Admin admin;
    private GestionUsuarios gestionUsuarios;

    public void setAdmin(Admin admin){
        this.admin = admin;
        lblNombreUsuario.setText(admin.getName()+" "+admin.getId());
    }
    public void setGestor(GestionUsuarios gestor){
        this.gestionUsuarios = gestor;
    }

    @FXML
    void OnRegistrarEmpleados(ActionEvent event) {

    }

    @FXML
    void cargarEmpleados(ActionEvent event) {

    }

    @FXML
    void onCerrarSesion(ActionEvent event) {

    }

}

