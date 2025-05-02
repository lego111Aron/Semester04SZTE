package hu.alkfejl.view;

import hu.alkfejl.controller.MobilController;
import hu.alkfejl.modell.Mobil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MobilListaz implements Initializable {

    public TableColumn<Mobil, String> nevColum;
    public TableColumn<Mobil, String> gyartoColum;
    public TableColumn<Mobil, Boolean> esimColum;
    public TableView<Mobil> tableTable;

    public TextField keresTextField;
    public Button keresButton;

    MobilController mobilController = MobilController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nevColum.setCellValueFactory(new PropertyValueFactory<>("nev"));
        gyartoColum.setCellValueFactory(new PropertyValueFactory<>("gyarto"));
        esimColum.setCellValueFactory(new PropertyValueFactory<>("esim"));
        tableTable.getItems().setAll(
            mobilController.find(new Mobil())
        );
        tableTable.refresh();
    }


    public void filterke(ActionEvent actionEvent) {
        String nev = keresTextField.getText();
        if(nev.isEmpty()) {
            tableTable.getItems().setAll(
                    mobilController.find(new Mobil())
            );
        } else {
            Mobil filterednev = new Mobil();
            filterednev.setNev(nev);
            tableTable.getItems().setAll(
                    mobilController.find(filterednev)
            );
        }
        tableTable.refresh();
    }
}
