package controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.PaySlipdaoModel;
import views.AdminView;
/**
 * <h1>Reports Controller</h1>
 * This class  is used to generate payment reports of selected users using Table View
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */
public class Reports extends User implements Initializable{
	ResultSet usersList = null;
	ResultSet usersPayslip = null;
	@FXML
	private ListView<String> listView1;
	
	 @FXML
	 TableView<PaySlipdaoModel> reportsTable;
	 @FXML
	 TableColumn<PaySlipdaoModel, Integer> EmployeeIdColoumn;
	 @FXML
	 TableColumn<PaySlipdaoModel, Double> basicSalaryColoumn;
	 @FXML
	 TableColumn<PaySlipdaoModel, Date> startDateColoumn;
	 @FXML
	 TableColumn<PaySlipdaoModel, Date> endDateColoumn;
	 @FXML
	 TableColumn<PaySlipdaoModel, Date> generatedDateColoumn;
	 @FXML
	 TableColumn<PaySlipdaoModel, Double> taColoumn;
	 @FXML
	 TableColumn<PaySlipdaoModel, Double> daColoumn;
	 @FXML
	 TableColumn<PaySlipdaoModel, Double> taxColoumn;
	 @FXML
	 TableColumn<PaySlipdaoModel, Double> netSalaryColoumn;
	 
	PaySlipdaoModel paySlip = new PaySlipdaoModel();
	
	public void getPayslip(){
		final ObservableList<PaySlipdaoModel> data = FXCollections.observableArrayList();
		ResultSet payslipreport = null;
		String selection = listView1.getSelectionModel().getSelectedItem();
		
		try{
			payslipreport = paySlip.getReport(selection);
			while(payslipreport.next()){
				System.out.println(payslipreport.getDouble("NetSalary"));
				
				paySlip = new PaySlipdaoModel(payslipreport.getInt("PayEmployeeId"), 
						payslipreport.getDouble("BasicSalary"), payslipreport.getDate("PaySlipFrom"),
						payslipreport.getDate("PaySlipTo"), payslipreport.getDate("GeneratedDate"),
						payslipreport.getDouble("TravelAllowance"), payslipreport.getDouble("DearnessAllowance"),
						payslipreport.getDouble("Taxes"),payslipreport.getDouble("NetSalary") );
				
				
				data.add(paySlip);
			}
			reportsTable.setItems(data);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				payslipreport.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	EmployeeIdColoumn.setCellValueFactory(new PropertyValueFactory<PaySlipdaoModel, Integer>("EmployeeIdColoumn"));
	basicSalaryColoumn.setCellValueFactory(new PropertyValueFactory<PaySlipdaoModel, Double>("salary"));
	startDateColoumn.setCellValueFactory(new PropertyValueFactory<PaySlipdaoModel, Date>("payslipFrom"));
	endDateColoumn.setCellValueFactory(new PropertyValueFactory<PaySlipdaoModel, Date>("payslipTo"));
	generatedDateColoumn.setCellValueFactory(new PropertyValueFactory<PaySlipdaoModel, Date>("generatedDate"));
	taColoumn.setCellValueFactory(new PropertyValueFactory<PaySlipdaoModel, Double>("ta"));
	daColoumn.setCellValueFactory(new PropertyValueFactory<PaySlipdaoModel, Double>("da"));
	taxColoumn.setCellValueFactory(new PropertyValueFactory<PaySlipdaoModel, Double>("tax"));
	netSalaryColoumn.setCellValueFactory(new PropertyValueFactory<PaySlipdaoModel, Double>("NetSalary"));
		final ObservableList<String> list = FXCollections.observableArrayList();
		
		
		try {
			 usersList =  paySlip.getUserList();
			while(usersList.next()){
				list.add(usersList.getString("UserName"));
			}
			listView1.setItems(list);
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
		//listView1.setItems(list); 
	}
	public void back(){
		new AdminView();
	}
	

}
