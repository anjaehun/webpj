package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyBookmarkService {
	/**
	 * 사용자가 설정한 
	 * 북마크 인서트 코드 
	 *
	 */
	public void myBookmarkInsert(String mgrNo , String bookmarkId, String bookName ,String name) {
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
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassWord);

			statement = connection.createStatement();

			// 4. 쿼리 실행
			String sql = "INSERT INTO TB_MY_BOOKMARK (X_SWIFI_MGR_NO, BOOKMARK_ID, WIFI_NAME, BOOKMARK_NAME, REG_DT)\r\n"
					+ "VALUES (?, ?, ?, ?, NOW() ); ";
            // 'DDM200026', 3, '미나리경로당', '내 카페 근처', NOW() 
			// String mgrNo , String bookmarkId, String bookName ,String name
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, mgrNo);
			preparedStatement.setString(2, bookmarkId);
			preparedStatement.setString(3, bookName);
			preparedStatement.setString(4, name);
			

			// rs = statement.executeQuery(sql);
			int affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println("저장 성공");
			} else {
				System.out.println("저장 실패");
			}

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
	
	/**
	  * 사용자가 설정한 북마크 관련 조회 메소드 
	  * @return
	  */
	 public List<MyBookMark> List() {

			List<MyBookMark> myBookmarkList = new ArrayList<>();
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
			try {
				connection = DriverManager.getConnection(url, dbUserId, dbPassWord);

				statement = connection.createStatement();

				// 4. 쿼리 실행
				String sql = "SELECT DISTINCT  m.MYBOOKMARK_ID,b.BOOKMARK_NAME , w.X_SWIFI_MAIN_NM , m.REG_DT \r\n"
						+ "FROM TB_MY_BOOKMARK AS m\r\n"
						+ "JOIN TB_WIFI_LOCATION_INFO AS w ON w.X_SWIFI_MGR_NO = m.X_SWIFI_MGR_NO\r\n"
						+ "JOIN TB_BOOKMARK_LIST AS b ON m.BOOKMARK_ID = b.BOOKMARK_ID\r\n"
						+ ";";

				preparedStatement = connection.prepareStatement(sql);
				
				// rs = statement.executeQuery(sql);
				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					int myBookMark = rs.getInt("MYBOOKMARK_ID");
					String wifiName = rs.getString("X_SWIFI_MAIN_NM");
					String bookmarkName = rs.getString("BOOKMARK_NAME");
					String regDt = rs.getString("REG_DT");
		
					MyBookMark myBookmark = new MyBookMark();
					
					myBookmark.setMYBOOKMARK_ID(myBookMark);
					myBookmark.setWIFI_NAME(wifiName);
					myBookmark.setBOOKMARK_NAME(bookmarkName);
					myBookmark.setREG_DT(regDt);
					

					myBookmarkList.add(myBookmark);

				}
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

			return myBookmarkList;
		}
	 
	    /**
		 * 아이디 값 받아와서 삭제를 하는 메소드 
		 */
		 public void MyBookmarkDeleteById(String mybookId){
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
		            String sql = "delete from TB_MY_BOOKMARK where MYBOOKMARK_ID = ?;";

		            preparedStatement = connection.prepareStatement(sql);
		            preparedStatement.setString(1,mybookId);
		            
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
		 
		    
		 /**
			 * 아이디 값 받아와서 삭제를 하는 메소드 
			 * @param bookId
			 */
			 public void MyBookmarkDeleteByBookmarkId(String bookId){
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
			            String sql = "delete from TB_MY_BOOKMARK where BOOKMARK_ID = ?;";

			            preparedStatement = connection.prepareStatement(sql);
			            preparedStatement.setString(1,bookId);
			            
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
