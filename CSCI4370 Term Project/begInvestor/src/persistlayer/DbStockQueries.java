package persistlayer;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import persistlayer.DbAccessImpl;

public class DbStockQueries {
	public static List<String> getStockInfo(String input, Connection con) throws SQLException {
		List<String> stockInfo = new ArrayList<String>();
		String query = "SELECT DISTINCT S.stockID, "
				     + "company, industry, description, URL, "
				     + "Latest, Opening, Closing, High, Low, "
				     + "marketCap, EPS, Beta, DivYield, "
				     + "Week52high, Week52low, "
				     + "PE_Ratio, ROA "   
				     + "From Stock S "
				     + "JOIN curPrice C ON S.stockID = C.stockID "
				     + "JOIN Metrics M On S.stockID = M.stockID "
				     + "WHERE S.company = \""
				     + input
				     + "\";";
		//String queryPriceInfo = "select * from curPrice WHERE stockID = \"" + input + "\";";
		//String queryMetricInfo = "select * from Metrics WHERE stockID = \"" + input + "\";";
		ResultSet rs = DbAccessImpl.retrieve(con, query);
		ResultSetMetaData metadata = rs.getMetaData();
		int columnCount = metadata.getColumnCount();    
		System.out.println(columnCount);
		//if(!rs.first()){
			//String errorMssg = "The symbol is invalid";
			//stockInfo.add(errorMssg);
			//return stockInfo;
		//}
		//else{
			while(rs.next())	
			{	
				for (int i = 1; i <= columnCount; i++) {
					stockInfo.add(rs.getString(i));
					
				}
			}
			for(int j = 0; j < stockInfo.size(); j ++){
				System.out.println(stockInfo.get(j));
			}
			
			return stockInfo;
				
		//}
		
		
	}
	public static List<String> getStockHisPrices(String input, Connection con) throws SQLException {
		List<String> prices = new ArrayList<String>();;
		String query = "SELECT * FROM hisPrice H JOIN Stock S ON S.stockID = H.stockID WHERE S.company = \""
				     +  input
				     + "\";";
		ResultSet rs = DbAccessImpl.retrieve(con, query);
		ResultSetMetaData metadata = rs.getMetaData();
		int columnCount = metadata.getColumnCount();  
		while(rs.next())	
		{	
			for (int i = 2; i <= columnCount; i++) {
				String price = rs.getString(i);	
			    prices.add(price);
			}
		}
	  return  prices;
	}
	
	public static String getCompanyList() throws SQLException {
		Connection con =DbAccessImpl.connect();
		String companyList = "";
		String query = "select company from Stock;";
		ResultSet rs = DbAccessImpl.retrieve(con, query); 
		while(rs.next())	
		{	
			
			companyList += "\"" + rs.getString(1) + "\", ";	
			
		}
	  return companyList;
	}

	public static ArrayList<ArrayList<ArrayList<String>>> getRankResults(String industry, boolean pricechange, boolean beta, boolean marketcap, 
			boolean eps, boolean peratio, boolean dividend, Connection con) throws SQLException{
		ArrayList<ArrayList<ArrayList<String>>> rankResults = new ArrayList<ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> pcrank = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> betarank = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> mcrank = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> epsrank = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> perrank = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> divrank = new ArrayList<ArrayList<String>>();
		
			
			if(pricechange) {
				String query;
				if(industry.equals("All Industry")) {
					query = "SELECT S.stockID, S.company, ROUND(M.Week52high-M.Week52low,2) AS Price_Change\n" + 
							"FROM Stock S\n" + 
							"JOIN Metrics M ON S.stockID = M.stockID \n" + 
							"WHERE S.company <> \"N/A\"\n" + 
							"AND M.Beta > 0\n" + 
							"AND M.Week52high <> 0 AND M.Week52low <> 0\n" + 
							"ORDER BY Price_Change ASC\n" + 
							"LIMIT 10;";
				}else {
					query = "SELECT S.stockID, S.company, ROUND(M.Week52high-M.Week52low,2) AS Price_Change\n" + 
							"FROM Stock S\n" + 
							"JOIN Metrics M ON S.stockID = M.stockID\n" + 
							"AND S.industry = \""
							+ industry
							+ "\"\n" + 
							"WHERE S.company <> \"N/A\"\n" + 
							"AND M.Beta > 0\n" + 
							"AND M.Week52high <> 0 AND M.Week52low <> 0\n" + 
							"ORDER BY Price_Change ASC\n" + 
							"LIMIT 10;";
				}
				ResultSet rs = DbAccessImpl.retrieve(con, query);
				ResultSetMetaData metadata = rs.getMetaData();
				int columnCount = metadata.getColumnCount();
				
				int count = 0;
				while(rs.next()){
					count++;
					ArrayList<String> stk = new ArrayList<String>();
					for (int j = 1; j <= columnCount; j++) {
						String value = rs.getString(j);	
					    stk.add(value);
					}//for: each row's values
					pcrank.add(stk);
				}//while: rs move to next row
				
				if(count < 10) {
					for(int k = 0; k < 10-count; k++) {
						ArrayList<String> stk = new ArrayList<String>();
						stk.add("N/A");
						stk.add("N/A");
						stk.add("-1");
						pcrank.add(stk);
					}
				}
				
			}
			
			if(beta) {
				String query;
				if(industry.equals("All Industry")) {
					query = "SELECT S.stockID, S.company, M.Beta\n" + 
							"FROM Stock S\n" + 
							"JOIN Metrics M ON S.stockID = M.stockID\n" + 
							"WHERE S.company <> \"N/A\"\n" + 
							"AND M.Beta > 0\n" + 
							"ORDER BY M.Beta ASC\n" + 
							"LIMIT 10;";
				}else {
					query = "SELECT S.stockID, S.company, M.Beta\n" + 
							"FROM Stock S\n" + 
							"JOIN Metrics M ON S.stockID = M.stockID\n" + 
							"WHERE S.company <> \"N/A\"\n" + 
							"AND M.Beta > 0\n" + 
							"AND S.industry = \""
							+ industry
							+ "\"\n" + 
							"ORDER BY M.Beta ASC\n" + 
							"LIMIT 10;";
				}
				ResultSet rs = DbAccessImpl.retrieve(con, query);
				ResultSetMetaData metadata = rs.getMetaData();
				int columnCount = metadata.getColumnCount();
				
				int count = 0;
				while(rs.next()){
					count++;
					ArrayList<String> stk = new ArrayList<String>();
					for (int j = 1; j <= columnCount; j++) {
						String value = rs.getString(j);	
					    stk.add(value);
					}//for: each row's values
					betarank.add(stk);
				}//while: rs move to next row
				
				if(count < 10) {
					for(int k = 0; k < 10-count; k++) {
						ArrayList<String> stk = new ArrayList<String>();
						stk.add("N/A");
						stk.add("N/A");
						stk.add("-1");
						betarank.add(stk);
					}
				}
			}
			if(marketcap) {
				String query;
				if(industry.equals("All Industry")) {
					query = "SELECT S.stockID, S.company, M.marketCap\n" + 
							"FROM Stock S\n" + 
							"JOIN Metrics M ON S.stockID = M.stockID \n" + 
							"WHERE S.company <> \"N/A\"\n" + 
							"AND M.Beta > 0\n" + 
							"AND M.marketCap <> 0\n" + 
							"ORDER BY  M.marketCap DESC\n" + 
							"LIMIT 10;";
				}else {
					query = "SELECT S.stockID, S.company, M.marketCap\n" + 
							"FROM Stock S\n" + 
							"JOIN Metrics M ON S.stockID = M.stockID \n" + 
							"WHERE S.company <> \"N/A\"\n" + 
							"AND M.Beta > 0\n" + 
							"AND  M.marketCap <> 0 \n" + 
							"AND S.industry = \""
							+ industry
							+ "\"\n" + 
							"ORDER BY  M.marketCap DESC\n" + 
							"LIMIT 10;";
				}
				ResultSet rs = DbAccessImpl.retrieve(con, query);
				ResultSetMetaData metadata = rs.getMetaData();
				int columnCount = metadata.getColumnCount();
				
				int count =0;
				while(rs.next()){
					count++;
					ArrayList<String> stk = new ArrayList<String>();
					for (int j = 1; j <= columnCount; j++) {
						String value = rs.getString(j);	
					    stk.add(value);
					}//for: each row's values
					mcrank.add(stk);
				}//while: rs move to next row
				
				if(count < 10) {
					for(int k = 0; k < 10-count; k++) {
						ArrayList<String> stk = new ArrayList<String>();
						stk.add("N/A");
						stk.add("N/A");
						stk.add("-1");
						mcrank.add(stk);
					}
				}
			}
			
			if(eps) {
				String query;
				if(industry.equals("All Industry")) {
					query = "SELECT S.stockID, S.company, M.EPS\n" + 
							"FROM Stock S\n" + 
							"JOIN Metrics M ON S.stockID = M.stockID \n" + 
							"WHERE S.company <> \"N/A\"\n" + 
							"AND M.Beta > 0\n" + 
							"AND M.EPS <> 0 \n" + 
							"ORDER BY  M.EPS DESC\n" + 
							"LIMIT 10;";
				}else {
					query = "SELECT S.stockID, S.company, M.EPS\n" + 
							"FROM Stock S\n" + 
							"JOIN Metrics M ON S.stockID = M.stockID \n" + 
							"WHERE S.company <> \"N/A\"\n" + 
							"AND M.Beta > 0\n" + 
							"AND  M.EPS <> 0 \n" + 
							"AND S.industry = \""
							+ industry
							+ "\"\n" + 
							"ORDER BY  M.EPS DESC\n" + 
							"LIMIT 10;";
				}
				ResultSet rs = DbAccessImpl.retrieve(con, query);
				ResultSetMetaData metadata = rs.getMetaData();
				int columnCount = metadata.getColumnCount();
				
				int count = 0;
				while(rs.next()){
					count++;
					ArrayList<String> stk = new ArrayList<String>();
					for (int j = 1; j <= columnCount; j++) {
						String value = rs.getString(j);	
					    stk.add(value);
					}//for: each row's values
					epsrank.add(stk);
				}//while: rs move to next row
				
				if(count < 10) {
					for(int k = 0; k < 10-count; k++) {
						ArrayList<String> stk = new ArrayList<String>();
						stk.add("N/A");
						stk.add("N/A");
						stk.add("-1");
						epsrank.add(stk);
					}
				}
			}
			
			if(peratio) {
				String query;
				if(industry.equals("All Industry")) {
					query = "SELECT S.stockID, S.company, M.PE_Ratio\n" + 
							"FROM Stock S\n" + 
							"JOIN Metrics M ON S.stockID = M.stockID \n" + 
							"WHERE S.company <> \"N/A\"\n" + 
							"AND M.Beta > 0\n" + 
							"AND M.PE_Ratio <> 0 \n" + 
							"ORDER BY M.PE_Ratio DESC\n" + 
							"LIMIT 10;";
				}else {
					query = "SELECT S.stockID, S.company, M.PE_Ratio\n" + 
							"FROM Stock S\n" + 
							"JOIN Metrics M ON S.stockID = M.stockID \n" + 
							"WHERE S.company <> \"N/A\"\n" + 
							"AND M.Beta > 0\n" + 
							"AND M.PE_Ratio <> 0 \n" + 
							"AND S.industry = \""
							+ industry
							+ "\"\n" + 
							"ORDER BY M.PE_Ratio DESC\n" + 
							"LIMIT 10;";
				}
				ResultSet rs = DbAccessImpl.retrieve(con, query);
				ResultSetMetaData metadata = rs.getMetaData();
				int columnCount = metadata.getColumnCount();
				
				int count =0;
				while(rs.next()){
					count++;
					ArrayList<String> stk = new ArrayList<String>();
					for (int j = 1; j <= columnCount; j++) {
						String value = rs.getString(j);	
					    stk.add(value);
					}//for: each row's values
					perrank.add(stk);
				}//while: rs move to next row
				
				if(count < 10) {
					for(int k = 0; k < 10-count; k++) {
						ArrayList<String> stk = new ArrayList<String>();
						stk.add("N/A");
						stk.add("N/A");
						stk.add("-1");
						perrank.add(stk);
					}
				}
			}
			
			if(dividend) {
				String query;
				if(industry.equals("All Industry")) {
					query = "SELECT S.stockID, S.company, ROUND(M.DivYield,2)\n" + 
							"FROM Stock S\n" + 
							"JOIN Metrics M ON S.stockID = M.stockID \n" + 
							"WHERE S.company <> \"N/A\"\n" + 
							"AND M.Beta > 0\n" + 
							"AND M.DivYield > 0 \n" + 
							"ORDER BY M.DivYield DESC\n" + 
							"LIMIT 10;";
				}else {
					query = "SELECT S.stockID, S.company, ROUND(M.DivYield,2)\n" + 
							"FROM Stock S\n" + 
							"JOIN Metrics M ON S.stockID = M.stockID \n" + 
							"WHERE S.company <> \"N/A\"\n" + 
							"AND M.Beta > 0\n" + 
							"AND M.DivYield > 0 \n" + 
							"AND S.industry = \""
							+ industry
							+ "\"\n" + 
							"ORDER BY M.DivYield DESC\n" + 
							"LIMIT 10;";
				}
				ResultSet rs = DbAccessImpl.retrieve(con, query);
				ResultSetMetaData metadata = rs.getMetaData();
				int columnCount = metadata.getColumnCount();
				
				int count = 0;
				while(rs.next()){
					count++;
					ArrayList<String> stk = new ArrayList<String>();
					for (int j = 1; j <= columnCount; j++) {
						String value = rs.getString(j);	
					    stk.add(value);
					}//for: each row's values
					divrank.add(stk);
				}//while: rs move to next row
				
				if(count < 10) {
					for(int k = 0; k < 10-count; k++) {
						ArrayList<String> stk = new ArrayList<String>();
						stk.add("N/A");
						stk.add("N/A");
						stk.add("-1");
						divrank.add(stk);
					}
				}
			}
			
			rankResults.add(pcrank);
			rankResults.add(betarank);
			rankResults.add(mcrank);
			rankResults.add(epsrank);
			rankResults.add(perrank);
			rankResults.add(divrank);
		return rankResults;
	}//getRankResults
}
