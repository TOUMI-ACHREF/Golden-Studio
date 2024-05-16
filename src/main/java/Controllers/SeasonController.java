package Controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import entities.Season;
import services.SeasonService;

public class SeasonController {
	public static void add(Season s) throws ParseException {
		SeasonService.add(s);		
	}
	public static void delete(int id_serie, int num) throws ParseException {
		SeasonService.delete(id_serie,num);		
	}
	public static void update(int num,int id_serie,String synop ) throws ParseException, SQLException {
		SeasonService.update(num, id_serie,synop);		
	}
	
	public static List<Season>getAllSeason(int id_serie){
		return SeasonService.getAllSeason(id_serie);
		
	}
}

