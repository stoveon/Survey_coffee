<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%System.out.println("확인"); %>
<c:if test="${result == -1}">
	<script>
		alert("비밀번호가 일치하지 않습니다.");
		window.history.go(-1);
	</script>
</c:if>
<c:if test="${result == 1}">
	<meta http-equiv="refresh" content="0; url=${pageContext.request.contextPath}/board/list">
</c:if>