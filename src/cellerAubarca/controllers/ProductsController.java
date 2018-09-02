package cellerAubarca.controllers;

import cellerAubarca.models.*;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.util.Callback;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductsController implements Initializable {
    public TableView<ProductDataModel> productsTable;
    public JFXButton addButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            setProductsTableData();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void setProductsTableData() throws IOException, JSONException {
        TableColumn<ProductDataModel, String> idCol = new TableColumn<>("Codi");
        idCol.setCellValueFactory(new PropertyValueFactory("objectId"));

        TableColumn<ProductDataModel, String> descriptionCol = new TableColumn<>("Descripció");
        descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCol.setOnEditCommit(data -> {
            System.out.println("Nuevo: " +  data.getNewValue());
            System.out.println("Antiguo: " + data.getOldValue());
            ProductDataModel p = data.getRowValue();
            p.setDescription(data.getNewValue());
            Product newProduct = p.toProduct();
            try {
                DBController.getInstance().getDBProductsController().editProduct(newProduct);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        ObservableList<Type> typeList = FXCollections.observableArrayList(Type.values());
        TableColumn<ProductDataModel, Type> typeCol = new TableColumn<>("Tipus");
        typeCol.setCellValueFactory(param -> {
            ProductDataModel product = param.getValue();
            // RA AM OL
            String typeCode = product.getType();
            Type type = Type.getByCode(typeCode);
            return new SimpleObjectProperty<>(type);
        });
        typeCol.setCellFactory(ComboBoxTableCell.forTableColumn(typeList));
        typeCol.setOnEditCommit((TableColumn.CellEditEvent<ProductDataModel, Type> event) -> {
            TablePosition<ProductDataModel, Type> pos = event.getTablePosition();
            Type newType = event.getNewValue();
            int row = pos.getRow();
            ProductDataModel product = event.getTableView().getItems().get(row);
            product.setType(newType.getCode());
            Product newProduct = product.toProduct();
            try {
                DBController.getInstance().getDBProductsController().editProduct(newProduct);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });
        typeCol.setMinWidth(100);

        TableColumn<ProductDataModel, String> priceCol = new TableColumn<>("Preu");
        priceCol.setCellValueFactory(new PropertyValueFactory("price"));
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        priceCol.setOnEditCommit(data -> {
            System.out.println("Nuevo: " +  data.getNewValue());
            System.out.println("Antiguo: " + data.getOldValue());

            if (!data.getNewValue().contains(",")) {

                ProductDataModel p = data.getRowValue();
                p.setPrice(data.getNewValue());
                Product newProduct = p.toProduct();
                try {
                    DBController.getInstance().getDBProductsController().editProduct(newProduct);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("No pot haver-hi comes!");
                alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea("Cambieu la coma per un punt per escriure decimals")));
                alert.showAndWait();
            }
        });

        ObservableList<ProductDataModel> providersData = toObservableArrayListOfProducts(DBController.getInstance().getDBProductsController().getProducts());
        productsTable.setItems(providersData);
        productsTable.getColumns().setAll(idCol, descriptionCol, typeCol, priceCol);
    }

    private ObservableList toObservableArrayListOfProducts(ArrayList<Product> products) {
        ObservableList<ProductDataModel> data = FXCollections.observableArrayList();
        for (Product product : products) {
            ProductDataModel productDataModel = new ProductDataModel(product.getObjectId(), product.getDescription(), product.getType(), product.getPrice());
            data.add(productDataModel);
        }
        return data;
    }

    public void newProduct(ActionEvent actionEvent) throws IOException, JSONException {
        Product newProduct = new Product();
        ServerResponse serverResponse = DBController.getInstance().getDBProductsController().saveNewProduct(newProduct);
        JSONObject object = new JSONObject(serverResponse.getMessage());
        JSONObject product = object.getJSONObject("product");
        String newId = product.getString("_id");
        ProductDataModel productDataModel = new ProductDataModel(newId, "", "", "");
        productsTable.getItems().add(productDataModel);
    }

    public void delete(ActionEvent actionEvent) throws IOException {
        ProductDataModel product = productsTable.getSelectionModel().getSelectedItem();
        deleteProduct(product);
    }

    private void deleteProduct(ProductDataModel product) throws IOException {
        DBController.getInstance().getDBProductsController().deleteOneProduct(product.getObjectId());
        productsTable.getItems().remove(product);
    }
}
