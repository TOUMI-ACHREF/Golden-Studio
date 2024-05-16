package entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class User extends UserUI{
	//table dans la db qui gere cette liste sont (FAVOURITES)
	private List<Media> favourites;
	//private List<String> favouritesGenre; cette champ optionel qu'on peut l'ajouter par la suite

	//on initialise la liste des serie prefere qui contient initialement 0 elements 
	//et on va la remlire tant que l'utilisateur ajoute un Media au favourie
	
	public User() {
		super();
		this.favourites = new ArrayList<>();
	}

	public User(String username, String email, String password, Date birthDate, String nativeCountry) {
		super(username, email, password, birthDate, nativeCountry);
	}

	//getters and setters
	public List<Media> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<Media> favourites) {
		this.favourites = favourites;
	}
	
	
	
}
