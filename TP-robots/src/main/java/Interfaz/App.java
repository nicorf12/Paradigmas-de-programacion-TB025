package Interfaz;

import Controlador.Controlador;
import Logica.Juego;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        var pane = new VBox();
        Image presentacionImage = new Image(new FileInputStream("src/main/resources/presentacion.jpg"));
        ImageView presentacionView = new ImageView(presentacionImage);
        pane.getChildren().add(presentacionView);
        var scene = new Scene(pane, 640, 480);
        stage.setTitle("Presentacion");
        stage.setScene(scene);
        stage.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            stage.close();
            VentanaInicio ventanaInicio = new VentanaInicio();
            VentanaReset ventanaReset = new VentanaReset();
            Controlador controlador = new Controlador(ventanaReset,ventanaInicio);
            controlador.inicializarEventosBotones();
            ventanaInicio.mostrar();
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}