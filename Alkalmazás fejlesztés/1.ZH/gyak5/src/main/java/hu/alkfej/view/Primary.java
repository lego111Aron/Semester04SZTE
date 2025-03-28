package hu.alkfej.view;

import hu.alkfej.controller.KutyaController;
import hu.alkfej.modul.Kutya;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
    public TableColumn<Kutya, String> nevTableColumn;
    public TableColumn<Kutya, String> gazdiTableColumn;
    public TableColumn<Kutya, String> fajtaTableColumn;

    public void open(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(loadFXML("hozzaad"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    private static KutyaController kutyaController = new KutyaController();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nevTableColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
        fajtaTableColumn.setCellValueFactory(new PropertyValueFactory<>("fajta"));
        gazdiTableColumn.setCellValueFactory(new PropertyValueFactory<>("gazda"));
        tableTable.getItems().setAll(
                KutyaController.getInstance().find(new Kutya())
        );
        tableTable.refresh(); // ez
    }
}
