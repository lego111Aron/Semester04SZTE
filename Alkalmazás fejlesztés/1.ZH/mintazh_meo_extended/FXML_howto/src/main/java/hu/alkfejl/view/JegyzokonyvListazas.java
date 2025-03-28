package hu.alkfejl.view;

import hu.alkfejl.App;
import hu.alkfejl.controller.JegyzokonyvController;
import hu.alkfejl.dao.util.Column;
import hu.alkfejl.model.Jegyzokonyv;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JegyzokonyvListazas implements Initializable {

    @FXML private TableColumn<Jegyzokonyv,String> cimColumn;
    @FXML private TableColumn<Jegyzokonyv,String> datumColumn;
    @FXML private TextField cimKereses;
    @FXML private TextField datumKereses;
    @FXML private TableView<Jegyzokonyv> table;


    private JegyzokonyvController ctr;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ctr = JegyzokonyvController.getInstance();
        cimColumn.setCellValueFactory(new PropertyValueFactory<>("cim"));
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));

        table.getItems().setAll(ctr.filter(Column.URES, null));

        table.setRowFactory(tv ->{
            TableRow<Jegyzokonyv> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    Jegyzokonyv clicked = row.getItem();
                    try {
                        editJegyzokonyv(clicked);
                    }catch(Exception e){

                    }
                }
            });
            return row;
        });
    }

    private void editJegyzokonyv(Jegyzokonyv jegyzokonyv){
        Stage stage = new Stage();
        Scene scene = null;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("jegyzokonyv_felvitel.fxml"));
            scene = new Scene(loader.load());
            JegyzokonyvFelvitel ctr = loader.getController();
            ctr.editJegyzokonyv(jegyzokonyv);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println("Hiba: " + e.getMessage());
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Hiba a form betöltésekor.");
            a.showAndWait();
        }
    }

    @FXML
    private void filterJegyokonyv(ActionEvent event){
        String datumKer = datumKereses.getText();
        String cimKer = cimKereses.getText();
        Jegyzokonyv filter = new Jegyzokonyv();
        List<Jegyzokonyv> full = new ArrayList<>();

        if(null != cimKer && !cimKer.isEmpty()){
            filter.setCim(cimKer);
        }
        else if(null != datumKer && !datumKer.isEmpty()){
            filter.setDatum(datumKer);
        }
        else{
            full = ctr.filter(Column.URES, filter);
        }
        if(full.isEmpty()) {
            var cim = ctr.filter(Column.CIM, filter);
            var datum = ctr.filter(Column.DATUM, filter);

            var union = Stream.concat(cim.stream(), datum.stream())
                    .distinct()
                    .collect(Collectors.toList());

            table.setItems(FXCollections.observableList(union));
        }else{
            table.setItems(FXCollections.observableList(full));
        }
    }

}
