package application;

import java.util.*;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
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
	
	@FXML
	private ToggleButton musicOn;
	
	@FXML
	private ToggleButton musicOff;
	@FXML
	private ToggleGroup music;
	
	public void initialize(/*ActionEvent event*/) {
		System.out.println("initialize");//getClass().getResourceAsStream(
		Image pic = new Image("/application/background1.jpg",600,600, true,true); 
		BackgroundImage background = new BackgroundImage(pic, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		menu.setBackground(new Background(background));
		//music = new ToggleGroup();
		//musicOn.setToggleGroup(music);
		//musicOff.setToggleGroup(music);
		
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/HelpPage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,600,600);
            Stage stage = (Stage) menu.getScene().getWindow();
            stage.setScene(scene);
            
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	public void setting(ActionEvent event) {
		System.out.println("setting clicked");
	}
	
	public void musicOn(ActionEvent event) {
		System.out.println("enable music");
		// get current status

		// set as musicOn selescted and disable true
		musicOff.setEffect(null);
		musicOn.setEffect(new DropShadow());
		musicOn.setSelected(true);
		musicOff.setSelected(false);

	}
	public void musicOff(ActionEvent event) {
		System.out.println("disable music");

		// set musicOff as selescted and disable true
		musicOn.setSelected(false);
		musicOn.setEffect(null);
		musicOff.setSelected(true);
		musicOff.setEffect(new DropShadow());

	}
	
}
