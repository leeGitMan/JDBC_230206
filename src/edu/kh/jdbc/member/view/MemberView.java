package edu.kh.jdbc.member.view;

import java.util.Scanner;

import edu.kh.jdbc.member.vo.Member;
import edu.kh.jdbc.model.service.MemberService;

public class MemberView {
	
	private Scanner sc = new Scanner(System.in);
	
	// 로그인 회원 정보 저장용 변수
	private Member loginMember = null;
	private MemberService memberService;
	
	

	public void memberMenu(Member LoginMember) {
		
		// 전달 받은 로그인 회원 정보를 필드에 저장
		this.loginMember = LoginMember;
		
		System.out.print(" *** 회원 기능 *** ");
		System.out.println();
		System.out.println();
		System.out.println();
		
	
		System.out.println("~~이렇게 메뉴판 만들다가 식당도 차리겠다 깔깔~~");
		System.out.println();
		System.out.println("1. 내 정보 조회");
		System.out.println("2. 회원 목록 조회");
		System.out.println("3. 내 정보 수정(이름, 성별)");
		System.out.println("4. 비밀변호 변경(현재 비번, 새 비번, 새 비번 확인)");
		System.out.println("5. 회원 탈퇴");
		
		int input = sc.nextInt();
		sc.nextLine();
		
		
		try {
			
			do {
				
				switch(input) {
				case 1 : selectMyInfo(loginMember); break;
				case 2 : //selectAll(); break;
				case 3 : //updateMember(); break;
				case 4 : //updatePw(); break;
				case 5 : //secession(); break;
				case 0 : System.out.println("프로그램 종료"); break;
				default : System.out.println("잘못된 메뉴번호를 입력하셨습니다11."); break;
				}
				
				
				input = 0;
				
			}while(input !=0);
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	
	
	public void selectMyInfo(Member loginMember) {
		
		
		
		System.out.println("[마이페이지]");
		System.out.println();
		
		try {
			while(true) {
				
				System.out.print("정보 조회 전 비밀번호 확인 : ");
				String input = sc.next();
				
				
				Member mem = memberService.selectMyInfo(loginMember);
				
				if(input.equals(mem.getMemberPw())) {
					System.out.println("비밀번호 일치 ");
					break;
					

				}else {
					System.out.println("비밀번호 불일치 ");
				}
			}
		}catch(Exception e) {
			System.out.println("비밀 번호 확인 중 예외발생");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
