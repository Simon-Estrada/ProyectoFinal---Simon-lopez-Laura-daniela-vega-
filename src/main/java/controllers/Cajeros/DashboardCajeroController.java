package controllers.Cajeros;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
        AnchorPane panelRegistrarClientes = FXMLLoader. load(getClass().getResource("/com/example/proyectofinal/Cajeros/RegistroClientes.fxml"));
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(panelRegistrarClientes);
    }

    @FXML
    void onRetirar() throws IOException {
        AnchorPane panelRetirar = FXMLLoader. load(getClass().getResource("/com/example/proyectofinal/Cajeros/RetirosView.fxml"));
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(panelRetirar);
    }

}
