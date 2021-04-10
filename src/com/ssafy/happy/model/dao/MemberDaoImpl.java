package com.ssafy.happy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.happy.model.MemberDetailDto;
import com.ssafy.happy.model.MemberDto;
import com.ssafy.util.DBUtil;

public class MemberDaoImpl implements MemberDao {

	private static MemberDao memberDao;
	
	private MemberDaoImpl() {};
	
	public static MemberDao getMemberDao() {
		if(memberDao==null)
			memberDao = new MemberDaoImpl();
		return memberDao;
	}
	
	@Override
	public boolean registerMember(MemberDto memberDto) {
		MemberDetailDto memberDetailDto = (MemberDetailDto) memberDto;

		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs;
		
		boolean result = false;
		
		try {
			conn = DBUtil.getConnect();
			String sql = "insert into member(userid, lastname, firstname, userpwd, email) \n";
			sql += "values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDetailDto.getUserId());
			pstmt.setString(2, memberDetailDto.getLastName());
			pstmt.setString(3, memberDetailDto.getFirstName());
			pstmt.setString(4, memberDetailDto.getUserPwd());
			pstmt.setString(5, memberDetailDto.getEmail());
			rs = pstmt.executeUpdate();
			
			if(rs==1) {
				sql = "insert into memberDetail(userid, postcode, address, detailaddress, extraaddress) \n";
				sql += "values(?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberDetailDto.getUserId());
				pstmt.setString(2, memberDetailDto.getPostcode());
				pstmt.setString(3, memberDetailDto.getAddress());
				pstmt.setString(4, memberDetailDto.getDetailAddress());
				pstmt.setString(5, memberDetailDto.getExtraAddress());
				rs = pstmt.executeUpdate();
				
				if(rs==1)
					result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
		
		return result;
	}

	@Override
	public MemberDto login(String userId, String userPwd) {
		MemberDetailDto memberDto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnect();
			String sql = "select * \n";
			sql += "from member m join memberdetail md\n";
			sql += "on m.userid = md.userid \n";
			sql += "where m.userid = ? and userpwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDto = new MemberDetailDto();
				memberDto.setUserId(rs.getString("userid"));
				memberDto.setLastName(rs.getString("lastname"));
				memberDto.setFirstName(rs.getString("firstname"));
				memberDto.setUserPwd(rs.getString("userpwd"));
				memberDto.setEmail(rs.getString("email"));
				memberDto.setPostcode(rs.getString("postcode"));
				memberDto.setAddress(rs.getString("address"));
				memberDto.setDetailAddress(rs.getString("detailaddress"));
				memberDto.setExtraAddress(rs.getString("extraaddress"));
				memberDto.setJoindate(rs.getString("joindate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
		return memberDto;
	}

	@Override
	public boolean modifyMember(MemberDto memberDto) {
		MemberDetailDto memberDetailDto = (MemberDetailDto) memberDto;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs;
		
		boolean result = false;
		
		try {
			conn = DBUtil.getConnect();
			String sql = "update member \n";
			sql += "set userpwd = ?, firstname = ?, lastname = ?, email =? \n";
			sql += "where userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDetailDto.getUserPwd());
			pstmt.setString(2, memberDetailDto.getFirstName());
			pstmt.setString(3, memberDetailDto.getLastName());
			pstmt.setString(4, memberDetailDto.getEmail());
			pstmt.setString(5, memberDetailDto.getUserId());
			rs = pstmt.executeUpdate();
			
			if(rs==1) {
				sql = "update memberdetail \n";
				sql += "set postcode = ?, address = ?, detailaddress = ?, extraaddress = ? \n";
				sql += "where userid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberDetailDto.getPostcode());
				pstmt.setString(2, memberDetailDto.getAddress());
				pstmt.setString(3, memberDetailDto.getDetailAddress());
				pstmt.setString(4, memberDetailDto.getExtraAddress());
				pstmt.setString(5, memberDetailDto.getUserId());
				rs = pstmt.executeUpdate();
				
				if(rs==1)
					result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
		
		return result;
	}

	@Override
	public boolean deleteMember(String userId, String userPwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean flag = true;
		
		try {
			conn = DBUtil.getConnect();
			
			String sql = "select userpwd \n";
			sql += "from member \n";
			sql += "where userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int cnt;
				
				if(userPwd.equals(rs.getString("userpwd"))){
					sql = "delete from member \n";
					sql += "where userid = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userId);
					cnt = pstmt.executeUpdate();
					
					if(cnt==1) {
						sql = "delete from memberdetail \n";
						sql += "where userid = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, userId);
						cnt = pstmt.executeUpdate();
						if(cnt!=1)
							flag=false;
					}else
						flag = false;
				}else
					flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}

		return flag;
	}

	@Override
	public String find(String userId, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String result = null;
		
		try {
			conn = DBUtil.getConnect();
			
			String sql = "select userpwd \n";
			sql += "from member \n";
			sql += "where userid = ? and email =? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				result = rs.getString("userpwd");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}

		return result;
	}
	
	

}
