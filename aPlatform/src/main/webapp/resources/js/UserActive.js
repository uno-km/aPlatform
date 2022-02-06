;
window.addEventListener('load', function() {
    document.getElementById('signUpModal').style.display='none';
    callNavBar();
    if(!localStorage.length<1){
        setLoginedRemoteCtrl();
    }else{
        setRemoteCtrl();
    }
});

function enterkey(stts) {
    if (window.event.keyCode == 13) {
        if(stts=="login")signin();
    }
}

function setRemoteCtrl(){
    let struct_div = `
    <div class="position-sticky border rounded" style="top: 10rem;">
    <div class="p-4">
        <ol class="list-unstyled mb-0">
            <li>
                <div class="d-grid gap-2">
                    <div class="mb-3">
                        <input type="text" class="form-control" id='sideRemoteId' placeholder="아이디" onkeyup="enterkey('login')">
                    </div>
                    <div class="mb-3">
                        <input type="password" class="form-control" id='sideRemotePW' name='sideRemotePW' placeholder="비빌번호." onkeyup="enterkey('login')">
                    </div>
                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="exampleCheck1">
                        <label class="form-check-label" for="exampleCheck1">자동 로그인</label>
                    </div>
                    <button type="button" class="btn btn-primary" id="loginButton" onclick="signin()">로그인</button>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#signUpModal" onclick='setInitSignUpModal()'>회원가입</button>
                </div>
            </li>
            <li></li>
        </ol>
    </div>
    </div>`;
    const inputBody = document.getElementById('sideRemoteController');
    inputBody.innerHTML = struct_div;
}
function setLoginedRemoteCtrl(){
    let struct_div = `
    <div class="position-sticky border rounded" style="top: 10rem;">
    <div class="p-4">
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
    </div>
    </div>`;
    const inputBody = document.getElementById('sideRemoteController');
    inputBody.innerHTML = struct_div;
}
function setSession(data){
    localStorage.clear();
    localStorage.setItem('user_auth', data.user_auth);
    localStorage.setItem('user_birth', data.user_birth );
    localStorage.setItem('user_email',  data.user_email);
    localStorage.setItem('user_id',  data.user_id);
    localStorage.setItem('user_name', data.user_name);
    localStorage.setItem('user_password',  data.user_password);
    localStorage.setItem('user_phonenum',  data.user_phonenum);
}
function logout () {
    localStorage.clear();
    window.location.reload();
}
function signin () {
      const user_id = document.getElementById("sideRemoteId").value;
      const user_password = document.getElementById("sideRemotePW").value;
      const maindataLoadInVO = {
          "user_id": user_id,
          "user_password": user_password
      }
      $.ajax({
          type: 'POST', //post 방식으로 전송
          url: '/user/signin', //데이터를 주고받을 파일 주소
          data: JSON.stringify(maindataLoadInVO), //위의 변수에 담긴 데이터를 전송해준다.
          dataType: 'JSON', 
//          async : false,
          contentType: 'application/json; charset=utf-8',
          success: function (data) {
              setSession(data);
              callNavBar();
              setLoginedRemoteCtrl();
          },
          error: function () {
              alert('통신실패!!');
          }
      });
}
function callNavBar() {
    if(localStorage.length===0){
        console.log("로그인기록없음");
    }else{
        const here_user_id = localStorage.getItem('user_id');
        const navbaroutVO = {
        		"user_id":  here_user_id
        	}
        $.ajax({
            type:'POST',
            url: '/dataload/navbar',
            data: JSON.stringify(navbaroutVO),
            dataType: 'JSON', 
//            async : false,
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                setNavbar(data);
            },
            error: function () {
                alert('통신실패!!'); 
            }
        });
    }
}
function setNavbar(data) {
    let navbarList = "";
    const navbarOutVO = data.navbaroutVO;
    if (navbarOutVO.length > 0) {
        navbarList = "<c:if test=`${not empty sessionScope.user_id}`>";
        navbarList +=   "<ul class='nav justify-content-center'>";
        for (var i = 0; i < navbarOutVO.length; i++) {
            navbarList += "<li class='nav-item'>";
            navbarList += "		<a class='nav-link active' aria-current='page' href='"+/service/+ navbarOutVO[i].tmplt_address+"'>"; 
            navbarList += navbarOutVO[i].tmplt_id + "</a>";
            navbarList += "</li>";
        }
        navbarList +=   "</ul>";
        navbarList += "</c:if>";
        
    }
    const inputBody = document.getElementById('servicesNavbar');
    inputBody.innerHTML = navbarList;
}
