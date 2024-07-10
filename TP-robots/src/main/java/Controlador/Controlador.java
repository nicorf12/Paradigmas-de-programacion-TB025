package Controlador;

import Interfaz.Ventana;
import Interfaz.VentanaInicio;
import Interfaz.VentanaNivel;
import Interfaz.VentanaReset;
import Logica.Juego;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.transform.Rotate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Controlador {
    private VentanaNivel ventanaNivel = null;
    private final VentanaReset ventanaReset;
    private final VentanaInicio ventanaInicio;
    private Juego partidaActual = null;
    private static final KeyCodeCombination arribaIzquierda = new KeyCodeCombination(KeyCode.Q);
    private static final KeyCodeCombination arriba = new KeyCodeCombination(KeyCode.W);
    private static final KeyCodeCombination arribaDerecha = new KeyCodeCombination(KeyCode.E);
    private static final KeyCodeCombination izquierda = new KeyCodeCombination(KeyCode.A);
    private static final KeyCodeCombination quieto = new KeyCodeCombination(KeyCode.S);
    private static final KeyCodeCombination derecha = new KeyCodeCombination(KeyCode.D);
    private static final KeyCodeCombination abajoIzquierda = new KeyCodeCombination(KeyCode.Z);
    private static final KeyCodeCombination abajo = new KeyCodeCombination(KeyCode.X);
    private static final KeyCodeCombination abajoDerecha = new KeyCodeCombination(KeyCode.C);

    private boolean isTPSafe = false;

    public Controlador(VentanaReset ventanaReset, VentanaInicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;
        this.ventanaReset = ventanaReset;
    }

    public void inicializarEventosBotones() {
        ventanaInicio.agregarBotonEscucha(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int[] dimensiones = ventanaInicio.getDimension();
                int filas = dimensiones[0];
                int columnas = dimensiones[1];
                partidaActual = new Juego(filas,columnas);
                var posicionJugador = partidaActual.obtenerPosicionJugador();
                try {
                    ventanaNivel = new VentanaNivel(filas,columnas);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                ventanaNivel.actualizar(partidaActual.obtenerPosicionesRobots(), posicionJugador, partidaActual.obtenerPosicionesExplosiones());
                inicializarEventos();

                ventanaInicio.cerrar();
                ventanaNivel.mostrar();
            }
        });

        ventanaReset.agregarEscuchaBotonReset(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ventanaReset.cerrar();
                ventanaInicio.mostrar();
            }
        });
    }

    public void inicializarEventos(){

        ventanaNivel.agregarBotonEscucha(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                isTPSafe = true;
            }
        }, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                partidaActual.teletransportacionRandom();
                pasarTurno(0,0,true);
            }
        });

        ventanaNivel.agregarMovimientoEscucha(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double click_x = mouseEvent.getX();
                double click_y = mouseEvent.getY();
                int[] posicion_j = partidaActual.obtenerPosicionJugador();
                double fila = posicion_j[0];
                double columna = posicion_j[1];
                double tamanioCasilla = ventanaNivel.getTamanioCasilla();

                int izq_der = 0;
                int arr_aba = 0;

                if (click_x < columna*tamanioCasilla) {
                    //izquierda
                    izq_der = -1;
                } else if (click_x > columna*tamanioCasilla +tamanioCasilla) {
                    //derecha
                    izq_der = 1;
                }

                if (click_y > fila*tamanioCasilla+tamanioCasilla) {
                    //abajo
                    arr_aba = 1;
                } else if (click_y < fila*tamanioCasilla) {
                    //arriba
                    arr_aba = -1;
                }


                if (isTPSafe) {
                    boolean seMovio = partidaActual.teletransportacionSegura( (int)(click_y/tamanioCasilla),(int)(click_x/tamanioCasilla));
                    if (seMovio){
                        ventanaNivel.actualizarSafeTPs(partidaActual.TPSeguraRestantes());
                    }
                    pasarTurno(arr_aba,izq_der,isTPSafe);
                    isTPSafe = false;
                } else {
                    pasarTurno(arr_aba,izq_der,isTPSafe);
                }
            }
        }, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (quieto.match(keyEvent)) {
                    pasarTurno(0,0,false);
                } else if (arriba.match(keyEvent)) {
                    pasarTurno(-1,0,false);
                } else if (arribaDerecha.match(keyEvent)) {
                    pasarTurno(-1,1,false);
                } else if (arribaIzquierda.match(keyEvent)) {
                    pasarTurno(-1,-1,false);
                } else if (derecha.match(keyEvent)) {
                    pasarTurno(0,1,false);
                } else if (izquierda.match(keyEvent)) {
                    pasarTurno(0,-1,false);
                }else if (abajo.match(keyEvent)) {
                    pasarTurno(1,0,false);
                } else if (abajoDerecha.match(keyEvent)) {
                    pasarTurno(1,1,false);
                } else if (abajoIzquierda.match(keyEvent)) {
                    pasarTurno(1,-1,false);
                }
            }
        });
    }
    public void pasarTurno(int x, int y, boolean esTP){ //usuario interactua para mover personaje
        if (!esTP){
            partidaActual.moverJugador(x,y);
        }
        partidaActual.moverRobots();

        if (!partidaActual.juegoEstaEnCurso()){
            ventanaNivel.cerrar();
            ventanaReset.mostrar();
            return;
        }
        if (partidaActual.nivelTerminado()){
            partidaActual.pasarSiguienteNivel();
            ventanaNivel.actualizarSafeTPs(1);
        }
        ventanaNivel.actualizar(partidaActual.obtenerPosicionesRobots(), partidaActual.obtenerPosicionJugador(), partidaActual.obtenerPosicionesExplosiones());
    }

}