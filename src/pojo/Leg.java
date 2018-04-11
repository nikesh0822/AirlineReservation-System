package pojo;

public class Leg {

	private int Id;
	private int StopId1;
	private int StopId2;
	private int FlightNumber;
	private int ReservationNumber;
	private String SeatNumber;
	private String SpecialMeal;
	private String SeatClass;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
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
	public int getFlightNumber() {
		return FlightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		FlightNumber = flightNumber;
	}
	public int getReservationNumber() {
		return ReservationNumber;
	}
	public void setReservationNumber(int reservationNumber) {
		ReservationNumber = reservationNumber;
	}
	public String getSeatNumber() {
		return SeatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		SeatNumber = seatNumber;
	}
	public String getSpecialMeal() {
		return SpecialMeal;
	}
	public void setSpecialMeal(String specialMeal) {
		SpecialMeal = specialMeal;
	}
	public String getSeatClass() {
		return SeatClass;
	}
	public void setSeatClass(String seatClass) {
		SeatClass = seatClass;
	}
	@Override
	public String toString() {
		return "Leg [Id=" + Id + ", StopId1=" + StopId1 + ", StopId2=" + StopId2 + ", FlightNumber=" + FlightNumber
				+ ", ReservationNumber=" + ReservationNumber + ", SeatNumber=" + SeatNumber + ", SpecialMeal="
				+ SpecialMeal + ", SeatClass=" + SeatClass + "]";
	}
	
	
}
