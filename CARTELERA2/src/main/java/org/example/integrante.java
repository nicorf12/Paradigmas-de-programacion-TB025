package org.example;

import java.util.ArrayList;

public abstract class integrante {
    private ArrayList<mensaje> enviados = new ArrayList<>();
    private ArrayList<mensaje> recibidos = new ArrayList<>();

    private String nombre;
    private String contraseña;

    public integrante(String nombre, String contraseña){
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    public void enviar_mensaje(mensaje mensaje){
        enviados.add(mensaje);
    }

    public void recibir_mensaje(mensaje mensaje){
        recibidos.add(mensaje);
    }

    public void ver_recibidos(){
        for (mensaje mensaje:recibidos) {
            System.out.print(mensaje.ver_contenido() + "\n");
        }
    }

}
