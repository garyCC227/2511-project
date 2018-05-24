package application;


import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;

public class SubMenuController {
	@FXML
	private AnchorPane menu;
	@FXML
	private Button continueGame;
	@FXML
	private Button backMain; 
	@FXML
	private Button nextGame;
	
	@FXML
	private Button easy;
	@FXML
	private Button normal;
	@FXML
	private Button hard;
	
	@FXML
	private Button music;
	@FXML
	private ToggleButton musicOn;
	@FXML
	private ToggleButton musicOff;
	
	@FXML 
	private MediaView mv;
	private MediaPlayer mp;
	private AnchorPane lastPage;
	private Background soundOnbackground;
	private Background soundOffbackground ;
	
	public SubMenuController(AnchorPane myGamePage, MediaPlayer mp) {
		lastPage = myGamePage;
		this.mp = mp;
	}
	
	public void initialize() {
		System.out.println("initialize");//getClass().getResourceAsStream(
		// set background
		Image pic = new Image("/application/menu.jpeg", 300, 450, false,true); 
		BackgroundImage background = new BackgroundImage(pic, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		menu.setBackground(new Background(background));
		
		// initial soundsOn background
		Image soundOnpic = new Image("/application/soundOn.jpg",40,40, false,true); 
		
		BackgroundImage soundOnbackImage = new BackgroundImage(soundOnpic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
		          BackgroundSize.DEFAULT);
		soundOnbackground = new Background(soundOnbackImage);
	
		// initial soundsOff background
		Image soundOffpic = new Image("/application/soundOff.jpg",40,40, false,true); 
		
		BackgroundImage soundOffbackImage = new BackgroundImage(soundOffpic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
		          BackgroundSize.DEFAULT);
		soundOffbackground = new Background(soundOffbackImage);
		//musicInital(mv);
		
		// set background	
		// if sounds off
		Status status = mp.getStatus();
			
		if ( status == Status.PAUSED
		            || status == Status.READY
		            || status == Status.STOPPED) {
			music.setBackground(soundOffbackground);
			music.setShape(new Circle(1.5));
			
		// if sounds on 
		}else {
			music.setBackground(soundOnbackground);
			music.setShape(new Circle(1.5));
		}
		
	}
	public void musicInital(MediaView mv) {
    	File file = new File("HuLuWa.mp3");
    	String path = file.toURI().toString();
    	
    	Media media = new Media(path);
    	mp = new MediaPlayer(media);
    	mp.setAutoPlay(true);
    	
    	mv.setMediaPlayer(mp);
    }
	public void continueGame() {
		System.out.println("continueGame clicked");
		// close this window
		((Stage)menu.getScene().getWindow()).close();
		
	}
	public void selectLevel() {
		// set visible
		System.out.println("start clicked");
		easy.setVisible(true);
		normal.setVisible(true);
		hard.setVisible(true);
	}
	
	public void backToMain() {
		System.out.println("return Main clicked");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MenuPage.fxml"));
			MenuController controller = new MenuController(mp);
			loader.setController(controller);
			Parent root = loader.load();
			Scene scene = new Scene(root,600,600);
			// close this window
			((Stage)menu.getScene().getWindow()).close();
			// open the menu
			Stage stage = (Stage) lastPage.getScene().getWindow();
			stage.setScene(scene);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void nextGame(ActionEvent event) {
		System.out.println("level selected -> genereate next game");
		Button currButton = (Button)event.getSource();
		String difficulty = currButton.getText();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Myscene.fxml"));
			MyController controller = new MyController(mp);
			controller.setDifficulty(difficulty);
			loader.setController(controller);
			Parent root = loader.load();
			Scene gameScene = new Scene(root,600,600);
			// close this window
			((Stage)menu.getScene().getWindow()).close();
			// open the menu
			Stage stage = (Stage) lastPage.getScene().getWindow();
			
			stage.setScene(gameScene);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void musicControl(ActionEvent event) {
		System.out.println("Adjust the background music");
		Background curr = music.getBackground();
		// current is on
		if(curr.equals(soundOnbackground)) {
			// turn off
			musicOff();
		// current is off
		}else {
			// turn on
			musicOn();
		}
	}
	
	
	private void musicOn() {
		System.out.println("enable music");
		// set background pic
		music.setBackground(soundOnbackground);
		music.setShape(new Circle(1.5));
		
		// get current status		
		Status status = mp.getStatus();
		
		if ( status == Status.PAUSED
	         || status == Status.READY
	         || status == Status.STOPPED)
	          {
	            mp.play();
	          }
	}
	private void musicOff() {
		System.out.println("disable music");
		// set background pic
		music.setBackground(soundOffbackground);
		music.setShape(new Circle(1.5));

		Status status = mp.getStatus();
		
		if ( status == Status.PAUSED
	             || status == Status.READY
	             || status == Status.STOPPED)
	    {
	            
	    } else {
	    	mp.pause();
	    }
	         
	}
	
}
