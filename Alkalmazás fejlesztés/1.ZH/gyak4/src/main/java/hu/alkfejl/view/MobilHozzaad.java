package hu.alkfejl.view;

import hu.alkfejl.controller.MobilController;
import hu.alkfejl.dao.MobilDAOImpl;
import hu.alkfejl.modell.Mobil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MobilHozzaad implements Initializable {
    public TextField nevTextField;
    public ComboBox<String> gyartoComboBox;
    public CheckBox esimCheckBox;
    public Button okButton;
    public Button megseButton;


    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) nevTextField.getScene().getWindow();
        stage.close();
    }

    public void save(ActionEvent actionEvent) {
        StringBuilder errors = new StringBuilder();

        if (nevTextField.getText().isEmpty())
            errors.append("Hianyzik a nev\n");
        if (gyartoComboBox.getValue() == null || gyartoComboBox.getValue().isEmpty())
            errors.append("Nem valasztott gyartot");
        if (!errors.toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setContentText(errors.toString());
            alert.showAndWait();
            return;
        }

        if (MobilController.getInstance().add(new Mobil(
                nevTextField.getText(),
                gyartoComboBox.getValue(),
                esimCheckBox.isSelected()
        ))) {
            Stage stage = (Stage) nevTextField.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setContentText("Sikertelen bevitel");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gyartoComboBox.getItems().setAll(
                "A", "S", "X", "H"
        );
    }
}
