package Controllers;

import java.io.IOException;
import java.sql.Date;

import entities.Admin;
import services.AdminService;

public class AdminController {
	public static void add(Admin admin) {
		AdminService.add(admin);
	}
	public static boolean check(String email)
	{
		return AdminService.checkAdmin(email);
	}

	public static boolean login(String email,String password)
	{
		return AdminService.login(email,password);
	}	
	public static void storeData(String mail) throws IOException
	{
		AdminService.storeData(mail);
	}
	

	public static void update(String mail ,String name,String country, Date date) {
		AdminService.update(mail,name,country,date);		
	}
	public static void ChangePass(String mail ,String pass) {
		AdminService.ChangePass(mail,pass);		
	}
}
