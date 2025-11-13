package controllers.Administradores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Usuarios.Cashier;
import models.Usuarios.Client;
import models.Usuarios.GestionUsuarios;

import java.util.Optional;

public class TablaEmpleadosController {

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnModificarEmplado;

    @FXML
    private TableColumn<Client, String> colAdress;

    @FXML
    private TableColumn<Cashier, String> colContraseña;

    @FXML
    private TableColumn<Client, String> colContraseña1;

    @FXML
    private TableColumn<Cashier, String> colEmail;

    @FXML
    private TableColumn<Client, String> colEmail1;

    @FXML
    private TableColumn<Cashier,String > colId;

    @FXML
    private TableColumn<Client, String > colId1;

    @FXML
    private TableColumn<Cashier, String> colNombre;

    @FXML
    private TableColumn<Client, String> colNombre1;

    @FXML
    private TableColumn<Client, String> colPhone;

    @FXML
    private TableColumn<Cashier, String> colWorkerId;

    @FXML
    private TableView<Cashier> tablaCajeros;

    @FXML
    private TableView<Client> tablaClientes;
    @FXML
    private TextField txtAdress;

    @FXML
    private TextField txtContraseña;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtWorkerId;

    private GestionUsuarios gestionUsuarios;
    private ObservableList<Cashier>listaCajeros;
    private ObservableList<Client>listaClientes;

    public void setGestionUsuarios(GestionUsuarios gestor) {
        this.gestionUsuarios = gestor;
        cargarDatos();
    }

    @FXML
    public void initialize(){
        configurarTablaCajero();
        configurarTablaCliente();
        btnBorrar.setDisable(true);
        btnModificarEmplado.setDisable(true);
        tablaClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        btnBorrar.setDisable(false);
                        btnModificarEmplado.setDisable(false);
                        mostrarCliente(newValue);
                        txtId.setDisable(true);

                        if (tablaCajeros.getSelectionModel().getSelectedItem() != null) {
                            tablaCajeros.getSelectionModel().clearSelection();
                        }
                    } else {

                        if (tablaCajeros.getSelectionModel().getSelectedItem() == null) {
                            btnBorrar.setDisable(true);
                            btnModificarEmplado.setDisable(true);
                            limpiarCampos();
                        }
                    }
                }
                );
        tablaCajeros.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        btnBorrar.setDisable(false);
                        btnModificarEmplado.setDisable(false);
                        mostrarCajero(newValue);
                        txtId.setDisable(true);

                        if (tablaClientes.getSelectionModel().getSelectedItem() != null) {
                            tablaClientes.getSelectionModel().clearSelection();
                        }
                    } else {

                        if (tablaClientes.getSelectionModel().getSelectedItem() == null) {
                            btnBorrar.setDisable(true);
                            btnModificarEmplado.setDisable(true);
                            limpiarCampos();
                        }
                    }
                }
        );

    }
    private void mostrarCliente(Client cliente) {
        if(cliente != null){
            txtId.setText(cliente.getId());
            txtNombre.setText(cliente.getName());
            txtEmail.setText(cliente.getEmail());
            txtContraseña.setText(cliente.getPassword());
            txtPhone.setText(cliente.getPhone());
            txtAdress.setText(cliente.getAdress());
            txtWorkerId.clear();
            txtWorkerId.setDisable(true);
            txtId.setDisable(true);
        } else {
            limpiarCampos();
        }
    }
    private void mostrarCajero(Cashier cajero) {
        if (cajero != null) {
            txtId.setText(cajero.getId());
            txtNombre.setText(cajero.getName());
            txtEmail.setText(cajero.getEmail());
            txtContraseña.setText(cajero.getPassword());
            txtWorkerId.setText(cajero.getWorkerId());
            txtPhone.clear();
            txtAdress.clear();
            txtPhone.setDisable(true);
            txtAdress.setDisable(true);
            txtId.setDisable(true);
        } else {
            limpiarCampos();
        }
    }
    private void limpiarCampos() {
        txtId.clear();
        txtNombre.clear();
        txtEmail.clear();
        txtContraseña.clear();
        txtPhone.clear();
        txtAdress.clear();
        txtWorkerId.clear();
        txtId.setDisable(false);
        txtPhone.setDisable(false);
        txtAdress.setDisable(false);
        txtWorkerId.setDisable(false);
        tablaClientes.getSelectionModel().clearSelection();
        tablaCajeros.getSelectionModel().clearSelection();
    }
    private void cargarDatos(){
        tablaClientes.getSelectionModel().clearSelection();
        tablaCajeros.getSelectionModel().clearSelection();
        listaCajeros = FXCollections.observableArrayList(gestionUsuarios.getCashierList());
        tablaCajeros.setItems(listaCajeros);
        listaClientes = FXCollections.observableArrayList(gestionUsuarios.getClientList());
        tablaClientes.setItems(listaClientes);
        tablaCajeros.refresh();
        tablaClientes.refresh();
    }
    private void configurarTablaCajero() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContraseña.setCellValueFactory(new PropertyValueFactory<>("password"));
        colWorkerId.setCellValueFactory(new PropertyValueFactory<>("workerId"));
    }
    private void configurarTablaCliente(){
        colId1.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre1.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail1.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContraseña1.setCellValueFactory(new PropertyValueFactory<>("password"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAdress.setCellValueFactory(new PropertyValueFactory<>("adress"));
    }

    @FXML
    void onBorrar(ActionEvent event) {
        Cashier cajeroSeleccionado = tablaCajeros.getSelectionModel().getSelectedItem();
        Client clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        if(cajeroSeleccionado!=null){
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar Eliminacion");
            confirmacion.setHeaderText("¿Eliminar Cajero?");
            confirmacion.setContentText("¿Esta seguro de eliminar al cajero: "
            + cajeroSeleccionado.getName()+"?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                if (gestionUsuarios.eliminarCajero(cajeroSeleccionado.getId())) {
                    listaCajeros.remove(cajeroSeleccionado);
                    mostrarAlerta("Éxito", "Cajero eliminado correctamente", Alert.AlertType.INFORMATION);
                } else {
                    mostrarAlerta("Error", "No se pudo eliminar el cajero", Alert.AlertType.ERROR);
                }
            }
        }else if (clienteSeleccionado != null) {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar eliminación");
            confirmacion.setHeaderText("¿Eliminar cliente?");
            confirmacion.setContentText("¿Está seguro de eliminar al cliente: " + clienteSeleccionado.getName() + "?");

            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                if (gestionUsuarios.eliminarCliente(clienteSeleccionado.getId())) {
                    listaClientes.remove(clienteSeleccionado);
                    mostrarAlerta("Éxito", "Cliente eliminado correctamente", Alert.AlertType.INFORMATION);
                } else {
                    mostrarAlerta("Error", "No se pudo eliminar el cliente", Alert.AlertType.ERROR);
                }
            }
        } else {
            mostrarAlerta("Advertencia", "Seleccione un cajero o cliente para eliminar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void onModificar(ActionEvent event) {
        Cashier cajeroSeleccionado = tablaCajeros.getSelectionModel().getSelectedItem();
        Client clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if(cajeroSeleccionado != null){
            modificarCajero(cajeroSeleccionado);
        }else if (clienteSeleccionado != null) {
            modificarCliente(clienteSeleccionado);
        } else {
            mostrarAlerta("Advertencia", "Seleccione un cajero o cliente para modificar", Alert.AlertType.WARNING);
        }

    }
    private void modificarCajero(Cashier cajero){
        if (txtNombre.getText().trim().isEmpty() ||
                txtEmail.getText().trim().isEmpty() ||
                txtContraseña.getText().isEmpty()) {
            mostrarAlerta("Error de validación", "Complete todos los campos obligatorios", Alert.AlertType.WARNING);
            return;
        }
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Modificación");
        confirmacion.setHeaderText("¿Modificar Cajero?");
        confirmacion.setContentText("¿Está seguro de modificar los datos del cajero?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            if (gestionUsuarios.modificarCajero(
                    cajero.getId(),
                    txtNombre.getText().trim(),
                    txtEmail.getText().trim(),
                    txtContraseña.getText())) {

                cargarDatos();
                limpiarCampos();
                mostrarAlerta("Éxito", "Cajero modificado correctamente", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No se pudo modificar el cajero", Alert.AlertType.ERROR);
            }
        }
    }
    private void modificarCliente(Client cliente){
        if (txtNombre.getText().trim().isEmpty() ||
                txtEmail.getText().trim().isEmpty() ||
                txtContraseña.getText().isEmpty() ||
                txtPhone.getText().trim().isEmpty() ||
                txtAdress.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "Complete todos los campos obligatorios", Alert.AlertType.WARNING);
            return;
        }
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Modificación");
        confirmacion.setHeaderText("¿Modificar Cliente?");
        confirmacion.setContentText("¿Está seguro de modificar los datos del cliente?");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            if (gestionUsuarios.modificarCliente(
                    cliente.getId(),
                    txtNombre.getText().trim(),
                    txtEmail.getText().trim(),
                    txtContraseña.getText(),
                    txtPhone.getText().trim(),
                    txtAdress.getText().trim())) {

                cargarDatos();
                limpiarCampos();
                mostrarAlerta("Éxito", "Cliente modificado correctamente", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No se pudo modificar el cliente", Alert.AlertType.ERROR);
            }
        }
    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}


