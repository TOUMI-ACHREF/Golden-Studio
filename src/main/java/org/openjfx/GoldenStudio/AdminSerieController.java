package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

import Controllers.SerieController;
import entities.Serie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminSerieController implements Initializable{
	@FXML
	List<Serie> liste; 
	@FXML
	private GridPane MediaContainer;

	@FXML
    private BorderPane borderPane;
	   

	//logout
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

		@FXML
		void switchToAccount(MouseEvent event) throws IOException {
			App.setRoot("UserAccountView");
		}
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
			
			int column = 0;
			int row = 1;
			try {
			
				liste = SerieController.getAllSeries();
				
				for(int i=0 ;i<liste.size() ; i++) {
					
					FXMLLoader fxmlLoader = new FXMLLoader();
					
					fxmlLoader.setLocation(getClass().getResource("SerieMiniView.fxml"));
					VBox mediaBox = fxmlLoader.load();
					SerieMiniController serieController = fxmlLoader.getController();
					serieController.setData(liste.get(i));
		
					if (column == 7) {
						column = 0;
						++row;
					}
					MediaContainer.add(mediaBox, column++, row);
					GridPane.setMargin(mediaBox, new Insets (10));
				}
			}catch(IOException  e) {
				e.printStackTrace();
			}
			
		}
	    
	    @FXML
		void switchToMovie(MouseEvent event) throws IOException {
			App.setRoot("adminHome");
		}

}
