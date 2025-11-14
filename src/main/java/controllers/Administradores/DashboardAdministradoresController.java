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
    private Button btnAdministradores;

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
    private GestionUsuarios gestorDatos;
    @FXML
    public void initialize() {
        this.gestorDatos = new GestionUsuarios();
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
        lblNombreUsuario.setText(admin.getName() + " " + admin.getId());
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/Administradores/RegistroEmpleados.fxml"));
        AnchorPane panelRegistrarEmpleadosFXML = loader.load();
        RegistroEmpleadosController controller =loader.getController();
        controller.setPanelContenido(panelContenido);
        GestionUsuarios gestorDatos = new GestionUsuarios();
        controller.setGestionUsuarios(this.gestorDatos);
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(panelRegistrarEmpleadosFXML);
    }
    @FXML
    void cargarEmpleados() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/Administradores/TablaEmpleados.fxml"));
        AnchorPane panelCargarEmpleadosFXML = loader.load();
        TablaEmpleadosController controller = loader.getController();
        GestionUsuarios gestorDatos = new GestionUsuarios();
        gestorDatos.cargarUsuarios();
        controller.setGestionUsuarios(gestorDatos);
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

    @FXML
    void cargarAdministradores() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/Administradores/RegistroAdministradores.fxml"));
        AnchorPane panelCargarAdministradoresFXML = loader.load();
        RegistroAdministradoresController controller = loader.getController();
        GestionUsuarios gestorDatos = new GestionUsuarios();
        gestorDatos.cargarUsuarios();
        controller.setGestionUsuarios(gestorDatos);
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(panelCargarAdministradoresFXML);

    }

}
