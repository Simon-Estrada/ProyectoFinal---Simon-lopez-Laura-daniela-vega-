package controllers.Clientes;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ResumenCuentasController {

    @FXML
    private TableColumn<?, ?> colNumero;

    @FXML
    private TableColumn<?, ?> colSaldo;

    @FXML
    private TableColumn<?, ?> colTipo;

    @FXML
    private TableView<?> tablaCuentas;

}

