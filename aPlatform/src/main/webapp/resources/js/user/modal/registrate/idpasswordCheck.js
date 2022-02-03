;
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
        document.getElementById('numExist').checked = false;
    } else {
        document.getElementById('numExist').checked = true;
    }

    if (!pattern2.test(firstInputPassword.value)) {
        document.getElementById('specialCharExist').checked = false;
    } else {
        document.getElementById('specialCharExist').checked = true;
    }

    if (res.innerText.length >= '8') {
        document.getElementById('wordCntCheck').checked = true;
    } else {
        document.getElementById('wordCntCheck').checked = false;
    }
    if (firstInputPassword.value == secondInputPassword.value && document.getElementById('numExist').checked && document.getElementById('specialCharExist').checked && document.getElementById('wordCntCheck').checked) {
        firstInputPassword.className = 'form-control is-valid';
        secondInputPassword.className = 'form-control is-valid';
    } else {
        firstInputPassword.className = 'form-control';
        secondInputPassword.className = 'form-control';
    }
}
function inputUserIdPassword(){
    this.signUpUserPassword = document.getElementById('firstInputPassword');
    this.signUpUserId = document.getElementById('inputtedId');
}