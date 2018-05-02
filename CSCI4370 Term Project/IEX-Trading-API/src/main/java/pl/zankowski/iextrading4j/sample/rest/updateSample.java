package pl.zankowski.iextrading4j.sample.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.zankowski.iextrading4j.api.stocks.KeyStats;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.api.stocks.TimeSeries;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.KeyStatsRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.TimeSeriesRequestBuilder;

public class updateSample {
	
	 private final IEXTradingClient iexTradingClient = IEXTradingClient.create();
	
	public static void main(String[] args) {
		
		final updateSample sampleSuite = new updateSample();
        Connection conn = JdbcConnection.connect();
              
        File file = new File("companylist.txt");
        
        
        try {
        		BufferedReader br = new BufferedReader(new FileReader(file));
        		        		
        		String stock ;
        	    		
        		while((stock = br.readLine()) != null) {

        			Statement statement = conn.createStatement();
        		      			
        			statement.executeUpdate(sampleSuite.updateCurPrice(stock));
        			statement.executeUpdate(sampleSuite.updateMetrics(stock));
        			statement.executeUpdate(sampleSuite.updateCurPrice(stock));

        		}

        		
        		
        		//conn.close();
        }catch(Exception e){
        		e.printStackTrace();
        }
		
	}
	
	
	
	 public String updateCurPrice(String stock) {
	    	
 		final Quote quote = iexTradingClient.executeRequest(new QuoteRequestBuilder()
             .withSymbol(stock)
             .build());
 	
 		double lastPrice = 0;
 		if(quote.getLatestPrice() != null) {
 			lastPrice = quote.getLatestPrice().doubleValue();
 		}
 		double open = 0;
 		if(quote.getOpen() != null) {
 			open = quote.getOpen().doubleValue();
 		}
 		double close = 0;
 		if(quote.getClose() != null) {
 			close = quote.getClose().doubleValue();
 		}
 		double change = 0;
 		if(quote.getChange() != null) {
 			change = quote.getChange().doubleValue();
 		}
 		double high = 0;
 		if(quote.getHigh() != null) {
 			high = quote.getHigh().doubleValue();
 		}
 		double low = 0;
 		if(quote.getLow() != null) {
 			low = quote.getLow().doubleValue();
 		}
 
 		String update = "UPDATE curPrice set latest = '"+ lastPrice + "', Opening ='" 
 														+ open + "', Closing ='" 
 														+ close +"', High='"
 														+ high +"', Low='"
 														+ low +"' WHERE StockID='"
 														+stock+"'";
 		
		
		System.out.println("result: " + update);
 		System.out.println("updateCurPrice done");
 		return update;
 }
 
 public String updateHisPrice(String stock) {
 	
 		final List<TimeSeries> timeSeriesList = iexTradingClient.executeRequest(new TimeSeriesRequestBuilder()
             .withSymbol(stock)
             .build());
 		
 		double day1 = 0, day2 = 0, day3 = 0, day4 = 0, day5 = 0, day6 = 0, day7 = 0, day8 = 0, day9 = 0, day10 = 0, day11 = 0, day12 = 0, day13 = 0, day14 = 0, day15 = 0, day16 = 0, day17 = 0, day18 = 0, day19 = 0, day20 = 0;
 		
 		
 		
 		List<Double> test = new ArrayList<>();
 		
 	
 		for(int j = timeSeriesList.size()-1; j >= 0; j--) {
 			
 			if(timeSeriesList.get(j).getHigh() != null) {
 				test.add(timeSeriesList.get(j).getHigh().doubleValue());
 			} else {
 				test.add(0.0);
 			}
 		}
 		
 		for(int i = test.size()-1; i >= 0; i--) {
 			if(i == 0) {
 				day1 = test.get(i);
 			}
 			if(i == 1) {
 				day2 = test.get(i);
 			}
 			if(i == 2) {
 				day3 = test.get(i);
 			}
 			if(i == 3) {
 				day4 = test.get(i);
 			}
 			if(i == 4) {
 				day5 = test.get(i);
 			}
 			if(i == 5) {
 				day6 = test.get(i);
 			}
 			if(i == 6) {
 				day7 = test.get(i);
 			}
 			if(i == 7) {
 				day8 = test.get(i);
 			}
 			if(i == 8) {
 				day9 = test.get(i);
 			}
 			if(i == 9) {
 				day10 = test.get(i);
 			}
 			if(i == 10) {
 				day11 = test.get(i);
 			}
 			if(i == 11) {
 				day12 = test.get(i);
 			}
 			if(i == 12) {
 				day13 = test.get(i);
 			}
 			if(i == 13) {
 				day14 = test.get(i);
 			}
 			if(i == 14) {
 				day15 = test.get(i);
 			}
 		}
 		

 
 		
 		String update = "UPDATE hisPrice set day1 = '" + day1 + "', day2 = '"
 														+ day2 + "', day3 = '"
 														+ day3 + "', day4 = '"
 														+ day4 + "', day5 = '"
 														+ day5 + "', day6 = '"
 														+ day6 + "', day7 = '"
 														+ day7 + "', day8 = '"
 														+ day8 + "', day9 = '"
 														+ day9 + "', day10 = '"
 														+ day10 + "', day11 = '"
 														+ day11 + "', day12 = '"
 														+ day12 + "', day13 = '"
 														+ day13 + "', day14 = '"
 														+ day14 + "', day15 = '"
 														+ day15 + "' WHERE StockID='"
 														+ stock +"'";
 		
							   		
		System.out.println("result: " + update);
		System.out.println("updateHisPrice done");
		return update;
 	}
 
 public String updateMetrics(String stock) {
 	  
		final Quote quote = iexTradingClient.executeRequest(new QuoteRequestBuilder()
             .withSymbol(stock)
             .build());
		
		double marketCap = 0;
		if(quote.getMarketCap() != null) {
			marketCap = quote.getMarketCap().doubleValue();
		}
	
		String update = "UPDATE Metrics set marketCap ='" + marketCap + "' WHERE StockID = '" + stock +"'";
 	
		
		System.out.println("result: " + update);
		System.out.println("updateMetrics done");
		return update;
 }
 
 
}
