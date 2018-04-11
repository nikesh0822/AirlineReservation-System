package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import dbConnection.dbConnection;
import pojo.AutoCompleteData;

/**
 * Servlet implementation class AjaxRequest
 */
@WebServlet("/AjaxRequest")
public class AjaxRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxRequest() {
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
		//System.out.println("post");
	        ArrayList<AutoCompleteData> results = new ArrayList<AutoCompleteData>();
	       String query = request.getParameter("term");
	        query = query.toLowerCase();
	        
	        Connection con = dbConnection.getConnection();
	        Gson gson = new Gson();
	        String sqlQuery = "";
	        
	        if(request.getParameter("type").equals("city")) {
	        
			sqlQuery = "select City from Airport where City like '" + query + "%'";
			PreparedStatement ps;
			
			try {
				ps = con.prepareStatement(sqlQuery);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					results.add(new AutoCompleteData(rs.getString(1),rs.getString(1))); 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        }
	        else if (request.getParameter("type").equals("flight_number")) {
		        
				sqlQuery = "select Name from Airport where Name like '" + query + "%'";
				PreparedStatement ps;
				
				try {
					ps = con.prepareStatement(sqlQuery);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						results.add(new AutoCompleteData(rs.getString(1),rs.getString(1))); 
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		        }
	        else if(request.getParameter("type").equals("custName")) {
		        
				sqlQuery = "select FirstName from Customer where FirstName like '" + query + "%'";
				PreparedStatement ps;
				
				try {
					ps = con.prepareStatement(sqlQuery);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						results.add(new AutoCompleteData(rs.getString(1),rs.getString(1))); 
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		        }
	        else if(request.getParameter("type").equals("airportName")) {
		        
				sqlQuery = "select Name from Airport where Name like '" + query + "%'";
				PreparedStatement ps;
				
				try {
					ps = con.prepareStatement(sqlQuery);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						results.add(new AutoCompleteData(rs.getString(1),rs.getString(1))); 
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		        }
	        
	        response.getWriter().write(gson.toJson(results));
	        
	}

}
