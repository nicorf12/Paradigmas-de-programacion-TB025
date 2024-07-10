package Logica;

public class Explosion extends Ubicable{

    public Explosion(Robot robot1, Robot robot2){
        robot1.morir();
        robot2.morir();
    }
    public void matar(Matable matable) {
        matable.morir();
    }
}
