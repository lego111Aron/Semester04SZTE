package hu.alkfej.view;

import hu.alkfej.controller.KutyaController;
import hu.alkfej.modul.Kutya;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Hozzaad implements Initializable {

    public TextField nevTextField;
    public TextField gazdaTextField;
    public ComboBox<String> fajtaComboBox;

    public void megse(ActionEvent actionEvent) {
        Stage stage = (Stage) nevTextField.getScene().getWindow();
        stage.close();
    }

    public void save(ActionEvent actionEvent) {
        StringBuilder errors = new StringBuilder();
        if (nevTextField.getText().isEmpty())
            errors.append("hiba 1.");
        if (fajtaComboBox.getValue() == null || fajtaComboBox.getValue().isEmpty())
            errors.append("hiba 2.");
        if (gazdaTextField.getText().isEmpty())
            errors.append("hiba 3.");

        if (!errors.toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setContentText(errors.toString());
            alert.showAndWait();
            return;
//            Stage stage = (Stage) nevTextField.getScene().getWindow();
//            stage.close();
        }

        if (KutyaController.getInstance().add(new Kutya( // ez
                nevTextField.getText(),
                fajtaComboBox.getValue(),
                gazdaTextField.getText()
        ))) {
            Stage stage = (Stage) nevTextField.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setContentText("Sikeretelen");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fajtaComboBox.getItems().addAll(
                "a", "b", "c"
        );
    }
}
