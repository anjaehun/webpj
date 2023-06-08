<%@page import="db.History"%>
<%@page import="java.util.List"%>
<%@page import="db.HistoryService"%>
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
	HistoryService historyService = new HistoryService();
	List<History> historyList = historyService.historySelect();
    %> 
	
	<script>
     
     
     
		function showAlert() {
			alert("오래 걸리니 잠시만 기다려 주세요");
		}
		
		
	</script>

	<h1>와이파이 정보 구하기</h1>
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
				<!-- 테이블 주제 -->
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>

			</tr>
		</thead>
		<tbody>
			<!-- 테이블 몸통 -->
			
			<% for (History history1 : historyList) {%>
			<tr>

				<td><%=history1.getHISTORY_ID() %></td>
				<td><%=history1.getLNT() %></td>
				<td><%=history1.getLAT() %></td>
				<td><%=history1.getINQUIRY_DATE() %></td>
				<td>
				 <form method="get" action="history-delete-fin.jsp">
                        <input type="hidden" name="historyId" value="<%= history1.getHISTORY_ID() %>">
                        <button type="submit" >삭제</button>
                 </form>
				</td>
           </tr>
           <%} %>
			
		</tbody>
	</table>
	<div></div>
</body>
</html>