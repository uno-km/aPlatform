;
'use strict';
document.write("<script src='/resources/js/service/fin/setContentsSection.js'></script>");
document.write("<script src='/resources/js/service/fin/setInfoShareDetailData.js'></script>");
document.write("<script src='/resources/js/service/fin/setMarketsInfo.js'></script>");
document.write("<script src='/resources/js/service/fin/setRankDataMarketCurculor.js'></script>");

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
	for(let index = 0 ; index < document.getElementsByClassName('inner_chart_words').length;index++) {
		document.getElementsByClassName('inner_chart_words')[index].addEventListener('click',changeChart);
	};
}
function changeChartRDO(e) {
	console.log(e);
}

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

function finPageInit() {
	this.nowFinData=null;
	setContentsSection();
	getFindata();
	getRankdata();
	getNewsdata();
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
	if(!this.localStorage.sharesInfo.length>0) {
		setSessionSharesInfo();
	}
	setNewdata();
	addEvent();
}
function checkBadandGood(data) {
	const bad =new RegExp("(급락|하락|붕괴|↓|약보합|하회|약세)");
	const good =  new RegExp("(급반등|급상승|상승|기대||↑|강보합|상회|강세)");
	if(bad.test(data)) {
		return "bad";
	}else if(good.test(data)) {
		return "good";
	}
	return ;
}
function setNewdata(){
	let struct_div ="";
	if(this.newsData!=null) {
		for(let  i = 0; i <this.newsData.length;i++) {
			const badgood = checkBadandGood(this.newsData[i][0]);
			struct_div	+=	`<div class='inner_news_title ${badgood}'>
								<a href='https://finance.naver.com/${this.newsData[i][1]}'>${this.newsData[i][0]}</a>
							</div>`;
		}
		const inputBody = document.getElementsByClassName('inner_news')[0];
		inputBody.innerHTML=struct_div;
	}else {
		console.log("해당 영억없음");
	}
}
function getNewsdata() {
	let outData='';
    const sendingVO = {
            "url" : "main"
        ,   "pharseType" : "news"  
        }
	$.ajax({
		type: 'GET',
		url: '/service/finance/news?',
		data: sendingVO,
        dataType: 'JSON', 
        async: false,
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
    		outData=data;
    		console.log(outData);
        },
        error: function () {
            alert('통신실패!!');
        }
    });
    this.newsData = outData;
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
    const sendingVO = {
            "url" : "infoDTL"
        ,   "pharseType" : "detail"  
        ,	"code" : code
        };
    $.ajax({
        type: 'GET',
        url: `/service/finance/shareInfo`,
        data: sendingVO,
        dataType: 'JSON', 
        async: false,
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
    	shareDetailInfo = data;
		setInfoShareDetail(data.statement);
		let shareName = getKeyByValue(JSON.parse(localStorage.sharesInfo), code);
		history.pushState({'name':shareName,'code':code},'종목상세보기','main');
		window.scrollTo(0,0);
		localStorage.setItem('BeforeScroll',window.scrollY);
		window.scrollY;
		document.getElementById('searchShareInput').value=shareName;
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
	document.querySelector('.toggleHide').style.visibility = 'visible';
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
function toggleHide()	{
	document.getElementById('ext').className = 'btn btn-outline-primary dropdown-toggle dropdown-toggle-split';
	document.getElementById('searchingList').className = 'dropdown-menu shareSearchInput';
	document.getElementById('searchingList').style = "visibility:hidden;";
	document.querySelector('.toggleHide').style.visibility = 'hidden';
}
function onblurShareInputValue()	{
	document.getElementById('ext').className = 'btn btn-outline-primary dropdown-toggle dropdown-toggle-split';
	document.getElementById('searchingList').className = 'dropdown-menu shareSearchInput';
	document.getElementById('searchingList').style = "visibility:hidden;";
}