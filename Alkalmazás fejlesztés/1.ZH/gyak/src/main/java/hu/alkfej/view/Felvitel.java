package hu.alkfej.view;

import hu.alkfej.controller.KutyaController;
import hu.alkfej.modell.Kutya;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Felvitel implements Initializable {
    public TextField nevTextField;
    public TextField gazdiTextField;
    public ComboBox<String> fajtaComboBox;
    public Button megsemButton;
    public Button okButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fajtaComboBox.getItems().addAll(
                "Tacskó", "Németjuhász", "Agár", "Akita"
        );
    }

    public void megse(ActionEvent actionEvent) {
        Stage stage = (Stage) nevTextField.getScene().getWindow();
        stage.close();
    }

    public void save(ActionEvent actionEvent) {
        StringBuilder errors = new StringBuilder();
        if (nevTextField.getText().isEmpty())
            errors.append("Nincs megadva a nev\n");
        if (fajtaComboBox.getValue() == null || fajtaComboBox.getValue().isEmpty())
            errors.append("Nincs kiválasztva a fajta\n");
        if (gazdiTextField.getText().isEmpty())
            errors.append("Nincs megadva a gazda\n");

        if (!errors.toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setContentText(errors.toString());
            alert.showAndWait();
            return;
        }

        if (KutyaController.getInstance().add(new Kutya(
                nevTextField.getText(),
                fajtaComboBox.getValue(),
                gazdiTextField.getText()
        ))) {
            Stage stage = (Stage) nevTextField.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setContentText("Sikertelen adatmentés");
            alert.showAndWait();
        }
    }
}
