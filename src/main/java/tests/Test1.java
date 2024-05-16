package tests;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import entities.Serie;

public class Test1 {
	public static void main(String[] args) throws ParseException, SQLException {
		String sDate1="31/12/1998";
		 SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		 java.util.Date date = sdf1.parse(sDate1);
		 java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		//String sDate2="12/12/2000";
		// java.util.Date date2 = sdf1.parse(sDate1);
		 java.sql.Date sqlDate2 = new java.sql.Date(sqlDate.getTime());
		//Producer p = new Producer("JASSER","achref@gmail.com","ii",sqlDate,"tunisia");
		Serie m = new Serie();
		m.setName(" believe on love !! ");
		//m.setProducer(p);
		m.setYear( sqlDate2);
		m.setLanguage("indian");
		m.setCountry("indonisia");
		m.setPoster("url");
		m.setTrailer("url");
		m.setDescription("the best one");
		m.setGenre("funny");
		m.setNote(5);
		/*Controllers.MovieController.add(m);
				
		System.out.println(m.toString());*/
		//Controllers.MovieController.delete(1);
		//Controllers.MovieController.update(2, m);
		//dao.SerieDao.add(m);
		//Controllers.SerieDao.delete(2); 
		//Controllers.SerieDao.update(1, m);
		dao.MovieDao.getAllMovies();
		dao.MovieDao.getBestMovies();
	}
}
