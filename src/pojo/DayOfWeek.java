package pojo;

public class DayOfWeek {

	private int FlightNumber;
	private String DayOfWeek;
	
	public int getFlightNumber() {
		return FlightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		FlightNumber = flightNumber;
	}
	public String getDayOfWeek() {
		return DayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		DayOfWeek = dayOfWeek;
	}
	@Override
	public String toString() {
		return "DayOfWeek [FlightNumber=" + FlightNumber + ", DayOfWeek=" + DayOfWeek + "]";
	}
	
	
}
