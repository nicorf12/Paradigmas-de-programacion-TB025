package Interfaz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class VentanaReset extends Ventana{
    private Button reset = new Button("Abrir Juego de Robots");
    public VentanaReset() {
        var pane = new VBox();
        var jugar = new Label("¿Deseas volver a jugar?");
        jugar.setTextFill(Color.rgb(85,239,245));
        pane.getChildren().add(jugar);

        pane.setBackground(new Background(new BackgroundFill(Color.rgb(2, 14, 36), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().add(reset);
        var scene = new Scene(pane, 640, 480);
        super.stage.setTitle("Reset");
        stage.setScene(scene);
    }

    public void agregarEscuchaBotonReset(EventHandler<ActionEvent> handler){
        reset.setOnAction(handler);
    }
}
