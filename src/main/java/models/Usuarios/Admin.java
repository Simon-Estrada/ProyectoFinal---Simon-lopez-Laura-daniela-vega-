package models.Usuarios;

public class Admin extends Usuario {

    private String depo;

    public Admin(String id, String name, String email, String password, String depo) {
        super(id, name, email, password);
        this.depo = depo;
    }
    public Admin(String id, String name, String email, String password) {
        this(id, name, email, password, "Administracion General");
    }
    @Override
    public String getRolDescripcion(){
        return "Administrador";
    }
    public String getDepo() {
        return depo;
    }

    public void setDepo(String depo) {
        this.depo = depo;
    }
}
