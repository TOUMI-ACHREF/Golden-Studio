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

import entities.Media;
import entities.Serie;
import utils.ConnectionDataBase;

public class SerieDao {
	private static Connection connection = ConnectionDataBase.getInstance();
	
	
	

	public static void add(Serie serie) throws ParseException {
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO series (name, producer,year_release ,language, native_country, poster, trailer, description,genre,note) VALUES (?,?,?,?,?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, serie.getName());
		
			pstmt.setString(2, serie.getProducer());
			pstmt.setDate(3, serie.getYear());
			pstmt.setString(4, serie.getLanguage());
			pstmt.setString(5, serie.getCountry());
			pstmt.setString(6, serie.getPoster());
			pstmt.setString(7, serie.getTrailer());
			pstmt.setString(8, serie.getDescription());
			pstmt.setString(9, serie.getGenre());
			pstmt.setDouble(10, serie.getNote());
			
			pstmt.executeUpdate();
			System.out.println("serie added !!");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
	}


	public static void delete(int id) throws ParseException {
		
		PreparedStatement pstmt = null;
		
		try {
			 String sql="delete from SERIES where ID_SERIE =?";
			pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("serie supprimé !!");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}
	public static void update(int id, Serie serie ) throws SQLException {
		try { 
		PreparedStatement pstmt= null;
		String sql="update SERIES set name= ?, producer=?,year_release=?, language=?, native_country=?, poster=?, trailer=?, description=?,genre=?,note=? where ID_SERIE =?";
		pstmt = connection.prepareStatement(sql);
		
		pstmt.setString(1, serie.getName());

		pstmt.setString(2, serie.getProducer());
		pstmt.setDate(3, serie.getYear());
		pstmt.setString(4, serie.getLanguage());
		pstmt.setString(5, serie.getCountry());
		pstmt.setString(6, serie.getPoster());
		pstmt.setString(7, serie.getTrailer());
		pstmt.setString(8, serie.getDescription());
		pstmt.setString(9, serie.getGenre());
		pstmt.setDouble(10, serie.getNote());
		pstmt.setInt(11, id);

		pstmt.executeUpdate();
			System.out.println("serie modifiée !!");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
	}

	public static List<Serie> getAllSeries(){
		List<Serie> list = new ArrayList<>();
		Serie m = new Serie();

		try { 
			Statement stmt= null;
			String sql="select * from SERIES";
			stmt = connection.createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			while (rs.next()){
			        String name = rs.getString("name");
			        String prod = rs.getString("producer");
			        Date dr = rs.getDate("year_release");
			        String lan = rs.getString("language");
			        String nc = rs.getString("native_country");
			        String pos = rs.getString("poster");
			        String tr = rs.getString("trailer");
			        String des = rs.getString("description");
			        String gen = rs.getString("genre");
			        int note = rs.getInt("note");
			      
					m = new Serie(name ,prod ,dr ,lan ,nc ,pos ,tr ,des ,gen ,note);
					list.add(m);
			  }

		}catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
			
			return list;
		}
	
		public static List<Serie> getAllSeriesWithId(){
			List<Serie> list = new ArrayList<>();
			Serie m = new Serie();
	
			try { 
				Statement stmt= null;
				String sql="select * from SERIES";
				stmt = connection.createStatement();
				ResultSet rs= stmt.executeQuery(sql);
				while (rs.next()){
					
						int id = rs.getInt("id_serie");
				        String name = rs.getString("name");
				        String prod = rs.getString("producer");
				        Date dr = rs.getDate("year_release");
				        String lan = rs.getString("language");
				        String nc = rs.getString("native_country");
				        String pos = rs.getString("poster");
				        String tr = rs.getString("trailer");
				        String des = rs.getString("description");
				        String gen = rs.getString("genre");
				        int note = rs.getInt("note");
				       
						m = new Serie(name ,prod ,dr ,lan ,nc ,pos ,tr ,des ,gen ,note,id);
						list.add(m);
				  
				}
	
			}catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			return list;
		}
		
	
		public static List<Serie> getBestSerie(){
			ArrayList<Serie> list = new ArrayList<>();
			Serie m = new Serie(); 
			
			try { 
				PreparedStatement pstmt= null;
				String sql="select * from series";
				pstmt = connection.prepareStatement(sql);
				ResultSet rs= pstmt.executeQuery(sql);
				 while (rs.next())
			      {
				        String name = rs.getString("name");
				        String prod = rs.getString("producer");
				        Date dr = rs.getDate("year_release");
				        String lan = rs.getString("language");
				        String nc = rs.getString("native_country");
				        String pos = rs.getString("poster");
				        String tr = rs.getString("trailer");
				        String des = rs.getString("description");
				        String gen = rs.getString("genre");
				        int note = rs.getInt("note");
				        
				      
				        m.setName(name);
						m.setProducer(prod);
						m.setYear( dr);
						m.setLanguage(lan);
						m.setCountry(nc);
						m.setPoster(pos);
						m.setTrailer(tr);
						m.setDescription(des);
						m.setGenre(gen);
						m.setNote(note);
					list.add(m);
			      }
			
		}catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

			list.sort(Comparator.comparing(Media::getNote));
			list.stream().limit(10)
						 .collect(Collectors.toList());
			return list;
		}
		
		
		public static int verifId(int id ) {

			try { 
				Statement stmt= null;
				String sql="select * from SERIES where id_serie='"+id+"'";
				stmt = connection.createStatement();
				ResultSet rs= stmt.executeQuery(sql);
				if (rs.next())
			      {
			      //System.out.println("exist");
			      return rs.getInt(1);
			      }
			}catch (SQLException ex) {
			    		System.out.println(ex.getMessage());
			      }
			return 0;
			
		}
	

	
}
