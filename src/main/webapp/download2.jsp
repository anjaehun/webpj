
<%@page import="db.WifiService"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다운로드 완료!</title>
</head>
<body>
<%
  WifiService wifiService = new WifiService();
  wifiService.jsonImport();
  long a = wifiService.totalCnt();  
%>

<h2><%=a %>건을 처리하였습니다. </h2>
<a href="list.jsp">홈으로 돌아가기 </a> 


</body>
</html>