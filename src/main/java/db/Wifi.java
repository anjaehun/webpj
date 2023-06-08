package db;

public class Wifi {
//	"X_SWIFI_MGR_NO": "---EP000001",
//    "X_SWIFI_WRDOFC": "은평구",
//    "X_SWIFI_MAIN_NM": "갈현1동주민센터",
//    "X_SWIFI_ADRES1": "갈현동 갈현로 301",
//    "X_SWIFI_ADRES2": "갈현1동 1층",
//    "X_SWIFI_INSTL_FLOOR": "",
//    "X_SWIFI_INSTL_TY": "7-1. 공공 - 행정",
//    "X_SWIFI_INSTL_MBY": "의견(자치)",
//    "X_SWIFI_SVC_SE": "공공WiFi",
//    "X_SWIFI_CMCWR": "자가망_U무선망",
//    "X_SWIFI_CNSTC_YEAR": "2011",
//    "X_SWIFI_INOUT_DOOR": "실내",
//    "X_SWIFI_REMARS3": "",
//    "LAT": "126.9167",
//    "LNT": "37.62364",
//    "WORK_DTTM": "2023-05-29 10:58:24.0"
	private String X_SWIFI_MGR_NO;
	private String X_SWIFI_WRDOFC;
	
	private String X_SWIFI_MAIN_NM;
	private String X_SWIFI_ADRES1;
	private String X_SWIFI_ADRES2;
	private String X_SWIFI_INSTL_FLOOR;
	private String X_SWIFI_INSTL_TY;
	private String X_SWIFI_INSTL_MBY;
	private String X_SWIFI_SVC_SE;
	private String X_SWIFI_CMCWR;
	private String X_SWIFI_CNSTC_YEAR;
	private String X_SWIFI_INOUT_DOOR;
	private String X_SWIFI_REMARS3;
	private Double LAT;
	private Double LNT;
	private String WORK_DTTM;
	private Double KM;
	private String X_NEAR_LOCATION;
	
	
	
	public String getX_NEAR_LOCATION() {
		return X_NEAR_LOCATION;
	}
	public void setX_NEAR_LOCATION(String x_NEAR_LOCATION) {
		X_NEAR_LOCATION = x_NEAR_LOCATION;
	}
	public String getX_SWIFI_WRDOFC() {
		return X_SWIFI_WRDOFC;
	}
	public void setX_SWIFI_WRDOFC(String x_SWIFI_WRDOFC) {
		X_SWIFI_WRDOFC = x_SWIFI_WRDOFC;
	}
	
	public Double getKM() {
		return KM;
	}
	public void setKM(Double kM) {
		KM = kM;
	}
	// getter and setter 
	public String getX_SWIFI_MGR_NO() {
		return X_SWIFI_MGR_NO;
	}
	public void setX_SWIFI_MGR_NO(String x_SWIFI_MGR_NO) {
		X_SWIFI_MGR_NO = x_SWIFI_MGR_NO;
	}
	public String getX_SWIFI_MAIN_NM() {
		return X_SWIFI_MAIN_NM;
	}
	public void setX_SWIFI_MAIN_NM(String x_SWIFI_MAIN_NM) {
		X_SWIFI_MAIN_NM = x_SWIFI_MAIN_NM;
	}
	public String getX_SWIFI_ADRES1() {
		return X_SWIFI_ADRES1;
	}
	public void setX_SWIFI_ADRES1(String x_SWIFI_ADRES1) {
		X_SWIFI_ADRES1 = x_SWIFI_ADRES1;
	}
	public String getX_SWIFI_ADRES2() {
		return X_SWIFI_ADRES2;
	}
	public void setX_SWIFI_ADRES2(String x_SWIFI_ADRES2) {
		X_SWIFI_ADRES2 = x_SWIFI_ADRES2;
	}
	public String getX_SWIFI_INSTL_FLOOR() {
		return X_SWIFI_INSTL_FLOOR;
	}
	public void setX_SWIFI_INSTL_FLOOR(String x_SWIFI_INSTL_FLOOR) {
		X_SWIFI_INSTL_FLOOR = x_SWIFI_INSTL_FLOOR;
	}
	public String getX_SWIFI_INSTL_TY() {
		return X_SWIFI_INSTL_TY;
	}
	public void setX_SWIFI_INSTL_TY(String x_SWIFI_INSTL_TY) {
		X_SWIFI_INSTL_TY = x_SWIFI_INSTL_TY;
	}
	public String getX_SWIFI_INSTL_MBY() {
		return X_SWIFI_INSTL_MBY;
	}
	public void setX_SWIFI_INSTL_MBY(String x_SWIFI_INSTL_MBY) {
		X_SWIFI_INSTL_MBY = x_SWIFI_INSTL_MBY;
	}
	public String getX_SWIFI_SVC_SE() {
		return X_SWIFI_SVC_SE;
	}
	public void setX_SWIFI_SVC_SE(String x_SWIFI_SVC_SE) {
		X_SWIFI_SVC_SE = x_SWIFI_SVC_SE;
	}
	public String getX_SWIFI_CMCWR() {
		return X_SWIFI_CMCWR;
	}
	public void setX_SWIFI_CMCWR(String x_SWIFI_CMCWR) {
		X_SWIFI_CMCWR = x_SWIFI_CMCWR;
	}
	public String getX_SWIFI_CNSTC_YEAR() {
		return X_SWIFI_CNSTC_YEAR;
	}
	public void setX_SWIFI_CNSTC_YEAR(String x_SWIFI_CNSTC_YEAR) {
		X_SWIFI_CNSTC_YEAR = x_SWIFI_CNSTC_YEAR;
	}
	public String getX_SWIFI_INOUT_DOOR() {
		return X_SWIFI_INOUT_DOOR;
	}
	public void setX_SWIFI_INOUT_DOOR(String x_SWIFI_INOUT_DOOR) {
		X_SWIFI_INOUT_DOOR = x_SWIFI_INOUT_DOOR;
	}
	public String getX_SWIFI_REMARS3() {
		return X_SWIFI_REMARS3;
	}
	public void setX_SWIFI_REMARS3(String x_SWIFI_REMARS3) {
		X_SWIFI_REMARS3 = x_SWIFI_REMARS3;
	}
	public Double getLAT() {
		return LAT;
	}
	public void setLAT(Double lAT) {
		LAT = lAT;
	}
	public Double getLNT() {
		return LNT;
	}
	public void setLNT(Double lNT) {
		LNT = lNT;
	}
	public String getWORK_DTTM() {
		return WORK_DTTM;
	}
	public void setWORK_DTTM(String wORK_DTTM) {
		WORK_DTTM = wORK_DTTM;
	}
	
	
}
