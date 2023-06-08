
<%@page import="db.BookmarkService"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크 카테고리 업데이트 완료</title>
</head>
<body>
<%
  String bookNameUpdate = request.getParameter("bookNameUpdate");
  String orderUpdate = request.getParameter("orderUpdate");
  String bookIdUpdate = request.getParameter("bookIdUpdate");
  
  BookmarkService bookmarkService = new BookmarkService();
  bookmarkService.bookmarkUpdateByOne(bookNameUpdate,orderUpdate, bookIdUpdate);
  response.sendRedirect("bookmark-group.jsp");
%>


</body>
</html>