package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import entities.Episode;
import utils.ConnectionDataBase;

public class EpisodeDao {
	private static Connection connection = ConnectionDataBase.getInstance();
	
	
	

	public static void add(Episode ep) throws ParseException {
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO EPISODES (num_episode,date_release, episode_stream, id_serie,id_season) VALUES (?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, ep.getNum());
			pstmt.setDate(2, ep.getDateDiff());
			pstmt.setString(3, ep.getStream());
			pstmt.setInt(4, ep.getId_serie());
			pstmt.setInt(5, ep.getId_season());
			
			
			
			pstmt.executeUpdate();
			System.out.println("episode added !!");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	public static void delete(int id_serie, int num) throws ParseException {
		
		PreparedStatement pstmt = null;
		
		try {
			 String sql="delete from EPISODES where id_serie =? and num_episode=?  ";
			pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1, id_serie);
			 pstmt.setInt(2, num);
			 if(dao.SerieDao.verifId(id_serie)!=0) {
					pstmt.executeUpdate();
					System.out.println("episode supprimé !!");}
					 else{
						 System.out.println("id n'existe pas");
					 }
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}
	
	public static void update(int num,int id_serie,Date d,String stream ) throws SQLException { // matnajem taamel update ken date w stream !!!
		try { 
		PreparedStatement pstmt= null;
		String sql="update EPISODES set date_release= ? , episode_stream=? where NUM_EPISODE=? and id_serie= ? ";
		pstmt = connection.prepareStatement(sql);
		pstmt.setDate(1,d);
		pstmt.setString(2, stream);
		pstmt.setInt(3, num);
		pstmt.setInt(4, id_serie);
		pstmt.executeUpdate();
		System.out.println("episode modifiée !!");
	} catch (SQLException ex) {
		System.out.println(ex.getMessage());
	}
	}
	
	
	public static List<Episode>getAllEpisode(int id_serie,int num_season){
		List<Episode> list = new ArrayList<>();
		Episode e = new Episode();
		try { 
			Statement stmt= null;
			String sql="select * from EPISODES where id_serie='"+id_serie+"'and id_season='"+num_season+"'";
			stmt = connection.createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			 while (rs.next())
		      {
				 int numEp =rs.getInt("num_episode");
				 Date d=rs.getDate("date_release");
				 String stream = rs.getString("episode_stream");
				 int id_ser = rs.getInt("id_serie");
				 int num = rs.getInt("id_season");
				/* 
				e.setNum(numEp);
				e.setDateDiff(d);
				e.setStream(stream);
				e.setId_serie(id_ser);//tkoun null khir wala matafichihesh wkhw
				e.setId_season(num); //kifkif
				*/
				e = new Episode(num,stream,d, id_serie,  num_season);
				//s.setId_serie(id_serie); // ken theb rajaaha 
				list.add(e);
		      }
		
	}catch (SQLException ex) {
		System.out.println(ex.getMessage());
	}
		return list;
}	
}
