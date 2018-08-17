package cellerAubarca.controllers;

import cellerAubarca.MainRunner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController {

//    private static MenuController instance;

    @FXML
    private AnchorPane viewHolder;
    public ImageView logoImageView;
    public Label sessioLabel;
    public Label tempAMLabel;
    public Label tempOLLabel;
    public Label tempRALabel;


//    public static MenuController getInstance() {
//        if (instance == null) {
//            instance = new MenuController();
//        }
//        return instance;
//    }

    public void setUsername(String username) {
        sessioLabel.setText(username);
    }

    public void show() throws IOException {
        MainRunner.changeScene("/cellerAubarca/views/menu.fxml", 1200, 800);
        MainRunner.getMainStage().setResizable(true);
        MainRunner.getMainStage().setMinHeight(650);
        MainRunner.getMainStage().setMinWidth(800);
//        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/cellerAubarca/views/menu.fxml"));
//        loader.setController(this);
//        sessioLabel.setText("admin");
    }

}
