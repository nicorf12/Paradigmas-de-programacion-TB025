package org.example;

public class materia {
    private String nombre;
    private String codigo;
    private int creditos;

    public materia(String nombre, String codigo, int creditos){
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCreditos() {
        return creditos;
    }
}
