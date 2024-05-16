package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.ResourceBundle;

import entities.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MovieInfo2Controller implements Initializable {
    @FXML
    private Label Language;

    @FXML
    private ImageView affiche;

    @FXML
    private Label country;

    @FXML
    private Label dateRealese;

    @FXML
    private Text description;

    @FXML
    private Label genre1;

    @FXML
    private Label genre2;

    @FXML
    private Label genre3;

    @FXML
    private Label name;

    @FXML
    private Label note;

    @FXML
    private Label prodName;

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
		
		if (data[4].equals("PRODUCER"))
			App.setRoot("producerHome");
		else if (data[4].equals("ACTOR"))
			App.setRoot("actorHome");
		else
			App.setRoot("adminHome");
    }
    public void setData(Movie movie) {
		Image aff = new Image(getClass().getResourceAsStream(movie.getPoster()));
		affiche.setImage(aff);
		name.setText(movie.getName());

		String genre_file=new String(movie.getGenre());
		String[] genres= genre_file.split("/");
		genre1.setText(genres[0]);
		genre2.setText(genres[1]);
		genre3.setText(genres[2]);
		
		country.setText(movie.getCountry());
		note.setText(String.valueOf(movie.getNote()));
		prodName.setText(movie.getProducer());
		Language.setText(movie.getLanguage());
		dateRealese.setText(String.valueOf(movie.getYear()));
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String movieClicked = null;
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
		
		try {
			movieClicked = new String(Files.readAllBytes(Path.of(finalpath+"\\library\\Cache\\tmpMovie.txt")));
		} catch (IOException e1) {
		
			e1.printStackTrace();
		}
		
		String[] MovieClickedSplited = movieClicked.split("&&");
		//recuperation des donnée stocke dans le fichier en une instance 
		
		
		Movie currentMovie = new Movie(MovieClickedSplited[0],MovieClickedSplited[1],Date.valueOf(MovieClickedSplited[2]),MovieClickedSplited[3],
				MovieClickedSplited[4],MovieClickedSplited[5],MovieClickedSplited[6],MovieClickedSplited[7],
				MovieClickedSplited[8],Double.valueOf(MovieClickedSplited[9]),MovieClickedSplited[10]);

		setData(currentMovie);		
	}
	
}
