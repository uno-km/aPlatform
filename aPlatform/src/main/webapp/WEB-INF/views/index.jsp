<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/common.jsp"%>
<%@ include file="/WEB-INF/views/modules/loading.jsp"%>


<%@ include file="/WEB-INF/views/modules/overviewNavbar.jsp"%>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>내손안에 플랫폼 - aPlatForm</title>
</head>
<html>
<body style="background-color: var(- -myBackGround);">
	<section class="features-icons text-center">
		<div class="container position-relative">
			<div class="container ">
				<div class="row">
					<div class="col-xl-2"></div>
					<div class="col-xl-8">
						<a href="">
							<img src="/resources/assets/useable/logo_onlyWord_blue.png" style="max-width: 100%; height: auto;" id='logo_onlyWord'>
						</a>
					</div>
					<div class="col-xl-2"></div>
				</div>
			</div>
			<div class="container" id="servicesNavbar"></div>
			<div class="row justify-content-center">
				<div class="col-xl-6">
					<div class="text-center text-white">
						<div class="row">
							<div class="col">
								<input class="form-control form-control-lg" id="searchInput" type="email" placeholder="검색어를 입력하세요." data-sb-validations="required,email" />
							</div>
							<div class="col-auto">
								<button class="btn btn-primary btn-lg" id="searchButton" type="submit">검색</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="features-icons text-center">
		<div class="container ">
			<div class="row">
				<div class="col-xl-2"></div>
				<div class="col-xl-8">
					<div class="container">
						<div class="row">
							<div class="col-lg-4">
								<div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
									<div class="features-icons-icon d-flex">
										<i class="bi-window m-auto text-primary"></i>
									</div>
									<h3>Fully Responsive</h3>
									<p class="lead mb-0">This theme will look great on any device, no matter the size!</p>
								</div>
							</div>
							<div class="col-lg-4">
								<div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
									<div class="features-icons-icon d-flex">
										<i class="bi-layers m-auto text-primary"></i>
									</div>
									<h3>Bootstrap 5 Ready</h3>
									<p class="lead mb-0">Featuring the latest build of the new Bootstrap 5 framework!</p>
								</div>
							</div>
							<div class="col-lg-4">
								<div class="features-icons-item mx-auto mb-0 mb-lg-3">
									<div class="features-icons-icon d-flex">
										<i class="bi-terminal m-auto text-primary"></i>
									</div>
									<h3>Easy to Use</h3>
									<p class="lead mb-0">Ready to use with your own content, or customize the source files!</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
<%@ include file="/WEB-INF/includes/footer.jsp"%>
<%@ include file="/WEB-INF/views/modules/sideRemoteCnt.jsp"%>