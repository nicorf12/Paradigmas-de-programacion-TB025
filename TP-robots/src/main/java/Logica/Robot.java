package Logica;

import java.util.ArrayList;

public abstract class Robot extends Ubicable implements Movible, Matable{
    private boolean estaVivo;
    public abstract void mover(int x, int y);

    public Robot() {
        estaVivo = true;
    }
    public void morir(){
        estaVivo = false;
    }

    public boolean estaVivo(){
        return estaVivo;
    }

    public void chocarse(Explosion explosion) {
        explosion.matar(this);
    }
    public void chocarse(Robot robot, Celda[][] tablero, ArrayList<Explosion> explosiones) {
        tablero[getX()][getY()].liberarCelda();
        Explosion explosion= new Explosion(this,robot);
        explosion.setPosicion(getX(),getY());
        tablero[getX()][getY()].setEntidad(explosion);
        explosiones.add(explosion);
    }
    public void chocarse(Jugador jugador, Celda[][] tablero) {
        jugador.morir();
    }
}
