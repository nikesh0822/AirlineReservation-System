package pojo;

public class Flight {

	private int FlightNumber;
	private int Seats;
	private String FareRestriction;
	private int Fare;
	private String Id;
	
	public int getFlightNumber() {
		return FlightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		FlightNumber = flightNumber;
	}
	public int getSeats() {
		return Seats;
	}
	public void setSeats(int seats) {
		Seats = seats;
	}
	public String getFareRestriction() {
		return FareRestriction;
	}
	public void setFareRestriction(String fareRestriction) {
		FareRestriction = fareRestriction;
	}
	public int getFare() {
		return Fare;
	}
	public void setFare(int fare) {
		Fare = fare;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	@Override
	public String toString() {
		return "Flight [FlightNumber=" + FlightNumber + ", Seats=" + Seats + ", FareRestriction=" + FareRestriction
				+ ", Fare=" + Fare + ", Id=" + Id + "]";
	}
	
	
}
