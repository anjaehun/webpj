package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookmarkService {
	/**
	 * 북마크 인서트 코드 
	 * @param bookmark
	 */
	public void bookmarkInsert(String bookmarkName , String bookmarkNo) {
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
			String sql = "INSERT INTO TB_BOOKMARK_LIST ( BOOKMARK_NAME, ORDER_NO, REG_DT)\r\n"
					+ "VALUES ( ?, ?, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s')); ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bookmarkName);
			preparedStatement.setString(2, bookmarkNo);
			

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

	
	public List<Bookmark> list() {

		List<Bookmark> bookmarkList = new ArrayList<>();
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
			String sql = "select * from TB_BOOKMARK_LIST order by order_NO asc;  ";

			preparedStatement = connection.prepareStatement(sql);
			
			// rs = statement.executeQuery(sql);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int bookmarkId = rs.getInt("BOOKMARK_ID");
				String bookmarkName = rs.getString("BOOKMARK_NAME");
				int orderNo = rs.getInt("ORDER_NO");
				String regDt = rs.getString("REG_DT");
				String modDt = rs.getString("MOD_DT");

				Bookmark bookmark = new Bookmark();
				bookmark.setBOOKMARK_ID(bookmarkId);
				bookmark.setBOOKMARK_NAME(bookmarkName);
				bookmark.setORDER_NO(orderNo);
				bookmark.setREG_DT(regDt);
				bookmark.setMOD_DT(modDt);

				bookmarkList.add(bookmark);

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

		return bookmarkList;
	}
	
	/**
	 * 북마크 1건 업데이트 하기 
	 */
	public void bookmarkUpdateByOne(String bookNameUpdate ,String orderUpdate, String bookIdUpdate) {
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
		String userIdValue = "zerobase123@naver.com";
		String passwordValue = "9999";

		// 2. 커넥션 객체 생성
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassWord);

			statement = connection.createStatement();

			// 4. 쿼리 실행
			String sql = "update TB_BOOKMARK_LIST \r\n"
					+ "set \r\n"
					+ "BOOKMARK_NAME = ?,"
					+ "ORDER_NO = ?,"
					+ "MOD_DT = DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s')\r\n"
					+ "where \r\n"
					+ "BOOKMARK_ID = ?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bookNameUpdate);
			preparedStatement.setString(2, orderUpdate);
			preparedStatement.setString(3, bookIdUpdate);

			// rs = statement.executeQuery(sql);
			int affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println("업데이트 성공");
			} else {
				System.out.println("업데이트 실패");
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
	 * 아이디 값 받아와서 삭제를 하는 메소드 
	 * @param bookId
	 */
	 public void BookmarkDeleteById(String bookId){
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
	            String sql = "delete from TB_BOOKMARK_LIST where BOOKMARK_ID = ?;";

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
	 
	 /**
	  * 북마크 관련 메소드 
	  * @return
	  */
	 public List<Bookmark> selectBoxList() {

			List<Bookmark> bookmarkList = new ArrayList<>();
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
				String sql = "select * from TB_BOOKMARK_LIST order by order_NO asc;   ";

				preparedStatement = connection.prepareStatement(sql);
				
				// rs = statement.executeQuery(sql);
				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					String bookmarkName = rs.getString("BOOKMARK_NAME");
					int bookmarkId = rs.getInt("BOOKMARK_ID");
		
					Bookmark bookmark = new Bookmark();
					
					bookmark.setBOOKMARK_NAME(bookmarkName);
					bookmark.setBOOKMARK_ID(bookmarkId);

					bookmarkList.add(bookmark);

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

			return bookmarkList;
		}
	 
	 /**
	  * 북마크 관련 메소드 
	  * @return
	  */
	 public Bookmark selectBoxAfterSelectByOne(String bookmarkName) {

			Bookmark bookmark = null;
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
				String sql = "select bookmark_ID from TB_BOOKMARK_LIST where bookmark_name = ?";

				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, bookmarkName);
				
				// rs = statement.executeQuery(sql);
				rs = preparedStatement.executeQuery();

				if (rs.next()) { // 쿼리 실행문
					bookmark = new Bookmark();

					bookmark.setBOOKMARK_ID(rs.getInt("BOOKMARK_ID"));
					
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

			return bookmark;
		}
}
