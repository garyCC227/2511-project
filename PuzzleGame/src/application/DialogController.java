package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class DialogController {
	@FXML
	private AnchorPane dialogBoard;
	
	private AnchorPane lastScene;
	public DialogController(AnchorPane aScene) {
		this.lastScene = aScene;
	}
	public void initialize() {
		System.out.println("initialize");//getClass().getResourceAsStream(
		Image pic = new Image("/application/finish.jpg",500,300, true,true); 
		BackgroundImage background = new BackgroundImage(pic, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		dialogBoard.setBackground(new Background(background));
	}

	public void nextGame(ActionEvent event) {
		System.out.println("NextGame clicked");
	}
	
	public void Restart(ActionEvent event) {
		System.out.println("restart clicked");
	}
	
	public void returnMain(ActionEvent event) {
		System.out.println("return Main clicked");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MenuPage.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,600,600);
			// close this window
			((Stage)dialogBoard.getScene().getWindow()).close();
			// open the menu
			Stage stage = (Stage) lastScene.getScene().getWindow();
			stage.setScene(scene);
			
			// how to close the original stage ??
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
