package org.openjfx.GoldenStudio;


import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.ResourceBundle;

import Controllers.ActorController;

import Controllers.ProducerController;
import Controllers.UserController;
import entities.Actor;

import entities.Producer;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class Sign2ViewController implements Initializable{
	 @FXML
	    private ChoiceBox<String> AccountType;
	   
	    @FXML
	    private ChoiceBox<String> Country;
	   
	    @FXML
	    private Label dateerror;
	    @FXML
	    private DatePicker dateBirth;
	    
	    @FXML
	    private Button create;
	    
	    final ObservableList<String> listeUsers = FXCollections.observableArrayList("USER","PRODUCER","ADMIN","ACTOR");
	    final ObservableList<String> listeCountries = FXCollections.observableArrayList("France","Tunisia","England","Italy","Germany","Netherland");

	    @Override
		public void initialize(URL url, ResourceBundle rb) {
	        AccountType.setItems(listeUsers);
	        Country.setItems(listeCountries);
	    }
	    
	    
	    
	    
	    @FXML
	    void switchToHome(ActionEvent event) throws IOException {
	    	String account = AccountType.getValue();
	    	String country = Country.getValue();
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		java.util.Date d = new java.util.Date(); 
    		if( (dateBirth.getValue() == null) || account == null || country == null) {
				alert.setHeaderText("ERROR !!");
				alert.setContentText("PLEASE FILL ALL DATA");
				alert.showAndWait();
	    	}
	    	else if ((d.getYear())-(dateBirth.getValue().getYear()-1900)<10) {

	    		dateerror.setText("You're too young ");
			} else if ( d.getYear()-(dateBirth.getValue().getYear()-1900) >  100) {

				dateerror.setText("date is not available ");
			}
	    	else {
	    		//je recupere la valeur du date de naissance apres le test pour ne pas avoir une exception de null pointer
				//String dateB =(Date.valueOf(dateBirth.getValue())).toString();
	    		//File tmp=new File("C:\\Users\\21696\\eclipse-workspace\\CinemaShow\\library\\tmp.txt");
	    		//FileReader fr= new FileReader(tmp);
	    		//Creation si'il n'existe pas un fichier
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
	    		String olddata=new String(Files.readAllBytes(Path.of(finalpath+"\\library\\Cache\\tmp.txt")));
	    		String[] data=olddata.split("/");
	    		/*
	    		alert.setHeaderText("Account !!");
				alert.setContentText(data[0]+" "+data[1]+" "+data[2]);
				alert.showAndWait();
				*/
	    		
				//creation d'une instance user
				switch(AccountType.getSelectionModel().getSelectedItem())
				{
				case "USER":

		    		User user = new User(data[0],data[1],data[2], Date.valueOf(dateBirth.getValue()), country);
					
					UserController.add(user);
					break;
				case "ADMIN":

		    		/*Admin admin = new User(data[0],data[1],data[2], Date.valueOf(dateBirth.getValue()), country);
					
					AdminController.add(admin);
					break;*/
				case "ACTOR":

		    		Actor actor = new Actor(data[0],data[1],data[2], Date.valueOf(dateBirth.getValue()), country);
					
					ActorController.add(actor);
					break;
				case "PRODUCER":

		    		Producer prod = new Producer(data[0],data[1],data[2], Date.valueOf(dateBirth.getValue()), country);
					
					ProducerController.add(prod);
					break;
					
					
				}
	    		
				
				// creation du compte puis ouverture de la vue HOME
					if (AccountType.getValue().equals("USER"))
						App.setRoot("home");
					else if (AccountType.getValue().equals("PRODUCER"))
						App.setRoot("producerHome");
					else if (AccountType.getValue().equals("ACTOR"))
						App.setRoot("actorHome");
					else
						App.setRoot("adminHome");
				
			}
		}
}


