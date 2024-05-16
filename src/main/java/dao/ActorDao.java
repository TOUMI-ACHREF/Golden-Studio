package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openjfx.GoldenStudio.App;

import entities.Actor;
import utils.ConnectionDataBase;

public class ActorDao {
private static Connection connection = ConnectionDataBase.getInstance();
	
	public static void add(Actor actor) {
		PreparedStatement pstmt = null;
		// ResultSet rs = null;

		try {
			String sql = "INSERT INTO Actors (actor_name, email, origin, birth_date, password) VALUES (?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, actor.getUsername());
			pstmt.setString(2, actor.getEmail());
			pstmt.setString(3, actor.getNativeCountry());
			pstmt.setDate(4, actor.getBirthDate());
			pstmt.setString(5, actor.getPassword());
			
			pstmt.executeUpdate();
			try {
				storeData(actor.getEmail());
			} catch (IOException e) {	
				e.printStackTrace();
			}
			System.out.println("Actor added !!");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}


	}
	public static boolean check(String email) {
		PreparedStatement pstmt = null;
		// ResultSet rs = null;

		String sql = "select * from actors where email='"+email+"'";
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			return rs.next();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	public static boolean login(String email,String password) {
		PreparedStatement pstmt = null;
		// ResultSet rs = null;

		String sql = "select * from actors where email='"+email+"' and password ='"+password+"'";
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs= pstmt.executeQuery(sql);
			return rs.next();
			//System.out.println("user Exist");
			
		}catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}



	}
	public static void storeData(String email) throws IOException {
		PreparedStatement pstmt = null;
		
		String path =App.class.getResource("").toString();
		String[] paths=path.split("/");
	    String finalpath=paths[1];
	    int i=2;
	    while(i<paths.length-1&&!paths[i].equalsIgnoreCase("GoldenStudio"))
	    {
	    	
	    	finalpath+="\\"+paths[i];
	    	i++;
	    }
		finalpath+="\\"+paths[i];
		File tmp = new File(finalpath+"\\library\\Cache\\cache.txt");
		tmp.createNewFile();
		FileWriter data = new FileWriter(tmp);
		
		String sql = "select * from Actors where email='"+email+"'";
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next())
			{
				
				String name =rs.getString(2);
				String mail=rs.getString(3);
				String country=rs.getString(4);
				Date date=rs.getDate(5);
				String type="ACTOR";
				data.write(name + "/" + mail + "/" + country+"/"+date+"/"+type);
				 data.close();
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void update(String mail ,String name,String country, Date date) {
		PreparedStatement pstmt = null;
		// ResultSet rs = null;

		try {
			String sql = "Update actors Set actor_name=?, origin=?, birth_date=? Where email='"+mail+"'";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.setString(2, country);
			pstmt.setDate(3, date);

			pstmt.executeUpdate();
			try {
				storeData(mail);
			} catch (IOException e) {	
				e.printStackTrace();
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	public static void ChangePass(String mail,String pass)
	{
		PreparedStatement pstmt = null;

		try {
			String sql = "Update Actors set password=? Where email='"+mail+"'";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1,pass);

			pstmt.executeUpdate();
			try {
				storeData(mail);
			} catch (IOException e) {	
				e.printStackTrace();
			}
			System.out.println("User updated !!");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		
	}
}
