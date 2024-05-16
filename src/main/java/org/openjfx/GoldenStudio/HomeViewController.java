package org.openjfx.GoldenStudio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

import Controllers.MovieController;
import Controllers.RechercheController;
import entities.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.RechercheService;

public class HomeViewController implements Initializable{
	@FXML
	List<Movie> liste;
	@FXML
	private HBox movie1;

	@FXML
	private Label movie2;

	@FXML
	private Pane movie3;
	@FXML
	private HBox FirstContainer;

	@FXML
	private HBox SecondContainer;
	
    @FXML
    private BorderPane borderPane;
    
    
    @FXML
	private TextField search;

	/*
	@FXML
	private FontAwesomeIconView home3;
	 
	@FXML
    private HBox productLayout;
	*/
   
    	
	@Override
	public void initialize (URL location ,ResourceBundle resources) {

		try {
			liste = MovieController.getAllMovies();
			for(int i=0 ;i<liste.size() ; i++) {
				//code standard a ne pas modifier
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("mediaMini.fxml"));
				VBox mediaBox = fxmlLoader.load();
				MediaMiniViewController mediaController = fxmlLoader.getController();
				mediaController.setData(liste.get(i));
				FirstContainer.getChildren().add(mediaBox);
				//
			}
			
			for(int i=0 ;i<liste.size() ; i++) {
				//code standard a ne pas modifier
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("mediaMini.fxml"));
				VBox mediaBox = fxmlLoader.load();
				MediaMiniViewController mediaController = fxmlLoader.getController();
				mediaController.setData(liste.get(i));
				SecondContainer.getChildren().add(mediaBox);
				//
			}
		}catch(IOException | ParseException | SQLException e){
			e.printStackTrace();
		}
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
	
	@FXML
	void switchToMovies(MouseEvent event) throws IOException {
		App.setRoot("movie");
	}

	@FXML
	void switchToSerie(MouseEvent event) throws IOException {
		App.setRoot("serie");
	}
	
	@FXML
	void switchToAccount(MouseEvent event) throws IOException {
		App.setRoot("UserAccountView");
	}
	
	@FXML
	void switchToFavourite(MouseEvent event) throws IOException {
		App.setRoot("favourite");
	}

	
	
    //supprimer tous les redandance dans la tableau
	private String[] table(String[] tab) {
		int j = 0;
		String[] newTab = null;
		boolean test = false;
		for (String ch : tab) {
			for (int i = 0; i < j; i++) {
				if (ch.equals(newTab[i]))
					test = true;
			}
			if (!test) {

				newTab[j] = ch;
				j++;
			}

		}
		return newTab;
	}

	@FXML
	 void Recherche(MouseEvent event) throws IOException {

		String text = search.getText();
		String[] words = text.split(" ");
		int[] idm = null;
		int[] ids = null;
		String idms = "";
		String idss = "";
		
		for (String word : words) {
			for (int i = 0; i < RechercheService.SearchMovie(word).size(); i++)
				idm[i] = RechercheService.SearchMovie(word).get(i);
			for (int i = 0; i < RechercheService.SearchSerie(word).size(); i++)
				ids[i] = RechercheService.SearchSerie(word).get(i);

		
			for (int x : idm)
				idms += x + "/";
			for (int x : ids)
				idss += x + "/";

		}
		
		String[] idmovie = idms.split("/");
		String[] idserie = idss.split("/");

		String[] idmovies = table(idmovie);

		String[] idseries = table(idserie);

		String movies = "";
		String series = "";
		for (String ch : idmovies)
			movies += ch + "/";
		for (String ch : idseries)
			series += ch + "/";
		PreparedStatement pstmt = null;

		String path = App.class.getResource("").toString();
		String[] paths = path.split("/");
		String finalpath = paths[1];
		int i = 2;
		while (i < paths.length - 1 && !paths[i].equalsIgnoreCase("GoldenStudio")) {

			finalpath += "\\" + paths[i];
			i++;
		}
		finalpath += "\\" + paths[i];
		File tmp = new File(finalpath + "\\library\\Cache\\RechercheMovie.txt");
		tmp.createNewFile();
		FileWriter data = new FileWriter(tmp);
		data.write(movies);
		data.close();

		tmp = new File(finalpath + "\\library\\Cache\\RechercheSerie.txt");
		tmp.createNewFile();
		data = new FileWriter(tmp);
		data.write(series);
		data.close();
		
		

		 App.setRoot("searchMedia");
	}
}
