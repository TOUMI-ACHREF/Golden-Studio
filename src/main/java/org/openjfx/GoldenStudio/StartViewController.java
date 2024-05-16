package org.openjfx.GoldenStudio;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class StartViewController {

	@FXML
	private Label start1;

	@FXML
	private ImageView start2;

	@FXML
	private Pane start3;

	@FXML
	private Pane start4;

	@FXML
	private Pane start5;

	@FXML
	private void switchToLogin(MouseEvent event) throws IOException {
		App.setRoot("login");
	}
}
