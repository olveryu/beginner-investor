package objectlayer;
import java.sql.SQLException;

import persistlayer.DbStockQueries;

public class GetCompanyList {

	public static void main(String[] args) throws SQLException {
		System.out.println(DbStockQueries.getCompanyList());

	}

}
