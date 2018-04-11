package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbConnection.dbConnection;
import pojo.Customer;
import pojo.CustomerByFlight;
import pojo.FDisplay;
import pojo.FlightStatus;
import pojo.Reservation;
import pojo.RevenueSummary;

public class ManagerDaoImpl implements ManagerDao {

	@Override
	public boolean edit(String accountNumber, String queryString) {
		// TODO Auto-generated method stub
		Connection con = dbConnection.getConnection();
		
		String query = "update Customer set " + queryString + " where AccountNumber = ?";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(accountNumber));
			
			int rs = ps.executeUpdate();
			
			if(rs == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(String accountNumber) {
		
		Connection con = dbConnection.getConnection();
		
		String query = "delete from Customer where AccountNumber = ?";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(accountNumber));
			
			int rs = ps.executeUpdate();
			
			if(rs == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<FDisplay> listAllFlights() {
		// TODO Auto-generated method stub
		Connection con = dbConnection.getConnection();
		
		String query = "select a.Name, f.FlightNumber, f.Seats, f.Fare, s.NoOfStops from Airline a, Flight f, Stops s where a.Id = f.AirlineId and f.FlightNumber = s.FlightNumber";
		ArrayList<FDisplay> flightData = new ArrayList<FDisplay>();
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				FDisplay f = new FDisplay(rs.getInt(2),rs.getString(1),rs.getInt(5),"", rs.getInt(3), rs.getInt(4));
				flightData.add(f);
			}
			for(FDisplay f : flightData) {
				String routeString = "";
				for(int i=1 ; i<=f.getStops(); i++) {
					query = "select a.Name from Airport a , Stop s where a.Id = s.AirportId and s.FlightNumber = ? and s.StopNo = ?";
					ps = con.prepareStatement(query);
					ps.setInt(1, f.getFlightNumber());
					ps.setInt(2, f.getStops());
					rs = ps.executeQuery();
					
					if(rs.next()) {
						if(i!=f.getStops()) {
							routeString += rs.getString(1) + "->";
						}else {
							routeString += rs.getString(1);
						}
					} 
				}
				f.setRouteString(routeString);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(flightData);
		return flightData;
	}

	@Override
	public String[] getCustomerWhoGeneratedTheMostRevenue() {
		// TODO Auto-generated method stub
		Connection con = dbConnection.getConnection();
		
		String query = "Select sum(r.TotalFare), c.FirstName, c.LastName, r.CustomerAcc from Reservation r, Customer c "
				+ "where r.CustomerAcc = c.AccountNumber having sum(r.TotalFare) = ("
				+ "select max(t1.revenue) from ("
				+ "select sum(r.TotalFare) as revenue, r.CustomerAcc from Reservation r, Customer c where r.CustomerAcc = c.AccountNumber group by r.CustomerAcc"
				+ ") as t1"
				+ ") ";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			String[] cust = new String[4];
			 
			if(rs.next()) {
				cust[0] = rs.getInt(1) + "";
				cust[1] = rs.getString(2);
				cust[2] = rs.getString(3);
				cust[3] = rs.getString(4);
			}
			return cust;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<FDisplay> mostActiveFLights() {
		// TODO Auto-generated method stub
		Connection con = dbConnection.getConnection();
		
		String query = "select t1.count seats_booked, t1.FlightNumber , t1.Name\r\n" + 
				"				   from\r\n" + 
				"				  (select count(*) as count , l.FlightNumber as FlightNumber ,a.Name as Name, s.NoOfStops as stops\r\n" + 
				"				  	from Reservation r , Leg l , Flight f, Airline a, Stops s\r\n" + 
				"				   where r.ReservationNumber = l.ReservationNumber\r\n" + 
				"				   and l.FlightNumber = f.FlightNumber\r\n" + 
				"				   and f.AirlineId = a.Id\r\n" + 
				"				   and f.FlightNumber = s.FlightNumber\r\n" + 
				"				   group by l.FlightNumber\r\n" + 
				"				   order by count DESC) as t1 \r\n" + 
				"				   limit 10;";
		ArrayList<FDisplay> flightData = new ArrayList<FDisplay>();
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				FDisplay f = new FDisplay(rs.getInt(2), rs.getString(3), rs.getInt(1));
				flightData.add(f);
			}
			for(FDisplay f : flightData) {
				String routeString = "";
				for(int i=1 ; i<=f.getStops(); i++) {
					query = "select a.Name from Airport a , Stops s where a.Id = s.AirportId and s.FlightNumber = ? and s.StopNo = ?";
					ps = con.prepareStatement(query);
					ps.setInt(1, f.getFlightNumber());
					ps.setInt(2, f.getStops());
					rs = ps.executeQuery();
					
					if(rs.next()) {
						if(i!=f.getStops()) {
							routeString += rs.getString(1) + "->";
						}else {
							routeString += rs.getString(1);
						}
					} 
				}
				f.setRouteString(routeString);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(flightData);
		return flightData;
	}

	@Override
	public ArrayList<CustomerByFlight> listCustomersForGivenFlight(int flightNumber) {
		// TODO Auto-generated method stub
		Connection con = dbConnection.getConnection();
		
		String query = "select c.AccountNumber , c.FirstName , c.LastName , r.ReservationNumber , r.DateOfTravel , r.Passengers"
				+ " from Customer c , Reservation r , Leg l"
				+ " where r.ReservationNumber = l.ReservationNumber"
				+ " and l.FlightNumber = ?"
				+ " and c.AccountNumber = r.CustomerAcc";
		ArrayList<CustomerByFlight> data = new ArrayList<CustomerByFlight>();
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, flightNumber);
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				CustomerByFlight f = new CustomerByFlight(rs.getString(2), rs.getString(3), rs.getInt(1), rs.getInt(4), rs.getDate(5), rs.getInt(6));
				data.add(f);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public ArrayList<FDisplay> getFLightsByAirport(String AirportName) {
		// TODO Auto-generated method stub
		
		Connection con = dbConnection.getConnection();
		
		String query = "select a.name , t1.FlightNumber , f1.Seats"
				+ " from Airline a , Flight f1 , "
				+ " (select distinct f.FlightNumber as FlightNumber"
				+ "	  from Flight f , Stop s , Airport a1"
				+ "   where a1.name = ?"
				+ "   and a1.Id = s.AirportId"
				+ "	  and s.FlightNumber = f.FlightNumber) as t1"
				+ "	   where t1.FlightNumber = f1.FlightNumber and "
				+ "    a.Id = f1.AirlineId";
		ArrayList<FDisplay> flightData = new ArrayList<FDisplay>();
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, AirportName);
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				//need to change the parameters below
				System.out.println(rs.getInt(3));
				FDisplay f = new FDisplay(rs.getInt(2), rs.getString(1), rs.getInt(3));
				flightData.add(f);
			}
			}catch (SQLException e) {
			e.printStackTrace();
		}
		return flightData;
	}

	@Override
	public ArrayList<Reservation> getReservationsByFlightNumber(int FlightNumber) {
		// TODO Auto-generated method stub
		Connection con = dbConnection.getConnection();
		System.out.println(FlightNumber);
		String query = "select r.ReservationNumber , r.DateOfTravel , r.Passengers , r.TotalFare , "
				+ "r.CustomerAcc , r.CustomerRepresentative"
				+ " from Reservation r , Leg l"
				+ " where r.ReservationNumber = l.ReservationNumber"
				+ " and l.FlightNumber = ?";
		ArrayList<Reservation> reservationData = new ArrayList<Reservation>();
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, FlightNumber);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				//need to change the parameters below
				 Reservation r = new Reservation(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
				 reservationData.add(r);
			}
			}catch (SQLException e) {
			e.printStackTrace();
		}
		return reservationData;
	}

	@Override
	public ArrayList<Reservation> getReservationsByCustomerName(String CustomerName) {
		// TODO Auto-generated method stub
		Connection con = dbConnection.getConnection();
		
		String query = "select  r.ReservationNumber , r.DateOfTravel , r.Passengers , r.TotalFare ,r.CustomerAcc , r.CustomerRepresentative"
				+ " from Reservation r , Customer c"
				+ " where c.FirstName = ?"
				+ " and r.CustomerAcc = c.AccountNumber";
		ArrayList<Reservation> reservationData = new ArrayList<Reservation>();
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, CustomerName);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				//need to change the parameters below
				Reservation r = new Reservation(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
				 reservationData.add(r);
			}
			}catch (SQLException e) {
			e.printStackTrace();
		}
		return reservationData;
	}

	@Override
	public ArrayList<RevenueSummary> getRevenueSummaryByFLightNumber(int flightNumber) {
		// TODO Auto-generated method stub
		Connection con = dbConnection.getConnection();
		ArrayList<RevenueSummary> rsummary = new ArrayList<RevenueSummary>();
		for(int i = 1 ; i <= 12 ; i++)
		{
			String query = "select count(*) , sum(r.TotalFare) "
					+ " from Reservation r , Leg l"
					+ " where r.ReservationNumber = l.ReservationNumber"
					+ " and l.FlightNumber = ?"
					+ " and month(DateOfTravel) = ?";
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, flightNumber);
				ps.setInt(2, i);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					RevenueSummary r = new RevenueSummary(rs.getInt(1), rs.getInt(2) , i);
					rsummary.add(r);
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rsummary;
	}

	@Override
	public ArrayList<RevenueSummary> getRevenueSummaryByDestinationCity(String city) {
		// TODO Auto-generated method stub
		Connection con = dbConnection.getConnection();
		
		ArrayList<RevenueSummary> rsummary = new ArrayList<RevenueSummary>();
		for(int i = 1 ; i <= 12 ; i++)
		{
			String query = "select count(*) , sum(r.TotalFare)\r\n" + 
					"					  from Reservation r , Leg l , Stop s , Airport a\r\n" + 
					"					  where a.City = ?" + 
					"					  and a.Id = s.AirportId\r\n" + 
					"					  and s.Id = l.StopId2\r\n" + 
					"					  and l.ReservationNumber = r.ReservationNumber\r\n" + 
					"					  and month(DateOfTravel) = ?";
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(query);
				ps.setString(1, city);
				ps.setInt(2, i);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					System.out.println(rs.getInt(2));
					RevenueSummary r = new RevenueSummary(rs.getInt(1), rs.getInt(2) , i);
					rsummary.add(r);
				}
				return rsummary;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rsummary;
	}

	@Override
	public ArrayList<RevenueSummary> getRevenueSummaryByCustomer(String CustomerName) {
		Connection con = dbConnection.getConnection();
		ArrayList<RevenueSummary> rsummary = new ArrayList<RevenueSummary>();
		
		String query = "";
		for(int i = 1 ; i <= 12 ; i++)
		{
			query = "select count(*) , sum(r.TotalFare)"
					+ " from Reservation r , Customer c"
					+ " where c.FirstName = ?"
					+ " and c.AccountNumber = r.CustomerAcc"
					+ " and month(DateOfTravel) = ?";
		
		//String query = "";
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(query);
				ps.setString(1, CustomerName);
				ps.setInt(2, i);
				ResultSet rs = ps.executeQuery();
				
				
				while(rs.next()) {
					RevenueSummary r = new RevenueSummary(rs.getInt(1), rs.getInt(2) , i);
					rsummary.add(r);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rsummary;
	}

	@Override
	public ArrayList<Reservation> getSummarReportForMonth(int monthNumber) {
		// TODO Auto-generated method stub
		Connection con = dbConnection.getConnection();
		
		String query = "select * from Reservation where month(DateOfTravel) = ?";
		ArrayList<Reservation> reservationData = new ArrayList<Reservation>();
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, monthNumber);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				//need to change the parameters below
				 Reservation r = new Reservation(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
				 reservationData.add(r);
			}
			}catch (SQLException e) {
			e.printStackTrace();
		}
		return reservationData;
	}

	@Override
	public ArrayList<FlightStatus> listAllFlightsStatus() {

		Connection con = dbConnection.getConnection();
		String query = "select * from Status";
		ArrayList<FlightStatus> flightData = new ArrayList<FlightStatus>();
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				FlightStatus f = new FlightStatus(rs.getInt(2), rs.getTime(3), rs.getTime(4), rs.getInt(5), rs.getDate(6));
				flightData.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(flightData);
		return flightData;
	}
	
	
	

	
	
	
	
	
	
	
	

}
