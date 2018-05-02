package boundarylayer;

import objectlayer.Stock;
import logiclayer.StockAnalysis;
import persistlayer.DbAccessImpl;
import persistlayer.DbStockQueries;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;


/**
 * Servlet implementation class LoadPage
 */
@WebServlet("/StockServlet")
public class StockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // variables store the user input
	boolean ifSearchStock = false;

  
	Configuration cfg = null;

	private String templateDir = "/WEB-INF/templates";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Set Up configuration
     */
	public void init() {
	
		cfg = new Configuration(Configuration.VERSION_2_3_25);

		// Specify the source where the template files come from.
		cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
       
		// Sets how errors will appear.
		
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        
	}
    public String searchOneStock(Connection con, String input) throws SQLException{
    	
    	  List<String> stockInfo = DbStockQueries.getStockInfo(input,con);
    	  List<String> stockPrices = DbStockQueries.getStockHisPrices(input,con);
    	  String stockID = stockInfo.get(0);
    	  String company = stockInfo.get(1);
    	  String industry = stockInfo.get(2);
    	  String description = stockInfo.get(3);
    	  String url = stockInfo.get(4);
          
    	  double curPrice = Double.parseDouble(stockInfo.get(5));
    	  double openPrice = Double.parseDouble(stockInfo.get(6));
    	  double closePrice = Double.parseDouble(stockInfo.get(7));
    	  double highPrice = Double.parseDouble(stockInfo.get(8));
    	  double lowPrice = Double.parseDouble(stockInfo.get(9));
    	  double marketCap= Double.parseDouble(stockInfo.get(10));
    	  double eps = Double.parseDouble(stockInfo.get(11));
    	  double beta = Double.parseDouble(stockInfo.get(12));
    	  double div = Double.parseDouble(stockInfo.get(13));
    	  double week52H = Double.parseDouble(stockInfo.get(14));
    	  double week52L = Double.parseDouble(stockInfo.get(15));
    	  double peRatio = Double.parseDouble(stockInfo.get(16));
    	  double roa = Double.parseDouble(stockInfo.get(17));
    
    	  
    	  double price1 = Double.parseDouble(stockPrices.get(0));
    	  double price2 = Double.parseDouble(stockPrices.get(1));;
    	  double price3 = Double.parseDouble(stockPrices.get(2));;
    	  double price4 = Double.parseDouble(stockPrices.get(3));;
    	  double price5 = Double.parseDouble(stockPrices.get(4));;
    	  double price6 = Double.parseDouble(stockPrices.get(5));;
    	  double price7 = Double.parseDouble(stockPrices.get(6));;
    	  double price8 = Double.parseDouble(stockPrices.get(7));;
    	  double price9 = Double.parseDouble(stockPrices.get(8));;
    	  double price10 = Double.parseDouble(stockPrices.get(9));;
    	  double price11 = Double.parseDouble(stockPrices.get(10));;
    	  double price12 = Double.parseDouble(stockPrices.get(11));;
    	  double price13 = Double.parseDouble(stockPrices.get(12));;
    	  double price14 = Double.parseDouble(stockPrices.get(13));;
    	  double price15 = Double.parseDouble(stockPrices.get(14));;

    	  
	      Stock stk = new Stock(stockID, company, industry, description, url, curPrice, openPrice, closePrice, highPrice, lowPrice, marketCap, eps, beta, div, peRatio, roa, week52H, week52L,
	    		                price1, price2, price3, price4, price5, price6, price7, price8, price9, price10,
			                    price11, price12, price13, price14, price15);
	      String json = new Gson().toJson(stk);
	     
	      return json;

    }
    
    public String compareTwoStock(Connection con, String input1, String input2) throws SQLException{
    	
  	  List<String> stockInfo1 = DbStockQueries.getStockInfo(input1,con);
  	  List<String> stockInfo2 = DbStockQueries.getStockInfo(input2,con);
  	  List<String> stockPrices1 = DbStockQueries.getStockHisPrices(input1,con);
  	  List<String> stockPrices2 = DbStockQueries.getStockHisPrices(input2,con);
  	  String priceWinner = "";
  	  String returnWinner = "";
  	  String riskWinner = "";
  	  String marketDominant = "";
	  String stockID = stockInfo1.get(0);
	  String company = stockInfo1.get(1);
	  String industry = stockInfo1.get(2);
	  String description = stockInfo1.get(3);
	  String url = stockInfo1.get(4);
      
	  double curPrice = Double.parseDouble(stockInfo1.get(5));
	  double openPrice = Double.parseDouble(stockInfo1.get(6));
	  double closePrice = Double.parseDouble(stockInfo1.get(7));
	  double highPrice = Double.parseDouble(stockInfo1.get(8));
	  double lowPrice = Double.parseDouble(stockInfo1.get(9));
	  double marketCap= Double.parseDouble(stockInfo1.get(10));
	  double eps = Double.parseDouble(stockInfo1.get(11));
	  double beta = Double.parseDouble(stockInfo1.get(12));
	  double div = Double.parseDouble(stockInfo1.get(13));
	  double week52H = Double.parseDouble(stockInfo1.get(14));
	  double week52L = Double.parseDouble(stockInfo1.get(15));
	  double peRatio = Double.parseDouble(stockInfo1.get(16));
	  double roa = Double.parseDouble(stockInfo1.get(17));
	  
	  String stockID2 = stockInfo2.get(0);
	  String company2 = stockInfo2.get(1);
	  String industry2 = stockInfo2.get(2);
	  String description2 = stockInfo2.get(3);
	  String url2 = stockInfo2.get(4);
      
	  double curPrice2 = Double.parseDouble(stockInfo2.get(5));
	  double openPrice2 = Double.parseDouble(stockInfo2.get(6));
	  double closePrice2 = Double.parseDouble(stockInfo2.get(7));
	  double highPrice2 = Double.parseDouble(stockInfo2.get(8));
	  double lowPrice2 = Double.parseDouble(stockInfo2.get(9));
	  double marketCap2 = Double.parseDouble(stockInfo2.get(10));
	  double eps2 = Double.parseDouble(stockInfo2.get(11));
	  double beta2 = Double.parseDouble(stockInfo2.get(12));
	  double div2 = Double.parseDouble(stockInfo2.get(13));
	  double week52H2 = Double.parseDouble(stockInfo2.get(14));
	  double week52L2 = Double.parseDouble(stockInfo2.get(15)); 
	  double peRatio2 = Double.parseDouble(stockInfo2.get(16));
	  double roa2 = Double.parseDouble(stockInfo2.get(17));
 
 
	  double price1 = Double.parseDouble(stockPrices1.get(0));
	  double price2 = Double.parseDouble(stockPrices1.get(1));;
	  double price3 = Double.parseDouble(stockPrices1.get(2));;
	  double price4 = Double.parseDouble(stockPrices1.get(3));;
	  double price5 = Double.parseDouble(stockPrices1.get(4));;
	  double price6 = Double.parseDouble(stockPrices1.get(5));;
	  double price7 = Double.parseDouble(stockPrices1.get(6));;
	  double price8 = Double.parseDouble(stockPrices1.get(7));;
	  double price9 = Double.parseDouble(stockPrices1.get(8));;
	  double price10 = Double.parseDouble(stockPrices1.get(9));;
	  double price11 = Double.parseDouble(stockPrices1.get(10));;
	  double price12 = Double.parseDouble(stockPrices1.get(11));;
	  double price13 = Double.parseDouble(stockPrices1.get(12));;
	  double price14 = Double.parseDouble(stockPrices1.get(13));;
	  double price15 = Double.parseDouble(stockPrices1.get(14));;
	
	  double price1B = Double.parseDouble(stockPrices2.get(0));
	  double price2B = Double.parseDouble(stockPrices2.get(1));;
	  double price3B = Double.parseDouble(stockPrices2.get(2));;
	  double price4B = Double.parseDouble(stockPrices2.get(3));;
	  double price5B = Double.parseDouble(stockPrices2.get(4));;
	  double price6B = Double.parseDouble(stockPrices2.get(5));;
	  double price7B = Double.parseDouble(stockPrices2.get(6));;
	  double price8B = Double.parseDouble(stockPrices2.get(7));;
	  double price9B = Double.parseDouble(stockPrices2.get(8));;
	  double price10B = Double.parseDouble(stockPrices2.get(9));;
	  double price11B = Double.parseDouble(stockPrices2.get(10));;
	  double price12B = Double.parseDouble(stockPrices2.get(11));;
	  double price13B = Double.parseDouble(stockPrices2.get(12));;
	  double price14B = Double.parseDouble(stockPrices2.get(13));;
	  double price15B = Double.parseDouble(stockPrices2.get(14));;
	  
	  
	  double prices[] = {price1,price2,price3,price4,price5,price6,price7,price8,price9,price10,price11,price12,price13,price14,price15};
	  double pricesB[] = {price1B,price2B,price3B,price4B,price5B,price6B,price7B,price8B,price9B,price10B,price11B,price12B,price13B,price14B,price15B};
      double sumPrices = 0;
      double sumPricesB = 0;
	  for(int i = 0; i < 14; i++){
    	  sumPrices+= prices[i];
    	  sumPricesB+= pricesB[i];
      }
	  double mean = sumPrices/15;
	  double meanB = sumPricesB/15;
	  double squareSum = 0;
	  double squareSumB = 0;
	  double stdev = -1;
	  double stdevB = -1;
	  for(int i = 0; i < 14; i++){
		  squareSum += Math.pow(prices[i]-mean, 2);
		  squareSumB += Math.pow(pricesB[i]-mean, 2);
      }
	  stdev = Math.sqrt(squareSum) / 14;
	  stdevB = Math.sqrt(squareSumB) / 14;
	  
	  priceWinner = StockAnalysis.higherPrice(company, company2,curPrice, curPrice2);

	  returnWinner = StockAnalysis.higherReturn(company, company2, eps, eps2, peRatio, peRatio2, div, div2, roa, roa2);
	  
	  riskWinner = StockAnalysis.lowerRisk(company, company2, marketCap, marketCap2, beta, beta2, stdev, stdevB);
	  //compare market share
	  marketDominant = StockAnalysis.higherPrice(company, company2, marketCap, marketCap2);
		 
	  
	  Stock stk = new Stock(stockID, company, industry, description, url, curPrice, openPrice, closePrice, highPrice, lowPrice, marketCap, eps, beta, div, peRatio, roa, week52H, week52L, 
			                stockID2, company2, industry2, description2, url2, curPrice2, openPrice2, closePrice2, highPrice2, lowPrice2, marketCap2, eps2, beta2, div2, peRatio2, roa2, week52H2, week52L2, 
			                priceWinner, returnWinner, riskWinner, marketDominant,
			                price1, price2, price3, price4, price5, price6, price7, price8, price9, price10,
		                    price11, price12, price13, price14, price15, 
		                    price1B, price2B, price3B, price4B, price5B, price6B, price7B, price8B, price9B, price10B,
		                    price11B, price12B, price13B, price14B, price15B);
      String json = new Gson().toJson(stk);
	  return json;

  }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Connection con;
		try {
			con = DbAccessImpl.connect();
			if (request.getParameter("stockSearch") != null){
				String input = request.getParameter("stockSearch");
				String json;
			    json = searchOneStock(con, input);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
			}
			else if (request.getParameter("compareStock1") != null){
				String input = request.getParameter("compareStock1");
				String[] inputs = input.split(",");
				String input1 = inputs[0];
				String input2 = inputs[1];
				System.out.println(input);
				System.out.println(input1);
				System.out.println(input2);
				String json;
			    json = compareTwoStock(con, input1, input2);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	
	}
        


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
