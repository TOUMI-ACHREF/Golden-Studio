package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class SerieTrailerController {
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
		String serieClicked = null;
		try {
			serieClicked = new String(Files.readAllBytes(Path.of("C:\\Users\\21696\\eclipse-workspace\\GoldenStudio\\library\\tmpSerie.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] serieClickedSplited = serieClicked.split("&&");
		//recuperation du link du trailer 
		String link = serieClickedSplited[6];
		
		engine.load(link);
	}
	
	@FXML
	void switchToHome(ActionEvent event) throws IOException {
		String account=new String(Files.readAllBytes(Path.of("C:\\Users\\21696\\eclipse-workspace\\GoldenStudio\\library\\cache.txt")));
		String[] data=account.split("/");
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
