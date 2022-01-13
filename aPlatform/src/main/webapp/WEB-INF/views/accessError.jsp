<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/includes/common.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Acces Denied</h1>
	<h2>
		<c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage()} }" />
	</h2>
	<h2>
		<c:out value="${msg} }" />
	</h2>
</body>
</html>