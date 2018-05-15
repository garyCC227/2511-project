package application;

import java.util.*;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.fxml.FXMLLoader;

public class MenuController {
	@FXML
	private Text start;
	@FXML
	private Text help;
	@FXML
	private Text exit;	
	@FXML
	private Text setting;
	@FXML
	private AnchorPane menu;
	
	public void initialize(MouseEvent event) {
		System.out.println("initialize");
	}
	
	public void start(MouseEvent event) {
		System.out.println("Start clicked");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MyScene.fxml"));
			MyController controller = new MyController();
			loader.setController(controller);
			Parent root = loader.load();
			Scene scene = new Scene(root,600,600);
			Stage stage = (Stage) menu.getScene().getWindow();
			stage.setScene(scene);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void help(MouseEvent event) {
		System.out.println("help clicked");
	}
	
	public void setting(MouseEvent event) {
		System.out.println("setting clicked");
	}
	
	public void exit(MouseEvent event) {
		System.out.println("exit clicked");
	}
}
