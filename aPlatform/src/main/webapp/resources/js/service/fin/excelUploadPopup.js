;
let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
let maxSize = 5242880; //5MB

window.addEventListener('load',excelPopupInit);
var File = '';
function excelPopupInit() {
	setEvents();
}
function setEvents() {
	document.getElementById('uploadExcel').addEventListener('click',function(){
		document.getElementById('excelFileUpload').click();
	});
	document.getElementById('excelFileUpload').addEventListener('change',function(e){
		 fileAttatchEvent(e);
	});
}

function fileAttatchEvent(e) {
	
}

function checkExtension(fileName, fileSize) {
	if (fileSize >= maxSize) {
		alert("파일 사이즈 초과");
		return false;
	}
	if (regex.test(fileName)) {
		alert("해당 종류의 파일은 업로드할 수 없습니다.");
		return false;
	}
	return true;
}
$("#uploadBtn").on("click", function(e) {
	var formData = new FormData();
	var formData = new FormData();
	var inputFile = $("input[name='uploadFile']");
	var files = inputFile[0].files;
	for (var i = 0; i < files.length; i++) {
		if (!checkExtension(files[i].name, files[i].size)) {
			return false;
		}
		formData.append("uploadFile", files[i]);
	}
	$.ajax({
		url : '/uploadAjaxAction',
		processData : false,
		contentType : false,
		data : formData,
		type : 'POST',
		dataType : 'json',
		success : function(result) {
			console.log(result);
			showUploadedFile(result);
			$(".uploadDiv").html(cloneObj.html());
		}
	}); //$.ajax
});