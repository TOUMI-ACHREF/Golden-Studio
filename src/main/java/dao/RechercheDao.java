package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import utils.ConnectionDataBase;

public class RechercheDao {

	private static Connection connection = ConnectionDataBase.getInstance();

	public static List<Integer> SearchMovie(String word) {
		Statement stmt = null;
		// ResultSet rs = null;
		List<Integer> list=null;

		String sql = "select * from Movies ";
		String name;
		String producer;
		try {
			stmt = connection.createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next())
			{
				name = rs.getString("NAME");
				producer = rs.getString("PRODUCER");
				if(name.toLowerCase().indexOf(word.toLowerCase()) > -1 || producer.toLowerCase().indexOf(word.toLowerCase()) > -1)
				{
					list.add(rs.getInt(0));
				}
			}
		
		}catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return list;
	}

	public static  List<Integer> SearchSerie(String word) {
		PreparedStatement pstmt = null;
		// ResultSet rs = null;
		List<Integer> list= Arrays.asList(null);

		String sql = "select * from Series ";
		String name;
		String producer;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs= pstmt.executeQuery(sql);
			while(rs.next())
			{
				name = rs.getString("NAME");
				producer = rs.getString("PRODUCER");
				if(name.indexOf(word)>-1||producer.indexOf(word)>-1)
				{
					list.add(rs.getInt(0));
				}
				
			}
		
		}catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}
}
