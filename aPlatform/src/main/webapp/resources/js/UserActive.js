;
document.write("<script src='/resources/js/side/navBar.js'></script>");
var floadOverViewNavBar ="";
var data ='';

window.addEventListener('load', function() {
    callNavBar();
    if(!(localStorage.user_auth==undefined||localStorage.user_auth==null)){
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
function signin() {
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
        async: false,
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
function setSession(data){
	if(!(localStorage.user_auth==undefined||localStorage.user_auth==null)) {
		removeUserSignedSession();
	}
	setUserSignedSession(data);
}
function logout () {
	removeUserSignedSession();
    window.location.reload();
}
function setUserSignedSession(data) {
	localStorage.setItem('user_auth', data.user_auth);
	localStorage.setItem('user_birth', data.user_birth );
	localStorage.setItem('user_email',  data.user_email);
	localStorage.setItem('user_id',  data.user_id);
	localStorage.setItem('user_name', data.user_name);
	localStorage.setItem('user_password',  data.user_password);
	localStorage.setItem('user_phonenum',  data.user_phonenum);
}
function removeUserSignedSession() {
	localStorage.removeItem('user_auth');
	localStorage.removeItem('user_birth');
	localStorage.removeItem('user_email');
	localStorage.removeItem('user_id');
	localStorage.removeItem('user_name');
	localStorage.removeItem('user_password');
	localStorage.removeItem('user_phonenum');
}
