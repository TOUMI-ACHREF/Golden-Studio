package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class TrailerController implements Initializable{

	 @FXML
	 private HBox hbox;

	 @FXML
	 private WebView webView;
	 private WebEngine engine;
	 
	 public void initialize(URL location, ResourceBundle resources) {
			//Stage stage = (Stage) anchor.getScene().getWindow();
			
			//stage.setResizable(false);
			engine = webView.getEngine();
			loadPage();
		
		
	}
	
	public void loadPage() {
		String MovieClicked = null;
		try {
			MovieClicked = new String(Files.readAllBytes(Path.of("C:\\Users\\21696\\eclipse-workspace\\GoldenStudio\\library\\tmpMovie.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] MovieClickedSplited = MovieClicked.split("&&");
		//recuperation du link du trailer 
		String link = MovieClickedSplited[6];
		
		engine.load(link);
	}
	
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
		
		if (data[4].equals("USER"))
			App.setRoot("home");
		else if (data[4].equals("PRODUCER"))
			App.setRoot("producerHome");
		else if (data[4].equals("ACTOR"))
			App.setRoot("actorHome");
		else
			App.setRoot("adminHome");
	}
}
