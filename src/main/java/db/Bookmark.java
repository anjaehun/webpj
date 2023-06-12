package db;

public class Bookmark {
 // 커밋 테스트 하기
private int BOOKMARK_ID ; 
   // 북마크 이름 
   private String BOOKMARK_NAME; 
   // 북마크 구분자 
   private int ORDER_NO ; 
   // 등록일자 
   private String REG_DT ; 
   // 수정일자 
   private String MOD_DT ; 
   
   public int getBOOKMARK_ID() {
		return BOOKMARK_ID;
	}
	public void setBOOKMARK_ID(int bOOKMARK_ID) {
		BOOKMARK_ID = bOOKMARK_ID;
	}
	public String getBOOKMARK_NAME() {
		return BOOKMARK_NAME;
	}
	public void setBOOKMARK_NAME(String bOOKMARK_NAME) {
		BOOKMARK_NAME = bOOKMARK_NAME;
	}
	public int getORDER_NO() {
		return ORDER_NO;
	}
	public void setORDER_NO(int oRDER_NO) {
		ORDER_NO = oRDER_NO;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	public String getMOD_DT() {
		return MOD_DT;
	}
	public void setMOD_DT(String mOD_DT) {
		MOD_DT = mOD_DT;
	}
   
   
}
