package Controllers;

import java.io.IOException;
import java.sql.Date;

import entities.Actor;
import services.ActorService;

public class ActorController {
	public static void add(Actor actor) {
		ActorService.add(actor);
	}
	public static boolean check(String email)
	{
		return ActorService.checkActor(email);
	}

	public static boolean login(String email,String password)
	{
		return ActorService.login(email,password);
	}
	public static void storeData(String mail) throws IOException
	{
		ActorService.storeData(mail);
	}
	public static void update(String mail ,String name,String country, Date date) {
		ActorService.update(mail,name,country,date);		
	}
	public static void ChangePass(String mail ,String pass) {
		ActorService.ChangePass(mail,pass);		
	}
/*
	@Override
	public void delete(Actor user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Actor oldUser, Actor newUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getListActors() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
}
