package entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Producer extends UserUI {
	public Producer(String username, String email, String password, Date birthDate, String nativeCountry) {
		super(username, email, password, birthDate, nativeCountry);
		// TODO Auto-generated constructor stub
	}

	//lors d'une ajout d'un produit ,ce dernier se rajoute dans cette liste 
	//table dans la db qui gere cette liste sont (MOVIES_ADDED / SERIES_ADDED)
	private List<Media> products;

	public Producer() {
		super();
		this.products = new ArrayList<> ();
	}

	//setter and getter
	public List<Media> getProducts() {
		return products;
	}

	public void setProducts(List<Media> products) {
		this.products = products;
	}

}
