package objectlayer;

public class RankResults {
	private String pcTb = "";
	private String betaTb = "";
	private String mcTb = "";
	private String epsTb = "";
	private String perTb = "";
	private String diviTb ="";
	
	public RankResults(){
		this.pcTb = "";
		this.betaTb = "";
		this.mcTb = "";
		this.epsTb = "";
		this.perTb = "";
		this.diviTb = "";
	}
	
	public RankResults(String pcTb, String betaTb, String mcTb, String epsTb, String perTb, String diviTb){
		this.pcTb = pcTb;
		this.betaTb = betaTb;
		this.mcTb = mcTb;
		this.epsTb = epsTb;
		this.perTb = perTb;
		this.diviTb = diviTb;
	}

	
	public void setPCTb(String pcTb) {
		this.pcTb = pcTb;
	}
	
	public void setBetaTb(String betaTb) {
		this.betaTb = betaTb;
	}
	
	public void setMCTb(String mcTb) {
		this.mcTb = mcTb;
	}
	
	public void setEPSTb(String epsTb) {
		this.epsTb = epsTb;
	}
	
	public void setPERTb(String perTb) {
		this.perTb = perTb;
	}
	
	public void setDiviTb(String diviTb) {
		this.diviTb = diviTb;
	}
	
	public String getPCTb() {
		return pcTb;
	}
	
	public String getBetaTb() {
		return betaTb;
	}
	
	public String getMCTb() {
		return mcTb;
	}
	
	public String getEPSTb() {
		return epsTb;
	}
	
	public String getPERTb() {
		return perTb;
	}
	
	public String getDiviTb() {
		return diviTb;
	}
	
}
