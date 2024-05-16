package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import entities.UserUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AccountLayoutController {
	@FXML
	private Button GOback;

	@FXML
	private Button GOupdate;

	@FXML
	private Button GochangePass;

	@FXML
	private Label userName;
	@FXML
	private Label accountType;

	@FXML
	private Label country;

	@FXML
	private Label dateBirth;

	@FXML
	private Label email;

	public void setData() {
		String olddata;
		try {String path =App.class.getResource("").toString();
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
			userName.setText(data[0]);
			country.setText(data[2]);
			dateBirth.setText(data[3]);
			email.setText(data[1]);
			accountType.setText(data[4]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	@FXML
	void switchToUpdatePass(ActionEvent event) throws IOException {
		App.setRoot("UpdatePasswordView");
	}

	@FXML
	void switchToUpdate(ActionEvent event) throws IOException {
		App.setRoot("UpdateAccountView");
	}
	
	@FXML
	void switchToUpdatePassword(ActionEvent event) throws IOException {
		App.setRoot("UpdatePasswordView");
	}

}
