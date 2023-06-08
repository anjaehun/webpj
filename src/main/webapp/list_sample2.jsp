<%@page import="db.Member"%>
<%@page import="java.util.List"%>
<%@page import="db.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	width: 100%;
}

th, td {
	border: solid 1px #000;
}
</style>
</head>
<body>
	<%
	MemberService dbTest3 = new MemberService();
	List<Member> memberList = dbTest3.list();
	%>
     <form action="yourServlet" method="post">
        <span> LAT: <input type="text" name="lat" /></span>
        <span> LNT: <input type="text" name="lnt" /></span>
        <input type="submit" value="버튼" />
    </form>
	<h1>회원 목록</h1>
	<table>
		<!-- 테이블 -->
		<thead>
			<!-- 테이블 머리 -->
			<tr>
				<th>회원구분</th>
				<!-- 테이블 주제 -->
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
			</tr>
		</thead>
		<tbody>
			<!-- 테이블 몸통 -->
			<tr>
				<%
				for (Member member : memberList) {
				%>
			
			<tr>
				<td><%=member.getMemberType()%></td>
				<!-- 데이터 목록 -->
				<td>
					<!-- <a>하이퍼링크 만드는 태그</a> --> <!-- 하이퍼링크 뒤에 무조건 href가 붙음 --> <a
					href="detail.jsp?memberType=<%=member.getMemberType()%>&userId=<%=member.getUserId()%> ">
						<%=member.getUserId()%>
				</a>
				</td>
				<td><%=member.getPassword()%></td>
				<td><%=member.getName()%></td>
			</tr>
			<%
			}
			%>
			</tr>
		</tbody>
	</table>
	<div>
		<a href="add.jsp">회원 추가</a>
	</div>
</body>
</html>