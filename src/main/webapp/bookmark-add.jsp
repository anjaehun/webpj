<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

function showUpdateByOne() {
	alert("1건의 데이터가 추가되었습니다");
}
</script>


<%
	BookmarkService bookmarkService = new BookmarkService();
%>
<%
	String bkName = request.getParameter("bk_name");
	String bkOrderNo = request.getParameter("bk_orderNo");
	if (!(bkName == null) || !(bkOrderNo == null)) {
		bookmarkService.bookmarkInsert(bkName, bkOrderNo);
	}
%>

<h1>북마크 그룹</h1>
<a href="list.jsp">홈</a> |
<a href="history.jsp">위치 히스토리 목록</a> |
<a href="download2.jsp" onclick="showAlert()">Open API 와이파이 정보 가져오기</a> |
<a href="list.jsp">북마크 보기</a> |
<a href="bookmark-group.jsp">북마크 그룹 관리</a>

<table>
	<colgroup>
		<col style="width: 40%;" />
		<col style="width: 60%;" />
	</colgroup>
	<tbody>
		<form action="./bookmark-add.jsp" method="GET">
			<tr>
				<th>북마크 이름</th>
				<td><input type="text" id="bk_name" name="bk_name"></td>
			</tr>
			<tr>
				<th>순서</th>
				<td><input type="text" id="bk_orderNo" name="bk_orderNo"></td>
			</tr>
			<tr>
				<td colspan="17" align="center">
				<button type="submit" onclick = "showUpdateByOne()">추가</button></td>
			</tr>
		</form>
	</tbody>
</table>

</body>
</html>
