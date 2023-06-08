package db;

public class MyBookMark {
	private int MYBOOKMARK_ID;
	private int BOOKMARK_ID;
	private String WIFI_NAME;
	private String BOOKMARK_NAME; 
	private String REG_DT;
	
	public int getMYBOOKMARK_ID() {
		return MYBOOKMARK_ID;
	}
	public void setMYBOOKMARK_ID(int mYBOOKMARK_ID) {
		MYBOOKMARK_ID = mYBOOKMARK_ID;
	}
	public int getBOOKMARK_ID() {
		return BOOKMARK_ID;
	}
	public void setBOOKMARK_ID(int bOOKMARK_ID) {
		BOOKMARK_ID = bOOKMARK_ID;
	}
	public String getWIFI_NAME() {
		return WIFI_NAME;
	}
	public void setWIFI_NAME(String wIFI_NAME) {
		WIFI_NAME = wIFI_NAME;
	}
	public String getBOOKMARK_NAME() {
		return BOOKMARK_NAME;
	}
	public void setBOOKMARK_NAME(String bOOKMARK_NAME) {
		BOOKMARK_NAME = bOOKMARK_NAME;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	
	
}
