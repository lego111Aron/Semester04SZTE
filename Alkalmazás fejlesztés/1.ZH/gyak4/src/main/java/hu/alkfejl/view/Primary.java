package hu.alkfejl.view;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static hu.alkfejl.App.loadFXML;

public class Primary {
    public void open(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(loadFXML("MobilHozzaad"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void open2(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(loadFXML("MobilListaz"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}
