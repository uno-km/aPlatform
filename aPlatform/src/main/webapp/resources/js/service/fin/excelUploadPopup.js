;
document.write("<script src='/resources/js/service/fin/commonFin.js'></script>");
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
		url : '/service/finance/excelUpload'
		,processData : false
		,contentType : false
		,data : formData
		,type : 'POST'
		,dataType : 'json'
		,beforeSend: function() {
	        //마우스 커서를 로딩 중 커서로 변경
	        $('html').css("cursor", "wait");
	    }
	    ,complete: function() {
	        //마우스 커서를 원래대로 돌린다
	        $('html').css("cursor", "auto");
	    }
		,success : function(res) {
			switch(res.resultDTO.code) {
				case "200" : 
					alert(res.resultDTO.message);
					setSessionSharesInfo();
					window.close();
					break;
				case '500' :
					alert(res.resultDTO.message);
					console.log(res.error);
					break;
			}
		}
	}); //$.ajax
}