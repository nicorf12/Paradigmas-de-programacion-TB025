package org.example;

import java.util.ArrayList;

public class usuario {
    private final String nombre_completo;
    private final int padron;
    private  ArrayList<carrera> carreras = new ArrayList<>();
    private  ArrayList<materia> materias_aprobadas = new ArrayList<>();

    public usuario ( String nombre, int padron) {
        this.nombre_completo = nombre;
        this.padron = padron;
    }

    public ArrayList<materia> getAprobadas() {
        return materias_aprobadas;
    }

    public String getNombre() {
        return nombre_completo;
    }

    public void inscribirse_carrera( carrera carrera){
        carreras.add(carrera);
    }

    public void aprobar_materia( materia materia){
        materias_aprobadas.add(materia);
    }
}
