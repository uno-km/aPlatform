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
			<button class="unoBnt unoBnt-middleBnt" type="button" id="uploadExcelFile" onclick="fn_uploaExcel()">종목최신화</button>
			`;
			break;
		default :
			innerStruckinnerHTML = `
			<button class="unoBnt unoBnt-middleBnt" type="button" id="topSectionLogin">로그인</button>
			`;
			break;
	}
}
function fn_uploaExcel() {
	console.log('qwe');
	let url = "excelUploadPopup";
	let name = "_blank";
	let setSizeOption = ['500','500','500','500'];
	let setWebOption = [];
	setPOPupOption(url,name,setOption);
}
function setPOPupOption(inUrl, inName, inSetSizeOption, inSetWebOption){
	const sizeOptionList = ["width","height","top","left"];
	const webOptionList = ["location","scrollbars","tollbar","menubar","status","fullscreen","resizeable"];];
	const url = inUrl;
	const name = inName;
	let option = "";
	if(inSetSizeOption.length!=0) {
		for(let i = 0 ; i < inSetSizeOption.length;i++) {
			option += sizeOptionList[i]+"="+inSetSizeOption[i];
			if(i!=inSetSizeOption.lenght-1) 
				option += ", ";
		}
	}
    if(inSetWebOption.length!=0) {
    	for(let i = 0 ; i < inSetWebOption.length;i++) {
    		option += webOptionList[i]+"="+inSetWebOption[i];
    		if(i!=inSetWebOption.lenght-1) 
    			option += ", ";
    	}
    }
    window.open(url, name, option);
}
