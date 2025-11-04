package models.Usuarios;
import models.enums.TipoUsuario;

public abstract class Usuario {
    protected String id;
    protected String name;
    protected String username;
    protected String password;
    protected TipoUsuario tipoUsuario;
    protected boolean activo;

    public Usuario(String id, String name, String username, String password,  TipoUsuario tipoUsuario) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.activo = true;
    }
    public boolean validarPassword(String password) {
        return this.password.equals(password);
    }
    public abstract String getRolDescripcion();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    public boolean isActivo(){
        return activo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
