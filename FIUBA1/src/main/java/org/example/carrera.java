package org.example;
import java.util.ArrayList;
public class carrera {
    private ArrayList<materia> optativas = new ArrayList<>();
    private ArrayList<materia> obligatorias = new ArrayList<>();
    private int creditos_necesarios;
    private String nombre;

    public carrera(int creditos, String nombre_carrera){
        creditos_necesarios = creditos;
        nombre = nombre_carrera;
    }

    public ArrayList<materia> getMaterias() {
        ArrayList<materia> materias = new ArrayList<>();
        materias.addAll(obligatorias);
        materias.addAll(optativas);
        return materias;
    }

    public int getCreditos() {
        return creditos_necesarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregar_materia( materia materia,boolean esObligatoria){
        if (esObligatoria) {
           obligatorias.add(materia);
        } else {
            optativas.add(materia);
        }
    }

}
