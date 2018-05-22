package application;
import java.util.*;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.fxml.FXMLLoader;

public class HelpController {
	@FXML
	private Button returnMenu; 
	@FXML
	private AnchorPane board;
	
	public void initialize() {
		
	}
	public void returnMenu() {
		System.out.println("return Menu clicked!");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MenuPage.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,600,600);
			Stage stage = (Stage) board.getScene().getWindow();
			stage.setScene(scene);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
