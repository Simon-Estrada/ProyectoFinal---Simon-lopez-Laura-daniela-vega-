package models.Usuarios;

import models.enums.TipoUsuario;

public class Admin extends Usuario {
    //Funcionalidades de administrador
    //Gestion de empleados
    //Seguridad y autentificacion
    //Monitoreo de models.transacciones
    //Generacion de reportes
    private String depo;

    public Admin(String id, String name, String username, String password, String depo) {
        super(id, name, username, password, TipoUsuario.ADMINISTRADOR);
        this.depo = depo;
    }
    public Admin(String id, String name, String username, String password) {
        this(id, name, username, password, "Administracion General");
    }
    @Override
    public String getRolDescripcion(){
        return "Administrador";
    }
    public String getDepo() {
        return depo;
    }
}
