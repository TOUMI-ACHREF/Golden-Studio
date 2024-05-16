package org.openjfx.GoldenStudio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import entities.Media;
import entities.Serie;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SerieMiniController {
//switchToSerieInfo
	
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
	
	private Serie serie = null;

	public void setData(Serie serie) {
		Image aff = new Image(getClass().getResourceAsStream(serie.getPoster()));
		affiche.setImage(aff);
		name.setText(serie.getName());
		genre.setText(serie.getGenre());
		
		this.serie = (Serie)serie;
	}
	
	@FXML
	void switchToSerieInfo(MouseEvent event) throws IOException {
		savingData(serie);
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
			App.setRoot("SerieInfo");
		else
			App.setRoot("SerieInfo2");
	
	}
	
	
	void savingData (Serie serie) throws IOException {
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
		File tmp = new File(finalpath+"\\library\\Cache\\tmpSerie.txt");
		tmp.createNewFile();
		FileWriter currentSerie = new FileWriter(tmp);
		
		currentSerie.write(serie.getName()+"&&"+serie.getProducer()+"&&"+serie.getYear()
							+"&&"+serie.getLanguage()+"&&"+serie.getCountry()+"&&"+serie.getPoster()
							+"&&"+serie.getTrailer()+"&&"+serie.getDescription()+"&&"+serie.getGenre()+"&&"
							+serie.getNote()+"&&"+serie.getId());
		currentSerie.close();
	}
	
}
