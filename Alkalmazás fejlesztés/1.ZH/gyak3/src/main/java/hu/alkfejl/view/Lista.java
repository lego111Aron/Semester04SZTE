package hu.alkfejl.view;

import hu.alkfejl.controller.MobilController;
import hu.alkfejl.modell.Mobil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

// ide is kell az implementálás
public class Lista implements Initializable {
//    oda kell írni a <részt>
    public TableColumn<Mobil, Boolean> ESIM;
    public TableView<Mobil> Tablee;
    public TableColumn<Mobil, String> Nev;
    public TableColumn<Mobil, String> Gyarto;
    public TextField Keres;

//    fejből

    MobilController controller = MobilController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ESIM.setCellValueFactory(new PropertyValueFactory<>("esim"));
        Nev.setCellValueFactory(new PropertyValueFactory<>("nev"));
        Gyarto.setCellValueFactory(new PropertyValueFactory<>("gyarto"));
        Tablee.getItems().setAll(
            controller.find(new Mobil())
        );
        Tablee.refresh();
    }

    public void filteke(ActionEvent actionEvent) {
        String nev = Keres.getText();

        if (nev.isEmpty()) {
            Tablee.getItems().setAll(
                    controller.find(new Mobil())
            );
        } else {
            Mobil filteredNev = new Mobil();
            filteredNev.setNev(nev);
            Tablee.getItems().setAll(
                    controller.find(filteredNev)
            );
        }
        Tablee.refresh();
    }
}
