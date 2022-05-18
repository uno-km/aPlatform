;
window.addEventListener('load',excelPopupInit);

function excelPopupInit() {
	setEvents();
}
function setEvents() {
	document.getElementById('uploadExcel').addEventListener('click',function(){
		document.getElementById('excelFileUpload').click();
	});
}