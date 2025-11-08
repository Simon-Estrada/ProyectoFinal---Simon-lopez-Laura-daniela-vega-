package controllers.Cajeros;

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
import models.Usuarios.Cashier;
import models.Usuarios.GestionUsuarios;

import java.io.IOException;

public class DashboardCajeroController {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnConsultarSaldo;

    @FXML
    private Button btnRealizarDeposito;

    @FXML
    private Button btnRealizarRetiros;

    @FXML
    private Button btnRealizarTransferencias;

    @FXML
    private Button btnRegistrarCliente;

    @FXML
    private AnchorPane panelContenido;

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private BorderPane mainBorderPane;
    private Cashier cashier;
    private GestionUsuarios gestionUsuarios;


    public void setCashier(Cashier cashier){
        this.cashier = cashier;
        lblNombreUsuario.setText(cashier.getName()+" "+cashier.getId());
    }
    public void setGestor(GestionUsuarios gestor){
        this.gestionUsuarios = gestor;
    }

    @FXML
    void onCerrarSesion(ActionEvent event) {
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
    void onConsultarSaldo() throws IOException {
        AnchorPane panelConsultarSaldoFXML = FXMLLoader. load(getClass().getResource("/com/example/proyectofinal/Cajeros/ConsultaSaldo.fxml"));
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(panelConsultarSaldoFXML);
    }

    @FXML
    void onRealizarDeposito() throws IOException {
        AnchorPane panelRealizarDepositos = FXMLLoader. load(getClass().getResource("/com/example/proyectofinal/Cajeros/DepositosView.fxml"));
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(panelRealizarDepositos);
    }

    @FXML
    void onRealizarTransferencias() throws IOException {
        AnchorPane panelRealizarTransferencias = FXMLLoader. load(getClass().getResource("/com/example/proyectofinal/Cajeros/TransferenciasView.fxml"));
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(panelRealizarTransferencias);
    }

    @FXML
    void onRegistrarCliente() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/Cajeros/RegistroClientes.fxml"));
        AnchorPane panelRegistrarClientes = loader. load();
        RegistroClientesController controllerHijo = loader.getController();
        controllerHijo.setPanelContenido(panelContenido);
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(panelRegistrarClientes);
    }

    @FXML
    void onRetirar() throws IOException {
        AnchorPane panelRetirar = FXMLLoader. load(getClass().getResource("/com/example/proyectofinal/Cajeros/RetirosView.fxml"));
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(panelRetirar);
    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
