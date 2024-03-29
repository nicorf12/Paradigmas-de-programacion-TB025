package org.example;

import java.util.ArrayList;

public class sistema {
    private ArrayList<integrante> huespedes = new ArrayList<>();
    private ArrayList<integrante> personal = new ArrayList<>();

    private integrante usuario_enlinea = null;

    public void registrar_usuario(huesped usuario){
        huespedes.add(usuario);
    }

    public void registrar_usuario(personal usuario){
        personal.add(usuario);
    }

    public boolean autentificarse(String nombre, String contraseña){
        for (integrante usuario : huespedes) {
            if (nombre.equals(usuario.getNombre())  && contraseña.equals(usuario.getContraseña())){
                usuario_enlinea = usuario;
                System.out.print("Logueado\n");
                return true;
            }
        }

        for (integrante usuario : personal) {
            if (nombre.equals(usuario.getNombre())  && contraseña.equals(usuario.getContraseña())){
                usuario_enlinea = usuario;
                System.out.print("Logueado\n");
                return true;
            }
        }

        return false;
    }

    public void cerrar_sesion() {
        usuario_enlinea = null;
    }

    public void salir() {
        if (usuario_enlinea instanceof huesped) {
            System.exit(0);
        }
        System.out.print("No tienes permiso para eso\n");
    }

    public void enviar_mensaje(mensaje mensaje){
        if (usuario_enlinea == null) {
            return;
        }
        usuario_enlinea.enviar_mensaje(mensaje);
        mensaje.getRemitente().recibir_mensaje(mensaje);
    }

    public void ver_recibidos(){
        usuario_enlinea.ver_recibidos();
    }

}
