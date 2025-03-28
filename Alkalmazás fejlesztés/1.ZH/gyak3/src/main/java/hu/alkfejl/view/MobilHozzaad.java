package hu.alkfejl.view;

import hu.alkfejl.controller.MobilController;
import hu.alkfejl.modell.Mobil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

// Az implementálást hozzá kell írni
public class MobilHozzaad implements Initializable {
    public TextField TextNev;
//    Meg kell adni, hogy string
    public ComboBox<String> ComboGyarto;
    public CheckBox CheckEsim;

    public void close(ActionEvent actionEvent) {
//        Fejből
        Stage stage = (Stage) TextNev.getScene().getWindow();
        stage.close();
    }

    public void save(ActionEvent actionEvent) {
//        Fejből
        StringBuilder errors = new StringBuilder();
        if (TextNev.getText().isEmpty()) {
            errors.append("Üres a nev mező!\n");
        }
        if (ComboGyarto.getValue() == null || ComboGyarto.getValue().isEmpty()) {
            errors.append("Üres a gyarto mező!\n");
        }
        if (!errors.toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(errors.toString());
            alert.showAndWait();
            return;
        }

        if (MobilController.getInstance().add(new Mobil(
                TextNev.getText(),
                ComboGyarto.getValue(),
                CheckEsim.isSelected()
        ))) {
            Stage stage = (Stage) TextNev.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("sikertelen mentés");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        fejből
        ComboGyarto.getItems().addAll(
                "Apple", "Samsung", "Xiaomi", "Huawei"
        );
    }
}
