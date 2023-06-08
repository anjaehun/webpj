package db;



import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DbTestMain {
    public static void main(String[] args) throws IOException{
//    	WifiService test = new WifiService();
//        //dbtest.dbSelect();
//    	//test.totalCnt();
//    	System.out.println(test.totalCnt());
//        //dbtest.dbUpdate();
//        //dbtest.dbDelete();
    	
//    	WifiService wifiService = new WifiService();
//    	Wifi wifi = wifiService.detail("DDM200026", "미나리경로당");
    	
    	//MyBookmarkService mybookmarkService = new MyBookmarkService();
    	
    	//mybookmarkService.myBookmarkInsert("되었구만1", "미나리경로당");
    	
    	//bookmarkService.bookmarkUpdateByOne("업데이트 테스트", "123", "34");
//      	bookmarkService.BookmarkDeleteById("35");
//    	System.out.println(wifi.getX_SWIFI_MGR_NO());
//    	System.out.println(wifi.getX_SWIFI_WRDOFC());
//    	System.out.println(wifi.getX_SWIFI_MAIN_NM());
//    	System.out.println(wifi.getX_SWIFI_ADRES1());
//    	System.out.println(wifi.getX_SWIFI_ADRES2());
//    	System.out.println(wifi.getX_SWIFI_INSTL_FLOOR());
//    	System.out.println(wifi.getX_SWIFI_INSTL_TY());
//    	System.out.println(wifi.getX_SWIFI_INSTL_MBY());
//    	System.out.println(wifi.getX_SWIFI_SVC_SE());
//    	System.out.println(wifi.getX_SWIFI_CMCWR());
//    	System.out.println(wifi.getX_SWIFI_CNSTC_YEAR());
//    	System.out.println(wifi.getX_SWIFI_INOUT_DOOR());
//    	System.out.println(wifi.getX_SWIFI_REMARS3());
//    	System.out.println(wifi.getLAT());
//    	System.out.println(wifi.getLNT());
//    	System.out.println(wifi.getWORK_DTTM());
    	
//    	BookmarkService bookmark = new BookmarkService();
//    	bookmark.bookmarkInsert("123", 1);
    	
//    	BookmarkService bookmarkService = new BookmarkService();
    	BookmarkService myBookmarkService = new BookmarkService();
    	
    	//myBookmarkService.myBookmarkInsert("DDM200026", "3", "미나리경로당", "내 카페 근처");
    	Bookmark bookmark = myBookmarkService.selectBoxAfterSelectByOne("내 카페 근처2");
        
    	System.out.println(bookmark.getBOOKMARK_ID());
//    	List<MyBookMark> bookmarkList = myBookmarkService.List(); 
//		
//    	for(MyBookMark mybBookmark : bookmarkList){ 
//    		System.out.println(mybBookmark.getMYBOOKMARK_ID());
//    		System.out.println(mybBookmark.getBOOKMARK_NAME());
//    		System.out.println(mybBookmark.getWIFI_NAME());
//    		System.out.println(mybBookmark.getREG_DT());
//    		
//	    }
	

    

//    	
//    	if(test == 0) {
//	    	for (History history1 : historyList) {
//	    		System.out.println(history1.getLNT());
//	    		System.out.println(history1.getLAT());
//	    		System.out.println(history1.getHISTORY_ID());
//	    		System.out.println(history1.getINQUIRY_DATE());
//	    		x++; 
//	    		System.out.println(x);
//	    	}
//    	}	
    	
    
    	
//    	HistoryService history = new HistoryService();
//    	history.historyRecord("1","2");
    	
//        Scanner scanner = new Scanner(System.in);
//
//        String memberType = "email";
//
//        System.out.println("탈퇴할 회원 아이디를 입력해 주세요");
//        System.out.println("아이디 입력 >>>");
//        String userId = scanner.next();
//        System.out.println("비밀번호 입력 >>>");
//        String password = scanner.next();
////        System.out.println("이름 입력 >>>");
////        String name = scanner.next();
//
//        Member member = new Member();
//        member.setUserId(userId);
//        member.setPassword(password);
//     //   member.setPassword(password);
//
//        dbtest.withdraw(member);

    }
}
