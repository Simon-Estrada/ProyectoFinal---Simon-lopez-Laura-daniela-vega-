package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtContraseña;

    @FXML
    private TextField txtUsuario;

    @FXML
    private void onIniciarSesion(ActionEvent event) {
        String usuario = txtUsuario.getText();
        String contraseña = txtContraseña.getText();

        if(usuario.isEmpty()||contraseña.isEmpty()){
            mostrarAlerta("Error", "Por favor, complete todos los campos", Alert.AlertType.ERROR);
            return;
        }
        if(validarCredenciales(usuario, contraseña)){
            abrirDashboard();
        } else{
            mostrarAlerta("Error de autentificacion","Usuario o Contraseña incorrectos", Alert.AlertType.ERROR );

        }
    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
