package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {
	
	public static Connection getConnection()
	{
		
		try { 
			String dbURL = "jdbc:mysql://rdbs.curegc5xypta.us-east-2.rds.amazonaws.com:3306/arms"; 
			String username ="shruthi"; 
			String password = "shruthi98"; 
			Connection dbCon; 
			//getting database connection to MySQL server 
			Class.forName("com.mysql.jdbc.Driver");
			dbCon = DriverManager.getConnection(dbURL, username, password);
			System.out.println(dbCon);
			return dbCon;
		}
			catch (Exception ex) 
		{ 
				System.out.println(ex);
				return null;
		}
	}

	public static void main(String args[]) 
	{ 
		Connection dbCon = getConnection();
		if(dbCon != null)
		{
			System.out.println("connection established");
		}
		else
		{
			System.out.println("connection failed");
		}
	}
	
	

}
