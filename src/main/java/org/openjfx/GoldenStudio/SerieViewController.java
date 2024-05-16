package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Controllers.SerieController;
import entities.Media;
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

public class SerieViewController implements Initializable{
	@FXML
	List<Serie> liste;
	@FXML
    private BorderPane borderPane;
	@FXML
    private GridPane moviesContainer;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		int column = 0;
		int row = 1;
		
		try {
			liste = SerieController.getAllSeriesWithId();
			
			for(int i=0 ;i<liste.size() ; i++) {
				
				FXMLLoader fxmlLoader = new FXMLLoader();
				
				fxmlLoader.setLocation(getClass().getResource("SerieMiniView.fxml"));
				VBox serieBox = fxmlLoader.load();
				SerieMiniController serieController = fxmlLoader.getController();
				//problem dans cette methode a cause du cast en attente du traitement sur la BD
				serieController.setData(liste.get(i));
	
				if (column == 6) {
					column = 0;
					++row;
				}

				moviesContainer.add(serieBox, column++, row);
				GridPane.setMargin(serieBox, new Insets (10));
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@FXML
	void switchToHome(MouseEvent event) throws IOException {
		App.setRoot("home");
	}
	@FXML
	void switchToAccount(MouseEvent event) throws IOException {
		App.setRoot("UserAccountView");
	}
	@FXML
	void switchToFavourite(MouseEvent event) throws IOException {
		App.setRoot("favourite");
	}
	@FXML
	void switchToMovie(MouseEvent event) throws IOException {
		App.setRoot("movie");
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

}
