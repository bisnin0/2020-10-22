package com.bitcamp.home.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitcamp.home.CommandService;

public class CommandLoginFormOk implements CommandService {

	@Override
	public String executeCommand(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 로그인  - 클라이언트가 입력해논 아이디 비밀번도는 req에 들어있다. login.jsp 폼에서 post형식으로 보냈음
		RegisterVO vo = new RegisterVO();
		vo.setUserid(req.getParameter("userid"));
		vo.setUserpwd(req.getParameter("userpwd"));
		
		RegisterDAO dao = new RegisterDAO();
		dao.loginCheck(vo);
		// ------엄청중요------ vo를 선언하고 vo를 dao로 보내고 dao에서 vo를 받아서 vo에 데이터를 넣으면 여기서 선언된 vo로 모두 이용 했기때문에 같은 주소를 가진다. 
		// 리턴 안해도 여기서 vo.get~을 이용해서 dao에서 vo로 입력한 데이터를 불러올수있다.
		
		//로그인 성공시 세션에 정보 기록 userid, username, logStatus.. request객체가 여기 있으니까 여기서 한다. dao는 없어서 추가작업 필요하기때문에
		if(vo.getLogStatus().equals("Y")) { //로그인 성공
			HttpSession session = req.getSession();
			session.setAttribute("logId", vo.getUserid());
			session.setAttribute("logName", vo.getUsername());
			session.setAttribute("logStatus", vo.getLogStatus());
			
		}
		
		
		return "/login/loginResult.jsp";
	}

}
