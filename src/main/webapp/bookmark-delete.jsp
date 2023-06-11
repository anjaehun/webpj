<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="db.Wifi"%>
<%@ page import="java.util.List"%>
<%@ page import="db.BookmarkService"%>
<%@ page import="db.Bookmark"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크 그룹</title>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

th {
	border: 1px solid #dddddd;
	text-align: center;
	padding: 8px;
}

td {
	border: 1px solid #dddddd;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}

th {
	background-color: #228B22;
	color: #dddddd;
}
</style>
</head>
<body>

	<script>
		function deleteAlert() {
			alert("북마크 정보를 삭제하였습니다");
		}
	</script>
	<%
	BookmarkService bookmarkService = new BookmarkService();
	%>
	<%
	// 수정 버튼 누르기 전에 처리하는 data 작업 
	String bookId = "";
	String order = "";
	String bookName = "";
	if (!(request.getParameter("bookId") == null) || !(request.getParameter("order") == null)
			|| !(request.getParameter("bookName") == null)) {
		bookId = request.getParameter("bookId");
		order = request.getParameter("order");
		bookName = request.getParameter("bookName");
		bookName = request.getParameter("bookName");

	}

	// 수정 버튼 누른 후 작업
	%>

	<h1>북마크 그룹</h1>
	<a href="list.jsp">홈</a> |
	<a href="history.jsp">위치 히스토리 목록</a> |
	<a href="download2.jsp" onclick="showAlert()">Open API 와이파이 정보 가져오기</a>
	|
	<a href="bookmark-mybookmark.jsp">북마크 보기</a> |
	<a href="bookmark-group.jsp">북마크 그룹 관리</a>

	<table>
		<colgroup>
			<col style="width: 40%;" />
			<col style="width: 60%;" />
		</colgroup>
		<tbody>
			<form action="./bookmark-delete-fin.jsp" method="GET">
				<tr>
					<th>북마크 이름</th>
					<td><%=bookName%></td>
				</tr>
				<tr>
					<th>순서</th>
					<td><%=order%></td>
				</tr>
				<input type="hidden" id="bookId" name="bookId" value="<%=bookId%>">
				<tr>

					<td colspan="17" align="center"><a href="bookmark-group.jsp">돌아가기</a>
						|
						<button type="submit" onclick="deleteAlert()">삭제</button></td>
				</tr>
			</form>
		</tbody>
	</table>

</body>
</html>
