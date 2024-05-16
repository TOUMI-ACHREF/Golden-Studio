package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import Controllers.EpisodeController;
import entities.Episode;
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

public class SeasonIfoController implements Initializable{
    @FXML
    private ImageView affiche;

    @FXML
    private AnchorPane box;

    @FXML
    private Label numSeason;

    @FXML
	void backHome(ActionEvent event) throws IOException {
		App.setRoot("home");
	}
    
    public void setData(Serie serie,Season season) {
		Image aff = new Image(getClass().getResourceAsStream(serie.getPoster()));
		affiche.setImage(aff);
		numSeason.setText(String.valueOf(season.getNum()));
	}



    @Override
	public void initialize(URL location, ResourceBundle resources) {
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String[] serieClickedSplited = serieClicked.split("&&");

		Serie currentSerie = null;

		//recuperation des donnée stocke dans le fichier en une instance 
		
		currentSerie = new Serie(serieClickedSplited[0],serieClickedSplited[1],Date.valueOf(serieClickedSplited[2]),serieClickedSplited[3],
				serieClickedSplited[4],serieClickedSplited[5],serieClickedSplited[6],serieClickedSplited[7],
				serieClickedSplited[8],Double.valueOf(serieClickedSplited[9])
				,Integer.valueOf(serieClickedSplited[10]));
    	
    	
    	
    	String seasonClicked = null;
		String path1 =App.class.getResource("").toString();
		String[] paths1=path1.split("/");
	    String finalpath1=paths1[1];
	    int f=2;
	    while(f<paths1.length-1&&!paths1[f].equalsIgnoreCase("GoldenStudio"))
	    {
	    	
	    	finalpath1+="\\"+paths1[f];
	    	f++;
	    }
		finalpath1+="\\"+paths1[f];
		
		try {
			seasonClicked = new String(Files.readAllBytes(Path.of(finalpath1+"\\library\\Cache\\tmpSeason.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] seasonClickedSplited = seasonClicked.split("&&");

		Season currentSeason = null;

		//recuperation des donnée stocke dans le fichier en une instance 
		
		currentSeason = new Season(Integer.valueOf(seasonClickedSplited[0]),seasonClickedSplited[1],Integer.valueOf(seasonClickedSplited[2]));
		
		setData(currentSerie,currentSeason);
		
		//recuperation du season de cette serie
		
		AnchorPane seasonsBox;
		try {
			List <Episode> listeEpisode = EpisodeController.getAllEpisode(currentSerie.getId(),Integer.valueOf(seasonClickedSplited[0]));
			
			if (!(listeEpisode == null)) {
				for(Episode j : listeEpisode) {
					//code standard a ne pas modifier
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("EpisodeMini.fxml"));
					seasonsBox = fxmlLoader.load();
					EpisodeMiniController episodeController = fxmlLoader.getController();
					episodeController.savingData(j);
					box.getChildren().add(seasonsBox);
					//
				}
			}
		} catch (IOException  e) {
			e.printStackTrace();
		}
    }








}
