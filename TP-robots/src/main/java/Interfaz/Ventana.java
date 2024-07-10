package Interfaz;

import javafx.stage.Stage;

public class Ventana {
    protected final Stage stage;

    public Ventana() {
        stage = new Stage();
        stage.setResizable(false);
    }
    public void mostrar(){
        stage.show();
    }
    public void cerrar(){ stage.close(); }
}
