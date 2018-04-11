package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import dbConnection.dbConnection;
import pojo.Customer;
import pojo.Login;

public class LoginDaoImpl implements LoginDao {

	@Override
	public String login(Login user) {
		// TODO Auto-generated method stub
		String query = "";
		Connection con = dbConnection.getConnection();
		
		
		PreparedStatement ps;
		try {
			if(user.getRole().equals("customer")) {
				query = "select c.FirstName, l.Password from CustomerLogin l, Customer c where l.Email = c.Email and c.email = ?";
				
				ps = con.prepareStatement(query);
				ps.setString(1, user.getEmail());
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					if(user.getPassword().equals(rs.getString(2)))
					{
						
						return rs.getString(1);
					}
					else
					{
						return "-1";
					}
				}
			}else if( user.getRole().equals("manager")) {
				query = "select Password from ManagerLogin where email = ?";
				
				ps = con.prepareStatement(query);
				ps.setString(1, user.getEmail());
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					if(user.getPassword().equals(rs.getString(1)))
					{
						return "admin";
					}
					else
					{
						return "-1";
					}
				}
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}

	@Override
	public boolean signup(Customer cust) {
		// TODO Auto-generated method stub

		Connection con = dbConnection.getConnection();
		
		String query = "insert into Customer(LastName, FirstName, Address, City, StateLine, ZipCode, Telephone, Email, AccountCreationDate, CreditCardNumber, Rating) values (?,?,?,?,?,?,?,?,?,?,?);";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(1, cust.getLastname());
			ps.setString(2, cust.getFirstname());
			ps.setString(3, cust.getAddress());
			ps.setString(4, cust.getCity());
			ps.setString(5, cust.getStateLine());
			ps.setString(6, cust.getZipcode());
			ps.setString(7, cust.getTelephone());
			ps.setString(8, cust.getEmail());
			ps.setDate(9, Date.valueOf(LocalDate.now()));
			ps.setString(10, cust.getCreditCardNumber());
			ps.setInt(11, cust.getRating());
			
			
			int r = ps.executeUpdate();
			
			if (r == 1 ){
				String query_login = "insert into CustomerLogin values (?,?);";
				ps = con.prepareStatement(query_login);
				ps.setString(1, cust.getEmail());
				ps.setString(2, cust.getPassword());
				
				int s = ps.executeUpdate();
				 if (s == 1) {
					 
					 System.out.println("sign up successful!");
					 return true;
				 }
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
