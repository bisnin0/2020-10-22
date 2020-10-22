package com.bitcamp.home.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitcamp.home.CommandService;

public class CommandIdCheck implements CommandService {

	@Override
	public String executeCommand(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//폼에 입력되어있는 아이디를 DB에 조회한다.
		String userid = req.getParameter("userid");
		
		RegisterDAO dao = new RegisterDAO();
		
		int cnt = dao.idSearch(userid);
		
		//view로 필요한 데이터 보내기
		req.setAttribute("userid",userid);	//아이디체크로입력한 아이디(DB에서 0이나오면 중복되는 아이디가 없다는거라 이 아이디를 사용해야하니까 보내준다.)
		req.setAttribute("result",cnt); //DB에서 읽어낸 레코드 갯수
		
		return "/register/idSearch.jsp";
	}

}
