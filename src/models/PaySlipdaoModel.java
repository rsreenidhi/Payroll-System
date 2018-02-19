package models;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 * <h1>Payslid Model</h1>
 * This method is used to generate payslips for the user
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */
public class PaySlipdaoModel {
	private  int eIDC;
	private double salary;
	private Date payslipFrom;
	private Date payslipTo;
	private Date generatedDate;
	private Double ta;
	private Double da;
	private Double tax;
	private Double NetSalary;
	
	
	public int geteIDC() {
		return eIDC;
	}


	public void seteIDC(int eIDC) {
		this.eIDC = eIDC;
	}


	public Date getGeneratedDate() {
		return generatedDate;
	}


	public void setGeneratedDate(Date generatedDate) {
		this.generatedDate = generatedDate;
	}


	public Double getTa() {
		return ta;
	}


	public void setTa(Double ta) {
		this.ta = ta;
	}


	public Double getDa() {
		return da;
	}


	public void setDa(Double da) {
		this.da = da;
	}


	public Double getTax() {
		return tax;
	}


	public void setTax(Double tax) {
		this.tax = tax;
	}


	public Double getNetSalary() {
		return NetSalary;
	}


	public void setNetSalary(Double netSalary) {
		this.NetSalary = netSalary;
	}


	public Date getPayslipFrom() {
		return payslipFrom;
	}


	public void setPayslipFrom(Date payslipFrom) {
		this.payslipFrom = payslipFrom;
	}


	public Date getPayslipTo() {
		return payslipTo;
	}


	public void setPayslipTo(Date payslipTo) {
		this.payslipTo = payslipTo;
	}


	public int getEmployeeIdColoumn() {
		return eIDC;
	}


	public void setEmployeeIdColoumn(int employeeIdColoumn) {
		eIDC = employeeIdColoumn;
	}
	
	static Connector connection = new Connector();
	public Statement stmt = null;
	/**
	 * This method is used to create a table called r_sree_tab with coloumns primary ID, customer ID,
	 * customer Income, and customer PEP informations to be stored
	 * @throws Exception SQLException is given as to if the table is already created or if there is any 
	 * error while creating the table
	 */
	public  ResultSet getUserDetails(String username) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try{
			stmt = connection.getConnection().createStatement();
			String sql1 = "SELECT UserId FROM r_sree_users WHERE UserName='"+username+"'";
			stmt.executeQuery(sql1);
			ResultSet rs1 = stmt.getResultSet();
			int employee = 0;
			if (rs1.next()) {
				employee = rs1.getInt(1);
			}
			
			stmt.close();
			stmt = connection.getConnection().createStatement();
			String sql2 = "SELECT COUNT(*) AS total FROM r_sree_fulltimeemployee WHERE FullTimeEmployeeId='"+employee+"'";
			ResultSet rs2 = stmt.executeQuery(sql2);
			int present = 0;
			if(rs2.next()){
				present = rs2.getInt("total");
			}
			stmt.close();
			
			stmt = connection.getConnection().createStatement();
			if(present == 1){
				String sql = "SELECT * FROM r_sree_users u, r_sree_fulltimeemployee e WHERE u.UserId = e.FullTimeEmployeeId AND UserName= '"+
						username + "'";
				rs = stmt.executeQuery(sql);
			}else{
				String sql = "SELECT * FROM r_sree_users u, r_sree_contractemployee e WHERE u.UserId = e.ContractEmployeeId AND UserName= '"+
						username + "'";
				rs = stmt.executeQuery(sql);
			}
			
			/*String sql = "SELECT * FROM users WHERE UserName='" + username + "'"
					+ " UNION "+
					"SELECT * FROM employee WHERE EmployeeId='"+employee + "'";*/
			
			
			
			


		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;


	}
	
	
	/**
	 * This method is used to create a table called r_sree_tab with coloumns primary ID, customer ID,
	 * customer Income, and customer PEP informations to be stored
	 * @throws Exception SQLException is given as to if the table is already created or if there is any 
	 * error while creating the table
	 */
	public  ResultSet getUserList() throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try{
			stmt = connection.getConnection().createStatement();


			String sql = "SELECT * FROM r_sree_users ";

			rs  = stmt.executeQuery(sql);
			
			


		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;


	}
	
	public double calculation(DatePicker from, DatePicker to, TextField basicsalary, TextField ta, TextField da,
			TextField tax){
		
		int  days  = (int) Math.abs(to.getValue().toEpochDay() - from.getValue().toEpochDay());
		
		double baseSalary = Double.parseDouble(basicsalary.getText());
		double netSalary = (baseSalary/30) * days;
		
		netSalary = netSalary + Double.parseDouble(ta.getText()) + Double.parseDouble(da.getText()) ;
		double tax1 =  netSalary * (Double.parseDouble(tax.getText())/100);
		netSalary = netSalary - tax1;
	
				return netSalary;
		
	}
	
	public void savePayToDB(DatePicker from, DatePicker to, TextField basicsalary,TextField ta, TextField da,
			TextField tax, TextField NetSalary,TextField Employeeid){
			
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate localDate = LocalDate.now();
		
		try {
			stmt = connection.getConnection().createStatement();
			String sql = "INSERT INTO r_sree_payslip (PaySlipFrom, PaySlipTo, GeneratedDate, TravelAllowance, "
					+ "DearnessAllowance, Taxes, BasicSalary, NetSalary, PayEmployeeId) VALUES"
					+ "('"+from.getValue()+"', '"+to.getValue()+"', '"+localDate+"','"+
					Float.parseFloat(ta.getText())+"','"+
					Float.parseFloat(da.getText())+"','"+Float.parseFloat(tax.getText())+"','"+
					Double.parseDouble(basicsalary.getText())+"','"+
					Double.parseDouble(NetSalary.getText())+
					"','"+Integer.parseInt(Employeeid.getText())+"')";
			
				stmt.executeUpdate(sql);
				
				stmt.close();
			
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	}
	
	public ResultSet getPayFromDB(){
		return null;
		
	}
	
	public ResultSet getReport(String username){
		
		ResultSet rs = null;
		int eid = 0;
		try {
			stmt = connection.getConnection().createStatement();
			String sql = "SELECT UserId FROM r_sree_users WHERE UserName= '"+ username + "'";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				 eid = rs.getInt("UserId");
			}
			
			
			String sql1 = "SELECT * FROM r_sree_payslip WHERE PayEmployeeId = '"+eid+"'";
			rs= stmt.executeQuery(sql1);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	public PaySlipdaoModel(){
		
	}
	

	
	
	
	public  PaySlipdaoModel(int EmployeeId, Double Salary, Date from, Date to, Date generatedDate,
			Double ta, Double da,Double tax, Double netSalary){
		
		this.eIDC = EmployeeId;
		this.salary = 	Salary;
		this.payslipFrom = from;
		this.payslipTo = to;
		this.generatedDate = generatedDate;
		this.ta = ta;
		this.da = da;
		this.tax = tax;
		this.NetSalary = netSalary;
		
		
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}



}
