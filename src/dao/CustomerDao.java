package dao;

import java.time.LocalDate;
import java.util.ArrayList;

import pojo.FDetailsDisplay;
import pojo.Reservation;
import pojo.RevenueSummary;

public interface CustomerDao {

	public ArrayList<FDetailsDisplay> getFlightsSingle(String from, String to, LocalDate date , int nopass);
	public ArrayList<FDetailsDisplay> getFlightsOneStop(String from, String to, LocalDate date , int nopass);
	public ArrayList<FDetailsDisplay> getFlightsTwoStop(String from, String to, LocalDate date , int nopass);
	public ArrayList<FDetailsDisplay> getFlightsThreeStop(String from, String to, LocalDate date , int nopass);
	public ArrayList<FDetailsDisplay> getFlightsRoundTrip(String from , String to , LocalDate depart , LocalDate returnDate , int nopass);
	public boolean isDomestic(String string, String string2);
	public int[] bookFlights(FDetailsDisplay fd, String flight, String[] passengerNames, String[] passengerPassport, LocalDate date, LocalDate date2 ,
			int bookingFee , int totalFare , String email);
	public int bookFlights(FDetailsDisplay fd, String flight, String[] passengerNames, String[] passengerPassport,
			LocalDate depart, int bookingFee, int totalFare , String email);
	public ArrayList<Reservation> getCurrentReservations(String email);
	public ArrayList<Reservation> getAllReservations(String email);
	public Reservation getReservationByReservationNumber(String email , int ReservationNumber);
	
	public ArrayList<RevenueSummary> getBestSellerFLightNumber();
}
