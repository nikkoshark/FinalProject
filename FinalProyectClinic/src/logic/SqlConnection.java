package logic;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnection {
	public static Connection getConnection(){
		String url =
	           "jdbc:sqlserver://localhost:1433; "
	         + "databaseName=clinica_m_f; "
	         + "trustServerCertificate=true;"
	         + "loginTimeout=30";

	    String user = "mawi"; 
	    String psw = "1234"; 
			
		try {
			Connection conn = DriverManager.getConnection(url, user, psw);
			return conn;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
}
