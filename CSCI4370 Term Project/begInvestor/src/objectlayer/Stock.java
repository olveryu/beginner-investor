package objectlayer;

public class Stock {
  String stockID = "";
  String company = "";
  String industry = "";
  String description = "";
  String url = "";
  double curPrice = -1;
  double openPrice = -1;
  double closePrice = -1;
  double highPrice = -1;
  double lowPrice = -1;
  double marketCap= -1;
  double eps = -1;
  double beta = -1;
  double div = -1;
  double peRatio = -1;
  double roa = -1;
  double week52H = -1;
  double week52L = -1;
  
  String stockID2 = "";
  String company2 = "";
  String industry2 = "";
  String description2 = "";
  String url2 = "";
  double curPrice2 = -1;
  double openPrice2 = -1;
  double closePrice2 = -1;
  double highPrice2 = -1;
  double lowPrice2 = -1;
  double marketCap2= -1;
  double eps2 = -1;
  double beta2 = -1;
  double div2 = -1;
  double peRatio2 = -1;
  double roa2 = -1;
  double week52H2 = -1;
  double week52L2 = -1;
  String priceWinner = "";
  String returnWinner = "";
  String riskWinner = "";
  String marketDominant = "";
  
  double price1 = -1;
  double price2 = -1;
  double price3 = -1;
  double price4 = -1;
  double price5 = -1;
  double price6 = -1;
  double price7 = -1;
  double price8 = -1;
  double price9 = -1;
  double price10 = -1;
  double price11 = -1;
  double price12 = -1;
  double price13 = -1;
  double price14 = -1;
  double price15 = -1;

  
  double price1B = -1;
  double price2B = -1;
  double price3B = -1;
  double price4B = -1;
  double price5B = -1;
  double price6B = -1;
  double price7B = -1;
  double price8B = -1;
  double price9B = -1;
  double price10B = -1;
  double price11B = -1;
  double price12B = -1;
  double price13B = -1;
  double price14B = -1;
  double price15B = -1;

  
  public Stock(String stockID, String company, String industry, String description, String url, double curPrice, double openPrice, double closePrice, double highPrice, double lowPrice, double marketCap, double eps, double beta, double div, double peRatio, double roa, double week52H, double week52L,
		       double price1, double price2, double price3, double price4, double price5, double price6, double price7, double price8, double price9, double price10,
		       double price11, double price12, double price13, double price14, double price15){
	  this.stockID = stockID;
	  this.company = company;
	  this.industry = industry;
	  this.description = description;
	  this.url = url;
	  this.curPrice =curPrice ;
	  this.openPrice = openPrice ;
	  this.closePrice = closePrice;
	  this.highPrice = highPrice;
	  this.lowPrice = lowPrice;
	  this.marketCap = marketCap;
	  this.eps = eps;
	  this.beta = beta;
	  this.div = div;
	  this.peRatio = peRatio;
	  this.roa = roa;
	  this.week52H = week52H;
	  this.week52L = week52L;
	  
	  this.price1 = price1;
	  this.price2 = price2;
	  this.price3 = price3;
	  this.price4 = price4;
	  this.price5 = price5;
	  this.price6 = price6;
	  this.price7 = price7;
	  this.price8 = price8;
	  this.price9 = price9;
	  this.price10 = price10;
	  this.price11 = price11;
	  this.price12 = price12;
	  this.price13 = price13;
	  this.price14 = price14;
	  this.price15 = price15;


	}
  public Stock(String stockID, String company, String industry, String description, String url, double curPrice, double openPrice, double closePrice, double highPrice, double lowPrice, double marketCap, double eps, double beta, double div, double peRatio, double roa, double week52H, double week52L,
		       String stockID2, String company2, String industry2, String description2, String url2, double curPrice2, double openPrice2, double closePrice2, double highPrice2, double lowPrice2, double marketCap2, double eps2, double beta2, double div2, double peRatio2, double roa2, double week52H2, double week52L2, 
		       String priceWinner, String returnWinner, String riskWinner, String marketDominant,
		       double price1, double price2, double price3, double price4, double price5, double price6, double price7, double price8, double price9, double price10,
		       double price11, double price12, double price13, double price14, double price15, 
		       double price1B, double price2B, double price3B, double price4B, double price5B, double price6B, double price7B, double price8B, double price9B, double price10B,
		       double price11B, double price12B, double price13B, double price14B, double price15B){
	  this.stockID = stockID;
	  this.company = company;
	  this.industry = industry;
	  this.description = description;
	  this.url = url;
	  this.curPrice =curPrice ;
	  this.openPrice = openPrice ;
	  this.closePrice = closePrice;
	  this.highPrice = highPrice;
	  this.lowPrice = lowPrice;
	  this.marketCap = marketCap;
	  this.eps = eps;
	  this.beta = beta;
	  this.div = div;
	  this.peRatio = peRatio;
	  this.roa = roa;
	  this.week52H = week52H;
	  this.week52L = week52L;
	  
	  this.stockID2 = stockID2;
	  this.company2 = company2;
	  this.industry2 = industry2;
	  this.description2 = description2;
	  this.url2 = url2;
	  this.curPrice2 =curPrice2;
	  this.openPrice2 = openPrice2;
	  this.closePrice2 = closePrice2;
	  this.highPrice2 = highPrice2;
	  this.lowPrice2 = lowPrice2;
	  this.marketCap2 = marketCap2;
	  this.eps2 = eps2;
	  this.beta2 = beta2;
	  this.div2 = div2;
	  this.peRatio2 = peRatio2;
	  this.roa2 = roa2;
	  this.week52H2 = week52H2;
	  this.week52L2 = week52L2;
	  
	  this.priceWinner = priceWinner;
	  this.returnWinner = returnWinner;
	  this.riskWinner = riskWinner;
	  this.marketDominant = marketDominant;
	  
	  
	  this.price1 = price1;
	  this.price2 = price2;
	  this.price3 = price3;
	  this.price4 = price4;
	  this.price5 = price5;
	  this.price6 = price6;
	  this.price7 = price7;
	  this.price8 = price8;
	  this.price9 = price9;
	  this.price10 = price10;
	  this.price11 = price11;
	  this.price12 = price12;
	  this.price13 = price13;
	  this.price14 = price14;
	  this.price15 = price15;

	  
	  
	  this.price1B = price1B;
	  this.price2B = price2B;
	  this.price3B = price3B;
	  this.price4B = price4B;
	  this.price5B = price5B;
	  this.price6B = price6B;
	  this.price7B = price7B;
	  this.price8B = price8B;
	  this.price9B = price9B;
	  this.price10B = price10B;
	  this.price11B = price11B;
	  this.price12B = price12B;
	  this.price13B = price13B;
	  this.price14B = price14B;
	  this.price15B = price15B;

	  
	  

	}
}
