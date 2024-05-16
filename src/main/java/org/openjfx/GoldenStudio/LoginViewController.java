package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import Controllers.ActorController;
import Controllers.AdminController;
import Controllers.ProducerController;
import Controllers.UserController;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController {
	@FXML
    private PasswordField password;
	
	@FXML
	private Label errormsg;
    @FXML
    private TextField userName;
    
    @FXML
    private Button sign;
    
    @FXML
    private Button log;
	
	@FXML
	void switchToSign(ActionEvent event) throws IOException {
		App.setRoot("signView1");
	}
	@FXML
    static void getData(String email) throws IOException
    {
		if(ProducerController.check(email))
		{
			//store Producer data
			ProducerController.storeData(email);
			
		}else if (UserController.check(email))
		{
			//store User data
			UserController.storeData(email);
		}else if (AdminController.check(email))
		{
			//store Admin data
			AdminController.storeData(email);
		}else 
		{
			//store Actor data
			ActorController.storeData(email);
		}
    }
	
	@FXML
	    void switchToHome(ActionEvent event) throws IOException {
		
		  	String mail = userName.getText();
		  	String pass = password.getText();
			if(pass.isEmpty() || mail.isEmpty()) {

				  //erreur ou un champ est vide est traitée
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("ERROR !!");
				alert.setContentText("PLEASE FILL ALL DATA");
				alert.showAndWait();
			}
			else{

				  //ici un test qui verifie que le compte existe ou non 
				if(ProducerController.login(mail,pass)||ActorController.login(mail,pass)||AdminController.login(mail,pass)||UserController.login(mail,pass))
				{

					errormsg.setText("");
					//store data dans un fichier temporelle
					getData(mail);
					//teste pour choisir l'interface de login
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
				else
				{
					  //afficher des alert dans le cas d'un compte inexistant.
					errormsg.setText("User name or password is incorrect");
				}
				
			}
	   }
}
