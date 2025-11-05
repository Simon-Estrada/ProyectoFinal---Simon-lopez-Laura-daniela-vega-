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

        if(usuario==null){
            mostrarAlerta("Error", "Usuario o contraseña incorrectos.", Alert.AlertType.ERROR);
            return;
        }
        String rutaFXML = "";
        String titulo= "";

        if (usuario instanceof Admin) {
            rutaFXML = "/com/example/proyectofinal/Administradores/DashboardAdministradores.fxml";
            titulo = "Admin";
        } else if (usuario instanceof Cashier) {
            rutaFXML = "/com/example/proyectofinal/Cajeros/DashboardCajero.fxml";
            titulo = "Cashier";
        } else if (usuario instanceof Client) {
            rutaFXML = "/com/example/proyectofinal/Clientes/DashboardCliente.fxml";
            titulo = "Client";
        }
        cambiarEscena(event,rutaFXML,titulo);
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

