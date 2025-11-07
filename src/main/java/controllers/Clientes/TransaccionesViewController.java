package controllers.Clientes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TransaccionesViewController {

    @FXML
    private Button btnTransferir;

    @FXML
    private ComboBox<?> cmbCuentaOrigen;

    @FXML
    private TextField txtConcepto;

    @FXML
    private TextField txtCuentaDestino;

    @FXML
    private TextField txtMonto;

    @FXML
    void onRealizarTransferencia(ActionEvent event) {

    }

}

