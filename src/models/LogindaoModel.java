package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 * 
 * <h1>Login Model</h1>
 * This method is used to verify the login credentials
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */
public class LogindaoModel {
	static Connector connection = new Connector();
	public Statement stmt = null;
	
	/**
	 * This method is used to create a table called r_sree_tab with coloumns primary ID, customer ID,
	 * customer Income, and customer PEP informations to be stored
	 * @throws Exception SQLException is given as to if the table is already created or if there is any 
	 * error while creating the table
	 */
	public  ResultSet userCheck(TextField username, PasswordField password) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		int login = 1;
		try{
			stmt = connection.getConnection().createStatement();
			
	
			String sql = "SELECT *  FROM r_sree_users WHERE UserName='" + username.getText() + "' AND Password='"+ password.getText() + "'";
			
			rs = stmt.executeQuery(sql);
			
			//String name = rs.getString("userType");
			
			

		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;
	}
}
