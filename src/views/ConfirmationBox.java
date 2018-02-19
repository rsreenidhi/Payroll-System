package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * <h1>Confirmation Box</h1>
 * 
 * This method is used to launch the confirmations box. It also sends the confirmation message to the controller
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */
public class ConfirmationBox {
	static boolean answer = false;
	public static boolean display(String title, String message){
	
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label = new Label();
		label.setText(message);
		
		Button YesButton = new Button("Yes");
		YesButton.setOnAction(e -> {
			answer = true;
			window.close();
		});
		
		
		Button NoButton = new Button("No");
		NoButton.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		VBox layout = new VBox(25);
		
		
		layout.getChildren().addAll(label, YesButton, NoButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		return answer;
		
	}

}
