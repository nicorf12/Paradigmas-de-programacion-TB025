package Logica;

public class RobotChico extends Robot{
    @Override
    public void mover(int x_j, int y_j) {
        int x_r = getX();
        int y_r = getY();

        if (x_r > x_j) {
            x_r--;
        } else if (x_r < x_j) {
            x_r++;
        }

        if (y_r > y_j) {
            y_r--;
        } else if (y_r < y_j) {
            y_r++;
        }
        setPosicion(x_r,y_r);
    }
}
