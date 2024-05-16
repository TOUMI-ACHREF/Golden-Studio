package services;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import dao.SeasonDao;
import entities.Season;

public class SeasonService {
	
		public static void add(Season s) throws ParseException {
			SeasonDao.add(s);		
		}
		public static void delete(int id_serie, int num) throws ParseException {
			SeasonDao.delete(id_serie,num);		
		}
		public static void update(int num,int id_serie,String synop ) throws ParseException, SQLException {
			SeasonDao.update(num, id_serie,synop);		
		}
		
		public static List<Season>getAllSeason(int id_serie){
			return SeasonDao.getAllSeason(id_serie);
			
		}
}
