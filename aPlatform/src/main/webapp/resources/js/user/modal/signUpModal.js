//회원가입 모달 전용 js 파일
document.write("<script src='/resources/js/user/modal/registrate/emailCheck.js'></script>");
document.write("<script src='/resources/js/user/modal/registrate/checkBoxCheck.js'></script>");
document.write("<script src='/resources/js/user/modal/registrate/idpasswordCheck.js'></script>");
document.write("<script src='/resources/js/user/modal/registrate/namebirthphoneInput.js'></script>");
var signUpModalCnt = document.getElementById('signUpModalCnt');
var signUpModalProgress = document.getElementById('signUpModalProgress').style;
var cnt = 180;
var counter = '';
var getRandom = "";

function signUpModalNextBnt(){
    switch(this.signUpModalCnt.value){
        case '1':
            checkCheckBox();
            if(document.getElementById("signUpModalCheck").value == 'false'){
                alert("체크박스를 확인해주세요!");
                break;
            }
            setSignUpModal_EmailIdCheck();
            this.signUpModalCnt.value='2';
            this.signUpModalProgress.width='25%';
            break;
        case '2':
            if(!(document.getElementById('signUpModalEmailRandomCheck').value=='true')) {
                alert('이메일 인증을 해주세요.');
                break;
            }
            clearTimeout(this.counter);
            setSignUpModal_IdPasswordCheck();
            this.signUpModalCnt.value='3';
            this.signUpModalProgress.width='50%';
            break;
        case '3':
            if (!(document.getElementById('checkDuplTest').value == 'true' && document.getElementById('firstInputPassword').className == 'form-control is-valid')) {
                alert('아이디중복체크 또는 비밀번호를 확인해주세요!');
                break;
            }
            inputUserIdPassword();
            setSignUpModal_NameBirthPhoneInput();
            buttonChange();
            this.signUpModalCnt.value='4';
            this.signUpModalProgress.width='75%';
            break;
        case '4':
            inputUserNameBirthPhone();
            signUp();
            this.signUpModalProgress.width='100%';
            break;
    };
}
function buttonChange(){
	document.getElementById('buttonChange').innerHTML=` 
			<button type="button" class	= "btn btn-primary" data-bs-dismiss	= "modal" 
			id = "signUpModalNextBnt" onclick = "signUpModalNextBnt()">다음</button>`
}
function setInitSignUpModal(){
    document.getElementById('signUpModal').style.display='block';
    this.signUpModalCnt.value='1';
    this.signUpModalProgress.width='0%';
    this.signUpUserId.value='';
    this.signUpUserPassword.value='';
    this.signUpUserEmail.value='';
    this.signUpUserName.value='';
    this.signUpUserBirth.value='';
    this.signUpUserPhone.value='';
    setSignUpModal_checkPage();
}
function signUpModalCloseBtn(){
    if (counter != null) {
        clearTimeout(this.counter);
    }
}
function signUp(){
    var maindataLoadInVO = {
        "user_id": this.signUpUserId.value,
        "user_password": this.signUpUserPassword.value,
        "user_email" : this.signUpUserEmail.value,
        "user_name" : this.signUpUserName.value,
        "user_phonenum" : this.signUpUserPhone.value,
        "user_birth" : this.signUpUserBirth.value,
        "user_auth" : '100'
    }
    $.ajax({
        type: 'POST',
        url: '/user/signup',
        data: JSON.stringify(maindataLoadInVO),
        dataType: 'JSON', 
        async : true,
        contentType: 'application/json; charset=utf-8',
        success: function (data) {  
            if(data)
            {
                alert("회원가입에 성공했습니다.");
            }
            else
            {
                alert("실패..");
            }
        },
        error: function () {
            alert('통신실패!!');
        }
    });
}