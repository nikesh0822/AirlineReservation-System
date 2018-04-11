package dao;

import java.util.ArrayList;

import pojo.Customer;
import pojo.CustomerByFlight;
import pojo.FDisplay;
import pojo.FlightStatus;
import pojo.Reservation;
import pojo.RevenueSummary;

public interface ManagerDao {

	public boolean edit(String accountNumber, String queryString);
	public boolean delete(String accountNumber);
	public String[] getCustomerWhoGeneratedTheMostRevenue();
	public ArrayList<FDisplay> mostActiveFLights();
	public ArrayList<FDisplay> listAllFlights();
	public ArrayList<FlightStatus> listAllFlightsStatus();
	public ArrayList<CustomerByFlight> listCustomersForGivenFlight(int flightNumber);
	public ArrayList<FDisplay> getFLightsByAirport(String AirportName);
	public ArrayList<Reservation> getReservationsByFlightNumber(int flightNumber);
	public ArrayList<Reservation> getReservationsByCustomerName(String CustomerName);
	public ArrayList<RevenueSummary> getRevenueSummaryByFLightNumber(int flightNumber); 
	public ArrayList<RevenueSummary> getRevenueSummaryByDestinationCity(String city);
	public ArrayList<RevenueSummary> getRevenueSummaryByCustomer(String CustomerName);
	public ArrayList<Reservation> getSummarReportForMonth(int monthNumber);
	
}
