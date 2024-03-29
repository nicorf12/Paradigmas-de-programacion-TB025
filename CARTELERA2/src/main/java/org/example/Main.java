package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        sistema cartelera = new sistema();
        personal nicor = new personal("nicor","1234");
        huesped juan = new huesped("juan","asdw");
        cartelera.registrar_usuario(nicor);
        cartelera.registrar_usuario(juan);

        cartelera.autentificarse("nicor","1234");

        mensaje mensaje = new texto_simple("hola juancito",juan,nicor);
        cartelera.enviar_mensaje(mensaje);

        cartelera.cerrar_sesion();
        cartelera.autentificarse("juan","asdw");
        cartelera.ver_recibidos();

        cartelera.cerrar_sesion();
        cartelera.autentificarse("nicor","1234");
        cartelera.salir();
    }
}