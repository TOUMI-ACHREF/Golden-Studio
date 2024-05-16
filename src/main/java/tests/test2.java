package tests;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import dao.RechercheDao;
import entities.Movie;

public class test2 {

	public static void main(String[] args) throws ParseException, SQLException {
		List<Integer> L = RechercheDao.SearchMovie("F");
			System.out.println(L);
	}

}
