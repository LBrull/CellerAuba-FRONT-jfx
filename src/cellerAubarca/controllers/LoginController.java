package cellerAubarca.controllers;

import cellerAubarca.MainRunner;
import cellerAubarca.models.ServerResponse;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class LoginController implements Initializable {

    private static LoginController instance = null;

    private Stage primaryStage;

    @FXML
    private JFXTextField usernameTextField;
    @FXML
    private JFXPasswordField passwordTextField;


    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    public void login(ActionEvent actionEvent) throws Exception {
        String pass = passwordTextField.getText();
        String username = usernameTextField.getText();
        ServerResponse res = DBController.getInstance().login(username, pass);
        if (res.getStatus() == 200) {
            saveToken(res.getToken());
            MainRunner.changeScene("/cellerAubarca/views/menu.fxml", 1200, 600);
            MainRunner.getMainStage().setResizable(true);

        }
        else if (res.getStatus() == 404) {
            JOptionPane.showMessageDialog(null, "L'usuari no és vàlid", "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (res.getStatus() == 400) {
            JOptionPane.showMessageDialog(null, "La contrasenya no és correcta", "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (res.getStatus() == 500) {
            JOptionPane.showMessageDialog(null, "Error validant contrasenyes, torneu-ho a intentar", "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "Hi ha hagut un error", "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }

//    public void loadStage() {
//        try {
//
////            Stage mainStage = (Stage) main.getScene().getWindow();
////            mainStage.close();
//
//            Parent menuScene = FXMLLoader.load(getClass().getResource("/cellerAubarca/views/menu.fxml"));
//            Stage stage = new Stage();
//            stage.setScene(menuScene.getScene());
//            stage.setTitle("Celler Aubarca - Menú");
//            stage.getIcons().add(new Image("/icons/grapeLogo.png"));
//            stage.initModality(Modality.WINDOW_MODAL);
//            //mainRunner.setPrimaryStage(stage);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void saveToken(String token) {
        Preferences root = Preferences.userRoot();
        root.put("token", token);
    }

//    public void setMainApp(MainRunner mainRunner) {
//        this.mainRunner = mainRunner;
//    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String fileName = location.getFile().substring(location.getFile().lastIndexOf('/')+1);
        if (fileName.equals("menu.fxml")) {
            fadeTransition(usernameTextField);
            fadeTransition(passwordTextField);
        }
    }

    private void fadeTransition(Node e) {
        FadeTransition x = new FadeTransition(new Duration(2000), e);
        x.setFromValue(0);
        x.setToValue(100);
        x.setCycleCount(1);
        x.setInterpolator(Interpolator.EASE_IN);
        x.play();
    }
}

