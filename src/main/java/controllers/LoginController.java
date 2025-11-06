package controllers;


import controllers.Administradores.DashboardAdministradoresController;
import controllers.Cajeros.DashboardCajeroController;
import controllers.Clientes.DashboardClienteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Usuarios.*;


import java.io.IOException;


public class LoginController {
    @FXML
    private Button btnIniciarSesion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPassword;

    private GestionUsuarios gestor;
    @FXML
    public void initialize() {
        gestor = new GestionUsuarios();
        if(!gestor.cargarUsuarios()){
            mostrarAlerta("Advertencia", "Ocurrio un error. Se iniciara con datos vacios",
                    Alert.AlertType.WARNING);
        }
        if(gestor.getAdminList().isEmpty()&&
        gestor.getCashierList().isEmpty()&&
        gestor.getClientList().isEmpty()){
            Admin adminDefault = new Admin("1", "Admin", "admin@gmail.com", "admin123", "Sistemas");
            gestor.agregarAdmin(adminDefault);
        }
    }
    @FXML
    private void onIniciarSesion(ActionEvent event) throws IOException {
        String id = txtId.getText();
        String password = txtPassword.getText();

        for (Client client : gestor.getClientList()) {
            if (client.getId().equals(id) && client.getPassword().equals(password)) {
                abrirVistaCliente(client);
                return;
            }
        }
        for (Cashier cashier : gestor.getCashierList()) {
            if (cashier.getId().equals(id) && cashier.getPassword().equals(password)) {
                abrirVistaCashier(cashier);
                return;
            }
        }
        for (Admin admin : gestor.getAdminList()) {
            if (admin.getId().equals(id) && admin.getPassword().equals(password)) {
                abrirVistaAdmin(admin);
                return;
            }
        }
        mostrarAlerta("Error", "Email o contraseña incorrectos.", Alert.AlertType.ERROR);
    }
    private boolean validarCampos() {
        if (txtId.getText().trim().isEmpty() ||
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
    private void abrirVistaCliente(Client client) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/Clientes/DashboardCliente.fxml"));
            Parent root = loader.load();

            DashboardClienteController controller = loader.getController();
            controller.setClient(client);
            controller.setGestor(gestor);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel Cliente - " + client.getName());
            stage.show();

            Stage loginStage = (Stage) txtId.getScene().getWindow();
            loginStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la vista del client", Alert.AlertType.ERROR);
        }
    }
    private void abrirVistaCashier(Cashier cashier) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/Cajeros/DashboardCajero.fxml"));
            Parent root = loader.load();

            DashboardCajeroController controller = loader.getController();
            controller.setCashier(cashier);
            controller.setGestor(gestor);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel Cajero - " + cashier.getName());
            stage.show();

            Stage loginStage = (Stage) txtId.getScene().getWindow();
            loginStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la vista del cajero", Alert.AlertType.ERROR);
        }
    }
    private void abrirVistaAdmin(Admin admin) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/Admin/DashboardAdmin.fxml"));
            Parent root = loader.load();

            DashboardAdministradoresController controller = loader.getController();
            controller.setAdmin(admin);
            controller.setGestor(gestor);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel Cajero - " + admin.getName());
            stage.show();

            Stage loginStage = (Stage) txtId.getScene().getWindow();
            loginStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la vista del Administrador", Alert.AlertType.ERROR);
        }
    }
}

