package models.Usuarios;

import models.CuentasBancarias.Cuenta;
import models.enums.TipoUsuario;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario{
    private String telefono;
    private String direccion;

    private List<Cuenta> cuentas;
    public Cliente(String id, String nombre, String email,
                   String contraseña, String telefono, String direccion) {
        super(id, nombre, email, contraseña, TipoUsuario.CLIENTE);
        this.telefono = telefono;
        this.direccion = direccion;
    }
    @Override
    public String getRolDescripcion() {
        return "Cliente";
    }
   public String getTelefono() {
        return telefono;
   }
   public String getDireccion() {
        return direccion;
   }


}
