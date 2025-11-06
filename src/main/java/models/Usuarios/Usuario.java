package models.Usuarios;

public abstract class Usuario {
    protected String id;
    protected String name;
    protected String email;
    protected String password;

    public Usuario(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
