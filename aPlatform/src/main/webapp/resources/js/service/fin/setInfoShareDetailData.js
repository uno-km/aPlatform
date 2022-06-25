;
var curSearchShareName = "";
function setInfoShareDetail(data) {
	setInfoShareDetailFrame();
	setInfoShareDetailData(data);
	setInfoShareDetailForignList();
	setInfoShareDetailOpinionList();
	setInfoShareDetailPerEpsList();
	setInfoShareDetailSichongList();
	setInfoShareDetailSameList();
	setInfoShareToday();
	setInfoShareAreaChart();
	for(let i = 0 ; i < document.getElementsByClassName('info_detail_chart_words').length;i++) 
		document.getElementsByClassName('info_detail_chart_words')[i].addEventListener('click',setInfoShareAreaChart);
}
function setInfoShareDetailFrame() {
	document.getElementById('ContentsSectionMain').innerHTML = `
				<div class='ContentsSectionInfoDTL' id='ContentsSectionInfoDTL'>
					<div class='info_detail_leftarea'>
						<div class='info_detail_today' id='detailToday'>
						</div>
						<div class='info_detail_chartSelector' id='chartSelecter'>
							<div class='' style ='color: #0d6efd'>영역차트</div>
							<div class='info_detail_chart_words'id='area_day'>1일</div>
							<div class='info_detail_chart_words'id='area_week'>1주일</div>
							<div class='info_detail_chart_words'id='area_month'>1개월</div>
							<div class='info_detail_chart_words'id='area_year'>1년</div>
							<div class='info_detail_chart_words'id='area_year3'>3년</div>
							<div class='info_detail_chart_words'id='area_year5'>5년</div>
							<div class='info_detail_chart_words'id='area_year10'>10년</div>
							<div class='' style ='color: #0d6efd'>캔들차트</div>
							<div class='info_detail_chart_words'id='candle_day'>1일</div>
							<div class='info_detail_chart_words'id='candle_week'>1주일</div>
							<div class='info_detail_chart_words'id='candle_month'>1개월</div>
						</div>
						<div class='info_detail_chart' id='detailChart'></div>
					</div>
					<div class='info_detail_outer'>
						<div class = 'info_detail_inner' id='sichongList'></div>
						<div class = 'info_detail_inner' id='perEpsList'></div>
						<div class = 'info_detail_inner' id='forignList'></div>
						<div class = 'info_detail_inner' id='opinionList'></div>
						<div class = 'info_detail_inner' id='sameList'></div>
					</div>
				</div>
				<div id='ContentsSectionDTL' style='width: 100%;'>
					<table class="table table-dark table-hover" style='background-color: #394a5c00;'>
						<thead>
							<tr>
								<th scope="col" class='center_uno' style='width: 13%'>주요재무정보</th>
								<th scope="col" colspan="4" class="table-active center_uno">최근 연간 실적</th>
								<th scope="col" colspan="6" class="table-active center_uno">최근 분기 실적</th>
							</tr>
							<tr id='monthList'>
							</tr>
					</thead>
						<tbody id='infoShareDetailData'>
						</tbody>
					</table>
				</div>`;
}

function searchShareInfo(e) {
	let inputData = document.getElementById('searchShareInput').value.replace(/(\s*)/g, "");
	if(inputData.length>0||inputData!="") {
		let sharesInfo = JSON.parse(localStorage.sharesInfo);
		let searchData = sharesInfo[inputData];
		if(searchData == undefined || searchData == null) 
			searchData = sharesInfo["삼성전자"];
		getShareInfoDTL(searchData);
	}
}
function searchShareInfoSearchList(value) {
	document.getElementById('searchingList').style = "visibility:hidden;";
	let sharesInfo = JSON.parse(localStorage.sharesInfo);
	let searchData = sharesInfo[value].replace(/(\s*)/g, "");
	getShareInfoDTL(searchData);
}


function setInfoShareDetailData(data) {
	let struct_div = ``;
	struct_div +='<th scope="col"></th>';
	for(let day = 0 ; day <10;day++) {
		if(day==3) {
			struct_div += `<td class='info_detail_expect'>${data[1][day]}</td>`;
		}else if(day==9){
			struct_div += `<td class='info_detail_expect'>${data[1][day]}</td>`;
		}else {
			struct_div += `<td>${data[1][day]}</td>`;
		}
	}
	let inputBody = document.getElementById('monthList');
	inputBody.innerHTML = struct_div;
	//세부 내용출력
	struct_div =``;
	for(let y =2 ; y <data.length;y++) {
		struct_div += `<tr>`;
		struct_div += `<th scope="row">${data[0][y-2]}</th>`;
		for(let x = 0 ; x<10;x++) {
			if(x==3) {
				if(data[y][x].length<1) {
					struct_div += `<td class='info_detail_expect'>-</td>`;
				}else if(data[y][x]>data[y][x-1]) {
					struct_div += `<td class='info_detail_expect' style='color : #ff3d3d;'>↑ ${data[y][x]}</td>`;
				}else if(data[y][x]<data[y][x-1]) {
					struct_div += `<td class='info_detail_expect' style='color : #a695ff;'>↓ ${data[y][x]}</td>`;
				}else {
					struct_div += `<td class='info_detail_expect'>${data[y][x]}</td>`;
				}
			}else if(x==9){
				if(data[y][x].length<1) {
					struct_div += `<td class='info_detail_expect'>-</td>`;
				}else if(data[y][x]>data[y][x-1]) {
					struct_div += `<td class='info_detail_expect' style='color : #ff3d3d;'>↑ ${data[y][x]}</td>`;
				}else if(data[y][x]<data[y][x-1]) {
					struct_div += `<td class='info_detail_expect' style='color : #a695ff;'>↓ ${data[y][x]}</td>`;
				}else {
					struct_div += `<td class='info_detail_expect'>${data[y][x]}</td>`;
				}
			}else {
				struct_div += `<td>${data[y][x]}</td>`;
			}
		}
		struct_div += `</tr>`;
	}
	inputBody = document.getElementById('infoShareDetailData');
	inputBody.innerHTML = struct_div;
}

function setInfoShareDetailForignList() {
	document.getElementById('forignList').innerHTML = `
				${shareDetailInfo.forignList[0]} : ${shareDetailInfo.forignList[1]} <br>
				${shareDetailInfo.forignList[2]} : ${shareDetailInfo.forignList[3]}`;
}
function setInfoShareDetailOpinionList() {
	document.getElementById('opinionList').innerHTML = `
				투자의견 : ${shareDetailInfo.opinionList[0]} <br>
				목표주가 : ${shareDetailInfo.opinionList[1]} <br>
				52주 최고 : ${shareDetailInfo.opinionList[2]}	최저 : ${shareDetailInfo.opinionList[3]}`;
}
function setInfoShareDetailPerEpsList() {
	document.getElementById('perEpsList').innerHTML =`
					PER : ${shareDetailInfo.perEpsList[0]} EPS : ${shareDetailInfo.perEpsList[1]} <br>
					추정 PER ${shareDetailInfo.perEpsList[2]} EPS ${shareDetailInfo.perEpsList[3]} <br>
					PBR : ${shareDetailInfo.perEpsList[4]} BPS : ${shareDetailInfo.perEpsList[5]}`;
}
function setInfoShareDetailSichongList() {
	document.getElementById('sichongList').innerHTML = `
				<strong>시가총액</strong> : ${shareDetailInfo.sichongList[0]} <br> 순위 : ${shareDetailInfo.sichongList[1]} ${shareDetailInfo.sichongList[2]}  <br>
				주식발행 수 : ${shareDetailInfo.sichongList[3]}
				<!-- 액면가 ${shareDetailInfo.sichongList[4]} -->`;
}
function setInfoShareDetailSameList() {
	document.getElementById('sameList').innerHTML = `
				동일업종 PER : ${shareDetailInfo.sameList[0]} <br>
				동일업종 등락률 : ${shareDetailInfo.sameList[1]}`;
}
function setInfoShareToday() {
	let struct_div = ``;
	let curStts = 'noneUno';
	if(shareDetailInfo.today[1]=='상승') curStts='up';
	if(shareDetailInfo.today[1]=='하락') curStts='down';
	document.getElementById('detailChart').className = `info_detail_chart ${curStts}`;
	document.getElementById('detailToday').innerHTML = `
			<div class='info_detail_today_contents ${curStts}' id='shareName'>${curSearchShareName}</div>
			<div class='info_detail_today_contents ${curStts}' id='todayCost'>${shareDetailInfo.today[0]}원</div>
			<div class='info_detail_today_contents ${curStts}' id='todayGapCash'>+${shareDetailInfo.today[2]}원</div>
			<div class='info_detail_today_contents ${curStts}' id='todayGapPer'>${shareDetailInfo.today[3]}%</div>
			`;
	for(let i = 0 ; i < document.getElementsByClassName('info_detail_inner').length ; i++) {
		document.getElementsByClassName('info_detail_inner')[i].className = `info_detail_inner ${curStts}`;
	}

}
function setInfoShareAreaChart(e) {
	if(e==null) { //기본값
		let imgsrc = ``;
		imgsrc = shareDetailInfo.areaChart[0];
		inPutBody(imgsrc);
	}else {
		if(e.target.id.substring(0,e.target.id.indexOf('_'))=='area'){
			for(let i = 0 ; i<shareDetailInfo.areaChart.length;i++  ) {
				if(shareDetailInfo.areaChart[i].includes(e.target.id.substring(e.target.id.indexOf('_')+1))) {
					imgsrc = shareDetailInfo.areaChart[i];
					inPutBody(imgsrc);
				}
			}
		}else {
			for(let i = 0 ; i<shareDetailInfo.candleChart.length;i++) {
				if(shareDetailInfo.candleChart[i].includes(e.target.id.substring(e.target.id.indexOf('_')+1))) {
					imgsrc = shareDetailInfo.candleChart[i];
					inPutBody(imgsrc);
				}
			}
		}
	}
	function inPutBody(imgsrc) {
		const inputBody= document.getElementById('detailChart');
		inputBody.style.backgroundImage=`URL('${imgsrc}')`;
		inputBody.style.backgroundSize="100% 100%";
		inputBody.style.backgroundPosition="center";
	}
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
			let shareName = getKeyByValue(JSON.parse(localStorage.sharesInfo), code);
			curSearchShareName = shareName;
			setInfoShareDetail(data.statement);
			history.pushState({'name':shareName,'code':code},'종목상세보기','main');
			window.scrollTo(0,0);
			localStorage.setItem('BeforeScroll',window.scrollY);
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
    	if(document.getElementById('searchShareInput').value>0||document.getElementById('searchShareInput').value!="") {
    		searchShareInfo();
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
	onblurShareInputValue();
	document.querySelector('.toggleHide').style.visibility = 'hidden';
}
function onblurShareInputValue()	{
	document.getElementById('ext').className = 'btn btn-outline-primary dropdown-toggle dropdown-toggle-split';
	document.getElementById('searchingList').className = 'dropdown-menu shareSearchInput';
	document.getElementById('searchingList').style = "visibility:hidden;";
}