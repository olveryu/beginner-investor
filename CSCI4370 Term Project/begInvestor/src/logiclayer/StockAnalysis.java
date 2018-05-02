package logiclayer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.math.*;

public class StockAnalysis {
	public static String higherPrice(String stk1, String stk2, double price1, double price2){
		String priceWinner = "";
		  if(price1 == 0 ||price2 == 0 ){
			  priceWinner = "Important information is not available";
		  }
		  else{
			  if(price1 > price2){
				  priceWinner = stk1;
			  }
			  else if(price1 < price2){
				  priceWinner = stk2;
			  }
			  else{
				  priceWinner = "Prices are the same";
			  }
		  }
		return priceWinner;
	}
	
	public static String higherReturn(String stk1, String stk2, double eps1, double eps2, double peR1, double peR2, double div1, double div2, double roa1, double roa2){
		String returnWinner = "";
		if(peR1 == 0 || peR2  == 0 ){
			if(eps1 == 0 || eps2 == 0){
				if(roa1 == 0 || roa2 == 0){
					if(div1 == 0 || div2 == 0){
						returnWinner = "Metrics are not available";
					}
					else if (div1 > div2){
						returnWinner = stk1;
					}
					else if (div1 < div2){
						returnWinner = stk2;
					}
					else{
						returnWinner = "Approximately Equal Return ";
					}
				}
				else if (roa1 > roa2){
					returnWinner = stk1;
				}
				else if (roa1 < roa2){
					returnWinner = stk2;
				}
				else{
					returnWinner = "Approximately Equal Return ";
				}
			}
			else if (eps1 > eps2){
				returnWinner = stk1;
			}
			else if (eps1 < eps2){
				returnWinner = stk2;
			}
			else{
				returnWinner = "Approximately Equal Return ";
			}  
		}
		else if (peR1 > peR2){
			returnWinner = stk1;
		}
		else if (peR1 < peR2){
			returnWinner = stk2;
		}
		else{
			returnWinner = "Approximately Equal Return ";
		}
		return returnWinner;
	}
	
	public static String lowerRisk(String stk1, String stk2, double mCap1, double mCap2, double beta1, double beta2, double pc1, double pc2){
		String riskWinner = "";
		if(pc1 == 0 || pc2 == 0){
			if(beta1 == 0 || beta2 == 0){
				if(mCap1 == 0 || mCap2 == 0){
					riskWinner = "Metrics are not available";
				}
				else if (mCap1 > mCap2){
					riskWinner = stk1;
				}
				else if (mCap1 < mCap2){
					riskWinner = stk2;
				}
				else{
					riskWinner = "Approximately Equal Risk ";
				}
			}
			else if (beta1 < beta2){
				riskWinner = stk1;
			}
			else if (beta1 > beta2){
				riskWinner = stk2;
			}
			else{
				riskWinner = "Approximately Equal Risk ";
			}
		}
		else if (pc1 < pc2){
			riskWinner = stk1;
		}
		else if (pc1 > pc2){
			riskWinner = stk2;
		}
		else{
			riskWinner = "Approximately Equal Return ";
		}  
		return riskWinner;
		
	}
	public static String higherMarketCap(String stk1, String stk2, double marketCap1, double marketCap2){
		String marketDominant = "";
		  if(marketCap1 == 0 || marketCap2 == 0){
			  marketDominant = "Important information is not available";
		  }
		  else{
		  
			  if(marketCap1 > marketCap2){
				  marketDominant = stk1;
			  }
			  else if(marketCap1 < marketCap2){
				  marketDominant = stk2;
			  }
			  else{
				  marketDominant = "Companies have the same market share";
			  } 
		  }
		return marketDominant;
	}
	
}

