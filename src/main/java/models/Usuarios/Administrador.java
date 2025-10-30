package models.Usuarios;

import models.enums.TipoUsuario;

public class Administrador extends Usuario {
    //Funcionalidades de administrador
    //Gestion de empleados
    //Seguridad y autentificacion
    //Monitoreo de models.transacciones
    //Generacion de reportes
    private String departamento;

    public Administrador(String id, String nombre, String email, String contraseña,String departamento) {
        super(id, nombre, email, contraseña, TipoUsuario.ADMINISTRADOR);
        this.departamento = departamento;
    }
    public Administrador(String id, String nombre, String email, String contraseña) {
        this(id, nombre, email, contraseña, "Administracion Generañ");
    }
    @Override
    public String getRolDescripcion(){
        return "Administrador";
    }
    public String getDepartamento() {
        return departamento;
    }
}
