<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device, initial-scale=1"/> 
<link rel="stylesheet" href="/webMVC/library/bootstrap.css" type="text/css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
<script src="/webMVC/library/bootstrap.js"></script>
<script>
	$(function(){
		//서버에서 비동기식으로 주소 목록 가져오기
		$("#zipFrm").submit(function(){
			if($("#doro").val()==""){//도로명을 입력하지 않은경우
				alert("도로명 입력 후 다시 검색 하세요.");
			}else{ //도로명이 있을때
				var url = "<%=request.getContextPath()%>/register/zipSearchAjax.do";
				//form의 필드 name과 값을 직렬화시키기
				// var params = "doro="+$("#doro").val(); //이렇게 해도 되지만 필드가 많아질수록 코드가 길어진다.
				var params = $("#zipFrm").serialize(); //doro=백범로22길 .. 이렇게 하면 필드가 100만개가 되어도 이걸로 끝난다.
											//serialize() 알아보기
				$.ajax({
					url:url,
					data:params,
					success:function(result){
						$("#zipList").html(result);
						console.log(result);
					},error:function(){ //실패했을때 error로 간다.
						console.log("검색 실패..")
					}
				
					
				});
			}
			return false;
			
		});
		
		//우편번호와 주소를 회원등록폼으로 셋팅하는 작업(검색된주소 폼에 넣는것)
		//$("#zipList>li").on('click', 'function(){  이게 먹히지 않는다. ajax로 다른곳에서 만든게 #zipList>li이기 때문이다.
		$(document).on('click', '#zipList>li',function(){ //선택자를 document로 하면 이 전체 페이지중에의 #zipList>li에서 클릭이벤트가 발생하면 으로 인식된다.
													//다른곳에서(ajax등) 생성한 부분을 이벤트 발생하려면 이렇게
			var zipcode = $(this).children(".zip").text(); //방금 이벤트가 생긴 li의 자식중에 zip클래스의 문자를 읽어와서 zipcode에 넣어라
			var address = $(this).children(".addr").text(); //
			
			opener.document.getElementById("zipcode").value = zipcode;//이 zipcode는 여기서 만든 zipcode고 괄호안의 zipcode는 창을 열어준곳의 zipcode다
			opener.document.getElementById("addr").value = address;
			
			window.close(); //팝업창 닫기 self.close();
		});
		
	});

</script>
</head>
<body>
<form method="post" id="zipFrm">
	도로명을 입력후 주소를 검색하세요.<br/>
	(예:백범로22길)<br/>
	<input type="text" name="doro" id="doro" placeholder="백범로22길"/>
	<input type="submit" value="주소검색하기"/>
	
</form>
<hr/>
<ul id="zipList"></ul>
</body>
</html>