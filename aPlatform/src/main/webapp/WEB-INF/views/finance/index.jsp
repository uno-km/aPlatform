<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/common.jsp"%>
<%@ include file="/WEB-INF/views/modules/loading.jsp"%>

<html>
<meta charset="UTF-8">
<title>내 손안의 금융 - aFinance</title>
<body class='finBody'>
	<div class='mainPage'>
		<%@ include file="/WEB-INF/views/finance/TopSection.jsp"%>
		<%@ include file="/WEB-INF/views/finance/MiddleSection.jsp"%>
		<%@ include file="/WEB-INF/views/finance/ContentsSection.jsp"%>
	</div>
</body>
</html>
<%@ include file="/WEB-INF/includes/footer.jsp"%>