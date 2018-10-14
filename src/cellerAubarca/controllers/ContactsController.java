package cellerAubarca.controllers;

import cellerAubarca.models.Client;
import cellerAubarca.models.ContactDataModel;
import cellerAubarca.models.Provider;
import cellerAubarca.models.ServerResponse;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ContactsController implements Initializable {
    public TableView<ContactDataModel> providersTable;
    public TableView<ContactDataModel> clientsTable;
    public JFXTabPane tabPane;
    public AnchorPane rootContactsPane;
    public JFXButton addButton;

    public ContactsController () {
        rootContactsPane = new AnchorPane();
        tabPane = new JFXTabPane();
        providersTable = new TableView<>();
        clientsTable = new TableView<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        providersTable.setPlaceholder(new Label("CARREGANT ELS PROVEÏDORS ..."));
        clientsTable.setPlaceholder(new Label("CARREGANT ELS CLIENTS ..."));
        Platform.runLater(() -> {
            try {
                setClientsTableData();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });

        Platform.runLater(() -> {
            try {
                setProvidersTableData();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
    }

    private void deleteClient(ContactDataModel client) throws IOException {
        DBController.getInstance().getDBContactsController().deleteOneClient(client.getObjectId());
        clientsTable.getItems().remove(client);
    }

    private void deleteProvider(ContactDataModel provider) throws IOException {
        DBController.getInstance().getDBContactsController().deleteOneProvider(provider.getObjectId());
        providersTable.getItems().remove(provider);

    }

    private void setProvidersTableData() throws IOException, JSONException {
        TableColumn<ContactDataModel, String> idCol = new TableColumn<>("Codi");
        idCol.setCellValueFactory(new PropertyValueFactory<>("objectId"));

        TableColumn<ContactDataModel, String> nameCol = new TableColumn<>("Nom");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(data -> {
            System.out.println("Nuevo: " +  data.getNewValue());
            System.out.println("Antiguo: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setName(data.getNewValue());
            Provider newProvider = p.toProvider();
            try {
                DBController.getInstance().getDBContactsController().editProvider(newProvider);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> surnameCol = new TableColumn<>("Cognom");
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameCol.setOnEditCommit(data -> {
            System.out.println("Nuevo Nombre: " +  data.getNewValue());
            System.out.println("Antiguo Nombre: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setSurname(data.getNewValue());
            Provider newProvider = p.toProvider();
            try {
                DBController.getInstance().getDBContactsController().editProvider(newProvider);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> dniCol = new TableColumn<>("NIF");
        dniCol.setCellValueFactory(new PropertyValueFactory<>("dni_nif"));
        dniCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dniCol.setOnEditCommit(data -> {
            System.out.println("Nuevo Nombre: " +  data.getNewValue());
            System.out.println("Antiguo Nombre: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setDniNif(data.getNewValue());
            Provider newProvider = p.toProvider();
            try {
                DBController.getInstance().getDBContactsController().editProvider(newProvider);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> telephoneCol = new TableColumn<>("Telèfon");
        telephoneCol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        telephoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        telephoneCol.setOnEditCommit(data -> {
            System.out.println("Nuevo Nombre: " +  data.getNewValue());
            System.out.println("Antiguo Nombre: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setTelephone(data.getNewValue());
            Provider newProvider = p.toProvider();
            try {
                DBController.getInstance().getDBContactsController().editProvider(newProvider);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(data -> {
            System.out.println("Nuevo Nombre: " +  data.getNewValue());
            System.out.println("Antiguo Nombre: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setEmail(data.getNewValue());
            Provider newProvider = p.toProvider();
            try {
                DBController.getInstance().getDBContactsController().editProvider(newProvider);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> accountNumberCol = new TableColumn<>("Núm. Compte");
        accountNumberCol.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        accountNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
        accountNumberCol.setOnEditCommit(data -> {
            System.out.println("Nuevo Nombre: " +  data.getNewValue());
            System.out.println("Antiguo Nombre: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setAccountNumber(data.getNewValue());
            Provider newProvider = p.toProvider();
            try {
                DBController.getInstance().getDBContactsController().editProvider(newProvider);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> cpCol = new TableColumn<>("C.P.");
        cpCol.setCellValueFactory(new PropertyValueFactory<>("cp"));
        cpCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cpCol.setOnEditCommit(data -> {
            System.out.println("Nuevo Nombre: " +  data.getNewValue());
            System.out.println("Antiguo Nombre: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setCp(data.getNewValue());
            Provider newProvider = p.toProvider();
            try {
                DBController.getInstance().getDBContactsController().editProvider(newProvider);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> townCol = new TableColumn<>("Població");
        townCol.setCellValueFactory(new PropertyValueFactory<>("town"));
        townCol.setCellFactory(TextFieldTableCell.forTableColumn());
        townCol.setOnEditCommit(data -> {
            System.out.println("Nuevo Nombre: " +  data.getNewValue());
            System.out.println("Antiguo Nombre: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setTown(data.getNewValue());
            Provider newProvider = p.toProvider();
            try {
                DBController.getInstance().getDBContactsController().editProvider(newProvider);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> addressCol = new TableColumn<>("Adreça");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setOnEditCommit(data -> {
            System.out.println("Nuevo : " +  data.getNewValue());
            System.out.println("Antiguo : " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setAddress(data.getNewValue());
            Provider newProvider = p.toProvider();
            try {
                DBController.getInstance().getDBContactsController().editProvider(newProvider);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        ObservableList<ContactDataModel> providersData = toObservableArrayListOfProviders(DBController.getInstance().getDBContactsController().getProviders());
        providersTable.setItems(providersData);
        providersTable.getColumns().setAll(idCol, nameCol, surnameCol, dniCol, telephoneCol, accountNumberCol, emailCol, cpCol, townCol, addressCol);
    }

    private void setClientsTableData() throws IOException, JSONException {
        TableColumn<ContactDataModel, String> idCol = new TableColumn<>("Codi");
        idCol.setCellValueFactory(new PropertyValueFactory<>("objectId"));

        TableColumn<ContactDataModel, String> nameCol = new TableColumn<>("Nom");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(data -> {
            System.out.println("Nuevo: " +  data.getNewValue());
            System.out.println("Antiguo: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setName(data.getNewValue());
            Client newClient = p.toClient();
            try {
                DBController.getInstance().getDBContactsController().editClient(newClient);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> surnameCol = new TableColumn<>("Cognom");
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameCol.setOnEditCommit(data -> {
            System.out.println("Nuevo: " +  data.getNewValue());
            System.out.println("Antiguo: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setSurname(data.getNewValue());
            Client newClient = p.toClient();
            try {
                DBController.getInstance().getDBContactsController().editClient(newClient);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> dniCol = new TableColumn<>("DNI");
        dniCol.setCellValueFactory(new PropertyValueFactory<>("dni_nif"));
        dniCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dniCol.setOnEditCommit(data -> {
            System.out.println("Nuevo: " +  data.getNewValue());
            System.out.println("Antiguo: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setDniNif(data.getNewValue());
            Client newClient = p.toClient();
            try {
                DBController.getInstance().getDBContactsController().editClient(newClient);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> telephoneCol = new TableColumn<>("Telèfon");
        telephoneCol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        telephoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        telephoneCol.setOnEditCommit(data -> {
            System.out.println("Nuevo: " +  data.getNewValue());
            System.out.println("Antiguo: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setTelephone(data.getNewValue());
            Client newClient = p.toClient();
            try {
                DBController.getInstance().getDBContactsController().editClient(newClient);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(data -> {
            System.out.println("Nuevo: " +  data.getNewValue());
            System.out.println("Antiguo: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setEmail(data.getNewValue());
            Client newClient = p.toClient();
            try {
                DBController.getInstance().getDBContactsController().editClient(newClient);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> accountNumberCol = new TableColumn<>("Núm. Compte");
        accountNumberCol.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        accountNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
        accountNumberCol.setOnEditCommit(data -> {
            System.out.println("Nuevo: " +  data.getNewValue());
            System.out.println("Antiguo: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setAccountNumber(data.getNewValue());
            Client newClient = p.toClient();
            try {
                DBController.getInstance().getDBContactsController().editClient(newClient);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> cpCol = new TableColumn<>("C.P.");
        cpCol.setCellValueFactory(new PropertyValueFactory<>("cp"));
        cpCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cpCol.setOnEditCommit(data -> {
            System.out.println("Nuevo: " +  data.getNewValue());
            System.out.println("Antiguo: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setCp(data.getNewValue());
            Client newClient = p.toClient();
            try {
                DBController.getInstance().getDBContactsController().editClient(newClient);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> townCol = new TableColumn<>("Població");
        townCol.setCellValueFactory(new PropertyValueFactory<>("town"));
        townCol.setCellFactory(TextFieldTableCell.forTableColumn());
        townCol.setOnEditCommit(data -> {
            System.out.println("Nuevo: " +  data.getNewValue());
            System.out.println("Antiguo: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setTown(data.getNewValue());
            Client newClient = p.toClient();
            try {
                DBController.getInstance().getDBContactsController().editClient(newClient);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        TableColumn<ContactDataModel, String> addressCol = new TableColumn<>("Adreça");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setOnEditCommit(data -> {
            System.out.println("Nuevo: " +  data.getNewValue());
            System.out.println("Antiguo: " + data.getOldValue());
            ContactDataModel p = data.getRowValue();
            p.setAddress(data.getNewValue());
            Client newClient = p.toClient();
            try {
                DBController.getInstance().getDBContactsController().editClient(newClient);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        ObservableList<ContactDataModel> clientsData = toObservableArrayListOfClients(DBController.getInstance().getDBContactsController().getClients());
        clientsTable.setItems(clientsData);
        clientsTable.getColumns().setAll(idCol, nameCol, surnameCol, dniCol, telephoneCol, accountNumberCol, emailCol, cpCol, townCol, addressCol);

    }

    private ObservableList<ContactDataModel> toObservableArrayListOfClients(ArrayList<Client> clients) {
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

    public void delete() throws IOException {
        int tabIndex = tabPane.getSelectionModel().getSelectedIndex();
        if (tabIndex == 0 && providersTable.getSelectionModel().getSelectedItem()!= null) { //pestanya providers
            ContactDataModel provider = providersTable.getSelectionModel().getSelectedItem();
            deleteProvider(provider);
        }
        else { //pestanya clients
            if (clientsTable.getSelectionModel().getSelectedItem() != null) {
                ContactDataModel client = clientsTable.getSelectionModel().getSelectedItem();
                deleteClient(client);
            }
        }
    }

    public void newContact() throws IOException, JSONException {
        int tabIndex = tabPane.getSelectionModel().getSelectedIndex();
        if (tabIndex == 0) { //pestanya providers
            Provider newProvider = new Provider();
            ServerResponse serverResponse = DBController.getInstance().getDBContactsController().saveNewProvider(newProvider);
            JSONObject object = new JSONObject(serverResponse.getMessage());
            JSONObject provider = object.getJSONObject("provider");
            String newId = provider.getString("_id");
            ContactDataModel contactDataModel = new ContactDataModel(newId, "", "", "", "", "", "", "", "", "");
            providersTable.getItems().add(contactDataModel);
        }
        else { //pestanya clients
            Client newClient = new Client();
            ServerResponse serverResponse = DBController.getInstance().getDBContactsController().saveNewClient(newClient);
            JSONObject object = new JSONObject(serverResponse.getMessage());
            JSONObject provider = object.getJSONObject("client");
            String newId = provider.getString("_id");
            ContactDataModel contactDataModel = new ContactDataModel(newId, "", "", "", "", "", "", "", "", "");
            clientsTable.getItems().add(contactDataModel);
        }
    }
}
