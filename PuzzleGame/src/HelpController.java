

import java.util.*;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXMLLoader;

public class HelpController {
    @FXML
    private Button returnMenu;
    @FXML
    private AnchorPane board;
    private MediaPlayer mp;

    public HelpController(MediaPlayer mp) {
        this.mp = mp;
    }

    public void initialize() {
        Image pic = new Image("/background3.jpg", 600, 600, false, true);
        BackgroundImage background = new BackgroundImage(pic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        board.setBackground(new Background(background));

    }

    public void returnMenu() {
        System.out.println("return Menu clicked!");
        returnMenu.setEffect(new DropShadow());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuPage.fxml"));
            MenuController controller = new MenuController(mp);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 600);
            Stage stage = (Stage) board.getScene().getWindow();
            stage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
