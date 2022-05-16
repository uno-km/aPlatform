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

function AJAX(TYPE_,URL_,DATA_,ASYNC_,fn1 ,fn2) {
	let inTYPE_ = TYPE_;
	let inURL_ = URL_;
	let inDATA_ = DATA_;
	let inASYNC_ = ASYNC_;
	if(inTYPE_==null || inTYPE_ ==undefined) {
		TYPE_="GET"
	}
	if(inURL_==null || inURL_==undefined) {
		inURL_ = "error/404"
	}
	if(inDATA_==null || inDATA_==undefined) {
		inDATA_ = null;
	}
	if(inASYNC_==null || inASYNC_==undefined) {
		inASYNC_ = false;
	}
	$.ajax({
        type: inTYPE_,
        url: inURL_,
        data : inDATA_,
        dataType: 'JSON', 
        async: inASYNC_,
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
			if(fn1!=null||fn1!=undefined) {
				fn1(data)
			}
        },
        error: function () {
            alert('통신실패!!');
        }
    });
	if(fn2!=null||fn2!=undefined) {
		infn2
	}
}