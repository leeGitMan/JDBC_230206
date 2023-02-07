package edu.kh.jdbc.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.jdbc.member.vo.Member;
import static edu.kh.jdbc.common.JDBCTemplate.*;

public class MemberDAO {
	
	
	private Statement stmt = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;
	
	private Properties prop = null;
	
	
	public MemberDAO() {
		try {
			
			prop = new Properties();
			
			prop.loadFromXML(new FileInputStream("member-query"));
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
}
