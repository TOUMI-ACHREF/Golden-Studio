package entities;

public class Season {
	private int num;
	private String trailer;
	private int id_serie;
	
	public Season(int num,String trailer,int id_serie) {
		super();
		this.num=num;
		this.trailer=trailer;
		this.id_serie=id_serie;
	}

	public Season() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public int getId_serie() {
		return id_serie;
	}

	public void setId_serie(int id_serie) {
		this.id_serie = id_serie;
	}

	
}
