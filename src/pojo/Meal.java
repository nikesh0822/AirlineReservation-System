package pojo;

public class Meal {

	private String Name;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String toString() {
		return "Meal [Name=" + Name + "]";
	}
	
	
	
}
