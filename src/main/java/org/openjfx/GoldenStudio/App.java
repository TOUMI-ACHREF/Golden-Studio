package org.openjfx.GoldenStudio;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
* JavaFX App
*/
public class App extends Application {

    private static Scene scene;

    @SuppressWarnings("exports")
	@Override
    public void start(Stage stage) throws IOException {
    	Image icone = new Image(getClass().getResourceAsStream("imageSource/logo.png"));
    	scene = new Scene(loadFXML("start"), 1200, 750);
        stage.setScene(scene);
        stage.show();
        stage.getIcons().add(icone);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}