package cellerAubarca;

import cellerAubarca.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainRunner extends Application {

    private static Stage primaryStage;
    private static BorderPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getMainStage() {
        return primaryStage;
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
        primaryStage.setScene(new Scene(root, 1281,640));
        primaryStage.show();

        //initRootLayout();
        //showLogin();
    }

    public static void changeScene(String sceneUrl, int width, int height) throws IOException {
        Parent root = FXMLLoader.load(MainRunner.class.getResource(sceneUrl));
        primaryStage.setScene(new Scene(root, width, height));
    }

//    private void showLogin() {
//        try {
//            // Load person overview.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainRunner.class.getResource("/cellerAubarca/views/login.fxml"));
//            AnchorPane loginView = loader.load();
//            LoginController loginController = loader.getController();
//            loginController.setPrimaryStage(primaryStage);
//            // Set person overview into the center of root layout.
//            rootLayout.setCenter(loginView);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    private void initRootLayout() {
//        try {
//            // Load root layout from fxml file.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainRunner.class.getResource("/cellerAubarca/views/rootLayout.fxml"));
//            rootLayout = loader.load();
//
//            // Show the scene containing the root layout.
//            Scene scene = new Scene(rootLayout, 1281, 640);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public Stage getPrimaryStage() {
//        return primaryStage;
//    }
//
//    public void setPrimaryStage(Stage primaryStage) {
//        this.primaryStage = primaryStage;
//    }

}