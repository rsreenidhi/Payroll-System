package views;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import controllers.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * <h1>Full Time Employee View</h1>
 * This method is used to launch the fulltime employee page. FXML file is fulltimeemployee.fxml
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */
public class FullTimeEmployeeView {
	
	public FullTimeEmployeeView(){
		Parent root;
		try {
			System.out.println("Ill bantu");
			root = FXMLLoader.load(getClass().getResource("/fxml/fulltimeemployee.fxml"));
			
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