package cellerAubarca.controllers;

import cellerAubarca.models.*;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AlbaransController implements Initializable {
    public JFXButton deleteButton;
    public JFXButton newInAlbaraButton;
    public JFXButton facturarButton;
    public TableView inAlbaransTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inAlbaransTable.setPlaceholder(new Label("CARREGANT ALBARANS D'ENTRADA ..."));
        Platform.runLater(() -> {
            try {
                setInAlbaransTableData();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
    }

    private void setInAlbaransTableData() throws IOException, JSONException {
        TableColumn<Albara, String> idCol = new TableColumn<>("Codi");
        idCol.setCellValueFactory(new PropertyValueFactory<>("objectId"));

        TableColumn<AlbaraDataModel, String> providerCol = new TableColumn<>("Proveïdor");
        providerCol.setCellValueFactory(new PropertyValueFactory<>("provider"));
        providerCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<AlbaraDataModel, String> clientCol = new TableColumn<>("Client");
        clientCol.setCellValueFactory(new PropertyValueFactory<>("client"));
        clientCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<AlbaraDataModel, String> typeCol = new TableColumn<>("Tipus");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<AlbaraDataModel, String> dateCol = new TableColumn<>("Data");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn());


        ObservableList<AlbaraDataModel> albaransData = toObservableArrayListOfAlbarans(DBController.getInstance().getDBAlbaransController().getInAlbarans());
        inAlbaransTable.setItems(albaransData);
        inAlbaransTable.getColumns().setAll(idCol, providerCol, clientCol, typeCol, dateCol);
    }

    private ObservableList<AlbaraDataModel> toObservableArrayListOfAlbarans(ArrayList<Albara> albarans) {
        ObservableList<AlbaraDataModel> data = FXCollections.observableArrayList();
        for (Albara albara : albarans) {
            AlbaraDataModel albaraDataModel = new AlbaraDataModel(albara.getObjectId(), albara.getProvider().getFullName(), albara.getClient().getFullName(), albara.getType().getCode(), albara.getDate().toString());
            data.add(albaraDataModel);
        }
        return data;
    }
}
