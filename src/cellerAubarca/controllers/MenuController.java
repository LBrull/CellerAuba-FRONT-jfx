package cellerAubarca.controllers;

import cellerAubarca.MainRunner;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    public JFXButton contactsButton;
    public ImageView contactsIcon;
    public JFXButton productsButton;
    public ImageView productsIcon;
    public JFXButton temporadesButton;
    public ImageView temporadesIcon;
    @FXML
    private AnchorPane viewHolder;
    public ImageView logoImageView;
    public Label sessioLabel;
    public Label tempAMLabel;
    public Label tempOLLabel;
    public Label tempRALabel;

    private ContactsController contactsController;
    private ProductsController productsController;


    public void show() throws IOException {
        MainRunner.changeScene("/cellerAubarca/views/menu.fxml", 1200, 800);
        MainRunner.getMainStage().setResizable(true);
        MainRunner.getMainStage().setMinHeight(650);
        MainRunner.getMainStage().setMinWidth(800);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sessioLabel.setText(MainRunner.getSession());
    }

    public void contactsView() throws IOException {
        Stage contactsStage = new Stage();
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/cellerAubarca/views/contacts.fxml"));
        Parent contactsParent = loader.load();
        contactsController = loader.getController();
        contactsStage.setTitle("Celler Aubarca - Contactes");
        contactsStage.getIcons().add(new Image("/icons/grapeLogo.png"));
        //set scene to the stage
        Scene contactsScene = new Scene(contactsParent, 1200,768);
        contactsStage.setScene(contactsScene);
        contactsStage.show();
    }

    public void productsView() throws IOException {
        Stage contactsStage = new Stage();
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/cellerAubarca/views/products.fxml"));
        Parent contactsParent = loader.load();
        productsController = loader.getController();
        contactsStage.setTitle("Celler Aubarca - Productes");
        contactsStage.getIcons().add(new Image("/icons/grapeLogo.png"));
        //set scene to the stage
        Scene contactsScene = new Scene(contactsParent, 1000,700);
        contactsStage.setScene(contactsScene);
        contactsStage.show();
    }

    public void temporadesView(ActionEvent actionEvent) {
    }
}
