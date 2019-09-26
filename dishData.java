package application;

public class dishData {
	private String name;
	private String res_name;
	private String price;
	private String star;
	private int id;
	
	public dishData() {
		this.name = "";
		this.res_name = "";
		this.price = "";
		this.star = "";
		this.id = 0;
	}
	
	public dishData(String name, String res_name, String price, String star) {
		this.name = name;
		this.res_name = res_name;
		this.price = price;
		this.star = star;
		this.id = 0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRes_name() {
		return res_name;
	}
	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
