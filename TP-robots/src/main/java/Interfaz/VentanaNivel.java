package Interfaz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class VentanaNivel extends Ventana {
    private GridPane tablero = new GridPane();
    private GridPane overlay = new GridPane();
    private ImageView[][] overlay_aux;
    private VBox panelMain = new VBox();
    private final int cantF;
    private final int cantC;
    private int restante_int;
    private final Button botonSafeTP;
    private final Button botonRandomTP;
    private final double tamanioCasilla = 20;
    private final Image jugadorImage = new Image(new FileInputStream("src/main/resources/jugador.png"));
    private final Image robotx1Image = new Image(new FileInputStream("src/main/resources/robotx1.png"));
    private final Image robotx2Image = new Image(new FileInputStream("src/main/resources/robotx2.png"));
    private final Image explosionImage = new Image(new FileInputStream("src/main/resources/explosion.png"));

    public VentanaNivel(int i,int j) throws FileNotFoundException {
        cantF = i;
        cantC = j;
        restante_int = 1;

        double anchoTablero = cantC * tamanioCasilla;
        double altoTablero = cantF * tamanioCasilla;

        overlay_aux = new ImageView[cantF][cantC];
        for (int fila = 0; fila < cantF; fila++) {
            for (int columna = 0; columna < cantC; columna++) {
                overlay_aux[fila][columna] = new ImageView();
                overlay_aux[fila][columna].setFitWidth(tamanioCasilla);
                overlay_aux[fila][columna].setFitHeight(tamanioCasilla);
                overlay.add(overlay_aux[fila][columna],columna,fila);

                Rectangle casilla = new Rectangle(tamanioCasilla,tamanioCasilla);
                casilla.setFill((fila + columna) % 2 == 0 ? Color.LIGHTCYAN : Color.LIGHTBLUE);
                tablero.add(casilla, columna, fila);
            }
        }

        botonSafeTP = new Button("    TPSafe\n(Restantes: "+restante_int+")");
        botonRandomTP = new Button("TPRandom:");
        botonSafeTP.setMinSize(anchoTablero/2, Math.max(altoTablero/8,70));
        botonRandomTP.setMinSize(anchoTablero/2, Math.max(altoTablero/8,70));
        

        GridPane safePane = new GridPane(1,2);
        safePane.setAlignment(Pos.CENTER);
        safePane.add(botonSafeTP,0,0);

        HBox botonPane = new HBox(2);
        botonPane.setAlignment(Pos.CENTER);
        botonPane.getChildren().addAll(botonRandomTP, safePane);

        GridPane root = new GridPane();
        root.add(tablero, 0, 0);
        root.add(overlay, 0, 0);
        panelMain.getChildren().addAll(root,botonPane);

        tablero.setPrefWidth(anchoTablero);
        tablero.setPrefHeight(altoTablero);

        panelMain.setPrefWidth(anchoTablero);
        panelMain.setPrefHeight(altoTablero + botonPane.getHeight());

        Scene scene = new Scene(panelMain);
        super.stage.setTitle("Robots");
        super.stage.setScene(scene);

        Image cursorImage = new Image(new FileInputStream("src/main/resources/cursor.png"));
        panelMain.setCursor(new ImageCursor(cursorImage));
    }

    public void actualizarSafeTPs(int n){
        restante_int = n;
        botonSafeTP.setText("       TPSafe\n(Restantes: "+n+")");
    }

    public void agregarBotonEscucha(EventHandler<ActionEvent> handler_safe,EventHandler<ActionEvent> handler_random){
        botonSafeTP.setOnAction(handler_safe);
        botonRandomTP.setOnAction(handler_random);
    }

    public void agregarMovimientoEscucha(EventHandler<MouseEvent> handler_mouse, EventHandler<KeyEvent> handler_keyboard ){
        overlay.addEventHandler(MouseEvent.MOUSE_CLICKED , handler_mouse);
        stage.addEventHandler(KeyEvent.KEY_PRESSED, handler_keyboard);
    }

    public double getTamanioCasilla() {
        return tamanioCasilla;
    }

    public void actualizar(int[][] posicionRobots,int[] posicionJugador,int[][] posicionesExplosiones){
        for (int fila = 0; fila < cantF; fila++) {
            for (int columna = 0; columna < cantC; columna++) {
                overlay_aux[fila][columna].setImage(null);
            }
        }
        ubicarJugador(posicionJugador);
        ubicarRobots(posicionRobots);
        ubicarExplosiones(posicionesExplosiones);
    }

    private void ubicarJugador(int[] posicionJugador){
        overlay_aux[posicionJugador[0]][posicionJugador[1]].setImage(jugadorImage);
    }

    private void ubicarRobots(int[][] posicionesRobots){
        for (int[] posicionesRobot : posicionesRobots) {
            int tipo = posicionesRobot[0];
            int fila  = posicionesRobot[1];
            int columna = posicionesRobot[2];

            if (tipo == 1) {
                overlay_aux[fila][columna].setImage(robotx1Image);
            } else {
                overlay_aux[fila][columna].setImage(robotx2Image);
            }
        }
    }
    private void ubicarExplosiones(int[][] posicionesExplosiones){
        for (int[] posicionesExplosione : posicionesExplosiones) {
            int fila = posicionesExplosione[0];
            int columna = posicionesExplosione[1];
            overlay_aux[fila][columna].setImage(explosionImage);
        }
    }
}