package db;

public class History {
	private int HISTORY_ID;
    private double LAT; 
    private double LNT; 
    private String INQUIRY_DATE ;
    
	public int getHISTORY_ID() {
		return HISTORY_ID;
	}
	public void setHISTORY_ID(int hISTORY_ID) {
		HISTORY_ID = hISTORY_ID;
	}
	public double getLAT() {
		return LAT;
	}
	public void setLAT(double lAT) {
		LAT = lAT;
	}
	public double getLNT() {
		return LNT;
	}
	public void setLNT(double lNT) {
		LNT = lNT;
	}
	public String getINQUIRY_DATE() {
		return INQUIRY_DATE;
	}
	public void setINQUIRY_DATE(String iNQUIRY_DATE) {
		INQUIRY_DATE = iNQUIRY_DATE;
	}
    
}
