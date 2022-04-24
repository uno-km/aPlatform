;
function setSignUpModal_checkPage(){
	document.getElementById('signUpModalBody').innerHTML = `
						<div class="form-check">
                            <input class="form-check-input" type="checkbox" value="all" id="signUpModal_consetrate_all" onClick="selectAll(this)" name="signUpCheckBox">
                            <label class="form-check-label" for="signUpModal_consetrate_all"> 전체 동의 </label>
                        </div>
                        <div class="alert alert-warning" role="alert">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="accept1" name="signUpCheckBox" id="signUpcheckBox1">
                                <label class="form-check-label" for="signUpcheckBox1">약관에 동의합니다. </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="accept2" name="signUpCheckBox" id="signUpcheckBox2">
                                <label class="form-check-label" for="signUpcheckBox2">약관에 동의합니다. </label>
                            </div>
                        </div>`;
}
function selectAll(all){
    const checkAll = document.getElementsByName('signUpCheckBox');
    checkAll.forEach((checkbox) => {
        checkbox.checked = all.checked;        
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