package cellerAubarca.controllers;

import cellerAubarca.MainRunner;
import cellerAubarca.models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
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
    public JFXButton activaButton;

    private MenuController menuController;
    private Stage temporadesStage;

    public TemporadesController (MenuController menuController) {

        // We received the first controller, now let's make it usable throughout this controller.
        this.menuController = menuController;
        // Create the new stage
        temporadesStage = new Stage();
        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cellerAubarca/views/temporades.fxml"));
            // Set this class as the controller
            loader.setController(this);
            // Load the scene
            temporadesStage.setScene(new Scene(loader.load()));
            // Setup the window/stage
            temporadesStage.setTitle("Celler Aubarca - Temporades");
            temporadesStage.getIcons().add(new Image("/icons/grapeLogo.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        temporadesStage.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ametllaTable.setPlaceholder(new Label("CARREGANT TEMPORADES ..."));
        raimTable.setPlaceholder(new Label("CARREGANT TEMPORADES ..."));
        olivaTable.setPlaceholder(new Label("CARREGANT TEMPORADES ..."));
        ArrayList<Type> tipus = new ArrayList<>();
        tipus.add(Type.AMETLLA);
        tipus.add(Type.RAIM);
        tipus.add(Type.OLIVA);
        typeSelector.getItems().addAll(tipus);
        DBController.getInstance().getDBTemporadesController().resetData();
        Platform.runLater(() -> {
            try {
                DBController.getInstance().getDBTemporadesController().getTemporades();
                DBController.getInstance().getDBTemporadesController().splitTemporades();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
        Platform.runLater(this::loadAmetllaTemporadesTable);
        Platform.runLater(this::loadOlivaTemporadesTable);
        Platform.runLater(this::loadRaimTemporadesTable);

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
        TableColumn<TemporadaDataModel, Boolean> activeCol = new TableColumn<>("Activa");
        activeCol.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
        activeCol.setCellFactory(CheckBoxTableCell.forTableColumn(param -> raimTable.getItems().get(param).activeProperty()));

        ObservableList<TemporadaDataModel> temporadesData = toObservableArrayListOfTemporades(DBController.getInstance().getDBTemporadesController().getTemporadesRaim());
        raimTable.setItems(temporadesData);
        raimTable.getColumns().setAll(idCol, typeCol, dateCol, activeCol);

        raimTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ametllaTable.getSelectionModel().clearSelection();
                olivaTable.getSelectionModel().clearSelection();
            }
        });
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
        activeCol.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
        activeCol.setCellFactory(CheckBoxTableCell.forTableColumn(param -> olivaTable.getItems().get(param).activeProperty()));

        ObservableList<TemporadaDataModel> temporadesData = toObservableArrayListOfTemporades(DBController.getInstance().getDBTemporadesController().getTemporadesOliva());
        olivaTable.setItems(temporadesData);
        olivaTable.getColumns().setAll(idCol, typeCol, dateCol, activeCol);

        olivaTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                raimTable.getSelectionModel().clearSelection();
                ametllaTable.getSelectionModel().clearSelection();
            }
        });
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
        activeCol.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
        activeCol.setCellFactory(CheckBoxTableCell.forTableColumn(param -> ametllaTable.getItems().get(param).activeProperty()));

        ObservableList<TemporadaDataModel> temporadesData = toObservableArrayListOfTemporades(DBController.getInstance().getDBTemporadesController().getTemporadesAmetlla());
        ametllaTable.setItems(temporadesData);
        ametllaTable.getColumns().setAll(idCol, typeCol, dateCol, activeCol);

        ametllaTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                raimTable.getSelectionModel().clearSelection();
                olivaTable.getSelectionModel().clearSelection();
            }
        });

    }

    public void delete() throws IOException {
        TemporadaDataModel olivaItem = olivaTable.getSelectionModel().getSelectedItem();
        TemporadaDataModel raimItem = raimTable.getSelectionModel().getSelectedItem();
        TemporadaDataModel ametllaItem = ametllaTable.getSelectionModel().getSelectedItem();

        if (raimItem != null) {
            deleteItem(raimItem);
            if (raimItem.isActive()) menuController.deleteTemporadaRaim();
        }
        else if (olivaItem != null) {
            deleteItem(olivaItem);
            if (olivaItem.isActive()) menuController.deleteTemporadaOliva();
        }
        else if (ametllaItem != null){
            deleteItem(ametllaItem);
            if (ametllaItem.isActive()) menuController.deleteTemporadaAmetlla();
        }
    }

    private void deleteItem(TemporadaDataModel item) throws IOException {
        ServerResponse serverResponse = DBController.getInstance().getDBTemporadesController().deleteTemporada(item.getObjectId());
        if (serverResponse.getStatus() == 200) actualizeTables(item);
    }

    private void actualizeTables(TemporadaDataModel item) {
        switch (item.getType()) {
            case "AM":
                if (item.getActive())
                ametllaTable.getItems().remove(item);
                MainRunner.setAmetllaActive("");
                break;
            case "OL":
                if (item.getActive())
                olivaTable.getItems().remove(item);
                MainRunner.setOlivaActive("");
                break;
            default:
                if (item.getActive())
                raimTable.getItems().remove(item);
                MainRunner.setRaimActive("");
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

    public void activaTemporada() throws IOException, JSONException {
        if (ametllaTable.getSelectionModel().getSelectedItem() != null) {
            TemporadaDataModel item = ametllaTable.getSelectionModel().getSelectedItem();
            //TODO: actualitzar active i inactive de la BD
            actualitzaActiveTable(ametllaTable);
            DBController.getInstance().getDBTemporadesController().editTemporada(item);

            //TODO: passar valor al menu
            menuController.setAmetlla(ametllaTable.getSelectionModel().getSelectedItem().getDate());
        }
        if (raimTable.getSelectionModel().getSelectedItem() != null) {
            TemporadaDataModel item = raimTable.getSelectionModel().getSelectedItem();
            //TODO: actualitzar active i inactive de la BD
            actualitzaActiveTable(raimTable);
            DBController.getInstance().getDBTemporadesController().editTemporada(item);

            //TODO: passar valor al menu
            menuController.setRaim(raimTable.getSelectionModel().getSelectedItem().getDate());
        }
        if (olivaTable.getSelectionModel().getSelectedItem() != null) {
            TemporadaDataModel item = olivaTable.getSelectionModel().getSelectedItem();
            //TODO: actualitzar active i inactive de la BD
            actualitzaActiveTable(olivaTable);
            DBController.getInstance().getDBTemporadesController().editTemporada(item);

            //TODO: passar valor al menu
            menuController.setOliva(olivaTable.getSelectionModel().getSelectedItem().getDate());
        }
        if (raimTable.getSelectionModel().getFocusedIndex() < 0 && ametllaTable.getSelectionModel().getFocusedIndex() < 0 && olivaTable.getSelectionModel().getFocusedIndex() < 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Seleccioneu una temporada de la taula per activar-la.");
            alert.showAndWait();
        }
    }

    private void actualitzaActiveTable(TableView<TemporadaDataModel> table) {
        boolean trobat = false;
        int i=0;
        while (!trobat && i<table.getItems().size()) {
            if (table.getItems().get(i).getActive()) {
                table.getItems().get(i).setActive(false);
                trobat = true;
            }
            ++i;
        }
        table.getSelectionModel().getSelectedItem().setActive(true);
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }
}
