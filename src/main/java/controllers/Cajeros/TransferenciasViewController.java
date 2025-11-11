package controllers.Cajeros;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.CuentasBancarias.Account;
import models.CuentasBancarias.GestorCuentas;

public class TransferenciasViewController {

    @FXML
    private Button btnTransferir;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtCuentaDestino;

    @FXML
    private TextField txtCuentaOrigen;

    @FXML
    private TextField txtMonto;

    private GestorCuentas gestorCuentas;
    private AnchorPane panelContenido;

    public TransferenciasViewController() {
        gestorCuentas = new GestorCuentas();
    }
    public void setPanelContenido(AnchorPane panelContenido) {
        this.panelContenido = panelContenido;
    }

    public void setGestorCuentas(GestorCuentas gestor) {
        this.gestorCuentas = gestor;
    }
    @FXML
    public void initialize(){
        if (gestorCuentas != null) {
            gestorCuentas.cargarCuentas();
        }
        txtCuentaOrigen.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtCuentaOrigen.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (newValue.length() > 8) {
                txtCuentaOrigen.setText(newValue.substring(0, 8));
            }
        });
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
    void onCancelar() {
        if (panelContenido != null) {
            panelContenido.getChildren().clear();
        }
    }

    @FXML
    void onRealizarTransferencia(ActionEvent event) {
        if (gestorCuentas == null) {
            gestorCuentas = new GestorCuentas();
            gestorCuentas.cargarCuentas();
        }
        String numOrigenSin = txtCuentaOrigen.getText().trim();
        String numDestinoSin = txtCuentaDestino.getText().trim();
        String montoTexto = txtMonto.getText().trim();
        if (numOrigenSin.isEmpty() || numDestinoSin.isEmpty() || montoTexto.isEmpty()) {
            mostrarAlerta("Error de validación", "Complete todos los campos de cuenta y monto.", Alert.AlertType.WARNING);
            return;
        }
        String cuentaOrigen = "BUQ-" + numOrigenSin;
        String cuentaDestino = "BUQ-" + numDestinoSin;
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
        if (cuentaOrigen.equals(cuentaDestino)) {
            mostrarAlerta("Error de transferencia", "La cuenta de origen y destino no pueden ser la misma.", Alert.AlertType.WARNING);
            return;
        }
        if (!gestorCuentas.existeCuenta(cuentaOrigen)) {
            mostrarAlerta("Cuenta de Origen no encontrada",
                    "No existe una cuenta de origen con el número: " + cuentaOrigen,
                    Alert.AlertType.ERROR);
            return;
        }
        if (!gestorCuentas.existeCuenta(cuentaDestino)) {
            mostrarAlerta("Cuenta de Destino no encontrada",
                    "No existe una cuenta de destino con el número: " + cuentaDestino,
                    Alert.AlertType.ERROR);
            return;
        }
        Account cuentaO = gestorCuentas.buscarCuenta(cuentaOrigen);
        Account cuentaD = gestorCuentas.buscarCuenta(cuentaDestino);
        double saldoAnteriorO = cuentaO.getBalance();
        if (monto > saldoAnteriorO) {
            mostrarAlerta("Saldo Insuficiente",
                    "No hay saldo suficiente para realizar la transferencia.\n" +
                            "Saldo actual en cuenta origen: $" + String.format("%,.2f", saldoAnteriorO),
                    Alert.AlertType.WARNING);
            return;
        }
        if (gestorCuentas.realizarTransferencia(cuentaOrigen, cuentaDestino, monto)) {
            double nuevoSaldoO = cuentaO.getBalance();
            double nuevoSaldoD = cuentaD.getBalance();

            mostrarAlerta("Transferencia exitosa",
                    "Transferencia realizada correctamente.\n\n" +
                            "Origen: " + cuentaOrigen + " | Nuevo Saldo: $" + String.format("%,.2f", nuevoSaldoO) + "\n" +
                            "Destino: " + cuentaDestino + " | Nuevo Saldo: $" + String.format("%,.2f", nuevoSaldoD) + "\n" +
                            "Monto transferido: $" + String.format("%,.2f", monto),
                    Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarAlerta("Error",
                    "No se pudo realizar la transferencia. Intente nuevamente.",
                    Alert.AlertType.ERROR);
        }

    }
    private void limpiarCampos() {
        txtCuentaOrigen.clear();
        txtCuentaDestino.clear();
        txtMonto.clear();
        txtCuentaOrigen.requestFocus();
    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
