package application;

import java.net.URL;
import java.security.acl.Group;

import javafx.util.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.sun.scenario.effect.Bloom;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.animation.RotateTransition;
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
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//import sun.misc.PerformanceLogger;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;

import java.io.File;;

public class MyController {
    @FXML
    private Button lastStep;
    @FXML
    private Button setting;
    @FXML
    private Button solution;
    @FXML
    private Button returnMenu;
    @FXML
    private GridPane board;
    @FXML
    private AnchorPane myGamePage;
    @FXML
    private Button move;
    @FXML
    private Rectangle redCar;

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
    private ImageView goalSign;

    private int step; // how many moves by user
    private Board newGame; // board data
    private Board boardBackUp; // a back up for initial board state
    private ArrayList<Rectangle> rList = new ArrayList<>(); // an arraylist to store the UL elements of vehicles
    private ArrayList<Move> userMoves; // arraylist to store all the user moves
    private int userMovesIndex;
    private String difficulty; // difficulty chose by user
    private MediaPlayer mp;

    public MyController(MediaPlayer mp) {
        this.mp = mp;
    }

    // @Override URL location, ResourceBundle resources
    public void initialize() {
        Image pic = new Image("/application/background3.jpg", 600, 600, false, true);
        BackgroundImage background = new BackgroundImage(pic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        myGamePage.setBackground(new Background(background));
        // set up goal image
        Image goalPic = new Image("/application/goal.jpg", 65, 65, true, true);
        goalSign.setImage(goalPic);
        // tranisition animation
        RotateTransition rt = new RotateTransition(Duration.millis(3000), goalSign);
        rt.setByAngle(180);
        rt.setCycleCount(RotateTransition.INDEFINITE);
        rt.setAutoReverse(true);
    
        rt.play();
        // add all UI elements of vehicles to a list
        if (rList.isEmpty()) {
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

        // set up empty the collection of all user moves
        userMoves = new ArrayList<Move>();
        userMovesIndex = -1;

        // randomly generate difficulty factors
        int[] factors = getFactorOfdifficulty(this.difficulty);
        int Vamount = factors[0]; // how many vehicles
        int bfs_time = factors[1]; // bfs times
        int redCarMoves = factors[2];

        // generate the board
        BoardGenerator n = new BoardGenerator(Vamount, bfs_time, redCarMoves);
        newGame = n.generate();

        // a back up for initial state of the game
        boardBackUp = newGame.boardClone(newGame);
        // initial user move to 0
        this.step = 0;

        newGame.print_board();

        // set board in UI by using board data in backstage
        setBoardUI(newGame);

    }
    
    public void clashEffect(Rectangle rec) {
    }

    /*
     * - easy model: 9-11 vehicles, bfs time = 5000; - normal model: 12-14 vehicles,
     * bfs time = 10000; - hard model: 13 - 15 vehicles, vfs time = 20000;
     */
    public int[] getFactorOfdifficulty(String diffi) {
        int[] factors = new int[3];
        Random random = new Random();
        double n;

        System.out.println("diff is : " + diffi);
        while (true) {
            n = random.nextDouble();
            if (diffi.equals("EASY")) {
                System.out.println("hello");
                if (n < 1 / 3) {
                    factors[0] = 9;
                } else if (n < 2 / 3) {
                    factors[0] = 10;
                } else {
                    factors[0] = 11;
                }
                factors[1] = 5;
                factors[2] = 2;
                break;
            } else if (diffi.equals("NORMAL")) {
                if (n < 1 / 3) {
                    factors[0] = 12;
                } else if (n < 2 / 3) {
                    factors[0] = 13;
                } else {
                    factors[0] = 14;
                }
                factors[1] = 10;
                factors[2] = 3;
                break;
            } else {
                if (n < 1 / 3) {
                    factors[0] = 13;
                } else if (n < 2 / 3) {
                    factors[0] = 14;
                } else {
                    factors[0] = 15;
                }
                factors[1] = 15;
                factors[2] = 5;
                break;
            }
        }
        return factors;
    }

    // set difficulty
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    // when the mouse is pressed
    public void vehiclePressed(MouseEvent event) {

        Rectangle currV = (Rectangle) event.getSource();
        System.out.println(currV.getId() + " pressed");
        ((Rectangle) event.getSource()).requestFocus();

    }

    // when the mouse is released
    public void vehicleMove(KeyEvent event) {

        /*
         * grab x, y coordinate of current focus object
         */
        Rectangle currV = (Rectangle) event.getSource();
        Integer r = GridPane.getRowIndex((Rectangle) event.getSource());
        Integer c = GridPane.getColumnIndex((Rectangle) event.getSource());

        r = (int) r;
        c = (int) c;

        // check invalid movement from Database --- which is boardData
        String move = event.getCode().toString();
        int direction = -1;
        Vehicle vehicle = newGame.getVehicle(c, r);
        if (!newGame.movementOp(vehicle, move)) {
            System.out.println("Errno: Invalid move");
            return;
        } else {
            newGame.print_board();
        }

        if (event.getCode() == KeyCode.RIGHT ) {
            GridPane.setColumnIndex(currV, (int) c + 1);
            direction = 1;
            step += 1;
        } else if (event.getCode() == KeyCode.LEFT ) {
            GridPane.setColumnIndex(currV, (int) c - 1);
            direction = 3;
            step += 1;
        } else if (event.getCode() == KeyCode.UP ) {
            GridPane.setRowIndex(currV, (int) r - 1);
            direction = 0;
            step += 1;
        } else if (event.getCode() == KeyCode.DOWN ) {
            GridPane.setRowIndex(currV, (int) r + 1);
            direction = 2;
            step += 1;
        }

        // record user move
        if (direction != -1) {
            Move userMove = new Move(vehicle, direction);
            userMovesIndex++;
            // Removes all forward moves after current move
            for (int i = userMovesIndex; i < userMoves.size(); i++) {
                userMoves.remove(i);
            }
            // Add new forward move
            userMoves.add(userMovesIndex, userMove);
        }
        this.move.setText(String.valueOf(step));

        Integer row = GridPane.getRowIndex((Rectangle) event.getSource());
        Integer col = GridPane.getColumnIndex((Rectangle) event.getSource());

        // if it reach the exit entry
        // col != 5 since this is the index of head need to consider size
        if (currV.equals(redCar) && row == 2 && col == 4) {
            System.out.println("SUCCEDD");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/finish.fxml"));
                DialogController controller = new DialogController(myGamePage, mp);
                loader.setController(controller);
                Parent dialog = loader.load();
                Scene scene = new Scene(dialog, 500, 300);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /*
     * restart the game
     */
    public void restart(ActionEvent event) {
        /*
         * how this method work: 1. clear the old UI elements for vehicles 2. reset UI
         * board by using board data back up 3. reset user move 4. "newGame" (which is
         * the current game data show in UI) is pointed to a clone of board back up. -
         * so can restart as many as we want
         */
        System.out.println("restart clicked!");

        // clear UI elements for vehicles
        clearUI_Vehicles();

        // rebuild the board in UI by BoardBackUp( a back up of initial game)
        setBoardUI(boardBackUp);

        /*
         * - let current board data point to initial board state - reset user moves to 0
         */
        newGame = boardBackUp.boardClone(boardBackUp);
        userMoves = new ArrayList<Move>();
        userMovesIndex = -1;
        this.step = 0;
        this.move.setText(String.valueOf(step));

    }

    /*
     * clear UI elements for vehicles
     */
    public void clearUI_Vehicles() {

        Rectangle rectangle = new Rectangle();
        ArrayList<Node> recSet = new ArrayList<Node>(); // all vehicles in UI

        // get all using UI elements for vehicles
        for (Node node : board.getChildren()) {
            if (node.getClass().isInstance(rectangle)) {
                recSet.add(node);
            }
        }

        // clear them in UI board
        for (Node node : recSet) {
            board.getChildren().remove(node);
            board.clearConstraints(node);
        }
    }

    /*
     * build board in UI by using the data of board in backstage
     */
    public void setBoardUI(Board boardData) {
        int i = 0;
        for (Vehicle v : boardData.getVehicleList()) {
            int row = v.getAddress()[0][1]; // the y of head - row
            int col = v.getAddress()[0][0]; // the x of head - col
            int size = v.getSize();
            int ori = v.getOrientation(); // 0 - horizontal 1 - vertical
            boolean isRed = v.getIsRedCar();
            Rectangle rec = (Rectangle) rList.get(i);

            // setting
            if (isRed == true) {
                rec = redCar;
                i--; // to offset the auto increase since the redcar is not in the list
                     // it should not affect the index
            }

            // if vehicle is horizontal
            if (ori == 0) {
                rec.setHeight(60);
                rec.setWidth(60 * size);
                board.add(rec, col, row, size, 1); // node, col, row, colspan, rowspan
            } else {
                // vehicle is vertical
                rec.setWidth(60);
                rec.setHeight(60 * size);
                board.add(rec, col, row, 1, size);
            }

            if (!rec.isVisible()) {
                rec.setVisible(true);
            }

            i++;
        }
    }

    public void forward(ActionEvent event) {
        System.out.println("forward clicked!");

        // check if curr move is last move recorded
        // if there is no next move, return
        if (userMovesIndex + 1 > userMoves.size() - 1) {
            return;
        }

        // Get next move && increment index
        userMovesIndex++;
        Move nextMove = userMoves.get(userMovesIndex);

        // perform move
        Vehicle v = nextMove.getVehicle();
        String direction = nextMove.dirToString(); // 0 up, 1 right , 2 down, 3 left
        int row = v.getAddress()[0][1]; // the y of head - row
        int col = v.getAddress()[0][0]; // the x of head - col

        // update data in backstage
        if (!newGame.movementOp(v, direction)) {
            System.out.println("Errno: Invalid move");
            return;
        } else {
            newGame.print_board();
        }

        // update UI
        Node currV = null;
        for (Node node : rList) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                currV = node;
                break;
            }
            if (GridPane.getRowIndex(redCar) == row && GridPane.getColumnIndex(redCar) == col) {
                currV = redCar;
                break;
            }
        }

        if (currV != null) {
            if (direction == "RIGHT") {
                GridPane.setColumnIndex(currV, (int) col + 1);
                if (step >= 0) {
                    step += 1;
                }
            } else if (direction == "LEFT") {
                GridPane.setColumnIndex(currV, (int) col - 1);
                if (step >= 0) {
                    step += 1;
                }
            } else if (direction == "UP") {
                GridPane.setRowIndex(currV, (int) row - 1);
                if (step >= 0) {
                    step += 1;
                }
            } else if (direction == "DOWN") {
                GridPane.setRowIndex(currV, (int) row + 1);
                if (step >= 0) {
                    step += 1;
                }
            }
            move.setText(String.valueOf(step));
        } else {
            System.out.println("Can't find the vehicle in UI");
        }

    }

    public void backToLastStep(ActionEvent event) {
        System.out.println("next Step clicked!");

        // If there is no previous moves recorded, exit
        if (userMovesIndex < 0) {
            return;
        }

        // get previous move & decrement index
        Move prevMove = userMoves.get(userMovesIndex).getReverseMove();
        userMovesIndex--;

        // perform move
        Vehicle v = prevMove.getVehicle();
        String direction = prevMove.dirToString(); // 0 up, 1 right , 2 down, 3 left
        int row = v.getAddress()[0][1]; // the y of head - row
        int col = v.getAddress()[0][0]; // the x of head - col

        // update data in backstage
        if (!newGame.movementOp(v, direction)) {
            System.out.println("Errno: Invalid move");
            return;
        } else {
            newGame.print_board();
        }

        // update UI
        Node currV = null;
        for (Node node : rList) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                currV = node;
                break;
            }
            if (GridPane.getRowIndex(redCar) == row && GridPane.getColumnIndex(redCar) == col) {
                currV = redCar;
                break;
            }
        }

        if (currV != null) {
            if (direction == "RIGHT") {
                GridPane.setColumnIndex(currV, (int) col + 1);
                if (step > 0) {
                    step -= 1;
                }
            } else if (direction == "LEFT") {
                GridPane.setColumnIndex(currV, (int) col - 1);
                if (step > 0) {
                    step -= 1;
                }
            } else if (direction == "UP") {
                GridPane.setRowIndex(currV, (int) row - 1);
                if (step > 0) {
                    step -= 1;
                }
            } else if (direction == "DOWN") {
                GridPane.setRowIndex(currV, (int) row + 1);
                if (step > 0) {
                    step -= 1;
                }
            }
            move.setText(String.valueOf(step));
        } else {
            System.out.println("Can't find the vehicle in UI");
        }

    }

    public void getMenu() {
        System.out.println("getMenu clicked!");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/SubMenu.fxml"));
            SubMenuController controller = new SubMenuController(myGamePage, mp);
            loader.setController(controller);
            Parent dialog = loader.load();
            Scene scene = new Scene(dialog, 300, 450);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSolution(ActionEvent event) {
        System.out.println("get solution clicked!");
    }

}
