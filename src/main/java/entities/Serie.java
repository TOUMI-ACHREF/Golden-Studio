package entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Serie extends Media {
	private int id; 
	

	private List<Season> seasons;
	
	//ces champs sont gerer par les table (ROLE_IN_SERIE / ROLR_IN_MOVIE)
	private List<Actor> acteurprimaire;
	private List<Actor> acteursecondaire;
	
	//Full constructor
	public Serie(String name, String producer, Date year, String language, String country, String poster,
			String trailer, String description, String genre, double note) {
		super(name, producer, year, language, country, poster, trailer, description, genre, note);
		
		this.acteurprimaire = new ArrayList<>();
		this.acteursecondaire = new ArrayList<>();
		this.seasons = new ArrayList<>();
	}
	//constructor with id
	public Serie(String name, String producer, Date year, String language, String country, String poster,
			String trailer, String description, String genre, double note,int id) {
		super(name, producer, year, language, country, poster, trailer, description, genre, note);
		this.id = id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Serie() {
		super();
		this.acteurprimaire = new ArrayList<>();
		this.acteursecondaire = new ArrayList<>();
		this.seasons = new ArrayList<>();
	}

	//setters and getters
	public List<Actor> getActeurprimaire() {
		return acteurprimaire;
	}

	public void setActeurprimaire(List<Actor> acteurprimaire) {
		this.acteurprimaire = acteurprimaire;
	}

	public List<Actor> getActeursecondaire() {
		return acteursecondaire;
	}

	public void setActeursecondaire(List<Actor> acteursecondaire) {
		this.acteursecondaire = acteursecondaire;
	}
	
}
