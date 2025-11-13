package controllers.Clientes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.CuentasBancarias.Account;
import models.CuentasBancarias.GestorCuentas;

import java.util.ArrayList;

public class TransaccionesViewController {

    @FXML
    private Button btnTransferir;

    @FXML
    private ComboBox<Account> cmbCuentaOrigen;

    @FXML
    private TextField txtConcepto;

    @FXML
    private TextField txtCuentaDestino;

    @FXML
    private TextField txtMonto;
    private GestorCuentas gestorCuentas;
    private AnchorPane panelContenido;
    private String clientId;
    public TransaccionesViewController(){
        this.gestorCuentas = new GestorCuentas();
    }
    public void setPanelContenido(AnchorPane panelContenido) {
        this.panelContenido = panelContenido;
    }

    public void setGestorCuentas(GestorCuentas gestor) {
        this.gestorCuentas = gestor;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
        if(this.gestorCuentas!=null){
            this.gestorCuentas.cargarCuentas();
            cargarCuentasOrigen();
        }
    }
    @FXML
    public void initialize(){

        cmbCuentaOrigen.setPromptText("Seleccione Cuenta Origen");
        cmbCuentaOrigen.setConverter(new javafx.util.StringConverter<Account>() {
            @Override
            public String toString(Account account) {
                return account != null ? account.getAccountNumber() : "";
            }

            @Override
            public Account fromString(String string) {
                return null;
            }
        });
    }
    private void cargarCuentasOrigen() {
        ArrayList<Account> cuentas = gestorCuentas.getCuentasCliente(clientId);
        ObservableList<Account> listaCuentas = FXCollections.observableArrayList(cuentas);
        cmbCuentaOrigen.setItems(listaCuentas);

        if (!listaCuentas.isEmpty()) {
            cmbCuentaOrigen.getSelectionModel().selectFirst();
        }
    }
    @FXML
    void onCancelar(ActionEvent event) {
        if (panelContenido != null) {
            panelContenido.getChildren().clear();
        }
    }

    @FXML
    void onRealizarTransferencia(ActionEvent event) {
        if(gestorCuentas == null){
            mostrarAlerta("Error", "El sistema de gestión de cuentas no está disponible.", Alert.AlertType.ERROR);
            return;
        }
        Account cuentaOrigenObj = cmbCuentaOrigen.getSelectionModel().getSelectedItem();
        if (cuentaOrigenObj == null) {
            mostrarAlerta("Error de validación", "Seleccione una cuenta de origen.", Alert.AlertType.WARNING);
            return;
        }
        String cuentaOrigen = cuentaOrigenObj.getAccountNumber();
        String cuentaDestinoSinPrefijo = txtCuentaDestino.getText().trim();
        if (cuentaDestinoSinPrefijo.isEmpty()) {
            mostrarAlerta("Error de validación", "Introduzca un número de cuenta destino.", Alert.AlertType.WARNING);
            return;
        }
        String cuentaDestino = "BUQ-" + cuentaDestinoSinPrefijo;
        if (cuentaOrigen.equals(cuentaDestino)) {
            mostrarAlerta("Error de transferencia", "La cuenta de origen y destino no pueden ser la misma.", Alert.AlertType.WARNING);
            return;
        }
        String montoTexto = txtMonto.getText().trim();
        double monto;
        try {
            monto = Double.parseDouble(montoTexto);
            if (monto <= 0) {
                mostrarAlerta("Error de validación", "El monto debe ser mayor a cero.", Alert.AlertType.WARNING);
                return;
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de validación", "El monto ingresado no es válido.", Alert.AlertType.ERROR);
            return;
        }
        if (!gestorCuentas.existeCuenta(cuentaDestino)) {
            mostrarAlerta("Cuenta de Destino no encontrada",
                    "No existe una cuenta con el número: " + cuentaDestino,
                    Alert.AlertType.ERROR);
            return;
        }
        if (monto > cuentaOrigenObj.getBalance()) {
            mostrarAlerta("Saldo Insuficiente",
                    "No tiene saldo suficiente en la cuenta de origen.",
                    Alert.AlertType.WARNING);
            return;
        }
        if (gestorCuentas.realizarTransferencia(cuentaOrigen, cuentaDestino, monto)) {

            Account cuentaDestinoObj = gestorCuentas.buscarCuenta(cuentaDestino);
            double nuevoSaldoO = cuentaOrigenObj.getBalance();
            double nuevoSaldoD = cuentaDestinoObj.getBalance();

            mostrarAlerta("Transferencia exitosa",
                    "Transferencia de $" + String.format("%,.2f", monto) + " realizada.\n\n" +
                            "Origen: " + cuentaOrigen + " | Nuevo Saldo: $" + String.format("%,.2f", nuevoSaldoO) + "\n" +
                            "Destino: " + cuentaDestino + " | Nuevo Saldo: $" + String.format("%,.2f", nuevoSaldoD),
                    Alert.AlertType.INFORMATION);
            limpiarCampos();
            cargarCuentasOrigen();
        } else {
            mostrarAlerta("Error de Transacción",
                    "No se pudo completar la transferencia. Verifique el monto y el saldo.",
                    Alert.AlertType.ERROR);
        }
    }
    private void limpiarCampos() {
        cmbCuentaOrigen.getSelectionModel().clearSelection();
        txtCuentaDestino.clear();
        txtMonto.clear();
        txtConcepto.clear();
        cmbCuentaOrigen.requestFocus();
    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    }




