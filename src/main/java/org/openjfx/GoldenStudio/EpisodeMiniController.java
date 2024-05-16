package org.openjfx.GoldenStudio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

import entities.Episode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EpisodeMiniController implements Initializable {

    @FXML
    private Label numEp;

    @FXML
    private ImageView poster;

    Episode ep;
    @FXML
    void switchToEpisode(MouseEvent event) throws IOException {
    	
    	App.setRoot("Episode");
    }
    
    void savingData(Episode ep) throws IOException {
    	//this.ep=ep;
    	
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
		File tmp = new File(finalpath+"\\library\\Cache\\episode.txt");
		tmp.createNewFile();
		FileWriter fw = new FileWriter(tmp);
		fw.write(ep.getStream());
		fw.close();
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//poster set 
		
		String olddata = null;
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
			olddata = new String(Files.readAllBytes(Path.of(finalpath+"\\library\\Cache\\tmpSerie.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] splited = olddata.split("&&");
		Image aff = new Image(getClass().getResourceAsStream(splited[5]));
		poster.setImage(aff);
		
		
		
		//numero
		//numEp.setText(String.valueOf(ep.getNum()));
		/*
		String EpisodeClicked = null;
		String path1 =App.class.getResource("").toString();
		String[] paths1=path1.split("/");
	    String finalpath1=paths1[1];
	    int j=2;
	    while(j<paths1.length-1&&!paths1[j].equalsIgnoreCase("GoldenStudio"))
	    {
	    	
	    	finalpath1+="\\"+paths1[j];
	    	j++;
	    }
		finalpath1+="\\"+paths1[i];
		try {
			EpisodeClicked = new String(Files.readAllBytes(Path.of(finalpath+"\\library\\Cache\\episode.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//recuperation du link du trailer
		String[] splited1 = EpisodeClicked.split("&&");
		String numero = splited1[1];
		
		numEp.setText(numero);
	*/
		
	
		
		
	}

}