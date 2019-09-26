package application;

public class resData {
	private String name;
	private String street;
	private String delivery;
	private String reviews;
	private String cost;
	private String hours;
	private int id;
	
	public resData() {
		this.name = "";
		this.street = "";
		this.delivery = "No";
		this.reviews = "0";
		this.cost = "0";
		this.hours = "";
		this.id = 0;
	}
	
	public resData(String name, String street, String delivery, String reviews, String cost, String hours) {
		this.id = 0;
		
		this.name = name;
		this.street = street;
		this.delivery = delivery;
		this.reviews = reviews;
		this.cost = cost;
		this.hours = hours;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
