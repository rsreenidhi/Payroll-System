package controllers;

import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;

import java.time.LocalDate;
import java.util.ResourceBundle;
import views.LoginView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.AddUserModel;
import models.EditUserModel;

import views.AddUserView;
import views.AdminView;
import views.AlertBox;
import views.ConfirmationBox;
import views.EditUserView;

import views.PaymentCalcView;
import views.ReportsView;

/**
 * <h1> Admin Controller </h1>
 * This method controlls the adding, editing, deleting of users, and also calculating the payroll
 * 
 * @author Ramanuja Sreenidhi
 * @version 2.0
 * @since 2016-11-22
 *
 */
public class Admin extends User implements Initializable{
	
	@FXML
	private ChoiceBox usertype;
	@FXML
	private TextField searchUserId;
	@FXML
	private Text loginName;
	@FXML
	private TextField fullname;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private DatePicker dateofbirth;
	@FXML
	private TextField phone;
	@FXML
	private TextField email;
	@FXML
	private TextField employeeid;
	@FXML
	private ChoiceBox employeetype;
	@FXML
	private TextField role = null;
	@FXML
	private ChoiceBox project;
	@FXML
	private TextField manager;
	@FXML
	private TextField salary;
	
	
	
	/**
	 * This method launches the Add user view
	 */
	public void addUser(){
		
		new AddUserView();
	}
	/**
	 * This method launches the User edit view
	 */
	
	public void editUser(){
		new EditUserView();
	}
	/**
	 * This method launches the PaySlip Calculator View
	 */
	public void payslipCalculator(){
		new PaymentCalcView();
	}
	
	/**
	 * This method is used to save the user details to the database
	 */
	public void addUserToDB(){
		AddUserModel addUserModel = new AddUserModel();
		if(fullname.getText() == null){
			AlertBox.display("Error", "Full name cannot be empty");
		}
		else if(role.getText() == null){
			AlertBox.display("Error", "Designation cannot be empty");
		}
		else if(manager.getText() == null){
			AlertBox.display("Error", "Assign Manager");
		}
		else if(username.getText() == null){
			AlertBox.display("Error", "Username cannot be empty");
		}
		else if(password.getText() == null){
			AlertBox.display("Error", "Password cannot be empty");
		}
		else if(dateofbirth.getValue() == null){
			AlertBox.display("Error", "Password cannot be empty");
		}
		else if(phone.getText() == null){
			AlertBox.display("Error", "Phone number cannot be empty");
		}
		else if(phone.getLength() != 10){
			AlertBox.display("Error", "Phone number can be 10 digits only");
		}
		else if(email.getText() == null){
			AlertBox.display("Error", "Email cannot be empty");
		}
		else if(!email.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			AlertBox.display("Error", "Invalid Email");
		}
		
	
		else if(!salary.getText().matches("[0-9]+([,.][0-9]{1,2})?")){
			AlertBox.display("Error", "Salary Error");
		}else{
			
			try{
				addUserModel.setFullname(fullname.getText());
				addUserModel.setSalary(Double.parseDouble(salary.getText()));
				addUserModel.setDateofbirth(dateofbirth.getValue());
				addUserModel.setEmail(email.getText());
				addUserModel.setUserType((String)usertype.getValue());
				addUserModel.setEmployeetype((String)employeetype.getValue());
				addUserModel.setManager(manager.getText());
				addUserModel.setPassword(password.getText());
				addUserModel.setPhone(phone.getText());
				addUserModel.setProject((String) project.getValue());
				addUserModel.setRole(role.getText());
				addUserModel.setUsername(username.getText());
				addUserModel.insertUser(fullname, username, password, dateofbirth, phone, email, employeetype,
						role, project, manager, salary, usertype);
				AlertBox.display("Success", "User details added Successfully");

			}catch(Exception e){
				// TODO Auto-generated catch block
				AlertBox.display("Error", "Cannot Add the details. Maybe Userdata already exists");
			}
		}
		

	}
	EditUserModel editUserModel = new EditUserModel();
	/**
	 * This method is used to save the edited user details to database
	 */
	public void editUserInDB(){
		EditUserModel editUserModel = new EditUserModel();
		if(fullname.getText() == null){
			AlertBox.display("Error", "Full name cannot be empty");
		}
		else if(role.getText() == null){
			AlertBox.display("Error", "Designation cannot be empty");
		}
		else if(manager.getText() == null){
			AlertBox.display("Error", "Assign Manager");
		}
		else if(username.getText() == null){
			AlertBox.display("Error", "Username cannot be empty");
		}
		else if(password.getText() == null){
			AlertBox.display("Error", "Password cannot be empty");
		}
		else if(dateofbirth.getValue() == null){
			AlertBox.display("Error", "Password cannot be empty");
		}
		else if(phone.getText() == null){
			AlertBox.display("Error", "Phone number cannot be empty");
		}
		else if(phone.getLength() != 10){
			AlertBox.display("Error", "Phone number can be 10 digits only");
		}
		else if(email.getText() == null){
			AlertBox.display("Error", "Email cannot be empty");
		}
		else if(!email.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			AlertBox.display("Error", "Invalid Email");
		}
		
	
		else if(!salary.getText().matches("[0-9]+([,.][0-9]{1,2})?")){
			AlertBox.display("Error", "Salary Error");
		}else{
			
		try{
		
			editUserModel.setFullname(fullname.getText());
			editUserModel.setSalary(Double.parseDouble(salary.getText()));
			editUserModel.setDateofbirth(dateofbirth.getValue());
			editUserModel.setEmail(email.getText());
			editUserModel.setUserType((String)usertype.getValue());
			
			editUserModel.setEmployeetype((String)employeetype.getValue());
			editUserModel.setManager(manager.getText());
			editUserModel.setPassword(password.getText());
			editUserModel.setPhone(phone.getText());
			editUserModel.setProject((String) project.getValue());
			editUserModel.setRole(role.getText());
			editUserModel.setUsername(username.getText());
			editUserModel.setEmployeeid(Integer.parseInt(employeeid.getText()));

			editUserModel.editUser(fullname, username, password, dateofbirth, phone, email,employeeid, employeetype,
					role, project, manager, salary, usertype);
			AlertBox.display("Success", "Data Updated Successfully");
		}catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		String adminuser = "Howdy! " + User.getusername();
		loginName.setText(adminuser);
		
	}
	
public void testing() throws SQLException{
		
		//String selection = listView1.getSelectionModel().getSelectedItem();
		System.out.println("hi");
}
	/**
	 * This will logout from the session
	 */
	public void logout(){
		LoginView.window.close();
		LoginController startAgain = new LoginController();
		startAgain.loginAgain();
	}
	
	/**
	 * This method is used to search the user List and display in listview
	 * @throws ParseException
	 */
	public void search() throws ParseException{
		
		
		ResultSet rs = null;
		try {
		
			 rs = editUserModel.getUser(searchUserId);
			while(rs.next()){
				
				 username.setText(rs.getString("UserName")); 
				 fullname.setText(rs.getString("FullName"));
				 password.setText(rs.getString("Password"));
				 usertype.getSelectionModel().select(rs.getString("UserType"));
				 dateofbirth.setValue(LocalDate.parse(rs.getString("DateOfBirth")));
				 phone.setText(rs.getString("Phone"));
				 email.setText(rs.getString("EmailId"));
				 manager.setText(rs.getString("Manager"));
				 usertype.getSelectionModel().select(rs.getString("UserType"));
				 employeetype.getSelectionModel().select(rs.getString("EmployeeType"));
				 role.setText(rs.getString("Role"));
				 project.getSelectionModel().select(rs.getString("Project"));
				 salary.setText(String.valueOf(rs.getDouble("Salary")));
				 employeeid.setText(String.valueOf(rs.getInt("UserId")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AlertBox.display("Message", "User Not found. Try different Id");
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				AlertBox.display("Message", "User Not found. Try different Id");
			}
		}
		
	}

	public void fullnameValidate(){
		if(!fullname.getText().matches("^[a-zA-Z ]*$")){
			AlertBox.display("Error", "Text Only");
		}
	}
	
	public void phoneNumberValidate(){
		if(!phone.getText().matches("^[0-9]*$")){
			AlertBox.display("Error", "Numbers Only");
		}
		
	}
	
	
	
	public void roleValidate(){
		if(!role.getText().matches("^[a-zA-Z ]*$")){
			AlertBox.display("Error", "Text Only");
		}
	}
	
	public void managerValidate(){
		if(!manager.getText().matches("^[a-zA-Z ]*$")){
			AlertBox.display("Error", "Text Only");
		}
	}
	
	public void back(){
		new AdminView();
	}
	
	public void payslipReports(){
		
		new ReportsView();
	}

	public void deleteUser(){
		boolean result = ConfirmationBox.display("DELETE", "Are you sure to delete this user?");
		EditUserModel deleteUser = new EditUserModel();
		if(result){
			deleteUser.setEmployeeid(Integer.parseInt(employeeid.getText()));
			deleteUser.deleteUser(employeeid.getText());
			AlertBox.display("Message", "User Deleted Successfully");
			 username.setText(null); 
			 fullname.setText(null);
			 password.setText(null);
			 
			 dateofbirth.setValue(null);
			 phone.setText(null);
			 email.setText(null);
			 manager.setText(null);
			 //usertype.getSelectionModel().select(rs.getString("UserType"));
			 //employeetype.getSelectionModel().select(null);
			 role.setText(null);
			 //project.getSelectionModel().select(rs.getString("Project"));
			 salary.setText(null);
			 employeeid.setText(null);
		}
	}
}
