//회원가입 모달 전용 js 파일
var signUpModalCnt = document.getElementById('signUpModalCnt');
var signUpModalProgress = document.getElementById('signUpModalProgress').style;
var cnt = 180;
var counter = '';
var getRandom = "";

function signUpModalNextBnt(){
    switch(this.signUpModalCnt.value){
        case '1':

            console.log(signUpModalCnt);
            this.signUpModalCnt.value='2';
            this.signUpModalProgress.width='50%';
            setSignUpModal_EmailIdCheck();
            break;
        case '2':
            if(!(document.getElementById('signUpModalEmailRandomCheck').value=='true')) {
                alert('이메일 인증을 해주세요.');
                break;
            }
            console.log(signUpModalCnt);
            this.signUpModalCnt.value='3';
            this.signUpModalProgress.width='75%';
            clearTimeout(this.counter);
            setSignUpModal_IdPasswordCheck();
            break;
        case '3':
            if (!(document.getElementById('checkDuplTest').value == 'true' && document.getElementById('firstInputPassword').className == 'form-control is-valid')) {
                alert('아이디중복체크 또는 비밀번호를 확인해주세요!');
                break;
            }
            console.log(signUpModalCnt);
            this.signUpModalCnt.value='4';
            this.signUpModalProgress.width='100%';
            break;
        case '4':
            console.log(signUpModalCnt);
            this.signUpModalCnt.value='1';
            this.signUpModalProgress.width='100%';
            break;
    };
}
function setInitSignUpModal(){
    document.getElementById('signUpModal').style.display='block';
    this.signUpModalCnt.value='1';
    this.signUpModalProgress.width='25%';
    setSignUpModal_checkPage();
}
function setSignUpModal_checkPage(){
    let struct_div = `  <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="all" id="signUpModal_consetrate_all" >
                            <label class="form-check-label" for="flexCheckDefault"> 전체 동의 </label>
                        </div>
                        <div class="alert alert-warning" role="alert">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="accept1" id="signUpModal_consetrate1">
                                <label class="form-check-label" for="flexCheckDefault">약관에 동의합니다. </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="accept2" id="signUpModal_consetrate2" >
                                <label class="form-check-label" for="flexCheckDefault">약관에 동의합니다. </label>
                            </div>
                        </div>`;
const inputBody = document.getElementById('signUpModalBody');
inputBody.innerHTML = struct_div;
};
function setCheckAll(){
}
function setSignUpModal_EmailIdCheck(){
    let struct_div = `  <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="이메일" aria-label="Username" id="signupModalInputtedUserEmail">
                            <span class="input-group-text">@</span>
                            <input type="text" class="form-control" placeholder="naver.com" id="showSelectedValue" value ="naver.com" aria-label="Username" disabled='true' />
               	            <select class="form-select" aria-label="Default select example" onchange="selectEmailChange(this)">
  						    	<option selected value ="naver">네이버</option>
  							    <option value="google">구글</option>
  							    <option value="daum">다음</option>
  							    <option value="user_email_input">직접입력</option>
							</select>
                        </div>
                        <div class="d-grid gap-2 col-6 mx-auto">
                            <button type="button" class="btn btn-primary" id="checkEmail" onclick="checkEmail()">이메일 인증</button>
                        </div>
                        <div id='checkEmailConfirm' ></div>
                        `;
const inputBody = document.getElementById('signUpModalBody');
inputBody.innerHTML = struct_div;
};
function selectEmailChange(selected){
    const selected_value = selected[selected.selectedIndex].value;
    let showSelectedValue = document.getElementById('showSelectedValue');
    switch(selected_value){
        case "naver":
            showSelectedValue.placeholder="naver.com";
            showSelectedValue.value="naver.com";
            break;
        case "daum":
            showSelectedValue.placeholder="daum.net";
            showSelectedValue.value="daum.net";
            break;
        case "google":
            showSelectedValue.placeholder="gmail.com";
            showSelectedValue.value="gmail.com";
            break;
        case "user_email_input":
        	 showSelectedValue.value="";
            showSelectedValue.placeholder="";
            showSelectedValue.disabled = false;
            showSelectedValue.focus();
            break;
    }
}
function checkEmail(){
    let showSelectedValue = document.getElementById('showSelectedValue');
    let signupModalInputtedUserEmail = document.getElementById('signupModalInputtedUserEmail');
    let regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])/;
    let sumeIvt =  document.getElementById('signUpModalEmailInputValidateTest').value;
    let sumeUvt =  document.getElementById('signUpModalEmailUrlValidateTest').value

    if(showSelectedValue.value==null||showSelectedValue.value.length==0){
        sumeIvt ='false';
        showSelectedValue.className='form-control is-invalid';
    }else showSelectedValue.className='form-control';
    if(signupModalInputtedUserEmail.value ==null||signupModalInputtedUserEmail.value.length==0){
        sumeUvt ='false';
        signupModalInputtedUserEmail.className = 'form-control is-invalid';
    }else{
        signupModalInputtedUserEmail.className='form-control';
        sumeUvt ='true';
    }
    if(!(signupModalInputtedUserEmail.value.match(regExp)!=null)) alert('올바른 이메일을 입력해주세요');
    else sumeIvt='true';
    if(sumeIvt=='true'&&sumeUvt=='true'){
        const user_email = signupModalInputtedUserEmail.value+"@"+showSelectedValue.value;
        sendEmailForCheckValidation(user_email);
        checkEmailForValidate();
        countDown3min();
    }
}
function checkEmailForValidate(){
    let struct_div = `  <hr/>
                            <div class="container">
                                <div class="row">
                                    <div class="col-sm-8">
                                        <div class="form-floating md-3" >
                                            <textarea class="form-control" placeholder="Leave a comment here" id="textAreaForCheckRandom"></textarea>
                                            <label for="textAreaForCheckRandom" id='setTime'></label>
                                            </div>
                                        </div>
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-primary" onclick="checkRandom()">입력완료</button>
                                        <button type="button" class="btn btn-primary" onclick="checkEmail()">재전송</button>
                                    </div>
                                </div>
                            </div>
                        `;
    const inputBody = document.getElementById('checkEmailConfirm');
    inputBody.innerHTML = struct_div;
}
function checkRandom(){
    const testAreaVal = document.getElementById('textAreaForCheckRandom').value;
    if(testAreaVal.length==0||testAreaVal==null){
        alert("빈칸을 입력하셨습니다. 다시 입력해주세요.");
    }
    if(testAreaVal==this.getRandom){
        alert("확인완료");
        document.getElementById('signUpModalEmailRandomCheck').value ='true';
        this.getRandom ='';
    }
    else{
        alert("틀렸습니다. 다시 입력해주세요.");
    }
}
function signUpModalCloseBtn(){
    if (counter != null) {
        clearTimeout(this.counter);
    }
}
function countDown3min(){
    if (counter != null) {
        clearTimeout(this.counter);
    }
    this.cnt = 180;
    document.getElementById('setTime').innerText = this.cnt;
    this.counter = setInterval(() => {
        this.cnt--;
        document.getElementById('setTime').innerText = this.cnt;
        if(this.cnt<=0){
            clearInterval(this.counter);
            document.getElementById('setTime').innerText = '시간이 초과되었습니다.';
            this.getRandom="";
        }
    }, 1000); 
}
function sendEmailForCheckValidation(user_email) {
    const sendingVO = {
        "mail_sbst" : "CHK"
    ,   "mail_send_time" : "signup"  
    ,   "user_email" : user_email 
    }
    $.ajax({
        type: 'GET',
        url: '/user/checkEmail',
        async: false,
        data : sendingVO,
        dataType: 'JSON', 
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            setRandom(data);
        },
        error: function () {
            alert('통신실패!!');
        }
    });
}
function setRandom(data){
    this.getRandom = data;
}

function setSignUpModal_IdPasswordCheck(){
let struct_div = `  
        <div class="input-group mb-3">
            <div class="input-group mb-3">
                <input type="hidden" id="checkDuplTest" value="false" />
                <input type="text" class="form-control"
                    placeholder="가입하실 아이디를 입력해주세요." aria-describedby="button-addon2"
                    id='inputtedId'>
                <button class="btn btn btn-primary" type="button"
                    id="button-addon2" onclick='checkDuplicataionId()'>중복검사</button>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox" role="switch"
                            id="numExist" disabled>
                        <label class="form-check-label" for="flexSwitchCheckDisabled">숫자여부</label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox" role="switch"
                            id="specialCharExist" disabled>
                        <label class="form-check-label"
                            for="flexSwitchCheckCheckedDisabled">특수문자</label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox" role="switch"
                            id="wordCntCheck" disabled>
                        <label class="form-check-label" for="specialCharExist">8자
                            이상</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="input-group mb-3">
            <input type="password" class="form-control"
                id="firstInputPassword" onkeyup='printName()'
                placeholder="8자이상, 숫자, 특수기호포함">

        </div>
        <div class="input-group mb-3">
            <input type="password" class="form-control"
                id="secondInputPassword" onkeyup='printName()'
                placeholder="8자이상, 숫자, 특수기호포함">
        </div>
        <div id='result' hidden='true' />
    </div>
`;
const inputBody = document.getElementById('signUpModalBody');
inputBody.innerHTML = struct_div;
}
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
function judgeDuplicataion(data) {
    const inputtedId = document.getElementById("inputtedId");
    if (null == inputtedId.value || inputtedId.value.length == 0) {
        document.getElementById('checkDuplTest').value = 'false';
        alert('아이디를 입력해주세요.');
        inputtedId.className = 'form-control is-invalid';
    } else {
        if (data == true) {
            document.getElementById('checkDuplTest').value = 'true';
            alert('사용가능한 아이디입니다.');
            inputtedId.className = 'form-control is-valid';
        } else {
            document.getElementById('checkDuplTest').value = 'false';
            alert('중복된 아이디입니다.');
            inputtedId.className = 'form-control is-invalid';
        }
    }
}
function printName() {
    var pattern1 = /[0-9]/; // 숫자
    var pattern2 = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자

    const res = document.getElementById("result");
    const firstInputPassword = document.getElementById('firstInputPassword');
    const secondInputPassword = document.getElementById('secondInputPassword');
    res.innerText = firstInputPassword.value;
    if (!pattern1.test(firstInputPassword.value)) {
        document
            .getElementById('numExist')
            .checked = false;
    } else {
        document
            .getElementById('numExist')
            .checked = true;
    }

    if (!pattern2.test(firstInputPassword.value)) {
        document
            .getElementById('specialCharExist')
            .checked = false;
    } else {
        document
            .getElementById('specialCharExist')
            .checked = true;
    }

    if (res.innerText.length >= '8') {
        document
            .getElementById('wordCntCheck')
            .checked = true;
    } else {
        document
            .getElementById('wordCntCheck')
            .checked = false;
    }
    if (firstInputPassword.value == secondInputPassword.value && document.getElementById('numExist').checked && document.getElementById('specialCharExist').checked && document.getElementById('wordCntCheck').checked) {
        firstInputPassword.className = 'form-control is-valid';
        secondInputPassword.className = 'form-control is-valid';
    } else {
        firstInputPassword.className = 'form-control';
        secondInputPassword.className = 'form-control';
    }
}