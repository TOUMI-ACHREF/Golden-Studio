package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

import Controllers.MovieController;
import entities.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MovieViewController implements Initializable{
	@FXML
	List<Movie> liste;
	@FXML
	private BorderPane borderPane;
	@FXML
    private GridPane moviesContainer;
	@FXML
	private HBox home1;

	@FXML
	private Label home2;

	@FXML
	private Pane home3;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		int column = 0;
		int row = 1;
		try {
		
			liste = MovieController.getAllMovies();
			
			for(int i=0 ;i<liste.size() ; i++) {
				
				FXMLLoader fxmlLoader = new FXMLLoader();
				
				fxmlLoader.setLocation(getClass().getResource("mediaMini.fxml"));
				VBox mediaBox = fxmlLoader.load();
				MediaMiniViewController mediaController = fxmlLoader.getController();
				mediaController.setData(liste.get(i));
	
				if (column == 6) {
					column = 0;
					++row;
				}
				moviesContainer.add(mediaBox, column++, row);
				GridPane.setMargin(mediaBox, new Insets (10));
			}
		}catch(IOException | ParseException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@FXML
	void switchToHome(MouseEvent event) throws IOException {
		App.setRoot("home");
	}
	@FXML
	void switchToSerie(MouseEvent event) throws IOException {
		App.setRoot("serie");
	}
	@FXML
	void switchToFavourite(MouseEvent event) throws IOException {
		App.setRoot("favourite");
	}
	
	@FXML
	void switchToAccount(MouseEvent event) throws IOException {
		App.setRoot("UserAccountView");
	}

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
}
