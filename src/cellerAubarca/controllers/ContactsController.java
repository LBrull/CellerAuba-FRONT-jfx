package cellerAubarca.controllers;

import cellerAubarca.models.Client;
import cellerAubarca.models.ContactDataModel;
import cellerAubarca.models.Provider;
import com.jfoenix.controls.JFXTabPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ContactsController implements Initializable {
    public TableView<ContactDataModel> providersTable;
    public TableView<ContactDataModel> clientsTable;
    public Tab providersTab;
    public Tab clientsTab;
    public JFXTabPane tabPane;
    public AnchorPane rootContactsPane;

    public ContactsController () {
        rootContactsPane = new AnchorPane();
        tabPane = new JFXTabPane();
        providersTable = new TableView<>();
        providersTable.setEditable(true);
        clientsTable = new TableView<>();
        clientsTable.setEditable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setClientsTableData();
            setProvidersTableData();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

    public void deleteClient(ContactDataModel client) throws IOException {
        DBController.getInstance().getDBContactsController().deleteOneClient(client.getObjectId());
        clientsTable.getItems().remove(client);
    }

    public void deleteProvider(ContactDataModel provider) throws IOException {
        DBController.getInstance().getDBContactsController().deleteOneProvider(provider.getObjectId());
        providersTable.getItems().remove(provider);

    }

    private void setProvidersTableData() throws IOException, JSONException {
        TableColumn<ContactDataModel, String> idCol = new TableColumn<>("Codi");
        idCol.setCellValueFactory(new PropertyValueFactory("objectId"));
        TableColumn<ContactDataModel, String> nameCol = new TableColumn<>("Nom");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<ContactDataModel, String> surnameCol = new TableColumn<>("Cognom");
        surnameCol.setCellValueFactory(new PropertyValueFactory("surname"));
        TableColumn<ContactDataModel, String> dniCol = new TableColumn<>("NIF");
        dniCol.setCellValueFactory(new PropertyValueFactory("dni_nif"));
        TableColumn<ContactDataModel, String> telephoneCol = new TableColumn<>("Telèfon");
        telephoneCol.setCellValueFactory(new PropertyValueFactory("telephone"));
        TableColumn<ContactDataModel, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        TableColumn<ContactDataModel, String> accountNumberCol = new TableColumn<>("Núm. Compte");
        accountNumberCol.setCellValueFactory(new PropertyValueFactory("accountNumber"));
        TableColumn<ContactDataModel, String> cpCol = new TableColumn<>("C.P.");
        cpCol.setCellValueFactory(new PropertyValueFactory("cp"));
        TableColumn<ContactDataModel, String> townCol = new TableColumn<>("Població");
        townCol.setCellValueFactory(new PropertyValueFactory("town"));
        TableColumn<ContactDataModel, String> addressCol = new TableColumn<>("Adreça");
        addressCol.setCellValueFactory(new PropertyValueFactory("address"));

        ObservableList<ContactDataModel> providersData = toObservableArrayListOfProviders(DBController.getInstance().getDBContactsController().getProviders());
        providersTable.setItems(providersData);
        providersTable.getColumns().setAll(idCol, nameCol, surnameCol, dniCol, telephoneCol, accountNumberCol, emailCol, cpCol, townCol, addressCol);
    }

    private void setClientsTableData() throws IOException, JSONException {
        TableColumn<ContactDataModel, String> idCol = new TableColumn<>("Codi");
        idCol.setCellValueFactory(new PropertyValueFactory("objectId"));
        TableColumn<ContactDataModel, String> nameCol = new TableColumn<>("Nom");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<ContactDataModel, String> surnameCol = new TableColumn<>("Cognom");
        surnameCol.setCellValueFactory(new PropertyValueFactory("surname"));
        TableColumn<ContactDataModel, String> dniCol = new TableColumn<>("DNI");
        dniCol.setCellValueFactory(new PropertyValueFactory("dni_nif"));
        TableColumn<ContactDataModel, String> telephoneCol = new TableColumn<>("Telèfon");
        telephoneCol.setCellValueFactory(new PropertyValueFactory("telephone"));
        TableColumn<ContactDataModel, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        TableColumn<ContactDataModel, String> accountNumberCol = new TableColumn<>("Núm. Compte");
        accountNumberCol.setCellValueFactory(new PropertyValueFactory("accountNumber"));
        TableColumn<ContactDataModel, String> cpCol = new TableColumn<>("C.P.");
        cpCol.setCellValueFactory(new PropertyValueFactory("cp"));
        TableColumn<ContactDataModel, String> townCol = new TableColumn<>("Població");
        townCol.setCellValueFactory(new PropertyValueFactory("town"));
        TableColumn<ContactDataModel, String> addressCol = new TableColumn<>("Adreça");
        addressCol.setCellValueFactory(new PropertyValueFactory("address"));

        ObservableList<ContactDataModel> clientsData = toObservableArrayListOfClients(DBController.getInstance().getDBContactsController().getClients());
        clientsTable.setItems(clientsData);
        clientsTable.getColumns().setAll(idCol, nameCol, surnameCol, dniCol, telephoneCol, accountNumberCol, emailCol, cpCol, townCol, addressCol);

    }

    private ObservableList toObservableArrayListOfClients(ArrayList<Client> clients) {
        ObservableList<ContactDataModel> data = FXCollections.observableArrayList();
        for (Client client : clients) {
            ContactDataModel contactDataModel = new ContactDataModel(client.getObjectId(), client.getName(), client.getSurname(), client.getDni_nif(), client.getTelephone(), client.getCp(), client.getTown(), client.getEmail(), client.getAddress(), client.getAccountNumber());
            data.add(contactDataModel);
        }
        return data;
    }

    private ObservableList<ContactDataModel> toObservableArrayListOfProviders(ArrayList<Provider> providers) {
        ObservableList<ContactDataModel> data = FXCollections.observableArrayList();
        for (Provider provider : providers) {
            ContactDataModel contactDataModel = new ContactDataModel(provider.getObjectId(), provider.getName(), provider.getSurname(), provider.getDni_nif(), provider.getTelephone(), provider.getCp(), provider.getTown(), provider.getEmail(), provider.getAddress(), provider.getAccountNumber());
            data.add(contactDataModel);
        }
        return data;
    }

    public void delete(ActionEvent actionEvent) throws IOException {
        int tabIndex = tabPane.getSelectionModel().getSelectedIndex();
        if (tabIndex == 0) { //pestanya providers
            ContactDataModel provider = providersTable.getSelectionModel().getSelectedItem();
            deleteProvider(provider);
        }
        else { //pestanya clients
            ContactDataModel client = clientsTable.getSelectionModel().getSelectedItem();
            System.out.println("ID: " + client.getObjectId());
            deleteClient(client);
        }
    }
}
