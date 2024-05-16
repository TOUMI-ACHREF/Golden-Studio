package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entities.Media;
import entities.Movie;
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

public class FavouriteViewController implements Initializable{
	
	@FXML
	List<Media> liste;
   @FXML
    private BorderPane borderPane;	
	@FXML
    private GridPane moviesContainer;
	
	private List<Media> getList()  {
		List<Media> ls = new ArrayList<>();
		
		Media p1,p2,p3,p4,p5,p6,p7,p8;
		String imagePath1 = "imageSource/Harry_Potter_affiche.jpg";
		String imagePath2 = "imageSource/titanic_affiche.jpg";
		String imagePath3 = "imageSource/fury_affiche.jpg";
		String imagePath4 = "imageSource/dark_knight_affiche.jpg";
		String imagePath5 = "imageSource/intouchables.jpg";
		String imagePath6 = "imageSource/joker.jpg";
		String imagePath7 = "imageSource/spider_man.jpg";
		String imagePath8 = "imageSource/Tirailleurs.jpg";
		
		
		
		p1 = new Media();
		p1.setName("Harry Potter");
		p1.setPoster(imagePath1);
		p1.setGenre("Magic");
		
		p2 = new Media();
		p2.setName("Titanic");
		p2.setPoster(imagePath2);
		p2.setGenre("Romance, drama");
		
		p3 = new Media();
		p3.setName("Fury");
		p3.setPoster(imagePath3);
		p3.setGenre("War");
		
		p4 = new Media();
		p4.setName("dark knight affiche");
		p4.setPoster(imagePath4);
		p4.setGenre("Science Fiction, Action");
		
		p5 = new Media();
		p5.setName("intouchables");
		p5.setPoster(imagePath5);
		p5.setGenre("Action");
		
		p6 = new Media();
		p6.setName("joker");
		p6.setPoster(imagePath6);
		p6.setGenre("Action");
		
		p7 = new Media();
		p7.setName("spider man");
		p7.setPoster(imagePath7);
		p7.setGenre("Science Fiction, Action");
		
		p8 = new Media();
		p8.setName("Tirailleurs");
		p8.setPoster(imagePath8);
		p8.setGenre("Action");
		
		ls.add(p1);
		ls.add(p2);
		ls.add(p3);
		ls.add(p4);
		ls.add(p5);
		ls.add(p6);
		ls.add(p7);
		ls.add(p8);
		
		ls.add(p1);
		ls.add(p2);
		ls.add(p3);
		ls.add(p4);
		ls.add(p5);
		ls.add(p6);
		ls.add(p7);
		ls.add(p8);
		
		ls.add(p1);
		ls.add(p2);
		ls.add(p3);
		ls.add(p4);
		ls.add(p5);
		ls.add(p6);
		ls.add(p7);
		ls.add(p8);
		
		return ls;
	
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		liste = new ArrayList<>(getList());
		int column = 0;
		int row = 1;
		try {
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
	void switchToMovie(MouseEvent event) throws IOException {
		App.setRoot("movie");
	}
	@FXML
	void switchToSerie(MouseEvent event) throws IOException {
		App.setRoot("serie");
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
