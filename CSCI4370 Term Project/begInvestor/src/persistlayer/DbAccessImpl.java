package persistlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAccessImpl extends DbAccessConfig {
	
	//Open Connection//
	public static Connection connect() throws SQLException
	{
		Connection connection = null;
		try {
			Class.forName(DB_DRIVE_NAME);
			connection = DriverManager.getConnection( DB_CONNECTION_URL,  DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//Retrieve ResultSet of query return//
	public static ResultSet retrieve (String query) throws SQLException
	{
		Connection conn = connect();
		ResultSet rset = null;
		try {
			Statement stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rset;
	}
	
	public static ResultSet retrieve (Connection conn, String query) throws SQLException
	{
		ResultSet rset = null;
		try {
			Statement stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rset;
	}
	
	//Create new entry//
	public static int create (String query) throws SQLException
	{
		Connection connection = connect();
		int r = 0;
		try {
			Statement s = connection.createStatement();
			r = s.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect(connection);
		}
		return r;
	}
	
	public static int update (String query) throws SQLException
	{
		return create(query);
		
	}
	
	public static int delete (String query) throws SQLException
	{
		return create(query);
	}
	
	//Disconnect from server//
	public static void disconnect(Connection con)
	{
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}

