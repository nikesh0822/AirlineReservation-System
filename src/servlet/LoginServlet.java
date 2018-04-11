package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import dao.LoginDaoImpl;
import pojo.Customer;
import pojo.Login;
/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String action = request.getParameter("action");
		
		if("signup".equals(action))
		{
			response.sendRedirect("signup.jsp");
		}
		else if(action.equals("login"))
		{
			HttpSession session = request.getSession(true);
			LoginDao logindao = new LoginDaoImpl();
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String role = request.getParameter("role");
			Login user = new Login(email, password, role);
			
			String flag = logindao.login(user);
			
			
			if(!flag.equals("-1") && role.equals("customer"))
			{
				System.out.println("Login Successful!");
				System.out.println("flag");
				session.setAttribute("email", email);
				session.setAttribute("customerEmail", email);
				response.sendRedirect("customerHome.jsp");
			}
			else if(!flag.equals("-1") && role.equals("manager"))
			{
				response.sendRedirect("managerHome.jsp");
				System.out.println(flag);
			}
			else
			{
				response.sendRedirect("failure.jsp");
			}
		}
		else if(action.equals("actual_signup")){
			
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

			if(flag) {
				request.setAttribute("check", true);
				response.sendRedirect("login.jsp");
			}else {
				System.out.println("sign up failed");
			}
			
		}
		else if(action.equals("logout"))
		{
			HttpSession session = request.getSession(false);
			session.invalidate();
			
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.sendRedirect("login.jsp");
			
		}
		doGet(request, response);
	}

}
