package services;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import dao.MovieDao;
import entities.Movie;

public class MovieService {
	public static void add(Movie movie) throws ParseException {
		MovieDao.add(movie);		
	}
	public static void delete(int id) throws ParseException {
		MovieDao.delete(id);		
	}
	public static void update(int id,Movie movie) throws ParseException, SQLException {
		MovieDao.update(id, movie);		
	}
	public static List<Movie> getAllMovies() {
		return MovieDao.getAllMovies();
	}
	public static void updateNote(String name ,double newNote) {
		MovieDao.updateNote( name ,newNote);
		
	}
	public static Movie getMovie(int id) throws SQLException {
		return MovieDao.getMovie(id);
	}
}
