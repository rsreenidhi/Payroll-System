package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import views.LoginView;
/**
 * <h1>Contract Employee Controller</h1>
 * This method is used to control the contract employee details
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */
public class ContractEmployee extends Employee implements Initializable {
	
	@FXML
	private Text loginTime;
	@FXML
	private Text loginName;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yy- HH-mm-ss");
		//	LocalDateTime localDate = LocalDateTime.now();
		LocalDate localDate = LocalDate.now();
		LocalTime specificSecondTime = LocalTime.now();

		String dateTime = String.valueOf(localDate) + "   " +  String.valueOf(specificSecondTime);

		loginTime.setText(dateTime);	
		
		loginName.setText(Employee.getemployeename() + " ContractEmployee");
	}

	public void logout(){
		LoginView.window.close();
		LoginController startAgain = new LoginController();
		startAgain.loginAgain();
	}

}
