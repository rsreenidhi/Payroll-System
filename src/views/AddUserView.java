package views;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
/**
 * <h1>Add User</h1>
 * This method is used to launch the add user page. FXML file is adduser.fxml
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */
public class AddUserView {

	public AddUserView(){

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/adduser.fxml"));
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
