package controllers.Administradores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.Usuarios.Admin;
import models.Usuarios.GestionUsuarios;

import java.util.Optional;

public class RegistroAdministradoresController {
    @FXML
    private Button btnBorrar;
    @FXML
    private Button btnModificar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TextField txtContraseña;

    @FXML
    private TextField txtDepartamento;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtIdentificacion;
    @FXML
    private TableColumn<Admin, String> colContraseña;

    @FXML
    private TableColumn<Admin, String> colDepartamento;

    @FXML
    private TableColumn<Admin, String> colEmail;

    @FXML
    private TableColumn<Admin, String> colId;

    @FXML
    private TableColumn<Admin, String> colNombre;
    @FXML
    private TextField txtNombre;
    @FXML
    private TableView<Admin> tablaAdministradores;
    private AnchorPane panelContenido;
    private GestionUsuarios gestionUsuarios;
    private ObservableList<Admin> listaAdministradores;

    @FXML
    public void initialize() {
        configurarTabla();
        btnModificar.setDisable(true);
        if (btnBorrar != null) {
            btnBorrar.setDisable(true);
        }
        tablaAdministradores.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        btnModificar.setDisable(false);
                        if (btnBorrar != null) {
                            btnBorrar.setDisable(false);
                        }
                        mostrarAdmin(newValue);
                        txtIdentificacion.setDisable(true);
                    } else {
                        btnModificar.setDisable(true);
                        if (btnBorrar != null) {
                            btnBorrar.setDisable(true);
                        }
                        limpiarCampos();
                        txtIdentificacion.setDisable(false);
                    }
                }
        );
    }

    public void setGestionUsuarios(GestionUsuarios gestor) {
        this.gestionUsuarios = gestor;
        if (this.gestionUsuarios != null) {
            this.gestionUsuarios.cargarUsuarios();
        }

        cargarDatos();
    }

    public void setPanelContenido(AnchorPane panelContenido) {
        this.panelContenido = panelContenido;
    }
    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContraseña.setCellValueFactory(new PropertyValueFactory<>("password"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("depo"));
        tablaAdministradores.setPlaceholder(new Label("No hay administradores registrados"));
    }
    private void cargarDatos() {
        if (gestionUsuarios != null) {
            listaAdministradores = FXCollections.observableArrayList(
                    gestionUsuarios.getAdminList()
            );
            tablaAdministradores.setItems(listaAdministradores);
            tablaAdministradores.refresh();
        }
    }
    private void mostrarAdmin(Admin admin) {
        if (admin != null) {
            txtIdentificacion.setText(admin.getId());
            txtNombre.setText(admin.getName());
            txtEmail.setText(admin.getEmail());
            txtContraseña.setText(admin.getPassword());
            txtDepartamento.setText(admin.getDepo());
        }
    }

    @FXML
    void onModificar(ActionEvent event) {
        Admin adminSeleccionado = tablaAdministradores.getSelectionModel().getSelectedItem();

        if (adminSeleccionado == null) {
            mostrarAlerta("Advertencia", "Seleccione un administrador para modificar", Alert.AlertType.WARNING);
            return;
        }

        if (txtNombre.getText().trim().isEmpty() ||
                txtEmail.getText().trim().isEmpty() ||
                txtContraseña.getText().isEmpty() ||
                txtDepartamento.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "Complete todos los campos obligatorios", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Modificación");
        confirmacion.setHeaderText("¿Modificar Administrador?");
        confirmacion.setContentText("¿Está seguro de modificar los datos del administrador: " +
                adminSeleccionado.getName() + "?");

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            if (gestionUsuarios.modificarAdmin(
                    adminSeleccionado.getId(),
                    txtNombre.getText().trim(),
                    txtEmail.getText().trim(),
                    txtContraseña.getText(),
                    txtDepartamento.getText().trim())) {

                cargarDatos();
                limpiarCampos();
                tablaAdministradores.getSelectionModel().clearSelection();
                mostrarAlerta("Éxito", "Administrador modificado correctamente", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No se pudo modificar el administrador", Alert.AlertType.ERROR);
            }
        }

    }


    @FXML
    void onCancelar() {
        if(panelContenido != null) {
            panelContenido.getChildren().clear();
        }
    }

    @FXML
    void onRegistrarEmpleado(ActionEvent event) {
        String id = txtIdentificacion.getText().trim();
        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();
        String contraseña = txtContraseña.getText();
        String depo = txtDepartamento.getText().trim();

        if (id.isEmpty() || nombre.isEmpty() || email.isEmpty() ||
                contraseña.isEmpty() || depo.isEmpty()) {
            mostrarAlerta("Error de Validación",
                    "Por favor, complete todos los campos no sea terco.",
                    Alert.AlertType.WARNING);
            return;
        }

        if (gestionUsuarios != null) {
            boolean registrado = gestionUsuarios.addAdmin(id, nombre, email, contraseña, depo);
            if (registrado) {
                mostrarAlerta("Éxito", "Administrador registrado correctamente ;).", Alert.AlertType.INFORMATION);
                limpiarCampos();
                cargarDatos();
            } else {
                mostrarAlerta("Error",
                        "No se pudo registrar el Administrador. Posiblemente el ID o Email ya existen.",
                        Alert.AlertType.ERROR);
            }
        }
    }
    @FXML
    void onBorrar(ActionEvent event) {
        Admin adminSeleccionado = tablaAdministradores.getSelectionModel().getSelectedItem();

        if (adminSeleccionado == null) {
            mostrarAlerta("Advertencia", "Seleccione un administrador para eliminar", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Eliminación");
        confirmacion.setHeaderText("¿Eliminar Administrador?");
        confirmacion.setContentText("¿Está seguro de eliminar al administrador: " +
                adminSeleccionado.getName() + "?\n\n" +
                "Esta acción no se puede deshacer.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            if (gestionUsuarios.eliminarAdmin(adminSeleccionado.getId())) {
                listaAdministradores.remove(adminSeleccionado);
                limpiarCampos();
                tablaAdministradores.getSelectionModel().clearSelection();
                mostrarAlerta("Éxito", "Administrador eliminado correctamente", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No se pudo eliminar el administrador", Alert.AlertType.ERROR);
            }
        }
    }

    private void limpiarCampos() {
        txtIdentificacion.clear();
        txtNombre.clear();
        txtEmail.clear();
        txtContraseña.clear();
        txtDepartamento.clear();
    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
