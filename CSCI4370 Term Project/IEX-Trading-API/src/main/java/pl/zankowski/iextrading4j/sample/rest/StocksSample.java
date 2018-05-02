package pl.zankowski.iextrading4j.sample.rest;

import pl.zankowski.iextrading4j.api.stocks.Company;
import pl.zankowski.iextrading4j.api.stocks.KeyStats;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.api.stocks.TimeSeries;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StocksSample {

    private final IEXTradingClient iexTradingClient = IEXTradingClient.create();

    public static void main(String[] args) {
        final StocksSample sampleSuite = new StocksSample();
        Connection conn = JdbcConnection.connect();
        
       
        
        File file = new File("companylist.txt");
        
        
        try {
        		BufferedReader br = new BufferedReader(new FileReader(file));
		
        		String stock ;
        	    		
        		while((stock = br.readLine()) != null) {

        			Statement statement = conn.createStatement();
        			      			
        			statement.executeUpdate(sampleSuite.getHisPrice(stock));
        			statement.executeUpdate(sampleSuite.getStockInfo(stock));
        			statement.executeUpdate(sampleSuite.getMetrics(stock));
        			statement.executeUpdate(sampleSuite.getCurPrice(stock));
        			 
        		}
     		
        		conn.close();
        }catch(Exception e){
        		e.printStackTrace();
        }
        
    }
  
  
    public String getStockInfo(String stock) {
    		final Quote quote = iexTradingClient.executeRequest(new QuoteRequestBuilder()
                .withSymbol(stock)
                .build());
                
        final Company company = iexTradingClient.executeRequest(new CompanyRequestBuilder()
                .withSymbol(stock)
                .build());
    		
      
    		System.out.println( "  Stock ID: "		+ stock + "\n" +		
    						 "  company: "		+ company.getCompanyName() + "\n" +
    				         "  Industry: "		+ company.getIndustry() +"\n" +
    						 "  Description: " 	+ company.getDescription() +"\n" +
    				         "  URL: "			+ company.getWebsite() + "\n" +
    						 "  Latest: "		+ quote.getLatestPrice() +"\n" +
    						 "  Opening: "		+ quote.getOpen() + "\n" +
    						 "  Closing: "		+ quote.getClose() +"\n" +
    						 "  Change:  "		+ quote.getChange() +"\n" +
    						 "  High: "			+ quote.getHigh() +"\n" +
    						 "  Low: "			+ quote.getLow());
    		System.out.println("---------------------------------------------");
  
    		String companyAfter = "";
    		String companyDes = company.getDescription();
    		if(companyDes == null) {
    			companyAfter = "N/A";
    		} else {
    			companyAfter = companyDes.replace("'", " ");
    			companyAfter = companyAfter.replace("&", " ");
    		}
    		
    		String companyName = company.getCompanyName();
    		if(companyName == null) {
    			companyName = "N/A";
    		}else {
    			companyName = companyName.replace("'", " ");
    		}
    		String industry = company.getIndustry();
    		if(industry == null) {
    			industry = "N/A";
    		}else {
    			industry = industry.replace("'", " ");
    		}
    		String web = company.getWebsite();
    		if(web == null) {
    			web = "N/A";
    		}else {
    			web = web.replace("'", " ");
    		}
    		
    		String result = "INSERT INTO Stock (StockID, company, industry, description, URL) values ('"+ stock+ "','"
    													  + companyName+"','"
    													  + industry+"','"
    													  + companyAfter+"','"
    													  + web+"')";
    		
    		System.out.println("result: " + result);
    		System.out.println("getStockInfo done");
    		return result;
    		
    }
    
    public String getCurPrice(String stock) {
    	
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
    
    		
		String result = "INSERT INTO curPrice (StockID, Latest, Opening, Closing, Changes, High, Low) VALUES ('"+ stock+ "','"
														  + lastPrice + "','"
														  + open + "','"
														  + close + "','"
														  + change + "','"
														  + high + "','"
														  + low + "')";
		
		System.out.println("result: " + result);
    		System.out.println("getCurPrice done");
    		return result;
    }
    
    public String getHisPrice(String stock) {
    	
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
    		
    		
    		
		String result = "INSERT INTO hisPrice (StockID, day1, day2, day3, "
				+ "day4, day5, day6, day7, day8, day9, day10, day11, day12, "
				+ "day13, day14, day15) "
				+ "VALUES ('"+ stock+ "','"
							+ day1 +"','"+ day2 +"','"+ day3 +"','"
							+ day4 +"','"+ day5 +"','"+ day6 +"','"
							+ day7 +"','"+ day8 +"','"+ day9 +"','"
							+ day10 +"','"+ day11 +"','"+ day12 +"','"
							+ day13 +"','"+ day14 +"','"+ day15 +"')";
							   		
		System.out.println("result: " + result);
		System.out.println("getHisPrice done");
		return result;
    }
    public String getMetrics(String stock) {
    	
		final KeyStats keyStats = iexTradingClient.executeRequest(new KeyStatsRequestBuilder()
                .withSymbol(stock)
                .build());   
		final Quote quote = iexTradingClient.executeRequest(new QuoteRequestBuilder()
                .withSymbol(stock)
                .build());
		
		double marketCap = 0;
		if(quote.getMarketCap() != null) {
			marketCap = quote.getMarketCap().doubleValue();
		}
		double week52change = -1000;
		if(keyStats.getWeek52change() != null) {
			week52change = keyStats.getWeek52change().doubleValue();
		}
		double week52high = 0;
		if(keyStats.getWeek52high() != null) {
			week52high = keyStats.getWeek52high().doubleValue();
		}
		double week52low = 0;
		if(keyStats.getWeek52low() != null) {
			week52low = keyStats.getWeek52low().doubleValue();
		}
		double peratio = 0;
		if(quote.getPeRatio() != null) {
			peratio = quote.getPeRatio().doubleValue();
		}
		double div = 0;
		if(keyStats.getDividendYield() != null) {
			div = keyStats.getDividendYield().doubleValue();
		}
		double roa = 0;
		if(keyStats.getReturnOnAssets() != null) {
			roa = keyStats.getReturnOnAssets().doubleValue();
		}
		double eps = 0;
		if(keyStats.getLatestEPS() != null) {
			eps = keyStats.getLatestEPS().doubleValue();
		}
		double beta = 0;
		if(keyStats.getBeta() != null) {
			beta = keyStats.getBeta().doubleValue();
		}
		double ytdchange = -10000;
		if(quote.getYtdChange() != null) {
			ytdchange = quote.getYtdChange().doubleValue();
		}
    	
		String result = "INSERT INTO Metrics (StockID, marketCap, Week52change, Week52high, Week52low, PE_Ratio, DivYield, ROA, EPS, Beta, ytdchange) "
				+ "VALUES('"+ stock+ "','"
				+ marketCap + "','"
				+ week52change + "','"
				+ week52high + "','"
				+ week52low+ "','"
				+ peratio + "','"
				+ div + "','"
				+ roa + "','"
				+ eps + "','"
				+ beta + "','"
				+ ytdchange + "')";   		
		
		System.out.println("result: " + result);
		System.out.println("getMetrics done");
		return result;
    }

    

}
