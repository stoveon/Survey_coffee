<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 게시판 목록</title>
</head>
<body style="text-align: center;">
<h2>파일 게시판</h2>
<h4>(전체 글 : ${totalCount})</h4>
<article>
<section>
<c:if test="${totalCount == 0}">
<table border="1" style="margin: auto;">
	<tr>
		<td>등록된 글이 없습니다.</td>
	</tr>
</table>
</c:if>
<c:if test="${totalCount > 0}">
<table id="listTable" border="1" style="margin: auto;">
	<tr>
		<th id="num">번호</th>
		<th id="title">제목</th>
		<th id="writer">글쓴이</th>
		<th id="writer">작성일</th>
		<th id="readcount">조회수</th>
	</tr>
	<c:forEach items="${articleList}" var="article">
	<tr>
	<td>
	<c:set var="number" value="${number - 1}"/>
	<c:out value="${number}"/>
	</td>
	<td><a href="${pageContext.request.contextPath}/board/detailF?num=${article.num}">${article.title}</a></td>
	<td>${article.writer}</td>
	<td><fmt:formatDate value="${article.regdate}" pattern="yyyy-MM-dd" /></td>
	<td>${article.readcount}</td>
	</tr>
	</c:forEach>
</table>

<div style="margin: 10px;">
	<c:if test="${pram.p == null || param.p == 1}">
		<button class= "btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</button>
	</c:if>
	<c:if test="${parm.p > 1 && param.p < endPage}">
		<button class= "btn-prev" onclick="javasctip.location.href='${pageContext.request.contextPath}/board/listF?p=${startPage - pageCount}'">이전</button>
	</c:if>
	

	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<a href="${pageContext.request.contextPath}/board/listF?p=${i}">[${i}]</a>
	</c:forEach>

	
	<c:if test="${startPage+5>= lastPage}">
		<button class= "btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</button>
	</c:if>
	<c:if test="${startPage+5<lastPage}">
		<button class= "btn-next" onclick="javasctip.location.href='${pageContext.request.contextPath}/board/listF?p=${startPage + pageCount}">다음</button>
	</c:if>
</div>	

</c:if>
<div>
	<input type="button" value="index로 이동" onclick="javascript:window.location='${pageContext.request.contextPath}/exboard/index.jsp'">
	<input type="button" value="글쓰기" onclick="javascript:window.location='${pageContext.request.contextPath}/board/writeF'">
</div>
</section>
</article>
</body>
</html>