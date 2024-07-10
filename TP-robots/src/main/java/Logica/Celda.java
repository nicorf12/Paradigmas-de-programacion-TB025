package Logica;

public class Celda {
    private Ubicable entidad;
    private boolean disponible;


    public Celda() {
        disponible = true;
    }

    public void setEntidad(Ubicable entidad) {
        disponible = false;
        this.entidad = entidad;
    }
    public Ubicable getEntidad() {
        return entidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void liberarCelda() {
        disponible = true;
    }

}
