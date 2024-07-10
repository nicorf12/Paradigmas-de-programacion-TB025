package Interfaz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class VentanaInicio extends Ventana {
    private final TextField f = new TextField();
    private final TextField c = new TextField();
    private Button comenzar = new Button("Abrir Juego");
    public VentanaInicio(){
        var pane = new VBox();
        var text_f = new Label("¿Cuantas filas deseas?");
        text_f.setTextFill(Color.rgb(85,239,245));
        var filasPane = new VBox(text_f,f);
        pane.getChildren().add(filasPane);
        filasPane.setAlignment(Pos.BASELINE_CENTER);
        pane.setSpacing(20);

        var text_c = new Label("¿Cuantas columnas deseas?");
        text_c.setTextFill(Color.rgb(85,239,245));
        var columnasPane = new VBox(text_c,c);
        pane.getChildren().add(columnasPane);
        columnasPane.setAlignment(Pos.BASELINE_CENTER);

        pane.getChildren().add(comenzar);
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(2, 14, 36), CornerRadii.EMPTY, Insets.EMPTY)));

        var scene = new Scene(pane, 640, 480);
        super.stage.setTitle("Inicio");
        stage.setScene(scene);
    }

    public void agregarBotonEscucha(EventHandler<ActionEvent> handler){
        comenzar.setOnAction(handler);
    }

    public int[] getDimension() throws NumberFormatException {
        int n = 0;
        int m = 0;
        try {
            n = Integer.parseInt(f.getText());
            m = Integer.parseInt(c.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Valor no válido");
            alert.setContentText("Ingresa valores numéricos en los campos.");
            alert.showAndWait();
        }
        return new int[]{n, m};
    }

}
