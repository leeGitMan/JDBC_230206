package edu.kh.jdbc.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import edu.kh.jdbc.member.vo.Member;

public class MemberDAO {
	
	
	private Statement stmt = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;
	
	private Properties prop = null;
	
	private List<Member> memList = new ArrayList<>();
	
	private Scanner sc = new Scanner(System.in);
	
	
	
	
	
	
	
	public MemberDAO() {
		try {
			
			prop = new Properties();
			
			prop.loadFromXML(new FileInputStream("member-query.xml"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	

	public Member selectMyInfo(Connection conn, Member loginMember) throws Exception{
		
		
		Member myInfo = null;
		
		try {
			String sql = prop.getProperty("myInfo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, loginMember.getMemberNo());
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				myInfo = new Member(rs.getInt("MEMBER_NO"),
									rs.getString("MEMBER_ID"),
									rs.getString("MEMBER_NM"), 
									rs.getString("MEMBER_GENDER"), 
									rs.getString("ENROLL_DATE"));
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return myInfo;
	}





	public List<Member> selectAll(Connection conn) throws Exception {
		
		 
		
		try {
			
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				memList.add(new Member(rs.getInt("MEMBER_NO"), rs.getString("MEMBER_ID"), rs.getString("MEMBER_NM"), rs.getString("MEMBER_GENDER"), 
						rs.getString("ENROLL_DATE")));	
				
				
			}
			
			
		}finally {
			close(rs);
			close(stmt);
		}
		return memList;
	}





	public int updateMember(Connection conn, String memberName, String memberGender, int memberNo) throws Exception {
	
		
		int result = 0;
		
		
		try {
			
			String sql = prop.getProperty("update");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberGender);
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}





	public int updatePw(Connection conn, int no, String pw0, String pw1) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updatePw");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pw1);
			pstmt.setString(2, pw0);
			pstmt.setInt(3, no);
			
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		return result;
	}
}
