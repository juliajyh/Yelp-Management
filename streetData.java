package application;

public class streetData {
	private String name;
	private String traffic;
	
	public streetData() {
		this.name = "";
		this.traffic = "0";
	}
	
	public streetData(String name, String traffic, String rent) {
		this.name = name;
		this.traffic = traffic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
}
