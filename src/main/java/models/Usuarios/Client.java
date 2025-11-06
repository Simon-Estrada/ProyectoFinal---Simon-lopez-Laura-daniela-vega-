
package models.Usuarios;


public class Client extends Usuario {
    private String phone;
    private String adress;

    public Client(String id, String name, String username,
                  String password, String phone, String adress) {
        super(id, name, username, password);
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
