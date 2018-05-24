package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class DialogController {
    @FXML
    private AnchorPane dialogBoard;
    @FXML
    private Button easy;
    @FXML
    private Button normal;
    @FXML
    private Button hard;
    private MediaPlayer mp;

    private AnchorPane lastScene;

    public DialogController(AnchorPane aScene, MediaPlayer mp) {
        this.lastScene = aScene;
        this.mp = mp;
    }

    public void initialize() {
        System.out.println("initialize");// getClass().getResourceAsStream(
        Image pic = new Image("/application/finish.jpg", 500, 300, true, true);
        BackgroundImage background = new BackgroundImage(pic, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        dialogBoard.setBackground(new Background(background));
    }

    /*
     * @desc: get next new game
     */
    public void nextGame(ActionEvent event) {
        System.out.println("NextGame clicked");
        Button currButton = (Button) event.getSource();
        String difficulty = currButton.getText();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Myscene.fxml"));
            MyController controller = new MyController(mp);
            // set difficulty chose by user
            controller.setDifficulty(difficulty);
            loader.setController(controller);
            Parent root = loader.load();
            Scene gameScene = new Scene(root, 600, 600);
            // close this window
            ((Stage) dialogBoard.getScene().getWindow()).close();
            // open the menu
            Stage stage = (Stage) lastScene.getScene().getWindow();
            stage.setScene(gameScene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectLevel(ActionEvent event) {
        // set visible
        System.out.println("start clicked");
        if (easy.isVisible() == false) {
            easy.setVisible(true);
            normal.setVisible(true);
            hard.setVisible(true);
        } else {
            easy.setVisible(false);
            normal.setVisible(false);
            hard.setVisible(false);
        }
    }

    public void Restart(ActionEvent event) {

    }

    public void returnMain(ActionEvent event) {
        System.out.println("return Main clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MenuPage.fxml"));
            MenuController controller = new MenuController(mp);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 600);
            // close this window
            ((Stage) dialogBoard.getScene().getWindow()).close();
            // open the menu
            Stage stage = (Stage) lastScene.getScene().getWindow();
            stage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
