package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * <h1>Alert Box</h1>
 * 
 * This method is used to display alert messages. 
 * @author Ramanuja Sreenidhi
 * @version 1.3
 * @since 2016-11-22
 *
 */
public class AlertBox {
	
	public static void display(String title, String message){
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label = new Label();
		label.setText(message);
		
		Button closeButton = new Button("OK");
		closeButton.setOnAction(e -> window.close());
		
		VBox layout = new VBox(25);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
	}

}
