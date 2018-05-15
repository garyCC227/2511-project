package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DialogController {
	@FXML
	private AnchorPane dialogBoard;

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
			Stage stage = (Stage) dialogBoard.getScene().getWindow();
			stage.setScene(scene);
			// how to close the original stage ??
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
