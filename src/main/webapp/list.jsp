<%@page import="db.Wifi"%>
<%@page import="java.util.List"%>
<%@page import="db.WifiService"%>
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

	<script>
		// 페이지 로드 시 현재 위치를 가져옴
		function myLtnRtn() {
			window.navigator.geolocation.getCurrentPosition(function(position) {
				var lat = position.coords.latitude;
				var lnt = position.coords.longitude;
				console.log("LAT:" + lat);
				console.log("LNT:" + lnt);
				document.getElementById("LAT").value = lat;
				
				document.getElementById("LNT").value = lnt;
			}, function(error) { //error
				switch (error.code) {
				case error.PERMISSION_DENIED:
					str = "사용자 거부";
					break;
				case error.POSITION_UNAVAILABLE:
					str = "지리정보 없음";
					break;
				case error.TIMEOUT:
					str = "시간 초과";
					break;
				case error.UNKNOWN_ERROR:
					str = "알수없는 에러";
					break;
				}
				document.getElementById("notLat").value = str;
				document.getElementById("notLnt").value = str;
			});
		}

		function showAlert() {
			alert("오래 걸리니 잠시만 기다려 주세요");
		}
		
		function summit(){
			
		}
	</script>
    <%
		String LAT = request.getParameter("LAT");
	    if(LAT == null){
	    	LAT = "0.0"; 
	    }
	   
		String LNT = request.getParameter("LNT");
		if(LNT == null){
			 LNT = "0.0"; 
		}
		 
	 

	//out.println(test1);
	//out.println(test2);
	%>
    
	<h1>와이파이 정보 구하기</h1>
	<a href="list.jsp">홈 </a> |
	<a href="history.jsp">위치 히스토리 목록 </a> |
	<a href="download2.jsp" onclick="showAlert()"> Open API 와이파이 정보 가져오기 |
	<a href="bookmark-mymark.jsp">북마크 보기 </a> |
	<a href="bookmark-group.jsp">북마크 그룹관리 </a>  
	
	<br>
	<form action="./list.jsp" method="GET">
		LAT: <input type="text" id="LAT" name="LAT" value=<%=LAT %>> 
		LNT: <input type="text" id="LNT" name="LNT" value=<%=LNT %>>

		<button type="button" onclick="myLtnRtn()">내 위치 가져오기</button>
		<button type="submit" >근처 wifi 정보 찾기</button>
	</form>

	
   
	<table>
		<!-- 테이블 -->
		<thead>
			<!-- 테이블 머리 -->
			<tr>
				<th>거리(KM)</th>
				<!-- 테이블 주제 -->
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>wifi접속환경</th>
				<th>x좌표</th>
				<th>y좌표</th>
				<th>작업일자</th>
			</tr>
		</thead>
		<tbody>
			<!-- 테이블 몸통 -->
			<% 
			WifiService wifiTest = new WifiService();
			%>
			
			<%if(request.getParameter("LAT") == null || request.getParameter("LAT").isEmpty() 
				    || request.getParameter("LNT") == null || request.getParameter("LNT").isEmpty()
				    || LNT == "0.0"|| LAT == "0.0"){ %>
			<tr>
				<td colspan = "15" , align = center> 위치 정보를 입력한 후에 조회해 주세요. </td>
			</tr>

			<%}else{ 
				HistoryService history = new HistoryService();
		    	history.historyRecord(LAT,LNT);
				List<Wifi> wifiList = wifiTest.list(LAT,LNT);	
			%>
				  <%for(Wifi wifi : wifiList){ %>
			  <tr>	  
	             <td><%=wifi.getKM()%></td>
	             <td><%=wifi.getX_SWIFI_MGR_NO()%></td>
	             <td><%=wifi.getX_SWIFI_WRDOFC()%></td>
	             
	             <td>
	             <a href="detail.jsp?mgrNo=<%=wifi.getX_SWIFI_MGR_NO()%>&name=<%=wifi.getX_SWIFI_MAIN_NM()%>&km=<%=wifi.getKM()%> ">
	             <%=wifi.getX_SWIFI_MAIN_NM()%>
	             
	             </a></td>
	             
	             <td><%=wifi.getX_SWIFI_ADRES1()%></td>
	             <td><%=wifi.getX_SWIFI_ADRES2()%></td>
	             <td><%=wifi.getX_SWIFI_INSTL_FLOOR()%></td>
	             <td><%=wifi.getX_SWIFI_INSTL_TY()%></td>
	             <td><%=wifi.getX_SWIFI_INSTL_MBY()%></td>
	             <td><%=wifi.getX_SWIFI_SVC_SE()%></td>
	             <td><%=wifi.getX_SWIFI_CMCWR()%></td>
	             <td><%=wifi.getX_SWIFI_CNSTC_YEAR()%></td>
	             <td><%=wifi.getX_SWIFI_INOUT_DOOR()%></td>
	             <td><%=wifi.getX_SWIFI_REMARS3()%></td>
	             <td><%=wifi.getLAT()%></td>
	             <td><%=wifi.getLNT()%></td>
	             <td><%=wifi.getWORK_DTTM()%></td>
	          </tr>   
	             <%} %>
             <%} %>
		</tbody>
	</table>
	<div></div>
</body>
</html>