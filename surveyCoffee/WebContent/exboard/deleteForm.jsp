<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 폼</title>
<script type="text/javascript">
function writesave(){
	if(document.updateForm.title.value == ""){
		alert("제목을 입력하세요.");
		document.updateForm.title.focus();
		return false;
	}
	if(document.updateForm.inputPass.value == ""){
		alert("비밀번호를 입력하세요.");
		document.updateForm.inputPass.focus();
		return false;
	}
	if(document.updateForm.content.value == ""){
		alert("내용을 입력하세요.");
		document.updateForm.content.focus();
		return false;
	}
}
</script>
</head>
<body style="text-align: center;">
<article>
<section>
	<form action="${pageContext.request.contextPath}/board/deletePro" method="post">
<input type="hidden" name="num" value="${num}">
<input type="hidden" name="ref" value="${ref}">
<input type="hidden" name="step" value="${step}">
<input type="hidden" name="depth" value="${depth}">
	<table border="1" style="margin: auto;">
		<tr>
		<td>제목</td>
		<td colspan="3" style="text-align: left; text-indent: 10px"><input type="text" name="title" value="${title}" size="25"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td colspan="3" style="text-align: left; text-indent: 10px"><input type="password" name="inputPass" size="25"></td>
	</tr>
	<tr>
		<td>작성자</td><td>${writer}</td>
		<td>조회수</td><td>${readcount}</td>
	</tr>
	<tr>
		<td colspan="4"><textarea rows="7" cols="40" name="content">${content}</textarea></td>
	</tr>
	<tr>
	<td colspan="4">
		<input type="submit" value="삭제">&nbsp;&nbsp;
		<input type="button" onClick="javascript:location.href='${pageContext.request.contextPath}/board/list'" value="목록">
	</td>
	</tr>
	</table>
	</form>
</section>
</article>
</body>
</html>