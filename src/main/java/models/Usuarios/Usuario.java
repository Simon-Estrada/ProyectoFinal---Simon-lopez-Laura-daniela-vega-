package models.Usuarios;
import models.enums.TipoUsuario;

public abstract class Usuario {
    protected String id;
    protected String nombre;
    protected String email;
    protected String contraseña;
    protected TipoUsuario tipoUsuario;
    protected boolean activo;

    public Usuario(String id, String nombre, String email, String contraseña,  TipoUsuario tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
        this.activo = true;
    }
    public boolean validarContraseña(String contraseña) {
        return this.contraseña.equals(contraseña);
    }
    public abstract String getRolDescripcion();

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    public boolean isActivo(){
        return activo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
