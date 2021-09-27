<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
<jsp:forward page="${pageContext.request.contextPath}/board/updateF?num=${num}">
<jsp:param name="num" value="${num}"/>
<jsp:param name="writer" value="${writer}"/>
<jsp:param name="content" value="${content}"/>
<jsp:param name="readcount" value="${readcount}"/>
<jsp:param name="check" value="${check}"/>
</jsp:forward>