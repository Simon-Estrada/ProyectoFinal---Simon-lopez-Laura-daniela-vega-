package controllers.Cajeros;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.CuentasBancarias.Account;
import models.CuentasBancarias.GestorCuentas;

public class DepositosViewController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnDepositar;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtCuentaDestino;

    public DepositosViewController() {
        gestorCuentas = new GestorCuentas();
    }

    @FXML
    private TextField txtMonto;
    private GestorCuentas gestorCuentas;
    private AnchorPane panelContenido;
    public void setPanelContenido(AnchorPane panelContenido) {
        this.panelContenido = panelContenido;
    }

    public void setGestorCuentas(GestorCuentas gestor){
        this.gestorCuentas=gestor;
    }
    @FXML
    public void initialize() {

        if (gestorCuentas != null) {
            gestorCuentas.cargarCuentas();
        }

        if (lblMensaje != null) {
            lblMensaje.setText("");
        }


        txtCuentaDestino.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtCuentaDestino.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (newValue.length() > 8) {
                txtCuentaDestino.setText(newValue.substring(0, 8));
            }
        });
    }

    @FXML
    void onCancelar(ActionEvent event) {
        if(panelContenido != null) {
            panelContenido.getChildren().clear();
        }
    }

    @FXML
    void onRealizarDeposito(ActionEvent event) {
        if (gestorCuentas == null) {
            gestorCuentas = new GestorCuentas();
            gestorCuentas.cargarCuentas();
        }

        String numeroCuentaSinPrefijo = txtCuentaDestino.getText().trim();

        if (numeroCuentaSinPrefijo.isEmpty()) {
            mostrarAlerta("Error de validación", "Introduzca un número de cuenta.", Alert.AlertType.WARNING);
            return;
        }
        String numeroCuenta = "BUQ-" + numeroCuentaSinPrefijo;
        String montoTexto = txtMonto.getText().trim();
        if(montoTexto.isEmpty()){
            mostrarAlerta("Error de validación", "Ingrese un monto a depositar.", Alert.AlertType.WARNING);
            return;
        }
        double monto;
        try{
            monto = Double.parseDouble(montoTexto);
            if(monto<=0){
                mostrarAlerta("Error de validación", "El monto debe ser mayor a cero.", Alert.AlertType.WARNING);
                return;
            }
        }catch (NumberFormatException e) {
            mostrarAlerta("Error de validación", "El monto ingresado no es válido.", Alert.AlertType.ERROR);
            return;
        }
        if(!gestorCuentas.existeCuenta(numeroCuenta)){
            mostrarAlerta("Cuenta no encontrada",
                    "No existe una cuenta con el número: " + numeroCuenta,
                    Alert.AlertType.ERROR);
            lblMensaje.setText("❌ Cuenta no encontrada");
            return;
        }
        Account cuenta = gestorCuentas.buscarCuenta(numeroCuenta);
        double saldoAnterior = cuenta.getBalance();
        if(gestorCuentas.depositar(numeroCuenta, monto)){
            double nuevoSaldo = cuenta.getBalance();
            mostrarAlerta("Depósito exitoso",
                    "Depósito realizado correctamente.\n\n" +
                            "Cuenta: " + numeroCuenta + "\n" +
                            "Tipo: " + cuenta.getAccountType() + "\n" +
                            "Saldo anterior: $" + String.format("%,.2f", saldoAnterior) + "\n" +
                            "Monto depositado: $" + String.format("%,.2f", monto) + "\n" +
                            "Nuevo saldo: $" + String.format("%,.2f", nuevoSaldo),
                    Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else{
            mostrarAlerta("Error",
                    "No se pudo realizar el depósito. Intente nuevamente.",
                    Alert.AlertType.ERROR);
        }
    }
    private void limpiarCampos() {
        txtCuentaDestino.clear();
        txtMonto.clear();
        txtCuentaDestino.requestFocus();
    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
