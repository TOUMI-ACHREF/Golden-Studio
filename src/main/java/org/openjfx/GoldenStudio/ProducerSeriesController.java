package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProducerSeriesController implements Initializable {
	@FXML
    private BorderPane borderPane;
	@FXML
	void switchToAccount(MouseEvent event) throws IOException {
		App.setRoot("UserAccountView");
	}
	
	@FXML
	void switchToHome(MouseEvent event) throws IOException {
		App.setRoot("producerHome");
	}
	
	
	Stage stage;
	@FXML
	void logOut(MouseEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("Logout from Golden Studio");
		alert.setContentText("Exit from Golden Studio");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			stage = (Stage) borderPane.getScene().getWindow();
			System.out.println("CLOSING Golden Studio");
			stage.close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
