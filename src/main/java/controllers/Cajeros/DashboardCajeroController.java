package controllers.Cajeros;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.Usuarios.Cashier;
import models.Usuarios.GestionUsuarios;

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
    private AnchorPane contentPane;

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
    void onConsultarSaldo(ActionEvent event) {

    }

    @FXML
    void onRealizarDeposito(ActionEvent event) {

    }

    @FXML
    void onRealizarTransferencias(ActionEvent event) {

    }

    @FXML
    void onRegistrarCliente(ActionEvent event) {

    }

    @FXML
    void onRetirar(ActionEvent event) {

    }

}
