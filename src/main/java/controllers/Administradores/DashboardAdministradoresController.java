package controllers.Administradores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Usuarios.Admin;
import models.Usuarios.GestionUsuarios;

import java.io.IOException;
import java.util.Objects;

public class DashboardAdministradoresController {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnEmpleados;

    @FXML
    private Button btnRegistroEmpleados;

    @FXML
    private AnchorPane panelContenido;

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private BorderPane mainBorderPane;
    private Admin admin;
    private GestionUsuarios gestionUsuarios;

    public void setAdmin(Admin admin) {
        this.admin = admin;
        lblNombreUsuario.setText(admin.getName() + " " + admin.getId());
    }

    public void setGestor(GestionUsuarios gestor) {
        this.gestionUsuarios = gestor;
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    void OnRegistrarEmpleados() throws IOException {
        AnchorPane panelRegistrarEmpleadosFXML = FXMLLoader. load(getClass().getResource("/com/example/proyectofinal/Administradores/RegistroEmpleados.fxml"));
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(panelRegistrarEmpleadosFXML);
    }
    @FXML
    void cargarEmpleados() throws IOException {
        AnchorPane panelCargarEmpleadosFXML = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/Administradores/TablaEmpleados.fxml"));
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(panelCargarEmpleadosFXML);
    }
    @FXML
    void onCerrarSesion(ActionEvent event){
        Node source = (Node)event.getSource();
        Stage stageActual = (Stage)  source.getScene().getWindow();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/proyectofinal/NewLogin.fxml"));
            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(root));
            loginStage.show();
        } catch(IOException e){
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo encontrar la vista.", Alert.AlertType.ERROR);
        }
        stageActual.close();
    }

}
