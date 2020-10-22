package com.bitcamp.home.register;

public interface RegisterInterface {
	//로그인
	public void loginCheck(RegisterVO vo);
	//회원가입
	public int registerInsert(RegisterVO vo);
	//회원정보 수정
	public void registerSelect(RegisterVO vo);
	//아이디 중복검사
	public int idSearch(String userid);
}
