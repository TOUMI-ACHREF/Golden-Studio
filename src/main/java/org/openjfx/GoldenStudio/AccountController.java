package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class AccountController implements Initializable{
	@FXML
    private VBox mainBox;
	//a modifier de la part de JASSER en modifiant le fichier cache pour ajouter un champ password 
	//noter bien qu'il faux les rajouter aprés le champ user_type puisqu'on a travailler tout avec T[4] == "USER_type"
	//vous pouver effacer cette instance et une simple lecture d'un fichier et une creation d'une instance pour
	//l'initialisation des données
	
	User user = new User("achref","achref@gmail.com","achref1234",Date.valueOf("2002-06-21"),"Tunisia");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("UserAccountLayout.fxml"));
			VBox box = fxmlLoader.load();
			AccountLayoutController controller = fxmlLoader.getController();
			controller.setData();
			mainBox.getChildren().add(box);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
