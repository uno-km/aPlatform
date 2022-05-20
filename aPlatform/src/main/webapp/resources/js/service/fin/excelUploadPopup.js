;
window.addEventListener('load',excelPopupInit);
var File = '';
function excelPopupInit() {
	setEvents();
}
function setEvents() {
	document.getElementById('uploadExcel').addEventListener('click',function(){
		document.getElementById('excelFileUpload').click();
	});
	document.getElementById('excelFileUpload').addEventListener('change',function(){
		alert('qwe');
	});
}

function fileAttatchEvent(e) {
	
}