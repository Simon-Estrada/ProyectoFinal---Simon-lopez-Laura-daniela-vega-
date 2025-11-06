package controllers.Clientes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.Usuarios.Client;
import models.Usuarios.GestionUsuarios;

public class DashboardClienteController {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnMisCuentas;

    @FXML
    private Button btnTransferencias;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private BorderPane mainBorderPane;

    private Client client;
    private GestionUsuarios gestionUsuarios;


    public void setClient(Client client){
        this.client = client;
        lblNombreUsuario.setText(client.getName()+" "+client.getId());
    }
    public void setGestor(GestionUsuarios gestor){
        this.gestionUsuarios = gestor;
    }



    @FXML
    void cargarConsultarCuentas(ActionEvent event) {

    }

    @FXML
    void onCerrarSesion(ActionEvent event) {

    }

    @FXML
    void onRealizarTransferencias(ActionEvent event) {

    }

}
