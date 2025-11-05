package models.Usuarios;

import models.enums.TipoUsuario;

public class Cajero extends Usuario {
    //Funcionalidades de cajero
    //Registro de clientes
    //Depositos y retiros
    //Consulta saldo
    //Transferencias
    hola
    //Generacion de reportes no avanzados
    private String codigoEmpleado;
    private String turno;

    public Cajero(String id, String nombre, String email, String contraseña,String codigoEmpleado, String turno){
        super(id,nombre,email, contraseña, TipoUsuario.CAJERO);
        this.codigoEmpleado=codigoEmpleado;
        this.turno=turno;
    }
    @Override
    public String getRolDescripcion(){
        return "Cajero";
    }

    public String getTurno() {
        return turno;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }
}
