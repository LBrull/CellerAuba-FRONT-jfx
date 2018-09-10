package cellerAubarca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainRunner extends Application {

    private static Stage primaryStage;
    private static BorderPane rootLayout;
    private static String session;
    private static Scene mainScene;
    private static String activeRaim = "";
    private static String activeOliva = "";
    private static String activeAmetlla = "";

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getMainStage() {
        return primaryStage;
    }

    public static String getSession() {
        return session;
    }

    public static void setSession(String username) {
        session = username;
    }

    public static Scene getScene() {
        return mainScene;
    }

    public static void setRaimActive(String s) {
        activeRaim = s;
    }

    public static void setOlivaActive(String s) {
        activeOliva = s;
    }

    public static void setAmetllaActive(String s) {
        activeAmetlla = s;
    }

    public static String getActiveRaim() {
        return activeRaim;
    }

    public static String getActiveOliva() {
        return activeOliva;
    }

    public static String getActiveAmetlla() {
        return activeAmetlla;
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        MainRunner.primaryStage = primaryStage;
        MainRunner.primaryStage.setTitle("Celler Aubarca");
        MainRunner.primaryStage.getIcons().add(new Image("/icons/grapeLogo.png"));
        MainRunner.primaryStage.setResizable(false);

        //load the first scene from a fxml file
        Parent root = FXMLLoader.load(MainRunner.class.getResource("/cellerAubarca/views/login.fxml"));
        //set scene to the stage
        mainScene = new Scene(root, 1281,640);
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    public static void changeScene(String sceneUrl, int width, int height) throws IOException {
        Parent root = FXMLLoader.load(MainRunner.class.getResource(sceneUrl));
        primaryStage.setScene(new Scene(root, width, height));
    }
}