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
					<div class="input-group mb-3">
						<div class="input-group mb-3">
							<input type="text" class="form-control"
								placeholder="가입하실 아이디를 입력해주세요."
								aria-describedby="button-addon2">
							<button class="btn btn btn-primary" type="button"
								id="button-addon2">중복검사</button>
						</div>
						<span class="input-group-text">@</span>
						<input type="text" class="form-control " placeholder="naver.com"
							id="showSelectedValue" value="naver.com" aria-label="Username"
							disabled='true' />
						<select class="form-select" aria-label="Default select example"
							onchange="selectEmailChange(this)">
							<option selected value="naver">네이버</option>
							<option value="google">구글</option>
							<option value="daum">다음</option>
							<option value="user_email_input">직접입력</option>
						</select>
					</div>
					<div class="d-grid gap-2 col-6 mx-auto">
						<button type="button" class="btn btn-primary" id="checkEmail"
							onclick="checkEmail()">이메일 인증</button>
					</div>
					<div id='checkEmailConfirm'></div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
<%@ include file="/WEB-INF/includes/common.jsp"%>
<%@ include file="/WEB-INF/includes/header.jsp"%>
<%@ include file="/WEB-INF/views/modules/loading.jsp"%>
