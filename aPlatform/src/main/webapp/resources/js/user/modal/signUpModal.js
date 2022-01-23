//회원가입 모달 전용 js 파일
var signUpModalCnt = document.getElementById('signUpModalCnt');
var signUpModalProgress = document.getElementById('signUpModalProgress').style;

function signUpModalNextBnt(){
    switch(this.signUpModalCnt.value){
        case '1':
            console.log(signUpModalCnt);
            this.signUpModalCnt.value='2';
            this.signUpModalProgress.width='25%';
            setSignUpModal_EmailIdCheck();
            break;
        case '2':
            console.log(signUpModalCnt);
            this.signUpModalCnt.value='3';
            this.signUpModalProgress.width='50%';
            break;
        case '3':
            console.log(signUpModalCnt);
            this.signUpModalCnt.value='4';
            this.signUpModalProgress.width='75%';
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
    this.signUpModalProgress.width='0%';
    setSignUpModal_EmailIdCheck();
    // setSignUpModal_checkPage();
}

function setSignUpModal_checkPage(){
    let struct_div = `  <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="signUpModal_consetrate_all" >
                            <label class="form-check-label" for="flexCheckDefault"> 전체 동의 </label>
                        </div>
                        <div class="alert alert-warning" role="alert">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="signUpModal_consetrate1">
                                <label class="form-check-label" for="flexCheckDefault">약관에 동의합니다. </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="signUpModal_consetrate2" >
                                <label class="form-check-label" for="flexCheckDefault">약관에 동의합니다. </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="signUpModal_consetrate3" >
                                <label class="form-check-label" for="flexCheckDefault"> 만 14세 이상입니다. </label>
                            </div>
                        </div>`;
const inputBody = document.getElementById('signUpModalBody');
inputBody.innerHTML = struct_div;
};
function setSignUpModal_EmailIdCheck(){
    let struct_div = `  <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="이메일" aria-label="Username">
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