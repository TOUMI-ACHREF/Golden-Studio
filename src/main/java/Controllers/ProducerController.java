package Controllers;

import java.io.IOException;
import java.sql.Date;

import entities.Producer;
import services.ProducerService;

public class ProducerController {
	public static void add(Producer prod) {
		ProducerService.add(prod);
	}
	public static boolean check(String email)
	{
		return ProducerService.checkProducer(email);
	}

	public static boolean login(String email,String password)
	{
		return ProducerService.login(email,password);
	}
	public static void storeData(String mail) throws IOException
	{
		ProducerService.storeData(mail);
	}
	public static void update(String mail,String country, Date date) {
		ProducerService.update(mail,country,date);		
	}
	public static void ChangePass(String mail ,String pass) {
		ProducerService.ChangePass(mail,pass);		
	}
}
