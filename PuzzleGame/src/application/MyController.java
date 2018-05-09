package application;
import java.net.URL;
import javafx.event.*;


import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.*;

public class MyController {
	   @FXML
	   private Button restart;
	   @FXML
	   private Button lastStep;
	   @FXML
	   private Button setting;
	   @FXML
	   private Button hint;
	   @FXML
	   private Button solution;
	   @FXML
	   private GridPane board;
	   @FXML
	   private Rectangle vCar0;
	   @FXML
	   private Rectangle vTruck1;
	   @FXML
	   private Rectangle vTruck2;
	   @FXML
	   private Rectangle vTruck3;
	   @FXML
	   private Rectangle vCar4;
	   @FXML
	   private Rectangle vCar5;
	   @FXML
	   private Rectangle vTruck6;
	   @FXML
	   private Rectangle vRedCar;
	   
	   //board data
	   private Board boardData;
	   
	   public MyController(Board boardData) {
		   this.boardData = boardData;
	   }
	   
	   // when the mouse is pressed
	   public void vehiclePressed(MouseEvent event) {
		   
	       Rectangle currV = (Rectangle)event.getSource();	       
	       System.out.println(currV.getId() + " pressed");
	       ((Rectangle)event.getSource()).requestFocus();
	  
	   }
	  
	   // when the mouse is released
	   public void vehicleMove(KeyEvent event) {
		   /*
		    * grab x, y coordinate of current focus object
		    */
		   Rectangle currV = (Rectangle)event.getSource();
	       Integer r = GridPane.getRowIndex((Rectangle)event.getSource());
	       Integer c = GridPane.getColumnIndex((Rectangle)event.getSource());
	       
	       r = (int)r;
	       c = (int)c;
	      
	       //check invalid movement from Database --- which is boardData
	       String move = event.getCode().toString();
	       Vehicle vehicle = boardData.getVehicle(c, r);
	       if(!boardData.movementOp(vehicle, move)) {
	    	   System.out.println("Errno: Invalid move");
	    	   return;
	       }else {
	    	   boardData.print_board();
	       }
	       
		   if(event.getCode() == KeyCode.RIGHT) {
			   GridPane.setColumnIndex(currV, (int)c + 1);
		   }else if(event.getCode() == KeyCode.LEFT) {
			   GridPane.setColumnIndex(currV, (int)c - 1);
		   }else if(event.getCode() == KeyCode.UP) {
			   GridPane.setRowIndex(currV, (int)r - 1);
		   }else if(event.getCode() == KeyCode.DOWN) {
			   GridPane.setRowIndex(currV, (int)r + 1);
		   }
		   
		   Rectangle currh = (Rectangle)event.getSource();
	       Integer row = GridPane.getRowIndex((Rectangle)event.getSource());
	       Integer col = GridPane.getColumnIndex((Rectangle)event.getSource());
	       
	       
	       System.out.println( "After Moved: "+ currh.getId()+ ", "+ col + ", "+ row);
	   }
	   
	   public void restart(ActionEvent event) {
		   System.out.println("restart clicked!");
	   }
	   
	   public void backToLastStep(ActionEvent event) {
		   System.out.println("next Step clicked!");
	   }
	   public void getHint(ActionEvent event) {
		   System.out.println("get hint clicked!");
	   }
	   public void getSolution (ActionEvent event) {
		   System.out.println("get solution clicked!");
	   }
	   public void getSetting(ActionEvent event) {
		   System.out.println("get setting clicked!");
	   }
	   

}
