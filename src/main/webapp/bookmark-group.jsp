<%@page import="db.Bookmark"%>
<%@page import="db.History"%>
<%@page import="java.util.List"%>
<%@page import="db.BookmarkService"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 리스트</title>
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
	text-align: left;
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



	<%
	BookmarkService bookmarkService = new BookmarkService();
	
	
	%>
	
	 <%
		// insert 처리
		
		List<Bookmark> bookmarkList = bookmarkService.list(); 
		
		
	%>

	<script>
		function showAlert() {
			alert("오래 걸리니 잠시만 기다려 주세요");
		}

		function deleteAlert() {

			alert("삭제되었습니다");
			location.reload();
		}
	</script>

	<h1>북마크 그룹</h1>
	<a href="list.jsp">홈 </a> |
	<a href="history.jsp">위치 히스토리 목록 </a> |
	<a href="download2.jsp" onclick="showAlert()"> Open API 와이파이 정보
		가져오기 | <a href="bookmark-mymark.jsp">북마크 보기 </a> | <a href="bookmark-group.jsp">북마크
			그룹관리 </a> <br>
	<br>

		<form action="./bookmark-add.jsp" method="GET">
			<button type="submit">북마크 그룹 이름 추가</button>
		</form>


	</a>
	<br>

	<table>
		<!-- 테이블 -->
		<thead>
			<!-- 테이블 머리 -->
			<tr>
				<th>ID</th>
				<!-- 테이블 주제 -->
				<th>북마크 이름</th>
				<th>순서</th>
				<th>등록일자</th>
				<th>수정일자</th>
				<th>비고</th>

			</tr>
		</thead>
		<tbody>
			<!-- 테이블 몸통 -->

			<%
			for(Bookmark bookmark : bookmarkList){ 
			%>
			<tr>

				<td><%=bookmark.getBOOKMARK_ID()%></td>
				<td><%=bookmark.getBOOKMARK_NAME()%></td>
				<td><%=bookmark.getORDER_NO()%></td>
				<td><%=bookmark.getREG_DT()%></td>
				<%if(bookmark.getMOD_DT() == null){ %>
				<td></td>
				<%}else{ %>
				<td><%=bookmark.getMOD_DT()%></td>
				<%} %>
				<td>
					<form method="post" action="history.jsp">
						<input type="hidden" name="historyId"
							value="">
					<a href="bookmark-update.jsp?bookId=<%=bookmark.getBOOKMARK_ID()%>
					&order=<%=bookmark.getORDER_NO()%>&bookName=<%=bookmark.getBOOKMARK_NAME()%>"> 수정 </a>
					<a href="bookmark-delete.jsp?bookId=<%=bookmark.getBOOKMARK_ID()%>
					&order=<%=bookmark.getORDER_NO()%>&bookName=<%=bookmark.getBOOKMARK_NAME()%>&regDt=<%=bookmark.getREG_DT()%>">삭제</a>
						
					</form>
				</td>
			</tr>
			<%
			}
			%>

		</tbody>
	</table>
	<div></div>
</body>
</html>