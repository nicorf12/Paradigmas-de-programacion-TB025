package org.example;

public class imagen extends mensaje{
    private String imagen;

    public imagen(String imagen,integrante remitente, integrante emisario){
        super(remitente,emisario);
        this.imagen = imagen;
    }
    public String ver_contenido() {
        return imagen;
    }
}
