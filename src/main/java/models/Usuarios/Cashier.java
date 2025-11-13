package models.Usuarios;

public class Cashier extends Usuario {

    private String workerId;

    public Cashier(String id, String name, String email, String password, String workerId){
        super(id, name,email, password);
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
