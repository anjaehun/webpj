-----------------------------------
프로젝트 설명 
----------------------------------- 
- 자바와 웹 그리고 데이터베이스를 통해서 프로젝트를 진행

- 프로젝트 명 : 내 위치 기반 공공 와이파이 정보를 제공하는 웹서비스 개발 

- 프로젝트 목적 : 공공 데이터 자원을 끌어와서 내가 서비스 하고자 하는 데이터로
마이그레이션하여 웹을 기반으로 하여 원하는 형태로 출력하고자 함. 또한, 사용한 히스토리에
대해서 데이터베이스에 저장하는 기능과 이를 출력하는 기능, 북마크를 통해서 사용자가 카테고리를 만들고 등록하는 기능을 구현하고자 함.

------------------------------------------
기술 스택 
------------------------------------------
- 언어 : java (maven project)
- 소스 저장 : github 
- SQL DB : mariadb
- server : Tomcat 8.5 
- tool : eclipse 

------------------------------------------
<ERD 사진>
------------------------------------------

![image](https://github.com/anjaehun/webpj/assets/77096665/fbb54d1d-4d74-4b84-9663-8a05dad681ee)

------------------------------------------
<Wifi java 처리>
------------------------------------------

- 활용 용도 23304개의 data 저장 및 보기 , 상세보기 코드 

- jsonSimple 라이브러리 사용 

- list.jsp : geolocation 사용하여 좌표 처리 

- 주요 파일 : WifiService.java 

- 와이파이 주요 메서드 (WifiService.java)

 urlParser(start,end) {} 
 - json 범위 내 데이터 보기 

totalCnt() {}  
- 오픈데이터 개수 확인 

 jsonImport() {} 
-  임포트 하는 코드 

 public List<Wifi> list(String MyLat, String MyLnt){} 
- 가까운 거리 20개 뽑는 코드 

 public Wifi detail(String mgrNo, String wifiNm) {}
- 근처 위치중 1개의 데이터 뽑기  

------------------------------------------
<히스토리(검색 결과 저장) 처리>
------------------------------------------

- 활용 용도 : 사용자가 검색한 기록을 저장한다. 
- 주요 파일 HistoryService .java 


<히스토리 검색 관련 처리 >

 public void historyRecord(String LAT,String LNT){}
 - 검색 기록 insert 
 
 public List<History> historySelect(){}
 - history 전체 보기 
 
  public void HistorydeleteById(String historyId){}
  
 히스토리 특정 pk 값 1건 삭제 

------------------------------------------
<북마크 카테고리 설정>
------------------------------------------
- 활용 용도 : 북마크 카테고리 설정 crud 
- 주요 파일 : BookmarkService.java 

public void bookmarkInsert(String bookmarkName , String bookmarkNo) {}
- 북마크 인서트 코드 

public List<Bookmark> list() {} 
- 북마크 카테고리 전체 리스트 뽑기 

public void bookmarkUpdateByOne(String bookNameUpdate ,String orderUpdate, String bookIdUpdate) {} 
- 북마크 1건 업데이트 하기 

public void BookmarkDeleteById(String bookId){} 
- 아이디 값 받아와서 삭제를 하는 메소드 

 public List<Bookmark> selectBoxList() {}
 -   20개 중 1개 조회했을 때 의 화면에서 
	 select box 처리 코드 
 
  public Bookmark selectBoxAfterSelectByOne(String bookmarkName) {}
  - select box 에서 한건 을 넘겼을 때 데이터 1건 출력
	
------------------------------------------
<사용자가 설정한 북마크 저장, 전체보기 , 삭제 >
------------------------------------------	
- 사용 용도 : 사용자가 설정한 북마크 저장 및 전체보기 , 삭제 
- 특이사항 : TB_MY_BOOKMARK 는 TB_WIFI_LOCATION_INFO , TB_BOOKMARK_LIST 의 PK값을 가져온다
  

public void myBookmarkInsert(String mgrNo , String bookmarkId, String bookName ,String name) {} 
  - 사용자가 설정한 북마크 인서트 코드 
  
   public List<MyBookMark> List() {
   -사용자가 설정한 북마크 관련 조회 메소드 
   - equal join 사용 
   
public void MyBookmarkDeleteById(String mybookId){} 
  - 아이디 값 가져와서 삭제 하는 메소드 
     
