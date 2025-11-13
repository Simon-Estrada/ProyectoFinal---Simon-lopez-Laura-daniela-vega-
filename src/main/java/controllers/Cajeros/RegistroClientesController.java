package controllers.Cajeros;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.CuentasBancarias.Account;
import models.CuentasBancarias.GestorCuentas;
import models.Usuarios.Cashier;
import models.Usuarios.Client;
import models.Usuarios.GestionUsuarios;

public class RegistroClientesController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TextField txtContraseña;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtSaldoInicial;
    private AnchorPane panelContenido;
    public void setPanelContenido(AnchorPane panelContenido) {
        this.panelContenido = panelContenido;
    }

    @FXML
    private ComboBox<String> cmbTipoCuenta;
    private GestionUsuarios gestionUsuarios;
    private GestorCuentas gestorCuentas;
    private Cashier cashier;
    @FXML
    public void initialize() {
        gestorCuentas = new GestorCuentas();
        gestorCuentas.cargarCuentas();
        if(this.gestionUsuarios == null){
            this.gestionUsuarios = new GestionUsuarios();
            this.gestionUsuarios.cargarUsuarios();
        }

        cmbTipoCuenta.setItems(FXCollections.observableArrayList(
                "AHORRO",
                "CORRIENTE",
                "EMPRESARIAL"
        ));
        txtSaldoInicial.setText("0.00");
    }
    public void setGestionUsuarios(GestionUsuarios gestor) {
        this.gestionUsuarios = gestor;
    }
    public void setCajero(Cashier cashier){
        this.cashier = cashier;
    }


    @FXML
    void onCancelar() {
        if(panelContenido != null) {
            panelContenido.getChildren().clear();
        }

    }

    @FXML
    void onRegistrarCliente(ActionEvent event) {
        if(txtIdentificacion.getText().trim().isEmpty()||
        txtNombre.getText().trim().isEmpty()||txtEmail.getText().trim().isEmpty() ||
                txtContraseña.getText().isEmpty() ||
                txtTelefono.getText().trim().isEmpty() ||
                txtDireccion.getText().trim().isEmpty()){
            mostrarAlerta("Error de validación", "Todos los campos son obligatorios.", Alert.AlertType.WARNING);
            return;
        }
        if (cmbTipoCuenta.getValue() == null) {
            mostrarAlerta("Error", "Seleccione un tipo de cuenta", Alert.AlertType.ERROR);
            return;
        }
        double saldoInicial;
        try{
            saldoInicial = Double.parseDouble(txtSaldoInicial.getText());
            if(saldoInicial < 0){
                mostrarAlerta("Error", "El saldo inicial no puede ser negativo", Alert.AlertType.ERROR);
                return;
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Saldo inicial inválido", Alert.AlertType.ERROR);
            return;
        }

        Client nuevoCliente = new Client(
                txtIdentificacion.getText().trim(),
                txtNombre.getText().trim(),
                txtEmail.getText().trim(),
                txtContraseña.getText().trim(),
                txtTelefono.getText().trim(),
                txtDireccion.getText().trim()
        );
        if(!gestionUsuarios.agregarCliente(nuevoCliente)){
            mostrarAlerta("Error", "No se pudo registrar el cliente", Alert.AlertType.ERROR);
            return;
        }
        Account nuevaCuenta = gestorCuentas.crearCuenta(
                nuevoCliente.getId(),
                cmbTipoCuenta.getValue(),
                saldoInicial
        );
        if(!gestorCuentas.agregarCuenta(nuevaCuenta)){
            mostrarAlerta("Advertencia",
                    "Cliente registrado pero no se pudo crear la cuenta",
                    Alert.AlertType.WARNING);
            return;
        }
        String mensaje = "Cliente registrado exitosamente\n" +
                "Cuenta " + cmbTipoCuenta.getValue() + " creada\n" +
                "Número de cuenta: " + nuevaCuenta.getAccountNumber();

        mostrarAlerta("Éxito", mensaje, Alert.AlertType.INFORMATION);
        limpiarCampos();


    }
    private void limpiarCampos() {
        txtIdentificacion.clear();
        txtNombre.clear();
        txtEmail.clear();
        txtContraseña.clear();
        txtTelefono.clear();
        txtDireccion.clear();
        cmbTipoCuenta.setValue(null);
        txtSaldoInicial.setText("0.00");
    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
//necesito hacer estor para poder hacer push :)!

