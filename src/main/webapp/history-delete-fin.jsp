
<%@page import="db.HistoryService"%>

<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>히스토리 1건의 데이터 삭제 완료</title>
</head>
<body>
	<%
	HistoryService historyService = new HistoryService();
	String historyId = request.getParameter("historyId");
	historyService.HistorydeleteById(historyId);
	response.sendRedirect("history.jsp");
	%>

	<script>
		window.history.back();
	</script>

</body>
</html>