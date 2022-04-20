package hrc_backend;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	
	static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost:3306/grey_goose";
	static final String uname = "root";
	static final String pass = "sageop1234";
	private static Connection conn;
	
	
	public static Connection getConnection() throws Exception{
		
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(url, uname, pass);
			System.out.println("Post establishing a DB connection - " +conn);
			
		}
		catch(SQLException e)
		{
			System.out.println("Error Occurred");
			e.printStackTrace();
		}
		return conn;
	}
	
}	
