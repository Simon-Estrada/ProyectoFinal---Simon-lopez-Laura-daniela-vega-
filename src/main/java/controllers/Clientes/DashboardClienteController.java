package controllers.Clientes;

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
import models.CuentasBancarias.GestorCuentas;
import models.Usuarios.Client;
import models.Usuarios.GestionUsuarios;

import java.io.IOException;

public class DashboardClienteController {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnMisCuentas;

    @FXML
    private Button btnTransferencias;

    @FXML
    private AnchorPane panelContenido;

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private BorderPane mainBorderPane;

    private Client client;
    private GestionUsuarios gestionUsuarios;
    private GestorCuentas gestorCuentas;


    public void setClient(Client client){
        this.client = client;
        lblNombreUsuario.setText(client.getName()+" "+client.getId());
    }

    public void setGestor(GestionUsuarios gestor){
        this.gestionUsuarios = gestor;
    }
    @FXML
    public void initialize(){
        this.gestorCuentas=new GestorCuentas();
        this.gestorCuentas.cargarCuentas();
    }



    @FXML
    void cargarConsultarCuentas() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/Clientes/ResumenCuentas.fxml"));
        AnchorPane panelConsultarCuentasFXML = loader.load();
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(panelConsultarCuentasFXML);
        ResumenCuentasController controller = loader.getController();
        GestorCuentas gestorCompartido = this.gestorCuentas;
        String idClienteLogeado = this.client.getId();
        controller.setGestorCuentas(gestorCompartido);
        controller.cargarDatosDelCliente(idClienteLogeado);

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
    void onRealizarTransferencias() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectofinal/Clientes/TransaccionesView.fxml"));
        AnchorPane panelRealizarTransferncias = loader.load();
        TransaccionesViewController controller = loader.getController();
        controller.setGestorCuentas(this.gestorCuentas);
        controller.setClientId(this.client.getId());
        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(panelRealizarTransferncias);
    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
