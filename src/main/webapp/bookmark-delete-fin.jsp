
<%@page import="db.BookmarkService"%>
<%@page import="db.MyBookmarkService"%>
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
  String bookId = request.getParameter("bookId");
  //out.println(bookId);
  // 북마크 
  BookmarkService bookmarkService = new BookmarkService();
  // 내 북마크 
  MyBookmarkService myBookmarkService = new MyBookmarkService();
  
  myBookmarkService.MyBookmarkDeleteByBookmarkId(bookId);
  bookmarkService.BookmarkDeleteById(bookId);
  
  response.sendRedirect("bookmark-group.jsp");
%>



</body>
</html>