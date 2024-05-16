package org.openjfx.GoldenStudio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import entities.Media;
import entities.Movie;
import entities.Serie;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MediaMiniViewController {
	@FXML
	private ImageView affiche;

	@FXML
	private VBox box;

	@FXML
	private HBox nameBox;

	@FXML
	private Label genre;

	@FXML
	private HBox gereBox;

	@FXML
	private Label name;
	
	private Movie movie = null;

	public void setData(Media movie) {
		Image aff = new Image(getClass().getResourceAsStream(movie.getPoster()));
		affiche.setImage(aff);
		name.setText(movie.getName());
		genre.setText(movie.getGenre());
		
		this.movie = (Movie)movie;
	}
	
	@FXML
	void switchToMediaInfo(MouseEvent event) throws IOException {
		savingData(movie);
		String account = null;
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
			account = new String(Files.readAllBytes(Path.of(finalpath+"\\library\\Cache\\cache.txt")));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		String[] data =account.split("/");
		
		if(data[4].equalsIgnoreCase("USER"))
			App.setRoot("MediaInfo");
		else
			App.setRoot("MovieInfo2");
	}
	
	
	void savingData (Movie movie) throws IOException {
		String path =App.class.getResource("").toString();
		String[] paths=path.split("/");
	    String finalpath=paths[1];
	    int i=2;
	    while(i<paths.length-1 && !paths[i].equalsIgnoreCase("GoldenStudio"))
	    {
	    	
	    	finalpath+="\\"+paths[i];
	    	i++;
	    }
		finalpath+="\\"+paths[i];
		File tmp = new File(finalpath+"\\library\\Cache\\tmpMovie.txt");
		tmp.createNewFile();
		FileWriter currentMovie = new FileWriter(tmp);
		
		currentMovie.write(movie.getName()+"&&"+movie.getProducer()+"&&"+movie.getYear()
							+"&&"+movie.getLanguage()+"&&"+movie.getCountry()+"&&"+movie.getPoster()
							+"&&"+movie.getTrailer()+"&&"+movie.getDescription()+"&&"+movie.getGenre()+"&&"
							+movie.getNote()+"&&"+movie.getMovieURL());
		currentMovie.close();
	}
	
}
