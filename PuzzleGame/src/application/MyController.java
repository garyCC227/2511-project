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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
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
	   private Button returnMenu;
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
	   @FXML
	   private Rectangle v8;
	   @FXML
	   private Rectangle v9;
	   @FXML
	   private Rectangle v10;
	   @FXML
	   private Rectangle v11;
	   @FXML
	   private Rectangle v12;
	   @FXML
	   private Rectangle v13;
	   @FXML
	   private Rectangle v14;
	   @FXML
	   private Rectangle v15;
	   @FXML
	   private Rectangle v16;
	   
	   @FXML
	   private Rectangle redCar;
	   
	   //board data
	   private Board newGame;
	   private ArrayList<Rectangle> rList;
	   //private ArrayList<Integer[]> initialData;
	   //private Integer[] initialRed;
	   
	   //@Override URL location, ResourceBundle resources
	   public void initialize() {
		   //initialData = new ArrayList<Integer[]>();
		   rList = new ArrayList<>(8);
		   rList.add(v0);
		   rList.add(v1);
		   rList.add(v2);
		   rList.add(v3);
		   rList.add(v4);
		   rList.add(v5);
		   rList.add(v6);
		   rList.add(v7);
		   rList.add(v8);
		   rList.add(v9);
		   rList.add(v10);
		   rList.add(v11);
		   rList.add(v12);
		   rList.add(v13);
		   rList.add(v14);
		   rList.add(v15);
		   rList.add(v16);
		   
		   newGame = new Board();
		   newGame.generateBoard();
		   int i = 0;
		   for(Vehicle v: newGame.getVehicleList()) {
			   int row = v.getAddress()[0][1]; // the y of head - row
			   int col = v.getAddress()[0][0]; // the x of head - col
			   int size = v.getSize();
			   int ori = v.getOrientation(); // 0 - horizontal 1 - vertical
			   boolean isRed = v.getIsRedCar();
			   Rectangle rec = (Rectangle) rList.get(i);
			   Integer[] data = new Integer[4]; // 0-col 1-row, 2-colspan, 3-rowspan
			   
			   // setting
			   if (isRed == true) {
				   //initialRed = new Integer[4];
				   rec = redCar;
				   i--; // to offset the auto inc since the redcar is not in the list
				   		// it should not affect the index
			   }  
   
			   // if it is horizontal
			   if(ori == 0) {
				   rec.setHeight(60);
				   rec.setWidth(60*size);
				   board.add(rec, col, row, size, 1); // node, col, row, colspan, rowspan				   
				   data[0]=col;
				   data[1]=row;
				   data[2]=size;
				   data[3]=1;
				   
			   } else {
				   rec.setWidth(60);
				   rec.setHeight(60*size);
				   board.add(rec, col, row, 1, size);
				   data[0]=col;
				   data[1]=row;
				   data[2]=1;
				   data[3]=size;				   
			   }
			   
			  /* if (isRed == true) {
				   initialRed[0] = data[0];
				   initialRed[1] = data[1];
				   initialRed[2] = data[2];
				   initialRed[3] = data[3];
				   
			   } else {
				   initialData.add(data);
			   }*/
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
		   
	       Integer row = GridPane.getRowIndex((Rectangle)event.getSource());
	       Integer col = GridPane.getColumnIndex((Rectangle)event.getSource());
	       
	       // if it reach the exit entry
	       // col != 5 since this is the index of head need to consider size
	       if(currV.equals(redCar) && row == 2 && col == 4) {
	    	   System.out.println("SUCCEDD");
	    	   try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/finish.fxml"));
					Parent dialog = loader.load();
					Scene scene = new Scene(dialog,500,300);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.show();
					
				} catch(Exception e) {
					e.printStackTrace();
				}
	       }
	       
	       System.out.println( "After Moved: "+ currV.getId()+ ", "+ col + ", "+ row);
	   }
	   
	   public void restart(ActionEvent event) {
		   System.out.println("restart clicked!");
		   // restart the board looking (UI)
		   // initialData : 0-col 1-row, 2-colspan, 3-rowspan
		   /*
		   ArrayList<Vehicle> vList = newGame.getVehicleList();
		   for (int i = 0; i < rList.size(); i++) {
			   board.setColumnIndex(rList.get(i), initialData.get(i)[0]);
			   board.setRowIndex(rList.get(i), initialData.get(i)[1]);
			   board.setColumnSpan(rList.get(i), initialData.get(i)[2]);
			   board.setRowSpan(rList.get(i), initialData.get(i)[3]);
			   // set for the board way 1 - delete the old Board and generate a new one
			   // set for the board way 1 - set new address for the old Board 
			   
			   
		   }
		   
		   // set the redCar
		   board.setColumnIndex(redCar, initialRed[0]);
		   board.setRowIndex(redCar, initialRed[1]);
		   board.setColumnSpan(redCar, initialRed[2]);
		   board.setRowSpan(redCar, initialRed[3]);
		   */
	   }
		
	   public void returnMenu(ActionEvent event) {
		   System.out.println("return Menu clicked!");
		   try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MenuPage.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root,600,600);
				Stage stage = (Stage) board.getScene().getWindow();
				stage.setScene(scene);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		   
	   }
	   
	   public void forward(ActionEvent event) {
		   System.out.println("forward clicked!");
		   
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
