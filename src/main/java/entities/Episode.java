package entities;

import java.sql.Date;

public class Episode {
	private int num;
	private Date dateDiff;
	private String stream;
	private int id_serie;
	private int id_season;

	public Episode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Episode(int num,String stream,Date dateDiff, int id_serie, int id_season) {
		super();
		this.num=num;
		this.stream=stream;
		this.dateDiff = dateDiff;
		this.id_serie = id_serie;
		this.id_season = id_season;
	}

	public int getNum() {
		return num;
	}
	

	public void setNum(int num) {
		this.num = num;
	}
	

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public Date getDateDiff() {
		return dateDiff;
	}

	public void setDateDiff(Date dateDiff) {
		this.dateDiff = dateDiff;
	}

	public int getId_serie() {
		return id_serie;
	}

	public void setId_serie(int id_serie) {
		this.id_serie = id_serie;
	}

	public int getId_season() {
		return id_season;
	}

	public void setId_season(int id_season) {
		this.id_season = id_season;
	}

	
}
