package services;

import java.io.IOException;
import java.sql.Date;

import dao.AdminDao;
import entities.Admin;

public class AdminService {
	public static void add(Admin admin) {
		AdminDao.add(admin);		
	}

	public static boolean checkAdmin(String email)
	{
		return AdminDao.check(email);
	}
	public static boolean login(String email,String password)
	{
		return AdminDao.login(email,password);
	}
	public static void storeData(String mail) throws IOException {
		AdminDao.storeData(mail);		
	}

	public static void update(String mail ,String name,String country, Date date) {
		AdminDao.update(mail,name,country,date);		
	}
	public static void ChangePass(String mail ,String pass) {
		AdminDao.ChangePass(mail,pass);		
	}
}
