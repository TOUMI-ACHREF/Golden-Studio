package Controllers;

import java.io.IOException;
import java.sql.Date;

import entities.User;
import services.UserService;

public class UserController {


	public static void add(User user) {
		UserService.add(user);
	}
	public static boolean check(String email)
	{
		return UserService.checkuser(email);
	}

	public static boolean login(String email,String password)
	{
		return UserService.login(email,password);
	}
	public static void storeData(String mail) throws IOException
	{
		UserService.storeData(mail);
	}
	public static void update(String mail ,String name,String country, Date date) {
		UserService.update(mail,name,country,date);		
	}
	public static void ChangePass(String mail ,String pass) {
		UserService.ChangePass(mail,pass);		
	}
}
