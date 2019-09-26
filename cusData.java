package application;

public class cusData {
	private String reviews;
	private String pictures;
	private String name;
	private int id;
	
	public cusData() {
		this.reviews = "";
		this.pictures = "";
		this.name = "";
		this.id = 0;
	}
	
	public cusData(String name, String pictures, String reviews) {
		this.reviews = reviews;
		this.pictures = pictures;
		this.name = name;
		this.id = 0;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
