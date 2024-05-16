package services;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import dao.EpisodeDao;
import entities.Episode;

public class EpisodeService {
	public static void add(Episode s) throws ParseException {
		EpisodeDao.add(s);		
	}
	public static void delete(int id_serie, int num) throws ParseException {
		EpisodeDao.delete(id_serie,num);		
	}
	public static void update(int num,int id_serie,Date d,String stream ) throws ParseException, SQLException {
		EpisodeDao.update(num, id_serie,d,stream);		
	}
	
	public static List<Episode>getAllEpisode(int id_serie,int num_season){
		return EpisodeDao.getAllEpisode(id_serie,num_season);
		
	}
}

