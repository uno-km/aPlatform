<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<html>
<meta charset="UTF-8">
<title>aPlatform 웹뷰 테스트</title>
<body>
	<h1>테스트화면입니다.</h1>
	<div class="container">
		<h2>파일업로드</h2>
		<div class="filebox">
			<label for="input_file">업로드</label>
			<input type="file" id="input_file" class="upload-hidden" multiple="multiple">
			<input type="button" id='sendFile' value='서버로 전송'>
		</div>
	</div>
</body>
</html>
<script>
	document.getElementById('sendFile').addEventListener('click', function() {
		let file = document.getElementById('input_file').files
		let formData = new FormData();
		formData.append('file', file);
		$.ajax({
			url : '/service/finance/excelUpload',
			processData : false,
			contentType : false,
			data : formData,
			type : 'POST',
			dataType : 'json',
			success : function(res) {
				alert();
			}
		}); //$.ajax
	})
</script>