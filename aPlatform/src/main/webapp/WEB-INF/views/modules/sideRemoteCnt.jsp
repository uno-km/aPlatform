<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/jstl.jsp"%>
<div id='sideRemoteController'>
	<div class="position-sticky border rounded" style="top: 10rem;">
		<div class="p-4">
			<ol class="list-unstyled mb-0">
				<li>
					<div class="d-grid gap-2">
						<c:if test="${!empty loginUser}">
							<button class="btn btn-primary" type="button"
								data-bs-toggle="modal" data-bs-target="#uploadeMedia">글올리기</button>
						</c:if>
						<div class="mb-3">
							<input type="text" class="form-control" id='sideRemoteId'
								placeholder="아이디">
						</div>
						<div class="mb-3">
							<input type="password" class="form-control" name='sideRemotePW'
								placeholder="비빌번호.">
						</div>
						<div class="mb-3 form-check">
							<input type="checkbox" class="form-check-input"
								id="exampleCheck1">
							<label class="form-check-label" for="exampleCheck1">로그인유지</label>
						</div>
						<button type="button" class="btn btn-primary" id="loginButton">로그인</button>
						<button type="button" class="btn btn-primary"
							data-bs-toggle="modal" data-bs-target="#exampleModal">
							회원가입</button>
					</div>
				</li>
				<li></li>
			</ol>
		</div>
	</div>
</div>
<script type="text/javascript" src="/resources/js/UserActive.js"></script>
<%@ include file="/WEB-INF/views/modules/signUp.jsp"%>
