package pojo;

import java.sql.Time;
import java.util.Date;

public class FDetailsDisplay {

	private int objectId;
	private int StopId1;
	private int StopId2;
	private int StopId3;
	private int StopId4;
	private int StopId5;
	private int StopId6;
	private int StopId7;
	private int StopId8;
	private String AirlineName1;
	private String AirlineName2;
	private String AirlineName3;
	private String AirlineName4;
	private int FLightNumber1;
	private int FLightNumber2;
	private int FLightNumber3;
	private int FLightNumber4;
	private Time DepartureTime;
	private Time ArrivalTime;
	private Time secDepartureTime;
	private Time secArrivalTime;
	private Time thirdDepartureTime;
	private Time thirdArrivalTime;
	private Time fourthDepartureTime;
	private Time fourthArrivalTime;
	private int Fare;
	private Date dateFlight;
	
	
	public Date getDateFlight() {
		return dateFlight;
	}
	public void setDateFlight(Date dateFlight) {
		this.dateFlight = dateFlight;
	}
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public int getStopId7() {
		return StopId7;
	}
	public void setStopId7(int stopId7) {
		StopId7 = stopId7;
	}
	public int getStopId8() {
		return StopId8;
	}
	public void setStopId8(int stopId8) {
		StopId8 = stopId8;
	}
	public String getAirlineName4() {
		return AirlineName4;
	}
	public void setAirlineName4(String airlineName4) {
		AirlineName4 = airlineName4;
	}
	public int getFLightNumber4() {
		return FLightNumber4;
	}
	public void setFLightNumber4(int fLightNumber4) {
		FLightNumber4 = fLightNumber4;
	}
	public Time getFourthDepartureTime() {
		return fourthDepartureTime;
	}
	public void setFourthDepartureTime(Time fourthDepartureTime) {
		this.fourthDepartureTime = fourthDepartureTime;
	}
	public Time getFourthArrivalTime() {
		return fourthArrivalTime;
	}
	public void setFourthArrivalTime(Time fourthArrivalTime) {
		this.fourthArrivalTime = fourthArrivalTime;
	}
	public int getStopId1() {
		return StopId1;
	}
	public void setStopId1(int stopId1) {
		StopId1 = stopId1;
	}
	public int getStopId2() {
		return StopId2;
	}
	public void setStopId2(int stopId2) {
		StopId2 = stopId2;
	}
	public int getStopId3() {
		return StopId3;
	}
	public void setStopId3(int stopId3) {
		StopId3 = stopId3;
	}
	public int getStopId4() {
		return StopId4;
	}
	public void setStopId4(int stopId4) {
		StopId4 = stopId4;
	}
	public int getStopId5() {
		return StopId5;
	}
	public void setStopId5(int stopId5) {
		StopId5 = stopId5;
	}
	public int getStopId6() {
		return StopId6;
	}
	public void setStopId6(int stopId6) {
		StopId6 = stopId6;
	}
	public int getFLightNumber1() {
		return FLightNumber1;
	}
	public void setFLightNumber1(int fLightNumber1) {
		FLightNumber1 = fLightNumber1;
	}
	public int getFLightNumber2() {
		return FLightNumber2;
	}
	public void setFLightNumber2(int fLightNumber2) {
		FLightNumber2 = fLightNumber2;
	}
	public int getFLightNumber3() {
		return FLightNumber3;
	}
	public void setFLightNumber3(int fLightNumber3) {
		FLightNumber3 = fLightNumber3;
	}
	public Time getSecDepartureTime() {
		return secDepartureTime;
	}
	public void setSecDepartureTime(Time secDepartureTime) {
		this.secDepartureTime = secDepartureTime;
	}
	public Time getSecArrivalTime() {
		return secArrivalTime;
	}
	public void setSecArrivalTime(Time secArrivalTime) {
		this.secArrivalTime = secArrivalTime;
	}
	public Time getThirdDepartureTime() {
		return thirdDepartureTime;
	}
	public void setThirdDepartureTime(Time thirdDepartureTime) {
		this.thirdDepartureTime = thirdDepartureTime;
	}
	public Time getThirdArrivalTime() {
		return thirdArrivalTime;
	}
	public void setThirdArrivalTime(Time thirdArrivalTime) {
		this.thirdArrivalTime = thirdArrivalTime;
	}
	
	public String getAirlineName1() {
		return AirlineName1;
	}
	public void setAirlineName1(String airlineName1) {
		AirlineName1 = airlineName1;
	}
	public String getAirlineName2() {
		return AirlineName2;
	}
	public void setAirlineName2(String airlineName2) {
		AirlineName2 = airlineName2;
	}
	public String getAirlineName3() {
		return AirlineName3;
	}
	public void setAirlineName3(String airlineName3) {
		AirlineName3 = airlineName3;
	}
	public Time getDepartureTime() {
		return DepartureTime;
	}
	public void setDepartureTime(Time departureTime) {
		DepartureTime = departureTime;
	}
	public Time getArrivalTime() {
		return ArrivalTime;
	}
	public void setArrivalTime(Time arrivalTime) {
		ArrivalTime = arrivalTime;
	}
	public int getFare() {
		return Fare;
	}
	public void setFare(int fare) {
		Fare = fare;
	}
	
	public String toString()
	{
		return FLightNumber1 + " " + StopId1;
	}
	
}
