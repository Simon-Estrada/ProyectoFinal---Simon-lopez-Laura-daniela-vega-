package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Usuarios.*;
import models.enums.TipoUsuario;


import java.io.IOException;
import java.util.ArrayList;


public class LoginController {
    @FXML
    private Button btnIniciarSesion;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    private ArrayList<Admin> adminList = new ArrayList<>();
    private ArrayList<Cashier> cashierList = new ArrayList<>();
    private ArrayList<Client> clientList = new ArrayList<>();
    @FXML
    public void initialize() {
        adminList.add(new Admin("1090275087", "Simon", "wooseoksimon@gmail.com", "Enhypen2008", "Administracion general"));
        cashierList.add(new Cashier("33817767","Adria", "dulce1100@gmail.com", "angeles1", "2908"));
        clientList.add(new Client("24575419", "Mary", "foreroluzmarina59@gmail.com", "luzma59", "3146979602", "calle 30c#27b-08"));



    }
    @FXML
    private void onIniciarSesion(ActionEvent event) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (!validarCampos()) return;

        Object usuario = verificarUsuario(username, password);

        if (usuario instanceof Admin) {
            cambiarEscena(event, "/com/example/proyectofinal/Administradores/DashboardAdministradores.fxml", "Panel de Administrador");
        } else if (usuario instanceof Cashier) {
            cambiarEscena(event, "/com/example/proyectofinal/Cajeros/DashboardCajero.fxml", "Panel de Cajero");
        } else if (usuario instanceof Client) {
            cambiarEscena(event, "/com/example/proyectofinal/Clientes/DashboardCliente.fxml", "Panel de Cliente");
        } else {
            mostrarAlerta("Error", "Usuario o contraseña incorrectos", Alert.AlertType.ERROR);
            return;
        }

        mostrarAlerta("Login Exitoso", "Usted ha iniciado sesión correctamente", Alert.AlertType.INFORMATION);
    }

    private Object verificarUsuario(String username, String password) {
        for (Admin a : adminList) {
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) return a;
        }
        for (Cashier c : cashierList) {
            if (c.getUsername().equals(username) && c.getPassword().equals(password)) return c;
        }
        for (Client cl : clientList) {
            if (cl.getUsername().equals(username) && cl.getPassword().equals(password)) return cl;
        }
        return null;
    }
    private boolean validarCampos() {
        if (txtUsername.getText().trim().isEmpty() ||
                txtPassword.getText().trim().isEmpty())
        {
            mostrarAlerta("Error de validación", "Todos los campos son obligatorios.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    private void cambiarEscena(ActionEvent event, String fxmlFile, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(titulo);
        stage.show();
    }
}

