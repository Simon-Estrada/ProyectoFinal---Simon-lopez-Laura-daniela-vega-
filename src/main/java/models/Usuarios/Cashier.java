package models.Usuarios;

public class Cashier extends Usuario {
    //Funcionalidades de cajero
    //Registro de clientes
    //Depositos y retiros
    //Consulta saldo
    //Transferencias
    //Generacion de reportes no avanzados
    private String workerId;

    public Cashier(String id, String name, String username, String password, String workerId){
        super(id, name,username, password);
        this.workerId = workerId;
    }
    @Override
    public String getRolDescripcion(){
        return "Cajero";
    }



    public String getWorkerId() {
        return workerId;
    }
}
