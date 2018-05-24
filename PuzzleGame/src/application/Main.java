package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.Parent;

public class Main extends Application {
    private MediaPlayer mp;

    @Override
    public void start(Stage primaryStage) {
        try {

            // set up UI view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MenuPage.fxml"));
            MenuController controller = new MenuController(mp);
            loader.setController(controller);
            Parent root = loader.load();

            Scene scene = new Scene(root, 600, 600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
