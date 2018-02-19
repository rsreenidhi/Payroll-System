package views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
/**
 * <h1>Edit User</h1>
 * This method is used to launch the edit user page. FXML file is edituser.fxml
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */
public class EditUserView {
	
	public EditUserView(){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/edituser.fxml"));
			Scene scene = new Scene(root, 800, 500);
			LoginView.window.setScene(scene);
			LoginView.window.setTitle("Payroll System");
			LoginView.window.show();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
