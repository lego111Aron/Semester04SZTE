package hu.alkfejl.view.common;

import hu.alkfejl.App;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    public MenuItem jegyzokonyvFelvitelMI;
    public MenuItem jegyzokonyvListazasMI;
    public MenuItem segitsegMI;

    public void openJegyzokonyvFelvitel(ActionEvent event){
        Stage stage = new Stage();
        Scene scene = null;
        try {
            scene = new Scene(App.loadFXML("jegyzokonyv_felvitel"));
        } catch (IOException e) {
            System.err.println("Hiba: " + e.getMessage());
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Hiba a form betöltésekor.");
            a.showAndWait();
        }
        stage.setScene(scene);
        stage.show();
    }

    public void setJegyzokonyvListazas(ActionEvent event){
        try{
            App.setRoot("jegyzokonyv_listazas");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void openSegitseg(ActionEvent event){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("Egy jegyzőkönyv az alábbi mezőkkel rendelkezik.");
        a.setContentText("Cím\t\tA jegyzőkönyv címe (szöveg)\nLeírás\tA jegyzőkönyv tartalma (szöveg)\nJegyző\tA rögzítő neve (szöveg)\nDátum\tA rögzítés dátuma: YYYY-MM-DD (szöveg)");
        a.showAndWait();
    }
}
