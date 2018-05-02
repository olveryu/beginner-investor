package objectlayer;

import com.google.gson.Gson;
import org.json.*;

public class stk extends JSONObject{
	public stk(String sym, String cn, double matrix) throws JSONException {
		this.put("sym", sym);
		this.put("cn", cn);
		this.put("matrix", matrix);
	}
}
