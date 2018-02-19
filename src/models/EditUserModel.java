package models;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import views.AlertBox;

/**
 * <h1>EditUserModel</h1>
 * This model class is used to edit or delete the user details
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */

public class EditUserModel {

	private String usertype;
	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	private String fullname;

	private String username;

	private String password;

	private LocalDate dateofbirth;

	private String phone;

	private String email;

	private int employeeid;

	private String employeetype;

	private String role;

	private String project;

	private String manager;

	private double salary;


	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(LocalDate localDate) {
		this.dateofbirth = localDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public String getEmployeetype() {
		return employeetype;
	}

	public void setEmployeetype(String employeetype) {
		this.employeetype = employeetype;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getManager() {
		return manager;
	}
	
	public void setUserType(String usertype) {
		this.usertype = usertype;

	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	static Connector connection = new Connector();
	public Statement stmt = null;
	String sql = null;
	public void editUser(TextField fullname, TextField username, PasswordField password, DatePicker dateofbirth,
			TextField phone, TextField email,TextField employeeid, ChoiceBox employeetype, TextField role, 
			ChoiceBox project,TextField manager, TextField salary, ChoiceBox userType) throws Exception{

		try{
			
			
			stmt = connection.getConnection().createStatement();

			sql = "UPDATE r_sree_users SET UserName ='"+getUsername()+"', Password = '"+getPassword()+"',"
					+ "FullName = '"+getFullname()+"', DateOfBirth = '"+ getDateofbirth()+"',"
					+ "Phone = '"+getPhone()+"', EmailId = '"+getEmail()+"', "
					+ "EmployeeType = '"+getEmployeetype()+"', "
					+" UserType ='"+ getUsertype()+ "' "
					+ "WHERE UserId= '"+getEmployeeid()+"'";




			stmt.executeUpdate(sql);
		
			
			

	
			sql = "UPDATE r_sree_employee SET Manager= '"+getManager()+"', Project = '"+getProject()+"',"
					+ "Role = '"+getRole()+"' WHERE EmployeeId = "+ getEmployeeid()+";";

			stmt.executeUpdate(sql);
			

			stmt = connection.getConnection().createStatement();
			if(employeetype.getValue().equals("Permanent")){
				String sql2 = "SELECT COUNT(*) AS COUNT FROM r_sree_fulltimeemployee WHERE FullTimeEmployeeId = '"+ getEmployeeid()+"'";
				ResultSet rs2 = stmt.executeQuery(sql2);
				int present = 0;
				if(rs2.next()){
					present = rs2.getInt("COUNT");
				}
				
				stmt = connection.getConnection().createStatement();
				if(present == 0){
					sql = "INSERT INTO r_sree_fulltimeemployee (FullTimeEmployeeId, Manager, Project, Role,Salary) "
							+ "VALUES ('"+getEmployeeid()+"','"+getManager()+"','"
							+getProject()+"','"+getRole()+"','"+getSalary()+"')";
					stmt.executeUpdate(sql);
					
				}else{
					sql = "UPDATE r_sree_fulltimeemployee SET Manager = '"+getManager()+"', Project = "
							+ "'"+getProject()+"',  Role = "
							+ "'"+getRole()+"', Salary = '"+getSalary()+"'"
							+ " WHERE FullTimeEmployeeId = '"+ getEmployeeid() +"'";
					stmt.executeUpdate(sql);
					
				}
			}

			else{
				String sql2 = "SELECT COUNT(*) AS COUNT FROM r_sree_contractemployee WHERE ContractEmployeeId = '"+getEmployeeid()+"'";
				ResultSet rs2 = stmt.executeQuery(sql2);
				int present = 0;
				if(rs2.next()){
					present = rs2.getInt("COUNT");
				}
				
				stmt = connection.getConnection().createStatement();
				if(present == 0){
					sql = "INSERT INTO r_sree_contractemployee (ContractEmployeeId, Manager, Project, Role,Salary) "
							+ "VALUES ('"+getEmployeeid()+"','"+getManager()+"','"
							+getProject()+"','"+getRole()+"','"+getSalary()+"')";
					stmt.executeUpdate(sql);
					
				}else{
					sql = "UPDATE r_sree_contractemployee SET Manager = '"+getManager()+"', Project = "
							+ "'"+getProject()+"',  Role = "
							+ "'"+getRole()+"', Salary = '"+getSalary()+"'"
							+ " WHERE FullTimeEmployeeId = '"+ getEmployeeid() +"'";
					stmt.executeUpdate(sql);
				
				}

			}

			stmt.executeUpdate(sql);

			System.out.println("Inserted records into the table...");
			stmt.close();

		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method is used to create a table called r_sree_tab with coloumns primary ID, customer ID,
	 * customer Income, and customer PEP informations to be stored
	 * @throws Exception SQLException is given as to if the table is already created or if there is any 
	 * error while creating the table
	 */
	public  ResultSet getUser(TextField userid) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		String sql =  null;
		try{
			stmt = connection.getConnection().createStatement();


			String sql2 = "SELECT COUNT(*) AS COUNT  FROM r_sree_fulltimeemployee WHERE"
					+ " FullTimeEmployeeId= '" + userid.getText() + "'";


			ResultSet rs2 = stmt.executeQuery(sql2);
			int present = 0;
			if(rs2.next()){
				present = rs2.getInt("COUNT");
			}
			stmt.close();
			stmt = connection.getConnection().createStatement();
			if(present == 1){
				sql = "SELECT * FROM r_sree_users u,  r_sree_fulltimeemployee fe WHERE u. UserId = "
						+ " fe.FullTimeEmployeeId AND u.UserId = '"+userid.getText() + "'";
			}else{
				sql = "SELECT * FROM r_sree_users u,  r_sree_contractemployee ce WHERE u. UserId = "
						+ " ce.ContractEmployeeId AND u.UserId = '"+userid.getText() + "'";
			}

			rs  = stmt.executeQuery(sql);




		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;


	}
	
	public void deleteUser(String userid ){
		
		String sql =  null;
		System.out.println(userid);
		try{
			stmt = connection.getConnection().createStatement();
			sql = "DELETE FROM r_sree_payslip WHERE PayEmployeeId = '"+userid+"'";
			stmt.executeUpdate(sql);
			
			sql = "DELETE FROM r_sree_fulltimeemployee WHERE FullTimeEmployeeId = '"+userid+"'";
			stmt.executeUpdate(sql);
			
			sql = "DELETE FROM r_sree_contractemployee WHERE ContractEmployeeId = '"+userid+"'";
			stmt.executeUpdate(sql);
			
			sql = "DELETE FROM r_sree_employee WHERE EmployeeId = '"+userid+"'";
			stmt.executeUpdate(sql);
			
			sql = "DELETE FROM r_sree_users WHERE UserId = '"+userid+"'";
			stmt.executeUpdate(sql);
			
			
			stmt.close();
	}catch(SQLException e){
		e.printStackTrace();
	
	}

	}
}
