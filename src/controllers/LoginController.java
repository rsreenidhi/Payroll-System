package controllers;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.awt.TextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import views.AlertBox;
import views.LoginView;
import models.LogindaoModel;
/**
 * <h1>Login Controller</h1>
 * This method checks the login details of user
 * @author Ramanuja Sreenidhi
 * @version 1.6
 * @since 2016-11-22
 *
 *
 */
public class LoginController implements Initializable{
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	public void login(){
		LoginView lv = new LoginView();
		lv.main();
		
	}
	public  void loginAgain(){
		LoginView newlv = new LoginView();
		newlv.startagain();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * This method validates the user credentials and displays error if any
	 * @throws SQLException
	 */
	public void showMessage() throws SQLException{
		ResultSet rs = null;
		String usertype = null;
		String userName = null;
		int userId = 0;
		LogindaoModel user = new LogindaoModel();
		try {
			 rs = user.userCheck(username, password);
			
			
		
				while(rs.next()){
					
						usertype =  rs.getString("UserType");
						userName = rs.getString("UserName");
						userId = rs.getInt("UserId");
				}
				
				if(usertype == null){
					AlertBox.display("Error", "Invalid Username or Password");
					
				}
				else if(usertype.equals("Admin")){
					
					User AdminController = new User();
					AdminController.setusername(userName);
					AdminController.admin();
				}
				
				else{
					
					User UserController = new User();
					UserController.setusername(userName);
					UserController.setuserid(userId);
					UserController.user();
				}
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
