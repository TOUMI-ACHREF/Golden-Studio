package Controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import entities.Episode;
import services.EpisodeService;

public class EpisodeController {
	public static void add(Episode s) throws ParseException {
		EpisodeService.add(s);		
	}
	public static void delete(int id_serie, int num) throws ParseException {
		EpisodeService.delete(id_serie,num);		
	}
	public static void update(int num,int id_serie,Date d,String stream ) throws ParseException, SQLException {
		EpisodeService.update(num, id_serie,d,stream);		
	}
	
	public static List<Episode>getAllEpisode(int id_serie,int num_season){
		return EpisodeService.getAllEpisode(id_serie,num_season);
		
	}
}
