<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${result==0 }">
	<script>
	/*
		alert("회원가입실패 하였습니다.");
		history.back(); //회원가입 실패시 비회원이 작성한 내용을 유지하고 이전페이지로 가기 위해서 이 파일을 만든것.. 이렇게만 쓰면 성공했는지는 안나온다. 그래서 성공도 구분 되게끔 추가함
	*/
	
		alert("회원가입실패 하였습니다.");
		history.back();
	</script>
</c:if>
<c:if test="${result>0 }">
	<script>
		alert("회원가입을 성공하였습니다. 홈페이지로 이동합니다.");
		location.href="/webMVC/index.do";
	</script>

</c:if>