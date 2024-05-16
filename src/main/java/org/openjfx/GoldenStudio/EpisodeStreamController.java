package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class EpisodeStreamController implements Initializable{
private WebEngine engine;
    
    @FXML
    private WebView webView;
	
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	engine = webView.getEngine();
    	
		try {
			loadPage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadPage() throws IOException {
		String EpisodeClicked;
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
		EpisodeClicked = new String(Files.readAllBytes(Path.of(finalpath+"\\library\\Cache\\episode.txt")));
		//recuperation du link du trailer
		String link = EpisodeClicked;
		
		engine.load(link);
	}
    @FXML
    void switchToHome(ActionEvent event) throws IOException {
    	App.setRoot("home");
    }
}
