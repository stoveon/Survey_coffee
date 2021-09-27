<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${result == 0}">
<script>
	alert("등록 실패");
	window.history.go(-1);
</script>
</c:if>
<c:if test="${result == 1}">
<meta http-equiv="refresh" content="0; url=${pageContext.request.contextPath}/board/listF">
</c:if>