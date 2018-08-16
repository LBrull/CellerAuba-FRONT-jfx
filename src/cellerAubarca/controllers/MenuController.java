package cellerAubarca.controllers;

import cellerAubarca.MainRunner;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MenuController {

    private static MenuController instance;
    private MainRunner mainRunner;

    @FXML
    private AnchorPane viewHolder;


    public static MenuController getInstance() {
        if (instance == null) {
            instance = new MenuController();
        }
        return instance;
    }

    public static void showMenu() {

    }
}
