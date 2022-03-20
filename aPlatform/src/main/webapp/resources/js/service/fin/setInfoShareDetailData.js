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
}

function setInfoShareDetailFrame() {
		let struct_div = `
				<div class='ContentsSectionInfoDTL' id='ContentsSectionInfoDTL'>
					<div class='info_detail_chart'>
						<div class='info_detail_today'></div>
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