package hu.alkfej.view;

import hu.alkfej.controller.KutyaController;
import hu.alkfej.modell.Kutya;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static hu.alkfej.App.loadFXML;

public class Primary implements Initializable {
    public TableView<Kutya> tableTable;
    public TableColumn<Kutya, String> nevColumn;
    public TableColumn<Kutya, String> falytaColumn;
    public TableColumn<Kutya, String> gazdiColumn;
    public Button frissitButton;

    public void open(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(loadFXML("felvitel"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    KutyaController kutyaController = new KutyaController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nevColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
        falytaColumn.setCellValueFactory(new PropertyValueFactory<>("fajta"));
        gazdiColumn.setCellValueFactory(new PropertyValueFactory<>("gazdi"));
        tableTable.getItems().setAll(
            kutyaController.find(new Kutya())
        );
        tableTable.refresh();
    }

    public void frisit(ActionEvent actionEvent) {
        tableTable.getItems().setAll(
                kutyaController.find(new Kutya())
        );
        tableTable.refresh();
    }
}
