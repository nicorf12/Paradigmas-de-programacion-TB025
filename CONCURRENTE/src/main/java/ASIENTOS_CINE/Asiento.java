package ASIENTOS_CINE;

public class Asiento {
    private boolean estado = false;

    public synchronized boolean reservar(){
        if (estado) {
            return false;
        }
        estado = true;
        return true;
    };
}
