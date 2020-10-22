<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${logStatus=='Y' }">
	<script>
		alert("${logStatus}로그인성공");
		location.href="/webMVC/index.do";
	</script>
</c:if>
<c:if test="${logStatus==null || logStatus!='Y'}">
	<script>
		alert("${logStatus}로그인에 실패하였습니다.");
		history.back();
	</script>
</c:if>