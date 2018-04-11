package pojo;

public class FDisplay {

	private int FlightNumber;
	private String AirlineName;
	private int Stops;
	private int Seats;
	private String RouteString;
	private int price;
	
	public FDisplay(int flightNumber, String airlineName, int stops, String routeString, int Seats, int price) {
		super();
		FlightNumber = flightNumber;
		AirlineName = airlineName;
		this.Stops = stops;
		this.RouteString = routeString;
		this.Seats = Seats;
		this.price = price;
	}
	public FDisplay(int flightNumber, String airlineName, int seats) {
		FlightNumber = flightNumber;
		AirlineName = airlineName;
		Seats = seats;
		
	}
	public int getFlightNumber() {
		return FlightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		FlightNumber = flightNumber;
	}

	public String getAirlineName() {
		return AirlineName;
	}

	public void setAirlineName(String airlineName) {
		AirlineName = airlineName;
	}

	public int getStops() {
		return Stops;
	}

	public void setStops(int stops) {
		Stops = stops;
	}

	public int getSeats() {
		return Seats;
	}

	public void setSeats(int seats) {
		Seats = seats;
	}

	public String getRouteString() {
		return RouteString;
	}

	public void setRouteString(String routeString) {
		RouteString = routeString;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}