package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.text.ParseException;
import java.util.ResourceBundle;

import Controllers.MovieController;
import entities.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddMovieController implements Initializable{
	  @FXML
	    private TextField MovieName;

	    @FXML
	    private ChoiceBox<String> country;

	    @FXML
	    private Label countryER;

	    @FXML
	    private DatePicker dateRealese;

	    @FXML
	    private Label daterelER;

	    @FXML
	    private TextArea description;

	    @FXML
	    private Label descriptionER;

	    @FXML
	    private ComboBox<String> genre;

	    @FXML
	    private Label genreER;

	    @FXML
	    private TextField language;

	    @FXML
	    private Label languageER;

	    @FXML
	    private TextField movieURL;

	    @FXML
	    private Label movieURLER;

	    @FXML
	    private Label nameER;

	    @FXML
	    private Label posterER;

	    @FXML
	    private TextField posterURL;

	    @FXML
	    private Label trailerER;
	    @FXML
	    private TextField trailerLink;
	    
	    final ObservableList<String> listeCountries = FXCollections.observableArrayList("France","Tunisia","England","Italy","Germany","Netherland","Japan");


		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			country.setItems(listeCountries);
			
			genre.getItems().addAll("Drama/War/", "Romance/Drama/", "Scien-fic/Drama/Adventure", "Comedie/Scien-fic/Adventure", "Drama/Adventure/Scien-fic");
			genre.setVisibleRowCount(5);

			
		}
		@FXML
		public void AddMovie(ActionEvent actionEvent) throws IOException, ParseException {
    		Alert alert = new Alert(Alert.AlertType.ERROR);

			if(MovieName==null || dateRealese.getValue()==null ||language==null ||country==null ||
					posterURL==null ||trailerLink==null ||description==null ||genre==null ||movieURL==null)
			{
				alert.setHeaderText("ERROR !!");
				alert.setContentText("PLEASE FILL ALL DATA");
				alert.showAndWait();
			}
			else {
				Movie movie = null;
				//pour recupere le nom du producteur
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
				
				movie = new Movie(MovieName.getText(), data[0], Date.valueOf(dateRealese.getValue()), language.getText(),country.getValue(), posterURL.getText(),
						trailerLink.getText(),description.getText(),genre.getValue(),0,movieURL.getText());
				
				MovieController.add(movie);
				App.setRoot("producerHome");
			}
		}
		
		
		public void Back(ActionEvent actionEvent) throws IOException{
			App.setRoot("ProducerHome");
		}
		
	    ///////:
	    public void setError() {
	    	if(MovieName==null)
	    		nameER.setText("**Champ Obligatoire !");
	    	if(dateRealese==null)
	    		daterelER.setText("**Champ Obligatoire !");
	    	if(language==null)
	    		languageER.setText("**Champ Obligatoire !");
	    	if(country==null)
	    		countryER.setText("**Champ Obligatoire !");
	    	if(posterURL==null)
	    		posterER.setText("**Champ Obligatoire !");
	    	if(trailerLink==null)
	    		trailerER.setText("**Champ Obligatoire !");
	    	if(description==null)
	    		descriptionER.setText("**Champ Obligatoire !");
	    	if(genre==null)
	    		genreER.setText("**Champ Obligatoire !");
	    	if(movieURL==null)
	    		movieURLER.setText("**Champ Obligatoire !");
	    }
}
