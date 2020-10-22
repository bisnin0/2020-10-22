package com.bitcamp.home.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitcamp.home.CommandService;

public class CommandFormOk implements CommandService {

	@Override
	public String executeCommand(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); //post방식으로 넘겨받았으니 한글깨지니까 인코딩 해줘야한다.
		
		//폼(regForm)의 데이터를 request해서 담을(저장할) 객체
		RegisterVO vo = new RegisterVO();
		vo.setUserid(req.getParameter("userid")); //name=userid에 있는게 request된다.
		vo.setUserpwd(req.getParameter("userpwd"));
		vo.setUsername(req.getParameter("username"));
		vo.setGender(req.getParameter("gender"));
		
		//생년월일 - 읽어올 데이터가 각각의 데이터로 되어있으니 합치는 작업
		vo.setYear(req.getParameter("year"));
		vo.setMonth(req.getParameter("month"));
		vo.setDay(req.getParameter("day"));
		//vo.setBirth(vo.getYear()+"-"+vo.getMonth()+"-"+vo.getDay()); 나는 이런식으로 하려고했지만 굳이 이럴필요없이 어디서든 쓸 수있게
		//RegisterVO의 getBirth()메소드에 birth = year+"-"+month+"-"+day;를 입력하여 getBirth()를 호출하면 위에서 입력한 데이터가 자동으로 양식에 맞게 호출되게 만드는게 좋다. <--이렇게해야함
		
		//전화번호 - 이것도 마찬가지고 RegisterVO의 getTel() 메소드에서 합치는 작업을 해놨다. getTel()하면 아래서 입력한 전화번호가 연결되어 나온다.
		vo.setTel1(req.getParameter("tel1"));
		vo.setTel2(req.getParameter("tel2"));
		vo.setTel3(req.getParameter("tel3"));
		
		vo.setEmail(req.getParameter("email"));
		
		vo.setZipcode(req.getParameter("zipcode"));
		vo.setAddr(req.getParameter("addr"));
		vo.setAddrDetail(req.getParameter("addrDetail"));
		
		RegisterDAO dao = new RegisterDAO();
		int result = dao.registerInsert(vo);
		
//		if(result==0) { //회원 가입 실패
//			return "/register/regFormResult.jsp";
//		}else { //회원 가입 성공
//			return "/index.do";
//		} //회원가입 실패시 비회원이 작성한 내용을 유지하고 이전페이지로 가기 위해서 한건데 이렇게하면 회원가입이 성공했는지 실패했는지 두 결과를 다알려주지 않는다. 알려주려면 아래로
		
		req.setAttribute("result", result);
		return "/register/regFormResult.jsp";
	}

}
