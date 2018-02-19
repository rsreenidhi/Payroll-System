package views;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * <h1>Login View</h1>
 * This method is used to launch the login page. FXML file is loginpage.fxml
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */
public class LoginView extends Application {
	 public static Stage window;
	
	public  void startagain(){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/loginpage.fxml"));
			Scene scene = new Scene(root, 800, 500);
			LoginView.window.setScene(scene);
			LoginView.window.setTitle("Payroll System");
			LoginView.window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void main(){
		launch();
		
	}

	@Override
	public  void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		window = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginpage.fxml"));
		Scene scene = new Scene(root, 800, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Payroll System");
		primaryStage.show();
	}

}
