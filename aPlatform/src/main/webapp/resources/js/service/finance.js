;
'use strict';
/*삽입될 JS파일들*/
document.write("<script src='/resources/js/service/fin/setContentsSection.js'></script>");
document.write("<script src='/resources/js/service/fin/setInfoShareDetailData.js'></script>");
document.write("<script src='/resources/js/service/fin/setMarketsInfo.js'></script>");
document.write("<script src='/resources/js/service/fin/setRankDataMarketCurculor.js'></script>");
document.write("<script src='/resources/js/service/fin/setNewsData.js'></script>");
document.write("<script src='/resources/js/service/fin/setOilInterestExchange.js'></script>");
document.write("<script src='/resources/js/service/fin/setUserInterestStocks.js'></script>");
document.write("<script src='/resources/js/service/fin/commonFin.js'></script>");
/*데이터 전역변수*/
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
/*서버input값 전역변수*/
var kospiSendingVO ='';
var kosdaqSendingVO='';
var shareDetailInfo = '';
var newsDataSendingVO ='';
var etcIndexSendingVO ='';
var rankDataSendingVO ='';
/*윈도우 이벤트 삽입*/
window.addEventListener('load', finPageInit);
window.onpopstate = function(event) { 
	document.getElementById('searchShareInput').value='';
	history.pushState({pageNum:3, searchDt:'2019-05-07'}, null, 'main'); 
	finPageInit();
	window.scrollTo(0,localStorage.BeforeScroll);
}
/*각 엘리먼트별 이벤트 삽입*/
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
			console.log('코스피 새로고침');
			getFindata('kospi');
			break;
		case "코스닥" : 
			console.log('코스닥 새로고침');
			getFindata('kosdaq');
			break;
		case "시가총액상위" : 
			console.log('시가총액상위 새로고침');
			getRankdata();
			break;
	}
}
function getSendingData() {
    const reqJson = {
            "url" : "main"
        ,   "pharseType" : "news"  
        }
	let httpRequest =  new XMLHttpRequest();
    httpRequest.onreadystatechange = () => {
	    if (httpRequest.readyState === XMLHttpRequest.DONE) {
		      if (httpRequest.status === 200) {
		    	let result = httpRequest.response;
		    	console.log(result);
		      } else {
		        alert('request에 뭔가 문제가 있어요.');
		      }
		}
    };
    httpRequest.open('POST', '/service/finance/getSendingData', true);
    httpRequest.responseType = "json";
    httpRequest.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
    httpRequest.send(JSON.stringify(reqJson));
}
function finPageInit() {
	nowFinData=null;
	setContentsSection();
	getFindata('total');//데이터를 가져오고 콜백으로 최초 우측페이지를 그려준다.
	getRankdata();//순위데이터를 가져오고 콜백으로 최초 랭크를 그려준다.
	getNewsdata();//뉴스데이터를 가져오고 콜백으로 최초 뉴스를 그려준다.
	getOilInterestExchange();
	if(this.localStorage.sharesInfo==null||this.localStorage.sharesInfo=='undefined') {
		setSessionSharesInfo();
	}
	setInterestStockBnt();
	addEvent();
//	getSendingData();
}