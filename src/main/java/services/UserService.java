package services;


import java.io.IOException;
import java.sql.Date;

import dao.UserDao;
import entities.User;

public class UserService {
	
	public static void add(User user) {
		UserDao.add(user);		
	}

	public static boolean checkuser(String email)
	{
		return UserDao.check(email);
	}
	public static boolean login(String email,String password)
	{
		return UserDao.login(email,password);
	}
	public static void storeData(String mail) throws IOException {
		UserDao.storeData(mail);		
	}
	public static void update(String mail ,String name,String country, Date date) {
		UserDao.update(mail,name,country,date);		
	}
	public static void ChangePass(String mail ,String pass) {
		UserDao.ChangePass(mail,pass);		
	}
}
