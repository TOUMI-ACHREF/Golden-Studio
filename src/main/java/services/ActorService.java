package services;

import java.io.IOException;
import java.sql.Date;

import dao.ActorDao;
import entities.Actor;

public class ActorService {
	public static void add(Actor actor) {
		ActorDao.add(actor);		
	}

	public static boolean checkActor(String email)
	{
		return ActorDao.check(email);
	}

	public static boolean login(String email,String password)
	{
		return ActorDao.login(email,password);
	}
	public static void storeData(String mail) throws IOException {
		ActorDao.storeData(mail);		
	}

	public static void update(String mail ,String name,String country, Date date) {
		ActorDao.update(mail,name,country,date);		
	}
	public static void ChangePass(String mail ,String pass) {
		ActorDao.ChangePass(mail,pass);		
	}
}
