package hu.alkfejl.view;

import java.io.IOException;

import hu.alkfejl.App;
import javafx.fxml.FXML;

public class PrimaryViewController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
