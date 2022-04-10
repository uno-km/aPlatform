;
'use strict';
document.write("<script src='/resources/js/service/fin/setContentsSection.js'></script>");
document.write("<script src='/resources/js/service/fin/setInfoShareDetailData.js'></script>");
document.write("<script src='/resources/js/service/fin/setMarketsInfo.js'></script>");
document.write("<script src='/resources/js/service/fin/setRankDataMarketCurculor.js'></script>");
document.write("<script src='/resources/js/service/fin/setNewsData.js'></script>");

var nowFinData='';
var kospiIndex='';
var kospiBuyer='';
var kospiImage='';
var kosdaqIndex='';
var kosdaqBuyer='';
var kosdaqImage='';
var rankDataMC = '';
var codeInfo = '';
var newsData = '';
var shareDetailInfo = '';

window.addEventListener('load', finPageInit);
window.onpopstate = function(event) { 
	document.getElementById('searchShareInput').value='';
	history.pushState({pageNum:3, searchDt:'2019-05-07'}, null, 'main'); 
	finPageInit();
	window.scrollTo(0,localStorage.BeforeScroll);
}
function addEvent() {
	document.getElementById('toggleHide').addEventListener('click',toggleHide);
	document.getElementById('searchShareBtn').addEventListener('click',searchShareInfo);
	document.getElementById('searchShareInput').addEventListener("keyup", keyupShareInputValue);
	document.getElementById('searchShareInput').addEventListener("focus", focusShareInputValue);
	document.addEventListener('keydown',function(e){if(e.which==17) isCtrl=true;if(e.which==89 && isCtrl ==true) alert('zz');});
	for(let index = 0 ; index < document.getElementsByClassName('inner_chart_words').length;index++) {document.getElementsByClassName('inner_chart_words')[index].addEventListener('click',changeChart);};
	for(let i = 0 ; i<document.querySelectorAll('.inner_title').length;i++) {
		document.getElementsByClassName('inner_title')[i].addEventListener('click',refreshInfo);
	}
}
function refreshInfo(e){
	switch(e.target.innerText) {
		case "뉴스" :
			console.log('뉴스 새로고침');
			getNewsdata();
			break;
		case "코스피" :
			break;
		case "코스닥" : 
			break;
		case "시가총액상위" : 
			console.log('시가총액상위 새로고침');
			getRankdata();
			break;
	}
}

function finPageInit() {
	nowFinData=null;
	setContentsSection();
	getFindata();//데이터를 가져오고 콜백으로 최초 우측페이지를 그려준다.
	getRankdata();//순위데이터를 가져오고 콜백으로 최초 랭크를 그려준다.
	getNewsdata();//뉴스데이터를 가져오고 콜백으로 최초 뉴스를 그려준다.
	if(this.localStorage.sharesInfo==null||this.localStorage.sharesInfo=='undefined') {
		setSessionSharesInfo();
	}
	addEvent();
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