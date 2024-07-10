package Logica;

import java.util.Arrays;

public abstract class Ubicable {
    private int x;
    private int y;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setPosicion(int x,int y) {
        this.x = x;
        this.y = y;
    }
}
