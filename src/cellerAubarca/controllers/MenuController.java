package cellerAubarca.controllers;

import cellerAubarca.MainRunner;
import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class MenuController {

    private static MenuController instance;

    private MainRunner mainRunner;

    @FXML
    private AnchorPane viewHolder;
    public ImageView logoImageView;


    public static MenuController getInstance() {
        if (instance == null) {
            instance = new MenuController();
        }
        return instance;
    }

    public void showMenu() {
        logoImageView.setEffect(new DropShadow(20, Color.BLACK));
    }
}
