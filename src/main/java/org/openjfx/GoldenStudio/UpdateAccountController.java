package org.openjfx.GoldenStudio;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.ResourceBundle;

import Controllers.ActorController;
import Controllers.AdminController;
import Controllers.ProducerController;
import Controllers.UserController;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class UpdateAccountController implements Initializable {
	@FXML
	private Button cancel;

	@FXML
	private Button update;

	@FXML
	private PasswordField pass;

	@FXML
	private TextField userName;

	@FXML
	private ChoiceBox<String> country;

	final ObservableList<String> listeCountries = FXCollections.observableArrayList("France", "Tunisia", "England",
			"Italy", "Germany", "Netherland");

	@FXML
	private TextField email;

	@FXML
	private DatePicker date;

	@FXML
	private Label nameerror, dateerror,passeror;

	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		country.setItems(listeCountries);
	}

	@FXML
	public void test(KeyEvent event) throws IOException {
		if (userName.getText().indexOf("/") > -1 || userName.getText().indexOf("'") > -1)
			setnameerror("Username cannot contain  /  ou '  ");
		else
			setnameerror("");

	}

	@FXML
	void setnameerror(String str) {
		nameerror.setText(str);
	}

	@FXML
	void updateSwitchToAccount(ActionEvent event) throws IOException {
		String olddata;

		Alert alert = new Alert(Alert.AlertType.ERROR);
		Alert alert1 = new Alert(Alert.AlertType.WARNING);
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
			// test
			String name = userName.getText();
			String count = country.getValue();
			java.util.Date d = new java.util.Date();
			if ((date.getValue() == null) || name == null || count == null) {
				alert.setHeaderText("ERROR !!");
				alert.setContentText("PLEASE FILL ALL DATA");
				alert.showAndWait();
			} else if ((d.getYear())-(date.getValue().getYear()-1900)<10) {

				dateerror.setText("You're too young ");
			} else if ( d.getYear()-(date.getValue().getYear()-1900) >  100) {

				dateerror.setText("date is not available ");
			}
			else if (!ProducerController.login(data[1], pass.getText())&&!UserController.login(data[1], pass.getText())&&!AdminController.login(data[1], pass.getText())&&!ActorController.login(data[1], pass.getText()))
			{
				passeror.setText("Password incorrect");
			}
			else  {
				if(ProducerController.check(data[1]))
				{
					ProducerController.update(data[1], count, Date.valueOf( date.getValue()));
					alert1.setHeaderText("WARNING !!");
					alert1.setContentText("PRODUCERS CANNOT CHANGE THERE NAME");
					alert1.showAndWait();
						
				}
				else if (UserController.check(data[1]))
				{
					UserController.update(data[1], name, count, Date.valueOf( date.getValue()));
				}
				else if (AdminController.check(data[1]))
				{
					AdminController.update(data[1], name, count, Date.valueOf( date.getValue()));
				}
				else 
				{
					ActorController.update(data[1], name, count, Date.valueOf( date.getValue()));
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
