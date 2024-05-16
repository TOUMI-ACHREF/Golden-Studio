package Controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;


import entities.Serie;
import services.SerieService;

public class SerieController {
	public static void add(Serie serie) throws ParseException {
		SerieService.add(serie);		
	}
	public static void delete(int id) throws ParseException {
		SerieService.delete(id);		
	}
	public static void update(int id, Serie serie) throws ParseException, SQLException {
		SerieService.update(id, serie);		
	}
	public static void getBestSeries() {
		SerieService.getBestSerie();
	}
	
	public static List<Serie> getAllSeries() {
		return SerieService.getAllSerie();
	}

	public static List<Serie> getAllSeriesWithId(){
		return SerieService.getAllSeriesWithId();
	}
}
