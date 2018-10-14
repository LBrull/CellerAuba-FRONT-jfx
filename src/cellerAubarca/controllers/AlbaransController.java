package cellerAubarca.controllers;

import cellerAubarca.models.*;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
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
            setInAlbaransTableData();
        });
    }

    private void setInAlbaransTableData() {
        TableColumn<Albara, String> idCol = new TableColumn<>("Codi");
        idCol.setCellValueFactory(new PropertyValueFactory<>("objectId"));

        TableColumn<AlbaraDataModel, String> provider = new TableColumn<>("Proveïdor");
        provider.setCellValueFactory(new PropertyValueFactory<>("provider"));
        provider.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<AlbaraDataModel, String> client = new TableColumn<>("Client");
        provider.setCellValueFactory(new PropertyValueFactory<>("client"));
        provider.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<AlbaraDataModel, String> type = new TableColumn<>("Tipus");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        type.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<AlbaraDataModel, String> date = new TableColumn<>("Data");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        date.setCellFactory(TextFieldTableCell.forTableColumn());


        ObservableList<AlbaraDataModel> albaransData = toObservableArrayListOfAlbarans(DBController.getInstance().getDBProductsController().getProducts());
        productsTable.setItems(providersData);
        productsTable.getColumns().setAll(idCol, descriptionCol, typeCol, priceCol);
    }
}
