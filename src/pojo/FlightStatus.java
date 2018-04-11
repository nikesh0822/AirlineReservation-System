package pojo;

import java.sql.Time;
import java.util.Date;

public class FlightStatus {
   private int FlightNumber;
   private Time DepartureDelay;
   private Time ArrivalDelay;
   private int StopNo;
   private Date DateOfTravel;
public int getFlightNumber() {
	return FlightNumber;
}
public void setFlightNumber(int flightNumber) {
	FlightNumber = flightNumber;
}
public Time getDepartureDelay() {
	return DepartureDelay;
}
public void setDepartureDelay(Time departureDelay) {
	DepartureDelay = departureDelay;
}
public Time getArrivalDelay() {
	return ArrivalDelay;
}
public void setArrivalDelay(Time arrivalDelay) {
	ArrivalDelay = arrivalDelay;
}
public int getStopNo() {
	return StopNo;
}
public void setStopNo(int stopNo) {
	StopNo = stopNo;
}
public Date getDateOfTravel() {
	return DateOfTravel;
}
public void setDateOfTravel(Date dateOfTravel) {
	DateOfTravel = dateOfTravel;
}
public FlightStatus(int flightNumber, Time departureDelay, Time arrivalDelay, int stopNo, Date dateOfTravel) {
	super();
	FlightNumber = flightNumber;
	DepartureDelay = departureDelay;
	ArrivalDelay = arrivalDelay;
	StopNo = stopNo;
	DateOfTravel = dateOfTravel;
}
   
   
}
