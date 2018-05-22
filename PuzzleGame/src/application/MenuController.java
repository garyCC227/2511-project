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

public class MenuController {
	@FXML
	private Button start;
	@FXML
	private Button help;
	@FXML
	private Button setting;
	@FXML
	private AnchorPane menu;
	
	@FXML
	private Button easy;
	@FXML
	private Button normal;
	@FXML
	private Button hard;
	
	
	public void initialize(/*ActionEvent event*/) {
		System.out.println("initialize");//getClass().getResourceAsStream(
		Image pic = new Image("/application/background1.jpg",600,600, true,true); 
		BackgroundImage background = new BackgroundImage(pic, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		menu.setBackground(new Background(background));
		
	}
	public void selectLevel(ActionEvent event) {
		// set visible
		System.out.println("start clicked");
		easy.setVisible(true);
		normal.setVisible(true);
		hard.setVisible(true);

	}
	
	public void start(ActionEvent event) {
		System.out.println("Level selected");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MyScene.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,600,600);
			Stage stage = (Stage) menu.getScene().getWindow();
			stage.setScene(scene);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void help(ActionEvent event) {
		System.out.println("help clicked");
	}
	
	public void setting(ActionEvent event) {
		System.out.println("setting clicked");
	}

}
