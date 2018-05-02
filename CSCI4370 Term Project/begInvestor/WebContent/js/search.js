$(document).ready(function(){
	$("#main-area").empty();
	$("#main-area").append('<div id= "searchArea"></div>');
	$("#searchArea").append('<input id = "searchField" class = "inputField" type = "text" name = "stockSearch" placeholder = "enter a company name to search"></input>');
	$("#searchArea").append('<button id= "searchbtn" class ="searchButton" name ="search">Search</button>');
	autocomplete(document.getElementById("searchField"), companyName);
});

$(document).on("click", "#compareTwoStocks", function() {
	$("#main-area").empty();
	$("#main-area").append('<div id= "searchArea"></div>');
	$("#searchArea").empty();
	$("#searchArea").append('<input id = "compareStock1" class = "inputField" type = "text" name = "stockCompare1" placeholder = "enter a company name to compare"></input>');
	$("#searchArea").append('<input id = "compareStock2" class = "inputField" type = "text" name = "stockCompare2" placeholder = "enter a company name to compare"></input>');
	$("#searchArea").append('<button id= "comparebtn" class ="compareButton" name ="compare">Compare</button>');
	autocomplete(document.getElementById("compareStock1"), companyName);
	autocomplete(document.getElementById("compareStock2"), companyName);
});

$(document).on("click", "#searchAStock", function() {
	$("#main-area").empty();
	$("#main-area").append('<div id= "searchArea"></div>');
	$("#searchArea").empty();
	$("#searchArea").append('<input id = "searchField" class = "inputField" type = "text" name = "stockSearch" placeholder = "enter a stock id to search"></input>');
	$("#searchArea").append('<button id= "searchbtn" class ="searchButton" name ="search">Search</button>');
	autocomplete(document.getElementById("searchField"), companyName);
});


//print result table and get data returned from database for search 
$(document).on("click", "#searchbtn", function() { 
	if(!$("#searchField").val()){
		alert("Please enter valid stock IDs for search!");
	}
	else{
		var input = $("#searchField").val(); 
	    $.get("StockServlet?stockSearch="+input, function(responseJson) {     	
			var bTable = "<table id = \"bTable\">" 
						  + "<tr> <td class = \"cellWithCommentS\" id = \"sidS\"> Stock ID <span class = \"comment\"> The unique identifier of a stock in market trade </span> </td> <td id = \"std\"></td> </tr> "
				          + "<tr> <td class = \"cellWithCommentS\"> Company <span class = \"comment\"> Company's Name </span> </td> <td id = \"company\"></td> </tr> "
				          + "<tr> <td class = \"cellWithCommentS\"> Industry <span class = \"comment\"> Classification of the company </span> </td> <td id = \"industry\"></td> </tr>"
				          + "<tr> <td class = \"cellWithCommentS\"> Description <span class = \"comment\"> What does the company do </span> </td> <td id = \"discrip\"></td> </tr>"
				          + "<tr> <td class = \"cellWithCommentS\" id = \"urlS\"> URL <span class = \"comment\"> Company's website </span> </td> <td id = \"url\"></td> </tr>"
				          +"</table>";
			var pTable = "<table id = \"pTable\">" 
				          + "<tr> <td class = \"cellWithCommentS\" id = \"priceS\"> Price <span class = \"comment\"> The latest price - 15 mins delayed </span> </td> <td id = \"curPrice\"></td> </tr> "
				          + "<tr> <td class = \"cellWithCommentS\"> Open <span class = \"comment\"> Price at which a security first trades upon the opening of an exchange on a given trading day </span> </td> <td id = \"opPrice\"></td> <td class = \"cellWithCommentS\" id = \"hP\"> High <span class = \"comment\"> The highest price attained during the trading session </span> </td> <td id = \"hPrice\"></td></tr>"
				          + "<tr> <td class = \"cellWithCommentS\" id = \"closeS\"> Close <span class = \"comment\"> Last price at which a stock trades during a regular trading session </span>  </td> <td id = \"clPrice\"></td> <td class = \"cellWithCommentS\"> Low <span class = \"comment\"> The lowest price during the trading session </span> </td> <td id = \"lPrice\"></td></tr>"
				          +"</table>";
			var mTable = "<table id = \"mTable\">" 
						  + "<tr> <td class = \"cellWithCommentS\" id = \"mCapS\"> Market Cap <span class = \"comment\">  The market value of the company's outstanding shares:</br> [Large - slower growth with lower risk] </br> [Small - higher growth potential with higher risk]  </span> </td> <td id = \"mrkCap\"></td> <td class = \"cellWithCommentS\"> EPS <span class = \"comment\"> Earnings per share, how much money the company is making in profits per share:</br> [Higher - more profitable] </span></td> <td id = \"eps\"></td></tr>"
						  + "<tr> <td class = \"cellWithCommentS\"> Beta <span class = \"comment\"> Violatility of the stock in comparision to market overall risk:</br> [Higher than 1 - more volatile than overall market] </br> [Less than 1 - less volatile than overall market] </span> </td> <td id = \"beta\"></td> <td class = \"cellWithCommentS\"> Dividend <span class = \"comment\"> Rate of return in the form of cash dividends to shareholders:</br> [Higher - better for current stage]</span> </td> <td id = \"div\"></td></tr>"
						  + "<tr> <td class = \"cellWithCommentS\"> PE Ratio <span class = \"comment\"> The price an investor paid for $1 of the company's earnings:</br> [Higher - more profitable but may be overpriced] </br> [Lower - less proftitable but maybe underpriced] </br> [Equal to 1 - Price matches the company's profitablity] </span>  </td> <td id = \"pe\"></td> <td class = \"cellWithCommentS\"> ROA <span class = \"comment\"> Return on asset: [Higher - better] </span> </td> <td id = \"roa\"></td></tr>"
						  + "<tr> <td class = \"cellWithCommentS\" id = \"w52S\"> Week52High <span class = \"comment\"> The highest price at which the stock was trade during the previous 52 weeks </span> </td> <td id = \"wh\"></td> <td class = \"cellWithCommentS\"> Week52Low <span class = \"comment\"> The lowest price at which the stock was trade during the previous 52 weeks </span></td> <td id = \"wl\"></td></tr>"
						  +"</table>";
	    	var stockID = responseJson.stockID;
	    	var data1 = responseJson.price1;
	    	var data2 = responseJson.price2;
	    	var data3 = responseJson.price3;
	    	var data4 = responseJson.price4;
	    	var data5 = responseJson.price5;
	    	var data6 = responseJson.price6;
	    	var data7 = responseJson.price7;
	    	var data8 = responseJson.price8;
	    	var data9 = responseJson.price9;
	    	var data10 = responseJson.price10;
	    	var data11 = responseJson.price11;
	    	var data12 = responseJson.price12;
	    	var data13 = responseJson.price13;
	    	var data14 = responseJson.price14;
	    	var data15 = responseJson.price15;

	    	//var test = 3000000000;
			var graphDiv = "<div class= \"tableArea\" id = \"graph\"> <canvas id=\"myChart\"> </canvas>" 
						 + "<script> new Chart(document.getElementById(\"myChart\"), {" 
						 + "type: 'line',data: {" 
						 + "labels: ['Day1','Day2','Day3','Day4','Day5','Day6','Day7','Day8','Day9','Day10','Day11','Day12','Day13','Day14','Day15']," 
						 + "datasets: [{ data: [" 
						 + data1 + "," + data2 + "," + data3 + "," + data4 + "," + data5 + "," + data6 + "," + data7 + "," + data8 + "," + data9 + "," + data10 + ","
						 + data11 + "," + data12 + "," + data13 + "," + data14 + "," + data15
			             +"]," 
			             + "label: \"" 
			             + stockID 
			             + "\",borderColor: \"#3e95cd\",fill: false}]}," 
						 + "options: {title: {display: true,text: 'Stock price change'}}});" 
						 + "</script></div>'";
			//var testDiv = "<div class= \"tableArea\" id = \"graph\"> <p>" + test +"</p></div>";
			$("#inputForm").empty();
		    $("#searchArea").empty();
		    $("#main-area").empty();
		    $("#main-area").css('overflow','scroll');
		    $("#main-area").append('<div class= "tableArea" id = "btb"></div>');
		    $("#btb").append(bTable);
		    $("#main-area").append('<div class= "tableArea" id = "ptb"></div>');
		    $("#ptb").append(pTable);
		    $("#main-area").append('<div class= "tableArea" id = "mtb"></div>');
		    $("#mtb").append(mTable);
		    $('#main-area').append(graphDiv);
			 var company = responseJson.company;
			 var industry = responseJson.industry;
			 var description = responseJson.description;
			 var url = responseJson.url;
			 var curPrice = responseJson.curPrice;
			 var openPrice = responseJson.openPrice;
			 var closePrice = responseJson.closePrice;
			 var highPrice = responseJson.highPrice;
			 var lowPrice = responseJson.lowPrice;
			 var marketCap = responseJson.marketCap;
			 var eps = responseJson.eps;
			 var beta = responseJson.beta;
			 var div= responseJson.div;
			 var peRatio = responseJson.peRatio;
			 var roa = responseJson.roa;
			 var week52H = responseJson.week52H;
			 var week52L = responseJson.week52L;
			 $("#std").html(stockID);
			 $("#company").html(company);
			 $("#industry").html(industry);
			 $("#discrip").html(description);
			 $("#url").html(url);
			 if (curPrice > 0){
				 $("#curPrice").html("$ " + curPrice);
			 }
			 else{
				 $("#curPrice").html("Not Available");
				 
			 }
			 if (openPrice > 0){
				 $("#opPrice").html("$ " + openPrice);
			 }
			 else{
				 $("#opPrice").html("Not Available");
				 
			 }
			 if (closePrice > 0){
				 $("#clPrice").html("$ " + closePrice);
			 }
			 else{
				 $("#clPrice").html("Not Available");
				 
			 }
			 if (highPrice > 0){
				 $("#hPrice").html("$ " + highPrice);
			 }
			 else{
				 $("#hPrice").html("Not Available");
				 
			 }
			 if (lowPrice > 0){
				 $("#lPrice").html("$ " + lowPrice);
			 }
			 else{
				 $("#lPrice").html("Not Available");
				 
			 }
			 if (marketCap> 0){
				 $("#mrkCap").html(marketCap);
			 }
			 else{
				 $("#mrkCap").html("Not Available");
				 
			 }
			 if (eps != 0){
				 $("#eps").html(eps);
			 }
			 else{
				 $("#eps").html("Not Available");
				 
			 }
			 if (beta > 0){
				 $("#beta").html(beta);
			 }
			 else{
				 $("#beta").html("Not Available");
				 
			 }
			 if (div > 0){
				 $("#div").html(div);
			 }
			 else{
				 $("#div").html("Not Available");
				 
			 }
			 if (peRatio != 0){
				 $("#pe").html(peRatio);
			 }
			 else{
				 $("#pe").html("Not Available");
				 
			 }
			 if (roa != 0){
				 $("#roa").html(roa);
			 }
			 else{
				 $("#roa").html("Not Available");
				 
			 }
			 if (week52H > 0){
				 $("#wh").html(week52H);
			 }
			 else{
				 $("#wh").html("Not Available");
				 
			 }
			 if (week52L> 0){
				 $("#wl").html(week52L);
			 }
			 else{
				 $("#wl").html("Not Available");
				 
			 }		
		 }).fail(function (){
		        alert("Invalid symbol. Company does not have stock in this database!");
		 });
	}
});

//print result table and get data returned from database for compare
$(document).on("click", "#comparebtn", function() {
	var input = $("#compareStock1").val() +","+ $("#compareStock2").val();
	if(!$("#compareStock1").val() ||!$("#compareStock2").val()){
		alert("Please enter valid stock IDs for comparison!");
	}
	else{	 
		  $.get("StockServlet?compareStock1="+input, function(responseJson) { 
			  var stockID= responseJson.stockID;
			  var stockID2= responseJson.stockID2;
			  var data1 = responseJson.price1;
			  var data2 = responseJson.price2;
			  var data3 = responseJson.price3;
			  var data4 = responseJson.price4;
			  var data5 = responseJson.price5;
			  var data6 = responseJson.price6;
			  var data7 = responseJson.price7;
			  var data8 = responseJson.price8;
			  var data9 = responseJson.price9;
			  var data10 = responseJson.price10;
			  var data11 = responseJson.price11;
			  var data12 = responseJson.price12;
			  var data13 = responseJson.price13;
			  var data14 = responseJson.price14;
			  var data15 = responseJson.price15;

			  
			  var data1B = responseJson.price1B;
			  var data2B = responseJson.price2B;
			  var data3B = responseJson.price3B;
			  var data4B = responseJson.price4B;
			  var data5B = responseJson.price5B;
			  var data6B = responseJson.price6B;
			  var data7B = responseJson.price7B;
			  var data8B = responseJson.price8B;
			  var data9B = responseJson.price9B;
			  var data10B = responseJson.price10B;
			  var data11B = responseJson.price11B;
			  var data12B = responseJson.price12B;
			  var data13B = responseJson.price13B;
			  var data14B = responseJson.price14B;
			  var data15B = responseJson.price15B;

			  var compResult = "<table id = \"compResult\">" 
						  	  + "<tr> <td class = \"eval\"> Higher Price </td> <td id = \"hrP\"></td> </tr> "
						  	  + "<tr> <td class = \"eval\"> Higher Return </td> <td id = \"hrR\"></td> </tr> "
						  	  + "<tr> <td class = \"eavl\"> Lower Risk </td> <td id = \"lrR\"></td> </tr>"
						  	  + "<tr> <td class = \"eavl\"> Market Dominant </td> <td id = \"mD\"></td> </tr>"
						  	  + "</table>";	
			  var graphDiv = "<div class= \"summary\" id = \"graph\"> <canvas id=\"myChart\"> </canvas>" 
					       + "<script> new Chart(document.getElementById(\"myChart\"), {" 
					       + "type: 'line',data: " 
					       + "{labels: ['Day1','Day2','Day3','Day4','Day5','Day6','Day7','Day8','Day9','Day10', 'Day11','Day12','Day13','Day14','Day15']," 
					       + "datasets: [{ data: [" 
					       + data1 + "," + data2 + "," + data3 + "," + data4 + "," + data5 + "," + data6 + "," + data7 + "," + data8 + "," + data9 + "," + data10 + ","
					       + data11 + "," + data12 + "," + data13 + "," + data14 + "," + data15
					       +"]," 
					       + "label: \"" 
					       + stockID 
					       + "\",borderColor: \"#3e95cd\",fill: false}," 
					       + "{ data: [" 
					       + data1B + "," + data2B + "," + data3B + "," + data4B + "," + data5B + "," + data6B + "," + data7B + "," + data8B + "," + data9B + "," + data10B + ","
					       + data11B + "," + data12B + "," + data13B + "," + data14B + "," + data15B
					       +"]," 
					       + "label: \"" 
					       + stockID2 
					       + "\",borderColor: \"#8e5ea2\",fill: false}]},"       
					       + "options: {title: {display: true,text: 'Stock price change'}}});" 
					       + "</script></div>";
			  var cbTable = "<table id = \"cbTable\">" 
					  	  + "<tr> <td class = \"cellWithComment\"> Stock ID <span class = \"comment\"> The unique identifier of a stock in market trade </span> </td> <td id = \"std\"></td> </tr> "
					  	  + "<tr> <td class = \"cellWithComment\"> Company <span class = \"comment\"> Company's Name </span> </td> <td id = \"company\"></td> </tr> "
					  	  + "<tr> <td class = \"cellWithComment\"> Industry <span class = \"comment\"> Classification of the company </span> </td> <td id = \"industry\"></td> </tr>"
					  	  + "<tr> <td class = \"cellWithComment\"> Description <span class = \"comment\"> What does the company do </span> </td> <td id = \"discrip\"></td> </tr>"
					  	  + "<tr> <td class = \"cellWithComment\"> URL <span class = \"comment\"> Company's website </span></td> <td id = \"url\"></td> </tr>"
					  	  +"</table>";
			  var cpTable = "<table id = \"cpTable\">" 
					      + "<tr> <td class = \"cellWithComment\"> Price <span class = \"comment\"> The latest price - 15 mins delayed </span></td> <td id = \"curPrice\"></td> </tr> "
					      + "<tr> <td class = \"cellWithComment\"> Open <span class = \"comment\"> Price at which a security first trades upon the opening of an exchange on a given trading day </span> </td> <td id = \"opPrice\"></td> <td class = \"cellWithComment\" id = \"hP\"> High <span class = \"comment\"> The highest price attained during the trading session </span></td> <td id = \"hPrice\"></td></tr>"
					      + "<tr> <td class = \"cellWithComment\"> Close <span class = \"comment\"> Last price at which a stock trades during a regular trading session </span></td> <td id = \"clPrice\"></td> <td class = \"cellWithComment\"> Low <span class = \"comment\"> The lowest price during the trading session </span></td> <td id = \"lPrice\"></td></tr>"
					      +"</table>";
			  var cmTable = "<table id = \"cmTable\">" 
						  + "<tr> <td class = \"cellWithComment\"> Market Cap <span class = \"comment\">  The market value of the company's outstanding shares:</br> [Large - slower growth with lower risk] </br> [Small - higher growth potential with higher risk]  </span></td> <td id = \"mrkCap\"></td> <td class = \"cellWithComment\"> EPS <span class = \"comment\"> Earnings per share, how much money the company is making in profits per share:</br> [Higher - more profitable] </span></td> <td id = \"eps\"></td></tr>"
						  + "<tr> <td class = \"cellWithComment\"> Beta <span class = \"comment\"> Violatility of the stock in comparision to market overall risk:</br> [Higher than 1 - more volatile than overall market] </br> [Less than 1 - less volatile than overall market] </span></td> <td id = \"beta\"></td> <td class = \"cellWithComment\"> Dividend <span class = \"comment\"> Rate of return in the form of cash dividends to shareholders:</br> [Higher - better for current stage]</span></td> <td id = \"div\"></td></tr>"
						  + "<tr> <td class = \"cellWithComment\"> PE Ratio <span class = \"comment\"> The highest price an investor paid for $1 of the company's earnings:</br> [Higher - more profitable but may be overpriced] </br> [Lower - less proftitable but maybe underpriced] </br> [Equal to 1 - Price matches the company's profitablity] </span></td> <td id = \"pe\"></td> <td class = \"cellWithComment\"> ROA <span class = \"comment\"> Return on asset: [Higher - better] </span></td> <td id = \"roa\"></td></tr>"
						  + "<tr> <td class = \"cellWithComment\"> Week52High <span class = \"comment\"> The highest price at which the stock was trade during the previous 52 weeks </span></td> <td id = \"wh\"></td> <td class = \"cellWithComment\"> Week52Low <span class = \"comment\"> The lowest price at which the stock was trade during the previous 52 weeks </span></td> <td id = \"wl\"></td></tr>"
						  +"</table>";
			  var cbTable2 = "<table id = \"cbTable2\">" 
				  		   + "<tr> <td class = \"cellWithComment\"> Stock ID <span class = \"comment\"> The unique identifier of a stock in market trade </span> </td> <td id = \"std2\"></td> </tr> "
				  		   + "<tr> <td class = \"cellWithComment\"> Company <span class = \"comment\"> Company's Name </span> </td> <td id = \"company2\"></td> </tr> "
				  		   + "<tr> <td class = \"cellWithComment\"> Industry <span class = \"comment\"> Classification of the company </span></td> <td id = \"industry2\"></td> </tr>"
				  		   + "<tr> <td class = \"cellWithComment\"> Description <span class = \"comment\"> What does the company do </span></td> <td id = \"discrip2\"></td> </tr>"
				  		   + "<tr> <td class = \"cellWithComment\"> URL <span class = \"comment\"> Company's website </span> </td> <td id = \"url\"></td> </tr>"
				  		   +"</table>";
			  var cpTable2 = "<table id = \"cpTable2\">" 
					       + "<tr> <td class = \"cellWithComment\"> Price <span class = \"comment\"> The latest price - 15 mins delayed </span></td> <td id = \"curPrice2\"></td> </tr> "
					       + "<tr> <td class = \"cellWithComment\"> Open <span class = \"comment\"> Price at which a security first trades upon the opening of an exchange on a given trading day </span> </td> <td id = \"opPrice2\"></td> <td class = \"cellWithComment\" id = \"hP2\"> High <span class = \"smallComment\"> The highest price attained during the trading session </span></td> <td id = \"hPrice2\"></td></tr>"
					       + "<tr> <td class = \"cellWithComment\"> Close <span class = \"comment\"> Last price at which a stock trades during a regular trading session </span> </td> <td id = \"clPrice2\"></td> <td class = \"cellWithComment\"> Low <span class = \"smallComment\"> The lowest price during the trading session </span></td> <td id = \"lPrice2\"></td></tr>"
					       +"</table>";
			  var cmTable2 = "<table id = \"cmTable2\">" 
						   + "<tr> <td class = \"cellWithComment\"> Market Cap <span class = \"comment\">  The market value of the company's outstanding shares:</br> [Large - slower growth with lower risk] </br> [Small - higher growth potential with higher risk]  </span></td> <td id = \"mrkCap2\"></td> <td class = \"cellWithComment\"> EPS <span class = \"smallComment\"> Earnings per share, how much money the company is making in profits per share:</br> [Higher - more profitable] </span></td> <td id = \"eps2\"></td></tr>"
						   + "<tr> <td class = \"cellWithComment\"> Beta <span class = \"comment\"> Violatility of the stock in comparision to market overall risk:</br> [Higher than 1 - more volatile than overall market] </br> [Less than 1 - less volatile than overall market] </span></td> <td id = \"beta2\"></td> <td class = \"cellWithComment\"> Dividend <span class = \"smallComment\"> Rate of return in the form of cash dividends to shareholders:</br> [Higher - better for current stage]</span></td> <td id = \"div2\"></td></tr>"
						   + "<tr> <td class = \"cellWithComment\"> PE Ratio <span class = \"comment\"> The highest price an investor paid for $1 of the company's earnings:</br> [Higher - more profitable but may be overpriced] </br> [Lower - less proftitable but maybe underpriced] </br> [Equal to 1 - Price matches the company's profitablity] </span></td> <td id = \"pe2\"></td> <td class = \"cellWithComment\"> ROA <span class = \"smallComment\"> Return on asset: [Higher - better] </span></td> <td id = \"roa2\"></td></tr>"
						   + "<tr> <td class = \"cellWithComment\"> Week52High <span class = \"comment\"> The highest price at which the stock was trade during the previous 52 weeks </span></td> <td id = \"wh2\"></td> <td class = \"cellWithComment\"> Week52Low <span class = \"smallComment\"> The lowest price at which the stock was trade during the previous 52 weeks </span> </td> <td id = \"wl2\"></td></tr>"
						   +"</table>";
			  $("#inputForm").empty();
			  $("#searchArea").empty();
			  $("#main-area").empty();
			  $("#main-area").css('overflow','scroll');
			  $("#main-area").append('<div class = "cArea"></div>'); 
			  $(".cArea").append('<div class = "summary" id = "summaryResult"></div>');
			  $(".cArea").append(graphDiv);
			  $(".cArea").append('<div id = "stk1"></div>');
			  $(".cArea").append('<div id = "stk2"></div>');
			  $("#summaryResult").append(compResult);
			  $("#stk1").append('<div class= "compArea" id = "cbtb"></div>')
			  $("#stk1").append('<div class= "compArea" id = "cptb"></div>')
			  $("#stk1").append('<div class= "compArea" id = "cmtb"></div>')
			  $("#cbtb").append(cbTable)
			  $("#cptb").append(cpTable)
			  $("#cmtb").append(cmTable)
			 
			  $("#stk2").append('<div class= "compArea" id = "cbtb2"></div>')
			  $("#stk2").append('<div class= "compArea" id = "cptb2"></div>')
			  $("#stk2").append('<div class= "compArea" id = "cmtb2"></div>')
			  $("#cbtb2").append(cbTable2)
			  $("#cptb2").append(cpTable2)
			  $("#cmtb2").append(cmTable2)
		  
			 
			  var company = responseJson.company;
			  var industry = responseJson.industry;
			  var description = responseJson.description;
			  var url = responseJson.url;
			  var curPrice = responseJson.curPrice;
			  var openPrice = responseJson.openPrice;
			  var closePrice = responseJson.closePrice;
			  var highPrice = responseJson.highPrice;
			  var lowPrice = responseJson.lowPrice;
			  var marketCap = responseJson.marketCap;
			  var eps = responseJson.eps;
			  var beta = responseJson.beta;
			  var div= responseJson.div;
			  var peRatio = responseJson.peRatio;
			  var roa = responseJson.roa;
			  var week52H = responseJson.week52H;
			  var week52L = responseJson.week52L;
			  $("#std").html(stockID);
			  $("#company").html(company);
			  $("#industry").html(industry);
			  $("#discrip").html(description);
			  $("#url").html(url);
			  if (curPrice > 0){
				  $("#curPrice").html("$ " + curPrice);
			  }
			  else{
				  $("#curPrice").html("Not Available");
					 
			  }
			  if (openPrice > 0){
				  $("#opPrice").html("$ " + openPrice);
			  }
			  else{
				  $("#opPrice").html("Not Available");
					 
			  }
			  if (closePrice > 0){
				  $("#clPrice").html("$ " + closePrice);
			  }
			  else{
				  $("#clPrice").html("Not Available");
					 
			  }
			  if (highPrice > 0){
				  $("#hPrice").html("$ " + highPrice);
			  }
			  else{
				  $("#hPrice").html("Not Available");
					 
			  }
			  if (lowPrice > 0){
				  $("#lPrice").html("$ " + lowPrice);
			  }
			  else{
				  $("#lPrice").html("Not Available");
					 
			  }
			  if (marketCap> 0){
				  $("#mrkCap").html(marketCap);
			  }
			  else{
				  $("#mrkCap").html("Not Available");
					 
			  }
			  if (eps != 0){
				  $("#eps").html(eps);
			  }
			  else{
				  $("#eps").html("Not Available");
					 
			  }
			  if (beta> 0){
				  $("#beta").html(beta);
			  }
			  else{
				  $("#beta").html("Not Available");	 
			  }
			  if (div> 0){
				  $("#div").html(div);
			  }
			  else{
				  $("#div").html("Not Available");	 
			  }
			  if (peRatio != 0){
				  $("#pe").html(peRatio);
			  }
			  else{
				  $("#pe").html("Not Available");
					 
			  }
			  if (roa != 0){
				  $("#roa").html(roa);
			  }
			  else{
				  $("#roa").html("Not Available");	 
			  }
			  if (week52H > 0){
				  $("#wh").html(week52H);
			  }
			  else{
				  $("#wh").html("Not Available");	 
			  }
			  if (week52L> 0){
				  $("#wl").html(week52L);
			  }
			  else{
				  $("#wl").html("Not Available");
			  }	
				 
			  var company2 = responseJson.company2;
			  var industry2 = responseJson.industry2;
			  var description2 = responseJson.description2;
			  var url2 = responseJson.url2;
			  var curPrice2 = responseJson.curPrice2;
			  var openPrice2 = responseJson.openPrice2;
			  var closePrice2 = responseJson.closePrice2;
			  var highPrice2 = responseJson.highPrice2;
			  var lowPrice2 = responseJson.lowPrice2;
			  var marketCap2 = responseJson.marketCap2;
			  var eps2 = responseJson.eps2;
			  var beta2 = responseJson.beta2;
			  var div2 = responseJson.div2;
			  var peRatio2 = responseJson.peRatio2;
			  var roa2 = responseJson.roa2;
			  var week52H2 = responseJson.week52H2;
			  var week52L2 = responseJson.week52L2;
			  $("#std2").html(stockID2);
			  $("#company2").html(company2);
			  $("#industry2").html(industry2);
			  $("#discrip2").html(description2);
			  $("#url2").html(url2);
			  if (curPrice2 > 0){
				  $("#curPrice2").html("$ " + curPrice2);
			  }
			  else{
				  $("#curPrice2").html("Not Available");	 
			  }
			  if (openPrice2 > 0){
				  $("#opPrice2").html("$ " + openPrice2);
			  }
			  else{
				  $("#opPrice2").html("Not Available");	 
			  }
			  if (closePrice2 > 0){
				  $("#clPrice2").html("$ " + closePrice2);
			  }
			  else{
				  $("#clPrice2").html("Not Available");	 
			  }
			  if (highPrice2 > 0){
				  $("#hPrice2").html("$ " + highPrice2);
			  }
			  else{
				  $("#hPrice2").html("Not Available");	 
			  }
			  if (lowPrice2 > 0){
				 $("#lPrice2").html("$ " + lowPrice2);
			  }
			  else{
				  $("#lPrice2").html("Not Available");	 
			  }
			  if (marketCap2 > 0){
				  $("#mrkCap2").html(marketCap2);
			  }
			  else{
				  $("#mrkCap2").html("Not Available");	 
			  }
			  if (eps2 != 0){
				  $("#eps2").html(eps2);
			  }
			  else{
				  $("#eps2").html("Not Available");	 
			  }
			  if (beta2 > 0){
				  $("#beta2").html(beta2);
			  }
			  else{
				  $("#beta2").html("Not Available");	 
			  }
			  if (div2 > 0){
				  $("#div2").html(div2 );
			  }
			  else{
				  $("#div2").html("Not Available");
			  }
			  if (peRatio2 != 0){
				  $("#pe2").html(peRatio2);
			  }
			  else{
				  $("#pe2").html("Not Available");
			  }
			  if (roa2 != 0){
				  $("#roa2").html(roa2);
			  }
			  else{
				  $("#roa2").html("Not Available");
			  }
			  if (week52H2 > 0){
				  $("#wh2").html(week52H2);
			  }
			  else{
				  $("#wh2").html("Not Available"); 
			  }
			  if (week52L2> 0){
				  $("#wl2").html(week52L2);
			  }
			  else{
				  $("#wl2").html("Not Available");
			  }	
			  var higherPrice = responseJson.priceWinner;
			  var higherReturn = responseJson.returnWinner;
			  var lowerRisk = responseJson.riskWinner;
			  var marketDominant = responseJson.marketDominant;
			  
			  $("#hrP").html(higherPrice);
			  $("#hrR").html(higherReturn);
			  $("#lrR").html(lowerRisk);
			  $("#mD").html(marketDominant);
		  }).fail(function (){
		        alert("Invalid symbol(s). Please enter valis symbols for comparison!");
		  });
		
		}
	
});
