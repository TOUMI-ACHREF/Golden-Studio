package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MediaInfoController implements Initializable{
	@FXML
	List<Movie> liste;

    @FXML
    private ImageView s1,s2,s3,s4,s5;
    
	@FXML
    private ImageView affiche;

    @FXML
    private Label genre1;

    @FXML
    private Label genre2;

    @FXML
    private Label genre3;

    @FXML
    private Label name;
    
    @FXML
    private Text description;
    
    @FXML
    private HBox similar;
    @FXML
    private TextField note;

    @FXML
	void switchToHome(ActionEvent event) throws IOException {
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
			App.setRoot("home");
		else if(data[4].equalsIgnoreCase("PRODUCER"))
			App.setRoot("producerHome");
		else if(data[4].equalsIgnoreCase("ACTOR"))
			App.setRoot("actorHome");
		else
			App.setRoot("adminHome");
	}

    @FXML
	void switchToStreamView(MouseEvent event) throws IOException {
		App.setRoot("StreamView");
	}
    
    @FXML
	void switchToTrailerView(MouseEvent event) throws IOException {
		App.setRoot("TrailerView");
	}

    public void setData(Movie movie, double note) {
		Image aff = new Image(getClass().getResourceAsStream(movie.getPoster()));
		affiche.setImage(aff);
		name.setText(movie.getName());
		description.setText(movie.getDescription());
		
		String genre_file=new String(movie.getGenre());
		String[] genres= genre_file.split("/");
		genre1.setText(genres[0]);
		genre2.setText(genres[1]);
		genre3.setText(genres[2]);
		
		Image starIcone = new Image(getClass().getResourceAsStream("imageSource/star.png"));
		
		if(note >= 1)
			s1.setImage(starIcone);
		if(note >= 2)
			s2.setImage(starIcone);
		if(note >= 3)
			s3.setImage(starIcone);
		if(note >= 4)
			s4.setImage(starIcone);
		if(note == 5)
			s5.setImage(starIcone);
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//l'idee est de cree un fichier depuit la clique sur la MediaMiniView qui contient le données 
		//de ce film et on va l'initialiser facilement chaque fois et on va facillite le code
	
		//au debut j'ai besoin de ce fichier qui contien le film (data) puis je vais instancier un nouveau
		//Movie finalement un simple appele au methode setData(Media movie) pour initialiser les données
		
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

		setData(currentMovie,Double.valueOf(MovieClickedSplited[9]));

		//cette partie va change en fonction du genre on va rammener des film similar
		//initialisation du discover similar movies
		VBox mediaBox;
		try {
			liste = MovieController.getAllMovies();
		
			for(int j=0 ;j<liste.size() ; j++) {
				//code standard a ne pas modifier
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("mediaMini.fxml"));
				mediaBox = fxmlLoader.load();
				MediaMiniViewController mediaController = fxmlLoader.getController();
				mediaController.setData(liste.get(j));
				similar.getChildren().add(mediaBox);
				//
			}
		} catch (ParseException | SQLException |IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
	void updateNote(ActionEvent actionEvent) {
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
		double newNote = 0.0;
		if(Double.valueOf(MovieClickedSplited[9])!=0) 
			newNote = (  (Double.valueOf(MovieClickedSplited[9])*5) + Integer.valueOf(note.getText())  ) /5;
		else
			newNote = Integer.valueOf(note.getText());
		MovieController.updateNote(MovieClickedSplited[0] ,newNote);
		
	}

}