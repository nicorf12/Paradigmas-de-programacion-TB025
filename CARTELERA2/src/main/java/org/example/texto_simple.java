package org.example;

public class texto_simple extends mensaje{
    private String texto;

    public texto_simple(String texto,integrante remitente, integrante emisario){
        super(remitente,emisario);
        this.texto = texto;
    }


    public String ver_contenido() {
        return texto;
    }
}
