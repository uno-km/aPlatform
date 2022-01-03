<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id='sideRemoteController'>
	<div class="position-sticky border rounded" style="top: 10rem;">
		<div class="p-4">
			<ol class="list-unstyled mb-0">
				<li><div class="d-grid gap-2">
						<c:if test="${!empty loginUser}">
							<button class="btn btn-primary" type="button"
								data-bs-toggle="modal" data-bs-target="#uploadeMedia">글올리기</button>
						</c:if>

						<button class="btn btn-primary" type="button">회원가입</button>
					</div></li>
			</ol>
		</div>
		<div class="p-4">
			<ol class="list-unstyled">
				<li><a
					href="https://github.com/greenteamtest/moongnyang/tree/master/moongnyang">GitHub</a></li>
			</ol>
		</div>
	</div>
</div>