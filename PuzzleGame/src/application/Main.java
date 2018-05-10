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
			 * set up UI viewer
			 */
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MyScene.fxml"));
			MyController controller = new MyController();
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
