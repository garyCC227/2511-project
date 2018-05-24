package application;

import java.io.File;

import java.util.*;

import javafx.concurrent.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
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
	
	private MediaPlayer mp;
	@FXML 
	private MediaView mv;
	@FXML
	private ProgressBar progressbar;
	@FXML
	private Label label;
	
	@FXML
	private Button music1;
	@FXML
	private Button music2;
	
	private String musicName;
	
	Task copyworker;
	
	public MenuController(MediaPlayer mp) {
		this.mp = mp;
	}
	
	public void initialize(/*ActionEvent event*/) {
		System.out.println("initialize");//getClass().getResourceAsStream(
		Image pic = new Image("/application/background1.jpg",600,600, true,true); 
		BackgroundImage background = new BackgroundImage(pic, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		menu.setBackground(new Background(background));
		if(mp == null) {
			musicInital(mv);
		}
		
	}
	public void selectLevel(ActionEvent event) {
		// set visible
		System.out.println("start clicked");
		if(easy.isVisible() == false) {
			easy.setVisible(true);
			normal.setVisible(true);
			hard.setVisible(true);
		} else {
			easy.setVisible(false);
			normal.setVisible(false);
			hard.setVisible(false);
		}

	}
	
	public void start(ActionEvent event) {
		System.out.println("Level selected");
		/*
		 * grab difficulty chose by user and pass into board generation
		 */
		Button currButton = (Button)event.getSource();
		String difficulty = currButton.getText();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MyScene.fxml"));
			MyController controller = new MyController(mp);
			controller.setDifficulty(difficulty);
			loader.setController(controller);
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
            HelpController controller = new HelpController(mp);
			loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root,600,600);
            Stage stage = (Stage) menu.getScene().getWindow();
            stage.setScene(scene);
            
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
	public void doubleplayer(ActionEvent event) {

	}
	
	public Task createWorker() {
	        return new Task() {
	            @Override
	            protected Object call() throws Exception {
	                for (int i = 0; i < 10; i++) {
	                    Thread.sleep(2000);
	                    updateMessage("2000 milliseconds");
	                    updateProgress(i + 1, 10);
	                }
	                return true;
	            }
	        };
	   }
	 
	public void musicSelect(ActionEvent event) {
	    System.out.println("music selection");
	    String s = ((Button)event.getSource()).getText();
	    musicName = s ;
	    //File file = new File(musicName+ ".mp3");
	    //String path = file.toURI().toString();
	    mp.dispose();
	    	
	    musicInital(mv);	
	 }
	 public void musicInital(MediaView mv) {
	    	System.out.println(musicName + ".mp3");
	    	//File file = new File("HuLuWa.mp3");
	    	if(musicName == null) {
	    		musicName = "HuLuWa";
	    	}
	    	File file = new File(musicName+ ".mp3");
	    	String path = file.toURI().toString();
	    	
	    	Media media = new Media(path);
	    	mp = new MediaPlayer(media);
	    	mp.setAutoPlay(true);
	    	mp.setCycleCount(MediaPlayer.INDEFINITE);
	    	
	    	mv.setMediaPlayer(mp);
	    }
	    
		public void setting(ActionEvent event) {
			System.out.println("setting clicked");
			if (music1.isVisible() == false) {
				music1.setVisible(true);
				music2.setVisible(true);
			} else {
				music1.setVisible(false);
				music2.setVisible(false);
			}
		}
		
		public void musicOn(ActionEvent event) {
			System.out.println("enable music");
			// get current status
			musicOff.setEffect(null);
			musicOn.setEffect(new DropShadow());
			musicOn.setSelected(true);
			musicOff.setScaleShape(false);
			Status status = mp.getStatus();
			
			if ( status == Status.PAUSED
		         || status == Status.READY
		         || status == Status.STOPPED)
		          {
		            mp.play();
		          }
		}
		public void musicOff(ActionEvent event) {
			System.out.println("disable music");
			
			musicOff.setEffect(new DropShadow());
			musicOn.setEffect(null);
			musicOff.setSelected(true);
			musicOn.setScaleShape(false);
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
