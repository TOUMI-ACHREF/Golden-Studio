package services;

import java.util.List;

import dao.RechercheDao;

public class RechercheService {

	public static List<Integer>SearchMovie (String word)
	{	
		return RechercheDao.SearchMovie(word);
	}

	public static List<Integer>SearchSerie (String word)
	{	
		return RechercheDao.SearchSerie(word);
	}
}
