<%@page import="db.History"%>
<%@page import="java.util.List"%>
<%@page import="db.MyBookmarkService"%>
<%@page import="db.MyBookMark"%>
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

th{
	border: 1px solid #dddddd;
	text-align: center;
	padding: 8px;
}

td{
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
	<%
	MyBookmarkService myBookmarkService = new MyBookmarkService();
	List<MyBookMark> bookmarkList = myBookmarkService.List(); 
	%>
	


	<h1>북마크 목록</h1>
	<a href="list.jsp">홈 </a> |
	<a href="history.jsp">위치 히스토리 목록 </a> |
	<a href="download2.jsp" onclick="showAlert()"> Open API 와이파이 정보 가져오기 |
	<a href="bookmark-mymark.jsp">북마크 보기 </a> |
	<a href="bookmark-group.jsp">북마크 그룹관리 </a> 
	</a>
	<br>
     
	<table>
		<!-- 테이블 -->
		<thead>
			<!-- 테이블 머리 -->
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>
				<th>와이파이명</th>
				<th>등록일자</th>
				<th>비고</th>

			</tr>
		</thead>
		<tbody>
			<!-- 테이블 몸통 -->
			
			
			
             <%for(MyBookMark mybBookmark : bookmarkList){  %>
			 <tr>
				<td><%=mybBookmark.getMYBOOKMARK_ID() %></td>
				<td><%=mybBookmark.getBOOKMARK_NAME() %></td>
				<td><%=mybBookmark.getWIFI_NAME() %></td>
				<td><%=mybBookmark.getREG_DT() %></td>
				<td align = "center">
				<a href="bookmark-myBookmark-delete.jsp?bookId=<%=mybBookmark.getMYBOOKMARK_ID()%>
				&bookname=<%=mybBookmark.getBOOKMARK_NAME()%>&wifiname=<%=mybBookmark.getWIFI_NAME()%>&regDt=<%=mybBookmark.getREG_DT()%>">삭제</a>
				</td>
			</tr>
			<%} %>	
           
        
			
		</tbody>
	</table>
	<div></div>
</body>
</html>