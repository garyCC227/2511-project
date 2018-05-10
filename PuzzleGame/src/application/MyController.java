package application;
import java.net.URL;
import java.util.ArrayList;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
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
	   private Rectangle v0;
	   @FXML
	   private Rectangle v1;
	   @FXML
	   private Rectangle v2;
	   @FXML
	   private Rectangle v3;
	   @FXML
	   private Rectangle v4;
	   @FXML
	   private Rectangle v5;
	   @FXML
	   private Rectangle v6;
	   @FXML
	   private Rectangle v7;
	   
	   //board data
	   private Board newGame;
	   
	   //@Override URL location, ResourceBundle resources
	   public void initialize() {
		   ArrayList<Rectangle> rList = new ArrayList<>(8);
		   rList.add(v0);
		   rList.add(v1);
		   rList.add(v2);
		   rList.add(v3);
		   rList.add(v4);
		   rList.add(v5);
		   rList.add(v6);
		   rList.add(v7);
		   
		   newGame = new Board();
		   newGame.generateBoard();
		   int i = 0;
		   for(Vehicle v: newGame.getVehicleList()) {
			   int row = v.getAddress()[0][1]; // the y of head - row
			   int col = v.getAddress()[0][0]; // the x of head - col
			   int size = v.getSize();
			   int ori = v.getOrientation(); // 0 - horizontal 1 - vertical
			   // setting
			   Rectangle rec = (Rectangle) rList.get(i);
			   rec.setArcWidth(30);
			   rec.setArcWidth(30);
			   
			   // if it is horizontal
			   if(ori == 0) {
				   rec.setHeight(60);
				   rec.setWidth(60*size);
				   board.add(rec, col, row, size, 1); // node, col, row, colspan, rowspan
				   
				   
			   } else {
				   rec.setWidth(60);
				   rec.setHeight(60*size);
				   board.add(rec, col, row, 1, size);
				   
			   }
			   
			   // after all setting set it as visible
			   rec.setVisible(true);
			   i++;
		   }
		   
	   }
	   
	   /*public MyController(Board boardData) {
		   this.boardData = boardData;
	   }*/
	   
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
	       Vehicle vehicle = newGame.getVehicle(c, r);
	       if(!newGame.movementOp(vehicle, move)) {
	    	   System.out.println("Errno: Invalid move");
	    	   return;
	       }else {
	    	   newGame.print_board();
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
