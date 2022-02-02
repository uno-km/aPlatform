<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Modal -->
<div class="modal fade modal-dialog modal-dialog-centered"
	id="signUpModal" tabindex="-1" data-bs-backdrop="static"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<input type="hidden" id="signUpModalCnt" value='1' />
		<input type="hidden" id="signUpModalCheck" value='false' />
		<input type="hidden" id="signUpModalEmailInputValidateTest" value='false' />
		<input type="hidden" id="signUpModalEmailUrlValidateTest" value='false' />
		<input type="hidden" id="signUpModalEmailRandomCheck" value='false' />
		<input type="hidden" id="signUpModalNameCheck" value='false' />
		<input type="hidden" id="signUpModalBirthCheck" value='false' />

		<input type="hidden" id="signUpUserId" value=""/>
		<input type="hidden" id="signUpUserPassword" value=""/>
		<input type="hidden" id="signUpUserEmail" value=""/>
		<input type="hidden" id="signUpUserName" value=""/>
		<input type="hidden" id="signUpUserBirth" value=""/>
		<input type="hidden" id="signUpUserPhone" value=""/>

		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">회원가입</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div id='signUpModalBody' >
				</div>
				<hr/>
				<div class="progress">
					<div
						class="progress-bar progress-bar-striped progress-bar-animated"
						role="progressbar" aria-valuenow="75" aria-valuemin="0"
						id="signUpModalProgress" aria-valuemax="100" style="width: 25%"></div>
				</div>
			</div>
			<div class="modal-footer">

				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal" onclick='signUpModalCloseBtn()'>닫기</button>
				<button type="button" class="btn btn-primary"
					id='signUpModalNextBnt' onclick="signUpModalNextBnt()">다음</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="/resources/js/user/modal/signUpModal.js"></script>