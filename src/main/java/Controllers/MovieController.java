package Controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import entities.Movie;
import services.MovieService;

public class MovieController {
	public static void add(Movie media) throws ParseException {
		MovieService.add(media);
	}
	
	public static void delete(int id) throws ParseException {
		MovieService.delete(id);
	}
	public static void update(int id,Movie media ) throws ParseException, SQLException {
		MovieService.update(id, media);
	}
	public static List<Movie> getAllMovies() throws ParseException, SQLException {
		return MovieService.getAllMovies();
	}
	public static void updateNote(String name ,double newNote) {
		MovieService.updateNote(name ,newNote);
	}
	public static Movie getMovie(int id) throws ParseException, SQLException {
		return MovieService.getMovie(id);
	}
}
