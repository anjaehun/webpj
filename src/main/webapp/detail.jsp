<%@page import="db.Wifi"%>
<%@page import="db.Bookmark"%>
<%@page import="java.util.*"%>
<%@page import="db.WifiService"%>
<%@page import="db.BookmarkService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<script>
		function showAlert() {
			alert("오래 걸리니 잠시만 기다려 주세요");
		}

		function insertAlert() {
			alert("한건의 마이 북마크 데이터가 들어갔습니다.");
		}
	</script>
	<%
	String memberType = request.getParameter("mgrNo");
	String userId = request.getParameter("name");
	String km = request.getParameter("km");
	String mgrNo = request.getParameter("mgrNo");

	WifiService wifiService = new WifiService();
	Wifi wifi = wifiService.detail(memberType, userId);

	BookmarkService bookmarkService = new BookmarkService();
	List<Bookmark> bookmarkList = bookmarkService.selectBoxList();
	//MemberService dbTest3 = new MemberService();
	//Member member = dbTest3.detail(memberType, userId);
	%>

	<h1>와이파이 정보 구하기</h1>
	<a href="list.jsp">홈 </a> |
	<a href="history.jsp">위치 히스토리 목록 </a> |
	<a href="download2.jsp" onclick="showAlert()"> Open API 와이파이 정보
		가져오기 | <a href="bookmark-mymark.jsp">북마크 보기 </a> | <a
		href="bookmark-group.jsp">북마크 그룹관리 </a>
	</a>

	<form method="get" action="mybookmark-insert-fin.jsp?">
		<label for="name"></label> <select name="name" id="name">
			<option value="none">북마크 그룹 이름 선택</option>
			<%
			String bookmarkId = "";

			for (Bookmark bookmark : bookmarkList) {
				String bookmarkName = bookmark.getBOOKMARK_NAME();
				Integer bookmarkIdInteger = Integer.valueOf(bookmark.getBOOKMARK_ID());

				String bookmarkIdString = bookmarkIdInteger.toString();
			%>
			<option value="<%=bookmarkName%>"><%=bookmarkName%></option>

			<%
			// bookmarkId 변수에 저장
			}
			%>

		</select> <input type="hidden" id="bookName" name="bookName"
			value="<%=userId%>"> <input type="hidden" id="mgrNo"
			name="mgrNo" value="<%=mgrNo%>"> <input type="hidden" id="km"
			name="km" value="<%=km%>">
		<button type="submit" onclick="insertAlert()">북마크 추가하기</button>
	</form>


	<table>
		<!-- 테이블 -->
		<colgroup>
			<!-- 테이블 하나 이상의 열에 스타일을 적용할 때 사용 -->
			<col style="width: 40%;" />
			<col style="width: 60%;" />
		</colgroup>
		<tbody>
			<!-- 테이블 몸통 -->
			<tr>
				<!-- table row(테이블 행) table row가 닫히면 다음 열로 이동 -->
				<th>거리(km)</th>

				<td><%=km%></td>
			</tr>
			<tr>
				<th>관리번호</th>
				<td><%=wifi.getX_SWIFI_MGR_NO()%></td>
			</tr>
			<tr>
				<th>자치구</th>
				<td><%=wifi.getX_SWIFI_WRDOFC()%></td>
			</tr>
			<tr>
				<th>와이파이명</th>
				<td><%=wifi.getX_SWIFI_MAIN_NM()%></td>
			</tr>
			<tr>
				<th>도로명주소</th>
				<td><%=wifi.getX_SWIFI_ADRES1()%></td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td><%=wifi.getX_SWIFI_ADRES2()%></td>
			</tr>
			<tr>
				<th>설치위치(층)</th>
				<td><%=wifi.getX_SWIFI_INSTL_FLOOR()%></td>
			</tr>

			<tr>
				<th>설치 유형</th>
				<td><%=wifi.getX_SWIFI_INSTL_TY()%></td>
			</tr>

			<tr>
				<th>설치 기관</th>
				<td><%=wifi.getX_SWIFI_INSTL_MBY()%></td>
			</tr>

			<tr>
				<th>서비스 구분</th>
				<td><%=wifi.getX_SWIFI_SVC_SE()%></td>
			</tr>

			<tr>
				<th>망종류</th>
				<td><%=wifi.getX_SWIFI_CMCWR()%></td>
			</tr>

			<tr>
				<th>설치년도</th>
				<td><%=wifi.getX_SWIFI_CNSTC_YEAR()%></td>
			</tr>

			<tr>
				<th>실내외구분</th>
				<td><%=wifi.getX_SWIFI_INOUT_DOOR()%></td>
			</tr>

			<tr>
				<th>WIFI접속환경</th>
				<td><%=wifi.getX_SWIFI_REMARS3()%></td>
			</tr>

			<tr>
				<th>X좌표</th>
				<td><%=wifi.getLAT()%></td>
			</tr>

			<tr>
				<th>y좌표</th>
				<td><%=wifi.getLNT()%></td>
			</tr>

			<tr>
				<th>작업일자</th>
				<td><%=wifi.getWORK_DTTM()%></td>
			</tr>
		</tbody>
	</table>
	<div>
		<a href="list.jsp">목록으로 이동</a>
	</div>
</body>
</html>