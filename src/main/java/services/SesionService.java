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
        System.out.println("Sesión iniciada: " + usuario.getNombre() + " (" + usuario.getRolDescripcion() + ")");
    }

    public void cerrarSesion() {
        if (usuarioActual != null) {
            System.out.println("Sesión cerrada: " + usuarioActual.getNombre());
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
