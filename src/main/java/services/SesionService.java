package services;

import models.Usuarios.Usuario;

public class SesionService {
    private static SesionService instancia;
    private Usuario usuarioActual;

    private SesionService() {}

    public static SesionService getInstancia() {
        if (instancia == null) {
            instancia = new SesionService();
        }
        return instancia;
    }
    public void iniciarSesion(Usuario usuario) {
        this.usuarioActual = usuario;
        System.out.println("Sesión iniciada: " + usuario.getName() + " (" + usuario.getRolDescripcion() + ")");
    }

    public void cerrarSesion() {
        if (usuarioActual != null) {
            System.out.println("Sesión cerrada: " + usuarioActual.getName());
            this.usuarioActual = null;
        }
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public boolean haySesionActiva() {
        return usuarioActual != null;
    }

}
