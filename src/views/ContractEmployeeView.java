package views;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * <h1>Contract Employee View</h1>
 * This method is used to launch the contract employee page. FXML file is contractemployee.fxml
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */
public class ContractEmployeeView{
	
	public ContractEmployeeView(){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/contractemployee.fxml"));
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