package pojo;

import java.util.Date;

public class Reservation {

	private int ReservationNumber;
	private Date DateOfTravel;
	private int BookingFee;
	private String FareRestriction;
	private int Passengers;
	private int TotalFare;
	private int CustomerAcc;
	private int CustomerRepresentative;
	public int getReservationNumber() {
		return ReservationNumber;
	}
	public void setReservationNumber(int reservationNumber) {
		ReservationNumber = reservationNumber;
	}
	public Date getDateOfTravel() {
		return DateOfTravel;
	}
	public void setDateOfTravel(Date dateOfTravel) {
		DateOfTravel = dateOfTravel;
	}
	public int getBookingFee() {
		return BookingFee;
	}
	public void setBookingFee(int bookingFee) {
		BookingFee = bookingFee;
	}
	public String getFareRestriction() {
		return FareRestriction;
	}
	public void setFareRestriction(String fareRestriction) {
		FareRestriction = fareRestriction;
	}
	public int getPassengers() {
		return Passengers;
	}
	public void setPassengers(int passengers) {
		Passengers = passengers;
	}
	public int getTotalFare() {
		return TotalFare;
	}
	public void setTotalFare(int totalFare) {
		TotalFare = totalFare;
	}
	public int getCustomerAcc() {
		return CustomerAcc;
	}
	public void setCustomerAcc(int customerAcc) {
		CustomerAcc = customerAcc;
	}
	public int getCustomerRepresentative() {
		return CustomerRepresentative;
	}
	public void setCustomerRepresentative(int customerRepresentative) {
		CustomerRepresentative = customerRepresentative;
	}
	@Override
	public String toString() {
		return "Reservation [ReservationNumber=" + ReservationNumber + ", DateOfTravel=" + DateOfTravel
				+ ", BookingFee=" + BookingFee + ", FareRestriction=" + FareRestriction + ", Passengers=" + Passengers
				+ ", TotalFare=" + TotalFare + ", CustomerAcc=" + CustomerAcc + ", CustomerRepresentative="
				+ CustomerRepresentative + "]";
	}
	public Reservation(int reservationNumber, Date dateOfTravel, int passengers,
			int totalFare, int customerAcc, int customerRepresentative) {
		super();
		ReservationNumber = reservationNumber;
		DateOfTravel = dateOfTravel;
		Passengers = passengers;
		TotalFare = totalFare;
		CustomerAcc = customerAcc;
		CustomerRepresentative = customerRepresentative;
	}
	public Reservation() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
