package cellerAubarca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainRunner extends Application {

    private static Stage primaryStage;
    private static BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        MainRunner.primaryStage = primaryStage;
        MainRunner.primaryStage.setTitle("Celler Aubarca");
        MainRunner.primaryStage.getIcons().add(new Image("/icons/grapeLogo.png"));
        MainRunner.primaryStage.setResizable(false);

        initRootLayout();
        showLogin();

//        Parent loginStage = FXMLLoader.load(getClass().getResource("/cellerAubarca/views/login.fxml"));
//        primaryStage.getIcons().add(new Image("/icons/grapeLogo.png"));
//        Scene loginScene = new Scene(loginStage, 1000, 460);
//        primaryStage.setScene(loginScene);
//        primaryStage.setResizable(false);
//        primaryStage.show();
    }

    private void showLogin() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainRunner.class.getResource("/cellerAubarca/views/login.fxml"));
            AnchorPane loginView = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(loginView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainRunner.class.getResource("/cellerAubarca/views/rootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout, 1281, 640);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}