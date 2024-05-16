package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import Controllers.SeasonController;
import entities.Media;
import entities.Season;
import entities.Serie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SerieinfoController implements Initializable{

	
		@FXML
		List<Media> liste;
		
		@FXML
	    private ImageView affiche,s1,s2,s3,s4,s5;

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
	    private HBox seasons;


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

	    public void setData(Serie serie,double note) {
			Image aff = new Image(getClass().getResourceAsStream(serie.getPoster()));
			affiche.setImage(aff);
			name.setText(serie.getName());

			String genre_file=new String(serie.getGenre());
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
			String serieClicked = null;
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
			
			String[] serieClickedSplited = serieClicked.split("&&");

			Serie currentSerie = null;

			//recuperation des donnée stocke dans le fichier en une instance 
			
			currentSerie = new Serie(serieClickedSplited[0],serieClickedSplited[1],Date.valueOf(serieClickedSplited[2]),serieClickedSplited[3],
					serieClickedSplited[4],serieClickedSplited[5],serieClickedSplited[6],serieClickedSplited[7],
					serieClickedSplited[8],Double.valueOf(serieClickedSplited[9])
					,Integer.valueOf(serieClickedSplited[10]));
			
			setData(currentSerie,Double.valueOf(serieClickedSplited[9]));
			
			//recuperation du season de cette serie
			
			AnchorPane seasonsBox;
			try {
				List <Season> listeSeasons = SeasonController.getAllSeason(currentSerie.getId());
				for(Season j : listeSeasons) {
					//code standard a ne pas modifier
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("SeasonMini.fxml"));
					seasonsBox = fxmlLoader.load();
					SeasonMiniController serieController = fxmlLoader.getController();
					serieController.setData(currentSerie,j);
					seasons.getChildren().add(seasonsBox);
					//
				}
			} catch (IOException  e) {
				e.printStackTrace();
			}
			
			//***********************************************
			//Partie des seasons aciver just apres la seasonDao
			
			/*
			VBox serieBox;
			try {
				liste = SerieController.getAllSeries();
			
				for(int i=0 ;i<liste.size() ; i++) {
					//code standard a ne pas modifier
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("SerieMiniView.fxml"));
					serieBox = fxmlLoader.load();
					SerieMiniController serieController = fxmlLoader.getController();
					
					serieController.setData(liste.get(i));
					seasons.getChildren().add(serieBox);
					//
				}
			} catch (IOException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
		}

	
}
