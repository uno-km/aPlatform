/**
 * 
 */
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
	for(let i = 0 ; i < document.getElementsByClassName('info_detail_chart_words').length;i++) {
		document.getElementsByClassName('info_detail_chart_words')[i].addEventListener('click',setInfoShareAreaChart);
	};
}

function setInfoShareDetailFrame() {
		let struct_div = `
				<div class='ContentsSectionInfoDTL' id='ContentsSectionInfoDTL'>
					<div class='info_detail_leftarea'>
						<div class='info_detail_today' id='detailToday'>
						</div>
						<div class='info_detail_chartSelector' id='chartSelecter'>
							<div class=''>영역차트</div>
							<div class='info_detail_chart_words'id='area_day'>1일</div>
							<div class='info_detail_chart_words'id='area_week'>1주일</div>
							<div class='info_detail_chart_words'id='area_month'>1개월</div>
							<div class='info_detail_chart_words'id='area_year'>1년</div>
							<div class='info_detail_chart_words'id='area_year3'>3년</div>
							<div class='info_detail_chart_words'id='area_year5'>5년</div>
							<div class='info_detail_chart_words'id='area_year10'>10년</div>
							<div class=''>캔들차트</div>
							<div class='info_detail_chart_words'id='candle_day'>1일</div>
							<div class='info_detail_chart_words'id='candle_week'>1주일</div>
							<div class='info_detail_chart_words'id='candle_month'>1개월</div>
						</div>
						<div class='info_detail_chart' id='detailChart'></div>
					</div>
					<div class='info_detail_outer'>
						<div class = 'info_detail_inner sichongList' id='sichongList'></div>
						<div class = 'info_detail_inner perEpsList' id='perEpsList'></div>
						<div class = 'info_detail_inner forignList' id='forignList'></div>
						<div class = 'info_detail_inner opinionList' id='opinionList'></div>
						<div class = 'info_detail_inner sameList' id='sameList'></div>
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
	inputBody = document.getElementById('ContentsSectionMain');
	inputBody.innerHTML = struct_div;
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
	let struct_div = ``;
	struct_div +=`${shareDetailInfo.forignList[0]} : ${shareDetailInfo.forignList[1]} ${shareDetailInfo.forignList[2]} : `;
	struct_div +=`${shareDetailInfo.forignList[3]}`;
	let inputBody = document.getElementById('forignList');
	inputBody.innerHTML = struct_div;					
}
function setInfoShareDetailOpinionList() {
	let struct_div = ``;
	struct_div +=`투자의견 : ${shareDetailInfo.opinionList[0]} 목표주가 : ${shareDetailInfo.opinionList[1]} 52주 최고 : ${shareDetailInfo.opinionList[2]}`;
	struct_div +=`최저 : ${shareDetailInfo.opinionList[3]}`;
	let inputBody = document.getElementById('opinionList');
	inputBody.innerHTML = struct_div;					
}
function setInfoShareDetailPerEpsList() {
	let struct_div = ``;
	struct_div +=`PER : ${shareDetailInfo.perEpsList[0]} EPS : ${shareDetailInfo.perEpsList[1]} 추정 PER ${shareDetailInfo.perEpsList[2]}`;
	struct_div +=`EPS ${shareDetailInfo.perEpsList[3]} PBR : ${shareDetailInfo.perEpsList[4]} BPS : ${shareDetailInfo.perEpsList[5]}`;
	let inputBody = document.getElementById('perEpsList');
	inputBody.innerHTML = struct_div;					
}
function setInfoShareDetailSichongList() {
	let struct_div = ``;
	struct_div +=`시가총액 : ${shareDetailInfo.sichongList[0]} 순위 : ${shareDetailInfo.sichongList[1]} ${shareDetailInfo.sichongList[2]}`;
	struct_div +=`주식발행 수 ${shareDetailInfo.sichongList[3]} 액면가 ${shareDetailInfo.sichongList[4]}`;
	let inputBody = document.getElementById('sichongList');
	inputBody.innerHTML = struct_div;					
}
function setInfoShareDetailSameList() {
	let struct_div = ``;
	struct_div +=`동일업종 PER : ${shareDetailInfo.sameList[0]} 동일업종 등락률 : ${shareDetailInfo.sichongList[1]}`;
	let inputBody = document.getElementById('sameList');
	inputBody.innerHTML = struct_div;					
}
function setInfoShareToday() {
	let struct_div = ``;
	if(shareDetailInfo.today[1]=='상승') {
		struct_div +=`
				<div class='info_detail_today_contents up' id='todayCost'>${shareDetailInfo.today[0]}원</div>
				<div class='info_detail_today_contents up' id='todayGapCash'>+${shareDetailInfo.today[2]}원</div>
				<div class='info_detail_today_contents up' id='todayGapPer'>${shareDetailInfo.today[3]}%</div>
				`;
	}else if(shareDetailInfo.today[1]=='하락') {
		struct_div +=`
				<div class='info_detail_today_contents down' id='todayCost'>${shareDetailInfo.today[0]}</div>
				<div class='info_detail_today_contents down' id='todayGapCash'>-${shareDetailInfo.today[2]}원</div>
				<div class='info_detail_today_contents down' id='todayGapPer'>${shareDetailInfo.today[3]}%</div>
				`;
	}else {
		struct_div +=`
				<div class='info_detail_today_contents' id='todayCost'>${shareDetailInfo.today[0]}원</div>
				<div class='info_detail_today_contents' id='todayGapCash'>-</div>
				<div class='info_detail_today_contents' id='todayGapPer'>${shareDetailInfo.today[3]}%</div>
				`;
	}
	let inputBody = document.getElementById('detailToday');
	inputBody.innerHTML = struct_div;	
}
function setInfoShareAreaChart(e) {
	if(e==null) { //기본값
		let imgsrc = ``;
		imgsrc = shareDetailInfo.areaChart[0];
		const inputBody= document.getElementById('detailChart');
		inputBody.style.backgroundImage=`URL('${imgsrc}')`;
		inputBody.style.backgroundSize="100% 100%";
		inputBody.style.backgroundPosition="center";
	}else {
		if(e.target.id.substring(0,e.target.id.indexOf('_'))=='area'){
			for(let i = 0 ; i<shareDetailInfo.areaChart.length;i++  ) {
				if(shareDetailInfo.areaChart[i].includes(e.target.id.substring(e.target.id.indexOf('_')+1))) {
					imgsrc = shareDetailInfo.areaChart[i];
					const inputBody= document.getElementById('detailChart');
					inputBody.style.backgroundImage=`URL('${imgsrc}')`;
					inputBody.style.backgroundSize="100% 100%";
					inputBody.style.backgroundPosition="center";
				}
			}
		}else {
			for(let i = 0 ; i<shareDetailInfo.candleChart.length;i++) {
				if(shareDetailInfo.candleChart[i].includes(e.target.id.substring(e.target.id.indexOf('_')+1))) {
					imgsrc = shareDetailInfo.candleChart[i];
					const inputBody= document.getElementById('detailChart');
					inputBody.style.backgroundImage=`URL('${imgsrc}')`;
					inputBody.style.backgroundSize="100% 100%";
					inputBody.style.backgroundPosition="center";
				}
			}
		}
	}
}