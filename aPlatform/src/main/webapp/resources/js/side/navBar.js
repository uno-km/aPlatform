;
function setRemoteCtrl(){
	if(document.getElementById('sideRemoteController')) {
		let struct_div = `
				<div class="sideRemote_line">
				<ol class="list-unstyled mb-0">
				<li>
				<div class="sideRemote_signinInput">
				<div class="mb-3">
				<input type="text" class="form-control" id='sideRemoteId' placeholder="아이디" onkeyup="enterkey('login')">
				</div>
				<div class="mb-3">
				<input type="password" class="form-control" id='sideRemotePW' name='sideRemotePW' placeholder="비빌번호." onkeyup="enterkey('login')">
				</div>
				<div class="form-check form-switch">
				<input class="form-check-input" type="checkbox" role="switch" id="autoLogin">
				<label class="form-check-label" for="autoLogin">자동 로그인</label>
				</div>
				<button type="button" class="btn btn-primary" id="loginButton" onclick="signin()">로그인</button>
				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#signUpModal" onclick='setInitSignUpModal()'>회원가입</button>
				</div>
				</li>
				<li></li>
				</ol>
				</div>`;
		const inputBody = document.getElementById('sideRemoteController');
		inputBody.innerHTML = struct_div;
	}else {
		console.log('해당 영역없음');
	}
}
function setLoginedRemoteCtrl(){
	if(document.getElementById('sideRemoteController')) {
	    let struct_div = `
	    <div class="sideRemote_line">
	        <ol class="list-unstyled mb-0">
	            <li>
	                <div class="d-grid gap-2">
	                    <div class="mb-3"></div>
	                    <div class="mb-3"></div>
	                    <div class="mb-3 form-check"></div>
	                    <button type="button" class="btn btn-primary" onclick="logout()">로그아웃</button>
	                </div>
	            </li>
	            <li></li>
	        </ol>
	    </div>`;
	    const inputBody = document.getElementById('sideRemoteController');
	    inputBody.innerHTML = struct_div;
	}else {
		console.log('해당 영역없음');
	}
}
function callNavBar() {
    if(localStorage.length===0){
        console.log("로그인기록없음");
    }else{
    	if(document.getElementById('servicesNavbar')) {
            const here_user_id = localStorage.getItem('user_id');
            const navbaroutVO = {"user_id":  here_user_id}
            $.ajax({
                type:'POST',
                url: '/dataload/navbar',
                data: JSON.stringify(navbaroutVO),
                dataType: 'JSON', 
                async : false,
                contentType: 'application/json; charset=utf-8',
                success:  function (data) {setNavbar(data);},
                error: function () {alert('통신실패!!');}
            });
    	}else {
            console.log("해당 영억없음");
    	}
    }
}

function setNavbar(data) {
	if(document.getElementById('sideRemoteController')) {
	    let navbarList = "";
	    const navbarOutVO = data.navbaroutVO;
	    if (navbarOutVO.length > 0) {
	    	navbarList = "";
	        navbarList = "<c:if test=`${not empty sessionScope.user_id}`>";
	        navbarList +=   "<ul class='nav justify-content-center'>";
	        for (var i = 0; i < navbarOutVO.length; i++) {
	            navbarList += "<li class='nav-item' id='"+navbarOutVO[i].tmplt_figure+"'";
	            navbarList += "onmouseover='overviewNavBarOpen(id)' onmouseout='overviewNavBarClose(id)'>";
	            navbarList += "		<a class='nav_template_link' aria-current='page' href='"+/service/+ navbarOutVO[i].tmplt_address+"'>"; 
	            navbarList += navbarOutVO[i].tmplt_id + "</a>";
	            navbarList += "</li>";
	        }
	        navbarList +=   "</ul>";
	        navbarList += "</c:if>";
	    }
	    const inputBody = document.getElementById('servicesNavbar');
	    inputBody.innerHTML = navbarList;
	}else {
		console.log('해당 영역없음');
	}
}

function overviewNavBarOpen(tmplt) {
	this.floadOverViewNavBar = document.getElementById('floadOverViewNavBar');
	floadOverViewNavBar.style.animation="fadeIn 1s";
	floadOverViewNavBar.style.display="flex";
	fillOverviewWindow(tmplt);
}
function overviewNavBarClose(tmplt) {
	this.floadOverViewNavBar = document.getElementById('floadOverViewNavBar');
	floadOverViewNavBar.style.display="none";
	clearOverviewWindow();
}

function fillOverviewWindow(tmplt) {
	
	let overViewWindow = "<h1>"+tmplt+"</h1>";
    const inputBody = document.getElementById('floadOverViewNavBar');
    inputBody.innerHTML = overViewWindow;
}
function clearOverviewWindow() {
	let overViewWindow = "";
    const inputBody = document.getElementById('floadOverViewNavBar');
    inputBody.innerHTML = overViewWindow;
}
