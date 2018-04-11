package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import dao.CustomerDaoImpl;
import pojo.FDetailsDisplay;
import pojo.Reservation;
import pojo.RevenueSummary;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		CustomerDao dao = new CustomerDaoImpl();
		String action = request.getParameter("action");

		if (action.equals("tripChoice")) {
			String trip = request.getParameter("trip");
			System.out.println(trip + "check1");
			session.setAttribute("trips", trip);
			response.sendRedirect("customerHome.jsp");

		} 
		
		
		else if (action.equals("singleChoices")) {
		System.out.println("singleChoices");
			
			String from = request.getParameter("from");
			String to = request.getParameter("to");
			session.setAttribute("from", from);
			session.setAttribute("to", to);
			LocalDate depart = LocalDate.parse(request.getParameter("date"));
			session.setAttribute("depart", depart);
			int numberOfPassengers = Integer.parseInt(request.getParameter("numberOfPassengers"));
			session.setAttribute("numberOfPassengers", numberOfPassengers);
			
			ArrayList<FDetailsDisplay> list = dao.getFlightsSingle(from, to, depart, numberOfPassengers);
			System.out.println(list + "listPP");
			if (list.size() != 0) {
				session.setAttribute("flight", "direct");
				session.setAttribute("list", list);
				System.out.println(list.size() + "1");
				response.sendRedirect("flightDetails.jsp");
			} 
			
			else {
				list = dao.getFlightsOneStop(from, to, depart, numberOfPassengers);
				System.out.println(list + "listPP2");
				if(list.size() != 0)
				{
					session.setAttribute("flight", "one");
					session.setAttribute("list", list);
					System.out.println(list.size() + "2");
					response.sendRedirect("flightDetails.jsp");
				}
				else
				{
					list = dao.getFlightsTwoStop(from, to, depart, numberOfPassengers);
					System.out.println(list + "listPP3");
					if(list.size() != 0)
					{
						session.setAttribute("flight", "two");
						session.setAttribute("list", list);
						System.out.println(list.size() + "3");
						response.sendRedirect("flightDetails.jsp");
					}
					else
					{
						//list = dao.getFlightsThreeStop(from, to, depart, numberOfPassengers);
						
						if(list.size() != 0)
						{
							session.setAttribute("flight", "three");
							session.setAttribute("list", list);
							System.out.println(list.size() + "4");
							response.sendRedirect("flightDetails.jsp");
						}
						else
						{
							depart = depart.minus(1, ChronoUnit.DAYS);
							list = dao.getFlightsSingle(from, to, depart, numberOfPassengers);
							
							for(FDetailsDisplay fdd : list)
							{
								fdd.setDateFlight(Date.valueOf(depart));
							}
							depart = depart.plus(2, ChronoUnit.DAYS);
							ArrayList<FDetailsDisplay> list2 = new ArrayList<FDetailsDisplay>();
							
							list2 = dao.getFlightsSingle(from, to, depart, numberOfPassengers);
							for(FDetailsDisplay fdd : list2)
							{
								fdd.setDateFlight(Date.valueOf(depart));
								System.out.println(fdd.toString() + "hello");
							}
							list.addAll(list2);
							System.out.println(list.size());
							session.setAttribute("list", list);
							session.setAttribute("flight", "differentDays");
							response.sendRedirect("flightDetails.jsp");
						}
					}
				}
			}

		} else if (action.equals("roundChoices")) {

			ArrayList<FDetailsDisplay> list = new ArrayList<FDetailsDisplay>();
			String from = request.getParameter("from");
			String to = request.getParameter("to");
			session.setAttribute("from", from);
			session.setAttribute("to", to);
			LocalDate depart = LocalDate.parse(request.getParameter("date"));
			LocalDate returnDate = LocalDate.parse(request.getParameter("date2"));
			session.setAttribute("date", depart);
			session.setAttribute("date2", returnDate);
			int numberOfPassengers = Integer.parseInt(request.getParameter("numberOfPassengers"));
			session.setAttribute("numberOfPassengers", numberOfPassengers);
			list = dao.getFlightsRoundTrip(from, to, depart, returnDate, numberOfPassengers);
			
			if(list.size() != 0)
			{
				session.setAttribute("list", list);
				session.setAttribute("flight", "round");
				response.sendRedirect("flightDetails.jsp");
			}
			else
			{
				session.setAttribute("flight", "none");
				response.sendRedirect("customerHome.jsp");
			}
		}
		else if(action.equals("flightOptions"))
		{
			ArrayList<FDetailsDisplay> list = (ArrayList<FDetailsDisplay>) session.getAttribute("list");
			for(FDetailsDisplay fdd : list)
			{
				System.out.println(fdd.toString());
			}
			
			int objectId = Integer.parseInt(request.getParameter("flightSelection"));
			session.setAttribute("objectId", objectId);
			boolean isDomestic = dao.isDomestic((String)session.getAttribute("from") , (String)session.getAttribute("to"));
			System.out.println(isDomestic + " isDomestic");
			session.setAttribute("isDomestic", isDomestic);
			
			FDetailsDisplay fd = new FDetailsDisplay();
			for(FDetailsDisplay fdd : list)
			{
				if(objectId == fdd.getObjectId())
				{
					fd = fdd;
				}
			}
			
			session.setAttribute("fdSelcted", fd);
			
			response.sendRedirect("PassengerOptions.jsp");
			
		}
		else if(action.equals("PassengerInfo"))
		{
			int numberOfPassengers = (int)session.getAttribute("numberOfPassengers");
			String[] PassengerNames = new String[numberOfPassengers];
			
			for(int i = 0 ; i < numberOfPassengers ; i++)
			{
				PassengerNames[i] = request.getParameter("Passenger"+i);
				System.out.println(PassengerNames[i]);
			}
			session.setAttribute("PassengerNames", PassengerNames);
			
			String[] PassengerPassport = new String[numberOfPassengers];
			
			for(int i = 0 ; i < PassengerPassport.length ; i++)
			{
				PassengerPassport[i] = "NoPassportRequired";
				System.out.println(PassengerPassport[i]);
			}
			
			if(!(boolean)session.getAttribute("isDomestic"))
			{
				for(int i = 0 ; i < numberOfPassengers ; i++)
				{
					PassengerPassport[i] = request.getParameter("PassengerPassport"+i);
				}
			}
			session.setAttribute("PassengerPassport", PassengerPassport);
			
			String flight = (String)session.getAttribute("flight");
			LocalDate date;
			if("round".equals(flight))
			{
				date = (LocalDate)session.getAttribute("date");
			}
			else
			{
				date = (LocalDate)session.getAttribute("depart");
			}
				
			FDetailsDisplay fd = (FDetailsDisplay) session.getAttribute("fdSelcted");
			LocalDate today = LocalDate.now();
			double discount = 0;
			
			System.out.println(today + " " + date  + " " + ChronoUnit.DAYS);
			
			int days = (int) today.until(date, ChronoUnit.DAYS);
			
			System.out.println(days);
			if(days >= 21)
			{
				discount = fd.getFare()*0.15;
			}
			else if(days >= 14)
			{
				discount = fd.getFare()*0.1;
			}
			else if(days >= 7)
			{
				discount = fd.getFare()*0.07;
			}
			else if(days >= 3)
			{
				discount = fd.getFare()*0.05;
			}
			
			session.setAttribute("discount", discount);
			
			session.setAttribute("bookingFee", (fd.getFare()+discount)*0.05);
			response.sendRedirect("Confirm.jsp");
		}
		else if(action.equals("confirmSubmit"))
		{
			double bookingFee = (double)session.getAttribute("bookingFee");
			int totalFare = (int)session.getAttribute("totalFare");
			FDetailsDisplay fd = (FDetailsDisplay) session.getAttribute("fdSelcted");
			String flight = (String) session.getAttribute("flight");
			String[] passengerNames  = (String[]) session.getAttribute("PassengerNames");
			String[] passengerPassport  = (String[]) session.getAttribute("PassengerPassport");
			for(int i = 0 ; i < passengerPassport.length ; i++)
			{
				System.out.println(passengerPassport[i]);
			}
			String email = (String) session.getAttribute("customerEmail");
			LocalDate date , date2 , depart;
			int booking = -1;
			int bookingRound[] = new int[2];
			
			for(int i = 0 ; i < passengerNames.length ; i++)
			{
				System.out.println(passengerNames[i]);
			}
			
			if(flight.equals("round"))
			{
				date = (LocalDate) session.getAttribute("date");
				date2 = (LocalDate) session.getAttribute("date2");
				bookingRound = dao.bookFlights(fd, flight , passengerNames, passengerPassport , date , date2 , (int)bookingFee , totalFare , email);
				
				session.setAttribute("bookingRound", bookingRound);
			}
			else
			{
				depart = (LocalDate)session.getAttribute("depart");
				System.out.println(depart + " " + fd.toString() + " " + flight + " " + bookingFee + totalFare +  " " + email);
				booking = dao.bookFlights(fd, flight, passengerNames, passengerPassport, depart , (int)bookingFee , totalFare , email);
				session.setAttribute("booking", booking);
			}
			
			response.sendRedirect("ShowFinalDetails.jsp");
			
		}
		else if(action.equals("GoToHome"))
		{
			response.sendRedirect("customerHome.jsp");
		}
		else if(action.equals("CurrentReservationDetails"))
		{
			ArrayList<Reservation> list = new ArrayList<Reservation>();
			list = dao.getCurrentReservations((String)session.getAttribute("email"));
			
			session.setAttribute("Reservationlist", list);
			session.setAttribute("Reservations", "Current");
			response.sendRedirect("ShowReservations.jsp");
		}
		else if(action.equals("AllReservations"))
		{
			ArrayList<Reservation> list = new ArrayList<Reservation>();
			
			list = dao.getAllReservations((String)session.getAttribute("email"));
			session.setAttribute("showAllReservations", list);
			response.sendRedirect("showAllReservations.jsp");
		}
		else if(action.equals("getBestSeller"))
		{
			ArrayList<RevenueSummary> list = dao.getBestSellerFLightNumber();
			session.setAttribute("showBestSeller", list);
			response.sendRedirect("showBestSeller.jsp");
		}

		doGet(request, response);
	}

}
