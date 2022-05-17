;
function setInterestStockBnt(){
	const auth = localStorage.user_auth;
	let innerStruck = document.getElementById('getStockItems');
	switch(auth) {
		case "100" :
			innerStruck.innerHTML = `
			<button class="unoBnt unoBnt-middleBnt" type="button" id="addInterestStocks">관심종목추가</button>
			`;
			break;
		case 'ROLE_MEMBER' :
			innerStruck.innerHTML = `
			<button class="unoBnt unoBnt-middleBnt" type="button" id="uploadExcelFile">종목최신화</button>
			`;
			document.getElementById('uploadExcelFile').addEventListener('clcik', setPOPupOption);
			break;
		default :
			innerStruckinnerHTML = `
			<button class="unoBnt unoBnt-middleBnt" type="button" id="topSectionLogin">로그인</button>
			`;
			break;
	}
}
function setPOPupOption(){
    var url = "finance/excelUploadPOP";
    var name = "종목최신화 엑셀 업로드 팝업";
    var option = "width = 500, height = 500, top = 100, left = 200, location = no"
    window.open(url, name, option);
}
