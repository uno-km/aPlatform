;
document.write("<script src='/resources/js/service/fin/setContentsSection.js'></script>");
document.write("<script src='/resources/js/service/fin/setInfoShareDetailData.js'></script>");
var nowFinData='';
var kospiIndex='';
var kospiBuyer='';
var kospiImage='';
var kosdaqIndex='';
var kosdaqBuyer='';
var kosdaqImage='';
var rankDataMC = '';
var codeInfo = '';
window.onpopstate = function(event) { 
	history.pushState({pageNum:3, searchDt:'2019-05-07'}, null, 'main'); 
	finPageInit();
	window.scrollTo(0,localStorage.BeforeScroll);
}
document.getElementById('searchShareBtn').addEventListener('click',searchShareInfo);
document.getElementById('searchShareInput').addEventListener("keyup", keyupShareInputValue);
document.getElementById('searchShareInput').addEventListener("focus", focusShareInputValue);
function searchShareInfo(e) {
	let inputData = document.getElementById('searchShareInput').value;
	let sharesInfo = JSON.parse(localStorage.sharesInfo);
	let searchData = sharesInfo[inputData];
	getShareInfoDTL(searchData);
}
function searchShareInfoSearchList(value) {
	document.getElementById('searchingList').style = "visibility:hidden;";
	let sharesInfo = JSON.parse(localStorage.sharesInfo);
	let searchData = sharesInfo[value];
	getShareInfoDTL(searchData);
}
window.addEventListener('load', function () {
	finPageInit()
});

function finPageInit() {
	setContentsSection();
	getFindata();
	getRankdata();
	setKospiIndex();
	setKosdaqIndex();
	setKospiImage();
	setKosdaqImage();
	setKospiBuyer();
	setKospiBuyerColor();
	setKosdaqBuyer();
	setKosdaqBuyerColor();
	setRankDataMC();
	setRankDataMCColor();
	setSessionSharesInfo();
}
function getFindata() {
	let outData='';
    $.ajax({
        type: 'GET',
        url: '/service/finance/total',
        dataType: 'JSON', 
        async: false,
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
    		outData=data;
        },
        error: function () {
            alert('통신실패!!');
        }
    });
	this.kospiIndex	= outData[0].kospi_index;
	this.kospiBuyer= outData[1];
	this.kospiImage= outData[2];
	this.kosdaqIndex= outData[3].kosdaq_index;
	this.kosdaqBuyer= outData[4];
	this.kosdaqImage= outData[5];
    this.nowFinData = outData;
}
function getRankdata() {
	let outData='';
	$.ajax({
		type: 'GET',
		url: '/service/finance/rank',
		dataType: 'JSON', 
		async: false,
		contentType: 'application/json; charset=utf-8',
		success: function (data) {
		outData=data;
	},
		error: function () {
		alert('통신실패!!');
	}
	});
	this.rankDataMC =outData;
}
function setKospiIndex() {
	const inputBody= document.getElementById('kospiIndex');
	inputBody.innerText = this.kospiIndex;
}
function setKosdaqIndex() {
	const inputBody= document.getElementById('kosdaqIndex');
	inputBody.innerText = this.kosdaqIndex;
}
function setKospiImage() {
	const inputBody= document.getElementById('kospiImage');
	inputBody.style.backgroundImage=`URL('${this.kospiImage.kospi_day}')`;
	inputBody.style.backgroundSize="100% 100%";
	inputBody.style.backgroundPosition="center";
}
function setKosdaqImage() {
	const inputBody= document.getElementById('kosdaqImage');
	inputBody.style.backgroundImage=`URL('${this.kosdaqImage.kosdaq_day}')`;
	inputBody.style.backgroundSize="100% 100%";
	inputBody.style.backgroundPosition="center";
}
function setKospiBuyer() {
	let struct_div ="";
	if(this.kospiBuyer!=null) {
		struct_div	=	`	<div class='inner_kospiBuyer'>개인</div>
							<div class='inner_kospiBuyer'>${this.kospiBuyer.kospi_ant}</div>
							<div class='inner_kospiBuyer'>기관</div>
							<div class='inner_kospiBuyer'>${this.kospiBuyer.kospi_org}</div>
							<div class='inner_kospiBuyer'>외국인</div>
							<div class='inner_kospiBuyer'>${this.kospiBuyer.kospi_frg}</div>`;
		const inputBody = document.getElementById('kospiBuyer');
		inputBody.innerHTML=struct_div;
	}else {
        console.log("해당 영억없음");
	}
}
function setKospiBuyerColor() {
	for(let i=0;i<document.getElementsByClassName('inner_kospiBuyer').length;i++) {
		if(document.getElementsByClassName('inner_kospiBuyer')[i].innerText.includes('+')) {
			document.getElementsByClassName('inner_kospiBuyer')[i].style.color='red';
			document.getElementsByClassName('inner_kospiBuyer')[i].style.borderColor='red';
		}
	}
}
function setKosdaqBuyer() {
	let struct_div ="";
	if(this.kosdaqBuyer!=null) {
		struct_div	=	`	<div class='inner_kosdaqBuyer'>개인</div>
							<div class='inner_kosdaqBuyer'>${this.kosdaqBuyer.kosdaq_ant}</div>
							<div class='inner_kosdaqBuyer'>기관</div>
							<div class='inner_kosdaqBuyer'>${this.kosdaqBuyer.kosdaq_org}</div>
							<div class='inner_kosdaqBuyer'>외국인</div>
							<div class='inner_kosdaqBuyer'>${this.kosdaqBuyer.kosdaq_frg}</div>`;
		const inputBody = document.getElementById('kosdaqBuyer');
		inputBody.innerHTML=struct_div;
	}else {
		console.log("해당 영억없음");
	}
}
function setKosdaqBuyerColor() {
	for(let i=0;i<document.getElementsByClassName('inner_kosdaqBuyer').length;i++) {
		if(document.getElementsByClassName('inner_kosdaqBuyer')[i].innerText.includes('+')) {
			document.getElementsByClassName('inner_kosdaqBuyer')[i].style.color='red';
			document.getElementsByClassName('inner_kosdaqBuyer')[i].style.borderColor='red';
		}
	}
}
function setRankDataMC() {
	let struct_div ="";
	let cntMax = Object.keys(this.rankDataMC).length;
	if(this.rankDataMC!=null) {
		for(let i=0;i<cntMax;i++) {// onclick='goShareInfo(this)'
			struct_div +=`
					<div class="inner_rank_m" onclick='goShareInfo(this)'>${this.rankDataMC[i][0]}</div>
						`;
		}
		document.getElementById('rankDataMCName').innerHTML=struct_div;
		struct_div =``;
		for(let i=0;i<cntMax;i++) {
			struct_div +=`
					<div class='inner_rank_values'>
						<div class='inner_rank_lin'>${this.rankDataMC[i][1]}</div>
						<div class='inner_rank_lin'>${this.rankDataMC[i][3]}</div>
						<div class='inner_rank_lin'>${this.rankDataMC[i][4]}</div>
					</div>
					<input type='hidden' name='updownChecker' value='${this.rankDataMC[i][2]}'>
						`;
		}	
		document.getElementById('rankDataMCValues').innerHTML=struct_div;
	}else {
		console.log("해당 영억없음");
	}
}
function  setRankDataMCColor() {
	for(let i=0;i<document.getElementsByClassName('inner_rank_values').length;i++) {
		if(document.getElementsByName('updownChecker')[i].value=='상승') {
			document.getElementsByClassName('inner_rank_m')[i].style.color='red';
			document.getElementsByClassName('inner_rank_m')[i].style.borderColor='red';
			document.getElementsByClassName('inner_rank_values')[i].style.color='red';
			document.getElementsByClassName('inner_rank_values')[i].style.borderColor='red';
		}else if (document.getElementsByName('updownChecker')[i].value=='0'){
			document.getElementsByClassName('inner_rank_m')[i].style.color='gray';
			document.getElementsByClassName('inner_rank_m')[i].style.borderColor='gray';
			document.getElementsByClassName('inner_rank_values')[i].style.color='gray';
			document.getElementsByClassName('inner_rank_values')[i].style.borderColor='gray';
		}
	}
}
function setSessionSharesInfo() {
	let outData ="";
    $.ajax({
        type: 'GET',
        url: '/service/finance/codeAllMap',
        dataType: 'JSON', 
        async: false,
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
    		outData=data;
        },
        error: function () {
            alert('통신실패!!');
        }
    });
    this.codeInfo = JSON.stringify(outData);
    let objData = JSON.stringify(outData);
    localStorage.setItem('sharesInfo' ,objData);
}

function goShareInfo(input) {
	let fin_name = input.innerText;
	let sharesInfo = JSON.parse(localStorage.sharesInfo);
	let obj = Object.keys(sharesInfo).filter(e => e.indexOf(fin_name) >-1);
	let code = '';
	if(obj.length==1) {
		code = sharesInfo[fin_name];
	} else if(obj.length>1) {
		for(let i = 0 ; i<obj.length;i++) {
			if(obj[i]==fin_name) {
				code = sharesInfo[fin_name];
			}
		}
		if(code=='') {
			alert('값이 없습니다. 잠시후 재시도해주세요.');
		}
	}else {
		alert('잘못된 경로입니다.');
	}
	if(code!='') {
		getShareInfoDTL(code);
	}
}
function getShareInfoDTL(code) {
    $.ajax({
        type: 'GET',
        url: `/service/finance/shareInfo?code=`+code,
        dataType: 'JSON', 
        async: false,
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
		setInfoShareDetailFrame();
		setInfoShareDetailData(data);
		let fin_name = getKeyByValue(JSON.parse(localStorage.sharesInfo), code);
		history.pushState({'name':fin_name,'code':code},'종목상세보기','main');
		window.scrollTo(0,0);
		localStorage.setItem('BeforeScroll',window.scrollY);
		window.scrollY;
    },
        error: function () {
            alert('통신실패!!');
        }
    });
}
function getKeyByValue(object, value) {
	  return Object.keys(object).find(key => object[key] === value);
}
function keyupShareInputValue(){
    if (window.event.keyCode == 13) {
    	if(document.querySelectorAll('.shareSearchInput li').length>0) {
    		searchShareInfoSearchList(document.querySelectorAll('.shareSearchInput li a')[0].text)
    	}else {
    		console.log('input값 없음');
    	}
    	document.getElementById('searchingList').style = "visibility:hidden;";
    	document.getElementById('searchShareInput').value='';
    }else {
    	if(!(this.value==""||this.value.legnth==0)) {
    		let struct_div ='';
    		Object.keys(JSON.parse(localStorage.sharesInfo)).forEach((obj)=>{
    			if(obj.includes(this.value.toUpperCase())) {
    				struct_div +=	`<li><a class="dropdown-item" onclick="searchShareInfoSearchList('${obj}')">${obj}</a></li>`;
    				console.log(obj);
    				cnt++;
    			}
    		});
    		document.getElementById('searchingList').innerHTML = struct_div;
        	if(document.querySelectorAll('.shareSearchInput li').length>0) {
        		document.getElementById('ext').className = 'btn btn-outline-primary dropdown-toggle dropdown-toggle-split show';
        		document.getElementById('searchingList').className = 'dropdown-menu shareSearchInput show';
        		document.getElementById('searchingList').style = "position: absolute; inset: 0px auto auto 0px; margin: 0px; transform: translate3d(0px, 58px, 0px);";
        	}else {
        		document.getElementById('ext').className = 'btn btn-outline-primary dropdown-toggle dropdown-toggle-split';
        		document.getElementById('searchingList').className = 'dropdown-menu shareSearchInput';
        		document.getElementById('searchingList').style = "visibility:hidden;";
        	}
    	}else {
    		document.getElementById('ext').className = 'btn btn-outline-primary dropdown-toggle dropdown-toggle-split';
    		document.getElementById('searchingList').className = 'dropdown-menu shareSearchInput show';
    		document.getElementById('searchingList').style = "visibility:hidden;";
    	}
    }
}
function focusShareInputValue()	{
	if(!(this.value==""||this.value.legnth==0)) {
		let struct_div ='';
		Object.keys(JSON.parse(localStorage.sharesInfo)).forEach((obj)=>{
			if(obj.includes(this.value.toUpperCase())) {
				struct_div +=`<li><a class="dropdown-item" onclick="searchShareInfoSearchList('${obj}')">${obj}</a></li>`;
			}
		});
		document.getElementById('searchingList').innerHTML = struct_div;
    	if(document.querySelectorAll('.shareSearchInput li').length>0) {
    		document.getElementById('ext').className = 'btn btn-outline-primary dropdown-toggle dropdown-toggle-split show';
    		document.getElementById('searchingList').className = 'dropdown-menu shareSearchInput show';
    		document.getElementById('searchingList').style = "position: absolute; inset: 0px auto auto 0px; margin: 0px; transform: translate3d(0px, 58px, 0px);";
    	}else {
    		document.getElementById('ext').className = 'btn btn-outline-primary dropdown-toggle dropdown-toggle-split';
    		document.getElementById('searchingList').className = 'dropdown-menu shareSearchInput';
    		document.getElementById('searchingList').style = "visibility:hidden;";
    	}
	}
}