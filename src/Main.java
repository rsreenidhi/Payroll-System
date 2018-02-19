import controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *  * <h1> Main Controller method </h1>
 *  This is the method from where program is launched
 * @author Ramanuja Sreenidhi
 * @version 1.0
 * @since 2016-11-22
 *
 */
public class Main {
	
	public static void main(String[] args){
		
		LoginController controller = new LoginController();
		controller.login();
		
	}
	
	
}

