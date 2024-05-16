package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import entities.Season;
import utils.ConnectionDataBase;

public class SeasonDao {
	private static Connection connection = ConnectionDataBase.getInstance();
	
	
	

	public static void add(Season season) throws ParseException {
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO SEASONS (num_season,synopsis, id_serie) VALUES (?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, season.getNum());
			pstmt.setString(2, season.getTrailer());
			pstmt.setInt(3, season.getId_serie());
			
			
			pstmt.executeUpdate();
			System.out.println("season added !!");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	public static void delete(int id_serie, int num) throws ParseException {
		
		PreparedStatement pstmt = null;
		
		try {
			 String sql="delete from SEASONS where id_serie =? and num_season=?";
			pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1, id_serie);
			 pstmt.setInt(2, num);
			 if(dao.SerieDao.verifId(id_serie)!=0) {
					pstmt.executeUpdate();
					System.out.println("season supprimé !!");}
					 else{
						 System.out.println("id n'existe pas");
					 }
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}
	/*public static int verifId(int id ) {

		try { 
			PreparedStatement pstmt= null;
			String sql="select * from season where num_season='"+id+"'";
			pstmt = connection.prepareStatement(sql);
			ResultSet rs= pstmt.executeQuery(sql);
			if (rs.next())
		      {
		     // System.out.println("exist");
		      return rs.getInt(1);
		      }
		}catch (SQLException ex) {
		    		System.out.println(ex.getMessage());
		      }
		return 0;
		
		
	}*/
	public static void update(int num,int id_serie,String synop ) throws SQLException { // matnajem taamel update ken li synopsis !!!
		try { 
		PreparedStatement pstmt= null;
		String sql="update SEASONS set synopsis= ? where NUM_SEASON =? and id_serie= ? ";
		pstmt = connection.prepareStatement(sql);
		pstmt.setString(1,synop);
		pstmt.setInt(2, num);
		pstmt.setInt(3, id_serie);
		pstmt.executeUpdate();
		System.out.println("season modifiée !!");
	} catch (SQLException ex) {
		System.out.println(ex.getMessage());
	}
	}
	
	
	public static List<Season>getAllSeason(int id_serie){
		ArrayList<Season> list = new ArrayList<Season>();
		Season s = new Season();
		try { 
			Statement stmt= null;
			String sql="select * from SEASONS where id_serie='"+id_serie+"'";
			stmt = connection.createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			while (rs.next())
		    {
				 int numSeason =rs.getInt("num_season");
				 String tr=rs.getString("synopsis");
				//s.setId_serie(id_serie); // ken theb rajaaha 
				s=new Season(numSeason,tr,id_serie);
				list.add(s);
		    }
			//return null;
		
		}catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return list;
}	
}
