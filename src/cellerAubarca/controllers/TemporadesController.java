package cellerAubarca.controllers;

import cellerAubarca.models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TemporadesController implements Initializable {
    public JFXButton deleteButton;
    public JFXDatePicker dateSelector;
    public JFXComboBox<Type> typeSelector;
    public JFXButton saveButton;
    public TableView<TemporadaDataModel> ametllaTable;
    public TableView<TemporadaDataModel> olivaTable;
    public TableView<TemporadaDataModel> raimTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Type> tipus = new ArrayList<>();
        tipus.add(Type.AMETLLA);
        tipus.add(Type.RAIM);
        tipus.add(Type.OLIVA);
        typeSelector.getItems().addAll(tipus);
        try {
            DBController.getInstance().getDBTemporadesController().resetData();
            DBController.getInstance().getDBTemporadesController().getTemporades();
            DBController.getInstance().getDBTemporadesController().splitTemporades();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        ametllaTable.setEditable(true);
        olivaTable.setEditable(true);
        raimTable.setEditable(true);
        loadAmetllaTemporadesTable();
        loadOlivaTemporadesTable();
        loadRaimTemporadesTable();
    }

    private void loadRaimTemporadesTable() {
        TableColumn<TemporadaDataModel, String> idCol = new TableColumn<>("Codi");
        idCol.setCellValueFactory(new PropertyValueFactory<>("objectId"));
        idCol.setVisible(false);

        TableColumn<TemporadaDataModel, String> typeCol = new TableColumn<>("Tipus");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<TemporadaDataModel, String> dateCol = new TableColumn<>("Temporada");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        /////////////creacio checkbox/////////////////
        TableColumn<TemporadaDataModel, Boolean> activeCol = new TableColumn<>("Active");
        activeCol.setCellValueFactory(param -> {
            TemporadaDataModel person = param.getValue();

            SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(person.isActive());

            // Note: singleCol.setOnEditCommit(): Not work for
            // CheckBoxTableCell.
            // When "Single?" column change.
            booleanProp.addListener((observable, oldValue, newValue) -> person.setActive(newValue));
            return booleanProp;
        });

        activeCol.setCellFactory(p -> {
            CheckBoxTableCell<TemporadaDataModel, Boolean> cell = new CheckBoxTableCell<>();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
////////////////////////////////////////////////////////
        ObservableList<TemporadaDataModel> temporadesData = toObservableArrayListOfTemporades(DBController.getInstance().getDBTemporadesController().getTemporadesRaim());
        raimTable.setItems(temporadesData);
        raimTable.getColumns().setAll(idCol, typeCol, dateCol, activeCol);

    }

    private void loadOlivaTemporadesTable() {
        TableColumn<TemporadaDataModel, String> idCol = new TableColumn<>("Codi");
        idCol.setCellValueFactory(new PropertyValueFactory<>("objectId"));
        idCol.setVisible(false);

        TableColumn<TemporadaDataModel, String> typeCol = new TableColumn<>("Tipus");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<TemporadaDataModel, String> dateCol = new TableColumn<>("Temporada");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<TemporadaDataModel, Boolean> activeCol = new TableColumn<>("Activa");
        activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));
        activeCol.setEditable(true);
        activeCol.setCellValueFactory(cell -> cell.getValue().activeProperty());
        activeCol.setCellFactory(CheckBoxTableCell.forTableColumn(activeCol));
        activeCol.setMaxWidth(50);
        activeCol.setCellValueFactory(cell -> {
            TemporadaDataModel p = cell.getValue();
            return new ReadOnlyBooleanWrapper(p.isActive());
        });

        ObservableList<TemporadaDataModel> temporadesData = toObservableArrayListOfTemporades(DBController.getInstance().getDBTemporadesController().getTemporadesOliva());
        olivaTable.setItems(temporadesData);
        olivaTable.getColumns().setAll(idCol, typeCol, dateCol, activeCol);

    }

    private void loadAmetllaTemporadesTable() {
        TableColumn<TemporadaDataModel, String> idCol = new TableColumn<>("Codi");
        idCol.setCellValueFactory(new PropertyValueFactory<>("objectId"));
        idCol.setVisible(false);

        TableColumn<TemporadaDataModel, String> typeCol = new TableColumn<>("Tipus");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<TemporadaDataModel, String> dateCol = new TableColumn<>("Temporada");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<TemporadaDataModel, Boolean> activeCol = new TableColumn<>("Activa");
        activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));
        activeCol.setEditable(true);
        activeCol.setCellValueFactory(cell -> cell.getValue().activeProperty());
        activeCol.setCellFactory(CheckBoxTableCell.forTableColumn(activeCol));
        activeCol.setMaxWidth(50);
        activeCol.setCellValueFactory(cell -> {
            TemporadaDataModel p = cell.getValue();
            return new ReadOnlyBooleanWrapper(p.isActive());
        });

        System.out.println("SIZE AMETLLA: "+ DBController.getInstance().getDBTemporadesController().getTemporadesAmetlla().size());
        ObservableList<TemporadaDataModel> temporadesData = toObservableArrayListOfTemporades(DBController.getInstance().getDBTemporadesController().getTemporadesAmetlla());
        ametllaTable.setItems(temporadesData);
        ametllaTable.getColumns().setAll(idCol, typeCol, dateCol, activeCol);

    }

    public void delete() throws IOException {
        TemporadaDataModel item;
        if (raimTable.getSelectionModel().getSelectedItem() != null) {
            item = raimTable.getSelectionModel().getSelectedItem();
        }
        else if (olivaTable.getSelectionModel().getSelectedItem() != null) {
            item = olivaTable.getSelectionModel().getSelectedItem();
        }
        else {
            item = ametllaTable.getSelectionModel().getSelectedItem();
        }
        ServerResponse serverResponse = DBController.getInstance().getDBTemporadesController().deleteTemporada(item.getObjectId());
        if (serverResponse.getStatus() == 200) actualizeTables(item);
    }

    private void actualizeTables(TemporadaDataModel item) {
        switch (item.getType()) {
            case "AM":
                ametllaTable.getItems().remove(item);
                break;
            case "OL":
                olivaTable.getItems().remove(item);
                break;
            default:
                raimTable.getItems().remove(item);
                break;
        }
    }

    public void saveTemporada() throws IOException, JSONException {
        if (typeSelector.getValue() == null || dateSelector.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Escolliu un tipus per la temporada i la seva data d'inici.");
            alert.showAndWait();
        }
        else {
            Temporada temporada = new Temporada(typeSelector.getValue(), dateSelector.getValue().toString(), false);
            ServerResponse serverResponse = DBController.getInstance().getDBTemporadesController().saveTemporada(temporada);
            if (serverResponse.getStatus() == 200) {
                JSONObject object = new JSONObject(serverResponse.getMessage());
                JSONObject newTemporada = object.getJSONObject("temporada");
                String newId = newTemporada.getString("_id");
                TemporadaDataModel temporadaDataModel = new TemporadaDataModel(newId, temporada.getTipus(), temporada.getDate(), false);
                if (temporada.getTipus().getCode().equals("AM")) {
                    ametllaTable.getItems().add(temporadaDataModel);
                } else if (temporada.getTipus().getCode().equals("RA")) {
                    raimTable.getItems().add(temporadaDataModel);
                } else {
                    olivaTable.getItems().add(temporadaDataModel);
                }
            }
        }

    }

    private ObservableList<TemporadaDataModel> toObservableArrayListOfTemporades(ArrayList<Temporada> temporades) {
        ObservableList<TemporadaDataModel> data = FXCollections.observableArrayList();
        for (Temporada temporada : temporades) {
            TemporadaDataModel temporadaDataModel = new TemporadaDataModel(temporada.getObjectId(), temporada.getTipus(), temporada.getDate(), temporada.getActive());
            data.add(temporadaDataModel);
        }
        return data;
    }

}
