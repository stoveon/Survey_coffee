<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 등록 페이지</title>
<script type="text/javascript">
function writeSave(){
	if(document.writeForm.title.value == ""){
		alert("제목을 입력하세요.");
		document.writeForm.title.focus();
		return false;
	}
	if(document.writeForm.writer.value == ""){
		alert("작성자를 입력하세요.");
		document.writeForm.writer.focus();
		return false;
	}
	if(document.writeForm.pass.value == ""){
		alert("비밀번호를 입력하세요.");
		document.writeForm.pass.focus();
		return false;
	}
	if(document.writeForm.content.value == ""){
		alert("내용을 입력하세요.");
		document.writeForm.content.focus();
		return false;
	}
}
</script>
</head>
<body style="text-align: center;">
<section>
<form action="${pageContext.request.contextPath}/board/writeProF" method="post" onsubmit="return writeSave()" enctype="multipart/form-data">
<input type="hidden" name="num" value="${num}">
<input type="hidden" name="ref" value="${ref}">
<input type="hidden" name="step" value="${step}">
<input type="hidden" name="depth" value="${depth}">

<table border="1" style="margin: auto;">
	<tr>
		<td>제목</td>
		<c:if test="${num != 0}">
		<td colspan="3" style="text-align: left; text-indent: 10px;"><input type="text" name="title" value="[답글] "></td>
		</c:if>
		<c:if test="${num == 0}">
		<td colspan="3" style="text-align: left; text-indent: 10px;"><input type="text" name="title"></td>
		</c:if>
	</tr>
	<tr>
		<td>작성자</td>
		<td colspan="3" style="text-align: left; text-indent: 10px;"><input type="text" name="writer"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td colspan="3" style="text-align: left; text-indent: 10px;"><input type="password" name="pass" placeholder="15자 이내"></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td colspan="3" style="text-align: left; text-indent: 10px;"><input type="file" name="file"></td>
	</tr>
	<tr>
		<td colspan="4"><textarea name="content" cols="50" rows="7" placeholder="내용 입력"></textarea></td>
	</tr>
	<tr>
		<td colspan="4">
			<input type="submit" value="등록">
			<input type="button" onClick="javascript:location.href='${pageContext.request.contextPath}/board/listF'" value="목록">
		</td>
	</tr>
</table>
</form>
</section>
</body>
</html>