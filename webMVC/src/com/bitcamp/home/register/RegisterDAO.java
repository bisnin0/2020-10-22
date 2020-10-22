package com.bitcamp.home.register;

import java.util.ArrayList;
import java.util.List;

import com.bitcamp.home.DBConnection;

public class RegisterDAO extends DBConnection implements RegisterInterface {


	//회원가입
	@Override
	public int registerInsert(RegisterVO vo) {
		int cnt=0;
		try {
			getConn();          //생년월일은 2020-04-30식으로 들어오는데, 이건 문자다. 데이터셋팅은 DATE로 했으니 날짜로 바꿔줘야한다. 
			String sql = "insert into register(no, userid, userpwd, username, gender, birth, tel, email, zipcode, addr, addrdetail, regdate)"
					+ " values(a_sq.nextval, ?,?,?,?,to_date(?,'YYYY-MM-DD'),?,?,?,?,?,sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getUserpwd());
			pstmt.setString(3, vo.getUserpwd());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getZipcode());
			pstmt.setString(9, vo.getAddr());
			pstmt.setString(10, vo.getAddrDetail());
			
			
			cnt = pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			System.out.println("회원가입 DAO 에러"+e.getMessage());
		}finally {
			getClose();
		}
		
		return cnt;
	}

	@Override
	public void registerSelect(RegisterVO vo) {

	}

	@Override
	public int idSearch(String userid) {
		int cnt = 0;
		try {
			getConn();
			String sql = "select count(userid) from register where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("아이디 중복검사 에러발생."+e.getMessage());
		}finally {
			getClose();
		}
		return cnt;
	}

	//우편번호 검색
	public List<ZipcodeVO> getZipcodeList(String doro) {
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		try {
			getConn();
			String sql = "select zipcode, sido, sigungu, um, doro, b_num1, b_num2, building, dong "
					+ " from zipcodetbl where doro like ?";
			//					 					'%백범로%'
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+doro+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ZipcodeVO vo = new ZipcodeVO();
				vo.setZipcode(rs.getString(1));
				vo.setSido(rs.getString(2));
				vo.setSigungu(rs.getString(3));
				vo.setUm(rs.getString(4));
				vo.setDoro(rs.getString(5));
				vo.setB_num1(rs.getInt(6));
				vo.setB_num2(rs.getInt(7));
				vo.setBuilding(rs.getString(8));
				vo.setDong(rs.getString(9));
				list.add(vo);
			}
		}catch(Exception e) {
			System.out.println("우편번호 검색 에러..(Ajax)..."+e.getMessage());
		}finally {
			getClose();
		}
		return list;
	}

	@Override
	public void loginCheck(RegisterVO vo) {
		try {
			getConn();
			String sql = "select username from register where userid=? and userpwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getUserpwd());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //했더니 선택된게 있으면
				vo.setUsername(rs.getString(1));
				vo.setLogStatus("Y");
			}
			
			
		}catch(Exception e) {
			System.out.println("로그인 에러 발생"+e.getMessage());
		}finally {
			getClose();
		}
	}
	
}
