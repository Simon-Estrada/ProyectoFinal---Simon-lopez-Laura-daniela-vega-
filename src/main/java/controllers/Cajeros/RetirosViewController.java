package controllers.Cajeros;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.CuentasBancarias.Account;
import models.CuentasBancarias.GestorCuentas;

public class RetirosViewController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnRetirarMonto;

    @FXML
    private TextField txtMonto;

    @FXML
    private TextField txtNumeroCuenta;

    private GestorCuentas gestorCuentas;
    private AnchorPane panelContenido;

    public RetirosViewController (){
        gestorCuentas=new GestorCuentas();
    }
    public void setPanelContenido(AnchorPane panelContenido){
        this.panelContenido = panelContenido;
    }
    public void setGestorCuentas(GestorCuentas gestor){
        this.gestorCuentas = gestor;
    }
    @FXML
    public void initialize(){
        if(gestorCuentas!=null){
            gestorCuentas.cargarCuentas();
        }
        txtNumeroCuenta.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtNumeroCuenta.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (newValue.length() > 8) {
                txtNumeroCuenta.setText(newValue.substring(0, 8));
            }
        });
    }

    @FXML
    void onCancelar() {
        if(panelContenido != null) {
            panelContenido.getChildren().clear();
        }

    }

    @FXML
    void onRetirarMonto(ActionEvent event) {
        if(gestorCuentas == null){
            gestorCuentas= new GestorCuentas();
            gestorCuentas.cargarCuentas();
        }

        String numeroCuentaSinPrefijo = txtNumeroCuenta.getText().trim();

        if (numeroCuentaSinPrefijo.isEmpty()) {
            mostrarAlerta("Error de validación", "Introduzca un número de cuenta.", Alert.AlertType.WARNING);
            return;
        }
        String numeroCuenta = "BUQ-" + numeroCuentaSinPrefijo;
        String montoTexto = txtMonto.getText().trim();

        if(montoTexto.isEmpty()){
            mostrarAlerta("Error de validación", "Ingrese un monto a retirar.", Alert.AlertType.WARNING);
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

            return;
        }
        Account cuenta = gestorCuentas.buscarCuenta(numeroCuenta);
        double saldoAnterior = cuenta.getBalance();

        if(monto > saldoAnterior){
            mostrarAlerta("Saldo Insuficiente", "No hay saldo suficiente para realizar el retiro.\n" +
                            "Saldo actual: $" + String.format("%,.2f", saldoAnterior),
                    Alert.AlertType.WARNING );
            return;
        }
        if(gestorCuentas.retirar(numeroCuenta, monto)){
            double nuevoSaldo = cuenta.getBalance();
            mostrarAlerta("Retiro exitoso",
                    "Retiro realizado correctamente.\n\n" +
                            "Cuenta: " + numeroCuenta + "\n" +
                            "Tipo: " + cuenta.getAccountType() + "\n" +
                            "Saldo anterior: $" + String.format("%,.2f", saldoAnterior) + "\n" +
                            "Monto retirado: $" + String.format("%,.2f", monto) + "\n" +
                            "Nuevo saldo: $" + String.format("%,.2f", nuevoSaldo),
                    Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarAlerta("Error",
                    "No se pudo realizar el retiro. Intente nuevamente.",
                    Alert.AlertType.ERROR);
        }
    }
    private void limpiarCampos() {
        txtNumeroCuenta.clear();
        txtMonto.clear();
        txtNumeroCuenta.requestFocus();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}





