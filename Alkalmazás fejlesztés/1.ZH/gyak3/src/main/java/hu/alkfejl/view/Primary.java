package hu.alkfejl.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static hu.alkfejl.App.loadFXML;

public class Primary {
    public void open(ActionEvent actionEvent) throws IOException {
//        Fejből kell tudni
        Stage stage = new Stage();
        Scene scene = new Scene(loadFXML("MobilHozzaad"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void open2(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(loadFXML("Lista"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}
