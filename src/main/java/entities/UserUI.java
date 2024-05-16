package entities;

import java.sql.Date;

public class UserUI {
	protected String Username;
	protected String Email;
	protected String password;
	protected Date birthDate;
	protected String nativeCountry;
	//account type on peut le distingué avec instence of .
	
	//constructeur
	public UserUI() {
		super();
	}
	
	public UserUI(String username, String email, String password, Date birthDate, String nativeCountry) {
		super();
		this.Username = username;
		this.Email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.nativeCountry = nativeCountry;
	}

	
	//getters and setters
	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getNativeCountry() {
		return nativeCountry;
	}

	public void setNativeCountry(String nativeCountry) {
		this.nativeCountry = nativeCountry;
	}

	@Override
	public String toString() {
		return "[Username=" + Username + ", Email=" + Email + ", password=" + password + ", birthDate="
				+ birthDate + ", nativeCountry=" + nativeCountry + "]";
	}
	
}
