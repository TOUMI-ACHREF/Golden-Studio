package entities;

import java.sql.Date;

public class Actor extends UserUI  {
	private double note;//j'ai pas ajouter ce champ dans la db

	public Actor(double note) {
		super();
		this.note = note;
	}
	
	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}

	public Actor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Actor(String username, String email, String password, Date birthDate, String nativeCountry) {
		super(username, email, password, birthDate, nativeCountry);
		// TODO Auto-generated constructor stub
	}
}
