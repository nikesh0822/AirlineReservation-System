package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.sql.PreparedStatement;

import dbConnection.dbConnection;
import pojo.Customer;
import pojo.FDetailsDisplay;
import pojo.Reservation;
import pojo.RevenueSummary;

public class CustomerDaoImpl implements CustomerDao {

	Connection con;
	public CustomerDaoImpl() {
		super();
		con = dbConnection.getConnection();
		// TODO Auto-generated constructor stub
	}

	
	public int getDayNumber(String s)
	{
		if(s.equals("monday"))
		{
			return 1;
		}
		else
			if(s.equals("tuesday"))
			{
				return 2;
			}
			else if(s.equals("wednesday"))
			{
				return 3;
			}
			else if(s.equals("thursday"))
			{
				return 4;
			}
			else if(s.equals("friday"))
			{
				return 5;
			}
			else if(s.equals("saturday"))
			{
				return 6;
			}
			else
			{
				return 7;
			}
	}
	
	
	@Override
	public ArrayList<FDetailsDisplay> getFlightsSingle(String from, String to, LocalDate date, int nopass) {
		// TODO Auto-generated method stub
		ArrayList<FDetailsDisplay> list = new ArrayList<FDetailsDisplay>();
				
		String query = "select s1.Id , s2.Id , a.Name , f.FlightNumber , s1.DepartureTime , s2.ArrivalTime , f.Fare \r\n" + 
				"						from DaysOfWeek d , Flight f , Stop s1 , Stop s2 , Airline a \r\n" + 
				"						where d.Day = ? and \r\n" + 
				"						s1.AirportId = (select Id from Airport where Name = ?) and\r\n" + 
				"						s2.AirportId = (select Id from Airport where Name = ?) and\r\n" + 
				"						s1.FlightNumber = d.FlightNumber and \r\n" + 
				"                        f.AirlineId = a.Id and\r\n" + 
				"						s1.FlightNumber = s2.FlightNumber and \r\n" + 
				"						s1.stopNo = s2.stopNo - 1 and \r\n" + 
				"						s1.FlightNumber = f.FlightNumber \r\n"
				+ " and s1.Day = ? "
				+ "and s2.Day = ? "
				+ "and s1.Day = s2.Day " + 
				"						and s1.FlightNumber NOT IN ( \r\n" + 
				"						select distinct t1.FlightNumber \r\n" + 
				"						from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked \r\n" + 
				"										 from Leg l , Reservation r \r\n" + 
				"										 where r.ReservationNumber = l.ReservationNumber \r\n" + 
				"										 and r.DateOfTravel = ? \r\n" + 
				"										 group by l.FlightNumber) \r\n" + 
				"						as t1 \r\n" + 
				"					where t1.booked + ? > f.Seats \r\n" + 
				"						and t1.FlightNumber = f.FlightNumber)  ";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			System.out.println(date.getDayOfWeek().toString());
			ps.setString(1, date.getDayOfWeek().toString().toLowerCase());
			ps.setString(2, from);
			ps.setString(3, to);
			ps.setInt(4, getDayNumber(date.getDayOfWeek().toString().toLowerCase()));
			ps.setInt(5, getDayNumber(date.getDayOfWeek().toString().toLowerCase()));
			ps.setDate(6, Date.valueOf(date));
			ps.setInt(7, nopass);
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while(rs.next())
			{
				FDetailsDisplay fdd = new FDetailsDisplay();
				fdd.setObjectId(i++);
				fdd.setStopId1(rs.getInt(1));
				fdd.setStopId2(rs.getInt(2));
				fdd.setAirlineName1(rs.getString(3));
				fdd.setFLightNumber1(rs.getInt(4));
				fdd.setDepartureTime(rs.getTime(5));
				fdd.setArrivalTime(rs.getTime(6));
				fdd.setFare(rs.getInt(7)*nopass);
				list.add(fdd);
			}
			
			
			if(list.size() == 0)
			{
				query = "select s1.Id , s2.Id , a.Name , f.FlightNumber , s1.DepartureTime , s2.ArrivalTime , f.Fare \r\n" + 
						"						from DaysOfWeek d , Flight f , Stop s1 , Stop s2 , Airline a \r\n" + 
						"						where d.Day = ? and \r\n" + 
						"						s1.AirportId = (select Id from Airport where Name = ?) and\r\n" + 
						"						s2.AirportId = (select Id from Airport where Name = ?) and\r\n" + 
						"						s1.FlightNumber = d.FlightNumber and \r\n" + 
						"                        f.AirlineId = a.Id and\r\n" + 
						"						s1.FlightNumber = s2.FlightNumber and \r\n" + 
						"						s1.stopNo = s2.stopNo - 1 and \r\n" + 
						"						s1.FlightNumber = f.FlightNumber \r\n"
						+ " and s1.Day = ? "
						+ "and s2.Day = ? "
						+ "and s1.Day = s2.Day - 1 " + 
						"						and s1.FlightNumber NOT IN ( \r\n" + 
						"						select distinct t1.FlightNumber \r\n" + 
						"						from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked \r\n" + 
						"										 from Leg l , Reservation r \r\n" + 
						"										 where r.ReservationNumber = l.ReservationNumber \r\n" + 
						"										 and r.DateOfTravel = ? \r\n" + 
						"										 group by l.FlightNumber) \r\n" + 
						"						as t1 \r\n" + 
						"					where t1.booked + ? > f.Seats \r\n" + 
						"						and t1.FlightNumber = f.FlightNumber)  ";
				
				ps = con.prepareStatement(query);
				
				ps = con.prepareStatement(query);
				System.out.println(date.getDayOfWeek().toString());
				ps.setString(1, date.getDayOfWeek().toString().toLowerCase());
				ps.setString(2, from);
				ps.setString(3, to);
				ps.setInt(4, getDayNumber(date.getDayOfWeek().toString().toLowerCase()));
				ps.setInt(5, getDayNumber(date.getDayOfWeek().toString().toLowerCase()));
				ps.setDate(6, Date.valueOf(date));
				ps.setInt(7, nopass);
				rs = ps.executeQuery();
				i = 0;
				while(rs.next())
				{
					FDetailsDisplay fdd = new FDetailsDisplay();
					fdd.setObjectId(i++);
					fdd.setStopId1(rs.getInt(1));
					fdd.setStopId2(rs.getInt(2));
					fdd.setAirlineName1(rs.getString(3));
					fdd.setFLightNumber1(rs.getInt(4));
					fdd.setDepartureTime(rs.getTime(5));
					fdd.setArrivalTime(rs.getTime(6));
					fdd.setFare(rs.getInt(7)*nopass);
					list.add(fdd);
				}
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<FDetailsDisplay> getFlightsOneStop(String from, String to, LocalDate date, int nopass)
	{
		ArrayList<FDetailsDisplay> list = new ArrayList<FDetailsDisplay>();
		
		 
		
		String query = "select s1.Id , s2.Id , s3.Id , s4.Id , a1.Name , a2.Name , f1.FlightNumber , f2.FlightNumber ,"
				+ " s1.DepartureTime , s2.ArrivalTime , s3.DepartureTime , s4.ArrivalTime ,  f1.Fare + f2.Fare " + 
						"from DaysOfWeek d , Flight f1 , Flight f2 , Stop s1 , Stop s2 , Stop s3 , Stop s4 ,"
						+ " Airline a1 , Airline a2 " + 
						"where d.Day = ? and "
						+ "s1.AirportId = (select Id from Airport where Name = ?) and "
						+ "s4.AirportId = (select Id from Airport where Name = ?) and "
						+ "s1.FlightNumber = d.FlightNumber and "
						+ " f1.AirlineId = a1.Id and "
						+ " f2.AirlineId = a2.Id and "
						+ "s1.FlightNumber = s2.FlightNumber and "
						+ "s1.StopNo = s2.StopNo - 1 and "
						+ " (s1.Day = s2.Day or s1.Day = s2.Day - 1) and "
						+ " (s3.Day = s4.Day or s3.Day = s4.Day - 1) and "
						+ " (s2.Day = s3.Day or s2.Day = s3.Day - 1) "
						+ "and s2.AirportId = s3.AirportId "
						+ "and s3.FlightNumber = s4.FlightNumber "
						+ "and s3.DepartureTime > s2.ArrivalTime "
						+ "and s3.StopNo = s4.StopNo - 1 "
						+ "and s1.FlightNumber = f1.FlightNumber "
						+ "and s3.FlightNumber = f2.FlightNumber "
						+ "and s1.FlightNumber NOT IN ( "
						+ "select t1.FlightNumber "
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked "
										 + "from Leg l , Reservation r "
										 + "where r.ReservationNumber = l.ReservationNumber "
										 + "and r.DateOfTravel = ? "
										 + "group by l.FlightNumber) "
						+ "as t1 "
						+ "where t1.booked + ? > f.Seats "
						+ "and t1.FlightNumber = f.FlightNumber) "
						+ "and s3.FlightNumber NOT IN( "
						+ "select t1.FlightNumber "
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked "
										 + "from Leg l , Reservation r "
										 + "where r.ReservationNumber = l.ReservationNumber "
										 + "and r.DateOfTravel = ? "
										 + "group by l.FlightNumber) "
						+ "as t1 "
						+ "where t1.booked + ? > f.Seats "
						+ "and t1.FlightNumber = f.FlightNumber) ";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, date.getDayOfWeek().toString());
			ps.setString(2, from);
			ps.setString(3, to);
			ps.setDate(4, Date.valueOf(date));
			ps.setInt(5, nopass);
			ps.setDate(6, Date.valueOf(date));
			ps.setInt(7, nopass);
			
			ResultSet rs = ps.executeQuery();
			
			int i = 0;
			while(rs.next())
			{
				//select s1.Id , s2.Id , s3.Id , s4.Id , a1.Name , a2.Name , f1.FlightNumber , f2.FlightNumber ,"
				//		+ " s1.DepartureTime , s2.ArrivalTime , s3.DepartureTime , s4.ArrivalTime ,  f1.Fare + f2.Fare
				FDetailsDisplay fdd = new FDetailsDisplay();
				fdd.setObjectId(i++);
				fdd.setStopId1(rs.getInt(1));
				fdd.setStopId2(rs.getInt(2));
				fdd.setStopId3(rs.getInt(3));
				fdd.setStopId4(rs.getInt(4));
				fdd.setAirlineName1(rs.getString(5));
				fdd.setAirlineName2(rs.getString(6));
				fdd.setFLightNumber1(rs.getInt(7));
				fdd.setFLightNumber2(rs.getInt(8));
				fdd.setDepartureTime(rs.getTime(9));
				fdd.setArrivalTime(rs.getTime(10));
				fdd.setSecDepartureTime(rs.getTime(11));
				fdd.setSecArrivalTime(rs.getTime(12));
				fdd.setFare(rs.getInt(13)*nopass);
				list.add(fdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return list;
	}


	public ArrayList<FDetailsDisplay> getFlightsTwoStop(String from, String to,LocalDate date, int nopass)
	{
		ArrayList<FDetailsDisplay> list = new ArrayList<FDetailsDisplay>();
		
		 
		
		String query = "select s1.Id , s2.Id , s3.Id , s4.Id , s5.Id , s6.Id , a1.Name , a2.Name , a3.Name ,"
				+ " f1.FlightNumber , f2.FlightNumber , f3.FlightNumber ,"
				+ " s1.DepartureTime , s2.ArrivalTime , s3.DepartureTime , s4.ArrivalTime , s5.DepartureTime , s6.ArrivalTime ,"
				+ "  f1.Fare + f2.Fare + f3.Fare " + 
						"from DaysOfWeek d , Flight f1 , Flight f2 , Flight f3,  Stop s1 , Stop s2 , Stop s3 , Stop s4 ,"
						+ " Stop s5 , Stop s6 , Airline a1 , Airline a2 , Airline a3 " + 
						"where d.Day = ? and "
						+ "s1.AirportId = (select Id from Airport where Name = ?) and "
						+ "s6.AirportId = (select Id from Airport where Name = ?) and "
						+ "s1.FlightNumber = d.FlightNumber and "
						+ "s1.FlightNumber = s2.FlightNumber and "
						+ "s1.StopNo = s2.StopNo - 1 "
						+ "and s2.AirportId = s3.AirportId "
						+ "and s3.FlightNumber = s4.FlightNumber "
						+ "and s3.DepartureTime > s2.ArrivalTime "
						+ "and s3.StopNo = s4.StopNo - 1 "
						+ "and s4.AirportId = s5.AirportId "
						+ "and s5.DepartureTime > s4.ArrivalTime "
						+ "and s5.FlightNumber = s6.FlightNumber "
						+ "and s5.StopNo = s6.StopNo - 1 "
						+ "and s1.FlightNumber = f1.FlightNumber "
						+ "and s3.FlightNumber = f2.FlightNumber "
						+ "and s5.FlightNumber = f3.FlightNumber "
						+ "and f1.AirlineId = a1.Id "
						+ "and f2.AirlineId = a2.Id "
						+ "and f3.AirlineId = a3.Id "
						+ "and (s1.Day = s2.Day or s1.Day = s2.Day - 1) "
						+ "and (s3.Day = s4.Day or s3.Day = s4.Day - 1) "
						+ "and (s5.Day = s6.Day or s5.Day = s6.Day - 1) "
						+ "and (s2.Day = s3.Day or s2.Day = s3.Day - 1) "
						+ "and (s4.Day = s5.Day or s4.Day = s5.Day - 1) "
						+ "and s1.FlightNumber NOT IN ( "
						+ "select t1.FlightNumber "
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked "
										 + "from Leg l , Reservation r "
										 + "where r.ReservationNumber = l.ReservationNumber "
										 + "and r.DateOfTravel = ? "
										 + "group by l.FlightNumber) "
						+ "as t1 "
						+ "where t1.booked + ? > f.Seats "
						+ "and t1.FlightNumber = f.FlightNumber) "
						+ "and s3.FlightNumber NOT IN( "
						+ "select t1.FlightNumber "
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked "
										 + "from Leg l , Reservation r "
										 + "where r.ReservationNumber = l.ReservationNumber "
										 + "and r.DateOfTravel = ? "
										 + "group by l.FlightNumber) "
						+ "as t1 "
						+ "where t1.booked + ? > f.Seats "
						+ "and t1.FlightNumber = f.FlightNumber) "
						+ "and s5.FlightNumber NOT IN ( "
						+ "select t1.FlightNumber "
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked "
										 + "from Leg l , Reservation r "
										 + "where r.ReservationNumber = l.ReservationNumber "
										 + "and r.DateOfTravel = ? "
										 + "group by l.FlightNumber) "
						+ "as t1 "
						+ "where t1.booked + ? > f.Seats "
						+ "and t1.FlightNumber = f.FlightNumber) ";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, date.getDayOfWeek().toString());
			ps.setString(2, from);
			ps.setString(3, to);
			ps.setDate(4, Date.valueOf(date));
			ps.setInt(5, nopass);
			ps.setDate(6, Date.valueOf(date));
			ps.setInt(7, nopass);
			ps.setDate(8, Date.valueOf(date));
			ps.setInt(9, nopass);
			
			ResultSet rs = ps.executeQuery();
			
			int i = 0;
			while(rs.next())
			{
				//select s1.Id , s2.Id , s3.Id , s4.Id , s5.Id , s6.Id , a1.Name , a2.Name , a3.Name ,"
				//		+ " f1.FlightNumber , f2.FlightNumber , f3.FlightNumber ,"
				//		+ " s1.DepartureTime , s2.ArrivalTime , s3.DepartureTime , s4.ArrivalTime , s5.DepartureTime , s6.ArrivalTime ,"
				//		+ "  f1.Fare + f2.Fare + f3.Fare"
				FDetailsDisplay fdd = new FDetailsDisplay();
				fdd.setObjectId(i++);
				fdd.setStopId1(rs.getInt(1));
				fdd.setStopId2(rs.getInt(2));
				fdd.setStopId3(rs.getInt(3));
				fdd.setStopId4(rs.getInt(4));
				fdd.setStopId5(rs.getInt(5));
				System.out.println(fdd.getStopId5() + "he");
				fdd.setStopId6(rs.getInt(6));
				System.out.println(fdd.getStopId6() + "he2");
				fdd.setAirlineName1(rs.getString(7));
				fdd.setAirlineName2(rs.getString(8));
				fdd.setAirlineName3(rs.getString(9));
				fdd.setFLightNumber1(rs.getInt(10));
				fdd.setFLightNumber2(rs.getInt(11));
				fdd.setFLightNumber3(rs.getInt(12));
				fdd.setDepartureTime(rs.getTime(13));
				fdd.setArrivalTime(rs.getTime(14));
				fdd.setSecDepartureTime(rs.getTime(15));
				fdd.setSecArrivalTime(rs.getTime(16));
				fdd.setThirdDepartureTime(rs.getTime(17));
				fdd.setThirdArrivalTime(rs.getTime(18));
				fdd.setFare(rs.getInt(19)*nopass);
				list.add(fdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return list;
	}	
	
	
	public ArrayList<FDetailsDisplay> getFlightsThreeStop(String from, String to, LocalDate date, int nopass)
	{
		ArrayList<FDetailsDisplay> list = new ArrayList<FDetailsDisplay>();
		
		String query = "select s1.Id , s2.Id , s3.Id , s4.Id , s5.Id , s6.Id , s7.Id , s8.Id , a1.Name , a2.Name , a3.Name , a4.Name,"
				+ " f1.FlightNumber , f2.FlightNumber , f3.FlightNumber , f4.FlightNumber,"
				+ " s1.DepartureTime , s2.ArrivalTime , s3.DepartureTime , s4.ArrivalTime , s5.DepartureTime , s6.ArrivalTime , s7.DepartureTIme , s8.ArrivalTime,"
				+ "  f1.Fare + f2.Fare + f3.Fare + f4.Fare " + 
						"from DaysOfWeek d , Flight f1 , Flight f2 , Flight f3, Flight f4 , Stop s1 , Stop s2 , Stop s3 , Stop s4 , Stop s7 , Stop s8 ,"
						+ " Stop s5 , Stop s6 , Airline a1 , Airline a2 , Airline a3 , Airline a4 " + 
						"where d.Day = ? and "
						+ " s1.AirportId = (select Id from Airport where Name = ?) and"
						+ " s8.AirportId = (select Id from Airport where Name = ?) and"
						+ " s1.FlightNumber = d.FlightNumber and"
						+ " s1.FlightNumber = s2.FlightNumber and"
						+ " s1.StopNo = s2.StopNo - 1"
						+ " and s2.AirportId = s3.AirportId"
						+ " and s3.FlightNumber = s4.FlightNumber"
						+ " and s3.DepartureTime > s2.ArrivalTime"
						+ " and s3.StopNo = s4.StopNo - 1"
						+ " and s4.AirportId = s5.AirportId"
						+ " and s5.DepartureTime > s4.ArrivalTime"
						+ " and s5.FlightNumber = s6.FlightNumber"
						+ " and s5.StopNo = s6.StopNo - 1"
						+ " and s6.AirportId = s7.AirportId"
						+ " and s7.DepartureTime > s6.ArrivalTime"
						+ " and s7.FlightNumber = s8.FlightNumber"
						+ " and s7.StopNo = s8.StopNo - 1"
						+ " and s1.FlightNumber = f1.FlightNumber"
						+ " and s3.FlightNumber = f2.FlightNumber"
						+ " and s5.FlightNumber = f3.FlightNumber"
						+ " and s7.FlightNumber = f4.FlightNumber"
						+ " and f1.AirlineId = a1.Id"
						+ " and f2.AirlineId = a2.Id"
						+ " and f3.AirlineId = a3.Id"
						+ " and f4.AirlineId = a4.Id"
						+ " and (s1.Day = s2.Day or s1.Day = s2.Day - 1) " + 
						" and (s3.Day = s4.Day or s3.Day = s4.Day - 1) " + 
						" and (s5.Day = s6.Day or s5.Day = s6.Day - 1) " + 
						" and (s2.Day = s3.Day or s2.Day = s3.Day - 1) " + 
						" and (s4.Day = s5.Day or s4.Day = s5.Day - 1)  "
						+ " and (s7.Day = s8.Day or s7.Day = s8.Day - 1) "
						+ " and (s6.Day = s7.Day or s6.Day = s7.Day - 1)"
						+ " and s1.FlightNumber NOT IN ("
						+ " select t1.FlightNumber"
						+ " from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + " from Leg l , Reservation r"
										 + " where r.ReservationNumber = l.ReservationNumber"
										 + " and r.DateOfTravel = ?"
										 + " group by l.FlightNumber) "
						+ " as t1"
						+ " where t1.booked + ? > f.Seats"
						+ " and t1.FlightNumber = f.FlightNumber)"
						+ " and s3.FlightNumber NOT IN("
						+ " select t1.FlightNumber"
						+ " from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + " from Leg l , Reservation r"
										 + " where r.ReservationNumber = l.ReservationNumber"
										 + " and r.DateOfTravel = ?"
										 + " group by l.FlightNumber) "
						+ " as t1"
						+ " where t1.booked + ? > f.Seats"
						+ " and t1.FlightNumber = f.FlightNumber)"
						+ " and s5.FlightNumber NOT IN ("
						+ " select t1.FlightNumber"
						+ " from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + " from Leg l , Reservation r"
										 + " where r.ReservationNumber = l.ReservationNumber"
										 + " and r.DateOfTravel = ?"
										 + " group by l.FlightNumber) "
						+ " as t1"
						+ " where t1.booked + ? > f.Seats"
						+ " and t1.FlightNumber = f.FlightNumber)"
						+ " and s7.FlightNumber NOT IN ("
						+ " select t1.FlightNumber"
						+ " from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + " from Leg l , Reservation r"
										 + " where r.ReservationNumber = l.ReservationNumber"
										 + " and r.DateOfTravel = ?"
										 + " group by l.FlightNumber) "
						+ " as t1"
						+ " where t1.booked + ? > f.Seats"
						+ " and t1.FlightNumber = f.FlightNumber)";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, date.getDayOfWeek().toString());
			ps.setString(2, from);
			ps.setString(3, to);
			ps.setDate(4, Date.valueOf(date));
			ps.setInt(5, nopass);
			ps.setDate(6, Date.valueOf(date));
			ps.setInt(7, nopass);
			ps.setDate(8, Date.valueOf(date));
			ps.setInt(9, nopass);
			ps.setDate(10, Date.valueOf(date));
			ps.setInt(11, nopass);
			ResultSet rs = ps.executeQuery();
			
			int i = 0;
			while(rs.next())
			{
				//select s1.Id , s2.Id , s3.Id , s4.Id , s5.Id , s6.Id , s7.Id , s8.Id , a1.Name , a2.Name , a3.Name , a4.Name"
				//		+ " f1.FlightNumber , f2.FlightNumber , f3.FlightNumber , f4.FlightNumber"
				//		+ " s1.DepartureTime , s2.ArrivalTime , s3.DepartureTime , s4.ArrivalTime , s5.DepartureTime , s6.ArrivalTime , s7.DepartureTIme , s8.ArrivalTime"
				//		+ "  f1.Fare + f2.Fare + f3.Fare + f4.Fare
				FDetailsDisplay fdd = new FDetailsDisplay();
				fdd.setObjectId(i++);
				fdd.setStopId1(rs.getInt(1));
				fdd.setStopId2(rs.getInt(2));
				fdd.setStopId3(rs.getInt(3));
				fdd.setStopId4(rs.getInt(4));
				fdd.setStopId5(rs.getInt(5));
				fdd.setStopId6(rs.getInt(6));
				fdd.setStopId7(rs.getInt(7));
				fdd.setStopId8(rs.getInt(8));
				fdd.setAirlineName1(rs.getString(9));
				fdd.setAirlineName2(rs.getString(10));
				fdd.setAirlineName3(rs.getString(11));
				fdd.setAirlineName4(rs.getString(12));
				fdd.setFLightNumber1(rs.getInt(13));
				fdd.setFLightNumber2(rs.getInt(14));
				fdd.setFLightNumber2(rs.getInt(15));
				fdd.setFLightNumber2(rs.getInt(16));
				fdd.setDepartureTime(rs.getTime(17));
				fdd.setArrivalTime(rs.getTime(18));
				fdd.setSecDepartureTime(rs.getTime(19));
				fdd.setSecArrivalTime(rs.getTime(20));
				fdd.setThirdDepartureTime(rs.getTime(21));
				fdd.setThirdArrivalTime(rs.getTime(22));
				fdd.setFourthDepartureTime(rs.getTime(23));
				fdd.setFourthArrivalTime(rs.getTime(24));
				fdd.setFare(rs.getInt(25)*nopass);
				list.add(fdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public ArrayList<FDetailsDisplay> getFlightsRoundTrip(String from, String to, LocalDate depart, LocalDate returnDate, int nopass) {
		// TODO Auto-generated method stub
		ArrayList<FDetailsDisplay> list = new ArrayList<FDetailsDisplay>();
				
		String query = "select s1.Id , s2.Id , s3.Id , s4.Id , a1.Name , a2.Name , f1.FlightNumber , f2.FlightNumber , s1.DepartureTime , s2.ArrivalTime , "
				+ "s3.DepartureTime , s4.ArrivalTime , f1.Fare + f2.Fare "
				+ " from Stop s1 , Stop s2 , Stop s3 , Stop s4 , Flight f1 , Flight f2 , Airline a1 , Airline a2 , DaysOfWeek d1 , DaysOfWeek d2 "
				+ " where d1.Day = ? "
				+ " and s1.FlightNumber = d1.FlightNumber "
				+ " and s1.FlightNumber = s2.FlightNumber "
				+ " and s1.StopNo = s2.StopNo - 1 "
				+ " and f1.FlightNumber = s1.FlightNumber "
				+ " and f1.AirlineId = a1.Id"
				+ " and s1.AirportId = (select Id from Airport where Name = ?)"
				+ " and s2.AirportId = (select Id from Airport where Name = ?)"
				+ " and s3.AirportId = s2.AirportId"
				+ " and s4.AirportId = s1.AirportId"
				+ " and d2.Day = ?"
				+ " and d2.FlightNumber = s3.FlightNumber"
				+ " and s3.FlightNumber = s4.FlightNumber"
				+ " and s3.StopNo = s4.StopNo - 1"
				+ " and f2.AirlineId = a2.Id"
				+ " and f2.FlightNumber = s3.FlightNumber"
				+ " and (s1.Day = s2.Day or s1.Day = s2.Day - 1)"
				+ " and (s3.Day = s4.Day or s3.Day = s4.Day - 1)"
				+ " and s1.FlightNumber not in("
						+ " select t1.FlightNumber"
						+ " from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + " from Leg l , Reservation r"
										 + " where r.ReservationNumber = l.ReservationNumber"
										 + " and r.DateOfTravel = ?"
										 + " group by l.FlightNumber) "
						+ " as t1"
						+ " where t1.booked + ? > f.Seats"
						+ " and t1.FlightNumber = f.FlightNumber)"
				+ " and s3.FlightNumber not in("
						+ " select t1.FlightNumber"
						+ " from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + " from Leg l , Reservation r"
										 + " where r.ReservationNumber = l.ReservationNumber"
										 + " and r.DateOfTravel = ?"
										 + " group by l.FlightNumber) "
						+ " as t1"
						+ " where t1.booked + ? > f.Seats"
						+ " and t1.FlightNumber = f.FlightNumber)";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1 , depart.getDayOfWeek().toString().toLowerCase());
			ps.setString(2 , from);
			ps.setString(3, to);
			ps.setString(4, returnDate.getDayOfWeek().toString().toLowerCase());
			ps.setDate(5, Date.valueOf(depart));
			ps.setInt(6, nopass);
			ps.setDate(7, Date.valueOf(returnDate));
			ps.setInt(8, nopass);
			
			ResultSet rs = ps.executeQuery();
			
			int i = 0;
			while(rs.next())
			{
				//select s1.Id , s2.Id , s3.Id , s4.Id , a1.Name , a2.Name , f1.FlightNumber , f2.FlightNumber , s1.DepartureTime , s2.ArrivalTime , "
				//		+ "s3.DepartureTime , s4.ArrivalTime , f1.Fare , f2.Fare
				FDetailsDisplay fdd = new FDetailsDisplay();
				fdd.setObjectId(i++);
				fdd.setStopId1(rs.getInt(1));
				fdd.setStopId2(rs.getInt(2));
				fdd.setStopId3(rs.getInt(3));
				fdd.setStopId4(rs.getInt(4));
				fdd.setAirlineName1(rs.getString(5));
				fdd.setAirlineName2(rs.getString(6));
				fdd.setFLightNumber1(rs.getInt(7));
				fdd.setFLightNumber2(rs.getInt(8));
				fdd.setDepartureTime(rs.getTime(9));
				fdd.setArrivalTime(rs.getTime(10));
				fdd.setSecDepartureTime(rs.getTime(11));
				fdd.setSecArrivalTime(rs.getTime(12));
				fdd.setFare(rs.getInt(13)*nopass);
				list.add(fdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;

	}

	

	@Override
	public boolean isDomestic(String string, String string2) {
		// TODO Auto-generated method stub
		
		String query = "select a1.Country , a2.Country "
				+ "from Airport a1 , Airport a2 "
				+ "where a1.Name = ? "
				+ " and a2.Name = ? ";
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, string);
			ps.setString(2, string2);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				System.out.println(rs.getString(1) + " " + rs.getString(2));
				if(rs.getString(1).equals(rs.getString(2)))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public int[] bookFlights(FDetailsDisplay fd, String flight, String[] passengerNames, String[] passengerPassport,
			LocalDate date, LocalDate date2 , int bookingFee , int totalFare ,String email) {
		// TODO Auto-generated method stub
		
		
		String query = "select AccountNumber from Customer where Email = ?" , query2 = "";
		PreparedStatement ps , ps2;
		int CustomerAcc;
		int ReservationDetails[] = new int [2];
		ReservationDetails[0] = -1;
		ReservationDetails[1] = -1;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				CustomerAcc = rs.getInt(1);
				System.out.println(CustomerAcc + " Account");
				query = "insert into Reservation (DateOfTravel , BookingFee , Passengers , TotalFare , CustomerAcc) values(? , ? , ? , ? , ?)";
				ps = con.prepareStatement(query);
				ps.setDate(1, Date.valueOf(date));
				ps.setInt(2, bookingFee);
				ps.setInt(3, passengerNames.length);
				ps.setInt(4, totalFare);
				ps.setInt(5, CustomerAcc);
				
				int i = ps.executeUpdate();
				System.out.println(i + " iiiii");
				if(i > 0)
				{
					query = "insert into Reservation (DateOfTravel , BookingFee , Passengers , TotalFare , CustomerAcc) values(? , ? , ? , ? , ?)";
					ps = con.prepareStatement(query);
					ps.setDate(1, Date.valueOf(date2));
					ps.setInt(2, bookingFee);
					ps.setInt(3, passengerNames.length);
					ps.setInt(4, totalFare);
					ps.setInt(5, CustomerAcc);
					
					i = ps.executeUpdate();
					
					if(i > 0)
					{
						query = "select max(ReservationNumber) from Reservation";
						ps = con.prepareStatement(query);
						
						rs = ps.executeQuery();
						if(rs.next())
						{
							int ReservationDate2 = rs.getInt(1);
							int ReservationDate = ReservationDate2 - 1;
							
							query = "insert into Leg (StopId1 , StopId2 , FlightNumber , ReservationNumber) values (? , ? , ? , ?)";
							query2 = "insert into Leg (StopId1 , StopId2 , FlightNumber , ReservationNumber) values (? , ? , ? , ?)";;
							
							ps = con.prepareStatement(query);
							ps2 = con.prepareStatement(query2);
							ps.setInt(1, fd.getStopId1());
							ps.setInt(2, fd.getStopId2());
							ps.setInt(3, fd.getFLightNumber1());
							ps.setInt(4, ReservationDate);
							ps2.setInt(1, fd.getStopId3());
							ps2.setInt(2, fd.getStopId4());
							ps2.setInt(3, fd.getFLightNumber2());
							ps2.setInt(4, ReservationDate2);
							
							i = ps.executeUpdate();
							i = i + ps2.executeUpdate();
							
							if(i > 0)
							{
								int k = 0;
								
								for(int p = 0 ; p < passengerNames.length ; p++)
								{
									query = "insert into Passengers (ReservationNumber , Passport , PassengerName) values (? , ? , ?)";
									ps = con.prepareStatement(query);
									ps.setInt(1, ReservationDate);
									ps.setString(2, passengerPassport[p]);
									ps.setString(3, passengerNames[p]);
									
									k = k + ps.executeUpdate();
									
								}
								
								for(int p = 0 ; p < passengerNames.length ; p++)
								{
									query = "insert into Passengers (ReservationNumber , Passport , PassengerName) values (? , ? , ?)";
									ps = con.prepareStatement(query);
									ps.setInt(1, ReservationDate2);
									ps.setString(2, passengerPassport[p]);
									ps.setString(3, passengerNames[p]);
									
									k = k + ps.executeUpdate();
									
								}
								
								if(k > 0)
								{
									ReservationDetails[0] = ReservationDate;
									ReservationDetails[1] = ReservationDate2;
									return ReservationDetails;
								}
							}
						}
						
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//String query = "insert into Reservation (DateOfTravel , BookingFee , Passengers , TotalFare , CustomerAcc) values()";
		return ReservationDetails;
		
		
	}

	@Override
	public int bookFlights(FDetailsDisplay fd, String flight, String[] passengerNames, String[] passengerPassport,
			LocalDate depart , int bookingFee , int totalFare , String email) {
		// TODO Auto-generated method stub
		
		String query = "select AccountNumber from Customer where Email = ?" , query2 = "" , query3 = "" , query4 = "";
		PreparedStatement ps , ps2 , ps3;
		int CustomerAcc , ReservationNumber;
		boolean flag = false;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				CustomerAcc = rs.getInt(1);
				query = "insert into Reservation (DateOfTravel , BookingFee , Passengers , TotalFare , CustomerAcc) values (? , ? , ? , ? , ?);";
				ps = con.prepareStatement(query);
				if(flight.equals("differentDays"))
				{
					ps.setDate(1, (Date) fd.getDateFlight());
				}
				else
				{
					ps.setDate(1, Date.valueOf(depart));
				}
				
				
				ps.setInt(2, bookingFee);
				ps.setInt(3, passengerNames.length);
				ps.setInt(4, totalFare);
				ps.setInt(5, CustomerAcc);
				
				int i = ps.executeUpdate();
				
				if(i == 1)
				{
					query = "select max(ReservationNumber) from Reservation";
					ps = con.prepareStatement(query);
					
					rs = ps.executeQuery();
					
					if(rs.next())
					{
						System.out.println(rs.getInt(1));
						ReservationNumber = rs.getInt(1);
						
						if(flight.equals("direct") || flight.equals("differentDays"))
						{
							query = "insert into Leg (StopId1 , StopId2 , FlightNumber , ReservationNumber) values (? , ? , ? , ?);";
							System.out.println("direct");
							ps = con.prepareStatement(query);
							ps.setInt(1, fd.getStopId1());
							ps.setInt(2, fd.getStopId2());
							ps.setInt(3, fd.getFLightNumber1());
							ps.setInt(4, ReservationNumber);
							
							i = ps.executeUpdate();
							if(i > 0)
							{
								flag = true;
							}
						}
						else if(flight.equals("one"))
						{
							query = "insert into Leg (StopId1 , StopId2 , FlightNumber , ReservationNumber) values (? , ? , ? , ?)";
							query2 = "insert into Leg (StopId1 , StopId2 , FlightNumber , ReservationNumber) values (? , ? , ? , ?)";
							
							System.out.println(fd.getStopId1() + " " + fd.getStopId3());
							
							ps = con.prepareStatement(query);
							ps2 = con.prepareStatement(query2);
							ps.setInt(1, fd.getStopId1());
							ps.setInt(2, fd.getStopId2());
							ps.setInt(3, fd.getFLightNumber1());
							ps.setInt(4, ReservationNumber);
							ps2.setInt(1, fd.getStopId3());
							ps2.setInt(2, fd.getStopId4());
							ps2.setInt(3, fd.getFLightNumber2());
							ps2.setInt(4, ReservationNumber);
							
							i = ps.executeUpdate();
							i = i + ps2.executeUpdate();
							
							if(i > 0)
							{
								flag = true;
							}
						}
						else if(flight.equals("two"))
						{
							query = "insert into Leg (StopId1 , StopId2 , FlightNumber , ReservationNumber) values (? , ? , ? , ?)";
							query2 =  "insert into Leg (StopId1 , StopId2 , FlightNumber , ReservationNumber) values (? , ? , ? , ?)";
							query3 = "insert into Leg (StopId1 , StopId2 , FlightNumber , ReservationNumber) values (? , ? , ? , ?)";
							
							ps = con.prepareStatement(query);
							ps2 = con.prepareStatement(query2);
							ps3 = con.prepareStatement(query3);
							ps.setInt(1, fd.getStopId1());
							ps.setInt(2, fd.getStopId2());
							ps.setInt(3, fd.getFLightNumber1());
							ps.setInt(4, ReservationNumber);
							ps2.setInt(1, fd.getStopId3());
							ps2.setInt(2, fd.getStopId4());
							ps2.setInt(3, fd.getFLightNumber2());
							ps2.setInt(4, ReservationNumber);
							ps3.setInt(1, fd.getStopId5());
							ps3.setInt(2, fd.getStopId6());
							ps3.setInt(3, fd.getFLightNumber3());
							ps3.setInt(4, ReservationNumber);
							
							
							i = ps.executeUpdate();
							i = i + ps2.executeUpdate();
							i = i + ps3.executeUpdate();
							
							if(i > 0)
							{
								flag = true;
							}
						}
						else if(flight.equals("three"))
						{
							query = "insert into Leg (StopId1 , StopId2 , FlightNumber , ReservationNumber) values (? , ? , ? , ?);"
									+ "insert into Leg (StopId1 , StopId2 , FlightNumber , ReservationNumber) values (? , ? , ? , ?);"
									+ "insert into Leg (StopId1 , StopId2 , FlightNumber , ReservationNumber) values (? , ? , ? , ?);"
									+ "insert into Leg (StopId1 , StopId2 , FlightNumber , ReservationNumber) values (? , ? , ? , ?);";
							ps = con.prepareStatement(query);
							ps.setInt(1, fd.getStopId1());
							ps.setInt(2, fd.getStopId2());
							ps.setInt(3, fd.getFLightNumber1());
							ps.setInt(4, ReservationNumber);
							ps.setInt(5, fd.getStopId3());
							ps.setInt(6, fd.getStopId4());
							ps.setInt(7, fd.getFLightNumber2());
							ps.setInt(8, ReservationNumber);
							ps.setInt(9, fd.getStopId5());
							ps.setInt(10, fd.getStopId6());
							ps.setInt(11, fd.getFLightNumber3());
							ps.setInt(12, ReservationNumber);
							ps.setInt(13, fd.getStopId7());
							ps.setInt(14, fd.getStopId8());
							ps.setInt(15, fd.getFLightNumber4());
							ps.setInt(16, ReservationNumber);
						}
						
						
						i = ps.executeUpdate();
						
						if(flag)
						{
							int k = 0;
							
							for(int p = 0 ; p < passengerNames.length ; p++)
							{
								query = "insert into Passengers (ReservationNumber , Passport , PassengerName) values (? , ? , ?)";
								ps = con.prepareStatement(query);
								ps.setInt(1, ReservationNumber);
								ps.setString(2, passengerPassport[p]);
								ps.setString(3, passengerNames[p]);
								
								k = k + ps.executeUpdate();
								
							}
							
							if(k > 0)
							{
								return ReservationNumber;
							}
												
						}
					}
					
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return -1;
	}

	@Override
	public ArrayList<Reservation> getCurrentReservations(String email) {
		ArrayList<Reservation> list = new ArrayList<Reservation>();
		String query = "select r.ReservationNumber , r.DateOfTravel , r.BookingFee , r.Passengers , r.TotalFare from Reservation r , Customer c"
				+ " where r.CustomerAcc = c.AccountNumber"
				+ " and c.Email = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Date date = rs.getDate(2);
				if((LocalDate.now().until(date.toLocalDate(), ChronoUnit.DAYS)) > 0)
				{
					Reservation r = new Reservation();
					r.setReservationNumber(rs.getInt(1));
					r.setDateOfTravel(date);
					r.setBookingFee(rs.getInt(3));
					r.setPassengers(rs.getInt(4));
					r.setTotalFare(rs.getInt(5));
					list.add(r);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public Reservation getReservationByReservationNumber(String email, int ReservationNumber) {
		// TODO Auto-generated method stub
		Reservation r = new Reservation();
		String query = "select * from Reservtion r , Customer c"
				+ " where r.CustomerAcc = c.AccountNumber"
				+ " and r.ReservationNumber = ?"
				+ " and c.Email = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, ReservationNumber);
			ps.setString(2, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
					r.setReservationNumber(rs.getInt(1));
					r.setDateOfTravel(rs.getDate(2));
					r.setBookingFee(rs.getInt(3));
					r.setPassengers(rs.getInt(5));
					r.setTotalFare(rs.getInt(6));
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return r;
	}


	@Override
	public ArrayList<Reservation> getAllReservations(String email) {
		// TODO Auto-generated method stub
		ArrayList<Reservation> list = new ArrayList<Reservation>();
		String query = "select r.ReservationNumber , r.BookingFee , r.Passengers , r.TotalFare , r.DateOfTravel from Reservation r , Customer c"
				+ " where r.CustomerAcc = c.AccountNumber"
				+ " and c.Email = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
					Reservation r = new Reservation();
					r.setReservationNumber(rs.getInt(1));
					r.setBookingFee(rs.getInt(2));
					r.setPassengers(rs.getInt(3));
					r.setTotalFare(rs.getInt(4));
					r.setDateOfTravel(rs.getDate(5));
					list.add(r);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}


	@Override
	public ArrayList<RevenueSummary> getBestSellerFLightNumber() {
		// TODO Auto-generated method stub
		Connection con = dbConnection.getConnection();
		ArrayList<RevenueSummary> rsummary = new ArrayList<RevenueSummary>();

			String query = "select count(*) as count, sum(r.TotalFare) as sum, l.FlightNumber"
					+ " from Reservation r , Leg l"
					+ " where r.ReservationNumber = l.ReservationNumber"
					+ " group by l.FlightNumber"
					+ " order by count , sum DESC"
					+ " limit 5";
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					RevenueSummary r = new RevenueSummary(rs.getInt(1), rs.getInt(2) , rs.getInt(3));
					rsummary.add(r);
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return rsummary;
	}

	
}
	


