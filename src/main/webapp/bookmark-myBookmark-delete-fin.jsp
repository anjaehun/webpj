
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
MyBookmarkService myBookmarkService = new MyBookmarkService();

myBookmarkService.MyBookmarkDeleteById(bookId);

response.sendRedirect("bookmark-mymark.jsp");
%>

<body>
  <h1>나의 북마크 삭제 완료</h1>
  <div class="message">
    <p>1건의 데이터가 삭제되었습니다.</p>
    <p><a class="link" href="bookmark-mymark.jsp">북마크 보기로 돌아가기</a></p>
  </div>



</body>
</html>