package cellerAubarca.controllers;

import cellerAubarca.models.Type;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TemporadesController implements Initializable {
    public JFXButton deleteButton;
    public JFXDatePicker dateSelector;
    public JFXComboBox<Type> typeSelector;
    public JFXButton saveButton;
    public TableView ametllaTable;
    public TableView olivaTable;
    public TableView raimTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Type> tipus = new ArrayList<>();
        tipus.add(Type.AMETLLA);
        tipus.add(Type.RAIM);
        tipus.add(Type.OLIVA);
        typeSelector.getItems().addAll(tipus);
    }

    public void delete() {
    }
}
