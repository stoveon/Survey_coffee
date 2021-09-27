<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 게시판 상세페이지</title>
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
		<td colspan="3" style="text-align: lreft; text-indent: 10px;"><fmt:formatDate value="${regdate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
	</tr>
	<tr>
		<td>작성자</td><td>${writer}</td>
		<td>조회수</td><td>${readcount}</td>
	</tr>
		<tr>
		<td>첨부 파일</td>
		<td colspan="3"><a download href="${pageContext.request.contextPath}/board/download?num=${num}">${file}</a></td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="3">${content}</td>
	</tr>
	<tr>
		<td colspan="2"><a href="${pageContext.request.contextPath}/board/detailAF?num=${num}">이전글</a></td>
		<td colspan="2"><a href="${pageContext.request.contextPath}/board/detailNF?num=${num}">다음글</a></td>
	</tr>
</table>
<div style="margin: 10px;">
		<form method="post" action="${pageContext.request.contextPath}/board/writeF?num=${num}">
		<input type="hidden" name="num" value="${num}">
		<input type="hidden" name="ref" value="${ref}">
		<input type="hidden" name="step" value="${step}">
		<input type="hidden" name="depth" value="${depth}">
		<input type="submit" value="답글">
		
		<input type="button" onClick="javascript:location.href='${pageContext.request.contextPath}/board/updateF?num=${num}'" value="수정">

		<input type="button" onClick="javascript:location.href='${pageContext.request.contextPath}/board/deleteF?num=${num}'" value="삭제">

		<input type="button" onClick="javascript:location.href='${pageContext.request.contextPath}/board/listF'" value="목록">
		</form>
</div>
</section>
</article>
</body>
</html>