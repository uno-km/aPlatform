;
function setSignUpModal_checkPage(){
    let struct_div = `  <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="all" id="signUpModal_consetrate_all" onClick="selectAll(this)" name="signUpCheckBox">
                            <label class="form-check-label" for="flexCheckDefault"> 전체 동의 </label>
                        </div>
                        <div class="alert alert-warning" role="alert">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="accept1" name="signUpCheckBox" id="signUpcheckBox1">
                                <label class="form-check-label" for="flexCheckDefault">약관에 동의합니다. </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="accept2" name="signUpCheckBox" id="signUpcheckBox2">
                                <label class="form-check-label" for="flexCheckDefault">약관에 동의합니다. </label>
                            </div>
                        </div>`;
const inputBody = document.getElementById('signUpModalBody');
inputBody.innerHTML = struct_div;
}
function selectAll(selectAll){
    const checkAll = document.getElementsByName('signUpCheckBox');
    checkAll.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;        
    });
}
function checkCheckBox(){
    const signUpcheckBox1 = document.getElementById("signUpcheckBox1");
    const signUpcheckBox2 = document.getElementById("signUpcheckBox2");
    if(signUpcheckBox1.checked && signUpcheckBox2.checked){
        document.getElementById("signUpModalCheck").value = 'true';
    }else{
        document.getElementById("signUpModalCheck").value = 'false';
    }
}