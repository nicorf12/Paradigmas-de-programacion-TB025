package org.example;

import java.util.ArrayList;

public abstract class mensaje {
    private integrante remitente;
    private integrante emisario;

    public mensaje(integrante remitente, integrante emisario) {
        this.remitente = remitente;
        this.emisario = emisario;
    }

    public integrante getRemitente() {
        return remitente;
    }

    public abstract String ver_contenido();
}
