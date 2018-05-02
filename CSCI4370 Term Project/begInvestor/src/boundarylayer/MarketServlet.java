package boundarylayer;

import objectlayer.RankResults;
import objectlayer.Rank;
import objectlayer.stk;
import persistlayer.DbAccessImpl;
import persistlayer.DbStockQueries;
import persistlayer.DatabaseAccess;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
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

import org.json.*;


/**
 * Servlet implementation class LoadPage
 */
@WebServlet("/MarketServlet")
public class MarketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // variables store the user input
	boolean ifSearchStock = false;

  
	Configuration cfg = null;

	private String templateDir = "/WEB-INF/templates";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketServlet() {
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

    public String getSelected(Connection con, String jsonstr) throws JSONException, SQLException{
    	
    	JSONObject obj = new JSONObject(jsonstr);
		String industry = obj.getString("industry");
		boolean pricechange = obj.getBoolean("pricechange");
		boolean beta = obj.getBoolean("beta");
		boolean marketcap = obj.getBoolean("marketcap");
		boolean eps = obj.getBoolean("eps");
		boolean peratio = obj.getBoolean("peratio");
		boolean dividend = obj.getBoolean("div");
		
		RankResults rs = new RankResults();
		
		ArrayList<ArrayList<ArrayList<String>>> rank = DbStockQueries.getRankResults(industry, pricechange, beta, marketcap, eps, peratio, dividend, con);

		if(pricechange) {
			stk stk1 = new stk(rank.get(0).get(0).get(0), rank.get(0).get(0).get(1), Double.parseDouble(rank.get(0).get(0).get(2)));
			stk stk2 = new stk(rank.get(0).get(1).get(0), rank.get(0).get(1).get(1), Double.parseDouble(rank.get(0).get(1).get(2)));
			stk stk3 = new stk(rank.get(0).get(2).get(0), rank.get(0).get(2).get(1), Double.parseDouble(rank.get(0).get(2).get(2)));
			stk stk4 = new stk(rank.get(0).get(3).get(0), rank.get(0).get(3).get(1), Double.parseDouble(rank.get(0).get(3).get(2)));
			stk stk5 = new stk(rank.get(0).get(4).get(0), rank.get(0).get(4).get(1), Double.parseDouble(rank.get(0).get(4).get(2)));
			stk stk6 = new stk(rank.get(0).get(5).get(0), rank.get(0).get(5).get(1), Double.parseDouble(rank.get(0).get(5).get(2)));
			stk stk7 = new stk(rank.get(0).get(6).get(0), rank.get(0).get(6).get(1), Double.parseDouble(rank.get(0).get(6).get(2)));
			stk stk8 = new stk(rank.get(0).get(7).get(0), rank.get(0).get(7).get(1), Double.parseDouble(rank.get(0).get(7).get(2)));
			stk stk9 = new stk(rank.get(0).get(8).get(0), rank.get(0).get(8).get(1), Double.parseDouble(rank.get(0).get(8).get(2)));
			stk stk10 = new stk(rank.get(0).get(9).get(0), rank.get(0).get(9).get(1), Double.parseDouble(rank.get(0).get(9).get(2)));
			
			Rank pcjson = new Rank(stk1.toString(), stk2.toString(), stk3.toString(), stk4.toString(), 
					stk5.toString(), stk6.toString(), stk7.toString(), stk8.toString(), stk9.toString(), stk10.toString());

			rs.setPCTb(pcjson.toString());
		}
		
		if(beta) {
			stk stk1 = new stk(rank.get(1).get(0).get(0), rank.get(1).get(0).get(1), Double.parseDouble(rank.get(1).get(0).get(2)));
			stk stk2 = new stk(rank.get(1).get(1).get(0), rank.get(1).get(1).get(1), Double.parseDouble(rank.get(1).get(1).get(2)));
			stk stk3 = new stk(rank.get(1).get(2).get(0), rank.get(1).get(2).get(1), Double.parseDouble(rank.get(1).get(2).get(2)));
			stk stk4 = new stk(rank.get(1).get(3).get(0), rank.get(1).get(3).get(1), Double.parseDouble(rank.get(1).get(3).get(2)));
			stk stk5 = new stk(rank.get(1).get(4).get(0), rank.get(1).get(4).get(1), Double.parseDouble(rank.get(1).get(4).get(2)));
			stk stk6 = new stk(rank.get(1).get(5).get(0), rank.get(1).get(5).get(1), Double.parseDouble(rank.get(1).get(5).get(2)));
			stk stk7 = new stk(rank.get(1).get(6).get(0), rank.get(1).get(6).get(1), Double.parseDouble(rank.get(1).get(6).get(2)));
			stk stk8 = new stk(rank.get(1).get(7).get(0), rank.get(1).get(7).get(1), Double.parseDouble(rank.get(1).get(7).get(2)));
			stk stk9 = new stk(rank.get(1).get(8).get(0), rank.get(1).get(8).get(1), Double.parseDouble(rank.get(1).get(8).get(2)));
			stk stk10 = new stk(rank.get(1).get(9).get(0), rank.get(1).get(9).get(1), Double.parseDouble(rank.get(1).get(9).get(2)));
			Rank betajson = new Rank(stk1.toString(), stk2.toString(), stk3.toString(), stk4.toString(), 
					stk5.toString(), stk6.toString(), stk7.toString(), stk8.toString(), stk9.toString(), stk10.toString());

			rs.setBetaTb(betajson.toString());
		}
		
		if(marketcap) {
			stk stk1 = new stk(rank.get(2).get(0).get(0), rank.get(2).get(0).get(1), Double.parseDouble(rank.get(2).get(0).get(2)));
			stk stk2 = new stk(rank.get(2).get(1).get(0), rank.get(2).get(1).get(1), Double.parseDouble(rank.get(2).get(1).get(2)));
			stk stk3 = new stk(rank.get(2).get(2).get(0), rank.get(2).get(2).get(1), Double.parseDouble(rank.get(2).get(2).get(2)));
			stk stk4 = new stk(rank.get(2).get(3).get(0), rank.get(2).get(3).get(1), Double.parseDouble(rank.get(2).get(3).get(2)));
			stk stk5 = new stk(rank.get(2).get(4).get(0), rank.get(2).get(4).get(1), Double.parseDouble(rank.get(2).get(4).get(2)));
			stk stk6 = new stk(rank.get(2).get(5).get(0), rank.get(2).get(5).get(1), Double.parseDouble(rank.get(2).get(5).get(2)));
			stk stk7 = new stk(rank.get(2).get(6).get(0), rank.get(2).get(6).get(1), Double.parseDouble(rank.get(2).get(6).get(2)));
			stk stk8 = new stk(rank.get(2).get(7).get(0), rank.get(2).get(7).get(1), Double.parseDouble(rank.get(2).get(7).get(2)));
			stk stk9 = new stk(rank.get(2).get(8).get(0), rank.get(2).get(8).get(1), Double.parseDouble(rank.get(2).get(8).get(2)));
			stk stk10 = new stk(rank.get(2).get(9).get(0), rank.get(2).get(9).get(1), Double.parseDouble(rank.get(2).get(9).get(2)));
			
			Rank mcjson = new Rank(stk1.toString(), stk2.toString(), stk3.toString(), stk4.toString(), 
					stk5.toString(), stk6.toString(), stk7.toString(), stk8.toString(), stk9.toString(), stk10.toString());

			rs.setMCTb(mcjson.toString());
		}
		
		if(eps) {
			stk stk1 = new stk(rank.get(3).get(0).get(0), rank.get(3).get(0).get(1), Double.parseDouble(rank.get(3).get(0).get(2)));
			stk stk2 = new stk(rank.get(3).get(1).get(0), rank.get(3).get(1).get(1), Double.parseDouble(rank.get(3).get(1).get(2)));
			stk stk3 = new stk(rank.get(3).get(2).get(0), rank.get(3).get(2).get(1), Double.parseDouble(rank.get(3).get(2).get(2)));
			stk stk4 = new stk(rank.get(3).get(3).get(0), rank.get(3).get(3).get(1), Double.parseDouble(rank.get(3).get(3).get(2)));
			stk stk5 = new stk(rank.get(3).get(4).get(0), rank.get(3).get(4).get(1), Double.parseDouble(rank.get(3).get(4).get(2)));
			stk stk6 = new stk(rank.get(3).get(5).get(0), rank.get(3).get(5).get(1), Double.parseDouble(rank.get(3).get(5).get(2)));
			stk stk7 = new stk(rank.get(3).get(6).get(0), rank.get(3).get(6).get(1), Double.parseDouble(rank.get(3).get(6).get(2)));
			stk stk8 = new stk(rank.get(3).get(7).get(0), rank.get(3).get(7).get(1), Double.parseDouble(rank.get(3).get(7).get(2)));
			stk stk9 = new stk(rank.get(3).get(8).get(0), rank.get(3).get(8).get(1), Double.parseDouble(rank.get(3).get(8).get(2)));
			stk stk10 = new stk(rank.get(3).get(9).get(0), rank.get(3).get(9).get(1), Double.parseDouble(rank.get(3).get(9).get(2)));
			
			Rank epsjson = new Rank(stk1.toString(), stk2.toString(), stk3.toString(), stk4.toString(), 
					stk5.toString(), stk6.toString(), stk7.toString(), stk8.toString(), stk9.toString(), stk10.toString());

			rs.setEPSTb(epsjson.toString());
		}
		
		if(peratio) {
			stk stk1 = new stk(rank.get(4).get(0).get(0), rank.get(4).get(0).get(1), Double.parseDouble(rank.get(4).get(0).get(2)));
			stk stk2 = new stk(rank.get(4).get(1).get(0), rank.get(4).get(1).get(1), Double.parseDouble(rank.get(4).get(1).get(2)));
			stk stk3 = new stk(rank.get(4).get(2).get(0), rank.get(4).get(2).get(1), Double.parseDouble(rank.get(4).get(2).get(2)));
			stk stk4 = new stk(rank.get(4).get(3).get(0), rank.get(4).get(3).get(1), Double.parseDouble(rank.get(4).get(3).get(2)));
			stk stk5 = new stk(rank.get(4).get(4).get(0), rank.get(4).get(4).get(1), Double.parseDouble(rank.get(4).get(4).get(2)));
			stk stk6 = new stk(rank.get(4).get(5).get(0), rank.get(4).get(5).get(1), Double.parseDouble(rank.get(4).get(5).get(2)));
			stk stk7 = new stk(rank.get(4).get(6).get(0), rank.get(4).get(6).get(1), Double.parseDouble(rank.get(4).get(6).get(2)));
			stk stk8 = new stk(rank.get(4).get(7).get(0), rank.get(4).get(7).get(1), Double.parseDouble(rank.get(4).get(7).get(2)));
			stk stk9 = new stk(rank.get(4).get(8).get(0), rank.get(4).get(8).get(1), Double.parseDouble(rank.get(4).get(8).get(2)));
			stk stk10 = new stk(rank.get(4).get(9).get(0), rank.get(4).get(9).get(1), Double.parseDouble(rank.get(4).get(9).get(2)));
			
			Rank perjson = new Rank(stk1.toString(), stk2.toString(), stk3.toString(), stk4.toString(), 
					stk5.toString(), stk6.toString(), stk7.toString(), stk8.toString(), stk9.toString(), stk10.toString());

			rs.setPERTb(perjson.toString());
		}
		if(dividend) {
			/*int size = rank.get(5).size();
			HashMap<Integer, JSONObject> n = new HashMap<Integer, JSONObject>();
			for(int a = 0; a < size; a++) {
				stk stk = new stk(rank.get(5).get(a).get(0), rank.get(5).get(a).get(1),Double.parseDouble(rank.get(5).get(a).get(2)));
				n.put(a, stk);
			}
			n.get(0).toString(), n.get(1).toString(), n.get(2).toString(), n.get(3).toString(), 
					n.get(4).toString(), n.get(5).toString(), n.get(6).toString(), n.get(7).toString(), n.get(8).toString(), n.get(9).toString()
			if(size < 10) {
				for(int k = size; k < 10-size; k++) {
					stk stk = new stk("N/A", "N/A", Double.NaN);
					n.put(k, stk);
				}
			}*/
			
			
			stk stk1 = new stk(rank.get(5).get(0).get(0), rank.get(5).get(0).get(1), Double.parseDouble(rank.get(5).get(0).get(2)));
			stk stk2 = new stk(rank.get(5).get(1).get(0), rank.get(5).get(1).get(1), Double.parseDouble(rank.get(5).get(1).get(2)));
			stk stk3 = new stk(rank.get(5).get(2).get(0), rank.get(5).get(2).get(1), Double.parseDouble(rank.get(5).get(2).get(2)));
			stk stk4 = new stk(rank.get(5).get(3).get(0), rank.get(5).get(3).get(1), Double.parseDouble(rank.get(5).get(3).get(2)));
			stk stk5 = new stk(rank.get(5).get(4).get(0), rank.get(5).get(4).get(1), Double.parseDouble(rank.get(5).get(4).get(2)));
			stk stk6 = new stk(rank.get(5).get(5).get(0), rank.get(5).get(5).get(1), Double.parseDouble(rank.get(5).get(5).get(2)));
			stk stk7 = new stk(rank.get(5).get(6).get(0), rank.get(5).get(6).get(1), Double.parseDouble(rank.get(5).get(6).get(2)));
			stk stk8 = new stk(rank.get(5).get(7).get(0), rank.get(5).get(7).get(1), Double.parseDouble(rank.get(5).get(7).get(2)));
			stk stk9 = new stk(rank.get(5).get(8).get(0), rank.get(5).get(8).get(1), Double.parseDouble(rank.get(5).get(8).get(2)));
			stk stk10 = new stk(rank.get(5).get(9).get(0), rank.get(5).get(9).get(1), Double.parseDouble(rank.get(5).get(9).get(2)));
			
			Rank divjson = new Rank(stk1.toString(), stk2.toString(), stk3.toString(), stk4.toString(), 
					stk5.toString(), stk6.toString(), stk7.toString(), stk8.toString(), stk9.toString(), stk10.toString());

			rs.setDiviTb(divjson.toString());
		}
		
		String json = new Gson().toJson(rs);
		return json;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con;
		try {
			con = DbAccessImpl.connect();
			if(request.getParameter("jsonstr") != null) {
				String jsonstr = request.getQueryString();
				jsonstr = jsonstr.substring(8, jsonstr.length());
				jsonstr = jsonstr.replaceAll("=", "\":\"");
			    jsonstr = jsonstr.replaceAll("&", "\",\"");
			    jsonstr = jsonstr.replaceAll("%20", " ");
			    jsonstr = jsonstr.replaceAll("%26", "&");
			    jsonstr = "{\"" + jsonstr + "\"}";
				String result = "";
				
			    try {
					result = this.getSelected(con, jsonstr);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//catch
				response.setContentType("text/plain");
		        response.setCharacterEncoding("UTF-8");
		        response.getWriter().write(result);
			}//if	
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//catch
	}//doGet
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

