
package models.Usuarios;
import models.enums.TipoUsuario;


public class Client extends Usuario {
    private String phone;
    private String adress;

    public Client(String id, String name, String username,
                  String password, String phone, String adress) {
        super(id, name, username, password, TipoUsuario.CLIENTE);
        this.phone = phone;
        this.adress = adress;
    }

    @Override
    public String getRolDescripcion() {
        return "Cliente";
    }

    public String getPhone() {
        return phone;
    }

    public String getAdress() {
        return adress;
    }
}
