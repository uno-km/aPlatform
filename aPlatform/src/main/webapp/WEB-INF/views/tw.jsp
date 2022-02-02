<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>aPlatform 웹뷰 테스트</title>
</head>
<body>
	<h1>테스트화면입니다.</h1>
	<div id="testWebView">
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
					<div class="form-floating mb-3">
						<input type="name" class="form-control" id="inputUserName"
							onkeyup="checkName()">
						<label for="inputUserName">이름을 입력해주세요.</label>
					</div>
					<div class="form-floating mb-3">
						<input type="number" class="form-control" id="inputUserBirth"
							oninput="checkBirth(this, 6)">
						<label for="inputUserBirth">생년월일을 입력해주세요. (YYDDMM)</label>
					</div>
					<div class="form-floating mb-3">
						<input type="tel" class="form-control" id="floatingInput">
						<label for="floatingInput">전화번호를 입력해주세요. (선택)</label>
					</div>
					<input type="hidden" id="signUpModalNameCheck" value='false' />
					<input type="hidden" id="signUpModalBirthCheck" value='false' />
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script>
	function checkDuplicataionId() {
		const inputtedId = document.getElementById("inputtedId");
		$.ajax({
			type : 'GET',
			url : '/user/checkid?user_id=' + inputtedId.value,
			async : false,
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(data) {
				judgeDuplicataion(data);
			},
			error : function() {
				alert('통신실패!!');
			}
		});
	}
	function checkName() {
		const pattern1 = /[0-9]/; // 숫자
		const pattern2 = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
		const inputUserName = document.getElementById('inputUserName');
		const signUpModalNameCheck = document
				.getElementById('signUpModalNameCheck');
		if (pattern1.test(inputUserName.value)) {
			inputUserName.className = 'form-control is-invalid';
			signUpModalNameCheck.value = 'false';
		} else {
			if (pattern2.test(inputUserName.value)) {
				inputUserName.className = 'form-control is-invalid';
				signUpModalNameCheck.value = 'false';
			} else {
				if (inputUserName.value.length >= '2') {
					inputUserName.className = 'form-control is-valid';
					signUpModalNameCheck.value = 'true';
				} else {
					inputUserName.className = 'form-control';
					signUpModalNameCheck.value = 'false';
				}
			}
		}
	}
	function checkBirth(inputUserBirth, maxlength) {
		const signUpModalBirthCheck = document
				.getElementById('signUpModalBirthCheck');
		if (inputUserBirth.value.length == maxlength) {
			inputUserBirth.className = 'form-control is-valid';
		} else {
			inputUserBirth.className = 'form-control';
			signUpModalBirthCheck.value = 'false';
		} 
		if (inputUserBirth.value.length > maxlength) {
			inputUserBirth.value = inputUserBirth.value.substr(0, maxlength);

		}
	}
</script>
<%@ include file="/WEB-INF/includes/common.jsp"%>
<%@ include file="/WEB-INF/includes/header.jsp"%>
<%@ include file="/WEB-INF/views/modules/loading.jsp"%>
