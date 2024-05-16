package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


import entities.Movie;
import utils.ConnectionDataBase;

public class MovieDao {
	private static Connection connection = ConnectionDataBase.getInstance();

	public static void add(Movie movie) throws ParseException {

		PreparedStatement pstmt = null;

		try {
			String sql = "INSERT INTO movies (name, producer, languge, native_country, poster, trailer, description,genre,date_realease,movie_stream,note) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, movie.getName());

			pstmt.setString(2, movie.getProducer());

			pstmt.setString(3, movie.getLanguage());
			pstmt.setString(4, movie.getCountry());
			pstmt.setString(5, movie.getPoster());
			pstmt.setString(6, movie.getTrailer());
			pstmt.setString(7, movie.getDescription());
			pstmt.setString(8, movie.getGenre());
			pstmt.setDate(9, movie.getYear());
			pstmt.setString(10, movie.getMovieURL());
			pstmt.setDouble(11, movie.getNote());

			pstmt.executeUpdate();
			System.out.println("movie added !!");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}

	public static void delete(int id) throws ParseException {

		PreparedStatement pstmt = null;

		try {
			String sql = "delete from MOVIES where ID_MOVIE =?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("movie supprimé !!");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}

	public static void update(int id, Movie movie) throws SQLException {
		try {
			PreparedStatement pstmt = null;
			String sql = "update MOVIES set name= ?, producer=?, languge=?, native_country=?, poster=?, trailer=?, description=?,genre=?,date_realease=?,movie_stream=?,note=? where ID_MOVIE =?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, movie.getName());
			pstmt.setString(2, movie.getProducer());
			pstmt.setString(3, movie.getLanguage());
			pstmt.setString(4, movie.getCountry());
			pstmt.setString(5, movie.getPoster());
			pstmt.setString(6, movie.getTrailer());
			pstmt.setString(7, movie.getDescription());
			pstmt.setString(8, movie.getGenre());
			pstmt.setDate(9, movie.getYear());
			pstmt.setString(10, movie.getMovieURL());
			pstmt.setDouble(11, movie.getNote());
			pstmt.setInt(12, id);
			pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}

	//methode valide 
	public static List<Movie> getAllMovies() {
		List<Movie> list = new ArrayList<Movie>();
		Movie m = new Movie();
		try {
			Statement stmt = null;
			String sql = "SELECT * FROM MOVIES";
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				String name = rs.getString("name");
				String prod = rs.getString("producer");
				String lan = rs.getString("languge");
				String nc = rs.getString("native_country");
				String pos = rs.getString("poster");
				String tr = rs.getString("trailer");
				String des = rs.getString("description");
				String gen = rs.getString("genre");
				Date dr = rs.getDate("date_realease");
				String ms = rs.getString("movie_stream");
				int note = rs.getInt("note");
				m = new Movie(name ,prod ,dr ,lan ,nc ,pos ,tr ,des ,gen ,note,ms);
				list.add(m);
			}

		} catch (SQLException ex) {
			System.out.println(ex.getSuppressed());
		}
		
		return list;
	}

	public static List<Movie> getBestMovies() {
		ArrayList<Movie> list = new ArrayList<>();
		Movie m = new Movie();

		try {
			Statement stmt = null;
			String sql = "select * from MOVIES";
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				String name = rs.getString("name");
				String prod = rs.getString("producer");
				String lan = rs.getString("languge");
				String nc = rs.getString("native_country");
				String pos = rs.getString("poster");
				String tr = rs.getString("trailer");
				String des = rs.getString("description");
				String gen = rs.getString("genre");
				Date dr = rs.getDate("date_realease");
				String ms = rs.getString("movie_stream");
				int note = rs.getInt("note");

				m = new Movie(name ,prod ,dr ,lan ,nc ,pos ,tr ,des ,gen ,note,ms);
				list.add(m);
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		//Using Streams it's easy to sort this list and get the top 20 
		list.sort(Comparator.comparing(Movie::getNote));
		list.stream().limit(10).collect(Collectors.toList());
		return list;
	}

	public static void updateNote(String name ,double newNote) {
		try {
			PreparedStatement pstmt = null;
			String sql = "update MOVIES set note=? where name =?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setDouble(1,newNote);
			pstmt.setString(2,name);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
	}

	public static Movie getMovie(int id) throws SQLException {
		Statement stmt = null;
		String sql = "SELECT * FROM MOVIES where id_movie = "+id;
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Movie m;	
			String name = rs.getString("name");
			String prod = rs.getString("producer");
			String lan = rs.getString("languge");
			String nc = rs.getString("native_country");
			String pos = rs.getString("poster");
			String tr = rs.getString("trailer");
			String des = rs.getString("description");
			String gen = rs.getString("genre");
			Date dr = rs.getDate("date_realease");
			String ms = rs.getString("movie_stream");
			int note = rs.getInt("note");
			m = new Movie(name ,prod ,dr ,lan ,nc ,pos ,tr ,des ,gen ,note,ms);
			return m;
	
		
	}
}
