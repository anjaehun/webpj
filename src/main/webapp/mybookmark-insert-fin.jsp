<%@page import="db.MyBookmarkService"%>
<%@page import="db.BookmarkService"%>
<%@page import="db.Bookmark"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크 카테고리 업데이트 완료</title>
</head>
<body>
<%
	 String mgrNo = request.getParameter("mgrNo");
	 String bookName = request.getParameter("bookName");
	 String name = request.getParameter("name");
	 String km = request.getParameter("km");
	 
	 BookmarkService myBookmarkService = new BookmarkService();
	 Bookmark bookmark = myBookmarkService.selectBoxAfterSelectByOne(name);
	 String bookmarkId = String.valueOf(bookmark.getBOOKMARK_ID());
	 
	 out.println(mgrNo);
	 out.println(bookmarkId);
	 out.println(bookName);
	 out.println(name);
	 
	 MyBookmarkService mybookmarkService = new MyBookmarkService();
 	
     mybookmarkService.myBookmarkInsert(mgrNo, bookmarkId, bookName, name);
%>


<script>
  // JavaScript code to automatically go back to the previous page
  window.history.back();
</script>

</body>
</html>
