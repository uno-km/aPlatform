<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/includes/noheaderCommon.jsp"%>
<%@ include file="/WEB-INF/views/modules/loading.jsp"%>
<script type="text/javascript" src="/resources/js/service/fin/excelUploadPopup.js"></script>
<meta charset="UTF-8">
<html>
<body class='finPopup'>
	<title>엑셀파일을 업로드 해주세요.</title>
	<input type="file" id="excelFileUpload" style="visibility: hidden; display: none;">
	<div style="display: flex; width: inherit; height: inherit; flex-direction: column; flex-wrap: nowrap; align-items: center;">
		<div class="title non-drag">
			<span style="color: white; font-size: 25px;">종목최신화</span>
		</div>
		<div>
			<input type="text" disabled class="form-control" id='searchShareInput' style='border: 2px solid #0d6efd; width: 80%;'>
			<button class="unoBnt" type="button" id='uploadExcel'>검색</button>
		</div>
	</div>
</body>
</html>