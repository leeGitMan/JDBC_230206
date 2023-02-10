package edu.kh.jdbc.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;


import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.member.vo.Member;
import edu.kh.jdbc.model.dao.MemberDAO;


public class MemberService {
	
	
	private MemberDAO dao = new MemberDAO();
	
	
	

	public  Member selectMyInfo(Member loginMember) throws Exception {
		
		Connection conn = getConnection();
		
		Member member = dao.selectMyInfo(conn, loginMember);
		
		
		
		
		close(conn);
		
		
		return member;
	}




	public List<Member> selectAll() throws Exception{
		
		
		Connection conn = getConnection();
		
		List<Member> memList = dao.selectAll(conn);
		
		close(conn);
		
		
		return memList;
	}




	public  int updateMember(String memberName, String memberGender, int memberNo) throws Exception {
		
		int result = 0;
		
		
		
		Connection conn = getConnection();
		
		
		
		result = dao.updateMember(conn, memberName,memberGender, memberNo);
		
		
		
		
		
		
		if(result > 0 ) 		commit(conn);
		else					rollback(conn);
		
		close(conn);
		
		return result;
	}




	public int updatePw( String pw0, String pw1, int no) throws Exception{
		
		
		int result = 0;
		
		Connection conn = getConnection();
		
		
		
		
		result = dao.updatePw(conn, no, pw0, pw1);
		
		
		
		
		if(result > 0 )		commit(conn);
		else				rollback(conn);
		
		
		
		close(conn);
		
		return result;
	}




	public int secession(int no, Member loginMember)throws Exception {
		
		int result = 0;
		Connection conn = getConnection();
		
		
		result = dao.secession(conn, no, loginMember);
		
		
		if(result > 0) 		commit(conn);
		else				rollback(conn);
		
		close(conn);
		return result;
	}
}
