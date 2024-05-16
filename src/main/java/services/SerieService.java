package services;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import dao.SerieDao;
import entities.Serie;

public class SerieService {
	public static void add(Serie serie) throws ParseException {
		SerieDao.add(serie);		
	}
	public static void delete(int id) throws ParseException {
		SerieDao.delete(id);		
	}
	public static void update(int id, Serie serie) throws ParseException, SQLException {
		SerieDao.update(id, serie);		
	}
	
	public static List<Serie> getAllSerie(){
		return SerieDao.getAllSeries();
		
	}
	
	public static List<Serie> getBestSerie(){
		return SerieDao.getBestSerie();
		
	}
	
	public static List<Serie> getAllSeriesWithId(){
		return SerieDao.getAllSeriesWithId();
	}
	
	
}
