package Logica;

public class Jugador extends Ubicable implements Movible, Matable {
    private boolean estaVivo;

    public Jugador(){
        estaVivo = true;
    }
    public void mover(int x, int y){
        setPosicion(getX()+x, getY()+y);
    }
    public boolean estaVivo(){
        return estaVivo;
    }
    public void morir(){
        estaVivo = false;
    }
}
