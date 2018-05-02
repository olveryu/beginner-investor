package persistlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class DatabaseAccess {
	
	static final String DRIVE_NAME = "com.mysql.jdbc.Driver";
	
	static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/stockDB?useSSL=false";
		
	static final String DB_CONNECTION_USERNAME = "root";
		
	static final String DB_CONNECTION_PASSWORD = "Weijike5";
	
	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName(DRIVE_NAME);
			con = DriverManager.getConnection(CONNECTION_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	} // end of connect
	
	public static ResultSet retrieve (Connection con, String query) {
		ResultSet rset = null;
		try {
			Statement stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			return rset;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rset;
	}// end of retrieve
	
	//make an insert
	public static int create(String query, Connection con){
		ResultSet rs;
		int id = -1;
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			rs = stmt.executeQuery("SELECT LAST_INSERT_ID() as last_id from users");
			rs.next();
			id = rs.getInt("last_id");
		} catch (SQLException e) {
			return -5;
		}
		return id;
	}
	
	public static void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of closeConnection

}
