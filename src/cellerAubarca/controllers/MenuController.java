package cellerAubarca.controllers;

import cellerAubarca.MainRunner;
import cellerAubarca.models.Temporada;
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
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class MenuController implements Initializable {

    @FXML
    public JFXButton contactsButton;
    public ImageView contactsIcon;
    public JFXButton productsButton;
    public ImageView productsIcon;
    public JFXButton temporadesButton;
    public JFXButton operationsButton;
    public Label ametllaTemp;
    public Label olivaTemp;
    public Label raimTemp;
    public ImageView olivaTempImage;
    public ImageView ametllaTempImage;
    public ImageView raimTempImage;
    @FXML
    private AnchorPane viewHolder;
    public ImageView logoImageView;
    public Label sessioLabel;

    private ContactsController contactsController;
    private ProductsController productsController;
    private TemporadesController temporadesController;
    private MenuController menuController;

    public MenuController() {
        try {
            this.menuController = this;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cellerAubarca/views/menu.fxml"));
            // Set this class as the controller
            loader.setController(this);
            // Load the scene
            MainRunner.getMainStage().setScene(new Scene(loader.load()));
            // Setup the window/stage
            MainRunner.getMainStage().setTitle("Celler Aubarca - Menú");
            MainRunner.getMainStage().getIcons().add(new Image("/icons/grapeLogo.png"));
            MainRunner.getMainStage().setResizable(true);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuController = this;
        //valor d'inici de les temporades
        ametllaTemp.setText("");
        raimTemp.setText("");
        olivaTemp.setText("");
        olivaTempImage.setImage(new Image(String.valueOf(getClass().getResource("/icons/crossIcon_25x25.png"))));
        raimTempImage.setImage(new Image(String.valueOf(getClass().getResource("/icons/crossIcon_25x25.png"))));
        ametllaTempImage.setImage(new Image(String.valueOf(getClass().getResource("/icons/crossIcon_25x25.png"))));
        sessioLabel.setText(MainRunner.getSession());
        contactsButton.setOnAction(event -> {
            try {
                contactsView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        productsButton.setOnAction(event ->  {
            try {
                productsView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        temporadesButton.setOnAction(event -> {
            try {
                temporadesView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        try {
            loadTemporades();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadTemporades() throws IOException, JSONException {
        ArrayList<Temporada> temps = DBController.getInstance().getDBTemporadesController().getTemporadesActives();
        if (temps.get(0) != null) {
            ametllaTemp.setText(temps.get(0).getDate());
            ametllaTempImage.setVisible(false);
        }
        if (temps.get(1) != null) {
            olivaTemp.setText(temps.get(1).getDate());
            olivaTempImage.setVisible(false);
        }
        if (temps.get(2) != null) {
            raimTemp.setText(temps.get(2).getDate());
            raimTempImage.setVisible(false);
        }
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

    public void temporadesView() throws IOException {
        System.out.println("menu controller: "+ this);
        temporadesController = new TemporadesController(this);
        // Show the new stage/window
        temporadesController.showStage();
    }

    public void movementsView(ActionEvent actionEvent) {
    }

    public void setAmetlla(String date) {
        System.out.println("menuController ara: "+ this);
        System.out.println("intentant fer set de: " + date);
        ametllaTemp.setText(date);
    }

    public void setRaim(String date) {
        raimTemp.setText(date);
    }

    public void setOliva(String date) {
        olivaTemp.setText(date);
    }

    public void deleteTemporadaAmetlla() {
        ametllaTemp.setText("");
        ametllaTempImage.setVisible(true);
    }

    public void deleteTemporadaRaim() {
        raimTemp.setText("");
        raimTempImage.setVisible(true);
    }

    public void deleteTemporadaOliva() {
        olivaTemp.setText("");
        olivaTempImage.setVisible(true);
    }
}
