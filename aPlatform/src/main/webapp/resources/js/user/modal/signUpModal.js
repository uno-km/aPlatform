//회원가입 모달 전용 js 파일
var signUpModalCnt = document.getElementById('signUpModalCnt').value;
var signUpModalProgress = document.getElementById('signUpModalProgress').style.width;

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
    this.signUpModalCnt='1';
    this.signUpModalProgress='0%';
    setSignUpModal_checkPage();
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
                            <input type="text" class="form-control" placeholder="Username" aria-label="Username">
                            <span class="input-group-text">@</span>
                            <input type="text" class="form-control" placeholder="Server" aria-label="Server">
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