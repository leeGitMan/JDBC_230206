package edu.kh.jdbc.member.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.main.view.MainView;
import edu.kh.jdbc.member.vo.Member;
import edu.kh.jdbc.model.service.MemberService;

public class MemberView {
	
	private Scanner sc = new Scanner(System.in);
	
	// 로그인 회원 정보 저장용 변수
	private Member loginMember = null;
	private MemberService memberService = new MemberService();
	private List<Member>memList = new ArrayList<>();
	
	
	
	
	
	
	
	
	

	public Member memberMenu(Member LoginMember) {
		
		// 전달 받은 로그인 회원 정보를 필드에 저장
		this.loginMember = LoginMember;
		
		int input = 0;
		
		try {
			do {
				
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
				System.out.println("0. 프로그램 종료");
				
				input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
				case 1 : selectMyInfo(loginMember); break;
				case 2 : selectAll(); break;
				case 3 : updateMember(loginMember); break;
				case 4 : updatePw(loginMember); break;
				case 5 : LoginMember = secession(loginMember); 
						if(LoginMember.getSecessionFlag().equals("Y")) {
							input = 0;
						} break;
				case 0 : LoginMember.setSecessionFlag("N");
							System.out.println("프로그램 종료"); break;
				default : System.out.println("잘못된 메뉴번호를 입력하셨습니다."); break;
				
				}
					
			}while(input !=0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return LoginMember;
	}
	
	/** 출력 메서드
	 * @param mem
	 */
	public void printOne(Member mem) {
		
		
		
			System.out.println("멤버번호 |   멤버ID  | 멤버이름 |   성별   |   등록날짜 " );
			System.out.println("------------------------------------------------------------------------------------------------");
			
			System.out.printf(" %2d  | %10s | %5s | %10s |  %s\n",
					mem.getMemberNo(), mem.getMemberId(), mem.getMemberName(), mem.getMemberGender(), 
					mem.getEnrollDate());
		
	}
	
	public void printSelectAll(List<Member> memList) {
		
		
		
		for(Member mem : memList) {
			
			System.out.println();
			System.out.println("멤버번호 |   멤버ID  | 멤버이름 |   성별   |   등록날짜 " );
			System.out.println("------------------------------------------------------------------------------------------------");
			
			
			System.out.printf(" %2d  | %10s | %5s | %10s |  %s\n",
					mem.getMemberNo(), mem.getMemberId(), mem.getMemberName(), mem.getMemberGender(), 
					mem.getEnrollDate());
		}
}
	
	
	
	
	
	
	
	/** 마이페이지 메서드
	 * @param loginMember
	 */
	public void selectMyInfo(Member loginMember) {
		
		
		
		System.out.println("[마이페이지]");
		System.out.println();
		
		try {
			
				
			Member mem = memberService.selectMyInfo(loginMember);
				
				
			printOne(mem);
				
				
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/** 회원 목록 조회
	 * 
	 */
	public void selectAll() {
		
		System.out.println("[회원 목록 조회]");
		System.out.println();
		
		try {
			
			memList = memberService.selectAll();
			printSelectAll(memList);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** 회원 정보 수정 메서드
	 * @param loginMember
	 */
	public void updateMember(Member loginMember) {
		
		int result = 0;
		System.out.println("[회원 정보 수정 페이지]");
		System.out.println();
		
		try {
			
			System.out.print("수정하고자 하는 회원 번호 입력 : ");
			int memberNo = sc.nextInt();
			
			System.out.print("이름 수정 : ");
			String memberName = sc.next();
			
			System.out.print("성별 수정 : ");
			String memberGender = sc.next();
			
			
			result = memberService.updateMember(memberName, memberGender, memberNo);
			
			if(result > 0) {
				System.out.println("수정 완료");
			}else {
				System.out.println("수정 실패");
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 비밀번호 변경 메서드
	 * @param loginMember
	 */
	public void updatePw(Member loginMember) {
		
		int result = 0;
		boolean flag = true;
		
		
		System.out.println();
		
		System.out.println("[비밀번호 변경 페이지]");
		System.out.println();
		
		try {
//
//			do {
				
//				if(pw0.equals(loginMember.getMemberPw())) {
//					System.out.println("비밀번호가 맞습니다.");
//					
//					flag = false;
//				}else {
//					System.out.println("비밀번호가 틀립니다.");
//					flag = true;
//				}
				
//			}while(flag);
			System.out.print("현재 비밀 번호 입력 : ");
			String pw0 = sc.next();
			
			
			System.out.print("수정할 비밀번호 입력 : ");
			String pw1 = sc.next();
			
			System.out.println();
			
			System.out.print("수정할 비밀번호 재입력 : ");
			String pw2 = sc.next();
			
			if(pw1.equals(pw2)) {
				System.out.println("비밀번호 수정 성공");
				result = memberService.updatePw(pw0, pw1, loginMember.getMemberNo());
			}else {
				System.out.println("비밀번호 수정 실패");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	public Member secession(Member loginMember) {
		int result = 0;
		
		try {
			System.out.println("[회원 탈퇴 페이지]");
			
			System.out.println();
			System.out.println();
			
			System.out.print("정말 회원 탈퇴를 진행하시겠습니까?(Y/N)");
			char answer = sc.next().toUpperCase().charAt(0);
			
			if(answer == 'Y') {
				System.out.print("회원 번호를 입력해주세요. : ");
				int no = sc.nextInt();
				if(loginMember.getMemberNo() == no) {
					result = memberService.secession(no,loginMember);
					
					if(result > 0) {
						System.out.println("회원 탈퇴 성공");
						loginMember.setSecessionFlag("Y"); // = "Y";
					}
				}else {
					System.out.println("회원 번호를 잘못 입력하셨습니다.");
				}
				
				
			}else {
				
				System.out.println();
				System.out.print("회원 탈퇴 종료\n");
				System.out.println();
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return loginMember;
	}
	
}