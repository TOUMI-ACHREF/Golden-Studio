package org.openjfx.GoldenStudio;


import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

import Controllers.MovieController;
import entities.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class searchController implements Initializable{
    @FXML
    private TextField TextSearch;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label errorsearching;

    @FXML
    private GridPane resultsContainer;

    @FXML
    private ScrollPane resultsScroll;
    
    List<Movie> liste = null;
    int [] TabIdMovies=null;
    @FXML
	void switchToAccount(MouseEvent event) throws IOException {
		App.setRoot("UserAccountView");
	}
    
    public void setData() {
		String olddata;
		try {
		String path =App.class.getResource("").toString();
		String[] paths=path.split("/");
		int [] idmovies=null;

		int [] idseries=null;
	    String finalpath=paths[1];
	    int i=2;
	    while(i<paths.length-1&&!paths[i].equalsIgnoreCase("GoldenStudio"))
	    {
	    	
	    	finalpath+="\\"+paths[i];
	    	i++;
	    }
		finalpath+="\\"+paths[i];
		olddata = new String(Files.readAllBytes(Path.of(finalpath+"\\library\\Cache\\RechercheMovie.txt")));

			String[] datam=olddata.split("/");
			i=0;
			for (String ch : datam)
				{
				idmovies[i]=Integer.valueOf(ch);
				i++;
				}
			olddata = new String(Files.readAllBytes(Path.of(finalpath+"\\library\\Cache\\RechercheSerie.txt")));

			String[] datas=olddata.split("/");
			i=0;
			for (String ch : datas)
				{
				idseries[i]=Integer.valueOf(ch);
				i++;
				}
			
			
			// idmovies tableau de id movies
			TabIdMovies=idmovies;
			// idseries tableau de id series
			
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    //initialization du gridPane
    public void initialize(URL location, ResourceBundle resources) {
		
		int column = 0;
		int row = 1;
		try {
			for(int i=0 ;i<liste.size() ; i++) {
				
				FXMLLoader fxmlLoader = new FXMLLoader();
				//il y a  une autre View SerieMini tu doit tester instanceOf liste.get(i)
				//if Movie ->mediaMini if serie->serieMini
				fxmlLoader.setLocation(getClass().getResource("mediaMini.fxml"));
				VBox mediaBox = fxmlLoader.load();
				MediaMiniViewController mediaController = fxmlLoader.getController();
				mediaController.setData(MovieController.getMovie(TabIdMovies[i]));
	
				if (column == 6) {
					column = 0;
					++row;
				}
				resultsContainer.add(mediaBox, column++, row);
				GridPane.setMargin(mediaBox, new Insets (10));
			}
		}catch(IOException | ParseException | SQLException e) {
			e.printStackTrace();
		}
    
    }
    
    @FXML
	void switchToHome(ActionEvent event) throws IOException {
		
		String olddata;
		String path =App.class.getResource("").toString();
		String[] paths=path.split("/");
	    String finalpath=paths[1];
	    int i=2;
	    while(i<paths.length-1&&!paths[i].equalsIgnoreCase("GoldenStudio"))
	    {
	    	
	    	finalpath+="\\"+paths[i];
	    	i++;
	    }
		finalpath+="\\"+paths[i];
		olddata = new String(Files.readAllBytes(Path.of(finalpath+"\\library\\Cache\\cache.txt")));
		String[] data=olddata.split("/");
		
		if (data[4].equals("USER"))
			App.setRoot("home");
		else if (data[4].equals("PRODUCER"))
			App.setRoot("producerHome");
		else if (data[4].equals("ACTOR"))
			App.setRoot("actorHome");
		else
			App.setRoot("adminHome");
	}
    
}
