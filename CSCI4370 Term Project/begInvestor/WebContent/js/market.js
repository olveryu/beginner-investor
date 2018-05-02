$(document).ready(function(){
    $("#mini_boardOne").hide();
    $("#mini_boardTwo").hide();
    $("#resultArea").hide();
    var checked = [];
    var industry;
    
    $("#choosesubmit").click(function(){
    	industry = $("#industry option:selected").val();
    	$("#resultInd").text(industry);
    	$("#mini_mssg").hide();
    	$("#mini_boardOne").show();
    	return false;
    });
    
    $("#b1submit").click(function(){
    	$('input:checkbox[name=matrix]:checked').each(function(){
    		checked.push($(this).val());
    	});
    	$("#mini_boardOne").hide();
    	$("#mini_boardTwo").show();
    	return false;
    });
    
    $("#b2submit").click(function(){
    	$('input:checkbox[name=matrix]:checked').each(function(){
    		checked.push($(this).val());
    	});
    	
    	$("#mini_nav").hide();
    	$("#resultArea").show();
    	$("#betaTb").hide();
		$("#pcTb").hide();
		$("#mcTb").hide();
		$("#epsTb").hide();
		$("#perTb").hide();
		$("#diviTb").hide();
		var pricechange = false;
		var beta = false;
		var marketcap = false;
		var eps = false;
		var peratio = false;
		var dividend = false;
		var tbTitle = "";
		var tbId = "";
    	
    	for(var i=0; i < checked.length; i++){
    		switch (checked[i]){
    		case "beta":
    			beta = true;
    			break;
    		case "pricechange":
    			pricechange = true;
    			break;
    		case "marketcap":
    			marketcap = true;
    			break;
    		case "eps":
    			eps = true;
    			break;
    		case "peratio":
    			peratio = true;
    			break;
    		case "dividend":
    			dividend = true;
    			break;
    		}
    	}
    	
    	var obj = {"industry": industry, "pricechange": pricechange, "beta": beta, "marketcap": marketcap, "eps": eps, "peratio": peratio, "div": dividend};
    	var jsonstr = $.param(obj);
    	//document.getElementById("resultArea").innerHTML=jsonstr;
    	
    	var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
        	if (this.readyState == 4 && this.status == 200) {
        		//document.getElementById("resultArea").innerHTML=this.responseText;
	        	var obj = JSON.parse(this.responseText);
	        	var objtemp = obj;
	        	

	        	for(var a = 0; a < 6; a++){
	            	var inarr = [];
	            	var objarr = [];
	            	var arrtemp = [];
	            	obj = objtemp;
		        	if(a==0 && obj.pcTb != ""){
		        		obj = JSON.parse(obj.pcTb);
		        	}else if(a==1 && obj.betaTb != ""){
		        		obj = JSON.parse(obj.betaTb);
		        	}else if(a==2 && obj.mcTb != ""){
		        		obj = JSON.parse(obj.mcTb);
		        	}else if(a==3 && obj.epsTb != ""){
		        		obj = JSON.parse(obj.epsTb);
		        	}else if(a==4 && obj.perTb != ""){
		        		obj = JSON.parse(obj.perTb);
		        	}else if(a==5 && obj.diviTb != ""){
		        		obj = JSON.parse(obj.diviTb);
		        	}else
		        		continue;
		        	
	        	    var emptycount = 0;
		        	for(var i in obj){
		        		objarr.push([i, obj[i]]);
		        		var inobj;
		        	    for(var k = 0; k < objarr.length; k++){
		        	    	inobj = JSON.parse(objarr[k][1]);
		        	    }
		        	    
		        	    if(inobj.sym=="N/A"){
		        	    	emptycount++;
		        	    }else{
		        	    	arrtemp.push(inobj);
		        	    }
		        	}
		        	if(a==0 || a == 1){
		        		arrtemp = sortJSON(arrtemp, 'matrix', '123');
		        	}else{
		        		arrtemp = sortJSON(arrtemp, 'matrix', '321');
		        	}
		        	
		        	if(emptycount > 0){
		        		for(var n = 0; n < emptycount; n++){
		        			var obj1 = {"sym":"N/A", "cn":"N/A", "matrix":"N/A"};
		        			arrtemp.push(obj1);
		        		}
		        	}
		        		
		        	for(var k = 0; k < arrtemp.length; k++){
		        		var inobj = arrtemp[k];
		        		for(var j in inobj){
		        			inarr.push([j, inobj[j]]);
		        		}
		        	}
		        	
		        		var r = new Array(), j = -1;
		        		r[++j] = '<table>';
		        		r[++j] = '<tr><th></th><th>';
		        		
		        		switch (a){
		        		case 0:
		        			tbTitle = 'Price Change';
		        			tbId = "#pcTb";
		        			break;
		        		case 1:
		        			tbTitle = 'Beta';
		        			tbId = "#betaTb";
		        			break;
		        		case 2:
		        			tbTitle = 'Market Cap';
		        			tbId = "#mcTb";
		        			break;
		        		case 3:
		        			tbTitle = 'EPS';
		        			tbId = "#epsTb";
		        			break;
		        		case 4:
		        			tbTitle = 'P/E Ratio';
		        			tbId = "#perTb";
		        			break;
		        		case 5:
		        			tbTitle = 'Dividend';
		        			tbId = "#diviTb";
		        			break;
		        		}
		        		
		        		 r[++j] = tbTitle;
		        		 r[++j] = '</th><th></th></tr>';
		        		 r[++j] = '<tr id="titlerow"><td>';
		        		 r[++j] = "Symbol";
		        		 r[++j] = '</td><td>';
		        		 r[++j] = "Company Name";
		        	     r[++j] = '</td><td>';
		        	     r[++j] = tbTitle;
		        	     r[++j] = '</td></tr>';
		        	
		        	     for (var key=0; key<inarr.length; key=key+3){
			        	     r[++j] ='<tr><td>';
			        	     r[++j] = inarr[key][1];
			        	     r[++j] = '</td><td>';
			        	     r[++j] = inarr[key+1][1];
			        	     r[++j] = '</td><td>';
			        	     r[++j] = inarr[key+2][1];
			        	     r[++j] = '</td></tr>';
		        	     }
		        	     r[++j] = '</table>';
		        	 $(tbId).html(r.join(''));
	        	
        		}
        	}
        };
    	
    	xhttp.open("GET","MarketServlet?jsonstr="+jsonstr, true);
    	xhttp.send();
    	
    	for(var i=0; i < checked.length; i++){
    		switch (checked[i]){
    		case "beta":
    			$("#betaTb").show();
    			break;
    		case "pricechange":
    			$("#pcTb").show();
    			break;
    		case "marketcap":
    			$("#mcTb").show();
    			break;
    		case "eps":
    			$("#epsTb").show();
    			break;
    		case "peratio":
    			$("#perTb").show();
    			break;
    		case "dividend":
    			$("#diviTb").show();
    			break;
    		}
    	}
    	return false;
    });
    
});

function sortJSON(data, key, way) {
    return data.sort(function(a, b) {
        var x = a[key]; var y = b[key];
        if (way === '123' ) { return ((x < y) ? -1 : ((x > y) ? 1 : 0)); }
        if (way === '321' ) { return ((x > y) ? -1 : ((x < y) ? 1 : 0)); }
    });
}