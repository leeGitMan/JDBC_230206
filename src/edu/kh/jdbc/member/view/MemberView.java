package edu.kh.jdbc.member.view;

import java.util.Scanner;

import edu.kh.jdbc.member.vo.Member;
import edu.kh.jdbc.model.service.MemberService;

public class MemberView {
	
	private Scanner sc = new Scanner(System.in);
	
	// 로그인 회원 정보 저장용 변수
	private Member loginMember = null;
	
	private MemberService service = new MemberService();
	
		

	public void memberMenu(Member LoginMember) {
		
		// 전달 받은 로그인 회원 정보를 필드에 저장
		this.loginMember = loginMember;
		
		System.out.print(" *** 회원 기능 *** ");
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		int input = -1;
		
		
		try {
			
			do {
				
				switch(input) {
				case 1 : service.selectMyInfo(); break;
				case 2 : selectAll(); break;
				case 3 : updateMember(); break;
				case 4 : updatePw(); break;
				case 5 : secession(); break;
				case 0 : System.out.println("프로그램 종료"); break;
				default : System.out.println("잘못된 메뉴번호를 입력하셨습니다."); break;
				}
				
				
				
				
			}while(input !=0);
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}

		
		
		
		
		
		
		
		
		
	}
	
}
