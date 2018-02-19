package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.EmployeeModel;
import views.ContractEmployeeView;
import views.FullTimeEmployeeView;

/**
 * <h1>Employee Controller</h1>
 * This method is used to set the type of employee view
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */
public class Employee extends User {
	
	@FXML
	private Text loginTime;
	@FXML
	private Text loginName;
	
private static String  employeename;
	
	private static int  employeeid;
	
	public void setemployeeid(int loginuserId){
		this.employeeid = loginuserId;
	}
	
	public static int getemployeeid(){
		return employeeid;
	}

	public   void setemployeename(String employee){
		this.employeename = employee;
	}
	
	public static String getemployeename(){
		return employeename;
	}
	/**
	 * This method checks for full time employee or part time employee and sets the view accordingly
	 */
	public void Employee1(){
		
		EmployeeModel employmentType = new EmployeeModel();
		String employeeTypeResult = employmentType.checkEmploymentType(this.employeeid);
		System.out.println(employeeTypeResult);
		if(employeeTypeResult.equals("fulltimeemployee")){
			
			new FullTimeEmployeeView();
			
		}else{
		
			new ContractEmployeeView();
		}
	}


}
