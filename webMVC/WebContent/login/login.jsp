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
<style>
	body{
		margin:0px;
		background-color:#ddd;
	}
	a:link, a:visited, a:hover{
		text-decoration:none; 
		color:gray;
	}
	#mainDiv{
		width:700px;
		height:100vh;
		margin:0 auto;
		background-color:#fff;
		
	}
	.bar{border-bottom:2px solid #ddd;}
	#logo{
		height:92px;
		text-align:center;
	}
	#logo img{height:90px;}
	#logFrm{
		padding:50px 40px 60px;
		overflow:auto; /*이걸 이용해서 빠져나오는 현상 제거*/
	}
	#logFrm>*{} /*아래꺼랑 똑같은거다.*/
	#logFrm>div, #logFrm>img, #logState>div{
		float:left; 
		
		
	}
	#logFrm>div{
		width:300px;
		margin-right:70px;
	}
	#logFrm>img{
		border:1px solid #ddd;
		width:248px;
		height:248px;	
	}
	
	#frm>input:not([type=submit]){
			width:298px; 
			padding:0px;
			height:48px;
			border:1px solid #ddd;
			font-size:20px;
			font-weight:bold;
			margin-bottom:5px;
	}
	#frm>input[type=submit]{
		width:300px;
		background-color:rgb(77,139,235);
		border:1px solid #ddd;
		height:48px;
		font-size:20px;
		color:#fff;
		margin:10px 0px 10px;
	}
	
	/*로그인 상태유지*/
	#logState>div{
		width:50%; 
		color:gray;
		margin-bottom:10px;
	}
	#logState>div:last-child{text-align:right;}
	#logState span{
		font-weight:bold;
		color:rgb(77,139,235);
	}
	
	/*아이디 찾기, 비밀번호 찾기*/
	.search{
		text-align:center;
		margin-bottom:10px;
	}
</style>
<script>
	$(function(){
		$("#frm").submit(function(){
			if($("#userid").val()==""){
				alert("아이디를 입력하세요");
				return false;
			}
			if($("#userpwd").val()==""){
				alert("비밀번호를 입력하세요");
				return false;
			}
			return true;
		});
	});
</script>
</head>
<body>
<div id="mainDiv">
	<div id="logo" class="bar"><a href="/webMVC/index.do"><img src="/webMVC/login/logo.png"/></a></div>
	<div id="logFrm" class="bar"> <!-- class는 여러번 쓸 수 있다. id는 한번밖에 못쓴다. -->
		<div>

		<!-- 로그인 폼 -->
			<form method="post" action="/webMVC/loginOk.do" id="frm">
				<input type="text" name="userid" id="userid" maxlength="20" placeholder="아이디 입력"/>
				<input type="password" name="userpwd" id="userpwd" maxlength="20" placeholder="비밀번호 입력"/>
				<input type="submit" value="로그인"/>
			</form>
			
			<div id="logState" style="overflow:auto"><!-- 로그인 상태유지 --> <!-- style="background:green;로 영역확인 -->
				<div><input type="checkbox"/>로그인 상태 유지</div>
				<div>  <!-- style="background:red" 로 영역 확인-->
					<a href="#">IP보안</a>
					<span>ON</span>
				</div>
			</div>
			<div class="search">
				<a href="#">아이디 찾기</a> |
				<a href="#">비밀번호 찾기</a>
			</div>
		</div>
		<img src="/webMVC/login/k1.jpg" title="banner"/>
	</div>
	<div class="search">
		&copy; KaKao Corp. | <a href="#">고객센터</a>
	</div>
</div>
</body>
</html>