package pojo;

public class Airport {

	private String id;
	private String Name;
	private String City;
	private String Country;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	@Override
	public String toString() {
		return "Airport [id=" + id + ", Name=" + Name + ", City=" + City + ", Country=" + Country + "]";
	}
	
	
}
