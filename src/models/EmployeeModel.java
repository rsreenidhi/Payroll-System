package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *<h1> Employee Model</h1>
 * This method is used to control the  employee 
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 */
public class EmployeeModel {
	static Connector connection = new Connector();
	public Statement stmt = null;
	
	public  String checkEmploymentType(int employeeId){
		
		String employeeType = null;
		try{
			stmt = connection.getConnection().createStatement();
			
	
			String sql = "SELECT count(*) AS COUNT FROM r_sree_fulltimeemployee WHERE FullTimeEmployeeId='" + employeeId+  "'";
			ResultSet rs2 = stmt.executeQuery(sql);
			int present = 0;
			if(rs2.next()){
				present = rs2.getInt("COUNT");
			}
			System.out.println("Present"+ present);
			if(present == 1){
				employeeType = "fulltimeemployee";
			}else{
				 employeeType = "contractemployee";
			}
			stmt.close();
			
			//String name = rs.getString("userType");
			
			

		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return employeeType;
		
	}
}
