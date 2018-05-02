package objectlayer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class Rank extends JSONObject {
		
	public Rank() {
		
	}
	
	public Rank(String stk1, String stk2, String stk3, String stk4, String stk5, String stk6,
			String stk7, String stk8, String stk9, String stk10) throws JSONException {
		this.put("stk1", stk1);
		this.put("stk2", stk2);
		this.put("stk3", stk3);
		this.put("stk4", stk4);
		this.put("stk5", stk5);
		this.put("stk6", stk6);
		this.put("stk7", stk7);
		this.put("stk8", stk8);
		this.put("stk9", stk9);
		this.put("stk10", stk10);
	}
	
	public Rank(JSONObject js1, JSONObject js2, JSONObject js3, JSONObject js4, JSONObject js5, JSONObject js6,
			JSONObject js7, JSONObject js8, JSONObject js9, JSONObject js10) throws JSONException {
		String stk1 = js1.toString();
		String stk2 = js1.toString();
		String stk3 = js1.toString();
		String stk4 = js1.toString();
		String stk5 = js1.toString();
		String stk6 = js1.toString();
		String stk7 = js1.toString();
		String stk8 = js1.toString();
		String stk9 = js1.toString();
		String stk10 = js1.toString();
		this.put("stk1", stk1);
		this.put("stk2", stk2);
		this.put("stk3", stk3);
		this.put("stk4", stk4);
		this.put("stk5", stk5);
		this.put("stk6", stk6);
		this.put("stk7", stk7);
		this.put("stk8", stk8);
		this.put("stk9", stk9);
		this.put("stk10", stk10);
	}
	
}
