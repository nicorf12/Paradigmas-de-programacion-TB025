package Logica;

import java.util.ArrayList;
import java.util.Random;

public class Juego {
    private final Celda[][] tablero;
    private final int F, C, ROBOTSBASE;
    private final int RCHICO = 1;
    private final int RGRANDE = 2;
    private static final Random random = new Random();
    private final ArrayList<Robot> robots;
    private final ArrayList<Explosion> explosiones;
    private final Jugador jugador;
    private int nivel, cantidadRobotsVivos;
    private int restantesTPSegura;

    public Juego(int F, int C){
        tablero = new Celda[F][C];
        for (int i = 0; i < F; i++) {
            for (int j = 0; j < C; j++) {
                tablero[i][j] = new Celda();
            }
        }

        jugador = new Jugador();
        robots = new ArrayList<>();
        explosiones = new ArrayList<>();

        this.F = F;
        this.C = C;

        int denominador_robots_base = 25;
        ROBOTSBASE = ((F*C) / denominador_robots_base)+1;

        restantesTPSegura = 0;
        nivel = 0;

        pasarSiguienteNivel();

    }
    public int TPSeguraRestantes(){
        return restantesTPSegura;
    }

    public void pasarSiguienteNivel(){
        nivel++;
        restantesTPSegura++;
        robots.clear();
        explosiones.clear();
        limpiarTablero();
        inicializarJugador();
        inicializarRobots();
    }

    private void limpiarTablero(){
        for (int i = 0; i < F; i++) {
            for (int j = 0; j < C ; j++) {
                tablero[i][j].liberarCelda();
            }
        }
    }

    private int calcularRobots(){
        //Cuando la cantidad de robots es tal que el tablero queda completo con ellos, el jugador
        //morira de manera natural al mover donde sea y es ahi que finalizan los niveles si o si
        int robotsActual = nivel*ROBOTSBASE;
        if (robotsActual > (F*C)-1){
            robotsActual = (F*C)-1;
        }
        cantidadRobotsVivos = robotsActual;
        return robotsActual;
    }

    private void inicializarJugador(){
        tablero[F/2][C/2].setEntidad(jugador);
        jugador.setPosicion(F/2,C/2);
    }

    private void inicializarRobots() {
        //devuelve un arreglo en la que cada posicion tiene coord fila coord columna y tipo robot
        Random random = new Random();
        for (int i = 0; i < calcularRobots(); i++) {
            boolean ubicado = false;
            int tipo = random.nextInt(2) + 1;
            Robot robot;
            while (!ubicado) {
                int random1 = random.nextInt(F);
                int random2 = random.nextInt(C);

                if (tablero[random1][random2].isDisponible() && random1 != F/2 && random2 != C/2) {

                    robot = (tipo == RCHICO) ? new RobotChico() : new RobotGrande();
                    robot.setPosicion(random1,random2);
                    tablero[random1][random2].setEntidad(robot);
                    robots.add(robot);
                    ubicado = true;
                }
            }
        }
    }

    public boolean teletransportacionSegura(int x, int y){
        if (restantesTPSegura>0 && 0<=x && 0<=y && x<F && y<C){
            moverJugador(x - jugador.getX(),y - jugador.getY());
            restantesTPSegura--;
            return true;
        }
        return false;
    }

    public void teletransportacionRandom(){

        int jugadorX= jugador.getX();
        int jugadorY= jugador.getY();

        int arriba = jugadorX != 0 ? random.nextInt(jugadorX) : 0;
        int izquierda = jugadorY != 0 ? random.nextInt(jugadorY) : 0;

        int abajo = F-jugadorX != 0 ? random.nextInt(F-jugadorX) : 0;
        int derecha = C-jugadorY != 0 ? random.nextInt(C-jugadorY) : 0;

        moverJugador(obtenerNumeroAleatorio(-arriba, abajo),obtenerNumeroAleatorio(-izquierda,derecha));
    }

    private static int obtenerNumeroAleatorio(int a, int b) {

        if(a==0){
            return b;
        }
        if (b==0){
            return a;
        }
        return random.nextBoolean() ? a : b;
    }


    public boolean juegoEstaEnCurso(){
        return jugador.estaVivo();
    }
    public boolean nivelTerminado(){
        if(cantidadRobotsVivos>0){
            return false;
        }
        return true;
    }

    public void moverJugador(int x, int y) {
        //si no es una posicion valida, supongo que se "choca contra la pared" y no se mueve
        if (jugador.getX()+x<F && 0<=jugador.getX()+x&& 0<=jugador.getY()+y && jugador.getY()+y<C){
            tablero[jugador.getX()][jugador.getY()].liberarCelda();
            jugador.mover(x,y);
            if (tablero[jugador.getX()][jugador.getY()].getEntidad() instanceof Explosion){
                Explosion explosion= (Explosion) tablero[jugador.getX()][jugador.getY()].getEntidad();
                explosion.matar(jugador);
            }
            tablero[jugador.getX()][jugador.getY()].setEntidad(jugador);
        }

    }

    private void asignarPosicionesRobots(){
        for (Robot robot : robots) {
            if (robot.estaVivo() && tablero[robot.getX()][robot.getY()].getEntidad() instanceof Robot) {
                tablero[robot.getX()][robot.getY()].liberarCelda();
                robot.mover(jugador.getX(), jugador.getY());
            }
        }
    }
    public void moverRobots(){
        asignarPosicionesRobots();

        for (Robot robot : robots) {
            if(robot.estaVivo()) {
                int x = robot.getX();
                int y = robot.getY();
                if ( tablero[x][y].isDisponible() ) {
                    tablero[x][y].setEntidad(robot);
                } else { //hubo colision
                    Ubicable entidad = tablero[robot.getX()][robot.getY()].getEntidad();
                    if (entidad instanceof Robot) {
                        robot.chocarse((Robot)entidad, tablero, explosiones);
                    }else if (entidad instanceof Explosion){
                        robot.chocarse((Explosion)entidad);
                    }else{ //jugador
                        robot.chocarse((Jugador)entidad, tablero);
                    }
                }
            }

        }
        actualizarRobotsVivos();
    }

    private void actualizarRobotsVivos() {
        cantidadRobotsVivos=0;
        for (Robot robot : robots) {
            if(robot.estaVivo()) {
                cantidadRobotsVivos++;
            }
        }
    }

    public int[][] obtenerPosicionesRobots() {

        int[][] posicionesRobots = new int[cantidadRobotsVivos][3];
        int actual=0;
        for (Robot robot : robots) {
            if (robot.estaVivo()){
                posicionesRobots[actual] = (robot instanceof RobotGrande) ?
                        new int[]{RGRANDE, robot.getX(), robot.getY()} :
                        new int[]{RCHICO, robot.getX(), robot.getY()};
                actual++;
            }
        }
        return posicionesRobots;
    }

    public int[][] obtenerPosicionesExplosiones() {

        int[][] posicionesExplosiones = new int[explosiones.size()][2];
        int actual=0;
        for (Explosion explosion : explosiones) {
            posicionesExplosiones[actual][0]=explosion.getX();
            posicionesExplosiones[actual][1]=explosion.getY();
            actual++;
        }
        return posicionesExplosiones;
    }

    public int[] obtenerPosicionJugador() {
        return new int[]{jugador.getX(), jugador.getY()};
    }
}