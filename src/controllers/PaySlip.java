package controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

import javafx.scene.control.TextField;

import models.PaySlipdaoModel;
import views.AdminView;
import views.AlertBox;

/**
 * <h1>PaySlip Controller</h1>
 * This method is used to control the payment calculations, save it to database.
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */
public class PaySlip extends User implements Initializable{
	

	@FXML
	private ListView<String> listView;
	
	@FXML
	private TextField un;
	@FXML
	private TextField Employeeid;
	@FXML
	private TextField BasicSalary;
	@FXML
	private DatePicker from;
	@FXML
	private DatePicker to;
	@FXML
	private TextField ta;
	@FXML
	private TextField da;
	@FXML
	private TextField tax;
	@FXML
	private TextField NetSalary;
	
	
	PaySlipdaoModel paySlip = new PaySlipdaoModel();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> list = FXCollections.observableArrayList();
		ResultSet usersList = null;
		try {
			 usersList =  paySlip.getUserList();
			while(usersList.next()){
				list.add(usersList.getString("UserName"));
			}
			listView.setItems(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				usersList.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		listView.setItems(list);
	}
	/**
	 * This method is used to Load the values to list view
	 * @throws SQLException
	 */
	public void testing() throws SQLException{
		
		String selection = listView.getSelectionModel().getSelectedItem();
		ResultSet userInfo = null;
		PaySlipdaoModel getUserInfo = new PaySlipdaoModel();
		try {
			
			 userInfo = getUserInfo.getUserDetails(selection);
			while(userInfo.next()){
				String username = userInfo.getString("UserName");
				int employeeid =  userInfo.getInt("UserId");
				double salary = userInfo.getDouble("Salary");
				un.setText(username);
				Employeeid.setText(String.valueOf(employeeid));
				BasicSalary.setText(String.valueOf(salary));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			userInfo.close();
		}
	}

	/**
	 * This method calculates the salary 
	 */
	public void calculatePay(){
			if(from.getValue() == null){
				AlertBox.display("Error", "From field cannot be Empty");
				
			}else if(to.getValue() == null){
				AlertBox.display("Error" , "To field cannot be Empty");
			}else if(tax.getText() == null){
				AlertBox.display("Error" , "Tax field cannot be Empty");
			}else if(ta.getText() == null){
					AlertBox.display("Error" , "Ta field cannot be Empty");
			}else if(da.getText() == null){
				AlertBox.display("Error" , "Da field cannot be Empty");
			}
			else{
				try {
					double finalSalary = paySlip.calculation(from, to, BasicSalary, ta, da, tax);
					NetSalary.setText(String.valueOf(finalSalary));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
	/**
	 * This method is used to save the salary details of the user to database
	 */
	public void savePay(){
			if(NetSalary.getText() == null){
				AlertBox.display("Error" , "Calculate the Salary first");
			}
			try{
				paySlip.savePayToDB(from, to,BasicSalary, ta, da, tax, NetSalary, Employeeid);
				AlertBox.display("Success" , "Payment Saved to Database");
			}catch (Exception e) {
				// TODO Auto-generated catch block
				AlertBox.display("Error" , "Payment Already Calculated for this Month");
			}
	}

	
	
	public void back(){
		new AdminView();
	}
	
	

	
}
