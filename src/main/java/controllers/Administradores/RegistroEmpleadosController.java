package controllers.Administradores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Usuarios.GestionUsuarios;

public class RegistroEmpleadosController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TextField txtContraseña;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtWorkerId;
    private GestionUsuarios gestionUsuarios;
    private AnchorPane panelContenido;
    public void setGestionUsuarios(GestionUsuarios gestor) {
        this.gestionUsuarios = gestor;
    }
    public void setPanelContenido(AnchorPane panelContenido) {
        this.panelContenido = panelContenido;
    }

    @FXML
    void onCancelar() {
        if(panelContenido != null) {
            panelContenido.getChildren().clear();
        }
    }

    @FXML
    void onRegistrarEmpleado(ActionEvent event) {
        String id = txtIdentificacion.getText().trim();
        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();
        String contraseña = txtContraseña.getText();
        String workerId = txtWorkerId.getText().trim();

        if (id.isEmpty() || nombre.isEmpty() || email.isEmpty() || contraseña.isEmpty() || workerId.isEmpty()) {
            mostrarAlerta("Error de Validación", "Por favor, complete todos los campos obligatorios.", Alert.AlertType.WARNING);
            return;
        }
        if(gestionUsuarios != null){
            boolean registrado = gestionUsuarios.addCashier(id, nombre, email, contraseña, workerId);
            if (registrado) {
                mostrarAlerta("Éxito", "Cajero registrado correctamente.", Alert.AlertType.INFORMATION);
                limpiarCampos();
            } else {
                mostrarAlerta("Error", "No se pudo registrar el Cajero. Posiblemente el ID o Email ya existen.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Error Interno", "El gestor de usuarios no ha sido inicializado.", Alert.AlertType.ERROR);
        }
        }
    private void limpiarCampos() {
        txtIdentificacion.clear();
        txtNombre.clear();
        txtEmail.clear();
        txtContraseña.clear();
        txtWorkerId.clear();
    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}


