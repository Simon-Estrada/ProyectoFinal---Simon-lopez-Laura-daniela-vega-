package controllers.Clientes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.CuentasBancarias.Account;
import models.CuentasBancarias.GestorCuentas;

public class ResumenCuentasController {

    @FXML
    private TableColumn<Account, String> colNumero;

    @FXML
    private TableColumn<Account, Double> colSaldo;

    @FXML
    private TableColumn<Account, String> colTipo;

    @FXML
    private TableView<Account> tablaCuentas;
    private GestorCuentas gestorCuentas;
    private ObservableList<Account> listaCuenta;

    public void setGestorCuentas(GestorCuentas gestor) {
        this.gestorCuentas = gestor;

    }

    @FXML
    public void initialize() {
        configurarTabla();
        tablaCuentas.setPlaceholder(new javafx.scene.control.Label("no hay cuentas."));
    }

    private void configurarTabla() {
        colNumero.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        colSaldo.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("accountType"));
    }

    public void cargarDatosDelCliente(String idCliente) {
        if (gestorCuentas != null) {
            this.listaCuenta = FXCollections.observableArrayList(gestorCuentas.getCuentasCliente(idCliente)
            );
            tablaCuentas.setItems(listaCuenta);
        } else{
            tablaCuentas.setPlaceholder(new Label("Error: GestorCuentas no disponible."));
        }

    }
}
