package com.bitcamp.home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/*.do") //어떤식으로 접속하든 .do면 다 된다는 설정 *.do
public class ControllerAction extends HttpServlet {
	Map<String, CommandService> map = new HashMap<String, CommandService>();
    public ControllerAction() {
        super();
    }
    
    // doGet(), doPost()가 실행되기 전에 호출되는 메소드
    //없으면 시행 안하지만 있으면 먼저실행
    public void init(ServletConfig config) throws ServletException{
    	// propertis파일 매핑주소와 객체를 Map에 바로 사용할 수 있게끔 저장하는 기능을 구현한다.(저장한다)
    	
    	// 1. properties파일 이름을 얻어온다.
    	String propertiesFile = config.getInitParameter("proFileName");
    	
    	Properties prop = new Properties(); // string, string
    	try {
    		FileInputStream fis = new FileInputStream(propertiesFile);
    		prop.load(fis);
    	}catch(FileNotFoundException fnfe) {
    		System.out.println("프로퍼티 파일이 존재하지 않습니다..." +fnfe.getMessage());
    	}catch(IOException ie) {
    		System.out.println("프로퍼티 로딩 에러 발생.." + ie.getMessage());
    	}
    	// 2. properties의 문자열을 객체로 생성하여 map에 추가하는 기능
    	try {
	    	// 키 목록 얻어오기
	    	Enumeration keyList = prop.propertyNames();
	    	while(keyList.hasMoreElements()) {
	    		String key=(String)keyList.nextElement();
	    		//key에 해당하는 commandclass명을 얻어온다.(String)
	    		String command = prop.getProperty(key); // "com.bitcamp.home.CommandIndex"
//	    		System.out.println("key="+key+", value="+command);
	    
	    		//문자열로 되어 있는 팩키지와 클래스명을 객체로 만들기
	    		Class commandClass= Class.forName(command); //Class
	    		CommandService service= (CommandService)commandClass.getDeclaredConstructors()[0].newInstance(); //배열의 첫번째거를 가져오겠다.. 하위클래스는 상위클래스로 대입시킬 수 있으니까
	    		//com.bitcamp.home.CommandIndex로 객체만든걸 이 안에서 구해서 상위클래스로 형변환해서 service로 담아준작업
	    		map.put(key, service);
	    		    		
	    		
	    	}
    	}catch(Exception e) {
    		System.out.println("프로퍼티를 Map으로 만들기에서 에러 발생.."+e.getMessage());
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//uri주소를 구한다. 
		String uriPath = request.getRequestURI(); //   /webMVC/index.do  ,  /webMVC/*.do  ,  /webMVC/board/list.do
		String contextPath = request.getContextPath(); //    /webMVC
//		System.out.println("uriPath="+uriPath);
//		System.out.println("context="+contextPath);   //  context=/webMVC .. 
		String commandKey = uriPath.substring(contextPath.length()); //webMVC를 substring으로. 7글자니까 7번째이후구하면됨
		CommandService service= map.get(commandKey);
		String viewFile = service.executeCommand(request, response);
		
		//view파일로 이동시키기
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewFile);
		dispatcher.forward(request, response);//페이지 이동하기
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); // /*.do가 들어올때 post로 오면 여기로 와서 doget을 통해 get으로간다. 그러니까 doGet은 뭐로오든 무조건 실행된다.
	}

}
