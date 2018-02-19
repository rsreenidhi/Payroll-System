package controllers;


import views.AdminView;

/**
 * <h1>User Controller<h1>
 *  This method checks for login type. If it is admin, adminview is launched else userview is launched
 * @author Ramanuja Sreenidhi
 * @version 1.6
 * @since 2016-11-22
 *
 */
public class User {

	private static int  loginuserId;
	
	private static String  loginuser;
	
	public void setuserid(int loginuserId){
		this.loginuserId = loginuserId;
	}
	
	public static  int getuserid(){
		return loginuserId;
	}

	public void setusername(String loginuser){
		this.loginuser = loginuser;
	}
	
	public static String getusername(){
		return loginuser;
	}
	
	/**
	 * This method opens the Admin page
	 */
	public void admin(){
		new AdminView();

	}
	/**
	 * This method sets the controller to employee
	 */
	public void user(){
	
		Employee employee = new Employee();
		employee.setemployeename(this.getusername());
		employee.setemployeeid(this.getuserid());
		employee.Employee1();
	} 

		
	

}
