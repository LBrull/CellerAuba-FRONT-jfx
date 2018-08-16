package cellerAubarca.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class mainLayoutController {

    @FXML
    public BorderPane mainLayer;

    public void setVista(Node node) {
        mainLayer.getChildren().setAll(node);
    }
}
