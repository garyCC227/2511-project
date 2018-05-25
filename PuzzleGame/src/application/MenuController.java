package application;

import java.io.File;

import java.util.*;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.shape.Rectangle;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
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
	
	private MediaPlayer mp;
	@FXML 
	private MediaView mv;
	@FXML
	private Label label;
	
	@FXML
	private Button music1;
	@FXML
	private Button music2;
	@FXML
	private Button music3;
	
	@FXML
	private ImageView startPic;
	@FXML
	private ImageView musicPic;
	@FXML
	private ImageView helpPic;
	
	private String musicName;
	private Button lastbtn;
	
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
		// initialize start pic
		startPic.setImage(new Image("/application/start.png", 70, 90, true, true));
		musicPic.setImage(new Image("/application/music.png", 70, 90, true, true));
		helpPic.setImage(new Image("/application/help.png", 70, 90, true, true));
		
	}
	public void selectLevel(ActionEvent event) {
		// set visible
		System.out.println("start clicked");
		Button curr = (Button)event.getSource();
		if(lastbtn != null) {
        	lastbtn.setEffect(null);
        }
		curr.setEffect(new DropShadow());
		
		startPic.setVisible(true);
		
		if(easy.isVisible() == false) {
			easy.setVisible(true);
			normal.setVisible(true);
			hard.setVisible(true);
		} else {
			easy.setVisible(false);
			normal.setVisible(false);
			hard.setVisible(false);
		}
		
		lastbtn = curr;

	}
	
	public void start(ActionEvent event) {
		System.out.println("Level selected");
		/*
		 * grab difficulty chose by user and pass into board generation
		 */
		Button currButton = (Button)event.getSource();
		if(lastbtn != null) {
        	lastbtn.setEffect(null);
        }
		currButton.setEffect(new DropShadow());
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
        Button curr = (Button)event.getSource();
		if(lastbtn != null) {
        	lastbtn.setEffect(null);
        }
		curr.setEffect(new DropShadow());
		lastbtn = curr;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/HelpPage.fxml"));
            HelpController controller = new HelpController(mp);
			loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root,600,600);
            Stage stage = (Stage) menu.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("It is a game");
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
	 
	public void musicSelect(ActionEvent event) {
	    System.out.println("music selection");
	    Button curr = (Button)event.getSource();
		if(lastbtn != null) {
        	lastbtn.setEffect(null);
        }
		curr.setEffect(new DropShadow());
	    String s = ((Button)event.getSource()).getText();
	    musicName = s ;
	    mp.dispose();
	    	
	    musicInital(mv);
	    lastbtn = curr;
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
			Button curr = (Button)event.getSource();
			if(lastbtn != null) {
	        	lastbtn.setEffect(null);
	        }
			curr.setEffect(new DropShadow());
            
			if (music1.isVisible() == false) {
				music1.setVisible(true);
				music2.setVisible(true);
				music3.setVisible(true);
				
			} else {
				music1.setVisible(false);
				music2.setVisible(false);
				music3.setVisible(false);
				
			}
			lastbtn = curr;
		}
		
		public void musicOn(ActionEvent event) {
			System.out.println("enable music");
			// get current status
			musicOff.setEffect(null);
			musicOn.setEffect(new DropShadow());
			musicOn.setSelected(true);
			musicOff.setSelected(false);
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
			musicOn.setSelected(false);
			 Status status = mp.getStatus();
			
			if ( status == Status.PAUSED
		             || status == Status.READY
		             || status == Status.STOPPED)
		    {
		            
		    } else {
		    	mp.pause();
		    }      
		}
		
		public void mouseOn (MouseEvent event) {
			//System.out.println("mouse on button");
			Button curr = (Button)event.getSource();
			if (curr.equals(start)) {
				startPic.setVisible(true);
			} else if (curr.equals(setting)) {
				musicPic.setVisible(true);	
			} else {
				helpPic.setVisible(true);
			}
		}
		public void mouseLeave (MouseEvent event) {
			//System.out.println("mouse leave button");
			Button curr = (Button)event.getSource();
			if(easy.isVisible() == false) {
				startPic.setVisible(false);
			}
			if(music1.isVisible() == false) {
				musicPic.setVisible(false);	
			}
			if(curr.equals(help)) {
				helpPic.setVisible(false);
			}
			
		}
	
}
