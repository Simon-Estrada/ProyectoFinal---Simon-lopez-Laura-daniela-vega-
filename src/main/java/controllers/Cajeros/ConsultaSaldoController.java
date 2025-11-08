package controllers.Cajeros;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.CuentasBancarias.GestorCuentas;

public class ConsultaSaldoController {

    @FXML
    private Button btnConsultar;

    @FXML
    private Label lblSaldo;

    @FXML
    private TextField txtCuenta;
    private GestorCuentas gestorCuentas;

    @FXML
    void onConsultarSaldo(ActionEvent event) {
        this.gestorCuentas = new GestorCuentas();
        this.gestorCuentas.cargarCuentas();
        String cuenta = txtCuenta.getText().trim();
        String cuentaCompleta = "BUQ-"+cuenta;
        if(cuenta.isEmpty()){
            mostrarAlerta("Error de validación", "Introduzca un numero de cuenta.", Alert.AlertType.WARNING);
            return;
        }
        if(gestorCuentas.existeCuenta(cuentaCompleta)){
            double saldo = gestorCuentas.consultarSaldo(cuentaCompleta);
            lblSaldo.setText(Double.toString(saldo));

        } else{
            mostrarAlerta("Error", "El numero de cuenta ingresado no existe.", Alert.AlertType.ERROR);
        }
        txtCuenta.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length()>8){
                txtCuenta.setText(newValue.substring(0,8));
            }
        });
    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
