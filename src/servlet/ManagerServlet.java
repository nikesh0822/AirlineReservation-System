package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import dao.LoginDaoImpl;
import dao.ManagerDao;
import dao.ManagerDaoImpl;
import pojo.Customer;
import pojo.CustomerByFlight;
import pojo.FDisplay;
import pojo.FlightStatus;
import pojo.Reservation;
import pojo.RevenueSummary;

/**
 * Servlet implementation class ManagerServlet
 */
@WebServlet("/ManagerServlet")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		
		
		if(request.getParameter("manager_selected_option").equals("add_customer_info")) {
			
			LoginDao logindao = new LoginDaoImpl();
			
			String Firstname = request.getParameter("firstname");
			String Lastname = request.getParameter("lastname");
			String Address = request.getParameter("address");
			String Telephone = request.getParameter("telephone");
			String City = request.getParameter("city");
			String Stateline = request.getParameter("stateline");
			String Zipcode = request.getParameter("zipcode");
			String CreditCardNumber = request.getParameter("creditCardNumber");
			String Email = request.getParameter("email");
			String Password = request.getParameter("password");
			LocalDate AccountCreationDate = LocalDate.now();
					
			Customer cust = new Customer(Lastname, Firstname, Address, City, Stateline, Zipcode, Telephone, Email, AccountCreationDate, CreditCardNumber, -1, Password);
			boolean flag = logindao.signup(cust);
			request.setAttribute("addFlag", flag);
			
			System.out.println(session + "hello100000000000" + flag);
			if(flag) {
				System.out.println("customer successfully added!!");
			}else {
				System.out.println("customer addition failed!!");
			}
			
			request.getRequestDispatcher("managerAdd.jsp").forward(request, response);
			//response.sendRedirect("managerAdd.jsp");
			
		}
		else if(request.getParameter("manager_selected_option").equals("edit_customer_info")) {
			
			
			ManagerDao mdao = new ManagerDaoImpl();
			
			String customerAccountNumber = request.getParameter("customer_account_number");
			
			String fieldBeingModified = request.getParameter("field_being_modified");
			String queryParameter = "";
			if(fieldBeingModified.equals("LN")) {
				queryParameter = "LastName = '" + request.getParameter("lname_name_edit") +"'";
			}else if(fieldBeingModified.equals("FN")) {
				queryParameter = "FirstName = '" + request.getParameter("fname_name_edit") + "'";
			}else if(fieldBeingModified.equals("Add")) {
				queryParameter = "Address = '" + request.getParameter("add_name_edit") + "'";
			}else if(fieldBeingModified.equals("City")) {
				queryParameter = "City = '" + request.getParameter("city_name_edit") + "'";
			}else if(fieldBeingModified.equals("SL")) {
				queryParameter = "StateLine = '" + request.getParameter("state_name_edit") + "'";
			}else if(fieldBeingModified.equals("ZC")) {
				queryParameter = "ZipCode = '" + request.getParameter("zip_name_edit") + "'";
			}else if(fieldBeingModified.equals("Tel")) {
				queryParameter = "Telephone = '" + request.getParameter("tel_name_edit") + "' ";
			}
			
			boolean flag = mdao.edit(customerAccountNumber, queryParameter);
			request.setAttribute("addFlag", flag);
			if(flag) {
				System.out.println("update successfull!");
			}else {
				System.out.println("update Failed!");
			}
			request.getRequestDispatcher("managerAdd.jsp").forward(request, response);
			
		}else if(request.getParameter("manager_selected_option").equals("delete_customer_info")) {
			ManagerDao mdao = new ManagerDaoImpl();
			String accountNumber = request.getParameter("acc_number_to_be_deleted");
			boolean flag = mdao.delete(accountNumber);
			request.setAttribute("addFlag", flag);
			if(flag){
				System.out.println("deletion successfull!");
			}else {
				System.out.println("deletion Failed!");
			}
			request.getRequestDispatcher("managerAdd.jsp").forward(request, response);
		}else if(request.getParameter("manager_selected_option").equals("list_all_flights")) {
			ManagerDao mdao = new ManagerDaoImpl();
			System.out.println(mdao.listAllFlights());
			
			ArrayList<FDisplay> allFlights = mdao.listAllFlights();
			request.setAttribute("allFlights", allFlights);
			request.getRequestDispatcher("listAllFlights.jsp").forward(request, response);
			
		}else if(request.getParameter("manager_selected_option").equals("flight_Status")) {
			ManagerDao mdao = new ManagerDaoImpl();
			ArrayList<FlightStatus> allFlights = mdao.listAllFlightsStatus();
			request.setAttribute("allFlights", allFlights);
			request.getRequestDispatcher("flightStatus.jsp").forward(request, response);
			
		}else if(request.getParameter("manager_selected_option").equals("most_revenue_customer")) {
			
			ManagerDao mdao =new ManagerDaoImpl();
			String[] cs = mdao.getCustomerWhoGeneratedTheMostRevenue();
			//System.out.println(cs.getFirstname() + " " + cs.getLastname());
			request.setAttribute("customer", cs);
			request.getRequestDispatcher("mostRevenueCustomer.jsp").forward(request, response);
			
		}else if(request.getParameter("manager_selected_option").equals("most_active_flights")) {
			
			ManagerDao mdao = new ManagerDaoImpl();
			ArrayList<FDisplay> mostActive = mdao.mostActiveFLights();
			request.setAttribute("mostActive", mostActive);
			request.getRequestDispatcher("mostActiveFlights.jsp").forward(request, response);
			
		}else if(request.getParameter("manager_selected_option").equals("flight_Status")){
			
			
			
		}else if(request.getParameter("manager_selected_option").equals("customers_on_flight")) {
			
			ManagerDao mdao = new ManagerDaoImpl();
			int flightNumber = Integer.parseInt(request.getParameter("get_customer_data_for_flight_number"));
			
			System.out.println(mdao.listCustomersForGivenFlight(flightNumber));
			
			
			ArrayList<CustomerByFlight> list = mdao.listCustomersForGivenFlight(flightNumber);
			session.setAttribute("flightNumber", flightNumber);
			session.setAttribute("custByFlight", list);
			response.sendRedirect("custByFlight.jsp");
			
		}else if(request.getParameter("manager_selected_option").equals("all_flights_that_stop_at_airport")) {
			
			ManagerDao mdao = new ManagerDaoImpl();
			String airportName = request.getParameter("airport_name");
			System.out.println(mdao.getFLightsByAirport(airportName));
			
			ArrayList<FDisplay> list = mdao.getFLightsByAirport(airportName);
			session.setAttribute("flightByAirport", list);
			response.sendRedirect("flightByAirport.jsp");
			
		}else if(request.getParameter("manager_selected_option").equals("res_by_flight_number_or_cust")) {
			System.out.println("entered the right function");
			ManagerDao mdao = new ManagerDaoImpl();
			if (request.getParameter("reservations").equals("fNumber_res")) {
				System.out.println("by flight number");
				int flightNumber = Integer.parseInt(request.getParameter("FlightNum"));
				System.out.println(mdao.getReservationsByFlightNumber(flightNumber));
				ArrayList<Reservation> list = mdao.getReservationsByFlightNumber(flightNumber);
				session.setAttribute("reservationByFlight", list);
				response.sendRedirect("reservationByFlight.jsp");
			}else if (request.getParameter("reservations").equals("cName_res")) {
				System.out.println("by customer name");
				String customerName = request.getParameter("CustName");
				System.out.println(customerName);
				System.out.println(mdao.getReservationsByCustomerName(customerName));
				ArrayList<Reservation> list = mdao.getReservationsByCustomerName(customerName);
				session.setAttribute("reservationByCustomer", list);
				response.sendRedirect("reservationByCustomer.jsp");
			}
			
			
		}else if(request.getParameter("manager_selected_option").equals("revenue_listing")) {
			ManagerDao mdao = new ManagerDaoImpl();
			if (request.getParameter("summary").equals("fNumber_revSummary_radio_value")) {
				System.out.println("by flight number");
				int flightNumber = Integer.parseInt(request.getParameter("FlightNumber"));
				System.out.println(mdao.getRevenueSummaryByFLightNumber(flightNumber));
				
				ArrayList<RevenueSummary> list = mdao.getRevenueSummaryByFLightNumber(flightNumber);
				session.setAttribute("revenueByFlight", list);
				response.sendRedirect("revenueByFlight.jsp");
				//session.setAttribute("", arg1);
				
			}else  if (request.getParameter("summary").equals("destCity_revSummary_radio_value")) {
				System.out.println("by destination city");
				String destinationCity = request.getParameter("DestCity");
				System.out.println(mdao.getRevenueSummaryByDestinationCity(destinationCity));
				ArrayList<RevenueSummary> list = mdao.getRevenueSummaryByDestinationCity(destinationCity);
				session.setAttribute("revenueByDestination", list);
				response.sendRedirect("revenueByDestination.jsp");
				
			}else  if (request.getParameter("summary").equals("cName_revSummary_radio_value")) {
				System.out.println("by customer name");
				String customerName = request.getParameter("CustName");
				System.out.println(mdao.getRevenueSummaryByCustomer(customerName));
				
				
				ArrayList<RevenueSummary> list = mdao.getRevenueSummaryByCustomer(customerName);
				session.setAttribute("revenueByCustomer", list);
				response.sendRedirect("revenueByCustomer.jsp");
			}
			
			
		}else if(request.getParameter("manager_selected_option").equals("sales_report_for_month")) {
			ManagerDao mdao = new ManagerDaoImpl();
			int monthNumber = Integer.parseInt(request.getParameter("selected_month"));
			System.out.println(mdao.getSummarReportForMonth(monthNumber));
			
			ArrayList<Reservation> list = mdao.getSummarReportForMonth(monthNumber);
			session.setAttribute("summaryForMonth", list);
			response.sendRedirect("summaryForMonth.jsp");
			
		}
		
		
		System.out.println(request.getParameter("manager_selected_option"));
		//doGet(request, response);
	}

}
