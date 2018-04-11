package pojo;

import java.util.Date;

public class Stop {

	
	private int Id;
	private int stopNo;
	public int getStopNo() {
		return stopNo;
	}
	public void setStopNo(int stopNo) {
		this.stopNo = stopNo;
	}
	private Date ArrivalTime;
	private Date DepartureTime;
	private String AirportId;
	private int FlightNumber;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Date getArrivalTime() {
		return ArrivalTime;
	}
	public void setArrivalTime(Date arrivalTime) {
		ArrivalTime = arrivalTime;
	}
	public Date getDepartureTime() {
		return DepartureTime;
	}
	public void setDepartureTime(Date departureTime) {
		DepartureTime = departureTime;
	}
	public String getAirportId() {
		return AirportId;
	}
	public void setAirportId(String airportId) {
		AirportId = airportId;
	}
	public int getFlightNumber() {
		return FlightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		FlightNumber = flightNumber;
	}
	@Override
	public String toString() {
		return "Stop [Id=" + Id + ", ArrivalTime=" + ArrivalTime + ", DepartureTime=" + DepartureTime + ", AirportId="
				+ AirportId + ", FlightNumber=" + FlightNumber + "]";
	}
	
	
}
