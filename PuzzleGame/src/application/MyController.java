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
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.io.File;;


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
	   private AnchorPane myGamePage;

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
	   private Button move;

	   private int step; // how many moves by user
	   @FXML
	   private Rectangle redCar;

	   //board data
	   private Board newGame;
	   private Board boardBackUp; // ( a back up for initial board state)
	   private ArrayList<Rectangle> rList = new ArrayList<>();;
	   //private ArrayList<Integer[]> initialData;
	   //private Integer[] initialRed;

	   //@Override URL location, ResourceBundle resources
	   public void initialize() {
		   Image pic = new Image("/application/background3.jpg",600,600, false,true); 
		   BackgroundImage background = new BackgroundImage(pic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
			          BackgroundSize.DEFAULT);
		   myGamePage.setBackground(new Background(background));
		
		   if(rList.isEmpty()) {
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
		   }
		   
		   BoardGenerator n = new BoardGenerator(14, 40);
		   newGame = n.generate();

		   // a back up for initial state of the game
		   boardBackUp = newGame.boardClone(newGame);
		   //initial user move to 0
		   this.step = 0;

		   newGame.print_board();

		   //set board in UI by using board data in backstage
		   setBoardUI(newGame);

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
			   step+=1;
		   }else if(event.getCode() == KeyCode.LEFT) {
			   GridPane.setColumnIndex(currV, (int)c - 1);
			   step+=1;
		   }else if(event.getCode() == KeyCode.UP) {
			   GridPane.setRowIndex(currV, (int)r - 1);
			   step+=1;
		   }else if(event.getCode() == KeyCode.DOWN) {
			   GridPane.setRowIndex(currV, (int)r + 1);
			   step+=1;
		   }

		   this.move.setText(String.valueOf(step));

	       Integer row = GridPane.getRowIndex((Rectangle)event.getSource());
	       Integer col = GridPane.getColumnIndex((Rectangle)event.getSource());

	       // if it reach the exit entry
	       // col != 5 since this is the index of head need to consider size
	       if(currV.equals(redCar) && row == 2 && col == 4) {
	    	   System.out.println("SUCCEDD");
	    	   try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/finish.fxml"));
					DialogController controller = new DialogController(myGamePage);
					loader.setController(controller);
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

	   /*
	    * restart the game
	    */
	   public void restart(ActionEvent event) {
		   /*
		    * how this method work:
		    * 1. clear the old UI elements for vehicles
		    * 2. reset UI board by using board data back up
		    * 3. reset user move
		    * 4. "newGame" (which is the current game data show in UI) is pointed to a clone of board back up. - so can restart as many as we want
		    */
		   System.out.println("restart clicked!");

		   //clear UI elements for vehicles
		   clearUI_Vehicles();

		   //rebuild the board in UI by BoardBackUp( a back up of initial game)
		   setBoardUI(boardBackUp);

		   /*
		    * - let current board data point to initial board state
		    * - reset user moves to 0
		    */
		   newGame = boardBackUp.boardClone(boardBackUp);
		   this.step = 0;
		   this.move.setText(String.valueOf(step));

	   }

	   /*
	    * clear UI elements for vehicles
	    */
	   public void clearUI_Vehicles() {

		   Rectangle rectangle = new Rectangle();
		   ArrayList<Node> recSet = new ArrayList<Node>(); // all vehicles in UI

		   //get all using UI elements for vehicles
		   for(Node node : board.getChildren()) {
			   if(node.getClass().isInstance(rectangle)) {
				   recSet.add(node);
			   }
		   }

		   //clear them in UI board
		   for(Node node : recSet) {
			   board.getChildren().remove(node);
			   board.clearConstraints(node);
		   }
	   }

	   /*
	    * build board in UI by using the data of board in backstage
	    */
	   public void setBoardUI(Board boardData) {
		   int i = 0;
		   for(Vehicle v: boardData.getVehicleList()) {
			   int row = v.getAddress()[0][1]; // the y of head - row
			   int col = v.getAddress()[0][0]; // the x of head - col
			   int size = v.getSize();
			   int ori = v.getOrientation(); // 0 - horizontal 1 - vertical
			   boolean isRed = v.getIsRedCar();
			   Rectangle rec = (Rectangle) rList.get(i);

			   // setting
			   if (isRed == true) {
				   rec = redCar;
				   i--; // to offset the auto inc since the redcar is not in the list
				   		// it should not affect the index
			   }

			   // if vehicle is horizontal
			   if(ori == 0) {
				   rec.setHeight(60);
				   rec.setWidth(60*size);
				   board.add(rec, col, row, size, 1); // node, col, row, colspan, rowspan

			   } else {
				// vehicle is vertical
				   rec.setWidth(60);
				   rec.setHeight(60*size);
				   board.add(rec, col, row, 1, size);

			   }

			   if(!rec.isVisible()) {
				   rec.setVisible(true);
			   }
			   i++;
		   }
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
