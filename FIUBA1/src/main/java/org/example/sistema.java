package org.example;
import java.util.ArrayList;
public class sistema {
    public void inscribirse_carrera(usuario usuario, carrera carrera){
        usuario.inscribirse_carrera(carrera);
    }
    public void materia_aprobada(usuario usuario,materia materia){
        usuario.aprobar_materia(materia);
    }
    public int consultar_carrera(usuario usuario, carrera carrera){
        int creditos = 0;
        int creditos_necesarios = carrera.getCreditos();

        for (materia materia : usuario.getAprobadas()) {
            if (carrera.getMaterias().contains(materia)) {
                creditos += materia.getCreditos();
            }
        }

        if (creditos > creditos_necesarios) {
            return 0;
        }
        return creditos_necesarios - creditos;
    }
}
