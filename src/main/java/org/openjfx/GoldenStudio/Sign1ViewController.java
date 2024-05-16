package org.openjfx.GoldenStudio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controllers.ActorController;
import Controllers.AdminController;
import Controllers.ProducerController;
import Controllers.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Sign1ViewController implements Initializable {
	@FXML
	private Label emailerror, nameerror, passworderror, repeaterror;

	@FXML
	private TextField adress;

	@FXML
	private Button log;

	@FXML
	private PasswordField pass;

	@FXML
	private PasswordField passConfir;

	@FXML
	private TextField userName;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

	public static boolean isValidEmail(String email) {
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	@FXML
	public void test(KeyEvent event) throws IOException {
		if (userName.getText().indexOf("/") > -1 || userName.getText().indexOf("'") > -1)
			setnameerror("Username cannot contain / or '");
		else
			setnameerror("");

	}

	@FXML
	public void testpass(KeyEvent event) throws IOException {

		if (pass.getText().length() < 6 || pass.getText().length() > 18)
			setpassworderror("Password should be between 6 and 18 characters");
		else
			setpassworderror("");

	}

	@FXML
	public void testrepeat(KeyEvent event) throws IOException {

		if (passConfir.getText() != pass.getText())
			setrepeaterror("Passwords are not similar");
		else
			setrepeaterror("");

	}

	public List<String> get_liste_Data() {
		List<String> liste = new ArrayList<>();
		liste.add(userName.getText());
		liste.add(pass.getText());
		liste.add(adress.getText());

		return liste;
	}

	@FXML
	void setnameerror(String str) {
		nameerror.setText(str);
	}

	@FXML
	void setemailerror(String str) {
		emailerror.setText(str);
	}

	@FXML
	void setpassworderror(String str) {
		passworderror.setText(str);
	}

	@FXML
	void setrepeaterror(String str) {
		repeaterror.setText(str);
	}

	@FXML
	void switchToSign2(ActionEvent event) throws IOException {

		String path = App.class.getResource("").toString();
		String[] paths = path.split("/");
		String finalpath = paths[1];
		int i = 2;
		while (i < paths.length - 1 && !paths[i].equalsIgnoreCase("GoldenStudio")) {
			finalpath += "\\" + paths[i];
			i++;
		}
		finalpath += "\\" + paths[i];
		File tmp = new File(finalpath + "\\library\\Cache\\tmp.txt");
		Alert alert = new Alert(Alert.AlertType.ERROR);
		tmp.createNewFile();

		FileWriter oldData = new FileWriter(tmp);
		String name = userName.getText();
		String password = pass.getText();
		String password2 = passConfir.getText();
		String address = adress.getText();

		if (name.isEmpty() || password.isEmpty() || password2.isEmpty() || address.isEmpty()) {

			alert.setHeaderText("ERROR !!");
			alert.setContentText("PLEASE FILL ALL DATA");
			alert.showAndWait();
		}

		else if (!((passConfir.getText()).equals(pass.getText()))) {
			setrepeaterror("Passwords are not similar");
		}

		else if (!isValidEmail(adress.getText())) {
			setemailerror("Invalide E-mail !");
		} else if (UserController.check(address) || ActorController.check(address) || AdminController.check(address)
				|| ProducerController.check(address))
			setemailerror("E-mail is used!");
		else {
			// setrepeaterror("");
			oldData.write(name + "/" + address + "/" + password);
			oldData.close();

			App.setRoot("signView2");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
