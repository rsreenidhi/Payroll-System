package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * <h1>Add User Model </h1>
 * This class is used to add the new users to database users, employee
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */

public class AddUserModel {
	
	private String usertype;
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
	
	public void setUserType(String usertype) {
		this.employeetype = usertype;
		
	}

	public String getUserType() {
		return usertype;
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
	/**
	 * This method is used to add user details to the database
	 * @param fullname Name of the user
	 * @param username Login username
	 * @param password Login password of the user
	 * @param dateofbirth Date of birth of the user
	 * @param phone Phone number of the user
	 * @param email Email Id of the user
	 * @param employeetype User employee type
	 * @param role Role of the employee
	 * @param project Employee project
	 * @param manager Manager assigned to the employee
	 * @param salary basic salary of the employee
	 * @param userType Admin / User
	 * @throws Exception
	 */
	public void insertUser(TextField fullname, TextField username, PasswordField password, DatePicker dateofbirth,
			TextField phone, TextField email, ChoiceBox employeetype, TextField role, 
			ChoiceBox project,TextField manager, TextField salary, ChoiceBox userType) throws Exception{
		
		try{
			String sql = null;
			stmt = connection.getConnection().createStatement();
			sql = "INSERT INTO r_sree_users (UserName,Password, FullName , DateOfBirth,Phone, EmailId, EmployeeType, "
					+ "UserType) "+
					"VALUES ('"+username.getText()+"', '"+password.getText()+"','"+fullname.getText()+"', '"
							+ dateofbirth.getValue()+"', '"+phone.getText()+"', '"+email.getText()+"', '"
							+ String.valueOf(employeetype.getValue())+ "', '" +String.valueOf(userType.getValue())
							+ "')";
		
			stmt.executeUpdate(sql);
			
			stmt.close();
			
			stmt = connection.getConnection().createStatement();
			sql = "SELECT UserId FROM r_sree_users WHERE UserName='"+username.getText()+"'";
			stmt.executeQuery(sql);
			ResultSet rs = stmt.getResultSet();
			int generatedKey = 0;
			if (rs.next()) {
			    generatedKey = rs.getInt(1);
			}
			
			stmt.close();
			stmt = connection.getConnection().createStatement();
			sql = "INSERT INTO r_sree_employee (EmployeeId, Manager, Project, Role) " + 
					"VALUES ('"+generatedKey+"','"+manager.getText()+"','"+project.getValue()+"','"
					+role.getText()+"')"; 
			stmt.executeUpdate(sql);
			stmt.close();
			stmt = connection.getConnection().createStatement();
				if(employeetype.getValue().equals("Permanent")){
					sql = "INSERT INTO r_sree_fulltimeemployee (FullTimeEmployeeId, Manager, Project, Role,Salary) "
							+ "VALUES ('"+generatedKey+"','"+manager.getText()+"','"
							+project.getValue()+"','"+role.getText()+"','"+Double.parseDouble(salary.getText())+"')";
				}
				
				else{
					sql = "INSERT INTO r_sree_contractemployee (ContractEmployeeId, Manager, Project, Role,Salary) "
							+ "VALUES ('"+generatedKey+"','"+manager.getText()+"','"
							+project.getValue()+"','"+role.getText()+"','"+Double.parseDouble(salary.getText())+"')";
				}
				
				stmt.executeUpdate(sql);

			System.out.println("Inserted records into the table...");
			stmt.close();

		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
}
