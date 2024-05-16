package services;

import java.io.IOException;
import java.sql.Date;

import dao.ProducerDao;
import entities.Producer;

public class ProducerService {
	public static void add(Producer prod) {
		ProducerDao.add(prod);		
	}
	public static boolean checkProducer(String email)
	{
		return ProducerDao.check(email);
	}
	public static boolean login(String email,String password)
	{
		return ProducerDao.login(email,password);
	}
	public static void storeData(String mail) throws IOException {
		ProducerDao.storeData(mail);		
	}
	public static void update(String mail ,String country, Date date) {
		ProducerDao.update(mail,country,date);		
	}
	public static void ChangePass(String mail ,String pass) {
		ProducerDao.ChangePass(mail,pass);		
	}
}
