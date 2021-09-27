<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
</head>
<body style="text-align: center;">
<article>
<section>
<table border="1" style="margin: auto;">
	<tr>
		<td>제목</td>
		<td colspan="3">${title}</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td colspan="3"><fmt:formatDate value="${regdate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
	</tr>
	<tr>
		<td>작성자</td><td>${writer}</td>
		<td>조회수</td><td>${readcount}</td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="3">${content}</td>
	</tr>
	<tr>
		<td colspan="2"><a href="${pageContext.request.contextPath}/board/detailA?num=${num}">이전글</a></td>
		<td colspan="2"><a href="${pageContext.request.contextPath}/board/detailN?num=${num}">다음글</a></td>
	</tr>
</table>
<div style="margin: 10px;">
		<form method="post" action="${pageContext.request.contextPath}/board/write?num=${num}">
		<input type="hidden" name="num" value="${num}">
		<input type="hidden" name="ref" value="${ref}">
		<input type="hidden" name="step" value="${step}">
		<input type="hidden" name="depth" value="${depth}">
		<input type="submit" value="답글">
		
		<input type="button" onClick="javascript:location.href='${pageContext.request.contextPath}/board/update?num=${num}'" value="수정">

		<input type="button" onClick="javascript:location.href='${pageContext.request.contextPath}/board/delete?num=${num}'" value="삭제">

		<input type="button" onClick="javascript:location.href='${pageContext.request.contextPath}/board/list'" value="목록">
		</form>
</div>
</section>
</article>
</body>
</html>