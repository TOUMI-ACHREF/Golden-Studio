package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.ResourceBundle;

import entities.Serie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class SerieInfo2Controller implements Initializable {
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
	    public void setData(Serie serie) {
			Image aff = new Image(getClass().getResourceAsStream(serie.getPoster()));
			affiche.setImage(aff);
			name.setText(serie.getName());

			String genre_file=new String(serie.getGenre());
			String[] genres= genre_file.split("/");
			genre1.setText(genres[0]);
			genre2.setText(genres[1]);
			genre3.setText(genres[2]);
			
			country.setText(serie.getCountry());
			note.setText(String.valueOf(serie.getNote()));
			prodName.setText(serie.getProducer());
			Language.setText(serie.getLanguage());
			dateRealese.setText(String.valueOf(serie.getYear()));
	    }
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			String serieClicked  = null;
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
				serieClicked = new String(Files.readAllBytes(Path.of(finalpath+"\\library\\Cache\\tmpSerie.txt")));
			} catch (IOException e1) {
			
				e1.printStackTrace();
			}
			
			String[] serieClickedSplited = serieClicked .split("&&");
			//recuperation des donnée stocke dans le fichier en une instance 
			
			
			Serie currentSerie = new Serie(serieClickedSplited[0],serieClickedSplited[1],Date.valueOf(serieClickedSplited[2]),serieClickedSplited[3],
					serieClickedSplited[4],serieClickedSplited[5],serieClickedSplited[6],serieClickedSplited[7],
					serieClickedSplited[8],Double.valueOf(serieClickedSplited[9])
					,Integer.valueOf(serieClickedSplited[10]));
			setData(currentSerie);		
		}
}
