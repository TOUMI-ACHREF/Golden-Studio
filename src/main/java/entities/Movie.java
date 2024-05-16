package entities;

import java.sql.Date;

public class Movie extends Media {
	private String MovieURL;
	
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movie(String name, String producer, Date year, String language, String country, String poster,
			String trailer, String description, String genre, double note,String movieURL) {
		super(name, producer, year, language, country, poster, trailer, description, genre, note);
		this.MovieURL = movieURL;
	}

	public String getMovieURL() {
		return MovieURL;
	}

	public void setMovieURL(String movieURL) {
		MovieURL = movieURL;
	}

	

	
}
