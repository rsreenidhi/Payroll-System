package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <h1> Connecting to the database </h1>
 * This class is used to connect to the database 510labs in the papademas server
 * @author Ramanuja Sreenidhi
 * @version 1.1
 * @since 2016-11-01
 */
public class Connector {
	
	/**
	 * This method is used to connect to the server
	 * @return Connection Connection status is returned as to connection success or failure
	 */
	public Connection getConnection() {
		Connection con = null;
		
		try{
			String host = "jdbc:mysql://www.papademas.net/fp";
			String userName = "dbfp";
			String password = "510";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(host, userName, password);
		}catch(SQLException err){
			System.out.println(err.getMessage());
		}catch(ClassNotFoundException err){
			err.printStackTrace();
		}
		
		return con;
	}
	
}

