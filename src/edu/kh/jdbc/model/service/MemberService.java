package edu.kh.jdbc.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;

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
}
	
	
	
	
	
	
	
	
	
	


