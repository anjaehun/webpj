package db;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
// 샘플코드
public class MemberService {

	public List<Member> list() {

		List<Member> memberList = new ArrayList<>();
		String url = "jdbc:mariadb://127.0.0.1:3306/test";
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

		// 2. 커넥션 객체 생성
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassWord);

			statement = connection.createStatement();

			// 4. 쿼리 실행
			String sql = "select member_type, user_id, password, name\n" + "from member m \n"
					+ "where member_type = ? ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberTypeValue);

			// rs = statement.executeQuery(sql);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String memberType = rs.getString("member_type");
				String userId = rs.getString("user_id");
				String password = rs.getString("password");
				String name = rs.getString("name");

				Member member = new Member();
				member.setMemberType(memberType);
				member.setUserId(userId);
				member.setPassword(password);
				member.setName(name);

				memberList.add(member);

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

		return memberList;
	}

	/*
	 * 회원 상세 정보
	 * 
	 */
	public Member detail(String memberType, String userId) {

		Member member = null;

		String url = "jdbc:mariadb://127.0.0.1:3306/test";
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

			String sql = "select m.member_type, m.user_id, m.password, m.name" + " ,md.mobile_no" + " ,md.marketing_yn"
					+ " ,md.register_date" + " from member m"
					+ " left join member_detail md on md.member_type = m.member_type and m.user_id = md.user_id "
					+ " where m.member_type = ? and m.user_id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberType); // parameter는 1부터 중가
			preparedStatement.setString(2, userId);

			rs = preparedStatement.executeQuery(); // 스테이트먼트 객체 생성

			if (rs.next()) { // 쿼리 실행문
				member = new Member();

				member.setMemberType(rs.getString("member_type"));
				member.setUserId(rs.getString("user_id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setMobileNo(rs.getString("mobile_no"));
				member.setMarketingYn(rs.getBoolean("marketing_yn"));
				member.setRegisterDate(rs.getDate("register_date"));

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
		return member;
	} // 회원정보 조회 //회원정보 조회

	/*
	 * 회원 가입 함수
	 * 
	 * @param member 회원정보
	 *
	 */
	public void register(Member member) {
		String url = "jdbc:mariadb://127.0.0.1:3306/test";
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
			String sql = "insert into member(member_type, user_id,password,name)\n" + "values (?, ?, ?, ?); ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, member.getMemberType());
			preparedStatement.setString(2, member.getUserId());
			preparedStatement.setString(3, member.getPassword());
			preparedStatement.setString(4, member.getName());

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

	public void dbUpdate() {
		String url = "jdbc:mariadb://127.0.0.1:3306/test";
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
			String sql = "UPDATE MEMBER \n" + "SET   \n" + "   password = ?" + "WHERE member_type = ?"
					+ "   AND user_id = ? ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, passwordValue);
			preparedStatement.setString(2, memberTypeValue);
			preparedStatement.setString(3, userIdValue);

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
	 *
	 * 회원 탈퇴 함수
	 */
	public void withdraw(Member member) {
		String url = "jdbc:mariadb://127.0.0.1:3306/test";
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
			String sql = "delete from member \n" + "  where member_type  = ? and user_id = ?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, member.getMemberType());
			preparedStatement.setString(2, member.getUserId());

			// rs = statement.executeQuery(sql);
			int affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
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

}
