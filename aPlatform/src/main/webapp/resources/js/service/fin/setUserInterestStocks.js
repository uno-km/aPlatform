;
function setInterestStockBnt(){
	const auth = localStorage.user_auth;
	let innerStruck = document.getElementById('getStockItems');
	switch(auth) {
		case "100" :
			innerStruck.innerHTML = `
			<button class="unoBnt unoBnt-middleBnt" type="button" id="addInterestStocks">관심종목추가</button>
			`;
			document.getElementById('addInterestStocks').addEventListener('click',addUserInterestStocks);
			break;
		case "101" :
			innerStruck.innerHTML = `
			<button class="unoBnt unoBnt-middleBnt" type="button" id="addInterestStocks">관심종목추가</button>
			`;
			document.getElementById('addInterestStocks').addEventListener('click',addUserInterestStocks);
			break;
		case 'ROLE_MEMBER' :
			innerStruck.innerHTML = `
			<button class="unoBnt unoBnt-middleBnt" type="button" id="uploadExcelFile">종목최신화</button>
			`;
			document.getElementById('uploadExcelFile').addEventListener('click',fn_uploaExcel);
			break;
		default :
			innerStruck.innerHTML = `
			<button class="unoBnt unoBnt-middleBnt" type="button" id="topSectionLogin">로그인 이용 바람</button>
			`;
			break;
	}
}
function fn_uploaExcel() {
	console.log('qwe');
	let url = "excelUploadPopup";
	let name = "_blank";
	let setSizeOption = ['500','300','250','250'];
	let setWebOption = [];
	setPOPupOption(url,name,setSizeOption,setWebOption);
}
function setPOPupOption(inUrl, inName, inSetSizeOption, inSetWebOption){
	const sizeOptionList = ["width","height","top","left"];
	const webOptionList = ["location","scrollbars","tollbar","menubar","status","fullscreen","resizeable"];
	let width = "";
	let height = "";
	if(inSetSizeOption[2].length>1) {
		width = inSetSizeOption[2];
	}
	if(inSetSizeOption[3].length>1) {
		height = inSetSizeOption[3];
	}
	const popupWidth = (window.screen.width / 2) - (width / 2);
	const popupHeight = (window.screen.height / 2) - (height / 2);
	
	const url = inUrl;
	const name = inName;
	let option = "";
	if(inSetSizeOption.length!=0) {
		for(let i = 0 ; i < inSetSizeOption.length-2;i++) {
			option += sizeOptionList[i]+"="+inSetSizeOption[i] + ", ";
		}
		option += sizeOptionList[2]+"="+popupWidth + ", ";
		option += sizeOptionList[3]+"="+popupHeight;
	}
    if(inSetWebOption.length!=0) {
    	option += ", ";
    	for(let i = 0 ; i < inSetWebOption.length;i++) {
    		option += webOptionList[i]+"="+inSetWebOption[i];
    		if(i!=inSetWebOption.lenght-1) 
    			option += ", ";
    	}
    }
    window.open(url, name, option);
}
function addUserInterestStocks() {
	if(curSearchShareName.length>0||curSearchShareName!="") {
		let sharesInfo = JSON.parse(localStorage.sharesInfo);
		let searchData = sharesInfo[curSearchShareName];
		if(searchData == undefined || searchData == null) {
			
		}
		alert(searchData);
	}
}
