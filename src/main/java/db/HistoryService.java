package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {
	
	
	    /*
	    * 검색 기록 insert 
	    * @param member 회원정보
	    *
	    * */
	    public void historyRecord(String LAT,String LNT){
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
	        //2. 커넥션 객체 생성
	        try {
	            connection = DriverManager.getConnection(url,dbUserId,dbPassWord);

	            statement = connection.createStatement();

	            // 4. 쿼리 실행
	            String sql = "INSERT INTO TB_WIFI_SEARCH_HISTORY (LAT, LNT, INQUIRY_DATE)\r\n"
	            		+ "VALUES (?, ?, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'));";

	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1,LAT);
	            preparedStatement.setString(2,LNT);
	      


	            //  rs = statement.executeQuery(sql);
	            int affected = preparedStatement.executeUpdate();

	             if(affected > 0) {
	                 System.out.println("저장 성공");
	             } else{
	                 System.out.println("저장 실패");
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
	    }
	    
	    public List<History> historySelect(){

	        List<History> historyList = new ArrayList<>();    	
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

	        String memberTypeValue = "email";
	        String userIdValue = "kwon@gmail.com";

	        //2. 커넥션 객체 생성
	        try {
	            connection = DriverManager.getConnection(url,dbUserId,dbPassWord);

	            statement = connection.createStatement();

	            // 4. 쿼리 실행
	            String sql = "select HISTORY_ID"
	            		+            ",LAT"
	            		+            ",LNT,"
	            		+            "INQUIRY_DATE "
	            		+ "           from TB_WIFI_SEARCH_HISTORY order by HISTORY_ID desc;";

	            preparedStatement = connection.prepareStatement(sql);
	          

	            //  rs = statement.executeQuery(sql);
	            rs = preparedStatement.executeQuery();


	            while(rs.next()){
	                int history_id = rs.getInt("HISTORY_ID");
	                Double userId = rs.getDouble("LAT");
	                Double password = rs.getDouble("LNT");
	                String name = rs.getString("INQUIRY_DATE");

	               History history = new History(); 
	               history.setHISTORY_ID(history_id);
	               history.setLAT(userId);
	               history.setLNT(password);
	               history.setINQUIRY_DATE(name);
	               
	               historyList.add(history); 
	               
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
	        
	        return historyList; 
	    }
	    
	    public void HistorydeleteById(String historyId){
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




	        //2. 커넥션 객체 생성
	        try {
	            connection = DriverManager.getConnection(url,dbUserId,dbPassWord);

	            statement = connection.createStatement();

	            // 4. 쿼리 실행
	            String sql = "delete from TB_WIFI_SEARCH_HISTORY where HISTORY_ID = ?;";

	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1,historyId);
	            
	            //  rs = statement.executeQuery(sql);
	            int affected = preparedStatement.executeUpdate();

	            if(affected > 0) {
	                System.out.println("삭제 성공");
	            } else{
	                System.out.println("삭제 실패");
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
	    }
	    
	    
}
