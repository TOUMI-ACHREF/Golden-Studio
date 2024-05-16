package org.openjfx.GoldenStudio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import entities.Season;
import entities.Serie;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SeasonMiniController {
	@FXML
    private Label numSeasonEp;

    @FXML
    private ImageView poster;

    Serie  serie = null ; 
    Season season = null;

    public void setData(Serie serie,Season season) {
		Image aff = new Image(getClass().getResourceAsStream(serie.getPoster()));
		poster.setImage(aff);
		numSeasonEp.setText(String.valueOf(season.getNum())); 

		this.serie = serie;
		this.season = season;
	}
    
    @FXML
	void switchToInfo(MouseEvent event) throws IOException {
    	savingData(season);
    	App.setRoot("seasonsInfo");
	}
    
    
    void savingData (Season season) throws IOException {
		
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
		File tmp = new File(finalpath+"\\library\\Cache\\tmpSeason.txt");
		tmp.createNewFile();
		FileWriter currentSeason = new FileWriter(tmp);
		
		currentSeason.write(season.getNum()+"&&"+season.getTrailer()+"&&"+season.getId_serie());
		currentSeason.close();
	}
}
