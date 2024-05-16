package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import Controllers.ActorController;
import Controllers.AdminController;
import Controllers.ProducerController;
import Controllers.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class UpdatePasswordController {
	@FXML
	private Button cancel;

	@FXML
	private Button update;
	@FXML
	private PasswordField oldpass,newpass,confpass;
	@FXML
	private Label olderror,newerror,conferror;
	@FXML
	void updateSwitchToAccount(ActionEvent event) throws IOException {
		String olddata;
		olderror.setText("");
		newerror.setText("");
		conferror.setText("");
		Alert alert = new Alert(Alert.AlertType.ERROR);
		try {
			String path = App.class.getResource("").toString();
			String[] paths = path.split("/");
			String finalpath = paths[1];
			int i = 2;
			while (i < paths.length - 1 && !paths[i].equalsIgnoreCase("GoldenStudio")) {

				finalpath += "\\" + paths[i];
				i++;
			}
			finalpath += "\\" + paths[i];
			olddata = new String(Files.readAllBytes(Path.of(finalpath + "\\library\\Cache\\cache.txt")));
			String[] data=olddata.split("/");
			 if (!ProducerController.login(data[1], oldpass.getText())&&!UserController.login(data[1], oldpass.getText())&&!AdminController.login(data[1], oldpass.getText())&&!ActorController.login(data[1], oldpass.getText()))
			{
				olderror.setText("Password incorrect");
			}
			else if (newpass.getText().length()<6)
			{
				newerror.setText("Password must have 6 or more letters");
			}
			else if (!confpass.getText().equals(newpass.getText()))
			{
				conferror.setText("Passwords are not similar");
			}
			else
			{
				if(ProducerController.check(data[1]))
				{
					ProducerController.ChangePass(data[1],newpass.getText());
						
				}
				else if (UserController.check(data[1]))
				{

					UserController.ChangePass(data[1],newpass.getText());
				}
				else if (AdminController.check(data[1]))
				{

					AdminController.ChangePass(data[1],newpass.getText());
				}
				else 
				{
					ActorController.ChangePass(data[1],newpass.getText());
					
				}
				App.setRoot("UserAccountView");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void cancelSwitchToAccount(ActionEvent event) throws IOException {
		App.setRoot("UserAccountView");
	}
}
