package entities;

import java.sql.Date;

public class Media {
	private int id;//ajoute par iheb
	private String name;
	private String producer;
	private Date year;
	private String language;
	private String country;
	/**URl d'une image affiche*/
	private String poster; 
	private String trailer; //URL d'un video trailer
	private String description;
	private String genre;
	private double note;
	
	public Media() {
		super();
	}

	//constructor with id
	public Media(int id, String name, String producer, Date year, String language, String country, String poster,
			String trailer, String description, String genre, double note) {
		super();
		this.id = id;
		this.name = name;
		this.producer = producer;
		this.year = year;
		this.language = language;
		this.country = country;
		this.poster = poster;
		this.trailer = trailer;
		this.description = description;
		this.genre = genre;
		this.note = note;
	}

	//constructor without id
	public Media(String name, String producer, Date year, String language, String country, String poster,
			String trailer, String description, String genre, double note) {
		super();
		this.name = name;
		this.producer = producer;
		this.year = year;
		this.language = language;
		this.country = country;
		this.poster = poster;
		this.trailer = trailer;
		this.description = description;
		this.genre = genre;
		this.note = note;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}
}
