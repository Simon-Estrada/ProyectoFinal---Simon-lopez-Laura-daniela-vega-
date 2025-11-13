
package models.Usuarios;


public class Client extends Usuario {
    private String phone;
    private String adress;

    public Client(String id, String name, String email,
                  String password, String phone, String adress) {
        super(id, name, email, password);
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
