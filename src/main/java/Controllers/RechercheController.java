package Controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;

import org.openjfx.GoldenStudio.App;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.RechercheService;

public class RechercheController  {

	@FXML
	private TextField search;
    //supprimer tous les redandance dans la tableau
	private String[] table(String[] tab) {
		int j = 0;
		String[] newTab = null;
		boolean test = false;
		for (String ch : tab) {
			for (int i = 0; i < j; i++) {
				if (ch.equals(newTab[i]))
					test = true;
			}
			if (!test) {

				newTab[j] = ch;
				j++;
			}

		}
		return newTab;
	}

	@FXML
	 void Recherche(MouseEvent event) throws IOException {

		String text = search.getText();
		String[] words = text.split(" ");
		int[] idm = null;
		int[] ids = null;
		String idms = "";
		String idss = "";

		for (String word : words) {
			for (int i = 0; i < RechercheService.SearchMovie(word).size(); i++)
				idm[i] = RechercheService.SearchMovie(word).get(i);
			for (int i = 0; i < RechercheService.SearchSerie(word).size(); i++)
				ids[i] = RechercheService.SearchSerie(word).get(i);

		
			for (int x : idm)
				idms += x + "/";
			for (int x : ids)
				idss += x + "/";

		}
		String[] idmovie = idms.split("/");
		String[] idserie = idss.split("/");

		String[] idmovies = table(idmovie);

		String[] idseries = table(idserie);

		String movies = "";
		String series = "";
		for (String ch : idmovies)
			movies += ch + "/";
		for (String ch : idseries)
			series += ch + "/";
		PreparedStatement pstmt = null;

		String path = App.class.getResource("").toString();
		String[] paths = path.split("/");
		String finalpath = paths[1];
		int i = 2;
		while (i < paths.length - 1 && !paths[i].equalsIgnoreCase("GoldenStudio")) {

			finalpath += "\\" + paths[i];
			i++;
		}
		finalpath += "\\" + paths[i];
		File tmp = new File(finalpath + "\\library\\Cache\\RechercheMovie.txt");
		tmp.createNewFile();
		FileWriter data = new FileWriter(tmp);
		data.write(movies);
		data.close();

		tmp = new File(finalpath + "\\library\\Cache\\RechercheSerie.txt");
		tmp.createNewFile();
		data = new FileWriter(tmp);
		data.write(series);
		data.close();
		
		

		 App.setRoot("searchMedia");
	}



	
}
