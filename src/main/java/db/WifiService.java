package db;

import java.io.InputStreamReader;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WifiService {

	private static final String API_KEY = "4a4c6a4c556a65683131387a616d696a";
	
	public long totalcnt = 0; 
	/*
	 * 데이터 파싱 하기 
	 * 테이블 명 : TB_WIFI_LOCATION_INFO  
	 * */
    public String urlParser(String start, String end) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");

        String jsonData = "";
        urlBuilder.append("/" + API_KEY);
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(start, "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(end, "UTF-8"));

        try {
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        sb.append(line);
                    }
                    jsonData = sb.toString();
                    //System.out.println(jsonData);
                }
            } else {
                try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                    String error;
                    while ((error = rd.readLine()) != null) {
                        System.err.println(error);
                    }
                }
            }

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonData;
    }
    
    
    /**
     * 토탈 카운트 구하기(파싱 json data 뽑기)
     * @return
     */
    public long totalCnt() {
    	JSONParser jsonParser = new JSONParser();
    	WifiService test = new WifiService();
    	
    	JSONObject total = null;
		try {
			total = (JSONObject) jsonParser.parse(test.urlParser("1", "1"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JSONObject totalcount = (JSONObject) total.get("TbPublicWifiInfo");
        long totalcnt = (long) totalcount.get("list_total_count");
    	
		return totalcnt;
    	
    }
	
    /**
     * json 나눈 코드 import 문 (totalcount return 값 (23304) 개수 넣기)
     * @throws ParseException
     * @throws IOException
     */
    public  void jsonImport() throws ParseException, IOException {
        String url = "jdbc:mariadb://127.0.0.1:3306/mission";
        String dbUserId = "root";
        String dbPassWord = "zerobase";

        // 1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        // 연결 관련 라이브러리 생성자
        Connection connection = null;

        // 상태 관련
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        // 결과 값 관련
        ResultSet rs = null;

        // 2. 커넥션 객체 생성
        JSONParser jsonParser = new JSONParser();
        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassWord);
            statement = connection.createStatement();
            WifiService apiExplorer = new WifiService();
            
           
            WifiService test = new WifiService();
            String cnt = apiExplorer.urlParser("1", "1");
            
            // 개수 
//            JSONObject total = (JSONObject) jsonParser.parse(test.urlParser("1", "1"));
//            JSONObject totalcount = (JSONObject) total.get("TbPublicWifiInfo");
            long listTotalCount = apiExplorer.totalCnt();
            
             // System.out.println("list_total_count: " + listTotalCount);
            
            
            
            // 첫번째로 1 ~ 1000, 1001 ~ 1000 , 2 ~2000 , 3 ~ 3000 , 4 ~ 4000, 
            
            long totalCount = listTotalCount;
            long batchSize = 1000;
            
            
            for (long i = 1; i <= totalCount; i += batchSize) {
                String start =  String.valueOf(i);
                String end = String.valueOf(Math.min(i + batchSize - 1, totalCount));
                
                // 해당 범위의 데이터를 처리하는 코드 작성
                // 예: apiExplorer.urlParser(start, end);
                // 4. 쿼리 실행
              
                // 처리한 결과에 대한 작업 수행
                JSONObject jsonObject = (JSONObject) jsonParser.parse(test.urlParser(start,end ));
                JSONObject tbPublicWifiInfo = (JSONObject) jsonObject.get("TbPublicWifiInfo");
                JSONArray rowArray = (JSONArray) tbPublicWifiInfo.get("row");
                
                String sql = "INSERT IGNORE INTO TB_WIFI_LOCATION_INFO (\r\n"
                		+ "    X_SWIFI_MGR_NO,\r\n"
                		+ "    X_SWIFI_WRDOFC,\r\n"
                		+ "    X_SWIFI_MAIN_NM,\r\n"
                		+ "    X_SWIFI_ADRES1,\r\n"
                		+ "    X_SWIFI_ADRES2,\r\n"
                		+ "    X_SWIFI_INSTL_FLOOR,\r\n"
                		+ "    X_SWIFI_INSTL_TY,\r\n"
                		+ "    X_SWIFI_INSTL_MBY,\r\n"
                		+ "    X_SWIFI_SVC_SE,\r\n"
                		+ "    X_SWIFI_CMCWR,\r\n"
                		+ "    X_SWIFI_CNSTC_YEAR,\r\n"
                		+ "    X_SWIFI_INOUT_DOOR,\r\n"
                		+ "    X_SWIFI_REMARS3,\r\n"
                		+ "    LAT,\r\n"
                		+ "    LNT,\r\n"
                		+ "    WORK_DTTM\r\n"
                		+ ") VALUES (\r\n"
                		+ "    ?,\r\n"
                		+ "    ?,\r\n"
                		+ "    ?,\r\n"
                		+ "    ?,\r\n"
                		+ "    ?,\r\n"
                		+ "    ?,\r\n"
                		+ "    ?,\r\n"
                		+ "    ?,\r\n"
                		+ "    ?,\r\n"
                		+ "    ?,\r\n"
                		+ "    ?,\r\n"
                		+ "    ?,\r\n"
                		+ "    ?,\r\n"
                		+ "    ?,\r\n"
                		+ "    ?,\r\n"
                		+ "    ?\r\n"
                		+ "); ";
                 
                int result = 0; 
	            for (Object obj : rowArray) {
	            	JSONObject rowObject = (JSONObject) obj;
//	            	Object X_SWIFI_MGR_NO = rowObject.get("X_SWIFI_MGR_NO");
//	            	Object X_SWIFI_WRDOFC = rowObject.get("X_SWIFI_WRDOFC");
//	            	Object X_SWIFI_MAIN_NM = rowObject.get("X_SWIFI_MAIN_NM");
//	            	Object X_SWIFI_ADRES1 = rowObject.get("X_SWIFI_ADRES1");
//	            	Object X_SWIFI_ADRES2 = rowObject.get("X_SWIFI_ADRES2");
//	            	Object X_SWIFI_INSTL_FLOOR = rowObject.get("X_SWIFI_INSTL_FLOOR");
//	            	Object X_SWIFI_INSTL_TY = rowObject.get("X_SWIFI_INSTL_TY");
//	            	Object X_SWIFI_INSTL_MBY = rowObject.get("X_SWIFI_INSTL_MBY");
//	            	Object X_SWIFI_SVC_SE = rowObject.get("X_SWIFI_SVC_SE");
//	            	Object X_SWIFI_CMCWR = rowObject.get("X_SWIFI_CMCWR");
//	            	Object X_SWIFI_CNSTC_YEAR = rowObject.get("X_SWIFI_CNSTC_YEAR");
//	            	Object X_SWIFI_INOUT_DOOR = rowObject.get("X_SWIFI_INOUT_DOOR");
//	            	Object X_SWIFI_REMARS3 = rowObject.get("X_SWIFI_REMARS3");
//	            	Object LAT = rowObject.get("LAT");
//	            	Object LNT = rowObject.get("LNT");
//	            	Object WORK_DTTM = rowObject.get("WORK_DTTM");
	            	
	            	
	            	 preparedStatement = connection.prepareStatement(sql);
	                 preparedStatement.setObject(1 ,  rowObject.get("X_SWIFI_MGR_NO"));
	                 preparedStatement.setObject(2 ,   rowObject.get("X_SWIFI_WRDOFC"));
	                 preparedStatement.setObject(3 ,  rowObject.get("X_SWIFI_MAIN_NM"));
	                 preparedStatement.setObject(4 ,  rowObject.get("X_SWIFI_ADRES1"));
	                 preparedStatement.setObject(5 ,  rowObject.get("X_SWIFI_ADRES2"));
	                 preparedStatement.setObject(6 ,  rowObject.get("X_SWIFI_INSTL_FLOOR"));
	                 preparedStatement.setObject(7 ,  rowObject.get("X_SWIFI_INSTL_TY"));
	                 preparedStatement.setObject(8 ,  rowObject.get("X_SWIFI_INSTL_MBY"));
	                 preparedStatement.setObject(9 ,  rowObject.get("X_SWIFI_SVC_SE"));
	                 preparedStatement.setObject(10,  rowObject.get("X_SWIFI_CMCWR"));
	                 preparedStatement.setObject(11,  rowObject.get("X_SWIFI_CNSTC_YEAR"));
	                 preparedStatement.setObject(12,  rowObject.get("X_SWIFI_INOUT_DOOR"));
	                 preparedStatement.setObject(13,  rowObject.get("X_SWIFI_REMARS3"));
	                 preparedStatement.setObject(14,  rowObject.get("LAT"));
	                 preparedStatement.setObject(15,  rowObject.get("LNT"));
	                 preparedStatement.setObject(16,  rowObject.get("WORK_DTTM"));
	                 int affected = preparedStatement.executeUpdate();
	                 // result = affected;
	                 if (affected > 0) {
	                   //  System.out.println("저장 성공");
	                 } else {
	                     System.out.println("저장 실패");
	                 }
	            }
	            
	           
            }
            System.out.println(totalCount + "건이 저장되었습니다2");
           // rs = statement.executeQuery(sql);
          
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 객체 연결 해제
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*
     * 데이터 파싱 인서트 코드 끝 
     * 테이블 명 : TB_WIFI_LOCATION_INFO  
   * */     
    
    /**
     * LIST 로 가까운 위치 20개 select (객체 넣기)
     * @return
     */
    public List<Wifi> list(String MyLat, String MyLnt){

        List<Wifi> WifiList = new ArrayList<>();    	
    	String url = "jdbc:mariadb://127.0.0.1:3306/mission";
        String dbUserId = "root";
        String dbPassWord = "zerobase";
        
        

        // 1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        // 연결 관련 라이브러리 생성자
        Connection connection = null;

        // 상태 관련
        Statement statement = null;

        PreparedStatement preparedStatement = null;

        // 결과 값 관련
        ResultSet rs = null;
//
//        MyLat = "37.5422976";
//        MyLnt = "127.13984";

        //2. 커넥션 객체 생성
        try {
            connection = DriverManager.getConnection(url,dbUserId,dbPassWord);

            statement = connection.createStatement();

            // 4. 쿼리 실행
            String sql = "SELECT *,\r\n"
            		+ "      round( (6371 * 2 * ASIN(SQRT(POWER(SIN((RADIANS(?) - RADIANS(LAT)) / 2), 2) + COS(RADIANS(?)) * COS(RADIANS(LAT)) * POWER(SIN((RADIANS(?) - RADIANS(LNT)) / 2), 2)))) ,3) AS KM\r\n"
            		+ "FROM TB_WIFI_LOCATION_INFO\r\n"
            		+ "ORDER BY KM\r\n"
            		+ "LIMIT 20;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,MyLat);
            preparedStatement.setString(2,MyLat);
            preparedStatement.setString(3,MyLnt);


            //  rs = statement.executeQuery(sql);
            rs = preparedStatement.executeQuery();


            while(rs.next()){
                String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
                String X_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
                String X_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
                double LAT = rs.getDouble("LAT");
                double LNT = rs.getDouble("LNT");
                String WORK_DTTM = rs.getString("WORK_DTTM");
                String X_NEAR_LOCATION = rs.getString("X_NEAR_LOCATION");
                double KM = rs.getDouble("KM");
                
                

              Wifi wifi = new Wifi(); 
               wifi.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
               
               wifi.setX_SWIFI_WRDOFC(X_SWIFI_WRDOFC);
               
               wifi.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
               
               wifi.setX_SWIFI_ADRES1(X_SWIFI_ADRES1);
               
               wifi.setX_SWIFI_ADRES2(X_SWIFI_ADRES2);
               
               wifi.setX_SWIFI_INSTL_FLOOR(X_SWIFI_INSTL_FLOOR);
               
               wifi.setX_SWIFI_INSTL_TY(X_SWIFI_INSTL_TY);
               
               wifi.setX_SWIFI_INSTL_MBY(X_SWIFI_INSTL_MBY);
               
               wifi.setX_SWIFI_SVC_SE(X_SWIFI_SVC_SE);
               
               wifi.setX_SWIFI_CMCWR(X_SWIFI_CMCWR);
               
               wifi.setX_SWIFI_CNSTC_YEAR(X_SWIFI_CNSTC_YEAR);
               
               wifi.setX_SWIFI_INOUT_DOOR(X_SWIFI_INOUT_DOOR);
               
               wifi.setX_SWIFI_REMARS3(X_SWIFI_REMARS3);
               
               wifi.setLAT(LAT);
               
               wifi.setLNT(LNT);
               
               wifi.setWORK_DTTM(WORK_DTTM);
               
               wifi.setX_NEAR_LOCATION(X_NEAR_LOCATION);
               
               wifi.setKM(KM);
               
               
               WifiList.add(wifi); 
               
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // 객체 연결 해제
            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        return WifiList; 
    }
    
    /*
     * 
     * wifi 근처 위치 중 하나 상세정보 
     */
    /*
	 * 회원 상세 정보
	 * 
	 */
	public Wifi detail(String mgrNo, String wifiNm) {

		Wifi wifi = null;

		String url = "jdbc:mariadb://127.0.0.1:3306/mission";
		String dbUserId = "root";
		String dbPassWord = "zerobase";

		//1. 드라이버 로드
		//2. 커넥션 객체 생성
		//3. 스테이트먼트 객체 생성
		//4. 쿼리 실행
		//5. 결과 수행
		//6. 객체 연결 해제(close)

		Connection connection = null; // 아래에서 finally 로 닫아줘야하기 때문에 미리 변수 선언.
		PreparedStatement preparedStatement = null;
	    // Statement statement = null;
		ResultSet rs = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver"); // 드라이버 로드
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassWord); // 커넥션 객체 생성

			String sql = "select * from \r\n"
					+ "TB_WIFI_LOCATION_INFO where X_SWIFI_MGR_NO = ? and X_SWIFI_MAIN_NM = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, mgrNo); // parameter는 1부터 중가
			preparedStatement.setString(2, wifiNm);

			rs = preparedStatement.executeQuery(); // 스테이트먼트 객체 생성

			if (rs.next()) { // 쿼리 실행문
				wifi = new Wifi();

				wifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
				wifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
				wifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
				wifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
				wifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
				wifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
				
				wifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
				wifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
				wifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
				wifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
				wifi.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
				wifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
				wifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
				wifi.setLAT(rs.getDouble("LAT"));
				wifi.setLNT(rs.getDouble("LNT"));
				wifi.setWORK_DTTM(rs.getString("WORK_DTTM"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally { // rs, statement, connection 을 무조건 닫아줘야하기 때문에 finally 로 마무리 해줘야함.
			try {
				if (rs != null && !rs.isClosed()) { // 객체 연결 해제
//close와 isClosed는 예외를 발생시키기 때문에
//try로 감싸 줘야함.
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) { // 객체 연결 해제
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (connection != null && !connection.isClosed()) { // 객체 연결 해제
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return wifi;
	} // 회원정보 조회 //회원정보 조회
    
    
    
    
    
}
