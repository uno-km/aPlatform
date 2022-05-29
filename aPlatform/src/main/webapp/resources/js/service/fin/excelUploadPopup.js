;
let regex = new RegExp("(.*?)\.(xlsx|xlsm|xlsb|xltx|xltm|xls|xlt|xls|xml|xlam|xla|xlw|xlr|csv)$");
let maxSize = 5242880*4; //20MB
let formData = new FormData();

window.addEventListener('load',excelPopupInit);
var File = '';
function excelPopupInit() {
	setEvents();
}
function setEvents() {
	document.getElementById('attatchExcel').addEventListener('click',function(){
		document.getElementById('excelFileUpload').click();
	});
	document.getElementById('uploadExcel').addEventListener('click',function(){
		uploadExcelFiles();
	});
	document.getElementById('excelFileUpload').addEventListener('change',function(e){
		fileAttatchEvent(e);
	});
	
}

function fileAttatchEvent(e) {
	const file = e.target.files[0];
	if(checkExtension(file)) {
		document.getElementById('searchShareInput').value=file.name;
		formData.append('file', file);
	}
}

function checkExtension(file) {
	if (file.Size > maxSize) {
		alert("파일 사이즈 초과");
		return false;
	}
	if (!regex.test(file.name)) {
		alert("엑셀파일만 업로드해주세요!.");
		return false;
	}
	return true;
}
function uploadExcelFiles() {
	$.ajax({
		url : '/service/finance/excelUpload',
		processData : false,
		contentType : false,
		data : formData,
		type : 'POST',
		dataType : 'json',
		success : function(result) {
			switch(result) {
				case "200" : 
					alert('저장 성공!');
					break;
				case "404" :
					alert('저장 실패, 운영자에게 문의 주세요');
					break;
				case "400" :
					alert('업로드하는 파일값이 잘못 되었습니다. 다시한번 시도해 주세요');
					break;
			}
		}
	}); //$.ajax
}