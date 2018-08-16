package cellerAubarca.controllers;

import cellerAubarca.MainRunner;
import cellerAubarca.models.ServerResponse;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.prefs.Preferences;

public class LoginController {

    private static LoginController instance = null;

    private MainRunner mainRunner;

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
            //dispose();
            saveToken(res.getToken());
            loadStage();

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

    public void loadStage() {
        try {

//            Stage mainStage = (Stage) main.getScene().getWindow();
//            mainStage.close();


            Parent menuScene = FXMLLoader.load(getClass().getResource("/cellerAubarca/views/menu.fxml"));
            Stage stage = new Stage();
            stage.setScene(menuScene.getScene());
            stage.setTitle("Celler Aubarca - Menú");
            stage.getIcons().add(new Image("/icons/grapeLogo.png"));
            stage.initModality(Modality.WINDOW_MODAL);
            //mainRunner.setPrimaryStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToken(String token) {
        Preferences root = Preferences.userRoot();
        root.put("token", token);
    }

    public void setMainApp(MainRunner mainRunner) {
        this.mainRunner = mainRunner;
    }

}

