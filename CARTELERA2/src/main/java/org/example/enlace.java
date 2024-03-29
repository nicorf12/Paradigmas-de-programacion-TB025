package org.example;

public class enlace extends mensaje{
    private String enlace;

    public enlace(String enlace,integrante remitente, integrante emisario){
        super(remitente,emisario);
        this.enlace = enlace;
    }
    public String ver_contenido() {
        return enlace;
    }
}
