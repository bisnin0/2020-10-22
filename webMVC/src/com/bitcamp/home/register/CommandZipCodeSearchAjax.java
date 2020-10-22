package com.bitcamp.home.register;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitcamp.home.CommandService;

public class CommandZipCodeSearchAjax implements CommandService {

	@Override
	public String executeCommand(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String doro = req.getParameter("doro");
		System.out.println("doro="+doro);
		
		RegisterDAO dao = new RegisterDAO();
		List<ZipcodeVO> list= dao.getZipcodeList(doro);
		
		req.setAttribute("zipList", list); //이안에 우편번호 주소록이 다 들어가있는것
		
		return "/register/zipcodeViewAjax.jsp"; //이 페이지로 실행은 되는데 화면 전환은 안된다.
		
	}

}
