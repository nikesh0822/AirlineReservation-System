package pojo;

import java.util.Date;

public class CustomerByFlight {

	private String FirstName;
	private String LastName;
	private int AccountNumber;
	private int ReservationNumber;
	private Date TravelDate;
	private int NumberOfPassengers;
	
	public CustomerByFlight(String firstName, String lastName, int accountNumber, int reservationNumber,
			Date travelDate, int numberOfPassengers) {
		super();
		FirstName = firstName;
		LastName = lastName;
		AccountNumber = accountNumber;
		ReservationNumber = reservationNumber;
		TravelDate = travelDate;
		NumberOfPassengers = numberOfPassengers;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public int getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}

	public int getReservationNumber() {
		return ReservationNumber;
	}

	public void setReservationNumber(int reservationNumber) {
		ReservationNumber = reservationNumber;
	}

	public Date getTravelDate() {
		return TravelDate;
	}

	public void setTravelDate(Date travelDate) {
		TravelDate = travelDate;
	}

	public int getNumberOfPassengers() {
		return NumberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		NumberOfPassengers = numberOfPassengers;
	}
	
}
