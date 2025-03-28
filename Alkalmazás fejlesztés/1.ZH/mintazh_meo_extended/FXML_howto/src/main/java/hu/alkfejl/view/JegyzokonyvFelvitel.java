package hu.alkfejl.view;

import hu.alkfejl.controller.JegyzokonyvController;
import hu.alkfejl.model.Jegyzokonyv;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class JegyzokonyvFelvitel implements Initializable {
   @FXML
   private TextField cimInput;
   @FXML
   private TextField datumInput;
   @FXML
   private TextField jegyzoInput;
   @FXML
   private TextArea leirasInput;

   private JegyzokonyvController ctr;
   private Jegyzokonyv jegyzokonyv;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ctr = JegyzokonyvController.getInstance();
        this.jegyzokonyv = new Jegyzokonyv();
    }

    public void editJegyzokonyv(Jegyzokonyv jegyzokonyv){
        this.jegyzokonyv = Objects.requireNonNullElseGet(jegyzokonyv, Jegyzokonyv::new);

        cimInput.setText(jegyzokonyv.getCim());
        leirasInput.setText(jegyzokonyv.getLeiras());
        datumInput.setText(jegyzokonyv.getDatum());
        jegyzoInput.setText(jegyzokonyv.getJegyzo());
    }

    @FXML
    private void save(ActionEvent event){
        Jegyzokonyv jk  = new Jegyzokonyv();

        jk.setCim(cimInput.getText());
        jk.setLeiras(leirasInput.getText());
        jk.setDatum(datumInput.getText());
        jk.setJegyzo(jegyzoInput.getText());

        if(jegyzokonyv.getId()>0){
            jk.setId(jegyzokonyv.getId());
        }

        if(!ctr.add(jk)){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Hiba történt a mentés során.");
            a.showAndWait();
        }else{
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("A mentés sikeres volt");
            a.showAndWait();
            erase();
        }
    }

    private void erase(){
        cimInput.clear();
        leirasInput.clear();
        datumInput.clear();
        jegyzoInput.clear();
        jegyzokonyv.setId(0);
    }

}
