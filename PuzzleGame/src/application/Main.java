package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			 * at the time, we set up all vehicles manually from fxml, in the further stage, 
			 * we need to generate vehicles from code in back stage
			 */
			
			
			
			/*
			 *  set up data
			 */
			Board board = new Board();
			//set vehicles
			Vehicle c1 = new Vehicle(0, 2, 0, 0);
			Vehicle c2 = new Vehicle(0, 2, 1, 2);
			Vehicle c3 = new Vehicle(0, 2, 4, 4);
			Vehicle c4 = new Vehicle(1, 2, 0, 4);
			Vehicle t1 = new Vehicle(0, 3, 2, 5);
			Vehicle t2 = new Vehicle(1, 3, 0, 1);
			Vehicle t3 = new Vehicle(1, 3, 3, 1);
			Vehicle t4 = new Vehicle(1, 3, 5, 0);
			
			board.addVehicle(c1);
			board.addVehicle(c2);
			board.addVehicle(c3);
			board.addVehicle(c4);
			board.addVehicle(t1);
			board.addVehicle(t2);
			board.addVehicle(t3);
			board.addVehicle(t4);			
			
			/*
			 * set up UI viewer
			 */
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MyScene.fxml"));
			MyController controller = new MyController(board);
			loader.setController(controller);
			Parent root = loader.load();
			
			
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
